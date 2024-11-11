<script setup lang="ts">
import { ref, onMounted } from 'vue';
import axios from 'axios';
import type { Contrat } from '~/types';
import { useRoute } from 'vue-router';

const headers = [
    {
        key: 'idContrat',
        label: 'ID',
        sortable: true,
    },
    {
        key: 'dateDebut',
        label: 'Date Debut',
        sortable: true
    },
    {
        key: 'dateFin',
        label: 'Date Fin',
        sortable: true
    },
    {
        key: 'salaireHoraire',
        label: 'Sal/Heure',
        sortable: true
    },
];

const contrat = ref<Contrat | null>(null);

async function loadContratDetails(idContrat: number) {
    try {
        const apiUrl: string = useRuntimeConfig().public.apiUrl as string;
        const response = await axios.get(`${apiUrl}/contrats/${idContrat}`);
        contrat.value = response.data;
    } catch (error) {
        console.error('Erreur lors du chargement des détails du contrat:', error);
    }
}

const route = useRoute();

onMounted(() => {
    const idContrat = Number(route.params.id);
    loadContratDetails(idContrat);
});
</script>

<template>
    <div class="container mx-auto">
        <h1 class="text-3xl font-bold mb-6 text-center">Détails Contrat</h1>

        <div v-if="contrat">
            <p><strong>ID Contrat:</strong> {{ contrat.idContrat }}</p>
            <p><strong>Date Début:</strong> {{ contrat.dateDebut }}</p>
            <p><strong>Date Fin:</strong> {{ contrat.dateFin }}</p>
            <p><strong>Salaire Horaire:</strong> {{ contrat.salaireHoraire }}</p>
            <p><strong>Poste:</strong> {{ contrat.poste.nom_poste }}</p>
            <p><strong>Description du Poste:</strong> {{ contrat.poste.description_poste }}</p>
        </div>
        <div v-else>
            Loading Details...
        </div>
    </div>
</template>