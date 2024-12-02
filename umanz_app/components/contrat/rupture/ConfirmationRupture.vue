<script setup lang="ts">
import { ref } from 'vue';

const props = defineProps<{
    idContrat: number;
    datePrevu: Date
}>();

const ruptureDate = ref<string>('');
const selectedContract = ref<string>('');

const datePrevu = new Date(2024, 11, 12);

const manajaPreavis = computed(() => {
    const rupture = new Date(ruptureDate.value);

    if (ruptureDate.value) return rupture.getTime() >= datePrevu.getTime();
    return false;
})

const submitForm = () => {
    // Handle form submission logic here
    console.log('Date de Rupture:', ruptureDate.value);
    console.log('Contrat Sélectionné:', selectedContract.value);
};
</script>

<template>
    <div class="max-w-md mx-auto mt-10">
        <h1 class="text-2xl font-bold mb-6">Confirmation de Rupture de Contrat</h1>
        <form @submit.prevent="submitForm" class="space-y-4">
            <div>
                <label for="ruptureDate" class="block text-sm font-medium text-gray-700">Date de Rupture:</label>
                <input type="date" id="ruptureDate" v-model="ruptureDate" required
                    class="mt-1 block w-full border border-gray-300 rounded-md shadow-sm focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm" />
            </div>

            <button type="submit"
                class="inline-flex items-center px-4 py-2 border border-transparent text-sm font-medium rounded-md shadow-sm text-white bg-indigo-600 hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500">
                Confirmer
            </button>

            <div v-if="!manajaPreavis" class="text-red-500 text-sm mt-2">
                La date de rupture ne peut pas être antérieure à la date prévue.
            </div>
        </form>
    </div>
</template>