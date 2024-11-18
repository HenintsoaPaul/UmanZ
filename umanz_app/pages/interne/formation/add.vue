<script setup lang="ts">
import { z } from 'zod'
import type { FormSubmitEvent } from '#ui/types'
import type { Competence, CompetenceAnnonce } from '~/types';

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
    competences: [] as CompetenceAnnonce[],
    error: ''
});

const isFormValid = computed(() => {
    return schema.safeParse({ ...form }).success && validateDates();
});

const apiUrl = useRuntimeConfig().public.apiUrl as string;
const { data: competences, refresh: refreshCompetences } = useFetch<Competence[]>(`${apiUrl}/competences`);

const errorMessage = ref('');
const successMessage = ref('');
const loading = ref(false);

async function onSubmit(event: FormSubmitEvent<Schema>) {
    if (!schema.safeParse(event.data).success) {
        form.error = 'Une erreur s\'est produite lors de la soumission du formulaire.';
        return;
    }

    loading.value = true;
    try {
        const formKdj = toRaw({
            ...form,
            competences: form.competences.filter(cp => cp.point > 0).map(cp => toRaw(cp)),
        });
        console.log(toRaw(formKdj));

        const response = await $fetch(`${apiUrl}/formations`, {
            method: 'POST',
            body: toRaw(formKdj)
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

onMounted(async () => {
    await refreshCompetences();
    if (competences.value) {
        form.competences = competences.value.map(cp => ({
            point: 0,
            competence: toRaw(cp)
        }));
    }
});
</script>

<template>
    <div class="max-w-3xl mx-auto p-6 border border-white rounded-lg shadow-md">
        <h1 class="text-3xl font-bold mb-6">Ajouter Formation</h1>

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

            <hr>

            <h1 class="text-xl font-bold mb-6">Competences</h1>
            <ListInputCompetence :competences="form.competences" />

            <UButton type="submit" :disabled="!isFormValid" :loading="loading">
                {{ loading ? 'En cours...' : 'Soumettre' }}
            </UButton>

            <p v-if="errorMessage" class="text-red-500">{{ errorMessage }}</p>
            <p v-if="successMessage" class="text-green-500">{{ successMessage }}</p>
        </UForm>
    </div>
</template>