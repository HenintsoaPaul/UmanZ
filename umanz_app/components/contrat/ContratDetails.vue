<script setup lang="ts">
import { defineProps } from 'vue';
import type { Contrat } from '~/types';

const props = defineProps<{
    apiUrl: string;
    contrat: Contrat;
    promotionFn: (idContrat: number) => Promise<void>;
    explusionFn: (idContrat: number, apiUrl: string) => Promise<void>;
    demissionFn: (idContrat: number, apiUrl: string) => Promise<void>;
}>();
</script>

<template>
    <div class="p-4">
        <div class="mb-4">
            <h3 class="text-xl font-semibold mb-2">Contrat :</h3>
            <p class="text-gray-700">{{ contrat.contrat }}</p>
        </div>
        <div class="mb-4">
            <h3 class="text-xl font-semibold mb-2">Poste :</h3>
            <p class="text-gray-700">{{ contrat.poste.description }}</p>
        </div>

        <div class="flex gap-4">
            <div class="mb-4 w-1/3">
                <h3 class="text-xl font-semibold mb-2">Salaire par heure :</h3>
                <p class="text-gray-700">{{ contrat.salaireHoraire }} Ar</p>
            </div>
            <div class="mb-4 w-1/3">
                <h3 class="text-xl font-semibold mb-2">Nb Heure par jour :</h3>
                <p class="text-gray-700">{{ contrat.nbHeureJour }}</p>
            </div>
            <div class="mb-4 w-1/3">
                <h3 class="text-xl font-semibold mb-2">Nb jour par semaine :</h3>
                <p class="text-gray-700">{{ contrat.nbJourSemaine }}</p>
            </div>
        </div>

        <button class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded"
            @click="props.promotionFn(contrat.idContrat)">
            Promotion
        </button>
        <button class="bg-red-500 hover:bg-red-700 text-white font-bold py-2 px-4 rounded"
            @click="props.explusionFn(contrat.idContrat, props.apiUrl)">
            Licenciement
        </button>
        <button class="bg-yellow-500 hover:bg-yellow-700 text-white font-bold py-2 px-4 rounded"
            @click="props.demissionFn(contrat.idContrat, props.apiUrl)">
            Demission
        </button>
    </div>
</template>