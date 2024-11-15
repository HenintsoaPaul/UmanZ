<script setup lang="ts">
import { z } from 'zod'
import { reactive, ref, toRaw } from 'vue';
import axios from 'axios';

import FormTalent from '~/components/talent/FormTalent.vue';

const schema = z.object({
    nom: z.string().min(1, 'Le nom est obligatoire'),
    prenom: z.string().min(1, 'Le prénom est obligatoire'),
    mail: z.string().email('Email incorrect'),
    password: z.string().min(6, 'Le mot de passe doit contenir au moins 6 caractères'),
    isAdmin: z.boolean()
});

const form = reactive({
    nom: '',
    prenom: '',
    mail: '',
    password: '',
    isAdmin: false,
    error: '',
    successMessage: ''
});

const loading = ref(false);

async function onSubmit() {
    const isValid = schema.safeParse(form).success;

    if (!isValid) {
        form.error = 'Une erreur s\'est produite lors de la soumission du formulaire.';
        return;
    }

    loading.value = true;
    try {
        const apiUrl: string = useRuntimeConfig().public.apiUrl as string;
        const response = await axios.post(`${apiUrl}/talents`, toRaw(form));

        console.log('Form submitted successfully:', response.data);
        form.error = '';
        form.successMessage = 'Le formulaire a été soumis avec succès.';
    } catch (error) {
        console.error('Error submitting form:', error);
        form.error = 'Une erreur s\'est produite lors de la soumission du formulaire.';
        form.successMessage = '';
    } finally {
        loading.value = false;
    }
}
</script>

<template>
    <div class="talent-form">
        <h1>Ajouter Talent</h1>

        <FormTalent :form="form" :schema="schema" :loading="loading" @submit="onSubmit" />
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
