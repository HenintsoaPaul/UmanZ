<script setup lang="ts">
import axios from 'axios';
import type { Entretien } from '~/types';

const headers = [
    {
        key: 'id_entretien',
        label: 'ID',
        sortable: true
    },
    {
        key: 'id_talent',
        label: 'Talent',
        sortable: true
    },
    {
        key: 'date_creation',
        label: 'Date Creation',
        sortable: true
    },
    {
        key: 'date_validation',
        label: 'Date Validation',
        sortable: true
    },
    {
        key: 'note',
        label: 'Note',
        sortable: true
    },
    {
        key: 'motif',
        label: 'Motif',
        sortable: true
    }
];

// Variable pour stocker les données de l'API
const lignes = ref<Entretien[]>([]);
const q = ref('');

const filteredLignes = computed(() => {
    return q.value ?
        lignes.value.filter((e: Entretien) =>
            Object.values(e).some((value) =>
                String(value).toLowerCase().includes(q.value.toLowerCase())
            )
        ) : lignes.value;
});

async function loadAnnonces() {
    try {
        const apiUrl: string = "http://localhost:911";

        const response = await axios.get(`${apiUrl}/entretiens`);

        if (response.status === 200 && Array.isArray(response.data)) {
            console.log(toRaw(response.data));

            lignes.value = response.data;
        } else {
            console.error('Erreur lors de la récupération des annonces', response.data);
        }
    } catch (error) {
        console.error('Erreur lors de la requête API:', error);
    }
}

loadAnnonces();

const expand = ref({
    openedRows: [],
    row: {}
})

const note = ref<number>(0);

const validerFn = async (id_entretien: number) => {
    if (!note.value) {
        const apiUrl = useRuntimeConfig().public.apiUrl;
        try {
            const params = {
                note: note.value,
                id_entretien: id_entretien
            }

console.log(params);


            const response = await axios.post(`${apiUrl}/entretien/candiat`, params);

            if (response.status === 200) {
                console.log('Valiation envoyée avec succès', params);
            } else {
                console.error('Erreur lors de la validation', response.data);
            }
        } catch (error) {
            console.error('Erreur lors de la requête API:', error);
        }
    }
}
</script>

<template>
    <div class="container mx-auto">
        <h1 class="text-3xl font-bold mb-4 text-center">Liste des Entretiens</h1>

        <div class="flex justify-center mb-4">
            <UInput v-model="q" placeholder="Filtrer les annonces..." class="w-full max-w-md px-4 py-2 rounded-lg" />
        </div>

        <UTable :columns="headers" :rows="filteredLignes" v-model:expand="expand">
            <template #expand="{ row }">
                <div class="p-4">
                    <UInput v-model="note" placeholder="Ajouter la note..."
                        class="w-full max-w-md px-4 py-2 rounded-lg" />


                    <button class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded"
                        @click="validerFn(row.id_entretien)">
                        Valider
                    </button>
                </div>
            </template>
        </UTable>
    </div>
</template>
