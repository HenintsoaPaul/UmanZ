<script setup lang="ts">

import type {Conge} from "~/types";
import axios from "axios";
import {useRoute} from "#vue-router";

const headers = [
  {
    key: 'idConge',
    label: 'ID',
    sortable: true
  },
  {
    key: 'contrat.contrat',
    label: 'Nom du contrat',
    sortable: true
  },
  {
    key: 'dateDebut',
    label: 'Date de début',
    sortable: true
  },
  {
    key: 'dateFin',
    label: 'Date de fin',
  },
  {
    key: 'nbJour',
    label: 'Nombre Jour',
    sortable: true
  },
  {
    key: 'motif',
    label: 'Motif',
    sortable: true
  }
];

// Variable pour stocker les données de l'API
const lignes = ref<Conge[]>([]);
const q = ref('');

const filteredLignes = computed(() => {
  return q.value ?
      lignes.value.filter((e: Conge) =>
          Object.values(e).some((value) =>
              String(value).toLowerCase().includes(q.value.toLowerCase())
          )
      ) : lignes.value;
});

async function loadConge(idTalent: number) {
  try {
    const apiUrl: string = useRuntimeConfig().public.apiUrl as string;
    const response = await axios.get(`${apiUrl}/conge/talent/${idTalent}`);

    if (response.status === 200 && Array.isArray(response.data)) {
      console.log(toRaw(response.data));

      lignes.value = response.data;
    } else {
      console.error('Erreur lors de la récupération des annonces', response.data);
    }
  } catch (error) {
    console.error('Erreur lors de la requête API:', error);
  }
}

const route = useRoute();

// On component mount
onMounted(() => {
  const id = route.params.id;
  console.log(id);


  const talentId = Number(id);
  console.log(talentId);

  if (!isNaN(talentId)) {
    loadConge(talentId);
  } else {
    console.error('Invalid talent ID');
  }
});

</script>

<template>
  <div class="container mx-auto">
    <h1 class="text-3xl font-bold mb-4 text-center">Liste des Conges</h1>

    <div class="flex justify-center mb-4">
      <UInput v-model="q" placeholder="Filtrer les conges..." class="w-full max-w-md px-4 py-2 rounded-lg" />
    </div>

    <UTable :columns="headers" :rows="filteredLignes"></UTable>
  </div>
</template>
