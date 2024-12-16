<script setup lang="ts">
import { ref, reactive, toRaw } from 'vue';
import type {  Contrat } from '~/types';

definePageMeta({
    middleware: ['auth-is-admin']
});

const apiUrl = useRuntimeConfig().public.apiUrl as string;
const { data: contrats } = useFetch<Contrat[]>(`${apiUrl}/contrats/now`);

const erreur = ref<String>("");

const form = reactive<{
    idContrat: number;
    motif: string;
    dateHeureDebut: Date;
    nbHeure: number;
}>({
    idContrat: 0,
    motif: '',
    dateHeureDebut: new Date(),
    nbHeure: 0,
});

const loading = ref(false);

const onSubmit = async () => {
    loading.value = true;
    try {
        console.log(toRaw(form));

        const response = await $fetch(`${apiUrl}/heure_sup`, {
            method: 'POST',
            body: toRaw(form)
        });
        erreur.value="";
        console.log(response);
        
    }catch (error) {
        console.error('Erreur réseau ou inconnue:', error);
    } finally {
        loading.value = false;
    }
};
</script>

<template>
        <div class="contrat-form max-w-3xl mx-auto p-6 border border-white rounded-lg shadow-md">
            <p style="color: red;">{{ erreur }}</p>
            <h1 class="text-3xl font-bold mb-6">Ajouter Heure supplementaire</h1>

            <form @submit.prevent="onSubmit" class="space-y-6">
                <!-- Info Entretien -->
                <div class="w-full flex gap-5">
                    <div class="form-group w-1/2">
                        <label for="talent" class="block text-sm font-medium">Motif:</label>
                        <input type="text" id="talent" v-model="form.motif" required
                            class="mt-1 block w-full border border-gray-300 rounded-md shadow-sm focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm" />
                    </div>
                    <div class="form-group w-1/2">
                        <label for="poste" class="block text-sm font-medium">Date heure supplémentaire:</label>
                        <input type="datetime-local" id="poste" v-model="form.dateHeureDebut" required
                            class="mt-1 block w-full border border-gray-300 rounded-md shadow-sm focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm" />
                    </div>
                    <div class="form-group w-1/2">
                        <label for="poste" class="block text-sm font-medium">Nombre heure:</label>
                        <input type="number" id="poste" v-model="form.nbHeure" required
                            class="mt-1 block w-full border border-gray-300 rounded-md shadow-sm focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm" />
                    </div>
                </div>

                <!-- Type Contrat -->
                <div v-if="contrats">
                    <div class="form-group">
                        <label for="typeContrat" class="block text-sm font-medium">Talent:</label>
                        <select id="typeContrat" v-model="form.idContrat" required
                            class="mt-1 block w-full border border-gray-300 rounded-md shadow-sm focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm">
                            <option v-for="(tp, i) in contrats" :key="i" :value="tp.idContrat">
                                {{ tp.talent.nom }} {{ tp.talent.prenom }}
                            </option>
                        </select>
                    </div>
                </div>

                <button type="submit" :disabled="loading"
                    class="w-full inline-flex justify-center py-2 px-4 border border-transparent rounded-md shadow-sm text-sm font-medium text-white bg-indigo-600 hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500">
                    {{ loading ? 'Chargement...' : 'Soumettre' }}
                </button>
            </form>
        </div>
</template>
