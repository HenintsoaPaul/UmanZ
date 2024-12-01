<script setup lang="ts">
definePageMeta({
    middleware: 'auth-is-emp'
});

import { z } from 'zod'
import { reactive, ref, computed } from 'vue'
import type { FormConge } from '~/types/interne/dto';
import type { TypeConge } from '~/types/interne/conge';

const idContrat = computed(() => localStorage.getItem("umanz-idContrat"));
const { demanderCongerFn, validateJustificatif } = useCongeActions();

const apiUrl = useRuntimeConfig().public.apiUrl as string;
const { data: typeConges } = useFetch<TypeConge[]>(`${apiUrl}/type_conges`);

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
    }
});

const validateForm = computed(() => {
    return schema.safeParse({ ...form });
});

const loading = ref(false);
const formError = ref('');

async function onSubmit(event: Event) {
    event.preventDefault();
    const validationResult = validateForm.value;
    if (!validationResult.success) {
        formError.value = validationResult.error.errors.map(err => err.message).join('\n');
        return;
    }

    loading.value = true;
    try {
        const idTypeConge = form.idTypeConge;
        if (idTypeConge === 2 || idTypeConge === 4) {
            let err = validateJustificatif(form.justificatif);
            if (err.length > 0) throw new Error(err);
        }

        await demanderCongerFn(toRaw(form), Number(idContrat.value), apiUrl);
    } catch (error) {
        formError.value = `Erreur lors de l'envoi du formulaire: ${(error as Error)?.message}`;
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
            </div>

            <p v-if="formError" class="text-red-500 mb-4">{{ formError }}</p>

            <button type="submit" :disabled="loading" class="w-full bg-blue-500 text-white p-2 rounded">
                {{ loading ? 'Envoi en cours...' : 'Ajouter' }}
            </button>
        </form>
    </div>
</template>