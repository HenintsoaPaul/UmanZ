<script setup lang="ts">
import type { DiplomeAvecNiveau } from '~/types';

const props = defineProps<{
    diplomes: DiplomeAvecNiveau[];
}>();
</script>

<template>
    <div class="space-y-4">
        <div class="flex items-center gap-3">
            <div class="w-10 h-10 bg-umanz-orange/10 dark:bg-umanz-orange/20 flex items-center justify-center rounded-xl">
                <UIcon name="i-heroicons-academic-cap" class="w-6 h-6 text-umanz-orange dark:text-umanz-orange/90" />
            </div>
            <h3 class="text-xl font-bold text-slate-800 dark:text-slate-100">Diplômes</h3>
        </div>

        <div v-if="diplomes.length === 0" class="flex flex-col items-center justify-center py-8 text-slate-400 dark:text-slate-500 bg-slate-50 dark:bg-slate-800/40 rounded-xl border border-dashed border-slate-200 dark:border-slate-700 transition-colors duration-300">
            <UIcon name="i-heroicons-information-circle" class="w-8 h-8 mb-2" />
            <p class="text-sm font-medium">Aucun diplôme à afficher</p>
        </div>
        
        <div v-else class="space-y-3">
            <div v-for="diplome in diplomes" :key="diplome.diplome.diplome" 
                class="p-4 bg-slate-50/50 dark:bg-slate-800/40 rounded-xl border border-slate-100 dark:border-slate-800 hover:border-umanz-orange/30 dark:hover:border-umanz-orange/50 transition-all duration-300 group">
                <div class="flex items-start justify-between">
                    <div class="space-y-1">
                        <p class="font-bold text-slate-700 dark:text-slate-300 group-hover:text-umanz-orange dark:group-hover:text-umanz-orange/90 transition-colors line-clamp-1">{{ diplome.diplome }}</p>
                        <div v-if="diplome.niveauDiplome" class="flex items-center gap-2 text-slate-500 dark:text-slate-400">
                            <UIcon name="i-heroicons-chart-bar" class="w-4 h-4" />
                            <p class="text-xs font-medium uppercase tracking-wider">{{ diplome.niveauDiplome?.niveauDiplome || 'Niveau non précisé' }}</p>
                        </div>
                    </div>
                    <UIcon name="i-heroicons-check-badge" class="w-5 h-5 text-umanz-orange/40 dark:text-umanz-orange/30 group-hover:text-umanz-orange dark:group-hover:text-umanz-orange transition-colors" />
                </div>
            </div>
        </div>
    </div>
</template>