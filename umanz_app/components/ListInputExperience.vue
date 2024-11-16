<script setup lang="ts">
import type { ExperiencePoste } from '~/types';

const props = defineProps({
    title: {
        type: String,
        required: true,
    },
    experiences: {
        type: Array as () => ExperiencePoste[],
        required: true
    }
});
</script>

<template>
    <label class="block text-sm font-medium">
        {{ props.title }}
    </label>
    <div v-for="(elmt, index) in props.experiences" :key="index" class="flex items-center mt-2">
        <label class="ml-2 block text-sm">
            {{ elmt.poste.nomPoste }}
        </label>
        <input type="number" v-model="elmt.ans" placeholder="Annee d'exp"
            class="mt-1 block w-full border border-gray-300 rounded-md shadow-sm focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm" />
    </div>
</template>