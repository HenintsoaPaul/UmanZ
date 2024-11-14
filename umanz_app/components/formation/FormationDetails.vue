<script setup lang="ts">
import { defineProps } from 'vue';
import type { Formation } from '~/types';

const props = defineProps<{
    apiUrl: string;
    idContrat: number;
    formation: Formation;
    participerFn: (idContrat: number, idFormation: number, apiUrl: string) => Promise<void>;
}>();
</script>

<template>
    <div class="mb-4">
        <h3 class="text-xl font-semibold mb-2">Titre:</h3>
        <p class="text-gray-700">{{ formation.nomFormation }}</p>
    </div>

    <div class="mb-4" v-if="formation.competenceCibles.length">
        <h3 class="text-xl font-semibold mb-2">Competences Cibles:</h3>
        <ul>
            <li v-for="(cible, i) in formation.competenceCibles" :key="i">
                {{ cible.competence.competence }} - Manampy {{ cible.pointGagne }}
            </li>
        </ul>
    </div>

    <button class="bg-green-500 hover:bg-green-700 text-white font-bold py-2 px-4 rounded"
        @click="participerFn(props.idContrat, formation.idFormation, props.apiUrl)">
        Participer
    </button>
</template>