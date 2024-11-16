<script setup lang="ts">
import type { Entretien } from '~/types';

const headers = [
    {
        key: 'idEntretien',
        label: 'ID',
        sortable: true
    },
    {
        key: 'talent.idTalent',
        label: 'Talent'
    },
    {
        key: 'etatEntretien.etatEntretien',
        label: 'Status'
    },
    {
        key: 'dateCreation',
        label: 'Date Creation',
        sortable: true
    },
    {
        key: 'dateValidation',
        label: 'Date Validation',
        sortable: true
    },
];

const apiUrl = useRuntimeConfig().public.apiUrl as string;
const etatEntretien = ref<number>(3);
const url = computed(() => `${apiUrl}/entretien/etat/${etatEntretien.value}`);
const { data: entretiens, error: entretiensError, refresh: refreshEntretiens } = useFetch<Entretien[]>(`${url.value}`);

const { q, filteredRows: filteredEntretiens } = useFilteredRows(entretiens);
const expand = ref({
    openedRows: [],
    row: {}
});


const validerFn = async (entretien: Entretien) => {
    if (entretien.note <= 0 || entretien.note >= 20) return;

    try {
        const response = await $fetch(`${apiUrl}/entretien/validate`, {
            method: 'POST',
            body: {
                idEntretien: entretien.idEntretien,
                note: entretien.note,
                motif: entretien.motif
            }
        });
        console.log('Entretien validé:', response);
        await refreshEntretiens();
    } catch (error) {
        console.error('Erreur lors de la validation de l\' Entretien:', error);
    }
}

const refuserFn = async (entretien: Entretien) => {
    try {
        const response = await $fetch(`${apiUrl}/entretien/deny`, {
            method: 'POST',
            body: {
                idEntretien: entretien.idEntretien,
            }
        });
        console.log('Entretien refusé:', response);
        await refreshEntretiens()
    } catch (error) {
        console.error('Erreur lors du refus de la validation:', error);
    }
}
</script>

<template>
    <div class="container mx-auto">
        <h1 class="text-3xl font-bold mb-4 text-center">Liste des Entretiens</h1>

        <div class="flex justify-center mb-4">
            <UInput v-model="q" placeholder="Filtrer les entretiens..." class="w-full max-w-md px-4 py-2 rounded-lg" />
        </div>

        <template v-if="entretiens">
            <UTable :columns="headers" :rows="filteredEntretiens ?? []" v-model:expand="expand">
                <template #expand="{ row }">
                    <div class="p-4">
                        <div class="flex">
                            <div class="w-1/2">
                                <div class="mb-4">
                                    <h3 class="text-xl font-semibold mb-2">Poste:</h3>
                                    <p class="text-gray-700">{{ row.annonce.poste.nomPoste }}</p>
                                </div>
                                <div class="mb-4">
                                    <h3 class="text-xl font-semibold mb-2">Description:</h3>
                                    <p class="text-gray-700">{{ row.annonce.poste.description }}</p>
                                </div>
                                <div class="mb-4">
                                    <h3 class="text-xl font-semibold mb-2">Intervalle de postulation:</h3>
                                    <p class="text-gray-700">{{ row.annonce.dateAnnonce }} - {{
                                        row.annonce.dateExpiration }}
                                    </p>
                                </div>
                            </div>

                            <div class="w-1/2">
                                <div class="mb-4">
                                    <p class="text-gray-500">
                                        <strong class="text-white">Nom:</strong> {{ row.talent.nom }} {{ row.talent.prenom }}
                                    </p>
                                </div>
                                <div class="mb-4">
                                    <p class="text-gray-500">
                                        <strong class="text-white">Email:</strong> {{ row.talent.mail }}
                                    </p>
                                </div>

                                <UInput v-model="row.note" type="number" min="0" max="20"
                                    placeholder="Ajouter la note..." class="w-full max-w-md py-2 rounded-lg" />
                                <UInput v-model="row.motif" type="text" placeholder="Ajouter motif..."
                                    class="w-full max-w-md py-2 rounded-lg" />

                                <button class="bg-green-500 hover:bg-green-700 text-white font-bold py-2 px-4 rounded"
                                    @click="validerFn(row)" :disabled="row.note <= 0">
                                    Valider
                                </button>

                                <button class="bg-red-500 hover:bg-red-700 text-white font-bold py-2 px-4 rounded ml-2"
                                    @click="refuserFn(row)">
                                    Refuser
                                </button>

                                <button
                                    class="bg-indigo-500 hover:bg-indigo-700 text-white font-bold py-2 px-4 rounded ml-2"
                                    @click="$router.push(`/talent/${row.talent.idTalent}`)">
                                    Consulter CV
                                </button>
                            </div>
                        </div>
                    </div>
                </template>
            </UTable>
        </template>
    </div>
</template>
