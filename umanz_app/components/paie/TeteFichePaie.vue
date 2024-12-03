<script setup lang="ts">
const dataLeft = [
    {
        label: "Nom et Prénoms",
        value: "RAZAFIARISON Laza"
    },
    {
        label: "Matricule",
        value: "627/TNR"
    },
    {
        label: "Fonction",
        value: "MPITOLONA"
    },
    {
        label: "N° CNaPS",
        value: "~"
    },
    {
        label: "Date d'embauche",
        value: "3/25/2011"
    },
    {
        label: "Ancienneté",
        value: "12 an(s) 5 mois et 10 jour(s)"
    },
];
const dataRight = [
    {
        label: "Classification",
        value: "HC"
    },
    {
        label: "Salaire de base",
        value: 4083409.09
    },
    {
        label: "Taux journaliers",
        value: 136114.00
    },
    {
        label: "Taux horaires",
        value: 23559.00
    },
    {
        label: "Indice",
        value: 17660.00
    },
];
</script>

<template>
    <div class="container mx-auto flex gap-4">
        <div class="w-1/2">
            <div class="mb-4" v-for="(elmt, i) in dataLeft" :key="i">
                <p class="text-gray-500">
                    <strong class="font-semibold text-gray-100">{{ elmt.label }} :</strong>
                    {{ elmt.value }}
                </p>
            </div>
        </div>
        <div class="w-1/2">
            <div class="mb-4" v-for="(elmt, i) in dataRight" :key="i">
                <p class="text-gray-500">
                    <strong class="font-semibold text-gray-100">{{ elmt.label }} :</strong>
                    {{ elmt.value }}
                </p>
            </div>
        </div>
    </div>
</template>