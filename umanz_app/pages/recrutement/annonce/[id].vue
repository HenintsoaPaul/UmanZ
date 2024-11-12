<script setup lang="ts">
import { useRoute } from 'vue-router';
import type { Annonce, CompetenceAnnonce, ExperiencePoste, Talent } from '~/types';

const route = useRoute();
const apiUrl = useRuntimeConfig().public.apiUrl as string;
const annonceId = computed(() => route.params.id);

const { data: annonce, error: annonceError } = useAsyncData<Annonce>(() =>
    $fetch(`${apiUrl}/annonce/${annonceId.value}`)
);

const { data: candidats, error: candidatsError } = useAsyncData<Talent[]>(() =>
    $fetch(`${apiUrl}/annonce/${annonceId.value}/candidats`)
);

const { data: competences, error: competencesError } = useAsyncData<CompetenceAnnonce[]>(() =>
    $fetch(`${apiUrl}/annonce/${annonceId.value}/competences`)
);

const { data: experiences, error: experiencesError } = useAsyncData<ExperiencePoste[]>(() =>
    $fetch(`${apiUrl}/annonce/${annonceId.value}/experiences`)
);

// Table Candidats
const columnsCandidats = [
    { key: "idTalent", label: "ID" },
    { key: "nom", label: "Nom" },
    { key: "prenom", label: "Prenom" },
    { key: "mail", label: "E-mail" },
];

const expand = ref({
    openedRows: [],
    row: {}
});

const validerFn = async (talentId: number) => {
    try {
        const response = await $fetch(`${apiUrl}/entretien/validate`, {
            method: 'POST',
            body: {
                idAnnonce: Number(annonceId.value),
                idTalent: talentId,
            }
        });
        console.log('Candidat validé:', response);
    } catch (error) {
        console.error('Erreur lors de la validation du candidat:', error);
    }
}

const refuserFn = async (talentId: number) => {
    try {
        const response = await $fetch(`${apiUrl}/entretien/deny`, {
            method: 'POST',
            body: {
                idAnnonce: Number(annonceId.value),
                idTalent: talentId,
            }
        });
        console.log('Candidat Refuse:', response);
    } catch (error) {
        console.error('Erreur lors de la validation du refus:', error);
    }
}
</script>

<template>
    <div class="container mx-auto">
        <h1 class="text-3xl font-bold mb-6 text-center">Détails de l'annonce</h1>

        <div v-if="annonce">
            <h2 class="text-2xl font-semibold">{{ annonce?.poste.nomPoste }}</h2>
            <p><strong>Description:</strong> {{ annonce?.poste.description }}</p>
            <p><strong>Date Annonce:</strong> {{ annonce?.dateAnnonce }}</p>
            <p><strong>Date Expiration:</strong> {{ annonce?.dateExpiration }}</p>
            <p><strong>Date Rupture:</strong> {{ annonce?.dateRupture || 'N/A' }}</p>

            <h3 class="text-xl font-semibold mt-4">Compétences requises:</h3>
            <ul>
                <li v-for="comp in competences" :key="comp.competence.idCompetence">
                    {{ comp.competence.competence }} - {{ comp.point }} points
                </li>
            </ul>

            <h3 class="text-xl font-semibold mt-4">Expériences requises:</h3>
            <ul>
                <li v-for="exp in experiences" :key="exp.poste.idPoste">
                    {{ exp.poste.nomPoste }} - {{ exp.ans }} ans
                </li>
            </ul>
        </div>
        <div v-else>
            Loading Details...
        </div>

        <h1 class="text-3xl font-bold mb-6 text-center">Candidats En Attente de Validation</h1>

        <div v-if="candidats?.length">
            <UTable :columns="columnsCandidats" :rows="candidats" v-model:expand="expand"
                class="w-full shadow-md rounded-lg overflow-hidden">
                <template #expand="{ row }">
                    <div class="p-4">


                        <button class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded"
                            @click="validerFn(row.idTalent)">
                            Valider
                        </button>
                        <button class="bg-red-500 hover:bg-red-700 text-white font-bold py-2 px-4 rounded"
                            @click="refuserFn(row.idTalent)">
                            Refuser
                        </button>
                    </div>
                </template>
            </UTable>
        </div>
        <div v-else>
            Loading Candidats...
        </div>
    </div>
</template>