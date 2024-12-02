<script setup lang="ts">

import type {PaysLipsDetails} from "~/types";

const props = defineProps<{
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
    surligne: boolean;
}

const apiUrl = useRuntimeConfig().public.apiUrl;

const { data: dataIRSA } = useFetch<PaysLipsDetails[]>(`${apiUrl}/talents/fiche-paie-retenue`, {
    query: {
        salaireBrute: props.paysLipsBrutDetails.find<PaysLipsDetails>((el) => {
            return el.designation === "Salaire brute";
        })?.montant
    },
});

console.log(props.paysLipsBrutDetails.find<PaysLipsDetails>((el) => {
    return el.designation === "Salaire brute";
}, "Here");

const highlightedClasses = (item: rowIrsa) => ({
  'bg-blue-500': item.surligne,
  'text-white': item.surligne,
})

</script>

<template>
    <div class="px-5">
        <UTable :columns="headers" :rows="paysLipsBrutDetails" />

        <br>

        <UTable :columns="headersIRSA" :rows="dataIRSA ?? []">
            <template #default="{ item }">
                <tr :class="highlightedClasses(item)"></tr>
            </template>
        </UTable>

    </div>
</template>