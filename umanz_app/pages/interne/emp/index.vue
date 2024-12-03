<script setup lang="ts">
definePageMeta({
    middleware: ['auth-is-admin']
})

import { ref } from 'vue';
import RuptureExpend from '~/components/contrat/rupture/RuptureExpend.vue';
import type { Rupture } from '~/types';
import type { Contrat } from '~/types/interne/contrat';

const headers = [
    { key: 'idContrat', label: 'ID', sortable: true },
    { key: 'talent.nomPrenom', label: 'Nom et Prenoms' },
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

async function licencierEmp(contrat: Contrat) {
    console.log("licencier ... \ntokony misy redirection vers le form");
    
    const payLoad = {
        idContrat: contrat.idContrat,
        motif: "fuf",
        date: new Date(),
        email: "henintsoapaul@gmail.com"
    }

    try {
        const response = $fetch<Rupture>(`${apiUrl}/ruptures/renvoi`, {
            method: 'POST',
            body: payLoad,
        });
        console.log('Renvoi request sent successfully', response);
    }
    catch (error) {
        console.error('Failed to send Renvoi request', error);
    }
}

async function goToCvEmp(contrat: Contrat) {
    console.log("cv");
    const router = useRouter();
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
