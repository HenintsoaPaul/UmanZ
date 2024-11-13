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
        label: 'Talent',
        sortable: true
    },
    {
        key: 'dateCreation',
        label: 'Date Creation',
        sortable: true
    },
];

const apiUrl = useRuntimeConfig().public.apiUrl as string;
const etatEntretien = ref<number>(3);
const url = computed(() => `${apiUrl}/entretien/etat/${etatEntretien.value}`);

const { data: entretiens, error: entretiensError, refresh: refreshEntretiens } = useFetch<Entretien[]>(`${url.value}`);

// Variable pour stocker les données de l'API
const q = ref('');
const filteredLignes = computed(() => {
    return q.value ?
        entretiens.value?.filter((e: Entretien) =>
            Object.values(e).some((value) =>
                String(value).toLowerCase().includes(q.value.toLowerCase())
            )
        ) : entretiens.value;
});

const expand = ref({
    openedRows: [],
    row: {}
})

const validerFn = async (entretien: Entretien) => {
    if ( entretien.note <= 0 ) return;

    try {
        const response = await $fetch(`${apiUrl}/entretien/validate/first`, {
            method: 'POST',
            body: {
                idEntretien: entretien.idEntretien,
                note: entretien.note
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
            <UInput v-model="q" placeholder="Filtrer les annonces..." class="w-full max-w-md px-4 py-2 rounded-lg" />
        </div>

        <template v-if="entretiens">
            <UTable :columns="headers" :rows="filteredLignes" v-model:expand="expand">
                <template #expand="{ row }">
                    <div class="p-4">
                        <UInput v-model="row.note" placeholder="Ajouter la note..."
                            class="w-full max-w-md px-4 py-2 rounded-lg" />
    
                        <button class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded"
                            @click="validerFn(row)"
                            :disabled="row.note <= 0" >
                            Valider
                        </button>
                        <button class="bg-red-500 hover:bg-red-700 text-white font-bold py-2 px-4 rounded"
                            @click="refuserFn(row)">
                            Refuser
                        </button>
                    </div>
                </template>
            </UTable>
        </template>
    </div>
</template>
