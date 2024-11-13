<script setup lang="ts">
import { ref } from 'vue';
import { useRoute } from 'vue-router';
import type { Talent } from '~/types';

import Profil from '~/components/talent/Profil.vue';
import CV from '~/components/talent/CV.vue';

const route = useRoute();
const apiUrl = useRuntimeConfig().public.apiUrl as string;
const talentId = computed(() => route.params.id);
const { data: talent, error: talentError } = useFetch<Talent>(`${apiUrl}/talent/${talentId.value}`);

const profilePicture = ref('/default-profile.png');

</script>

<template>
    <div v-if="talent">
        <Profil :talent="talent" :photo="profilePicture" />
        <CV :competences="talent.competences" :experiences="talent.experiences" />
    </div>
    <div v-else>
        <p>Loading profile...</p>
    </div>
</template>