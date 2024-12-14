<script setup lang="ts">
import { defineProps } from 'vue';
import type { Entretien } from '~/types';

const props = defineProps<{
    entretien: Entretien;
}>();

const emit = defineEmits(["valider", "refuser", "redirect", "prochain", "contrat"]);

function validerFn() {
    emit("valider", props.entretien);
}

function prochainFn() {
    emit("prochain", props.entretien);
}

function contratFn() {
    emit("contrat", props.entretien);
}

function refuserFn() {
    emit("refuser", props.entretien.idEntretien);
}

function redirectFn() {
    emit("redirect", props.entretien.talent.idTalent);
}

const isChangeable = computed(() => props.entretien.dateValidation === null);

const isValidFirst = computed(() => props.entretien.etatEntretien.idEtatEntretien === 4);

const isValidSecond = computed(() => props.entretien.etatEntretien.idEtatEntretien === 6);
</script>

<template>
    <div class="p-4">
        <div class="flex">
            <div class="w-1/2">
                <div class="mb-4">
                    <h3 class="text-xl font-semibold mb-2">Poste:</h3>
                    <p class="text-gray-700">{{ entretien.annonce.poste.nomPoste }}</p>
                </div>
                <div class="mb-4">
                    <h3 class="text-xl font-semibold mb-2">Description:</h3>
                    <p class="text-gray-700">{{ entretien.annonce.poste.description }}</p>
                </div>
                <div class="mb-4">
                    <h3 class="text-xl font-semibold mb-2">Intervalle de postulation:</h3>
                    <p class="text-gray-700">{{ entretien.annonce.dateAnnonce }} - {{
                        entretien.annonce.dateExpiration }}
                    </p>
                </div>
            </div>

            <div class="w-1/2">
                <div class="mb-4">
                    <p class="text-gray-500">
                        <strong class="text-white">Nom:</strong> {{ entretien.talent.nom }} {{ entretien.talent.prenom
                        }}
                    </p>
                </div>
                <div class="mb-4">
                    <p class="text-gray-500">
                        <strong class="text-white">Email:</strong> {{ entretien.talent.mail }}
                    </p>
                </div>

                <template v-if="isValidFirst">
                    <button class="bg-green-500 hover:bg-green-700 text-white font-bold py-2 px-4 rounded ml-2"
                        @click="prochainFn" v-if="isChangeable">
                        Passer au prochain Entretien
                    </button>
                </template>
                <template v-else-if="isValidSecond">
                    <button class="bg-green-500 hover:bg-green-700 text-white font-bold py-2 px-4 rounded ml-2"
                        @click="contratFn" v-if="isChangeable">
                        Passer au contrat
                    </button>
                </template>
                <template v-else>
                    <UInput v-model="entretien.note" type="number" min="0" max="20" placeholder="Ajouter la note..."
                        class="w-full max-w-md py-2 rounded-lg" />
                    <UInput v-model="entretien.motif" type="text" placeholder="Ajouter motif..."
                        class="w-full max-w-md py-2 rounded-lg" />

                    <button class="bg-green-500 hover:bg-green-700 text-white font-bold py-2 px-4 rounded"
                        @click="validerFn" :disabled="!entretien.motif || entretien.motif.length <= 0 || entretien.note <= 0" v-if="isChangeable">
                        Valider
                    </button>
                </template>

                <button class="bg-red-500 hover:bg-red-700 text-white font-bold py-2 px-4 rounded ml-2"
                    @click="refuserFn" v-if="isChangeable">
                    Refuser
                </button>

                <button class="bg-indigo-500 hover:bg-indigo-700 text-white font-bold py-2 px-4 rounded ml-2"
                    @click="redirectFn">
                    Consulter CV
                </button>
            </div>
        </div>

        <br>

    </div>
</template>