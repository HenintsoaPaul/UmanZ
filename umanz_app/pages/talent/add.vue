<script setup lang="ts">
import { z } from 'zod'
import { reactive, ref, toRaw } from 'vue';
import axios from 'axios';

import FormTalent from '~/components/talent/FormTalent.vue';
import type { Competence, CompetenceAnnonce, ExperiencePoste, Poste } from '~/types';

const schema = z.object({
    nom: z.string().min(1, 'Le nom est obligatoire'),
    prenom: z.string().min(1, 'Le prénom est obligatoire'),
    mail: z.string().email('Email incorrect'),
    password: z.string().min(6, 'Le mot de passe doit contenir au moins 6 caractères'),
    isAdmin: z.boolean()
});

interface Form {
    nom: string;
    prenom: string;
    mail: string;
    password: string;
    isAdmin: Boolean;
    competences: CompetenceAnnonce[];
    experiences: ExperiencePoste[];
}
const form = reactive<Form>({
    nom: '',
    prenom: '',
    mail: '',
    password: '',
    isAdmin: false,
    competences: [],
    experiences: [],
});
const formKdj = reactive<Form>({
    nom: '',
    prenom: '',
    mail: '',
    password: '',
    isAdmin: false,
    competences: [],
    experiences: [],
});

const apiUrl = useRuntimeConfig().public.apiUrl as string;
const { data: postes, refresh: refreshPostes } = useFetch<Poste[]>(`${apiUrl}/postes`);
const { data: competences, refresh: refreshCompetences } = useFetch<Competence[]>(`${apiUrl}/competences`);

const updateForm = () => {
    if (postes.value && competences.value) {
        form.experiences = postes.value.map(pt => ({
            ans: 0,
            poste: toRaw(pt)
        }));
        form.competences = competences.value.map(cp => ({
            point: 0,
            competence: toRaw(cp)
        }));
    }
};

onMounted(async () => {
    await refreshPostes();
    await refreshCompetences();
    updateForm();
});

const errorMessage = ref('');
const successMessage = ref('');
const loading = ref(false);

async function onSubmit() {
    const isValid = schema.safeParse(form).success;

    if (!isValid) {
        errorMessage.value = 'Une erreur s\'est produite lors de la soumission du formulaire.';
        return;
    }

    loading.value = true;
    try {
        formKdj.nom = form.nom;
        formKdj.prenom = form.prenom;
        formKdj.mail = form.mail;
        formKdj.password = form.password;
        formKdj.isAdmin = form.isAdmin;
        formKdj.competences = form.competences.filter(cp => cp.point > 0).map(cp => toRaw(cp));
        formKdj.experiences = form.experiences.filter(exp => exp.ans > 0).map(exp => toRaw(exp));

        console.log(toRaw(formKdj));
        

        const apiUrl: string = useRuntimeConfig().public.apiUrl as string;
        const response = await axios.post(`${apiUrl}/talents`, toRaw(formKdj));

        console.log('Form submitted successfully:', response.data);
        errorMessage.value = '';
        successMessage.value = 'Le formulaire a été soumis avec succès.';
    } catch (error) {
        console.error('Error submitting form:', error);
        errorMessage.value = 'Une erreur s\'est produite lors de la soumission du formulaire.';
        successMessage.value = '';
    } finally {
        loading.value = false;
    }
}
</script>

<template>
    <div class="talent-form">
        <h1 class="text-2xl font-bold mb-6">Profil Talent</h1>

        <FormTalent :form="form" :schema="schema" :loading="loading" :error-message="errorMessage"
            :success-message="errorMessage" @submit="onSubmit" />
    </div>
</template>

<style scoped>
.talent-form {
    max-width: 600px;
    margin: 0 auto;
    padding: 20px;
    border: 1px solid #ccc;
    border-radius: 8px;
}
</style>
