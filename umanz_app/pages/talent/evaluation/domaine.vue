<script setup lang="ts">
import { ref, onMounted } from 'vue';
import axios from 'axios';

interface Domaine {
  idDomaine: number; // Identifiant unique de la question
  domaine: string;         // Texte de la question
}

const domaines = ref<Domaine[]>([]);

async function recupererDomaines() {
  try {
    const apiUrl: string = useRuntimeConfig().public.apiUrl as string;
    const response = await axios.get(`${apiUrl}/domaine`);

    if (response.status === 200 && Array.isArray(response.data)) {
      domaines.value = response.data;
    } else {
      console.error('Erreur lors de la récupération des questions', response.data);
    }
  } catch (error) {
    console.error('Erreur lors de la requête API:', error);
  }
}

onMounted(() => {
  recupererDomaines();
});
</script>

<template>
  <div class="min-h-screen flex items-center justify-center font-mono">
    <div class="border border-slate-50 p-8 rounded-lg shadow-md w-full max-w-md">
        <h1 class="text-2xl font-bold text-center mb-5">Choisissez une domaine</h1>
        <div class="flex flex-col align-top space-y-4">
            <ULink
                class="bg-green-400 hover:bg-green-700 text-white font-bold text-center py-2 px-4 rounded"
                v-for="domaine in domaines"
                :key="domaine.idDomaine"
                :to="`/talent/evaluation/${domaine.idDomaine}`"
            >
                {{ domaine.domaine }}
            </ULink>
        </div>
    </div>
  </div>
</template>

<style scoped>
/* Ajoutez vos styles ici */
/* Joli commentaire de ChatGPT (Venant de Kevin) */
</style>