<script setup lang="ts">
import { z } from 'zod'
import type { FormSubmitEvent } from '#ui/types'
import axios from 'axios';

const schema = z.object({
    nomFormation: z.string().min(5, 'Le nom de la formation est obligatoire'),
    dateDebut: z.string().date('Date Debut incorrect'),
    dateFin: z.string().date()
});
type Schema = z.output<typeof schema>;

function validateDates() {
    const dateDebut = new Date(form.dateDebut);
    const dateFin = new Date(form.dateFin);
    return dateDebut <= dateFin;
}

const form = reactive({
    nomFormation: '',
    dateDebut: '',
    dateFin: '',
    error: ''
});

const isFormValid = computed(() => {
    return schema.safeParse({ ...form }).success && validateDates();
});

const loading = ref(false);

async function onSubmit(event: FormSubmitEvent<Schema>) {
    const isValid = schema.safeParse(event.data).success;

    if (!isValid) {
        form.error = 'Une erreur s\'est produite lors de la soumission du formulaire.';
        return;
    }

    loading.value = true;
    try {
        const apiUrl: string = useRuntimeConfig().public.apiUrl as string;
        const response = await axios.post(`${apiUrl}/formations`, toRaw(form));

        console.log('Form submitted successfully:', response.data);
        form.error = '';
    } catch (error) {
        console.error('Error submitting form:', error);
        form.error = 'Une erreur s\'est produite lors de la soumission du formulaire.';
    } finally {
        loading.value = false;
    }
}
</script>

<template>
    <UForm :schema="schema" :state="form" class="space-y-4" @submit="onSubmit">
        <UFormGroup label="Titre" name="titre">
            <UInput v-model="form.nomFormation" required />
        </UFormGroup>

        <UFormGroup label="Date Debut" name="dateDebut">
            <UInput v-model="form.dateDebut" type="date" required />
        </UFormGroup>

        <UFormGroup label="Date Fin" name="dateFin">
            <UInput v-model="form.dateFin" type="date" />
        </UFormGroup>

        <UButton type="submit" :disabled="!isFormValid" :loading="loading">
            {{ loading ? 'En cours...' : 'Soumettre' }}
        </UButton>

        <p v-if="form.error" class="text-red-500">{{ form.error }}</p>
    </UForm>
</template>