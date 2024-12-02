<script setup lang="ts">
const data = [
    {
        label: "Avantages en nature",
        value: ""
    },
    {
        label: "Déductions IRSA",
        value: ""
    },
    {
        label: "Montant imposable",
        value: 4022575.00
    },
];
</script>

<template>
    <div class="container mx-auto flex gap-4 border rounded border-red-300">
        <div class="w-1/2 border border-white">
            <div class="mb-4" v-for="(elmt, i) in data" :key="i">
                <p class="text-gray-500">
                    <strong class="font-semibold text-gray-100">{{ elmt.label }} :</strong>
                    {{ elmt.value }}
                </p>
            </div>
            <div class="mb-4">
                <p class="text-gray-500 italic">
                    <strong class="font-semibold text-gray-100">Mode de paiement :</strong>
                    <span class="color text-blue-500">Virement/Cheque</span>
                </p>
            </div>
        </div>
        <div class="w-1/2"></div>
    </div>
</template>