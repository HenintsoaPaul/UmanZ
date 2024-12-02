<script setup lang="ts">
import { defineProps } from 'vue';
import type { Contrat } from '~/types/interne/contrat';

const props = defineProps<{
    apiUrl: string;
    currency: string;
    contrat: Contrat;
    promotionFn: (idContrat: number, apiUrl: string) => Promise<void>;
    explusionFn: (idContrat: number, apiUrl: string) => Promise<void>;
    demissionFn: (idContrat: number, apiUrl: string) => Promise<void>;
}>();
</script>

<template>
    <div class="p-4">
        <div class="w-full">
            <div class="mb-4">
                <h3 class="text-xl font-semibold mb-2">Contrat :</h3>
                <p class="text-gray-700">
                    <strong class="text-gray-500 font-bold">TypeContrat:</strong> {{ contrat.contrat }}
                </p>
                <p class="text-gray-700">
                    <strong class="text-gray-500 font-bold">Talent:</strong> {{ contrat.talent.nom }} {{ contrat.talent.prenom }}
                </p>
            </div>
            <div class="mb-4">
                <h3 class="text-xl font-semibold mb-2">Poste :</h3>
                <p class="text-gray-700">
                    <strong class="text-gray-500 font-bold">Titre:</strong> {{ contrat.poste.nomPoste }}
                </p>
                <p class="text-gray-700">
                    <strong class="text-gray-500 font-bold">Description:</strong> {{ contrat.poste.description }}
                </p>
                <p class="text-gray-700">
                    <strong class="text-gray-500 font-bold">Salaire par heure:</strong> {{ contrat.salaireHoraire }} {{ currency }}
                </p>
                <p class="text-gray-700">
                    <strong class="text-gray-500 font-bold">Nb Heure par jour:</strong> {{ contrat.nbHeureJour }}
                </p>
                <p class="text-gray-700">
                    <strong class="text-gray-500 font-bold">Nb jour par semaine:</strong> {{ contrat.nbJourSemaine }}
                </p>
            </div>
        </div>

        <!-- <button class="bg-green-500 hover:bg-green-700 text-white font-bold py-2 px-4 rounded mr-4"
            @click="props.promotionFn(contrat.idContrat, props.apiUrl)">
            Promotion
        </button> -->
        <button class="bg-red-500 hover:bg-red-700 text-white font-bold py-2 px-4 rounded mr-4"
            @click="props.explusionFn(contrat.idContrat, props.apiUrl)">
            Licenciement
        </button>
        <button class="bg-yellow-500 hover:bg-yellow-700 text-white font-bold py-2 px-4 rounded"
            @click="props.demissionFn(contrat.idContrat, props.apiUrl)">
            Demission
        </button>
    </div>
</template>