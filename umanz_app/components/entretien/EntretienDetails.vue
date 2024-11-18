<script setup lang="ts">
import { defineProps } from 'vue';
import type { Entretien } from '~/types';

const props = defineProps<{
    entretien: Entretien;
    validerEntretien: (e: Entretien) => Promise<void>,
    refuserEntretien: () => Promise<void>,
}>();
</script>

<template>
    <h1 class="text-3xl font-bold mb-6 text-center">Détails de l'Entretien</h1>
    <div>
        <h2 class="text-2xl font-bold">Entretien</h2>
        <div class="flex gap-12">
            <div class="w 1/2">
                <p><strong>Date de Création:</strong> {{ entretien.dateCreation }}</p>
                <p><strong>Date de Validation:</strong> {{ entretien.dateValidation }}</p>
                <p><strong>Etat:</strong> {{ entretien.etatEntretien.etatEntretien }}</p>
            </div>
            <div class="w 1/2">
                <p><strong>Talent:</strong> {{ entretien.talent?.nom }} {{ entretien.talent?.prenom }}</p>
                <p><strong>Note:</strong> {{ entretien.note }}</p>
                <p><strong>Motif:</strong> {{ entretien.motif }}</p>
            </div>
        </div>
    </div>
    <div class="mt-4">
        <button class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded"
            @click="props.validerEntretien(entretien)">
            Valider
        </button>
        <button class="bg-red-500 hover:bg-red-700 text-white font-bold py-2 px-4 rounded" @click="props.refuserEntretien">
            Refuser
        </button>
    </div>
</template>