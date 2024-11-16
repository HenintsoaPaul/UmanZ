<script setup lang="ts">
import { ref, reactive, toRaw } from 'vue';
import type { Entretien, TypeContrat } from '~/types';

const router = useRoute();
const idEntretien: number = Number(router.params.entretien);

const apiUrl = useRuntimeConfig().public.apiUrl as string;
const { data: entretienAttenteContrat } = useFetch<Entretien[]>(`${apiUrl}/entretien/etat/${6}`);
const { data: typeContrats } = useFetch<TypeContrat[]>(`${apiUrl}/type_contrat`);

const form = reactive<{
    dateDebut: string;
    dateFin: string;
    salaireHoraire: number;
    nbJourSemaine: number;
    nbJourCongeAn: number;
    nbHeureJour: number;
    entretien: Entretien;
    idTypeContrat: number;
}>({
    dateDebut: '',
    dateFin: '',
    salaireHoraire: 0,
    nbJourSemaine: 0,
    nbJourCongeAn: 0,
    nbHeureJour: 0,
    entretien: {} as Entretien,
    idTypeContrat: 0
});

const loading = ref(false);

const onSubmit = async () => {
    loading.value = true;
    try {
        const { entretien, ...formWithoutEntretien } = toRaw(form);
        const formKdj = {
            ...formWithoutEntretien,
            idEntretien: entretien.idEntretien,
            idPoste: entretien.annonce.poste.idPoste,
            idTalent: entretien.talent.idTalent,
        };
        console.log(toRaw(formKdj));

        const response = await $fetch(`${apiUrl}/contrat`, {
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
    <div class="contrat-form max-w-3xl mx-auto p-6 border border-white rounded-lg shadow-md">
        <h1 class="text-3xl font-bold mb-6">Ajouter Contrat</h1>

        <form @submit.prevent="onSubmit" class="space-y-6">
            <!-- Entretien -->
            <div v-if="entretienAttenteContrat">
                <div class="form-group">
                    <label for="entretien" class="block text-sm font-medium">Entretien en attente Contrat:</label>
                    <select id="entretien" v-model="form.entretien" required
                        class="mt-1 block w-full border border-gray-300 rounded-md shadow-sm focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm">
                        <option v-for="entretien in entretienAttenteContrat" :key="entretien.idEntretien"
                            :value="entretien" :selected="entretien.idEntretien === idEntretien">
                            {{ entretien.talent.nom }} {{ entretien.talent.prenom }}
                        </option>
                    </select>
                </div>
            </div>

            <!-- Type Contrat -->
            <div v-if="typeContrats">
                <div class="form-group">
                    <label for="typeContrat" class="block text-sm font-medium">Type Contrat:</label>
                    <select id="typeContrat" v-model="form.idTypeContrat" required
                        class="mt-1 block w-full border border-gray-300 rounded-md shadow-sm focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm">
                        <option v-for="(tp, i) in typeContrats" :key="i" :value="tp.idTypeContrat">
                            {{ tp.typeContrat }}
                        </option>
                    </select>
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
                        <label for="nbJourCongeAn" class="block text-sm font-medium">Nombre de Jours de Congé par
                            An:</label>
                        <input type="number" id="nbJourCongeAn" v-model="form.nbJourCongeAn" required
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
</template>
