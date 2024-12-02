<script setup lang="ts">
import type { Absence } from '~/types/interne/absence';

const headers = [
    { key: 'idAbsence', label: 'ID', sortable: true },
    { key: 'nomPrenom', label: 'NOM et Prénoms' },
    { key: 'dateAbsence', label: "Date d'Absence", sortable: true },
    { key: 'motif', label: "Motif", sortable: true },
];

const apiUrl = useRuntimeConfig().public.apiUrl as string;
const { data: absences } = useFetch<Absence[]>(`${apiUrl}/absences`);

const { q, filteredRows: filteredAbsences } = useFilteredRows(absences);
</script>

<template>
    <div class="container mx-auto">
        <h1 class="text-2xl font-bold mb-6 text-center">Liste des absences</h1>

        <div class="flex justify-center mb-4">
            <UInput v-model="q" placeholder="Filtrer les absences..." />
        </div>

        <UTable :columns="headers" :rows="filteredAbsences ?? []" />
    </div>
</template>