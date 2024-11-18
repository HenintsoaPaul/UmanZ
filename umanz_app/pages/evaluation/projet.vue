<script setup lang="ts">
import { ref, onMounted } from 'vue';
import axios from 'axios';

interface Question {
  idQuestionProjet: number; // Identifiant unique de la question
  question: string;         // Texte de la question
}

// Interface pour formState
interface FormState {
  [key: number]: string; // Permet d'indexer par numéro et de stocker une chaîne
}

const questions = ref<Question[]>([]);
const formState = ref<FormState>({}); // État du formulaire

async function loadQuestions() {
  try {
    const apiUrl: string = useRuntimeConfig().public.apiUrl as string;
    const response = await axios.get(`${apiUrl}/question_projet`);

    if (response.status === 200 && Array.isArray(response.data)) {
      questions.value = response.data;
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

function handleSubmit() {
  console.log(toRaw(formState.value)); // Traitez ici les données du formulaire
}
</script>

<template>
  <UForm :state="formState" @submit.prevent="handleSubmit">
    <h1>Liste des Questions</h1>
    <div v-for="question in questions" :key="question.idQuestionProjet">
      <UFormGroup>
        <label :for="`question-${question.idQuestionProjet}`">{{ question.question }}</label>
        <UTextarea
            v-model="formState[question.idQuestionProjet]"
            :id="`question-${question.idQuestionProjet}`"
            :rows="4"
            cols="50">
        </UTextarea>
      </UFormGroup>
    </div>
    <UButton type="submit">Répondre</UButton>
  </UForm>
</template>

<style scoped>
/* Ajoutez vos styles ici */
</style>