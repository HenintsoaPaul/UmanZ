<script setup lang="ts">
import type {Evaluation, EvaluationNote} from "~/types";
import {useRoute} from "#vue-router";
import axios from "axios";

const route = useRoute();
const apiUrl = useRuntimeConfig().public.apiUrl as string;
const idEvaluation = computed(() => route.params.id);

const { data: evaluation, error: evaluationError } = useFetch<Evaluation>(`${apiUrl}/evaluation/non_note/${idEvaluation.value}`);

async function handleSubmit() {
  try {
    const evaluationPlayload: EvaluationNote = {};
    evaluationPlayload.idEvaluation = evaluation.value?.idEvaluation;
    evaluationPlayload.idTalent = evaluation.value?.talent.idTalent;
    evaluationPlayload.dateEvaluation = evaluation.value?.dateEvaluation;
    evaluationPlayload.evaluations = evaluation.value?.questionEvaluations.map(
      question => ({
        idQuestionProjet: question.questionProjet.idQuestionProjet,
        note: question.note
      })
    );

    const response = await axios.post(`${apiUrl}/evaluation/evaluate`, evaluationPlayload);

    if (response.status === 200) {
      console.log('Évaluation notée avec succès:', response.data);
    } else {
      console.error('Erreur lors du notage de l\'évaluation', response.data);
    }
  } catch (error) {
    console.error('Erreur lors de la requête API:', error);
  }
}

</script>

<template>
  <div class="min-h-screen flex items-center justify-center font-mono">
    <div class="border border-slate-50 p-8 rounded-lg shadow-md w-full max-w-md">
      <template v-if="evaluation">
        <h1>Évaluation de {{ evaluation.talent.prenom }} {{ evaluation.talent.nom }}</h1>
        <p>Date d'évaluation : {{ evaluation.dateEvaluation }}</p>

        <UForm :state="evaluation" @submit.prevent="handleSubmit">
          <UFormGroup
              v-for="(questionEvaluation, index) in evaluation.questionEvaluations"
              :key="index"
              class="my-3"
          >
            <h3>{{ questionEvaluation.questionProjet.question }}</h3>
            <p><strong>Réponse :</strong> {{ questionEvaluation.reponse }}</p>
            <label for="note-{{ questionEvaluation.questionProjet.idQuestionProjet }}">
              Note (0-20) :
              <UInput
                  type="number"
                  v-model.number="questionEvaluation.note"
                  :id="`note-${questionEvaluation.questionProjet.idQuestionProjet}`"
                  min="0"
                  max="20"
              />
            </label>
          </UFormGroup>

          <UButton type="submit">Noter</UButton>
        </UForm>
      </template>
      <div v-else>
        Chargement de l'évaluation...
      </div>

      <div v-if="evaluationError" class="text-red-500">
        Erreur lors du chargement de l'évaluation: {{ evaluationError.message }}
      </div>
    </div>
  </div>
</template>

<style scoped>

</style>