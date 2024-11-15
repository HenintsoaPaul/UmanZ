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
    <UForm :schema="schema" :state="form" class="space-y-4" @submit.prevent="submit">
        <UFormGroup label="Nom" name="nom">
            <UInput v-model="form.nom" required />
        </UFormGroup>

        <UFormGroup label="Prenom" name="prenom">
            <UInput v-model="form.prenom" required />
        </UFormGroup>

        <UFormGroup label="Email" name="email">
            <UInput v-model="form.mail" type="email" required />
        </UFormGroup>

        <UFormGroup label="Mot de passe" name="password">
            <UInput v-model="form.password" type="password" required />
        </UFormGroup>

        <UFormGroup label="Admin" name="isAdmin">
            <UCheckbox v-model="form.isAdmin" />
        </UFormGroup>

        <UButton type="submit" :disabled="!isFormValid" :loading="loading">
            {{ loading ? 'En cours...' : 'Soumettre' }}
        </UButton>

        <p v-if="form.error" class="text-red-500">{{ form.error }}</p>
        <p v-if="form.successMessage" class="text-green-500">{{ form.successMessage }}</p>
    </UForm>
</template>