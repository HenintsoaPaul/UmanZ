<script setup lang="ts">
import { ref, onMounted, toRaw } from 'vue';
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

async function handleSubmit() {
  // Créer l'objet d'évaluation selon le format attendu
  const evaluationPayload = {
    idTalent: Number(localStorage.getItem("idUser")),
    dateEvaluation: new Date().toISOString().split('T')[0], // Date au format YYYY-MM-DD
    evaluations: Object.entries(formState.value).map(([idQuestionProjet, reponse]) => ({
      idQuestionProjet: Number(idQuestionProjet),
      reponse: reponse || "Je ne sais pas" // Valeur par défaut si aucune réponse
    }))
  };

  try {
    console.log(toRaw(evaluationPayload));

    const apiUrl: string = useRuntimeConfig().public.apiUrl as string;
    const response = await axios.post(`${apiUrl}/evaluation`, evaluationPayload);

    if (response.status === 200) {
      console.log('Évaluation enregistrée avec succès:', response.data);
    } else {
      console.error('Erreur lors de l\'enregistrement de l\'évaluation', response.data);
    }
  } catch (error) {
    console.error('Erreur lors de la requête API:', error);
  }
}
</script>

<template>
  <div class="min-h-screen flex items-center justify-center font-mono">
    <div class="border border-slate-50 p-8 rounded-lg shadow-md w-full max-w-md text-slate-500">
      <UForm :state="formState" @submit.prevent="handleSubmit">
        <h1>Evaluation de projet</h1>
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
        <UButton type="submit" class="mt-1">Répondre</UButton>
      </UForm>
    </div>
  </div>
</template>

<style scoped>
/* Ajoutez vos styles ici */
</style>