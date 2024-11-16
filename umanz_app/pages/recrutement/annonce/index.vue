<script setup lang="ts">
import type { Annonce } from '~/types';

const { canditerFn, headers } = useAnnonceActions();
const idTalent = localStorage.getItem('idUser') ?? "0";

const apiUrl = useRuntimeConfig().public.apiUrl as string;
const { data: annonces } = useFetch<Annonce[]>(`${apiUrl}/annonce/disponible`);

const { q, filteredRows: filteredAnnonces } = useFilteredRows(annonces);
const expand = ref({
    openedRows: [],
    row: {}
});
</script>

<template>
    <div class="container mx-auto">
        <h1 class="text-3xl font-bold mb-6 text-center">Liste des annonces</h1>

        <div class="flex justify-center mb-4">
            <UInput v-model="q" placeholder="Filtrer les annonces..."
                class="w-full max-w-md px-4 py-2  rounded-lg shadow-sm focus:outline-none focus:ring-2 focus:ring-blue-500" />
        </div>

        <div v-if="annonces">
            <UTable :columns="headers" :rows="filteredAnnonces ?? []" v-model:expand="expand"
                class="w-full shadow-md rounded-lg overflow-hidden">
                <template #expand="{ row }">
                    <div class="p-4">
                        <div class="mb-4">
                            <h3 class="text-xl font-semibold mb-2">Description:</h3>
                            <p class="text-gray-700">{{ row.poste.description }}</p>
                        </div>
    
                        <button class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded"
                            @click="canditerFn(row.idAnnonce, idTalent, apiUrl)">
                            Candidater
                        </button>
    
                        <button class="bg-green-500 hover:bg-green-700 text-white font-bold py-2 px-4 rounded ml-2"
                            @click="$router.push(`/recrutement/annonce/${row.idAnnonce}`)">
                            Voir Détails
                        </button>
                    </div>
                </template>
            </UTable>
        </div>
        <div v-else>
            Loading Annonces...
        </div>
    </div>
</template>
