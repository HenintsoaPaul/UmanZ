<script setup lang="ts">
import axios from 'axios';

// Columns a afficher
const headers = [{
    key: 'poste',
    label: 'Poste',
    sortable: true
}, {
    key: 'dateAnnonce',
    label: 'Date Annonce',
    sortable: true
}, {
    key: 'dateExpiration',
    label: 'Date Expiration',
    sortable: true
}];

const lignes = [
    {
        "dateAnnonce": "2023-10-01",
        "dateExpiration": "2023-12-01",
        "poste": "Développeur Web",
        "descPoste": "Développement de sites web et d'applications web.",
        "competences": ["JavaScript", "Vue.js", "HTML", "CSS"],
        "experiences": [
            {
                "label": "Développeur Frontend",
                "duree": 2
            },
            {
                "label": "Développeur Backend",
                "duree": 3
            }
        ]
    },
    {
        "dateAnnonce": "2023-09-15",
        "dateExpiration": "2023-11-15",
        "poste": "Designer UX/UI",
        "descPoste": "Conception d'interfaces utilisateur et d'expériences utilisateur.",
        "competences": ["Adobe XD", "Figma", "Sketch"],
        "experiences": [
            {
                "label": "Designer UX",
                "duree": 4
            },
            {
                "label": "Designer UI",
                "duree": 5
            }
        ]
    }
];

// Variable pour stocker les données de l'API
const annonces = ref([])

async function loadAnnonces() {
    try {
        // Récupération des données de l'API
        const apiUrl: string = useRuntimeConfig().public.apiUrl as string;
        const response = await axios.get(apiUrl)

        if (response.status === 200) {
            annonces.value = response.data;
        } else {
            console.error('Erreur lors de la récupération des annonces')
        }
    } catch (error) {
        console.error('Erreur lors de la requête API:', error)
    }
}

// Appel de la fonction pour charger les données au chargement du composant
loadAnnonces();


// Filtre par recherche
const q = ref('')

const filteredLignes = computed(() => {
    if (!q.value) {
        return lignes
    }

    return lignes.filter((person) => {
        return Object.values(person).some((value) => {
            return String(value).toLowerCase().includes(q.value.toLowerCase())
        })
    })
});

// ---
const expand = ref({
    openedRows: [],
    row: {}
})

const canditerFn = () => {
    console.log("Envoi candidature");
    // REquete base
}

const supprimerFn = () => {
    console.log("Supprimer mon annonce");
    // Verifier tompony
    // Requete base
}
</script>

<template>
    <h1>Liste des annonces</h1>

    <div class="flex px-3 py-3.5 border-b border-gray-200 dark:border-gray-700">
        <UInput v-model="q" placeholder="Filtrer les annonces..." />
    </div>

    <UTable :columns="headers" :rows="filteredLignes" v-model:expand="expand">
        <template #expand="{ row }">
            <div class="p-4">
                <pre>{{ row }}</pre>

                <button class="btn-candidater" @click="canditerFn">Candidater</button>
                <button class="btn-supprimer" @click="supprimerFn">Supprimer</button>
            </div>
        </template>
    </UTable>
</template>