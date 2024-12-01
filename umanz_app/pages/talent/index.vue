<script setup lang="ts">
import type { Talent } from '~/types';

const headers = [
  { key: 'idTalent', label: 'ID', sortable: true },
  { key: 'nom', label: 'Nom', sortable: true },
  { key: 'prenom', label: 'Prénom', sortable: true },
  { key: 'mail', label: 'Email', sortable: true }
];

const apiUrl = useRuntimeConfig().public.apiUrl as string;
const { data: talents } = useFetch<Talent[]>(`${apiUrl}/talents`);

const { q, filteredRows: filteredTalents } = useFilteredRows(talents);

const expand = ref({
  openedRows: [],
  row: {}
})

</script>

<template>
    <h1 class="text-2xl font-bold mb-4">Liste des talents</h1>

    <div class="flex px-3 py-3.5 border-b border-gray-200 dark:border-gray-700">
        <UInput v-model="q" placeholder="Filtrer les talents..." />
    </div>

    <UTable :columns="headers" :rows="filteredTalents ?? []" v-model:expand="expand">
        <template #expand="{ row }">
            <UButton @click="$router.push(`/interne/evaluation/projet/note/${row.idTalent}`)">
              Note d'évaluations de projet
            </UButton>
            <UButton @click="$router.push(`/talent/${row.idTalent}`)">
                Voir le profil
            </UButton>
        </template>
    </UTable>
</template>
