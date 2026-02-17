<script setup lang="ts">
import type { CompetenceTalent } from '~/types';

const props = defineProps<{
    competences: CompetenceTalent[];
}>();
</script>

<template>
    <div class="space-y-4">
        <div class="flex items-center gap-3">
            <div class="w-10 h-10 bg-umanz-purple/10 dark:bg-umanz-purple/20 flex items-center justify-center rounded-xl">
                <UIcon name="i-heroicons-bolt" class="w-6 h-6 text-umanz-purple dark:text-umanz-purple/90" />
            </div>
            <h3 class="text-xl font-bold text-slate-800 dark:text-slate-100">Compétences</h3>
        </div>

        <div v-if="competences.length === 0" class="flex flex-col items-center justify-center py-8 text-slate-400 dark:text-slate-500 bg-slate-50 dark:bg-slate-800/40 rounded-xl border border-dashed border-slate-200 dark:border-slate-700 transition-colors duration-300">
            <UIcon name="i-heroicons-information-circle" class="w-8 h-8 mb-2" />
            <p class="text-sm font-medium">Aucune compétence à afficher</p>
        </div>
        
        <div v-else class="grid grid-cols-1 sm:grid-cols-2 gap-3">
            <div v-for="comp in competences" :key="comp.competence.idCompetence" 
                class="flex items-center justify-between p-4 bg-slate-50/50 dark:bg-slate-800/40 rounded-xl border border-slate-100 dark:border-slate-800 hover:border-umanz-purple/30 dark:hover:border-umanz-purple/50 transition-all duration-300 group">
                <span class="font-semibold text-slate-700 dark:text-slate-300 group-hover:text-umanz-purple dark:group-hover:text-umanz-purple/90 transition-colors">{{ comp.competence.competence }}</span>
                <UBadge color="primary" variant="soft" class="rounded-lg">{{ comp.point }} pts</UBadge>
            </div>
        </div>
    </div>
</template>