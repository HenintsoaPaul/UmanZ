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
        if (response.status === 200) {
            apiTalents.value = response.data;
        } else {
            console.error('Erreur lors de la récupération des talents');
        }
    } catch (error) {
        console.error('Erreur lors de la requête API:', error);
    }
}

// Appel de la fonction pour charger les données au chargement du composant
onMounted(() => {
    loadTalents();
});

// Filtre par recherche
const q = ref('');

const filteredTalents = computed(() => {
    if (!q.value) {
        return apiTalents.value;
    }

    return apiTalents.value.filter((talent) => {
        return Object.values(talent).some((value) => {
            return String(value).toLowerCase().includes(q.value.toLowerCase());
        });
    });
});

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
        </template>
    </UTable>
</template>
