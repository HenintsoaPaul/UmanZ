<script setup lang="ts">

import type { PaysLipsBody } from "~/types";

defineProps<{
    paysLipsBrutDetails?: PaysLipsBody;
}>();

const headers = [
    { key: 'designation', label: 'Designation' },
    { key: 'nombre', label: 'Nombre' },
    { key: 'taux', label: 'Taux' },
    { key: 'montant', label: 'Montant' },
];

// ==== IRSA + sanitaire ===
const headersIRSA = [
    { key: 'designation', label: 'Designation' },
    { key: 'taux', label: 'Taux' },
    { key: 'montant', label: 'Montant' },
];

interface rowIrsa {
    designation: string;
    taux: any;
    montant: number|string;
    surligne: boolean;
}

const highlightedClasses = (item: rowIrsa) => ({
  'bg-blue-500': item.surligne,
  'text-white': item.surligne,
})

</script>

<template>
    <div class="px-5">
        <UTable :columns="headers" :rows="paysLipsBrutDetails?.brute" />

        <br>

        <UTable :columns="headersIRSA" :rows="paysLipsBrutDetails?.retenue">
            <template #default="{ item }">
                <tr :class="highlightedClasses(item)"></tr>
            </template>
        </UTable>

    </div>
</template>