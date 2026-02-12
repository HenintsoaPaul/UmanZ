<script setup lang="ts">
definePageMeta({
    layout: 'blank',
    key: 'register-page'
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
    <div :key="$route.fullPath" class="min-h-screen flex items-center justify-center bg-gray-50 px-4 py-12">
        <div class="bg-white p-10 rounded-2xl shadow-sm border border-gray-100 w-full max-w-md">
            <div class="text-center mb-10">
                <div class="w-12 h-12 bg-umanz-purple rounded-xl flex items-center justify-center text-white text-xl font-bold mx-auto mb-4">U</div>
                <h2 class="text-3xl font-extrabold text-gray-900 tracking-tight">Inscription</h2>
                <p class="text-gray-500 mt-2">Rejoignez l'aventure UmanZ</p>
            </div>

            <UForm :schema="schema" :state="formState" class="space-y-5" @submit.prevent="onSubmit">

                <div v-if="message" class="flex items-center p-4 mb-4 text-sm text-green-800 rounded-xl bg-green-50" role="alert">
                    <UIcon name="i-heroicons-check-circle" class="flex-shrink-0 w-5 h-5 me-3" />
                    <div>
                        <span class="font-medium">{{ message }}</span>
                    </div>
                </div>

                <div class="grid grid-cols-2 gap-4">
                    <UFormGroup label="Nom" name="nom">
                        <UInput v-model="formState.nom" placeholder="Nom" />
                    </UFormGroup>

                    <UFormGroup label="Prénom" name="prenom">
                        <UInput v-model="formState.prenom" placeholder="Prénom" />
                    </UFormGroup>
                </div>

                <UFormGroup label="Email" name="email">
                    <UInput v-model="formState.mail" icon="i-heroicons-envelope" placeholder="email@exemple.com" />
                </UFormGroup>

                <UFormGroup label="Date de naissance" name="date-naissance">
                    <UInput v-model="formState.dateNaissance" type="date" icon="i-heroicons-calendar" />
                </UFormGroup>

                <UFormGroup label="Numéro CNaPS" name="numero-cnaps">
                    <UInput v-model="formState.idCnaps" icon="i-heroicons-identification" placeholder="12345678" />
                </UFormGroup>

                <UFormGroup label="Mot de passe" name="password">
                    <UInput v-model="formState.password" type="password" icon="i-heroicons-lock-closed" placeholder="••••••••" />
                </UFormGroup>

                <UFormGroup label="Confirmer le mot de passe" name="password2">
                    <UInput v-model="formState.password2" type="password" icon="i-heroicons-lock-closed" placeholder="••••••••" />
                </UFormGroup>

                <p v-if="formState.error" class="text-red-500 text-sm text-center bg-red-50 py-2 rounded-lg">{{ formState.error }}</p>

                <UButton type="submit" block size="lg" class="shadow-sm">
                    M'inscrire
                </UButton>

                <div class="text-center text-sm text-gray-500 mt-8">
                    Vous avez déjà un compte ?
                    <ULink class="text-umanz-green font-semibold hover:underline" to="/">Connectez-vous ici</ULink>
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