<script setup lang="ts">
import { ref, reactive, toRaw } from 'vue';
import type { Entretien } from '~/types';

const route = useRoute();
const idEntretien: number = Number(route.query.entretien);

const apiUrl = useRuntimeConfig().public.apiUrl as string;
const { data: entretien } = useFetch<Entretien>(`${apiUrl}/entretien/${idEntretien}`);

const form = reactive<{
    dateDebut: string;
    dateFin: string;
    salaireHoraire: number;
    nbJourSemaine: number;
    nbHeureJour: number;
    idTypeContrat: number;
}>({
    dateDebut: '',
    dateFin: '',
    salaireHoraire: 0,
    nbJourSemaine: 0,
    nbHeureJour: 0,
    idTypeContrat: 1 // Contrat d'essaie par defaut
});

const loading = ref(false);

const onSubmit = async () => {
    loading.value = true;
    try {
        const formKdj = {
            ...form,
            idEntretien: entretien.value?.idEntretien,
            idPoste: entretien.value?.annonce.poste.idPoste,
            idTalent: entretien.value?.talent.idTalent,
        };
        console.log(toRaw(formKdj));

        const response = await $fetch(`${apiUrl}/contrats`, {
            method: 'POST',
            body: toRaw(formKdj)
        });
        console.log('Form submitted successfully:', response);
    } catch (error) {
        console.error('Error submitting form:', error);
    } finally {
        loading.value = false;
    }
};
</script>

<template>
    <div v-if="idEntretien">
        <div class="contrat-form max-w-3xl mx-auto p-6 border border-white rounded-lg shadow-md">
            <h1 class="text-3xl font-bold mb-6">Ajouter nouveau Contrat d'Essaie</h1>

            <form @submit.prevent="onSubmit" class="space-y-6">
                <!-- Info Entretien -->
                <div class="w-full flex gap-5">
                    <div class="form-group w-1/2">
                        <label for="talent" class="block text-sm font-medium">Talent:</label>
                        <input type="text" id="talent" :value="entretien?.talent.nom" readonly
                            class="mt-1 block w-full border border-gray-300 rounded-md shadow-sm focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm" />
                    </div>
                    <div class="form-group w-1/2">
                        <label for="poste" class="block text-sm font-medium">Poste:</label>
                        <input type="text" id="poste" :value="entretien?.annonce.poste.nomPoste" readonly
                            class="mt-1 block w-full border border-gray-300 rounded-md shadow-sm focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm" />
                    </div>
                </div>

                <div class="w-full flex gap-5">
                    <div class="w-1/2">
                        <div class="form-group">
                            <label for="dateDebut" class="block text-sm font-medium">Date de Début:</label>
                            <input type="date" id="dateDebut" v-model="form.dateDebut" required
                                class="mt-1 block w-full border border-gray-300 rounded-md shadow-sm focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm" />
                        </div>
                        <div class="form-group">
                            <label for="dateFin" class="block text-sm font-medium">Date de Fin:</label>
                            <input type="date" id="dateFin" v-model="form.dateFin"
                                class="mt-1 block w-full border border-gray-300 rounded-md shadow-sm focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm" />
                        </div>
                    </div>
                    <div class="w-1/2">
                        <div class="form-group">
                            <label for="salaireHoraire" class="block text-sm font-medium">Salaire Horaire:</label>
                            <input type="number" id="salaireHoraire" v-model="form.salaireHoraire" required
                                class="mt-1 block w-full border border-gray-300 rounded-md shadow-sm focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm" />
                        </div>
                        <div class="form-group">
                            <label for="nbJourSemaine" class="block text-sm font-medium">Nombre de Jours par
                                Semaine:</label>
                            <input type="number" id="nbJourSemaine" v-model="form.nbJourSemaine" required
                                class="mt-1 block w-full border border-gray-300 rounded-md shadow-sm focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm" />
                        </div>
                        <div class="form-group">
                            <label for="nbHeureJour" class="block text-sm font-medium">Nombre d'Heures par Jour:</label>
                            <input type="number" id="nbHeureJour" v-model="form.nbHeureJour" required
                                class="mt-1 block w-full border border-gray-300 rounded-md shadow-sm focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm" />
                        </div>
                    </div>
                </div>

                <button type="submit" :disabled="loading"
                    class="w-full inline-flex justify-center py-2 px-4 border border-transparent rounded-md shadow-sm text-sm font-medium text-white bg-indigo-600 hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500">
                    {{ loading ? 'Chargement...' : 'Soumettre' }}
                </button>
            </form>
        </div>
    </div>
    <div v-else class="text-red">
        Erreur lors de la recuperation de l' idEntretien
    </div>
</template>
