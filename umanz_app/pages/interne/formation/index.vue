<script setup lang="ts">
import type { Formation } from '~/types';

const { participerFn, headers } = useFormationActions();
const apiUrl = useRuntimeConfig().public.apiUrl as string;
const { data: formations } = useFetch<Formation[]>(`${apiUrl}/formations/disponible`);
const { q, filteredRows: filteredFormations } = useFilteredRows(formations);

const expand = ref({
    openedRows: [],
    row: {}
});

const idContrat = computed(() => Number(localStorage.getItem("idUser")));
</script>

<template>
    <div class="container mx-auto">
        <h1 class="text-3xl font-bold mb-6 text-center">Formations Disponibles</h1>

        <div class="flex justify-center mb-4">
            <UInput v-model="q" placeholder="Filtrer les formations..."
                class="w-full max-w-md px-4 py-2  rounded-lg shadow-sm focus:outline-none focus:ring-2 focus:ring-blue-500" />
        </div>

        <UTable :columns="headers" :rows="filteredFormations ?? []" v-model:expand="expand"
            class="w-full shadow-md rounded-lg overflow-hidden">
            <template #expand="{ row }">
                <div class="p-4">
                    <FormationDetails :api-url="apiUrl" :formation="row" :id-contrat="idContrat"
                        :participer-fn="participerFn" />
                </div>
            </template>
        </UTable>
    </div>
</template>
