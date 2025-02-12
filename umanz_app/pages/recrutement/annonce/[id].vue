<script setup lang="ts">
import { useRoute } from 'vue-router';
import type { Annonce, CompetenceAnnonce, ExperiencePoste, Talent } from '~/types';

const route = useRoute();
const apiUrl = useRuntimeConfig().public.apiUrl as string;
const annonceId = computed(() => route.params.id);
const url = computed(() => `${apiUrl}/annonce/${annonceId.value}`);

const { data: annonce, error: annonceError } = useFetch<Annonce>(`${url.value}`);
const { data: candidatsInterne, refresh: refreshInterne } = useFetch<Talent[]>(`${url.value}/candidats/interne`);
const { data: candidatsExterne, refresh: refreshExterne } = useFetch<Talent[]>(`${url.value}/candidats/externe`);
const { data: competences, error: competencesError } = useFetch<CompetenceAnnonce[]>(`${url.value}/competences`);
const { data: experiences, error: experiencesError } = useFetch<ExperiencePoste[]>(`${url.value}/experiences`);

const successMessage = ref('');
const errorMessage = ref('');

// Table Candidats
const columnsCandidats = [
    { key: "idTalent", label: "ID" },
    { key: "nom", label: "Nom" },
    { key: "prenom", label: "Prenom" },
    { key: "mail", label: "E-mail" },
];

const expandInterne = ref({
    openedRows: [],
    row: {}
});
const expandExterne = ref({
    openedRows: [],
    row: {}
});

const { validerFn: valider, refuserFn: refuser } = useAnnonceActions();

const validerFn = async (talentId: number) => {
    try {
        await valider(talentId, Number(annonceId.value), apiUrl);
        successMessage.value = 'Candidat validé avec succès';
        errorMessage.value = '';
        await refreshInterne();
        await refreshExterne();
    } catch (error) {
        errorMessage.value = 'Erreur lors de la validation du candidat';
        successMessage.value = '';
        console.error('Erreur lors de la validation du candidat:', error);
    }
}

const refuserFn = async (talentId: number) => {
    try {
        await refuser(talentId, Number(annonceId.value), apiUrl);
        successMessage.value = 'Candidat refusé avec succès';
        errorMessage.value = '';
        await refreshInterne();
        await refreshExterne();
    } catch (error) {
        errorMessage.value = 'Erreur lors du refus du candidat';
        successMessage.value = '';
        console.error('Erreur lors du refus du candidat:', error);
    }
}

const isAdmin = computed(() => localStorage.getItem("umanz-isAdmin") === 'true');
</script>

<template>
    <div class="container mx-auto text-sm">
        <div v-if="annonce && competences && experiences">
            <AnnonceDetails :annonce="annonce" :competences="competences" :experiences="experiences" />
        </div>
        <div v-else>
            Loading Details...
        </div>

        <div v-if="annonceError" class="text-red-500">
            Erreur lors du chargement de l'annonce: {{ annonceError.message }}
        </div>
        <div v-if="competencesError" class="text-red-500">
            Erreur lors du chargement des compétences: {{ competencesError.message }}
        </div>
        <div v-if="experiencesError" class="text-red-500">
            Erreur lors du chargement des expériences: {{ experiencesError.message }}
        </div>

        <br>

        <div v-if="successMessage" class="text-green-500 text-center">
            {{ successMessage }}
        </div>
        <div v-if="errorMessage" class="text-red-500 text-center">
            {{ errorMessage }}
        </div>

        <h1 class="text-3xl font-bold mb-6 text-center">Candidats Interne</h1>
        <div v-if="candidatsInterne">
            <div v-if="candidatsInterne.length > 0">
                <UTable :columns="columnsCandidats" :rows="candidatsInterne" v-model:expand="expandInterne"
                    class="w-full shadow-md rounded-lg overflow-hidden">
                    <template #expand="{ row }">
                        <template v-if="isAdmin">
                            <button
                                class="bg-green-500 hover:bg-green-700 text-white font-bold py-2 px-4 mt-2 rounded mr-2"
                                @click="validerFn(row.idTalent)">
                                Valider
                            </button>
                            <button class="bg-red-500 hover:bg-red-700 text-white font-bold py-2 px-4 mt-2 rounded mr-2"
                                @click="refuserFn(row.idTalent)">
                                Refuser
                            </button>
                        </template>
                        <button class="bg-indigo-500 hover:bg-indigo-700 text-white font-bold py-2 px-4 mt-2 rounded"
                            @click="$router.push(`/talent/${row.idTalent}`)">
                            Consulter CV
                        </button>
                    </template>
                </UTable>
            </div>
            <div v-else class="text-center mb-5">
                Aucun Candidats Interne pour le moment
            </div>
        </div>
        <div v-else>
            Chargement ...
        </div>

        <h1 class="text-3xl font-bold mb-6 text-center">Candidats Externe</h1>
        <div v-if="candidatsExterne">
            <div v-if="candidatsExterne.length > 0">
                <UTable :columns="columnsCandidats" :rows="candidatsExterne" v-model:expand="expandExterne"
                    class="w-full shadow-md rounded-lg overflow-hidden">
                    <template #expand="{ row }">
                        <template v-if="isAdmin">
                            <button
                                class="bg-green-500 hover:bg-green-700 text-white font-bold py-2 px-4 mt-2 rounded mr-2"
                                @click="validerFn(row.idTalent)">
                                Valider
                            </button>
                            <button class="bg-red-500 hover:bg-red-700 text-white font-bold py-2 px-4 mt-2 rounded mr-2"
                                @click="refuserFn(row.idTalent)">
                                Refuser
                            </button>
                        </template>
                        <button class="bg-indigo-500 hover:bg-indigo-700 text-white font-bold py-2 px-4 mt-2 rounded"
                            @click="$router.push(`/talent/${row.idTalent}`)">
                            Consulter CV
                        </button>
                    </template>
                </UTable>
            </div>
            <div v-else class="text-center mb-5">
                Aucun Candidats Externe pour le moment
            </div>
        </div>
        <div v-else>
            Chargement ...
        </div>
    </div>
</template>