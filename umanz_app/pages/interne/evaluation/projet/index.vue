<script setup lang="ts">
import axios from 'axios';
import type {Evaluation} from '~/types';

const headers = [
  {
    key: 'idEvaluation',
    label: 'ID',
  },
  {
    key: 'dateEvaluation',
    label: 'Date',
    sortable: true
  },
  {
    key: 'talent.nom',
    label: 'Candidat',
    sortable: true
  }
];

// Variable pour stocker les données de l'API
const lignes = ref<Evaluation[]>([]);
const q = ref('');

const filteredLignes = computed(() => {
  return q.value ?
      lignes.value.filter((e: Evaluation) =>
          Object.values(e).some((value) =>
              String(value).toLowerCase().includes(q.value.toLowerCase())
          )
      ) : lignes.value;
});

async function loadUnratedEvaluations() {
  try {
    const apiUrl: string = useRuntimeConfig().public.apiUrl as string;
    const response = await axios.get(`${apiUrl}/evaluation/non_note`);

    if (response.status === 200 && Array.isArray(response.data)) {
      console.log(toRaw(response.data));

      lignes.value = response.data;
    } else {
      console.error('Erreur lors de la récupération des évaluations non notées', response.data);
    }
  } catch (error) {
    console.error('Erreur lors de la requête API:', error);
  }
}

onMounted(() => {
  loadUnratedEvaluations();
})

const expand = ref({
  openedRows: [],
  row: {}
})

</script>

<template>
  <div class="container mx-auto">
    <h1 class="text-3xl font-bold mb-6 text-center">Liste des évaluations non notés</h1>

    <div class="flex justify-center mb-4">
      <UInput v-model="q" placeholder="Filtrer les annonces..."
              class="w-full max-w-md px-4 py-2  rounded-lg shadow-sm focus:outline-none focus:ring-2 focus:ring-blue-500" />
    </div>

    <UTable :columns="headers" :rows="filteredLignes" v-model:expand="expand"
            class="w-full shadow-md rounded-lg overflow-hidden">
      <template #expand="{ row }">
        <div class="p-4">
          <div class="mb-4">
            <h3 class="text-xl font-semibold mb-2">Talent:</h3>
            <p class="text-gray-700">{{ row.talent.nom + ' ' + row.talent.prenom }}</p>
          </div>

          <button class="bg-green-500 hover:bg-green-700 text-white font-bold py-2 px-4 rounded ml-2"
                  @click="$router.push(`/interne/evaluation/projet/${row.idEvaluation}`)">
            Noter
          </button>
        </div>
      </template>
    </UTable>
  </div>
</template>

<style scoped>

</style>