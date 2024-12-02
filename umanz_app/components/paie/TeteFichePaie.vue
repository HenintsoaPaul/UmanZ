<script setup lang="ts">

import type { PaysLipsHeader } from '~/types';

defineProps<{
    paysLipsHeader: PaysLipsHeader | null
}>();

</script>

<template>
    <div class="container mx-auto flex gap-4">
        <div class="w-1/2">
            <div class="mb-4">
                <p class="text-gray-500">
                    <strong class="font-semibold text-gray-100">Nom et prénom(s) :</strong>
                    {{ paysLipsHeader?.nomPrenom }}
                </p>
            </div>
            <div class="mb-4">
                <p class="text-gray-500">
                    <strong class="font-semibold text-gray-100">Matricule :</strong>
                    {{ paysLipsHeader?.matricule }}
                </p>
            </div>
            <div class="mb-4">
                <p class="text-gray-500">
                    <strong class="font-semibold text-gray-100">Fonction :</strong>
                    {{ paysLipsHeader?.fonction }}
                </p>
            </div>
            <div class="mb-4">
                <p class="text-gray-500">
                    <strong class="font-semibold text-gray-100">Numéro CNaPS :</strong>
                    {{ paysLipsHeader?.idCnaps }}
                </p>
            </div>
            <div class="mb-4">
                <p class="text-gray-500">
                    <strong class="font-semibold text-gray-100">Date d'embauche :</strong>
                    {{ paysLipsHeader?.dateEmbauche }}
                </p>
            </div>
            <div class="mb-4">
                <p class="text-gray-500">
                    <strong class="font-semibold text-gray-100">Ancienneté :</strong>
                    {{ paysLipsHeader?.anciennete }}
                </p>
            </div>
        </div>
        <div class="w-1/2">
            <div class="mb-4">
                <p class="text-gray-500">
                    <strong class="font-semibold text-gray-100">Classification :</strong>
                    {{ paysLipsHeader?.classification }}
                </p>
            </div>
            <div class="mb-4">
                <p class="text-gray-500">
                    <strong class="font-semibold text-gray-100">Salaire de base :</strong>
                    {{ paysLipsHeader?.salaire }}
                </p>
            </div>
            <div class="mb-4">
                <p class="text-gray-500">
                    <strong class="font-semibold text-gray-100">Taux journalier :</strong>
                    {{ paysLipsHeader?.tauxJournalier }}
                </p>
            </div>
            <div class="mb-4">
                <p class="text-gray-500">
                    <strong class="font-semibold text-gray-100">Taux horaire :</strong>
                    {{ paysLipsHeader?.tauxHoraire }}
                </p>
            </div>
            <div class="mb-4">
                <p class="text-gray-500">
                    <strong class="font-semibold text-gray-100">Indice :</strong>
                    {{ paysLipsHeader?.indice }}
                </p>
            </div>
        </div>
    </div>
</template>