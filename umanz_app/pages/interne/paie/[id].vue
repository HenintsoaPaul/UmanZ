<script setup lang="ts">
import FichePaie from "~/components/paie/FichePaie.vue";

const route = useRoute();
const month = ref(Number(route.query.month));
const year = ref(Number(route.query.year));
const idTalent = ref(Number(route.params.id));

</script>

<template>
    <FichePaie :id-talent="idTalent" :month="month" :year="year" />
</template>
