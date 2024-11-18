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
    <label class="block text-xl font-medium" v-if="props.title">
        {{ props.title }}
    </label>

    <div v-for="(elmt, index) in props.listDiplomeAvecNiveau" :key="index" class="flex items-center mt-2">
        <input type="checkbox" v-model="elmt.selected" class="form-checkbox h-5 w-5 text-indigo-600">

        <label class="w-1/2 ml-2 block text-sm">
            {{ elmt.diplome.diplome }} ({{ elmt.diplome.niveauDiplome.niveauDiplome }})
        </label>
    </div>
</template>
