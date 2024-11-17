<script setup lang="ts">
import type { LangueAvecNiveau, NiveauLangue } from '~/types';

const props = defineProps({
    title: {
        type: String,
        default: '',
    },
    niveauLangues: {
        type: Array as () => NiveauLangue[],
        required: true
    },
    listLangueAvecNiveau: {
        type: Array as () => LangueAvecNiveau[],
        required: true
    }
});
</script>

<template>
    <label class="block text-sm font-medium" v-if="props.title">
        {{ props.title }}
    </label>

    <div v-for="(elmt, index) in props.listLangueAvecNiveau" :key="index" class="flex items-center mt-2">
        <label class="w-1/2 ml-2 block text-sm">
            {{ elmt.langue.langue }}
        </label>

        <select v-model="elmt.niveauLangue"
            class="mt-1 block w-full border border-gray-300 rounded-md shadow-sm focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm">
            <option :value="null">
                Non requis
            </option>
            <option v-for="(nl, i) in props.niveauLangues" :key="i" :value="nl">
                {{ nl.niveauLangue }}
            </option>
        </select>
    </div>
</template>
