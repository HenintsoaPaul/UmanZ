<script setup lang="ts">
import { computed } from 'vue';

interface Domaine{
    idDomaine: number;
    domaine: string;
}

interface Resultat {
  idResultat: number; // Identifiant unique du resultat
  note: number; // Note
  dateResultat: Date;
  domaine: Domaine;
}
const idTalent = computed(() => localStorage.getItem('umanz-idUser') ?? "0");

const apiUrl = useRuntimeConfig().public.apiUrl as string;
const { data: resultats } = useFetch<Resultat[]>(`${apiUrl}/resultat/talent/${idTalent.value}`);

const headers = [
    {
        key: 'idResultat',
        label: 'ID',
        sortable: true,
    },
    {
        key: 'note',
        label: 'Note/5',
        sortable: true
    },
    {
        key: 'dateResultat',
        label: 'Date évaluation',
        sortable: true
    },
    {
        key: 'domaine.domaine',
        label: 'Domaine',
    }
];

const { q, filteredRows: filteredResultats } = useFilteredRows(resultats);
</script>

<template>
    <div class="container mx-auto">
        <h1 class="text-3xl font-bold mb-6 text-center">Résultats des évaluations</h1>

        <div class="flex justify-center mb-4">
            <UInput v-model="q" placeholder="Filtrer les résultats..."
                class="w-full max-w-md px-4 py-2  rounded-lg shadow-sm focus:outline-none focus:ring-2 focus:ring-blue-500" />
        </div>

        <div v-if="resultats">
            <div v-if="resultats.length > 0">
                <UTable :columns="headers" :rows="filteredResultats ?? []"
                    class="w-full shadow-md rounded-lg overflow-hidden">
                </UTable>
            </div>
            <div v-else>
                Aucune évaluation pour le moment 😅
            </div>
        </div>
        <div v-else>
            Chargement des évaluations...
        </div>
    </div>
</template>
