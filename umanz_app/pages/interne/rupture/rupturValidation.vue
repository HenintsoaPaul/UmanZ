<script>
import axios from 'axios'; // Assurez-vous d'importer axios

export default {
    data() {
        return {
            email: '',
            name: '',
            message: '',
            success: false
        };
    },
    methods: {
        async sendEmail() {
            try {
                // Vérifiez les données avant l'envoi
                if (!this.email || !this.name) {
                    this.message = 'Veuillez remplir tous les champs';
                    this.success = false;
                    return;
                }

                console.log('Données envoyées:', { email: this.email, name: this.name });

                // Envoi de la requête POST
                const response = await axios.post('http://localhost:911/ruptures/sendDossier', {
                    email: this.email,
                    name: this.name
                }, {
                    headers: {
                        'Content-Type': 'application/json' // Assurez-vous que l'en-tête est bien défini
                    }
                });

                // Vérifiez la réponse
                if (response.data && response.data.message) {
                    this.message = response.data.message;
                    this.success = true;
                } else {
                    this.message = 'Aucune donnée reçue';
                    this.success = false;
                }
            } catch (error) {
                console.error('Erreur lors de l\'envoi:', error); // Afficher l'erreur dans la console

                // Gérer les erreurs 400 et autres erreurs spécifiques
                if (error.response) {
                    // Vérifier si l'erreur provient d'un code HTTP 400
                    if (error.response.status === 400) {
                        this.message = 'Erreur 400 : Requête incorrecte. Vérifiez vos données.';
                    } else {
                        this.message = 'Erreur lors de l\'envoi de l\'email: ' + (error.response.data.message || error.response.data);
                    }
                } else {
                    this.message = 'Erreur lors de l\'envoi de l\'email: ' + error.message;
                }

                this.success = false;
            }
        }
    }
};
</script>

<template>
  <div class="content flex items-center justify-center">
    <div class="border border-slate-50 p-8 rounded-lg shadow-md w-full max-w-md text-slate-500">
      <h2 class="text-2xl font-semibold text-center mb-6">Envoyer un email avec pièces jointes</h2>

      <form @submit.prevent="sendEmail">

          <!-- Success/Error Message -->
          <div v-if="message" class="mt-6 text-center">
              <p class="font-medium" :class="{'text-green-500': success, 'text-red-500': !success}">
                  {{ message }}
              </p>
          </div>

        <!-- Email Input -->
        <div class="mb-4">
          <label for="email" class="block text-sm font-medium">Email</label>
          <input
            v-model="email"
            type="email"
            id="email"
            class="w-full px-4 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-400"
            required
            placeholder="Votre email"
          />
        </div>

        <!-- Name Input -->
        <div class="mb-4">
          <label for="name" class="block text-sm font-medium">Nom</label>
          <input
            v-model="name"
            type="text"
            id="name"
            class="w-full px-4 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-400"
            required
            placeholder="Nom de la personne"
          />
        </div>

        <!-- Submit Button -->
        <button
          type="submit"
          class="w-full bg-blue-500 text-white py-2 px-4 rounded-md hover:bg-blue-600 focus:outline-none focus:ring-2 focus:ring-blue-400"
        >
          Envoyer l'email
        </button>
      </form>

    </div>
  </div>
</template>

<style scoped>
.content {
    --header-content-height: 40px;
    --header-padding: 16px;
    --header-border-width: 1px;
    --header-margin: 20px;
    --header-height: calc(var(--header-content-height) + var(--header-padding) * 2 + var(--header-border-width) + var(--header-margin));
    --content-height: calc(100vh - var(--header-height));
    height: max(var(--content-height), 600px);
}
</style>
