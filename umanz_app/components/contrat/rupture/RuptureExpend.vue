<script setup lang="ts">
import { defineProps } from 'vue';
import type { Contrat } from '~/types/interne/contrat';

const props = defineProps<{
    contrat: Contrat;
}>();

const emit = defineEmits(["licencier", "cv"]);
const licencierFn = () => { emit("licencier", props.contrat); }
const cvFn = () => { emit("cv", props.contrat); }
</script>

<template>
    <div>
        <div class="mb-4">
            <h3 class="text-xl font-semibold mb-2">Poste :</h3>
            <p class="text-gray-700">
                <strong class="text-gray-500 font-bold">Titre:</strong> {{ contrat.poste.nomPoste }}
            </p>
            <p class="text-gray-700">
                <strong class="text-gray-500 font-bold">Description:</strong> {{ contrat.poste.description }}
            </p>
        </div>
        <div class="flex">
            <button class="bg-green-500 hover:bg-green-700 text-white font-bold py-2 px-4 rounded" @click="licencierFn">
                Licencier
            </button>

            <button class="bg-red-500 hover:bg-red-700 text-white font-bold py-2 px-4 rounded ml-2" @click="cvFn">
                Consulter CV
            </button>
        </div>
    </div>
</template>
