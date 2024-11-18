<script setup lang="ts">
import { computed } from 'vue';
import type { NiveauDiplome, NiveauLangue } from '~/types';

const props = defineProps({
    form: {
        type: Object,
        required: true,
    },
    schema: {
        type: Object,
        required: true,
    },
    loading: {
        type: Boolean,
        default: false,
    },
    niveauLangues: {
        type: Array as () => NiveauLangue[],
        required: true,
    },
    niveauDiplomes: {
        type: Array as () => NiveauDiplome[],
        required: true,
    },
    errorMessage: {
        type: String,
        default: '',
    },
    successMessage: {
        type: String,
        default: '',
    },
});

const { form, schema, loading } = props;

const emit = defineEmits(["submit"]);

const isFormValid = computed(() => {
    return schema.safeParse({ ...form }).success;
});

const submit = () => {
    emit("submit");
};
</script>

<template>
    <UForm :schema="schema" :state="form" class="space-y-4" @submit="submit">
        <ClientOnly>
            <!-- Profil -->
            <UFormGroup label="Nom" name="nom">
                <UInput id="nom" v-model="form.nom" required />
            </UFormGroup>

            <UFormGroup label="Prenom" name="prenom">
                <UInput id="prenom" v-model="form.prenom" required />
            </UFormGroup>

            <UFormGroup label="Email" name="email">
                <UInput id="email" v-model="form.mail" type="email" required />
            </UFormGroup>

            <UFormGroup label="Mot de passe" name="password">
                <UInput id="password" v-model="form.password" type="password" required />
            </UFormGroup>

            <UFormGroup label="Admin" name="isAdmin">
                <UCheckbox id="isAdmin" v-model="form.isAdmin" />
            </UFormGroup>
        </ClientOnly>

        <hr>
        <div class="flex gap-4 w-full">
            <div class="w-1/2" v-if="props.niveauLangues">
                <ListInputLangue title="Langues" :niveau-langues="props.niveauLangues"
                    :list-langue-avec-niveau="form.languesAvecNiveaux" />
            </div>
            <div v-else>
                Loading Langues...
            </div>
            <div class="w-1/2" v-if="props.niveauDiplomes">
                <ListInputDiplome title="Diplomes" :niveau-diplomes="props.niveauDiplomes"
                    :list-diplome-avec-niveau="form.diplomesAvecNiveaux" />
            </div>
            <div v-else>
                Loading Diplomes...
            </div>
        </div>
        <hr>

        <!-- Competences et Experiences -->
        <div class="grid grid-cols-2 gap-4">
            <div v-if="form.competences.length > 0" class="form-group">
                <ListInputCompetence title="Competences" :competences="form.competences" />
            </div>
            <div v-else>
                Loading Competences...
            </div>

            <div v-if="form.experiences.length > 0" class="form-group">
                <ListInputExperience title="Experiences" :experiences="form.experiences" />
            </div>
            <div v-else>
                Loading Experiences...
            </div>
        </div>

        <UButton type="submit" :disabled="!isFormValid" :loading="loading">
            {{ loading ? 'En cours...' : 'Soumettre' }}
        </UButton>

        <p v-if="props.errorMessage" class="text-red-500">{{ props.errorMessage }}</p>
        <p v-if="props.successMessage" class="text-green-500">{{ props.successMessage }}</p>
    </UForm>
</template>