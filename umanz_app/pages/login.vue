<script setup lang="ts">
import { useRouter } from 'vue-router'
import { z } from 'zod'
import type { FormSubmitEvent } from '#ui/types'

// Définir le schéma
const schema = z.object({
  email: z.string().email('Invalid email'),
  password: z.string().min(8, 'Must be at least 8 characters')
});
type Schema = z.output<typeof schema>;

const formState = reactive({
  email: '',
  password: '',
  error: ''
});

const router = useRouter();
const { authenticate } = useAuth();

async function onSubmit(event: FormSubmitEvent<Schema>) {
  const isValid = schema.safeParse(event.data).success;

  if (isValid) {
    const userEmail = formState.email;
    const userPassword = formState.password;

    const apiUrl = useRuntimeConfig().public.apiUrl;
    const user = await authenticate(userEmail, userPassword, `${apiUrl}/talent/users`);

    if (typeof (user) != "string" && user != null) {
      localStorage.setItem('idUser', user.idTalent.toString());
      localStorage.setItem('isAdmin', user.isAdmin.toString());
      router.push('/'); 
    } else {
      formState.error = 'Utilisateur inconnu'
    }
  }
}
</script>

<template>
  <UForm :schema="schema" :state="formState" class="space-y-4" @submit="onSubmit">
    <UFormGroup label="Email" name="email">
      <UInput v-model="formState.email" />
    </UFormGroup>

    <UFormGroup label="Mot de passe" name="password">
      <UInput v-model="formState.password" type="password" />
    </UFormGroup>

    <UButton type="submit">
      Soumettre
    </UButton>

    <p v-if="formState.error" class="text-red-500">{{ formState.error }}</p>
  </UForm>
</template>