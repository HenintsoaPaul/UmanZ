<script setup lang="ts">
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import { z } from 'zod'
import type { FormSubmitEvent } from '#ui/types'

// Interface pour les utilisateurs
interface User {
  id: string;
  email: string;
  role: string;
}

// Mock API
const mockApi = {
  login: async (credentials: { email: string; password: string }) => {
    // Simuler une réponse réussie
    return {
      success: true,
      user: {
        id: '123',
        email: credentials.email,
        role: 'user'
      }
    };
  }
};

// Définir le schéma
const schema = z.object({
  email: z.string().email('Invalid email'),
  password: z.string().min(8, 'Must be at least 8 characters')
});

type Schema = z.output<typeof schema>;

// État réactif
const state = reactive({
  email: '',
  password: ''
});

// Instance Router
const router = useRouter();

// Informations de l'utilisateur
const userInfo: Ref<User | null> = ref(null);

// Fonction de simulation d'appel API
function simulateApiCall(endpoint: string, data: any) {
  return Promise.resolve({
    status: 200,
    data: {
      message: `Simulé ${endpoint} call`,
      ...data
    }
  });
}

// Fonction d'authentification
async function authenticateUser(credentials: { email: string; password: string }): Promise<boolean> {
  try {
    // Simuler un appel API
    const response = await simulateApiCall('/api/auth/login', credentials);

    if (!response.status) {
      throw new Error('Échec de authentification');
    }

    const userData = response.data.user;
    userInfo.value = userData;
    return true;
  } catch (error) {
    console.error('Erreur de connexion:', error);
    return false;
  }
}

// Vérifier la validité du formulaire
function validateForm(data: Schema): boolean {
  return schema.safeParse(data).success;
}

// Gestionnaire de soumission du formulaire
async function onSubmit(event: FormSubmitEvent<Schema>) {
  const isValid = validateForm(event.data);

  if (isValid && await authenticateUser(event.data)) {
    handleSuccessfulLogin();
  }
}

// Gérer une connexion réussie
function handleSuccessfulLogin() {
  // Identifier l'utilisateur
  const userId = userInfo.value?.id || '';
  const userEmail = userInfo.value?.email || '';

  // Définir les permissions et rôles
  setPermissionsAndRoles(userId, userEmail);

  // Rediriger vers la page d'accueil ou le tableau de bord
  router.push('/');
}

// Fonction pour définir les permissions et rôles
function setPermissionsAndRoles(userId: string, userEmail: string) {
  // Utilisation d'un objet statique pour simplifier
  const userRoles: Record<string, string[]> = {
    admin: ['createPost', 'editPost', 'deletePost'],
    moderator: ['editPost', 'deletePost'],
    user: []
  };

  const myRole: string = getUserRole(userEmail);
  const userPermissions: string[] = userRoles[myRole] || [];

  // Stocker les informations de l'utilisateur et les permissions dans un objet réactif
  const userStore = {
    userId,
    userEmail,
    userRole: myRole,
    userPermissions
  };

  // Loguer les informations de l'utilisateur
  console.log("Informations de l'utilisateur:", userStore);
}

// Fonction pour obtenir le rôle de l'utilisateur basé sur l'email
function getUserRole(email: string): string {
  // Cette fonction est un exemple simplifié
  const roles = ['admin@example.com', 'moderator@example.com'];
  return roles.includes(email)
    ? 'admin'
    : (roles.includes(email) ? 'moderator' : 'user');
}
</script>

<template>
  <UForm :schema="schema" :state="state" class="space-y-4" @submit="onSubmit">
    <UFormGroup label="Email" name="email">
      <UInput v-model="state.email" />
    </UFormGroup>

    <UFormGroup label="Mot de passe" name="password">
      <UInput v-model="state.password" type="password" />
    </UFormGroup>

    <UButton type="submit">
      Soumettre
    </UButton>
  </UForm>
</template>