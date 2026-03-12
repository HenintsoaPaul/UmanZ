<script setup lang="ts">
import QrcodeVue from 'qrcode.vue';
import { useAuth } from '~/features/auth/useAuth';

const props = defineProps({
    email: {
        type: String,
        required: true
    },
    mfaEnabled: {
        type: Boolean,
        default: false
    }
});

const emit = defineEmits(['mfa-updated']);
const apiUrl = useRuntimeConfig().public.apiUrl;

const setupData = ref<{ secret: string; qrCodeUri: string } | null>(null);
const verificationCode = ref('');
const loading = ref(false);
const error = ref('');
const success = ref('');
const scratchCodes = ref<string[]>([]);
const { setupMfa, confirmMfa } = useAuth();

async function startSetup() {
    loading.value = true;
    error.value = '';
    try {
        const response = await setupMfa(props.email, apiUrl);
        setupData.value = response;
    } catch (err) {
        error.value = "Erreur lors de l'initialisation du 2FA";
    } finally {
        loading.value = false;
    }
}

async function confirmSetup() {
    if (verificationCode.value.length !== 6) {
        error.value = "Le code doit contenir 6 chiffres";
        return;
    }

    loading.value = true;
    error.value = '';
    try {
        const response = await confirmMfa(props.email, parseInt(verificationCode.value, 10), apiUrl);
        scratchCodes.value = response.scratchCodes;
        success.value = "Authentification à deux facteurs activée avec succès !";
        setupData.value = null;
        emit('mfa-updated');
    } catch (err) {
        error.value = "Code invalide. Veuillez réessayer.";
    } finally {
        loading.value = false;
    }
}

async function disableMfa() {
    // For now, let's just show a message. Disabling might need another endpoint or just setting mfaEnabled to false.
    // In a real app, you'd want to verify the code before disabling.
    error.value = "La désactivation du 2FA n'est pas encore implémentée.";
}

function copyScratchCodes() {
    navigator.clipboard.writeText(scratchCodes.value.join('\n'));
}
</script>

<template>
    <div class="bg-white dark:bg-slate-900 rounded-3xl p-6 shadow-sm border border-gray-100 dark:border-slate-800">
        <div class="flex items-center gap-3 mb-4">
            <div class="w-10 h-10 rounded-xl bg-umanz-purple/10 flex items-center justify-center text-umanz-purple">
                <UIcon name="i-heroicons-shield-check" class="text-2xl" />
            </div>
            <div>
                <h3 class="text-lg font-bold text-gray-900 dark:text-white">Authentification à deux facteurs (2FA)</h3>
                <p class="text-sm text-gray-500 dark:text-slate-400">Renforcez la sécurité de votre compte</p>
            </div>
        </div>

        <div v-if="success" class="space-y-4 mb-4">
            <div class="bg-green-50 dark:bg-green-900/20 text-green-700 dark:text-green-400 p-4 rounded-2xl text-sm font-medium flex items-center gap-2">
                <UIcon name="i-heroicons-check-circle" />
                {{ success }}
            </div>
            
            <div v-if="scratchCodes.length > 0" class="bg-amber-50 dark:bg-amber-900/20 border border-amber-100 dark:border-amber-800 p-4 rounded-2xl">
                <p class="text-amber-800 dark:text-amber-400 font-bold text-sm mb-2">Codes de secours (Sauvegardez-les !)</p>
                <p class="text-xs text-amber-700 dark:text-amber-500 mb-3">Ces codes ne seront affichés qu'une seule fois. Ils vous permettent d'accéder à votre compte si vous perdez votre téléphone.</p>
                <div class="grid grid-cols-2 gap-2">
                    <div v-for="code in scratchCodes" :key="code" class="bg-white dark:bg-slate-800 p-2 rounded-xl text-center font-mono font-bold text-gray-700 dark:text-slate-300 border border-amber-100/50">
                        {{ code }}
                    </div>
                </div>
                <UButton
                    class="mt-4"
                    size="xs"
                    color="amber"
                    variant="soft"
                    icon="i-heroicons-clipboard-document"
                    @click="copyScratchCodes"
                >
                    Copier les codes
                </UButton>
            </div>
        </div>

        <div v-if="error" class="bg-red-50 dark:bg-red-900/20 text-red-700 dark:text-red-400 p-4 rounded-2xl mb-4 text-sm font-medium flex items-center gap-2">
            <UIcon name="i-heroicons-exclamation-circle" />
            {{ error }}
        </div>

        <div v-if="!mfaEnabled && !setupData" class="space-y-4">
            <p class="text-sm text-gray-600 dark:text-slate-400">
                L'authentification à deux facteurs ajoute une couche de sécurité supplémentaire. Une fois activée, vous devrez fournir un code généré par une application (comme Google Authenticator) pour vous connecter.
            </p>
            <UButton 
                color="primary" 
                @click="startSetup" 
                :loading="loading"
                :ui="{ rounded: 'rounded-xl' }"
            >
                Activer le 2FA
            </UButton>
        </div>

        <div v-else-if="setupData" class="space-y-6">
            <div class="flex flex-col md:flex-row gap-8 items-center md:items-start text-center md:text-left">
                <div class="p-4 bg-white rounded-2xl shadow-inner border border-gray-100">
                    <QrcodeVue :value="setupData.qrCodeUri" :size="200" level="M" />
                </div>
                <div class="flex-1 space-y-4">
                    <div class="space-y-2">
                        <p class="font-bold text-gray-900 dark:text-white">1. Scannez ce code QR</p>
                        <p class="text-sm text-gray-600 dark:text-slate-400">
                            Ouvrez votre application d'authentification (Google Authenticator, Authy, etc.) et scannez le code QR ci-contre.
                        </p>
                        <p class="text-xs text-gray-500 bg-gray-50 dark:bg-slate-800 p-2 rounded-lg break-all">
                            Secret: <span class="font-mono font-bold">{{ setupData.secret }}</span>
                        </p>
                    </div>
                    <div class="space-y-2">
                        <p class="font-bold text-gray-900 dark:text-white">2. Vérifiez l'activation</p>
                        <p class="text-sm text-gray-600 dark:text-slate-400">
                            Entrez le code à 6 chiffres généré par votre application.
                        </p>
                        <div class="flex gap-2">
                            <UInput 
                                v-model="verificationCode" 
                                placeholder="000000" 
                                maxlength="6" 
                                class="w-32"
                                :ui="{ rounded: 'rounded-xl' }"
                            />
                            <UButton 
                                color="primary" 
                                @click="confirmSetup" 
                                :loading="loading"
                                :ui="{ rounded: 'rounded-xl' }"
                            >
                                Confirmer
                            </UButton>
                            <UButton 
                                color="gray" 
                                variant="ghost" 
                                @click="setupData = null"
                                :ui="{ rounded: 'rounded-xl' }"
                            >
                                Annuler
                            </UButton>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div v-else class="space-y-4">
            <div class="flex items-center gap-2 text-green-600 dark:text-green-400 font-bold">
                <UIcon name="i-heroicons-check-badge" class="text-xl" />
                <span>Le 2FA est actuellement activé sur votre compte.</span>
            </div>
            <p class="text-sm text-gray-600 dark:text-slate-400">
                Votre compte est protégé par une authentification à deux facteurs.
            </p>
            <UButton 
                color="red" 
                variant="soft" 
                @click="disableMfa"
                :ui="{ rounded: 'rounded-xl' }"
            >
                Désactiver le 2FA
            </UButton>
        </div>
    </div>
</template>
