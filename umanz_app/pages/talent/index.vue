<script setup lang="ts">
import { ref, computed } from 'vue';
import axios from 'axios';
import type { Talent } from '~/types';
// import { useRuntimeConfig } from '#imports'; // Ensure you have this import if you're using Nuxt.js

// Columns à afficher
const headers = [
    { key: 'id_talent', label: 'ID', sortable: true },
    { key: 'nom', label: 'Nom', sortable: true },
    { key: 'prenom', label: 'Prénom', sortable: true },
    { key: 'mail', label: 'Email', sortable: true }
];

// Données d'exemple pour les talents
const talents = ref<Talent[]>([
    {
        id_talent: 1,
        nom: 'Dupont',
        prenom: 'Jean',
        mail: 'jean.dupont@example.com',
        password: 'password1'
    },
    {
        id_talent: 2,
        nom: 'Martin',
        prenom: 'Marie',
        mail: 'marie.martin@example.com',
        password: 'password2'
    },
    {
        id_talent: 3,
        nom: 'Durand',
        prenom: 'Pierre',
        mail: 'pierre.durand@example.com',
        password: 'password3'
    }
]);

// Variable pour stocker les données de l'API
const apiTalents = ref([]);

// Fonction pour charger les données des talents depuis l'API
async function loadTalents() {
    try {
        const apiUrl: string = useRuntimeConfig().public.apiUrl as string;
        const response = await axios.get(`${apiUrl}/talents`);

        if (response.status === 200) {
            apiTalents.value = response.data;
        } else {
            console.error('Erreur lors de la récupération des talents');
        }
    } catch (error) {
        console.error('Erreur lors de la requête API:', error);
    }
}

// Appel de la fonction pour charger les données au chargement du composant
loadTalents();

// Filtre par recherche
const q = ref('');

const filteredTalents = computed(() => {
    if (!q.value) {
        return talents.value;
    }

    return talents.value.filter((talent) => {
        return Object.values(talent).some((value) => {
            return String(value).toLowerCase().includes(q.value.toLowerCase());
        });
    });
});
</script>

<template>
    <h1 class="text-2xl font-bold mb-4">Liste des talents</h1>

    <div class="flex px-3 py-3.5 border-b border-gray-200 dark:border-gray-700">
        <UInput v-model="q" placeholder="Filtrer les talents..." />
    </div>

    <UTable :columns="headers" :rows="filteredTalents" />
</template>

<style scoped>
/* Ajoutez vos styles personnalisés ici si nécessaire */
</style>