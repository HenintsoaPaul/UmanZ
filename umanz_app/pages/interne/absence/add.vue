<script setup lang="ts">
import { z } from 'zod'

const schema = z.object({
    motif: z.string().min(1, "Motif d'absence obligatoire"),
    dateAbsence: z.string().date('Date Absence incorrect'),
});

const form = reactive({
    motif: '',
    dateAbsence: '',
    idContrat: ''
});

const errorMessage = ref('');
const successMessage = ref('');
const loading = ref(false);

const apiUrl = useRuntimeConfig().public.apiUrl as string;
async function onSubmit() {
    const result = schema.safeParse({ ...form });
    if (!result.success) {
        errorMessage.value = result.error.errors.map(err => err.message).join(', ');
        return;
    }

    loading.value = true;
    try {
        const response = await $fetch(`${apiUrl}/formations`, {
            method: 'POST',
            body: toRaw(form)
        });

        console.log('Form submitted successfully:', response);
        errorMessage.value = '';
        successMessage.value = 'Le formulaire a été soumis avec succès.';
    } catch (error) {
        console.error('Error submitting form:', error);
        errorMessage.value = 'Une erreur s\'est produite lors de la soumission du formulaire.';
        successMessage.value = '';
    } finally {
        loading.value = false;
    }
}
</script>

<template>
    <div class="max-w-3xl mx-auto p-6 border border-white rounded-lg shadow-md">
        <h1 class="text-3xl font-bold mb-6">Ajouter Absence</h1>

        <UForm :schema="schema" :state="form" class="space-y-4" @submit="onSubmit">
            <UFormGroup label="Motif" name="motif">
                <UInput v-model="form.motif" />
            </UFormGroup>

            <UFormGroup label="Date d'absence" name="dateAbsence">
                <UInput v-model="form.dateAbsence" type="date" required />
            </UFormGroup>

            <UFormGroup label="ID Contrat" name="idContrat">
                <UInput v-model="form.idContrat" type="number" required />
            </UFormGroup>

            <UButton type="submit" :disabled="!isFormValid" :loading="loading">
                {{ loading ? 'En cours...' : 'Soumettre' }}
            </UButton>

            <p v-if="errorMessage" class="text-red-500">{{ errorMessage }}</p>
            <p v-if="successMessage" class="text-green-500">{{ successMessage }}</p>
        </UForm>
    </div>
</template>