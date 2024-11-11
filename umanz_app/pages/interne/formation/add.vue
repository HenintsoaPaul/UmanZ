<script setup lang="ts">
import { useRouter } from 'vue-router'
import { z } from 'zod'
import type { FormSubmitEvent } from '#ui/types'
import axios from 'axios';

// Définir le schéma
const schema = z.object({
    titre: z.string().min(1, 'Le titre est obligatoire'),
    dateDebut: z.string().date('Date Debut incorrect'),
    dateFin: z.string().date()
});
type Schema = z.output<typeof schema>;

function validateDates() {
    const dateDebut = new Date(formState.dateDebut);
    const dateFin = new Date(formState.dateFin);

    return dateDebut <= dateFin;
}



const formState = reactive({
    titre: '',
    dateDebut: '',
    dateFin: '',
    error: ''
});

const isFormValid = computed(() => {
    return schema.safeParse({ ...formState }).success && validateDates();
});



const loading = ref(false);

async function onSubmit(event: FormSubmitEvent<Schema>) {
    const isValid = schema.safeParse(event.data).success;

    if (!isValid) {
        formState.error = 'Une erreur s\'est produite lors de la soumission du formulaire.';
        return;
    }

    loading.value = true;
    try {
        const formData = {
            titre: formState.titre,
            dateDebut: formState.dateDebut,
            dateFin: formState.dateFin,
        };

        console.log(formData);

        const apiUrl: string = useRuntimeConfig().public.apiUrl as string;
        console.log(apiUrl);

        const response = await axios.post(`${apiUrl}/formations`, formData);
        console.log('Form submitted successfully:', response.data);
        formState.error = '';

    } catch (error) {
        console.error('Error submitting form:', error);
        formState.error = 'Une erreur s\'est produite lors de la soumission du formulaire.';
    }
    finally {
        loading.value = false;
    }
}
</script>

<template>
    <UForm :schema="schema" :state="formState" class="space-y-4" @submit="onSubmit">
        <UFormGroup label="Titre" name="titre">
            <UInput v-model="formState.titre" />
        </UFormGroup>

        <UFormGroup label="Date Debut" name="dateDebut">
            <UInput v-model="formState.dateDebut" type="date" />
        </UFormGroup>

        <UFormGroup label="Date Debut" name="dateDebut">
            <UInput v-model="formState.dateDebut" type="date" />
        </UFormGroup>

        <UButton type="submit" :disabled="!isFormValid" :loading="loading">
            {{ loading ? 'En cours...' : 'Soumettre' }}
        </UButton>

        <p v-if="formState.error" class="text-red-500">{{ formState.error }}</p>
    </UForm>
</template>