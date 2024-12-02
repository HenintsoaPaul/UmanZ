<script setup lang="ts">
import { ref, onMounted, toRaw } from 'vue';
import axios from 'axios';

interface Domaine {
  idDomaine: number; // Identifiant unique de la question
  domaine: string;         // Texte de la question
}

// Interface pour formState
interface FormState {
  [key: number]: string; // Permet d'indexer par numéro et de stocker une chaîne
}

const domaines = ref<Domaine[]>([]);
const formState = ref<FormState>({}); // État du formulaire

async function loadQuestions() {
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
  loadQuestions();
});
</script>

<template>
  <div class="min-h-screen flex items-center justify-center font-mono">
    <div class="border border-slate-50 p-8 rounded-lg shadow-md w-full max-w-md">
        <h1>Choisissez une domaine</h1>
        <div v-for="domaine in domaines" :key="domaine.idDomaine">
            <a :href="`${domaine.idDomaine}`">{{ domaine.domaine }}</a>
        </div>
    </div>
  </div>
</template>

<style scoped>
/* Ajoutez vos styles ici */
/* Joli commentaire de ChatGPT (Venant de Kevin) */
</style>