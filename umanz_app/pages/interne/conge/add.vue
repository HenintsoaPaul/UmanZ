<script setup lang="ts">
import { z } from 'zod'
import { reactive, ref, computed } from 'vue'
import type { FormConge } from '~/types/dto';
import type { TypeConge, TypeJustificatif } from '~/types/interne';

const idUser = computed(() => localStorage.getItem("idUser"));
const { demanderCongerFn } = useCongeActions();

const apiUrl = useRuntimeConfig().public.apiUrl as string;
const { data: typeConges } = useFetch<TypeConge[]>(`${apiUrl}/type_conges`);
const { data: typeJustificatifs } = useFetch<TypeJustificatif[]>(`${apiUrl}/type_justificatifs`);

const schema = z.object({
    nbJour: z.number().min(1, "Le nombre de jour de conge minimum est 1 jour."),
    dateDebut: z.string().date('Veuillez specifier la date de debut.'),
    idTypeConge: z.number().min(1, 'Veuillez specifier le type du conge.')
});

const form = reactive<FormConge>({
    dateDebut: '',
    nbJour: 1,
    motif: '',
    idTypeConge: 0,
    justificatif: {
        imageJustificatif: '',
        dateJustificatif: '',
        idTypeJustificatif: 0
    }
});

const isFormValid = computed(() => {
    return schema.safeParse({ ...form }).success;
});

const loading = ref(false);
const formError = ref('');

async function onSubmit(event: Event) {
    event.preventDefault();
    if (!isFormValid.value) {
        formError.value = 'Veuillez remplir correctement tous les champs.';
        return;
    }

    loading.value = true;
    try {
        await demanderCongerFn(form, Number(idUser.value), apiUrl);
    } catch (error) {
        formError.value = 'Erreur lors de l\'envoi du formulaire.';
    } finally {
        loading.value = false;
    }
}
</script>

<template>
    <div class="container mx-auto">
        <h1 class="text-3xl font-bold mb-6 text-center">Demander un Congé</h1>
        <form @submit="onSubmit" class="max-w-lg mx-auto">
            <div class="mb-4">
                <label for="motif" class="block text-gray-700">Motif</label>
                <input v-model="form.motif" type="text" id="motif"
                    class="w-full p-2 border border-gray-300 rounded mt-1" />
            </div>

            <div class="mb-4 flex gap-5">
                <div class="w-1/2">
                    <label for="nb_jour" class="block text-gray-700">Nombre de jour</label>
                    <input v-model="form.nbJour" type="number" min="1" id="nb_jour"
                        class="w-full p-2 border border-gray-300 rounded mt-1" />
                </div>
                <div class="w-1/2">
                    <label for="date_debut" class="block text-gray-700">Date debut</label>
                    <input v-model="form.dateDebut" type="date" id="date_debut"
                        class="w-full p-2 border border-gray-300 rounded mt-1" />
                </div>
            </div>

            <div class="mb-4">
                <label for="type_conge" class="block text-gray-700">Type de conge</label>
                <select v-model="form.idTypeConge" id="type_conge"
                    class="w-full p-2 border border-gray-300 rounded mt-1">
                    <option value="" disabled>Choisir un type conge</option>
                    <option v-for="tconge in typeConges" :key="tconge.idTypeConge" :value="tconge.idTypeConge">
                        {{ tconge.nomTypeConge }}
                    </option>
                </select>
            </div>

            <div class="border border-white px-4 py-4 mb-4">
                <h3>Justificatif en cas de maladie</h3>

                <div class="mb-4 flex gap-4">
                    <div class="w-1/2">
                        <label for="date_debut" class="block text-gray-700">Date prise du justificatif</label>
                        <input v-model="form.justificatif.dateJustificatif" type="date" id="date_debut"
                            class="w-full p-2 border border-gray-300 rounded mt-1" />
                    </div>
                    <div class="w-1/2">
                        <label for="date_debut" class="block text-gray-700">Photo du justificatif</label>
                        <input v-model="form.justificatif.imageJustificatif" type="string" id="date_debut"
                            class="w-full p-2 border border-gray-300 rounded mt-1" />
                    </div>
                </div>

                <div class="mb-4">
                    <label for="type_justificatif" class="block text-gray-700">Type Justificatif</label>
                    <select v-model="form.justificatif.idTypeJustificatif" id="type_justificatif"
                        class="w-full p-2 border border-gray-300 rounded mt-1">
                        <option value="" disabled>Choisir un type justificatif</option>
                        <option v-for="tjustif in typeJustificatifs" :key="tjustif.idTypeJustificatif"
                            :value="tjustif.idTypeJustificatif">
                            {{ tjustif.nomTypeJustificatif }}
                        </option>
                    </select>
                </div>
            </div>

            <p v-if="formError" class="text-red-500 mb-4">{{ formError }}</p>

            <button type="submit" :disabled="loading" class="w-full bg-blue-500 text-white p-2 rounded">
                {{ loading ? 'Envoi en cours...' : 'Ajouter' }}
            </button>
        </form>
    </div>
</template>