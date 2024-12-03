<script setup lang="ts">
import PiedFichePaie from './PiedFichePaie.vue';
import CorpsFichePaie from './CorpsFichePaie.vue';
import TeteFichePaie from './TeteFichePaie.vue';

import type {PaysLipsHeader, PaysLipsBody} from '~/types';

const {
    idTalent,
    year,
    month
} = defineProps<{
    idTalent: number;
    year: number;
    month: number;
}>();

const apiUrl = useRuntimeConfig().public.apiUrl;

const { data: paysLipsHeader } = useFetch<PaysLipsHeader>(`${apiUrl}/talents/${idTalent}/fiche-paie-tete`, {
    query: { annee: year, mois: month },
});

const { data: paysLipsBodyDetails } = useFetch<PaysLipsBody>(`${apiUrl}/talents/${idTalent}/fiche-paie-brute`, {
    query: { annee: year, mois: month },
});

</script>

<template>
    <div class="fiche-paie">

        <TeteFichePaie :pays-lips-header="paysLipsHeader" />

        <CorpsFichePaie :pays-lips-brut-details="paysLipsBodyDetails" />

        <PiedFichePaie />

    </div>
</template>