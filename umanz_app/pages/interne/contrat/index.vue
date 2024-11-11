<script setup lang="ts">
import axios from 'axios';
import type { Contrat } from '~/types';

const headers = [
    {
        key: 'idContrat',
        label: 'ID',
        sortable: true,
    },
    {
        key: 'dateDebut',
        label: 'Date Debut',
        sortable: true
    },
    {
        key: 'dateFin',
        label: 'Date Fin',
        sortable: true
    },
    {
        key: 'salaireHoraire',
        label: 'Sal/Heure',
        sortable: true
    },

    // nbjourtravail , nbHeuretravail

    // {
    //     key: 'dateFin',
    //     label: 'Date Fin',
    //     sortable: true
    // },
    // {
    //     key: 'dateFin',
    //     label: 'Date Fin',
    //     sortable: true
    // },
];

// Variable pour stocker les données de l'API
const lignes = ref<Contrat[]>([]);
const q = ref('');

const filteredLignes = computed(() => {
    return q.value ?
        lignes.value.filter((e: Contrat) =>
            Object.values(e).some((value) =>
                String(value).toLowerCase().includes(q.value.toLowerCase())
            )
        ) : lignes.value;
});

const expand = ref({
    openedRows: [],
    row: {}
})



const contrats = ref<Contrat[]>([]);

async function loadContrats() {
    try {
        const apiUrl: string = useRuntimeConfig().public.apiUrl as string;
        const response = await axios.get(`${apiUrl}/contrats/en-cours`);
        contrats.value = response.data;
    } catch (error) {
        console.error('Erreur lors du chargement des contrats:', error);
    }
}

onMounted(() => {
    loadContrats();
})

const promotionFn = async (idContrat: number) => {
    // TODO: Implement
    console.log("Not defined");
}

const expulsionFn = async (idContrat: number) => {
    // TODO: Implement
    console.log("Not defined");
}
</script>

<template>
    <div class="container mx-auto">
        <h1 class="text-3xl font-bold mb-6 text-center">Liste des annonces</h1>

        <div class="flex justify-center mb-4">
            <UInput v-model="q" placeholder="Filtrer les annonces..."
                class="w-full max-w-md px-4 py-2  rounded-lg shadow-sm focus:outline-none focus:ring-2 focus:ring-blue-500" />
        </div>

        <UTable :columns="headers" :rows="filteredLignes" v-model:expand="expand"
            class="w-full shadow-md rounded-lg overflow-hidden">
            <template #expand="{ row }">
                <div class="p-4">
                    <div class="mb-4">
                        <h3 class="text-xl font-semibold mb-2">Titre:</h3>
                        <p class="text-gray-700">{{ row.nomContrat }}</p>
                    </div>

                    <button class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded"
                        @click="promotionFn(row.idContrat)">
                        Promotion
                    </button>

                    <button class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded"
                        @click="expulsionFn(row.idContrat)">
                        Explusion
                    </button>
                </div>
            </template>
        </UTable>
    </div>
</template>
