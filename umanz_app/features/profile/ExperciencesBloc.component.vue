<script setup lang="ts">
import type { ExperienceTalent } from '~/types';

const props = defineProps<{
    experiences: ExperienceTalent[];
}>();
</script>

<template>
    <div class="space-y-4">
        <div class="flex items-center gap-3">
            <div class="w-10 h-10 bg-umanz-green/10 dark:bg-umanz-green/20 flex items-center justify-center rounded-xl">
                <UIcon name="i-heroicons-briefcase" class="w-6 h-6 text-umanz-green dark:text-umanz-green/90" />
            </div>
            <h3 class="text-xl font-bold text-slate-800 dark:text-slate-100">Expériences</h3>
        </div>

        <div v-if="experiences.length === 0" class="flex flex-col items-center justify-center py-8 text-slate-400 dark:text-slate-500 bg-slate-50 dark:bg-slate-800/40 rounded-xl border border-dashed border-slate-200 dark:border-slate-700 transition-colors duration-300">
            <UIcon name="i-heroicons-information-circle" class="w-8 h-8 mb-2" />
            <p class="text-sm font-medium">Aucune expérience à afficher</p>
        </div>
        
        <div v-else class="space-y-3">
            <div v-for="exp in experiences" :key="exp.poste.idPoste" 
                class="flex items-center justify-between p-4 bg-slate-50/50 dark:bg-slate-800/40 rounded-xl border border-slate-100 dark:border-slate-800 hover:border-umanz-green/30 dark:hover:border-umanz-green/50 transition-all duration-300 group">
                <div class="flex flex-col">
                    <span class="font-bold text-slate-700 dark:text-slate-300 group-hover:text-umanz-green dark:group-hover:text-umanz-green/90 transition-colors">{{ exp.poste.nomPoste }}</span>
                </div>
                <div class="flex items-center gap-2 text-slate-500 dark:text-slate-400">
                    <UIcon name="i-heroicons-calendar" class="w-4 h-4" />
                    <span class="text-sm font-semibold">{{ exp.ans }} ans</span>
                </div>
            </div>
        </div>
    </div>
</template>