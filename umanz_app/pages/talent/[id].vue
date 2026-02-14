<script setup lang="ts">
import { useRoute } from 'vue-router';
import type { Talent } from '~/types';
import { useAuth } from '~/features/auth/useAuth';

import PageError from '~/components/layout/PageError.vue';
import PageLoading from '~/components/layout/PageLoading.vue';

import ProfilHeader from '~/features/profile/ProfilHeader.component.vue';
import ProfileBody from '~/features/profile/ProfileBody.component.vue';
import PendingRupture from '~/components/talent/PendingRupture.vue';

const route = useRoute();
const talentId = computed(() => route.params.id);

const apiUrl = useRuntimeConfig().public.apiUrl as string;
const { data: talent, pending, error } = useFetch<Talent>(`${apiUrl}/talents/${talentId.value}`, {
    key: `talent-${talentId.value}`,
    server: false
});

const { getSession } = useAuth();
const session = computed(() => getSession());
const isAdmin = computed(() => session.value?.isAdmin || false);
const idContrat = computed(() => session.value?.idContrat || -1);

// SEO
useHead({
    title: computed(() => talent.value ? `Profil: ${talent.value.nom} ${talent.value.prenom} | UmanZ` : 'Chargement du profil...'),
    meta: [
        { name: 'description', content: 'Consultez les compétences et expériences de nos talents sur UmanZ.' }
    ]
});
</script>

<template>
    <div class="min-h-screen pb-20">
        <PageError v-if="error" 
            title="Talent introuvable" 
            description="Désolé, nous n'avons pas pu charger les informations de ce talent." 
        />

        <PageLoading v-else-if="pending" />

        <!-- Content -->
        <div v-else-if="talent" class="space-y-8">
            <ProfilHeader :talent="talent" />

            <ProfileBody 
                :competences="talent.competenceTalents" 
                :experiences="talent.experienceTalents"
                :langues="talent.talentLangues" 
                :diplomes="talent.diplomes" 
            />

            <!-- Rupture Section -->
            <div v-if="!isAdmin && idContrat > -1" class="max-w-3xl mx-auto px-4">
                <PendingRupture :id-contrat="idContrat" :api-url="apiUrl" />
            </div>
        </div>
    </div>
</template>