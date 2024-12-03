<template>
    <div class="min-h-screen flex items-center justify-center font-mono bg-gray-100">
      <div class="border border-gray-300 bg-white p-8 rounded-lg shadow-md w-full max-w-md text-gray-700">
        <h2 class="text-2xl font-bold mb-6 text-center">Envoyer un Email</h2>
        <UForm class="space-y-4" @submit="handleSubmit">
          <UFormGroup label="Motif" name="motif">
            <UInput
              id="motif"
              v-model="formData.motif"
              placeholder="Entrez le motif"
              class="w-full px-4 py-2 border rounded-lg focus:ring-2 focus:ring-blue-500 focus:outline-none"
              required
            />
          </UFormGroup>
  
          <UFormGroup label="Date" name="date">
            <UInput
              id="date"
              v-model="formData.date"
              type="date"
              class="w-full px-4 py-2 border rounded-lg focus:ring-2 focus:ring-blue-500 focus:outline-none"
              required
            />
          </UFormGroup>
  
          <div class="w-full flex justify-center">
            <UButton
              type="submit"
              class="w-1/2 bg-blue-500 text-white py-2 rounded-lg hover:bg-blue-600 transition duration-300"
            >
              Valider
            </UButton>
          </div>
        </UForm>
      </div>
    </div>
  </template>
  
  <script>
  import axios from "axios";
  
  export default {
    data() {
      return {
        formData: {
          motif: "",
          date: "",
        },
      };
    },
    methods: {
      async handleSubmit() {
        try {
          const response = await axios.post(
            "http://localhost:911/talents/ruptureEmail",
            this.formData
          );
          alert("Email envoyé avec succès : " + response.data);
  
          this.formData.motif = "";
          this.formData.date = "";
  
          this.$router.push("/interne/rupture/rupturValidation");
        } catch (error) {
          console.error("Erreur lors de l'envoi de l'email:", error);
          alert("Échec de l'envoi de l'email.");
        }
      },
    },
  };
  </script>
  
  <style scoped>
  body {
    font-family: "Inter", sans-serif;
  }
  </style>
  