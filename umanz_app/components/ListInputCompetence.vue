<script setup lang="ts">
import type { CompetenceAnnonce } from '~/types';

const props = defineProps({
    title: {
        type: String,
        default: '',
    },
    competences: {
        type: Array as () => CompetenceAnnonce[],
        required: true
    }
});
</script>

<template>
    <label class="block font-medium text-xl" v-if="props.title">
        {{ props.title }}
    </label>
    <div v-for="(elmt, index) in props.competences" :key="index" class="flex items-center mt-2">
        <label class="w-1/2 ml-2 block text-sm">
            {{ elmt.competence.competence }}
        </label>
        <input type="number" v-model="elmt.point" placeholder="Point" max="10" min="0"
            class="mt-1 block w-full border border-gray-300 rounded-md shadow-sm focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm" />
    </div>
</template>