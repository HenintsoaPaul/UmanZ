<script setup lang="ts">
import type { Rupture } from '~/types';

const props = defineProps<{
    idContrat: number;
    apiUrl: string;
}>();

const { data: pendingRupture } = useFetch<Rupture>(`${props.apiUrl}/ruptures/pendings/${props.idContrat}`);

const isAdmin = computed(() => {
    const sstr = localStorage.getItem("umanz-isAdmin");
    return sstr ? Boolean(sstr) : false;
});
const canDemissioner = computed(() => {
    if (pendingRupture) return props.idContrat > -1 && isAdmin.value;
    return false;
});

// TODO: manova anreto
const motif = "fux u";
const daty = new Date(2024, 11, 2);
// TODO: manova anreto

const isLoading = ref(false);


const demissionFn = () => {
    isLoading.value = true;
    console.log("demission");

    const payLoad = {
        motif: motif, date: daty, idContrat: props.idContrat
    }

    useFetch(`${props.apiUrl}/ruptures/demission`, {
        method: 'POST',
        body: JSON.stringify(payLoad),
        headers: {
            'Content-Type': 'application/json'
        }
    }).then(({ data }) => {
        if (data) {
            console.log('Demission request sent successfully');
        } else {
            console.error('Failed to send demission request');
        }
    }).catch(error => {
        console.error('Error:', error);
    }).finally(() => {
        isLoading.value = false;
    });
}
</script>

<template>
    <div class="container mx-auto">
        <template v-if="canDemissioner">
            <div class="mx-auto w-full flex flex-col items-center justify-center">

                <UButton :loading="isLoading" @click="demissionFn" class="bg-red-500 hover:bg-red-700 text-white font-bold py-2 px-4 rounded">
                    Demission
                </UButton>
            </div>
        </template>
        <template v-else>
            <div class="flex flex-col items-center justify-center border border-gray-100 p-5 rounded-md">
                <p class="mb-4 text-lg text-gray-700">This is a simple paragraph.</p>
                <button class="px-4 py-2 font-semibold text-white bg-blue-500 rounded hover:bg-blue-700">
                    OK
                </button>
            </div>
        </template>
    </div>
</template>
