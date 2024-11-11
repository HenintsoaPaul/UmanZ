<script setup lang="ts">
import { ref, onMounted } from 'vue';
import axios from 'axios';
import type { Annonce, Talent } from '~/types';
import { useRoute } from 'vue-router';

const annonce = ref<Annonce | null>(null);
const candidats = ref<Talent[]>([]);
const route = useRoute();

const columnsCandidats = ref([
    {
        key: "nom",
        label: "Nom"
    },
    {
        key: "prenom",
        label: "Prenom"
    },
    {
        key: "mail",
        label: "E-mail"
    },
]);
const candidatsEndpoint = ref<string>("");

const loadAnnonceDetails = async (apiUrl: string) => {
    try {
        const id = route.params.id;
        const response = await axios.get(`${apiUrl}/annonce/${id}`);

        if (response.status === 200) {
            annonce.value = response.data;
        } else {
            console.error('Erreur lors de la récupération de l\'annonce', response.data);
        }
    } catch (error) {
        console.error('Erreur lors de la requête API:', error);
    }
};

const loadCandidats = async (apiUrl: string) => {
    try {
        const id = route.params.id;
        const response = await axios.get(`${apiUrl}/annonce/${id}/candidats`);

        if (response.status === 200) {
            candidats.value = response.data;
        } else {
            console.error('Erreur lors de la récupération des candidats', response.data);
        }
    } catch (error) {
        console.error('Erreur lors de la requête API:', error);
    }
};

onMounted(() => {
    const apiUrl: string = useRuntimeConfig().public.apiUrl as string;

    loadAnnonceDetails(apiUrl);
    loadCandidats(apiUrl);

    const id = route.params.id;
    candidatsEndpoint.value = `${apiUrl}/annonce/${id}/candidats`;
});
</script>

<template>
    <div class="container mx-auto">
        <h1 class="text-3xl font-bold mb-6 text-center">Détails de l'annonce</h1>

        <div v-if="annonce">
            <h2 class="text-2xl font-semibold">{{ annonce.poste.nom_poste }}</h2>
            <p><strong>Description:</strong> {{ annonce.poste.description_poste }}</p>
            <p><strong>Date Annonce:</strong> {{ annonce.dateAnnonce }}</p>
            <p><strong>Date Expiration:</strong> {{ annonce.dateExpiration }}</p>
            <p><strong>Date Rupture:</strong> {{ annonce.dateRupture || 'N/A' }}</p>

            <h3 class="text-xl font-semibold mt-4">Compétences requises:</h3>
            <ul>
                <li v-for="comp in annonce.competenceAnnonces" :key="comp.competence.idCompetence">
                    {{ comp.competence.competence }} - {{ comp.point }} points
                </li>
            </ul>

            <h3 class="text-xl font-semibold mt-4">Expériences requises:</h3>
            <ul>
                <li v-for="exp in annonce.experiencePostes" :key="exp.poste.idPoste">
                    {{ exp.poste.nom_poste }} - {{ exp.ans }} ans
                </li>
            </ul>
        </div>
        <div v-else>
            Loading Details...
        </div>

        <div v-if="candidats.length">
            <h2 class="text-2xl font-semibold mt-6">Candidats</h2>
            <MyTable :columns="columnsCandidats" :rows="candidats" :api-endpoint="candidatsEndpoint"/>
        </div>
        <div v-else>
            Loading Candidats...
        </div>
    </div>
</template></ul>
</div>