<script setup lang="ts">
import { ref } from 'vue';
import axios from 'axios';
import type { Absence } from '~/types';


// Data
const form = ref<Absence>({
    id_absence: 0,
    motif: '',
    date_absence: new Date(),
    id_contrat: 0
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
                <input type="date" id="date_absence" v-model="form.date_absence" required />
            </div>

            <!-- ID Contrat -->
            <div class="form-group">
                <label for="id_contrat">ID Contrat:</label>
                <input type="number" id="id_contrat" v-model="form.id_contrat" required />
            </div>

            <button type="submit"
                class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded">Soumettre</button>
        </form>
    </div>
</template>
