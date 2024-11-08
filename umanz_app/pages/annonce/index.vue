<script setup lang="ts">
const cols = [{
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
</script>

<template>
    <h1>Liste des annonces</h1>

    <UTable :columns="cols" :rows="lignes" />
</template>