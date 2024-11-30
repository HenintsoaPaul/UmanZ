<script setup lang="ts">
definePageMeta({
    middleware: ['auth-is-admin']
})

import type { Conge } from '~/types';
import { ref, computed, toRaw } from 'vue';
import DemandeCongeExpend from '~/components/conge/DemandeCongeExpend';

const headers = [
    { key: 'idConge', label: 'ID', sortable: true },
    { key: 'contrat.idContrat', label: 'Contrat', sortable: true },
    { key: 'dateDebut', label: 'Date Debut', sortable: true },
    { key: 'nbJour', label: 'Nb Jour', sortable: true },
    { key: 'solde', label: 'Solde' },
    { key: 'contrat.poste.nomPoste', label: 'Poste' },
    { key: 'typeConge.nomTypeConge', label: 'Type' },
];

const apiUrl = useRuntimeConfig().public.apiUrl as string;
const { data: conges, refresh: refreshConges } = useFetch<Conge[]>(`${apiUrl}/conges/needs-validation`);

const { q, filteredRows: filteredConges } = useFilteredRows(conges);
const expand = ref({
    openedRows: [],
    row: {}
});

const { validerCongerFn, refuserCongerFn } = useCongeActions();
async function validerFn(conge: Conge) {
    await validerCongerFn(conge.idConge, apiUrl);
    refreshConges();
}
async function refuserFn(conge: Conge) {
    const motifRefus: string = conge.motifRefus;
    if (motifRefus.length <= 0) {
        console.error("MotifRefus is required");
        return;
    }
    await refuserCongerFn(conge.idConge, conge.motifRefus, apiUrl);
    refreshConges();
}
</script>

<template>
    <div class="container mx-auto">
        <h1 class="text-3xl font-bold mb-4 text-center">Liste des Congés En Attente de validation</h1>

        <div class="flex justify-center mb-4">
            <UInput v-model="q" placeholder="Filtrer les congés..." class="w-full max-w-md px-4 py-2 rounded-lg" />
        </div>

        <UTable :columns="headers" :rows="filteredConges ?? []" v-model:expand="expand">
            <template #expand="{ row }">
                <div class="p-4">
                    <DemandeCongeExpend :conge="row" :api-url="apiUrl" @valider="validerFn" @refuser="refuserFn" />
                </div>
            </template>
        </UTable>
    </div>
</template>
