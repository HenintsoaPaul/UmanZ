<script setup lang="ts">
import { defineProps } from 'vue';
import type { Annonce, CompetenceAnnonce, ExperiencePoste } from '~/types';

const props = defineProps<{
    annonce: Annonce;
    competences: CompetenceAnnonce[];
    experiences: ExperiencePoste[];
}>();
</script>

<template>
    <h1 class="text-3xl font-bold mb-6 text-center">Détails de l'annonce</h1>

    <div v-if="annonce">
        <h2 class="text-2xl font-semibold">{{ annonce.poste.nomPoste }}</h2>
        <p><strong>Description:</strong> {{ annonce.poste.description }}</p>
        <p><strong>Date Annonce:</strong> {{ annonce.dateAnnonce }}</p>
        <p><strong>Date Expiration:</strong> {{ annonce.dateExpiration }}</p>
        <p><strong>Date Rupture:</strong> {{ annonce.dateRupture || 'N/A' }}</p>
    </div>
    <div v-else>
        No Annonce
    </div>

    <div v-if="competences">
        <h3 class="text-xl font-semibold mt-4">Compétences requises:</h3>
        <ul>
            <li v-for="comp in competences" :key="comp.competence.idCompetence">
                {{ comp.competence.competence }} - {{ comp.point }} points
            </li>
        </ul>
    </div>
    <div v-else>
        No Competences
    </div>

    <div v-if="experiences">
        <h3 class="text-xl font-semibold mt-4">Expériences requises:</h3>
        <ul>
            <li v-for="exp in experiences" :key="exp.poste.idPoste">
                {{ exp.poste.nomPoste }} - {{ exp.ans }} ans
            </li>
        </ul>
    </div>
    <div v-else>
        No Experiences
    </div>
</template>