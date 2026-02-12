<script setup lang="ts">
definePageMeta({
    layout: 'blank',
    middleware: 'logout',
    key: 'login-page'
});

import { z } from 'zod'
import type { FormSubmitEvent } from '#ui/types'

const schema = z.object({
    email: z.string().email('Invalid email'),
    password: z.string().min(8, 'Must be at least 8 characters')
});
type Schema = z.output<typeof schema>;

const formState = reactive({
    email: '',
    password: '',
    error: ''
});

const { authenticate, saveUser } = useAuth();

async function onSubmit(event: FormSubmitEvent<Schema>) {
    const isValid = schema.safeParse(event.data).success;

    if (isValid) {
        const userEmail = formState.email;
        const userPassword = formState.password;

        const apiUrl = useRuntimeConfig().public.apiUrl;
        const user = await authenticate(userEmail, userPassword, apiUrl);

        if (user) {
            saveUser(user);
            await navigateTo('/Home');
        } else {
            formState.error = 'Email ou Mot de passe inconnu'
        }
    }
}
</script>

<template>
    <div :key="$route.fullPath" class="min-h-screen flex items-center justify-center bg-gray-50 px-4">
        <div class="bg-white p-10 rounded-2xl shadow-sm border border-gray-100 w-full max-w-md">
            <div class="text-center mb-10">
                <div class="w-12 h-12 bg-umanz-purple rounded-xl flex items-center justify-center text-white text-xl font-bold mx-auto mb-4">U</div>
                <h2 class="text-3xl font-extrabold text-gray-900 tracking-tight">Connexion</h2>
                <p class="text-gray-500 mt-2">Bienvenue sur votre plateforme UmanZ</p>
            </div>

            <UForm :schema="schema" :state="formState" class="space-y-6" @submit="onSubmit">
                <UFormGroup label="Email" name="email">
                    <UInput v-model="formState.email" icon="i-heroicons-envelope" placeholder="email@exemple.com" />
                </UFormGroup>

                <UFormGroup label="Mot de passe" name="password">
                    <UInput v-model="formState.password" type="password" icon="i-heroicons-lock-closed" placeholder="••••••••" />
                </UFormGroup>

                <p v-if="formState.error" class="text-red-500 text-sm text-center bg-red-50 py-2 rounded-lg">{{ formState.error }}</p>

                <UButton type="submit" block size="lg" class="shadow-sm">
                    Me connecter
                </UButton>

                <div class="text-center text-sm text-gray-500 mt-8">
                    Vous n'avez pas encore de compte ?
                    <ULink class="text-umanz-green font-semibold hover:underline" to="/register">Inscrivez-vous ici</ULink>
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