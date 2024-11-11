<script setup lang="ts">
import axios from 'axios';
import type { Annonce } from '~/types';

const headers = [
    {
        key: 'idAnnonce',
        label: 'ID',
    },
    {
        key: 'poste.nomPoste',
        label: 'Poste',
    },
    {
        key: 'dateAnnonce',
        label: 'Date Annonce',
        sortable: true
    },
    {
        key: 'dateExpiration',
        label: 'Date Expiration',
        sortable: true
    }
];

// Variable pour stocker les données de l'API
const lignes = ref<Annonce[]>([]);
const q = ref('');

const filteredLignes = computed(() => {
    return q.value ?
        lignes.value.filter((e: Annonce) =>
            Object.values(e).some((value) =>
                String(value).toLowerCase().includes(q.value.toLowerCase())
            )
        ) : lignes.value;
});

async function loadAnnonces() {
    try {
        const apiUrl: string = useRuntimeConfig().public.apiUrl as string;
        const response = await axios.get(`${apiUrl}/annonce/disponible`);

        if (response.status === 200 && Array.isArray(response.data)) {
            console.log(toRaw(response.data));

            lignes.value = response.data;
        } else {
            console.error('Erreur lors de la récupération des annonces', response.data);
        }
    } catch (error) {
        console.error('Erreur lors de la requête API:', error);
    }
}

onMounted(() => {
    loadAnnonces();
})

const expand = ref({
    openedRows: [],
    row: {}
})

const canditerFn = async (idAnnonce: number) => {
    const idTalent = localStorage.getItem('idUser');
    if (idTalent) {
        const apiUrl = useRuntimeConfig().public.apiUrl;
        try {
            const candidature = {
                idTalent: Number(idTalent),
                idAnnonce: Number(idAnnonce),
            }
            const response = await axios.post(`${apiUrl}/entretien/candiat`, candidature);

            if (response.status === 200) {
                console.log('Candidature envoyée avec succès', candidature);
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
                        <h3 class="text-xl font-semibold mb-2">Description:</h3>
                        <p class="text-gray-700">{{ row.poste.description }}</p>
                    </div>

                    <button class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded"
                        @click="canditerFn(row.idAnnonce)">
                        Candidater
                    </button>

                    <button class="bg-green-500 hover:bg-green-700 text-white font-bold py-2 px-4 rounded ml-2"
                        @click="$router.push(`/recrutement/annonce/${row.idAnnonce}`)">
                        Voir Détails
                    </button>
                </div>
            </template>
        </UTable>
    </div>
</template>
