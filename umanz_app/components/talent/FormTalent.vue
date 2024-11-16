<script setup lang="ts">
import { computed } from 'vue';

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