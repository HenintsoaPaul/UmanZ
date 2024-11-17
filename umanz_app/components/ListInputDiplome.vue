<script setup lang="ts">
import type { DiplomeAvecNiveau, NiveauDiplome } from '~/types';

const props = defineProps({
    title: {
        type: String,
        default: '',
    },
    niveauDiplomes: {
        type: Array as () => NiveauDiplome[],
        required: true
    },
    listDiplomeAvecNiveau: {
        type: Array as () => DiplomeAvecNiveau[],
        required: true
    }
});
</script>

<template>
    <label class="block text-sm font-medium" v-if="props.title">
        {{ props.title }}
    </label>

    <div v-for="(elmt, index) in props.listDiplomeAvecNiveau" :key="index" class="flex items-center mt-2">
        <label class="w-1/2 ml-2 block text-sm">
            {{ elmt.diplome.diplome }}
        </label>

        <select v-model="elmt.niveauDiplome"
            class="mt-1 block w-full border border-gray-300 rounded-md shadow-sm focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm">
            <option :value="null">
                Non requis
            </option>
            <option v-for="(nl, i) in props.niveauDiplomes" :key="i" :value="nl">
                {{ nl.niveauDiplome }}
            </option>
        </select>
    </div>
</template>
