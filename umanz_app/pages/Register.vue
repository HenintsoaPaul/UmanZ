<script setup lang="ts">
definePageMeta({
    layout: 'blank',
    middleware: 'logout'
});

import { z } from 'zod'
import type { FormSubmitEvent } from '#ui/types'

const schema = z.object({
    nom: z.string(),
    prenom: z.string(),
    email: z.string().email('Email invalide'),
    dateNaissance: z.string().date(),
    password: z.string().min(8, 'Must be at least 8 characters'),
    password2: z.string().min(8, 'Must be at least 8 characters'),
});
type Schema = z.output<typeof schema>;

const formState = reactive({
    nom: undefined,
    prenom: undefined,
    email: undefined,
    dateNaissance: undefined,
    password: undefined,
    password2: undefined,
    error: ''
});

async function onSubmit() {
    console.log('Soumission');
    console.log(toRaw(formState));

    if (formState.password !== formState.password2) {
        formState.error = 'Les 2 mots de passe ne correspondent pas'
        return;
    }

    console.log('Password checked');
}
</script>

<template>
    <div class="min-h-screen flex items-center justify-center font-mono">
        <div class="border border-slate-50 p-8 rounded-lg shadow-md w-full max-w-md text-slate-500">
            <h2 class="text-2xl font-bold mb-6 text-center">Inscription</h2>
            <UForm :schema="schema" :state="formState" class="space-y-4" @submit.prevent="onSubmit">
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
                    <UInput v-model="formState.email"
                        class="w-full focus:outline-none focus:ring-2 focus:ring-blue-500" />
                </UFormGroup>

                <UFormGroup label="Date de naissace" name="date-naissance">
                    <UInput
                        v-model="formState.dateNaissance"
                        type="date"
                    />
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

            </UForm>
        </div>
    </div>
</template>

<style scoped>
body {
    font-family: 'Inter', sans-serif;
}
</style>