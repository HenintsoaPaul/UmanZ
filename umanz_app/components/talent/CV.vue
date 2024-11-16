<script setup lang="ts">
import type { CompetenceTalent, ExperienceTalent } from '~/types';

const props = defineProps<{
    competences: CompetenceTalent[];
    experiences: ExperienceTalent[];
}>();
</script>

<template>
    <div class="cv">
        <div>
            <h3 class="text-2xl font-semibold mt-4">Compétences:</h3>
            <div v-if="competences.length > 0">
                <ul>
                    <li v-for="comp in competences" :key="comp.competence.idCompetence">
                        {{ comp.competence.competence }} - {{ comp.point }} points
                    </li>
                </ul>
            </div>
            <div v-else>
                No Competences
            </div>
        </div>

        <div>
            <h3 class="text-2xl font-semibold mt-4">Expériences:</h3>
            <div v-if="experiences.length > 0">
                <ul>
                    <li v-for="exp in experiences" :key="exp.poste.idPoste">
                        {{ exp.poste.nomPoste }} - {{ exp.ans }} ans
                    </li>
                </ul>
            </div>
            <div v-else>
                No Experiences
            </div>
        </div>
    </div>
</template>

<style scoped>
.cv {
    max-width: 600px;
    margin: 0 auto;
    padding: 20px;
    border: 1px solid #ccc;
    border-radius: 10px;
}

.cv-title {
    font-size: 24px;
    font-weight: bold;
    margin-bottom: 20px;
}

.cv-section-title {
    font-size: 20px;
    font-weight: bold;
    margin-top: 20px;
    margin-bottom: 10px;
}
</style>