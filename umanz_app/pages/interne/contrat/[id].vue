<script setup lang="ts">
import type { Contrat } from '~/types';
import { useRoute } from 'vue-router';

const { expulsionFn, demissionFn } = useContratActions();
const route = useRoute();
const apiUrl = useRuntimeConfig().public.apiUrl as string;
const contratId = computed(() => route.params.id);
const { data: contrat } = useFetch<Contrat>(`${apiUrl}/contrat/${contratId.value}`);
</script>

<template>
    <div class="container mx-auto">
        <div v-if="contrat">
            <h1 class="text-3xl font-bold mb-6 text-center">Détails Contrat</h1>
            <ContratDetails :contrat="contrat" :explusion-fn="expulsionFn" :demission-fn="demissionFn" :api-url="apiUrl" />
        </div>
        <div v-else>
            Loading Details...
        </div>
    </div>
</template>