<script setup lang="ts">
import axios from 'axios';

// Columns a afficher
const headers = [{
    key: 'id_absence',
    label: 'ID',
    sortable: true
}, {
    key: 'id_contrat',
    label: 'Contrat',
    sortable: true
}, {
    key: 'date_absence',
    label: 'Date Absence',
    sortable: true
}, {
    key: 'motif',
    label: 'Motif',
    sortable: true
}];

const lignes = [
    {
        "id_absence": 1,
        "motif": "Maladie",
        "date_absence": "2023-10-01T00:00:00Z",
        "id_contrat": 101
    },
    {
        "id_absence": 2,
        "motif": "Congé",
        "date_absence": "2023-10-05T00:00:00Z",
        "id_contrat": 102
    },
    {
        "id_absence": 3,
        "motif": "Formation",
        "date_absence": "2023-10-10T00:00:00Z",
        "id_contrat": 103
    },
    {
        "id_absence": 4,
        "motif": "Maladie",
        "date_absence": "2023-10-15T00:00:00Z",
        "id_contrat": 104
    },
    {
        "id_absence": 5,
        "motif": "Congé",
        "date_absence": "2023-10-20T00:00:00Z",
        "id_contrat": 105
    }
];

// Variable pour stocker les données de l'API
const annonces = ref([])

async function loadAnnonces() {
    try {
        // Récupération des données de l'API
        const apiUrl: string = useRuntimeConfig().public.apiUrl as string;
        const response = await axios.get(apiUrl)

        if (response.status === 200) {
            annonces.value = response.data;
        } else {
            console.error('Erreur lors de la récupération des annonces')
        }
    } catch (error) {
        console.error('Erreur lors de la requête API:', error)
    }
}

// Appel de la fonction pour charger les données au chargement du composant
loadAnnonces();


// Filtre par recherche
const q = ref('')

const filteredLignes = computed(() => {
    if (!q.value) {
        return lignes
    }

    return lignes.filter((person) => {
        return Object.values(person).some((value) => {
            return String(value).toLowerCase().includes(q.value.toLowerCase())
        })
    })
});
</script>

<template>
    <h1 class="text-2xl font-bold mb-4">Liste des annonces</h1>

    <div class="flex px-3 py-3.5 border-b border-gray-200 dark:border-gray-700">
        <UInput v-model="q" placeholder="Filtrer les annonces..." />
    </div>

    <UTable :columns="headers" :rows="filteredLignes" />
</template>