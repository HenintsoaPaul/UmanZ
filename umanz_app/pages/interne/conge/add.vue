<script setup lang="ts">
import { useRouter } from 'vue-router'
import { z } from 'zod'
import { reactive, ref, computed, onMounted } from 'vue'
import axios from 'axios'
import type { Contrat } from '~/types';

// Définir le schéma
const schema = z.object({
    motif: z.string().min(1, 'Le motif est obligatoire'),
    date_absence: z.string().date('Date d\'absence incorrecte'),
    id_contrat: z.number().min(1, 'L\'ID du contrat est obligatoire')
});
type Schema = z.output<typeof schema>;

const formState = reactive({
    motif: '',
    date_absence: '',
    id_contrat: 0,
    error: ''
});

const isFormValid = computed(() => {
    return schema.safeParse({ ...formState }).success;
});

const loading = ref(false);
const router = useRouter();
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

async function onSubmit(event: Event) {
    event.preventDefault();
    if (!isFormValid.value) {
        formState.error = 'Veuillez remplir correctement tous les champs.';
        return;
    }

    loading.value = true;
    try {
        await axios.post('/api/conges', {
            motif: formState.motif,
            date_absence: formState.date_absence,
            id_contrat: formState.id_contrat
        });
        router.push('/conges');
    } catch (error) {
        formState.error = 'Erreur lors de l\'envoi du formulaire.';
    } finally {
        loading.value = false;
    }
}
</script>

<template>
    <div class="container mx-auto">
        <h1 class="text-3xl font-bold mb-6 text-center">Ajouter un Congé</h1>
        <form @submit="onSubmit" class="max-w-lg mx-auto">
            <div class="mb-4">
                <label for="motif" class="block text-gray-700">Motif</label>
                <input v-model="formState.motif" type="text" id="motif"
                    class="w-full p-2 border border-gray-300 rounded mt-1" />
            </div>
            <div class="mb-4">
                <label for="date_absence" class="block text-gray-700">Date d'absence</label>
                <input v-model="formState.date_absence" type="date" id="date_absence"
                    class="w-full p-2 border border-gray-300 rounded mt-1" />
            </div>
            <div class="mb-4">
                <label for="id_contrat" class="block text-gray-700">Contrat</label>
                <select v-model="formState.id_contrat" id="id_contrat"
                    class="w-full p-2 border border-gray-300 rounded mt-1">
                    <option value="" disabled>Choisir un contrat</option>
                    <option v-for="contrat in contrats" :key="contrat.idContrat" :value="contrat.idContrat">
                        {{ contrat.talent.nom }} - {{ contrat.talent.prenom }}
                    </option>
                </select>
            </div>
            <div v-if="formState.error" class="text-red-500 mb-4">{{ formState.error }}</div>
            <button type="submit" :disabled="loading" class="w-full bg-blue-500 text-white p-2 rounded">
                {{ loading ? 'Envoi en cours...' : 'Ajouter' }}
            </button>
        </form>
    </div>
</template>