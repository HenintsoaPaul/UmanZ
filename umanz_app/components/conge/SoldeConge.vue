<script setup lang="ts">
import { defineProps } from 'vue';
import type { SoldeCongeByTypeDTO } from '~/types/interne/conge';

defineProps<{
    soldesConges: SoldeCongeByTypeDTO[];
}>();
</script>

<template>
    <div class="container mx-auto">
        <div class="mb-4" v-for="(elmt, i) in soldesConges" :key="i">
            <h3 class="text font-semibold mb-2">{{ elmt.typeConge.nomTypeConge }}:</h3>
            <p class="text-gray-700">{{ elmt.soldeConge }} jour(s)</p>
        </div>
    </div>
</template>