<script setup lang="ts">
import type { CompetenceTalent, DiplomeAvecNiveau, ExperienceTalent, LangueAvecNiveau } from '~/types';

defineProps<{
    competences: CompetenceTalent[];
    experiences: ExperienceTalent[];
    langues: LangueAvecNiveau[];
    diplomes: DiplomeAvecNiveau[];
}>();
</script>

<template>
    <div class="cv">
        <div>
            <div v-if="competences">
                <h3 class="text-2xl font-semibold mt-4">Compétences:</h3>
                <div v-if="competences.length > 0">
                    <ul>
                        <li v-for="comp in competences" :key="comp.competence.idCompetence" class="text-sm">
                            {{ comp.competence.competence }} - {{ comp.point }} points
                        </li>
                    </ul>
                </div>
                <div v-else>
                    No Competences
                </div>
            </div>
            <div v-if="experiences">
                <h3 class="text-2xl font-semibold mt-4">Expériences:</h3>
                <div v-if="experiences.length > 0">
                    <ul>
                        <li v-for="exp in experiences" :key="exp.poste.idPoste" class="text-sm">
                            {{ exp.poste.nomPoste }} - {{ exp.ans }} ans
                        </li>
                    </ul>
                </div>
                <div v-else>
                    No Experiences
                </div>
            </div>
        </div>
        <br>
        <hr>
        <div>
            <!-- <div v-if="langues">
                <h3 class="text-2xl font-semibold mt-4">Langues:</h3>
                <div v-if="langues.length > 0">
                    <ul>
                        <li v-for="lg in langues" :key="lg.langue.idLangue">
                            {{ lg.langue.langue }} - {{ lg.niveauLangue?.niveau }} points
                        </li>
                    </ul>
                </div>
                <div v-else>
                    No langues
                </div>
            </div> -->
            <div v-if="diplomes">
                <h3 class="text-2xl font-semibold mt-4">Diplomes:</h3>
                <div v-if="diplomes.length > 0">
                    <ul>
                        <li v-for="dipl in diplomes" :key="dipl.diplome.diplome" class="text-sm">
                            {{ dipl.diplome }} - {{ dipl.niveauDiplome?.niveauDiplome }}
                        </li>
                    </ul>
                </div>
                <div v-else>
                    No Experiences
                </div>
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