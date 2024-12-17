<script setup lang="ts">
import { useRoute } from 'vue-router';
import type { Talent } from '~/types';

import Profil from '~/components/talent/Profil.vue';
import CV from '~/components/talent/CV.vue';
import PendingRupture from '~/components/talent/PendingRupture.vue';

const route = useRoute();
const apiUrl = useRuntimeConfig().public.apiUrl as string;
const talentId = computed(() => route.params.id);
const { data: talent } = useFetch<Talent>(`${apiUrl}/talents/${talentId.value}`);

const idContrat = computed(() => {
    if (process.client) {
        const dd = localStorage.getItem("umanz-idContrat");
        return dd ? Number(dd) : -1;
    }
    return -1;
});

const isAdmin = computed(() => {
    if (process.client) {
        const sstr = localStorage.getItem("umanz-isAdmin");
        return sstr ? Boolean(sstr) : false;
    }
    return false;
});
</script>

<template>
    <div v-if="talent">
        <Profil :talent="talent" />
        <br>
        <CV :competences="talent.competenceTalents" :experiences="talent.experienceTalents"
            :langues="talent.talentLangues" :diplomes="talent.diplomes" />
        <br>
        <template v-if="!isAdmin">
            <template v-if="idContrat && idContrat > -1">
                <PendingRupture :id-contrat="idContrat" :api-url="apiUrl" />
            </template>
        </template>
    </div>
    <div v-else>
        <p>Loading profile...</p>
    </div>
</template>