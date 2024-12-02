<script setup lang="ts">
definePageMeta({
    layout: 'blank',
    middleware: 'logout'
});

import axios from 'axios';
import { z } from 'zod'

const message = ref('');

const schema = z.object({
    nom: z.string(),
    prenom: z.string(),
    mail: z.string().email('Email invalide'),
    dateNaissance: z.string().date(),
    idCnaps: z.string(),
    password: z.string().min(8, 'Must be at least 8 characters'),
    password2: z.string().min(8, 'Must be at least 8 characters'),
});
const formState = reactive({
    nom: undefined,
    prenom: undefined,
    mail: undefined,
    dateNaissance: undefined,
    idCnaps: '',
    password: undefined,
    password2: undefined,
    error: ''
});

async function onSubmit() {
    formState.error = '';
    message.value = '';

    if (formState.password !== formState.password2) {
        formState.error = 'Les 2 mots de passe ne correspondent pas'
        return;
    }

    const {password2, error, ...data} = formState;

    try {
        console.log(data);

        const apiUrl: string = useRuntimeConfig().public.apiUrl as string;
        const response = await axios.post(`${apiUrl}/talents`, data);
        if (response.status === 200) {
            message.value = 'Inscription réussie !';
        } else {
            formState.error = "Erreur lors de l'inscription";
            console.error("Erreur lors de l'inscription", response.data);
        }
    } catch (error) {
        console.error('Erreur lors de la requête API:', error);
    }
}
</script>

<template>
    <div class="min-h-screen flex items-center justify-center font-mono">
        <div class="border border-slate-50 p-8 rounded-lg shadow-md w-full max-w-md text-slate-500">
            <h2 class="text-2xl font-bold mb-6 text-center">Inscription</h2>
            <UForm :schema="schema" :state="formState" class="space-y-4" @submit.prevent="onSubmit">

                <div v-if="message" class="flex items-center p-4 mb-4 text-sm text-green-800 rounded-lg bg-green-50 dark:bg-gray-800 dark:text-green-400" role="alert">
                    <svg class="flex-shrink-0 inline w-4 h-4 me-3" aria-hidden="true" xmlns="http://www.w3.org/2000/svg" fill="currentColor" viewBox="0 0 20 20">
                        <path d="M10 .5a9.5 9.5 0 1 0 9.5 9.5A9.51 9.51 0 0 0 10 .5ZM9.5 4a1.5 1.5 0 1 1 0 3 1.5 1.5 0 0 1 0-3ZM12 15H8a1 1 0 0 1 0-2h1v-3H8a1 1 0 0 1 0-2h2a1 1 0 0 1 1 1v4h1a1 1 0 0 1 0 2Z"/>
                    </svg>
                    <span class="sr-only">Info</span>
                    <div>
                        <span class="font-medium">{{ message }}</span>
                    </div>
                </div>

                <UFormGroup label="Nom" name="nom">
                    <UInput
                        v-model="formState.nom"
                    />
                </UFormGroup>

                <UFormGroup label="Prénom" name="prenom">
                    <UInput
                        v-model="formState.prenom"
                    />
                </UFormGroup>

                <UFormGroup label="Email" name="email">
                    <UInput v-model="formState.mail"
                        class="w-full focus:outline-none focus:ring-2 focus:ring-blue-500" />
                </UFormGroup>

                <UFormGroup label="Date de naissace" name="date-naissance">
                    <UInput
                        v-model="formState.dateNaissance"
                        type="date"
                    />
                </UFormGroup>

                <UFormGroup label="Numéro CNaPS" name="numero-cnaps">
                    <UInput v-model="formState.idCnaps"/>
                </UFormGroup>

                <UFormGroup label="Mot de passe" name="password">
                    <UInput v-model="formState.password" type="password"
                        class="w-full focus:outline-none focus:ring-2 focus:ring-blue-500" />
                </UFormGroup>

                <UFormGroup label="Confirmer le mot de passe" name="password2">
                    <UInput v-model="formState.password2" type="password"
                        class="w-full focus:outline-none focus:ring-2 focus:ring-blue-500" />
                </UFormGroup>

                <p v-if="formState.error" class="text-red-500 text-center mt-4">{{ formState.error }}</p>

                <div class="w-full flex justify-center">
                    <UButton type="submit"
                        class="w-1/2 bg-blue-500 text-white py-2 rounded-lg hover:bg-blue-600 transition duration-300 flex justify-center">
                        M'inscrire
                    </UButton>
                </div>

                <div class="text-sm mt-4">
                    Vous avez déjà un compte ?
                    <NuxtLink class="text-green-400" to="/">Connectez-vous ici</NuxtLink>
                </div>

            </UForm>
        </div>
    </div>
</template>

<style scoped>
body {
    font-family: 'Inter', sans-serif;
}
</style>