<script setup lang="ts">
import type { Entretien } from '~/types';

definePageMeta({
    middleware: ['auth-is-admin']
});

const router = useRouter();
const apiUrl = useRuntimeConfig().public.apiUrl as string;
const { data: entretiens, refresh: refreshEntretiens } = useFetch<Entretien[]>(`${apiUrl}/entretien/etat/${6}`);

const { q, filteredRows: filteredEntretiens } = useFilteredRows(entretiens);
const expand = ref({
    openedRows: [],
    row: {}
});

const { headers, refuserEntretien } = useEntretienActions();
const contratFn = async (entretien: Entretien) => {
    router.push(`/interne/contrat/add?entretien=${entretien.idEntretien}`);
}
const refuserFn = async (idEntretien: number) => {
    await refuserEntretien(idEntretien, apiUrl, refreshEntretiens);
}
const redirectFn = (idTalent: number) => {
    router.push(`/talent/${idTalent}`);
}
</script>

<template>
    <div class="container mx-auto">
        <h1 class="text-3xl font-bold mb-4 text-center">Liste des Deuxiemes Entretiens Valides</h1>

        <div class="flex justify-center mb-4">
            <UInput v-model="q" placeholder="Filtrer les entretiens..." class="w-full max-w-md px-4 py-2 rounded-lg" />
        </div>

        <template v-if="entretiens">
            <UTable :columns="headers" :rows="filteredEntretiens ?? []" v-model:expand="expand">
                <template #expand="{ row }">
                    <EntretienExpend :entretien="row" @refuser="refuserFn" @redirect="redirectFn"
                        @contrat="contratFn" />
                </template>
            </UTable>
        </template>
    </div>
</template>
