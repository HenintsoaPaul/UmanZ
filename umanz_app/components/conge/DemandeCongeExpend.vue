<script setup lang="ts">
import { defineProps } from 'vue';
import type { Conge } from '~/types/interne/conge';

const props = defineProps<{
    conge: Conge;
    solde: number;
    apiUrl: string;
}>();

const emit = defineEmits(["valider", "refuser"]);
const validerFn = () => { emit("valider", [props.conge, props.solde]); }
const refuserFn = () => { emit("refuser", props.conge); }

const isChangeable = computed(() => props.conge.dateValidation == null || props.conge.dateRefus == null);
</script>

<template>
    <div class="flex gap-4">
        <div class="w-1/2">
            <div class="mb-4">
                <h3 class="text font-semibold mb-2">Poste:</h3>
                <p class="text-gray-700">{{ conge.contrat.poste.nomPoste }}</p>
            </div>
            <div class="mb-4">
                <h3 class="text font-semibold mb-2">Employe:</h3>
                <p class="text-gray-700">{{ conge.contrat.talent.nom + " " + conge.contrat.talent.prenom }}</p>
            </div>
        </div>

        <div class="w-1/2">
            <template v-if="isChangeable">
                <UInput v-model="conge.motifRefus" type="text" placeholder="Ajouter motif de refus..."
                    class="w-full max-w-md py-2 rounded-lg" />

                <button class="bg-green-500 hover:bg-green-700 text-white font-bold py-2 px-4 rounded"
                    @click="validerFn" :disabled="solde < conge.nbJour">
                    Valider
                </button>

                <button class="bg-red-500 hover:bg-red-700 text-white font-bold py-2 px-4 rounded ml-2"
                    @click="refuserFn" :disabled="conge.motifRefus == null || conge.motifRefus.length === 0">
                    Refuser
                </button>
            </template>

            <button class="bg-indigo-500 hover:bg-indigo-700 text-white font-bold py-2 px-4 rounded ml-2"
                @click="$router.push(`/talent/${conge.contrat.talent.idTalent}`)">
                Consulter CV
            </button>
        </div>
    </div>
</template>