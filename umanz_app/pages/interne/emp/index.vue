<script setup lang="ts">
definePageMeta({
    middleware: ['auth-is-admin']
})

import { ref } from 'vue';
import RuptureExpend from '~/components/contrat/rupture/RuptureExpend.vue';
import type { Contrat } from '~/types/interne/contrat';

const headers = [
    { key: 'idContrat', label: 'ID', sortable: true },
    { key: 'talent.nomPrenom', label: 'Nom et Prenoms' },
    { key: 'poste.typePoste.typePoste', label: 'Catégorie' },
    { key: 'dateDebut', label: 'Date Debut', sortable: true },
    { key: 'dateFin', label: 'Date Fin', sortable: true },
];

const apiUrl = useRuntimeConfig().public.apiUrl as string;
const { data: contrats } = useFetch<Contrat[]>(`${apiUrl}/contrats/now`);

const { q, filteredRows: filteredContrats } = useFilteredRows(contrats);
const expand = ref({
    openedRows: [],
    row: {}
});

const router = useRouter();

async function licencierEmp(contrat: Contrat) {
    router.push(`/interne/rupture/${contrat.idContrat}`);
}

async function goToCvEmp(contrat: Contrat) {
    console.log("cv");
    router.push(`/talent/${contrat.talent.idTalent}`);
}
</script>

<template>
    <div class="container mx-auto">
        <h1 class="text-3xl font-bold mb-4 text-center">Nos employes du moment</h1>

        <div class="flex justify-center mb-4">
            <UInput v-model="q" placeholder="Filtrer les employes..." class="w-full max-w-md px-4 py-2 rounded-lg" />
        </div>

        <UTable :columns="headers" :rows="filteredContrats ?? []" v-model:expand="expand">
            <template #expand="{ row }">
                <div class="p-4">
                    <RuptureExpend :contrat="row" @licencier="licencierEmp" @cv="goToCvEmp" />
                </div>
            </template>
        </UTable>
    </div>
</template>
