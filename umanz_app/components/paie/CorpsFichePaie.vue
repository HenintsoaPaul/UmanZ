<script setup lang="ts">

import type {PaysLipsDetails} from "~/types";

defineProps<{
    paysLipsBrutDetails: PaysLipsDetails[];
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
    isMiavaka: boolean;
}

const dataIRSA: rowIrsa[] = [
    { designation: 'Retenue CNaPS 1%', taux: '', montant: 20000.00, isMiavaka: false },
    { designation: 'Retenue sanitaire', taux: '', montant: 40834.09, isMiavaka: false },
    { designation: 'Tranche IRSA INF 350 0000', taux: '', montant: '', isMiavaka: false },
    { designation: 'Tranche IRSA I DE 350 0001 à 400 000', taux: '5%', montant: 2500.00, isMiavaka: false },
    { designation: 'Tranche IRSA I DE 400 0001 à 500 000', taux: '10%', montant: 10000.00, isMiavaka: false },
    { designation: 'Tranche IRSA I DE 500 001 à 600 000', taux: '15%', montant: 15000.00, isMiavaka: false },
    { designation: 'Tranche IRSA SUP 600 0000', taux: '20%', montant: 684515.00, isMiavaka: false },

    { designation: 'TOTAL IRSA', taux: '', montant: 712015.00 , isMiavaka: true},
    { designation: 'Total des retenues', taux: '', montant: 772849.09 , isMiavaka: true},
    { designation: 'Autres indemnités', taux: '', montant: 0 , isMiavaka: true},
    { designation: 'Net à payer', taux: '', montant: 3310560.00 , isMiavaka: true}
];

const highlightedClasses = (item: rowIrsa) => ({
  'bg-blue-500': item.isMiavaka,
  'text-white': item.isMiavaka,
})

</script>

<template>
    <div class="px-5">
        <UTable :columns="headers" :rows="paysLipsBrutDetails" />

        <br>

        <UTable :columns="headersIRSA" :rows="dataIRSA">
            <template #default="{ item }">
                <tr :class="highlightedClasses(item)">
                    <p>Damn u</p>
                </tr>
            </template>
        </UTable>

    </div>
</template>