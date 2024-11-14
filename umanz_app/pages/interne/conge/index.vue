<script setup lang="ts">
import axios from 'axios';
import type { Conge } from '~/types';
import { ref, computed, toRaw } from 'vue';

const headers = [
    { key: 'idConge', label: 'ID', sortable: true },
    { key: 'idContrat', label: 'Contrat', sortable: true },
    { key: 'dateDebut', label: 'Date Debut', sortable: true },
    { key: 'nbJour', label: 'Nombre Jour', sortable: true },
    { key: 'dateFin', label: 'Date Validation' }
];

// Variable pour stocker les données de l'API
const lignes = ref<Conge[]>([]);
const q = ref('');

const filteredLignes = computed(() => {
    return q.value
        ? lignes.value.filter((e: Conge) =>
            Object.values(e).some((value) =>
                String(value).toLowerCase().includes(q.value.toLowerCase())
            )
        )
        : lignes.value;
});

async function loadConge() {
    try {
        const apiUrl: string = useRuntimeConfig().public.apiUrl as string;
        const response = await axios.get(`${apiUrl}/conge`);

        if (response.status === 200 && Array.isArray(response.data)) {
            console.log("Data from API:", toRaw(response.data));

            const congeData = response.data.map((item) => {
                const idTalent = item.talent?.idTalent ?? 0;

                return {
                    idConge: item.idConge ?? 0,
                    dateDebut: item.dateDebut ?? '',
                    nbJour: item.nbJour ?? 0,
                    motif: item.motif ?? 'Non spécifié',
                    dateFin: item.dateFin ?? '',
                    contrat: item.contrat ?? { idContrat: 0, contrat: 'Non spécifié' },
                    talent: {
                        idTalent: idTalent,
                        nomTalent: item.talent?.nom ?? 'Non spécifié',
                        prenomTalent: item.talent?.prenom ?? 'Non spécifié',
                        mailTalent: item.talent?.mail ?? 'Non spécifié'
                    }
                };
            });
            lignes.value = congeData;
        } else {
            console.error('Erreur lors de la récupération des données', response.data);
        }
    } catch (error) {
        console.error('Erreur lors de la requête API:', error);
    }
}

loadConge();

const expand = ref({
    openedRows: [],
    row: {}
});

const validerFn = async (idConge: number, idContrat: number) => {
    const apiUrl = useRuntimeConfig().public.apiUrl;
    try {
        const params = {
            idConge: idConge,
            idContrat: idContrat
        };

        console.log(params);

        const response = await axios.post(`${apiUrl}/entretien/candidat`, params);

        if (response.status === 200) {
            console.log('Validation envoyée avec succès', params);
        } else {
            console.error('Erreur lors de la validation', response.data);
        }
    } catch (error) {
        console.error('Erreur lors de la requête API:', error);
    }
};
</script>

<template>
    <div class="container mx-auto">
        <h1 class="text-3xl font-bold mb-4 text-center">Liste des Congés</h1>

        <div class="flex justify-center mb-4">
            <UInput v-model="q" placeholder="Filtrer les congés..." class="w-full max-w-md px-4 py-2 rounded-lg" />
        </div>

        <UTable :columns="headers" :rows="filteredLignes" v-model:expand="expand">
            <template #default="{ row }">
                <tr>
                    <td>{{ row.idConge }}</td>
                    <td>{{ row.contrat.contrat }}</td>
                    <td>{{ row.dateDebut }}</td>
                    <td>{{ row.nbJour }}</td>
                    <td>{{ row.dateFin }}</td>
                </tr>
            </template>
            <template #expand="{ row }">
                <div class="p-4">
                    <p>Nom: {{ row.talent.nomTalent }}</p>
                    <p>Prénom: {{ row.talent.prenomTalent }}</p>
                    <p>Email: {{ row.talent.mailTalent }}</p>
                    <button class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded"
                        @click="validerFn(row.idConge, row.contrat.idContrat)">
                        Valider Congé
                    </button>
                </div>
            </template>
        </UTable>
    </div>
</template>
