<script setup lang="ts">
import { ref, computed } from 'vue';
import { useRoute } from 'vue-router';
import type { Entretien } from '~/types';

const route = useRoute();
const apiUrl = useRuntimeConfig().public.apiUrl as string;
const entretienId = computed(() => route.params.id);
const url = computed(() => `${apiUrl}/entretien/${entretienId.value}`);

const { data: entretien, error: entretienError, refresh: refreshEntretien } = useFetch<Entretien>(`${url.value}`);

const successMessage = ref('');
const errorMessage = ref('');

const validerEntretien = async (entretien: Entretien) => {    
    try {
        if (entretien.note <= 0 || entretien.note >= 20) {
            throw new Error("Note must be in range [0:20]");    
        }
        if (entretien.motif === "") {
            throw new Error("Motif is undefined");    
        }

        const response = await $fetch(`${apiUrl}/entretien/validate`, {
            method: 'POST',
            body: {
                idEntretien: entretien.idEntretien,
                note: entretien.note,
                motif: entretien.motif
            }
        });
        successMessage.value = 'Entretien validé avec succès';
        errorMessage.value = '';
        console.log('Entretien validé:', response);
        await refreshEntretien();
    } catch (error) {
        errorMessage.value = 'Erreur lors du validation de l\'entretien';
        successMessage.value = '';
        console.error('Erreur lors du validation de l\'entretien:', error);
    }
}

const refuserEntretien = async () => {
    try {
        const response = await $fetch(`${apiUrl}/entretien/deny`, {
            method: 'POST',
            body: {
                idEntretien: Number(entretienId.value),
            }
        });
        successMessage.value = 'Entretien refusé avec succès';
        errorMessage.value = '';
        console.log('Entretien refusé:', response);
    } catch (error) {
        errorMessage.value = 'Erreur lors du refus de l\'entretien';
        successMessage.value = '';
        console.error('Erreur lors du refus de l\'entretien:', error);
    }
}
</script>

<template>
    <div class="container mx-auto">

        <div v-if="entretien">
            <EntretienDetails :entretien="entretien" :valider-entretien="validerEntretien"
                :refuser-entretien="refuserEntretien" />
        </div>
        <div v-else>
            Loading Details...
        </div>

        <div v-if="entretienError" class="text-red-500">
            Erreur lors du chargement de l'entretien: {{ entretienError.message }}
        </div>

        <div v-if="successMessage" class="text-green-500">
            {{ successMessage }}
        </div>
        <div v-if="errorMessage" class="text-red-500">
            {{ errorMessage }}
        </div>
    </div>
</template>
