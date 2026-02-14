<script setup lang="ts">
import type { ExperienceTalent } from '~/types';

const props = defineProps<{
    experiences: ExperienceTalent[];
}>();
</script>

<template>
    <div class="space-y-4">
        <div class="flex items-center gap-3">
            <div class="w-10 h-10 bg-umanz-green/10 flex items-center justify-center rounded-xl">
                <UIcon name="i-heroicons-briefcase" class="w-6 h-6 text-umanz-green" />
            </div>
            <h3 class="text-xl font-bold text-slate-800">Expériences</h3>
        </div>

        <div v-if="experiences.length === 0" class="flex flex-col items-center justify-center py-8 text-slate-400 bg-slate-50 rounded-xl border border-dashed border-slate-200">
            <UIcon name="i-heroicons-information-circle" class="w-8 h-8 mb-2" />
            <p class="text-sm font-medium">Aucune expérience à afficher</p>
        </div>
        
        <div v-else class="space-y-3">
            <div v-for="exp in experiences" :key="exp.poste.idPoste" 
                class="flex items-center justify-between p-4 bg-slate-50/50 rounded-xl border border-slate-100 hover:border-umanz-green/30 transition-colors group">
                <div class="flex flex-col">
                    <span class="font-bold text-slate-700 group-hover:text-umanz-green transition-colors">{{ exp.poste.nomPoste }}</span>
                </div>
                <div class="flex items-center gap-2 text-slate-500">
                    <UIcon name="i-heroicons-calendar" class="w-4 h-4" />
                    <span class="text-sm font-semibold">{{ exp.ans }} ans</span>
                </div>
            </div>
        </div>
    </div>
</template>