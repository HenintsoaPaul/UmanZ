<script setup lang="ts">
import axios from 'axios';
import type { Entretien } from '~/types';

// Columns a afficher
const headers = [
    {
        key: 'id_entretien',
        label: 'ID',
        sortable: true
    },
    {
        key: 'id_talent',
        label: 'Talent',
        sortable: true
    },
    {
        key: 'date_creation',
        label: 'Date Creation',
        sortable: true
    },
    {
        key: 'date_validation',
        label: 'Date Validation',
        sortable: true
    },
    {
        key: 'note',
        label: 'Note',
        sortable: true
    },
    {
        key: 'motif',
        label: 'Motif',
        sortable: true
    }
];

// Variable pour stocker les données de l'API
const lignes = ref<Entretien[]>([]);
const q = ref('');

const filteredLignes = computed(() => {
    return q.value ?
        lignes.value.filter((e: Entretien) =>
            Object.values(e).some((value) =>
                String(value).toLowerCase().includes(q.value.toLowerCase())
            )
        ) : lignes.value;
});

async function loadAnnonces() {
    try {
        const apiUrl: string = "http://localhost:911";

        const response = await axios.get(`${apiUrl}/entretiens`);

        if (response.status === 200 && Array.isArray(response.data)) {
            console.log(toRaw(response.data));

            lignes.value = response.data;
        } else {
            console.error('Erreur lors de la récupération des annonces', response.data);
        }
    } catch (error) {
        console.error('Erreur lors de la requête API:', error);
    }
}

// Appel de la fonction pour charger les données au chargement du composant
loadAnnonces();
</script>

<template>
    <h1 class="text-2xl font-bold mb-4">Liste des Entretiens</h1>

    <div class="flex px-3 py-3.5 border-b border-gray-200 dark:border-gray-700">
        <UInput v-model="q" placeholder="Filtrer les annonces..." />
    </div>

    <UTable :columns="headers" :rows="filteredLignes" />
</template>
