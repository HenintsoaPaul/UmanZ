<script setup lang="ts">
definePageMeta({
    layout: 'blank'
});

import { useRouter } from 'vue-router'
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

const router = useRouter();
const { authenticate } = useAuth();

async function onSubmit(event: FormSubmitEvent<Schema>) {
    const isValid = schema.safeParse(event.data).success;

    if (isValid) {
        const userEmail = formState.email;
        const userPassword = formState.password;

        const apiUrl = useRuntimeConfig().public.apiUrl;
        const user = await authenticate(userEmail, userPassword, `${apiUrl}/talents/users`);


        if (user) {
            if (user.mail === userEmail || user.password === userPassword) {
                localStorage.setItem('idUser', user.idTalent.toString());
                localStorage.setItem('isAdmin', user.isAdmin.toString());
                router.push('/Home');
            } else {
                formState.error = 'Email ou Mot de Passe incorrecte'
            }
        } else {
            formState.error = 'Utilisateur inconnu'
        }
    }
}
</script>

<template>
    <div class="min-h-screen flex items-center justify-center font-mono">
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

                <div class="w-full flex justify-center">
                    <UButton type="submit"
                        class="w-1/2 bg-blue-500 text-white py-2 rounded-lg hover:bg-blue-600 transition duration-300 flex justify-center">
                        Soumettre
                    </UButton>
                </div>

                <p v-if="formState.error" class="text-red-500 text-center mt-4">{{ formState.error }}</p>
            </UForm>
        </div>
    </div>
</template>
