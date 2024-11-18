<template>
  <div>
    <h2>Effectuer une action sur un candidat</h2>
    <form @submit.prevent="handleSubmit">
      <div>
        <label for="id">Choisir un candidat</label>
        <select v-model="id" id="id" required>
          <option value="" disabled>Choisissez un candidat</option>
          <option 
            v-for="candidate in candidates" 
            :key="candidate.idTalent" 
            :value="candidate.idTalent"
          >
            {{ candidate.mail }}
          </option>
        </select>
      </div>

      <div>
        <label for="action">Choisir une action</label>
        <select v-model="action" id="action" required>
          <option value="entretien">Entretien</option>
          <option value="rejet">Rejet</option>
        </select>
      </div>

      <div>
        <button type="submit">Envoyer l'action</button>
      </div>

      <div v-if="responseMessage" :class="responseStatus">
        <p>{{ responseMessage }}</p>
      </div>
    </form>
  </div>
</template>


<script setup>
import { ref, onMounted } from 'vue';
import { useFetch, useRuntimeConfig } from '#app';

const id = ref('');
const action = ref('entretien');
const candidates = ref([]);
const responseMessage = ref('');
const responseStatus = ref('');

const loadCandidates = async () => {
  try {
    const { data, error } = await useFetch('/talent/all', {
      baseURL: useRuntimeConfig().public.apiUrl,
    });

    if (error.value) {
      console.error('Erreur lors du chargement des candidats:', error.value);
    } else {
      console.log('Données récupérées depuis l\'API:', data.value);
      candidates.value = data.value || [];
    }
  } catch (err) {
    console.error('Erreur inconnue:', err);
  }
};

const handleSubmit = async () => {
  try {
    const baseURL = useRuntimeConfig().public.apiUrl;
    const response = await fetch(`${baseURL}/candidats/${id.value}/actions`, {
      method: 'PUT',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({ action: action.value }),
    });

    const result = await response.json();
    if (response.ok) {
      responseMessage.value = result.message || 'Action envoyée avec succès.';
      responseStatus.value = 'success';
    } else {
      throw new Error(result.message || 'Erreur lors de l\'envoi.');
    }
  } catch (error) {
    console.error(error);
    responseMessage.value = error.message || 'Erreur inconnue.';
    responseStatus.value = 'error';
  }
};

// Charger les candidats lors du montage
onMounted(loadCandidates);
</script>


<style scoped>
.success {
  color: green;
}

.error {
  color: red;
}
</style>
