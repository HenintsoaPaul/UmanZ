<script setup lang="ts">
import type { Langue, NiveauLangue } from '~/types';

const props = defineProps({
    title: {
        type: String,
        default: '',
    },
    langues: {
        type: Array as () => Langue[],
        required: true
    },
    niveauLangues: {
        type: Array as () => NiveauLangue[],
        required: true
    }
});
</script>

<template>
    <label class="block text-sm font-medium" v-if="props.title">
        {{ props.title }}
    </label>
    <div v-for="(elmt, index) in props.langues" :key="index" class="flex items-center mt-2">
        <label class="w-1/2 ml-2 block text-sm">
            {{ elmt.langue }}
        </label>

        <select
            class="mt-1 block w-full border border-gray-300 rounded-md shadow-sm focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm">
            <option value="">
                Non requis
            </option>
            <option v-for="(nl, i) in props.niveauLangues" :key="i" :value="nl.idNiveauLangue">
                {{ nl.niveauLangue }}
            </option>
        </select>
    </div>
</template>