<script setup lang="ts">
import type { Contrat } from '~/types/interne/contrat';

const { promotionFn, expulsionFn, demissionFn } = useContratActions();
const currency = useRuntimeConfig().public.currency as string;

const apiUrl = useRuntimeConfig().public.apiUrl as string;
const { data: contrats } = useFetch<Contrat[]>(`${apiUrl}/contrats/now`);

const headers = [
    { key: 'idContrat', label: 'ID', sortable: true },
    { key: 'talent.nomPrenom', label: 'Nom et Prenoms' },
    { key: 'dateDebut', label: 'Date Debut', sortable: true },
    { key: 'dateFin', label: 'Date Fin', sortable: true },
];

const { q, filteredRows: filteredContrats } = useFilteredRows(contrats);
const expand = ref({
    openedRows: [],
    row: {}
});
</script>

<template>
    <div class="container mx-auto">
        <h1 class="text-3xl font-bold mb-6 text-center">Liste des Contrats</h1>

        <div class="flex justify-center mb-4">
            <UInput v-model="q" placeholder="Filtrer les contrats..."
                class="w-full max-w-md px-4 py-2  rounded-lg shadow-sm focus:outline-none focus:ring-2 focus:ring-blue-500" />
        </div>

        <div v-if="contrats">
            <div v-if="contrats.length > 0">
                <UTable :columns="headers" :rows="filteredContrats ?? []" v-model:expand="expand"
                    class="w-full shadow-md rounded-lg overflow-hidden">
                    <template #expand="{ row }">
                        <ContratDetails :contrat="row" :currency="currency" :promotion-fn="promotionFn" :explusion-fn="expulsionFn" :demission-fn="demissionFn"
                            :api-url="apiUrl" />
                    </template>
                </UTable>
            </div>
            <div v-else>
                Aucun contrats pour le moment ... 😅
            </div>
        </div>
        <div v-else>
            Loading Contrats...
        </div>
    </div>
</template>
