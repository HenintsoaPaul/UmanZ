<script setup lang="ts">
import type { Rupture } from '~/types';

const props = defineProps<{
    idContrat: number;
    apiUrl: string;
}>();

const { data: pendingRupture } = useFetch<Rupture>(`${props.apiUrl}/ruptures/pendings/${props.idContrat}`);

const isAdmin = computed(() => {
    if (process.client) {
        const sstr = localStorage.getItem("umanz-isAdmin");
        return sstr ? Boolean(sstr) : false;
    }
    return false;
});
const canDemissioner = computed(() => {
    if (pendingRupture) return props.idContrat > -1 && isAdmin.value;
    return false;
});

const isLoading = ref(false);

const demissionFn = async () => {
    isLoading.value = true;
    console.log("Redirection vers form validation demission");

    const motif = "Demande de demission.";
    const payLoad = {
        motif: motif, date: new Date().toDateString(), idContrat: props.idContrat
    }

    try {
        const response = $fetch<Rupture>(`${props.apiUrl}/ruptures/demission`, {
            method: 'POST',
            body: payLoad,
        });
        console.log('Demission request sent successfully', response);
    }
    catch (error) {
        console.error('Failed to send demission request', error);
    } finally {
        isLoading.value = false;
    }
}

const validerFn = async () => {
    isLoading.value = true;

    const payLoad = {
        dateValidation: new Date().toDateString()
    }

    try {
        const response = $fetch<Rupture>(`${props.apiUrl}/ruptures/validate/${props.idContrat}`, {
            method: 'POST',
            params: payLoad,
        });
        console.log('Demission request sent successfully', response);
    }
    catch (error) {
        console.error('Failed to send demission request', error);
    } finally {
        isLoading.value = false;
    }
}
</script>

<template>
    <div class="mx-auto">
        <template v-if="pendingRupture">
            <div class="flex flex-col items-center justify-center 
                bg-orange-50/50 dark:bg-orange-950/10 
                border border-orange-100 dark:border-orange-900/20 
                p-8 rounded-[2rem] transition-colors duration-300">
                <div class="w-12 h-12 
                    bg-orange-100 dark:bg-orange-900/30 
                    rounded-full flex items-center justify-center mb-4">
                    <UIcon name="i-heroicons-exclamation-circle" class="w-7 h-7 text-orange-600 dark:text-orange-500" />
                </div>
                <h3 class="text-xl font-bold text-slate-900 dark:text-white mb-2">Rupture de contrat en cours</h3>
                <p class="text-slate-600 dark:text-slate-400 mb-1 font-medium">Type: <span class="text-slate-900 dark:text-slate-200">{{ pendingRupture.typeRupture.typeRupture }}</span></p>
                <p class="text-slate-600 dark:text-slate-400 mb-6 font-medium">Déclaré le: <span class="text-slate-900 dark:text-slate-200">{{ pendingRupture.dateRupture }}</span></p>
                
                <UButton
                    v-if="isAdmin"
                    @click="validerFn"
                    color="orange"
                    size="lg"
                    :loading="isLoading"
                    class="rounded-xl px-6 font-bold shadow-lg shadow-orange-500/20"
                >
                    Accepter la rupture
                </UButton>
                <UBadge v-else color="orange" variant="soft" class="rounded-full px-4 py-1.5 font-bold">
                    En attente de validation
                </UBadge>
            </div>
        </template>
        <template v-else-if="canDemissioner">
            <div class="flex flex-col items-center justify-center p-8">
                <UButton 
                    :loading="isLoading" 
                    @click="demissionFn"
                    color="red"
                    size="xl"
                    variant="soft"
                    class="rounded-2xl px-10 font-bold shadow-lg shadow-red-500/10 hover:shadow-red-500/20 transition-all duration-300"
                    icon="i-heroicons-arrow-right-on-rectangle"
                >
                    Démissionner
                </UButton>
                <p class="mt-4 text-sm text-slate-400 font-medium italic">Une notification sera envoyée aux administrateurs.</p>
            </div>
        </template>
    </div>
</template>
