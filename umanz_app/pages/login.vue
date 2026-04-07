<script setup lang="ts">
definePageMeta({
    layout: 'blank',
    middleware: 'logout',
    key: 'login-page'
});

import { z } from 'zod'
import type { FormSubmitEvent } from '#ui/types'
import { useAuth } from '~/features/auth/useAuth';

const schema = z.object({
    email: z.string().email('Email invalide'),
    password: z.string().min(8, 'Le mot de passe doit contenir au moins 8 caractères')
});
type Schema = z.output<typeof schema>;

const formState = reactive({
    email: '',
    password: '',
    error: '',
    loading: false, // in the future
    remember: false // in the future
});

const isPasswordVisible = ref(false);
const { login, verifyMfa, setSession } = useAuth();
const mfaStep = ref(false);
const totpCode = ref('');
const scratchCode = ref('');
const useScratchCode = ref(false);

async function onSubmit(event: FormSubmitEvent<Schema>) {
    formState.error = '';
    formState.loading = true;

    try {
        const apiUrl = useRuntimeConfig().public.apiUrl;
        
        if (mfaStep.value) {
            let loginResponse;
            if (useScratchCode.value) {
                if (!scratchCode.value) {
                    formState.error = 'Veuillez entrer un code de secours';
                    formState.loading = false;
                    return;
                }
                loginResponse = await verifyMfa(formState.email, undefined, scratchCode.value, apiUrl);
            } else {
                const code = parseInt(totpCode.value);
                if (isNaN(code) || totpCode.value.length !== 6) {
                    formState.error = 'Veuillez entrer un code valide à 6 chiffres';
                    formState.loading = false;
                    return;
                }
                loginResponse = await verifyMfa(formState.email, code, undefined, apiUrl);
            }
            
            if (loginResponse) {
                setSession(loginResponse);
                await navigateTo('/');
            }
        } else {
            const loginResponse = await login(formState.email, formState.password, apiUrl);

            if (loginResponse) {
                if (loginResponse.mfaRequired) {
                    mfaStep.value = true;
                } else {
                    setSession(loginResponse);
                    await navigateTo('/');
                }
            } else {
                formState.error = 'Email ou mot de passe incorrect';
            }
        }
    } catch (err: any) {
        console.error('Login error:', err);
        if (err.status === 404) {
            formState.error = 'Email ou mot de passe incorrect';
        } else if (err.status === 400 && mfaStep.value) {
            formState.error = 'Code 2FA invalide';
        } else {
            formState.error = 'Une erreur est survenue lors de la connexion. Veuillez réessayer.';
        }
    } finally {
        formState.loading = false;
    }
}
</script>

<template>
    <div :key="$route.fullPath" class="min-h-screen flex items-center justify-center bg-slate-50 relative overflow-hidden px-4">
        <div class="w-full max-w-md transition-all duration-500 hover:translate-y-[-2px]">
            <div class="bg-white/80 backdrop-blur-xl p-8 md:p-10 rounded-[2rem] shadow-2xl shadow-slate-200/50 border border-white/20">
                <div class="text-center mb-8">
                    <div class="inline-flex items-center justify-center w-16 h-16 rounded-[1.25rem] shadow-lg shadow-umanz-green/25 text-umanz-green text-3xl font-bold mb-6 transform transition-transform hover:scale-110 duration-300">
                        U
                    </div>
                    <h2 class="text-3xl font-bold text-slate-900 tracking-tight">Connexion</h2>
                    <p class="text-slate-500 mt-2 font-medium">Bon retour sur <span class="text-umanz-purple">UmanZ</span></p>
                </div>

                <UForm :schema="schema" :state="formState" class="space-y-5" @submit="onSubmit">
                    <template v-if="!mfaStep">
                        <UFormGroup label="Email" name="email" :ui="{ label: { base: 'text-red-500 font-semibold mb-1.5' } }">
                            <UInput 
                                v-model="formState.email" 
                                icon="i-heroicons-envelope" 
                                placeholder="email@exemple.com"
                                type="email"
                                autocomplete="email"
                                size="lg"
                                variant="outline"
                                :ui="{ rounded: 'rounded-2xl' }"
                                class="transition-all duration-200"
                            />
                        </UFormGroup>

                        <UFormGroup label="Mot de passe" name="password" :ui="{ label: { base: 'text-red-500 font-semibold mb-1.5' } }">
                            <UInput 
                                v-model="formState.password" 
                                :type="isPasswordVisible ? 'text' : 'password'" 
                                autocomplete="current-password"
                                icon="i-heroicons-lock-closed" 
                                placeholder="••••••••"
                                size="lg"
                                variant="outline"
                                :ui="{ rounded: 'rounded-2xl', trailing: { padding: { lg: 'pe-12' } } }"
                                class="transition-all duration-200"
                            >
                                <template #trailing>
                                    <UButton
                                        color="gray"
                                        variant="ghost"
                                        :icon="isPasswordVisible ? 'i-heroicons-eye-slash' : 'i-heroicons-eye'"
                                        class="mr-1"
                                        size="sm"
                                        @click="isPasswordVisible = !isPasswordVisible"
                                    />
                                </template>
                            </UInput>
                        </UFormGroup>
                    </template>

                    <template v-else>
                        <div class="space-y-4">
                            <div class="text-center bg-slate-50 p-4 rounded-2xl border border-slate-100">
                                <p class="text-slate-600 text-sm font-medium">L'authentification à deux facteurs est activée.</p>
                                <p class="text-slate-500 text-xs mt-1">
                                    {{ useScratchCode ? 'Entrez un de vos codes de secours.' : 'Entrez le code qui vous a été envoyé par email.' }}
                                </p>
                            </div>
                            
                            <UFormGroup v-if="!useScratchCode" label="Code 2FA" name="totpCode">
                                <UInput 
                                    v-model="totpCode" 
                                    placeholder="000000"
                                    type="text"
                                    inputmode="numeric"
                                    pattern="[0-9]*"
                                    maxlength="6"
                                    size="xl"
                                    variant="outline"
                                    class="text-center text-2xl tracking-[1em] font-bold"
                                    :ui="{ rounded: 'rounded-2xl' }"
                                />
                            </UFormGroup>

                            <UFormGroup v-else label="Code de secours" name="scratchCode">
                                <UInput 
                                    v-model="scratchCode" 
                                    placeholder="Code de secours"
                                    size="lg"
                                    variant="outline"
                                    :ui="{ rounded: 'rounded-2xl' }"
                                />
                            </UFormGroup>

                            <div class="flex flex-col gap-2">
                                <UButton
                                    variant="ghost"
                                    color="primary"
                                    block
                                    size="sm"
                                    @click="useScratchCode = !useScratchCode"
                                >
                                    {{ useScratchCode ? 'Utiliser un code TOTP' : 'Utiliser un code de secours' }}
                                </UButton>
                                <UButton
                                    variant="ghost"
                                    color="gray"
                                    block
                                    size="sm"
                                    @click="mfaStep = false"
                                >
                                    Retour à la connexion
                                </UButton>
                            </div>
                        </div>
                    </template>

                    <div class="flex items-center justify-between">
                        <UCheckbox 
                            v-model="formState.remember" 
                            label="Se souvenir de moi"
                            :ui="{ label: 'text-sm font-medium text-slate-600', base: 'h-4 w-4 rounded-md border-slate-300 text-umanz-purple focus:ring-umanz-purple' }"
                        />
                        <ULink 
                            to="/forgot-password" 
                            class="text-sm font-semibold text-umanz-purple hover:text-umanz-purple/80 transition-colors"
                        >
                            Mot de passe oublié ?
                        </ULink>
                    </div>

                    <div>
                        <p v-if="formState.error" class="text-red-600 text-sm font-medium text-center bg-red-50 py-3 px-4 rounded-2xl border border-red-100 mb-2">
                            {{ formState.error }}
                        </p>
                    </div>

                    <UButton 
                        type="submit" 
                        block 
                        size="xl" 
                        color="primary"
                        :loading="formState.loading"
                        :disabled="formState.loading"
                        class="py-4 font-bold text-base transition-all duration-300"
                        :ui="{ rounded: 'rounded-2xl' }"
                    >
                        {{ mfaStep ? 'Vérifier le code' : 'Me connecter' }}
                    </UButton>

                    <div class="text-center text-sm text-slate-500 mt-8 font-medium">
                        Vous n'avez pas encore de compte ?
                        <ULink 
                            class="text-umanz-green font-bold hover:text-umanz-green/80 transition-colors ml-1" 
                            to="/register"
                        >
                            Inscrivez-vous ici
                        </ULink>
                    </div>
                </UForm>
            </div>
            
            <p class="text-center text-slate-400 text-xs mt-8">
                &copy; {{ new Date().getFullYear() }} UmanZ. Tous droits réservés.
            </p>
        </div>
    </div>
</template>