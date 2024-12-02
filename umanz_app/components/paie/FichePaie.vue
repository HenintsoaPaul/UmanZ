<script setup lang="ts">
import PiedFichePaie from './PiedFichePaie.vue';
import CorpsFichePaie from './CorpsFichePaie.vue';
import TeteFichePaie from './TeteFichePaie.vue';

import type {PaysLipsHeader, PaysLipsDetails, PaysLipsBody} from '~/types';

const idUser = 1;
const year = 2024;
const month = 11;

const apiUrl = useRuntimeConfig().public.apiUrl;

const { data: paysLipsHeader } = useFetch<PaysLipsHeader>(`${apiUrl}/talents/${idUser}/fiche-paie-tete`, {
    query: { annee: year, mois: month },
});

const { data: paysLipsBodyDetails } = useFetch<PaysLipsBody>(`${apiUrl}/talents/${idUser}/fiche-paie-brute`, {
    query: { annee: year, mois: month },
});

</script>

<template>
    <TeteFichePaie :pays-lips-header="paysLipsHeader" />

    <CorpsFichePaie :pays-lips-brut-details="paysLipsBodyDetails" />

    <PiedFichePaie />
</template>