<script setup lang="ts">
import axios from "axios";

const route = useRoute();
const idContrat = computed(() => route.params.id);

const router = useRouter();

const formData = {
  motif: "",
  date: "",
}

const apiUrl = useRuntimeConfig().public.apiUrl as string;

async function handleSubmit() {
  const payLoad = {
    idContrat: idContrat.value,
    motif: formData.motif,
    date: formData.date
  };

  console.log(payLoad);


  try {
    const response = await axios.post(
      `${apiUrl}/ruptures/renvoi`,
      payLoad
    );
    alert("Email envoyé avec succès : " + response.data);

    formData.motif = "";
    formData.date = "";

    await router.push("/interne/rupture/rupturValidation");
  } catch (error) {
    console.error("Erreur lors de l'envoi de l'email:", error);
    alert("Échec de l'envoi de l'email.");
  }
}
</script>

<template>
  <div class="content flex items-center justify-center font-mono">
    <div class="border border-slate-50 p-8 rounded-lg shadow-md w-full max-w-md text-slate-500">
      <h2 class="text-2xl font-bold mb-6 text-center">Licenciement</h2>
      <UForm :state="formData" class="space-y-4" @submit="handleSubmit">
        <UFormGroup label="Motif" name="motif">
          <UInput id="motif" v-model="formData.motif" placeholder="Entrez le motif"
            class="w-full focus:outline-none focus:ring-2 focus:ring-blue-500" required />
        </UFormGroup>

        <UFormGroup label="Date" name="date">
          <UInput id="date" v-model="formData.date" type="date"
            class="w-full focus:outline-none focus:ring-2 focus:ring-blue-500" required />
        </UFormGroup>

        <div class="w-full flex justify-center">
          <UButton type="submit"
            class="w-1/2 bg-blue-500 text-white py-2 rounded-lg hover:bg-blue-600 transition duration-300 flex justify-center">
            Licencier
          </UButton>
        </div>
      </UForm>
    </div>
  </div>
</template>

<style scoped>
body {
  font-family: "Inter", sans-serif;
}

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