<script lang="ts" setup>
import type { Conge } from '~/types/interne/conge';

const props = defineProps<{
    conges: Conge[];
}>();

const colors = ['blue', 'green', 'red', 'yellow', 'purple'];
const attrs = computed(() => {
    return props.conges.map((conge, index) => ({
        key: `conge-${conge.idConge}`,
        highlight: {
            color: colors[index % colors.length],
            fillMode: 'solid'
        },
        dates: { start: new Date(conge.dateDebut), end: new Date(conge.dateFin) }
    }))
});

const date = ref(new Date());
</script>

<template>
    <div class="container mx-auto">
        <h2>Calendar</h2>
        <VCalendar v-model="date" :attributes="attrs" />
    </div>
</template>