<script setup lang="ts">
import { ref } from 'vue';
import type { EtatPaie } from '~/types/interne/paie';

const date = ref<string | null>(null);
const apiUrl = useRuntimeConfig().public.apiUrl as string;
const etatPaies = ref<EtatPaie[]>([]);

const onSubmit = async () => {
    try {
        if (date.value) {
            const [year, month] = date.value.split('-');
            console.log(`Year: ${year}, Month: ${month}`);

            etatPaies.value = await $fetch<EtatPaie[]>(`${apiUrl}/paies`, {
                method: 'GET',
                params: { month, year },
            });

            console.log('EtatPaies data get successfully', etatPaies.value);
        }
    } catch (error) {
        console.error('Failed to get EtatPaies data', error);
        throw error;
    }
}

// SALAIRE DE BASE	RETENUES SUR ABSCENCES	SALAIRE DE BASE DU MOIS	INDEMNITE	RAPPEL	AUTRES	HR SUP/MAJ	"Salaire 
// brut"	"Revenu
// Imposable"	"Impôt 
// dû"	 ENFANT A CHARGE 	Montant	 IGR NET 	 AUTRES RETENUES 	 TOTAL RETENUES 	 SALAIRE NET 	 AVANCE 	 NET A PAYER 	 AUTRES INDEMNITE 	NET DU MOIS

const headers = [
    { key: 'matricule', label: 'No Matricule', sortable: true },
    { key: 'idCnaps', label: 'No Cnaps', sortable: true },
    { key: 'nomPrenom', label: 'NOM et Prénoms' },
    { key: 'dateEmbauche', label: "Date d'embauche", sortable: true },
    { key: 'anciennete', label: 'Anciennete', sortable: true },
    { key: 'fonction', label: 'Fonction' },
    { key: 'salaire', label: 'Salaire' },
    { key: 'tauxHoraire', label: 'Taux horaire' },
    { key: 'tauxJournalier', label: 'Taux journalier' },
];

const expand = ref({
    openedRows: [],
    row: {}
});

const constructDetailsUrl = (row : EtatPaie) => {
    if (date.value) {
        const [year, month] = date.value.split('-');
        return `/interne/paie/${row.talent.idTalent}?month=${month}&year=${year}`;
    }
    return '';
}

</script>

<template>
    <div class="container px-auto py-auto border border-white rounded-md">
        <form @submit.prevent="onSubmit">
            <label for="date" class="text-lg font-medium">Date (Année-Mois):</label>

            <input type="month" id="date" v-model="date" class="border border-gray-300 rounded-md p-2" />

            <button type="submit" class="bg-blue-500 text-white px-4 py-2 rounded-md hover:bg-blue-600">Valider</button>
        </form>

        <template v-if="etatPaies">
            <UTable :headers="headers" :rows="etatPaies" v-model:expand="expand">
                <template #expand="{ row }">
                    <div class="p-4">
                        <button class="bg-green-500 text-white px-4 py-2 rounded-md hover:bg-green-600"
                                @click="$router.push(constructDetailsUrl(row))">
                            Voir Details
                        </button>
                    </div>
                </template>
            </UTable>
        </template>
    </div>
</template>
