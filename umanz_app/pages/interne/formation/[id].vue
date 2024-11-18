<script setup lang="ts">
import { useRoute } from 'vue-router';
import type { Formation, Participation } from '~/types';

const route = useRoute();
const apiUrl = useRuntimeConfig().public.apiUrl as string;
const { data: formation } = useFetch<Formation>(`${apiUrl}/formations/${route.params.id}`);
const { data: participants } = useFetch<Participation[]>(`${apiUrl}/participations/${route.params.id}`);

async function finir() {
    try {
        const response = await fetch(`${apiUrl}/formations/${route.params.id}/finir`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            }
        });
        if (!response.ok) {
            throw new Error('Failed to finish the formation');
        }
    } catch (error) {
        console.error(error);
    }
}
</script>

<template>
    <div class="max-w-4xl mx-auto p-6 border border-gray-300 rounded-lg shadow-md text-sm">
        <template v-if="formation">
            <h1 class="text-3xl font-bold mb-6">{{ formation?.nomFormation }}</h1>
            <div class="mb-4">
                <p><strong>Date de début:</strong> {{ formation?.dateDebut }}</p>
                <p><strong>Date de fin:</strong> {{ formation?.dateFin }}</p>
            </div>
            <div class="mb-4">
                <h2 class="text-2xl font-bold mb-2">Compétences Cibles</h2>
                <ul class="list-disc pl-5">
                    <li v-for="competenceCible in formation?.competenceCibles"
                        :key="competenceCible.competence.idCompetence">
                        {{ competenceCible.competence.competence }} - Points: {{ competenceCible.pointGagne }}
                    </li>
                </ul>
            </div>

            <hr>
            <br>

            <div class="mb-4">
                <template v-if="participants">
                    <template v-if="participants.length > 0">
                        <h2 class="text-2xl font-bold mb-2">Participants</h2>
                        <ul class="list-disc pl-5">
                            <li v-for="(parti, i) in participants" :key="i">
                                {{ parti.contrat.talent.nom }} {{ parti.contrat.talent.prenom }}
                            </li>
                        </ul>

                        <!-- <UTable :columns="headers" :rows="filteredAnnonces ?? []" v-model:expand="expand"
                            class="w-full shadow-md rounded-lg overflow-hidden">
                        </UTable> -->
                    </template>
                    <div v-else>
                        No Participant
                    </div>
                </template>
                <div v-else>
                    Loading Participants...
                </div>
            </div>

            <div class="mb-4">
                <button class="bg-indigo-500 hover:bg-indigo-700 text-white font-bold py-2 px-4 rounded" @click="finir"
                    :disabled="!formation.estFini">
                    Finir la formation
                </button>
            </div>
        </template>
        <div class="mb-4" v-else>
            Loading Formation...
        </div>
    </div>
</template>