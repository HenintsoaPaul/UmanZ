<script setup lang="ts">
import { ref } from 'vue';
import axios from 'axios';
import type { Absence, Contrat } from '~/types';


// Data
const form = ref<Absence>({
    idAbsence: 0,
    motif: '',
    dateAbsence: new Date().toString(),
    idContrat: 0
});

const contrats = ref<Contrat[]>([]); // État réactif pour stocker les contrats

// Charger les contrats depuis l'API
const loadContrats = async () => {
    try {
        const response = await axios.get('/api/contrats');
        if (response.status === 200) {
            contrats.value = response.data;
        } else {
            console.error('Erreur lors de la récupération des contrats', response.data);
        }
    } catch (error) {
        console.error('Erreur lors de la requête API:', error);
    }
};

onMounted(() => {
    loadContrats();
});

// Method
const submitForm = async () => {
    try {
        console.log(toRaw(form.value));

        const apiUrl: string = useRuntimeConfig().public.apiUrl as string;
        console.log(apiUrl);

        const response = await axios.post(`${apiUrl}/absences`, form.value);
        console.log('Form submitted successfully:', response.data);
    } catch (error) {
        console.error('Error submitting form:', error);
    }
};
</script>

<template>
    <div class="absence-form">
        <h1>Ajouter Absence</h1>

        <form @submit.prevent="submitForm">
            <!-- Motif -->
            <div class="form-group">
                <label for="motif">Motif:</label>
                <input type="text" id="motif" v-model="form.motif" />
            </div>

            <!-- Date Absence -->
            <div class="form-group">
                <label for="date_absence">Date d'Absence:</label>
                <input type="date" id="date_absence" v-model="form.dateAbsence" required />
            </div>

            <!-- Contrat -->
            <div class="mb-4">
                <label for="id_contrat" class="block text-gray-700">Contrat</label>
                <select v-model="form.idContrat" id="id_contrat"
                    class="w-full p-2 border border-gray-300 rounded mt-1">
                    <option value="" disabled>Choisir un contrat</option>
                    <option v-for="contrat in contrats" :key="contrat.idContrat" :value="contrat.idContrat">
                        {{ contrat.talent.nom }} - {{ contrat.talent.prenom }}
                    </option>
                </select>
            </div>

            <button type="submit"
                class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded">Soumettre</button>
        </form>
    </div>
</template>
