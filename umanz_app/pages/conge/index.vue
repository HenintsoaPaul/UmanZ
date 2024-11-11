<script setup lang="ts">
import axios from 'axios';
import type { Conge, Entretien } from '~/types';

const headers = [
    {
        key: 'idConge',
        label: 'ID',
        sortable: true
    },
    {
        key: 'idContrat',
        label: 'Contrat',
        sortable: true
    },
    {
        key: 'dateDebut',
        label: 'Date Debut',
        sortable: true
    },
    {
        key: 'nbJour',
        label: 'Nombre Jour',
        sortable: true
    },
    {
        key: 'dateValidation',
        label: 'Date Validation',
    }
];

// Variable pour stocker les données de l'API
const lignes = ref<Conge[]>([]);
const q = ref('');

const filteredLignes = computed(() => {
    return q.value ?
        lignes.value.filter((e: Conge) =>
            Object.values(e).some((value) =>
                String(value).toLowerCase().includes(q.value.toLowerCase())
            )
        ) : lignes.value;
});

async function loadConge() {
    try {
        const apiUrl: string = useRuntimeConfig().public.apiUrl as string;
        const response = await axios.get(`${apiUrl}/conge`);

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

loadConge();

const expand = ref({
    openedRows: [],
    row: {}
})

const validerFn = async (idConge: number, idContrat: number) => {
    const apiUrl = useRuntimeConfig().public.apiUrl;
    try {
        const params = {
            idConge: idConge,
            idContrat: idContrat
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
</script>

<template>
    <div class="container mx-auto">
        <h1 class="text-3xl font-bold mb-4 text-center">Liste des Conges</h1>

        <div class="flex justify-center mb-4">
            <UInput v-model="q" placeholder="Filtrer les conges..." class="w-full max-w-md px-4 py-2 rounded-lg" />
        </div>

        <UTable :columns="headers" :rows="filteredLignes" v-model:expand="expand">
            <template #expand="{ row }">
                <div class="p-4">
                    <button class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded"
                        @click="validerFn(row.idConge, row.idContrat)">
                        Valider Conger
                    </button>
                </div>
            </template>
        </UTable>
    </div>
</template>
