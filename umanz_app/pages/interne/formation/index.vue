<script setup lang="ts">
import axios from 'axios';
import type { Annonce, Formation } from '~/types';

const headers = [
    {
        key: 'idFormation',
        label: 'ID',
        sortable: true,
    },
    {
        key: 'dateDebut',
        label: 'Date Debut',
        sortable: true
    },
    {
        key: 'dateFin',
        label: 'Date Fin',
        sortable: true
    }
];

// Variable pour stocker les données de l'API
const lignes = ref<Formation[]>([]);
const q = ref('');

const filteredLignes = computed(() => {
    return q.value ?
        lignes.value.filter((e: Formation) =>
            Object.values(e).some((value) =>
                String(value).toLowerCase().includes(q.value.toLowerCase())
            )
        ) : lignes.value;
});

async function loadFormations() {
    try {
        const apiUrl: string = useRuntimeConfig().public.apiUrl as string;
        const response = await axios.get(`${apiUrl}/formation/disponible`);

        if (response.status === 200 && Array.isArray(response.data)) {
            console.log(toRaw(response.data));

            lignes.value = response.data;
        } else {
            console.error('Erreur lors de la récupération des Formations', response.data);
        }
    } catch (error) {
        console.error('Erreur lors de la requête API:', error);
    }
}

loadFormations();

const expand = ref({
    openedRows: [],
    row: {}
})

const participerFn = async (idFormation: number) => {
    const idTalent = localStorage.getItem('idUser');
    if (idTalent) {
        console.log(`Candidater pour l'Formation ${idFormation} en etant le talent ${idTalent}`);

        const apiUrl = useRuntimeConfig().public.apiUrl;
        try {
            const data = { idTalent, idFormation };
            const response = await axios.post(`${apiUrl}/candiat`, data);

            if (response.status === 200) {
                console.log('Candidature envoyée avec succès', data);
            } else {
                console.error('Erreur lors de l\'envoi de la candidature', response.data);
            }
        } catch (error) {
            console.error('Erreur lors de la requête API:', error);
        }
    } else {
        console.error('Utilisateur non connecté');
    }
}
</script>

<template>
    <div class="container mx-auto">
        <h1 class="text-3xl font-bold mb-6 text-center">Liste des annonces</h1>

        <div class="flex justify-center mb-4">
            <UInput v-model="q" placeholder="Filtrer les annonces..."
                class="w-full max-w-md px-4 py-2  rounded-lg shadow-sm focus:outline-none focus:ring-2 focus:ring-blue-500" />
        </div>

        <UTable :columns="headers" :rows="filteredLignes" v-model:expand="expand"
            class="w-full shadow-md rounded-lg overflow-hidden">
            <template #expand="{ row }">
                <div class="p-4">
                    <div class="mb-4">
                        <h3 class="text-xl font-semibold mb-2">Titre:</h3>
                        <p class="text-gray-700">{{ row.nomFormation }}</p>
                    </div>

                    <button class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded"
                        @click="participerFn(row.idFormation)">
                        Candidater
                    </button>
                </div>
            </template>
        </UTable>
    </div>
</template>
