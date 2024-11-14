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

    <button class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded"
        @click="participerFn(props.idContrat, formation.idFormation, props.apiUrl)">
        Candidater
    </button>
</template>