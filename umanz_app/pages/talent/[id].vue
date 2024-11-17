<script setup lang="ts">
import { ref } from 'vue';
import { useRoute } from 'vue-router';
import type { Talent } from '~/types';

import Profil from '~/components/talent/Profil.vue';
import CV from '~/components/talent/CV.vue';

const route = useRoute();
const apiUrl = useRuntimeConfig().public.apiUrl as string;
const talentId = computed(() => route.params.id);
const { data: talent } = useFetch<Talent>(`${apiUrl}/talents/${talentId.value}`);
</script>

<template>
    <div v-if="talent">
        <Profil :talent="talent" />
        <br>
        <CV :competences="talent.competenceTalents" :experiences="talent.experienceTalents" />
    </div>
    <div v-else>
        <p>Loading profile...</p>
    </div>
</template>