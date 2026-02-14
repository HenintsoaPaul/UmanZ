<script setup lang="ts">
import type { CompetenceTalent, DiplomeAvecNiveau, ExperienceTalent, LangueAvecNiveau } from '~/types';
import SkillsBloc from '~/features/profile/SkillsBloc.component.vue';
import ExperciencesBloc from '~/features/profile/ExperciencesBloc.component.vue';
import DiplomaBloc from '~/features/profile/DiplomaBloc.component.vue';

defineProps<{
    competences: CompetenceTalent[];
    experiences: ExperienceTalent[];
    langues: LangueAvecNiveau[];
    diplomes: DiplomeAvecNiveau[];
}>();
</script>

<template>
    <div class="max-w-3xl mx-auto space-y-6 px-4 pb-12">
        <div v-if="competences && competences.length > 0" class="bg-white rounded-[2rem] shadow-sm border border-slate-100 p-8 transition-all hover:shadow-md">
            <SkillsBloc :competences="competences" />
        </div>

        <div v-if="experiences && experiences.length > 0" class="bg-white rounded-[2rem] shadow-sm border border-slate-100 p-8 transition-all hover:shadow-md">
            <ExperciencesBloc :experiences="experiences" />
        </div>

        <div v-if="diplomes && diplomes.length > 0" class="bg-white rounded-[2rem] shadow-sm border border-slate-100 p-8 transition-all hover:shadow-md">
            <DiplomaBloc :diplomes="diplomes" />
        </div>
    </div>
</template>