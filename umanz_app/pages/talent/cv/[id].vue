<template>
    <div class="cv">
        <div v-if="cv" class="cv-details">
            <h1 class="cv-title">CV de {{ talent?.nom }} {{ talent?.prenom }}</h1>

            <h2 class="cv-section-title">Compétences</h2>
            <ul>
                <li v-for="competence in cv.competences" :key="competence.id_competence">
                    {{ competence.id_competence }} - {{ competence.point }} points
                </li>
            </ul>

            <h2 class="cv-section-title">Expériences</h2>
            <ul>
                <li v-for="experience in cv.experiences" :key="experience.id_poste">
                    {{ experience.id_poste }} - {{ experience.ans }} ans
                </li>
            </ul>
        </div>

        <div v-else>
            <p>Loading...</p>
        </div>
    </div>
</template>

<script setup lang="ts">
import { ref, onMounted } from 'vue';
import { useRoute } from 'vue-router';
import axios from 'axios';
import type { Talent, CV } from '~/types';

// State
const talent = ref<Talent | null>(null);
const cv = ref<CV | null>(null);

// Get the route
const route = useRoute();

// Fetch talent data
// const apiUrl: string = useRuntimeConfig().public.apiUrl as string;
const apiUrl: string = "http://localhost:911";

const fetchTalent = async (id: number) => {
    try {
        const response = await axios.get(`${apiUrl}/talents/${id}`);
        if (response.status === 200) {
            talent.value = response.data;
        } else {
            console.error('Erreur lors de la récupération du talent');
        }
    } catch (error) {
        console.error('Erreur lors de la requête API:', error);
    }
};

// Fetch CV data
const fetchCV = async (id: number) => {
    try {
        const response = await axios.get(`${apiUrl}/cv/${id}`);
        if (response.status === 200) {
            cv.value = response.data;
        } else {
            console.error('Erreur lors de la récupération du CV');
        }
    } catch (error) {
        console.error('Erreur lors de la requête API:', error);
    }
};

// On component mount
onMounted(() => {
    const id = route.params.id;
    console.log(id);
    

    const talentId = Number(id);
    console.log(talentId);
    
    if (!isNaN(talentId)) {
        fetchTalent(talentId);
        fetchCV(talentId);
    } else {
        console.error('Invalid talent ID');
    }
});
</script>

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