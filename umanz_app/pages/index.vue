<script setup lang="ts">
definePageMeta({
    layout: 'blank',
    middleware: 'logout'
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
    <div :key="$route.fullPath" class="min-h-screen flex items-center justify-center font-mono">
        <div class="border border-slate-50 p-8 rounded-lg shadow-md w-full max-w-md text-slate-500">
            <h2 class="text-2xl font-bold mb-6 text-center">Login</h2>
            <UForm :schema="schema" :state="formState" class="space-y-4" @submit="onSubmit">
                <UFormGroup label="Email" name="email">
                    <UInput v-model="formState.email"
                        class="w-full focus:outline-none focus:ring-2 focus:ring-blue-500" />
                </UFormGroup>

                <UFormGroup label="Mot de passe" name="password">
                    <UInput v-model="formState.password" type="password"
                        class="w-full focus:outline-none focus:ring-2 focus:ring-blue-500" />
                </UFormGroup>

                <p v-if="formState.error" class="text-red-500 text-center mt-4">{{ formState.error }}</p>

                <div class="w-full flex justify-center">
                    <UButton type="submit"
                        class="w-1/2 bg-blue-500 text-white py-2 rounded-lg hover:bg-blue-600 transition duration-300 flex justify-center">
                        Me connecter
                    </UButton>
                </div>

                <div class="text-sm mt-4">
                    Vous n'avez pas encore de compte ?
                    <ULink class="text-green-400" to="/register">Inscrivez-vous ici</ULink>
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