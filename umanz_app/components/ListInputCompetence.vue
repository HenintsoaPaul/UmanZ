<script setup lang="ts">
import type { CompetenceAnnonce } from '~/types';

const props = defineProps({
    title: {
        type: String,
        required: true,
    },
    competences: {
        type: Array as () => CompetenceAnnonce[],
        required: true
    }
});
</script>

<template>
    <label class="block text-sm font-medium text-gray-700">
        {{ props.title }}
    </label>
    <div v-for="(elmt, index) in props.competences" :key="index" class="flex items-center mt-2">
        <label class="ml-2 block text-sm text-gray-900">
            {{ elmt.competence.competence }}
        </label>
        <input type="number" v-model="elmt.point" placeholder="Point"
            class="mt-1 block w-full border border-gray-300 rounded-md shadow-sm focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm" />
    </div>
</template>