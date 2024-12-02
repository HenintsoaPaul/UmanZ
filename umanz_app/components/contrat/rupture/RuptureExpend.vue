<script setup lang="ts">
import { defineProps } from 'vue';
import type { Contrat } from '~/types/interne/contrat';

const props = defineProps<{
    contrat: Contrat;
}>();

const emit = defineEmits(["licencier", "retraite"]);
const licencierFn = () => { emit("licencier", props.contrat); }
const retraiteFn = () => { emit("retraite", props.contrat); }
</script>

<template>
    <div class="flex gap-4">
        <button class="bg-green-500 hover:bg-green-700 text-white font-bold py-2 px-4 rounded" @click="licencierFn">
            Licencier
        </button>

        <button class="bg-red-500 hover:bg-red-700 text-white font-bold py-2 px-4 rounded ml-2" @click="retraiteFn">
            Retraite
        </button>
    </div>
</template>