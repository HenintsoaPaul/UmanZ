<script setup lang="ts">
import { ref, onMounted, toRaw } from 'vue';
import axios from 'axios';

interface Reponse {
    idReponse: number;
    reponse: string;
}

interface Domaine {
  idDomaine: number; // Identifiant unique de la question
  domaine: string;         // Texte de la question
}

interface Question {
  idQuestion: number; // Identifiant unique de la question
  question: string; // Texte de la question
  domaine: Domaine;
  reponses: Reponse[];
}

// Interface pour formState
interface FormState {
  [key: number]: string; // Permet d'indexer par numéro et de stocker une chaîne
}
const route = useRoute();
const domaineId = computed(() => route.params.id);
const questions = ref<Question[]>([]);
const formState = ref<FormState>({}); // État du formulaire
var reponsesQuestion=ref('');

onMounted(()=>{
  console.log(domaineId.value);
});

async function loadQuestions() {
  try {
    const apiUrl: string = useRuntimeConfig().public.apiUrl as string;
    const response = await axios.get(`${apiUrl}/question/${domaineId.value}`);

    if (response.status === 200 && Array.isArray(response.data)) {
      questions.value = response.data;
      console.log("Questions");
      //console.log(questions.value[0].reponses[0].reponse);
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
    idTalent: Number(localStorage.getItem("idUser")), // Date au format YYYY-MM-DD
    questionReponses: Object.entries(formState.value).map(([idQuestion, reponse]) => ({
      idQuestion: Number(idQuestion),
      idReponse: reponse || "Je ne sais pas" // Valeur par défaut si aucune réponse
    }))
  };

  try {
    console.log(toRaw(evaluationPayload));

    const apiUrl: string = useRuntimeConfig().public.apiUrl as string;
    const response = await axios.post(`${apiUrl}/resultat`, evaluationPayload);

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
    <div class="border border-slate-50 p-8 rounded-lg shadow-md w-full max-w-md">
      <UForm :state="formState" @submit.prevent="handleSubmit">
        <h1>Evaluation de projet</h1>
        <div v-for="question in questions" :key="question.idQuestion">
          <UFormGroup>
            <label :for="`question-${question.idQuestion}`">{{ question.question }}</label>
            <div style="display: flex;margin-bottom: 10%;" v-for="reponse in question.reponses">
              <URadio v-model="formState[question.idQuestion]" :value="reponse.idReponse"></URadio>
              <label>{{ reponse.reponse }}</label>
            </div>
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