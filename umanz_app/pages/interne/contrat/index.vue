<script setup lang="ts">
import type { Contrat } from '~/types';

const { promotionFn, expulsionFn } = useContratActions();
const apiUrl = useRuntimeConfig().public.apiUrl as string;
const { data: contrats, error: contratError } = useFetch<Contrat[]>(`${apiUrl}/contrat`);

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
        key: 'poste.nomPoste',
        label: 'Poste',
    }
];

const q = ref('');
const filteredLignes = computed(() => {
    return q.value ?
        contrats.value?.filter((e: Contrat) =>
            Object.values(e).some((value) =>
                String(value).toLowerCase().includes(q.value.toLowerCase())
            )
        ) : contrats.value;
});

const expand = ref({
    openedRows: [],
    row: {}
})
</script>

<template>
    <div class="container mx-auto">
        <h1 class="text-3xl font-bold mb-6 text-center">Liste des Contrats</h1>

        <div class="flex justify-center mb-4">
            <UInput v-model="q" placeholder="Filtrer les annonces..."
                class="w-full max-w-md px-4 py-2  rounded-lg shadow-sm focus:outline-none focus:ring-2 focus:ring-blue-500" />
        </div>

        <UTable :columns="headers" :rows="filteredLignes" v-model:expand="expand"
            class="w-full shadow-md rounded-lg overflow-hidden">
            <template #expand="{ row }">
                <ContratDetails :contrat="row" :promotion-fn="promotionFn" :explusion-fn="expulsionFn" />
            </template>
        </UTable>
    </div>
</template>
