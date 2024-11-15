<script setup lang="ts">
import { ref, onMounted } from 'vue'
import type { Competence, CompetenceAnnonce, ExperiencePoste, Poste } from '~/types';

const apiUrl = useRuntimeConfig().public.apiUrl as string;
const { data: postes, refresh: refreshPostes } = useFetch<Poste[]>(`${apiUrl}/postes`);
const { data: competences, refresh: refreshCompetences } = useFetch<Competence[]>(`${apiUrl}/competences`);

interface Form {
    dateAnnonce: string;
    dateExpiration: string;
    idPoste: string;
    competences: CompetenceAnnonce[];
    experiences: ExperiencePoste[];
}
const form = reactive<Form>({
    dateAnnonce: '',
    dateExpiration: '',
    idPoste: '',
    competences: [],
    experiences: []
});
const formKdj = reactive<Form>({
    dateAnnonce: '',
    dateExpiration: '',
    idPoste: '',
    competences: [],
    experiences: []
});

const loading = ref(false);

const onSubmit = async () => {
    loading.value = true;
    try {
        formKdj.dateAnnonce = form.dateAnnonce;
        formKdj.dateExpiration = form.dateExpiration;
        formKdj.idPoste = form.idPoste;
        formKdj.competences = form.competences.filter(cp => cp.point > 0).map(cp => toRaw(cp));
        formKdj.experiences = form.experiences.filter(exp => exp.ans > 0).map(exp => toRaw(exp));

        console.log(toRaw(formKdj));

        const response = await $fetch(`${apiUrl}/annonce`, {
            method: 'POST',
            body: toRaw(formKdj)
        });
        console.log('Form submitted successfully:', response);
    } catch (error) {
        console.error('Error submitting form:', error);
        // Afficher une notification à l'utilisateur
        // useToast().add({
        //     title: 'Erreur lors de la soumission',
        //     description: "Une erreur s'est produite lors de la soumission du formulaire.",
        //     // status: 'error'
        // });
    }
    finally {
        loading.value = false;
    }
};

const updateData = async () => {
    await refreshPostes();
    await refreshCompetences();
    updateForm();
};

const updateForm = () => {
    if (postes.value && competences.value) {
        form.experiences = postes.value.map(pt => ({
            ans: 0,
            poste: toRaw(pt)
        }));
        form.competences = competences.value.map(cp => ({
            point: 0,
            competence: toRaw(cp)
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
                <div v-if="form.competences.length > 0" class="form-group">
                    <ListInputCompetence title="Competences" :competences="form.competences" />
                </div>
                <div v-else>
                    Loading Competences...
                </div>

                <div v-if="form.experiences.length > 0" class="form-group">
                    <ListInputExperience title="Experiences" :experiences="form.experiences" />
                </div>
                <div v-else>
                    Loading Experiences...
                </div>
            </div>

            <button type="submit" :disabled="loading"
                class="w-full inline-flex justify-center py-2 px-4 border border-transparent rounded-md shadow-sm text-sm font-medium text-white bg-indigo-600 hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500">
                {{ loading ? 'Chargement...' : 'Soumettre' }}
            </button>
        </form>
    </div>
</template>
