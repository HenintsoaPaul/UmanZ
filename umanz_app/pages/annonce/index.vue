<script setup lang="ts">
import axios from 'axios';
import type { Annonce } from '~/types';

// Columns a afficher
const headers = [
    {
        key: 'idAnnonce',
        label: 'ID',
        sortable: true,
    }, {
        key: 'poste.description',
        label: 'Desc Poste',
    }, {
        key: 'dateAnnonce',
        label: 'Date Annonce',
        sortable: true
    }, {
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

loadAnnonces();

const canditerFn = () => {
    console.log("Candidater");
}
</script>

<template>
    <h1 class="text-2xl font-bold mb-4">Liste des annonces</h1>

    <div class="flex px-3 py-3.5 border-b border-gray-200 dark:border-gray-700">
        <UInput v-model="q" placeholder="Filtrer les annonces..." />
    </div>

    <UTable :columns="headers" :rows="filteredLignes" v-model:expand="expand">
        <template #expand="{ row }">
            <div class="p-4">
                <pre>{{ row }}</pre>

                <button class="btn-candidater bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded mr-2"
                    @click="canditerFn">
                    Candidater
                </button>
            </div>
        </template>
    </UTable>
</template>