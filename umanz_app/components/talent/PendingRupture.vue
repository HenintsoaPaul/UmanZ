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
    <div class="container mx-auto">
        <template v-if="pendingRupture">
            <div class="flex flex-col items-center justify-center border border-gray-100 p-5 rounded-md">
                <p class="mb-4 text-lg text-gray-200">Type: {{ pendingRupture.typeRupture.typeRupture }}</p>
                <p class="mb-4 text-lg text-gray-200">Date declaration: {{ pendingRupture.dateRupture }}</p>
                <button @click="validerFn"
                    class="px-4 py-2 font-semibold text-white bg-orange-500 rounded hover:bg-orange-700">
                    Accepter, sans broncher
                </button>
            </div>
        </template>
        <template v-else-if="canDemissioner">
            <div class="mx-auto w-full flex flex-col items-center justify-center">
                <UButton :loading="isLoading" @click="demissionFn"
                    class="bg-red-500 hover:bg-red-700 text-white font-bold py-2 px-4 rounded">
                    Demission
                </UButton>
            </div>
        </template>
    </div>
</template>
