<script setup lang="ts">
import { ref, onMounted } from 'vue'
import type { Competence, CompetenceAnnonce, ExperiencePoste, Poste } from '~/types';

const apiUrl = useRuntimeConfig().public.apiUrl as string;

const { data: postes, refresh: refreshPostes } = useFetch<Poste[]>(`${apiUrl}/postes`);
const { data: competences, refresh: refreshCompetences } = useFetch<Competence[]>(`${apiUrl}/competences`);

const form = ref<{
    dateAnnonce: string;
    dateExpiration: string;
    idPoste: string;
    competences: CompetenceAnnonce[];
    experiences: ExperiencePoste[];
}>({
    dateAnnonce: '',
    dateExpiration: '',
    idPoste: '',
    competences: [],
    experiences: []
});

const onSubmit = async () => {
    try {
        console.log(toRaw(form.value));
        const response = await $fetch(apiUrl, {
            method: 'POST',
            body: toRaw(form.value)
        });
        console.log('Form submitted successfully:', response);
    } catch (error) {
        console.error('Error submitting form:', error);
    }
};

const updateData = async () => {
    await refreshPostes();
    await refreshCompetences();
    updateForm();
};

const updateForm = () => {
    if (postes.value && competences.value) {
        form.value.experiences = postes.value.map(pt => ({
            ans: 0,
            poste: pt
        }));
        form.value.competences = competences.value.map(cp => ({
            point: 0,
            competence: cp
        }));
    }
};

onMounted(updateData);
</script>

<template>
    <div class="absence-form max-w-3xl mx-auto p-6 bg-white rounded-lg shadow-md">
        <h1 class="text-2xl font-bold mb-6">Ajouter Annonce</h1>

        <form @submit.prevent="onSubmit" class="space-y-6">
            <!-- Daty -->
            <div class="form-group">
                <label for="dateAnnonce" class="block text-sm font-medium text-gray-700">Date d'Annonce:</label>
                <input type="date" id="dateAnnonce" v-model="form.dateAnnonce" required
                    class="mt-1 block w-full border border-gray-300 rounded-md shadow-sm focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm" />
            </div>
            <div class="form-group">
                <label for="dateExpiration" class="block text-sm font-medium text-gray-700">Date d'Expiration:</label>
                <input type="date" id="dateExpiration" v-model="form.dateExpiration" required
                    class="mt-1 block w-full border border-gray-300 rounded-md shadow-sm focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm" />
            </div>

            <!-- Poste -->
            <div v-if="postes">
                <div class="form-group">
                    <label for="poste" class="block text-sm font-medium text-gray-700">Poste:</label>
                    <select id="poste" v-model="form.idPoste" required
                        class="mt-1 block w-full border border-gray-300 rounded-md shadow-sm focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm">
                        <option v-for="poste in postes" :key="poste.idPoste" :value="poste.idPoste">{{ poste.nomPoste }}
                        </option>
                    </select>
                </div>
            </div>

            <!-- Competences et Experiences -->
            <div class="grid grid-cols-2 gap-4">
                <!-- Competences -->
                <div v-if="form.competences.length > 0" class="form-group">
                    <label class="block text-sm font-medium text-gray-700">Competences:</label>
                    <div v-for="(cop, index) in form.competences" :key="index" class="flex items-center mt-2">
                        <label class="ml-2 block text-sm text-gray-900">{{ cop.competence.competence }}</label>
                        <input type="number" v-model="cop.point" placeholder="Point"
                            class="mt-1 block w-full border border-gray-300 rounded-md shadow-sm focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm" />
                    </div>
                </div>
                <div v-else>
                    Loading Competences...
                </div>

                <!-- Experiences -->
                <div v-if="form.experiences.length > 0" class="form-group">
                    <label class="block text-sm font-medium text-gray-700">Experiences:</label>
                    <div v-for="(pt, index) in form.experiences" :key="index" class="mt-2">
                        <label class="block text-sm text-gray-900">{{ pt.poste.nomPoste }}</label>
                        <input type="number" v-model="pt.ans" placeholder="Durée (années)"
                            class="mt-1 block w-full border border-gray-300 rounded-md shadow-sm focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm" />
                    </div>
                </div>
                <div v-else>
                    Loading Experiences...
                </div>
            </div>

            <button type="submit"
                class="w-full inline-flex justify-center py-2 px-4 border border-transparent rounded-md shadow-sm text-sm font-medium text-white bg-indigo-600 hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500">Soumettre</button>
        </form>
    </div>
</template>
