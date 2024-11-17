<script setup lang="ts">
import type { Entretien } from '~/types';

const router = useRouter();
const apiUrl = useRuntimeConfig().public.apiUrl as string;
const { data: entretiens, refresh: refreshEntretiens } = useFetch<Entretien[]>(`${apiUrl}/entretien/etat/${5}`);

const { q, filteredRows: filteredEntretiens } = useFilteredRows(entretiens);
const expand = ref({
    openedRows: [],
    row: {}
});

const { headers, validerEntretien, refuserEntretien } = useEntretienActions();
const validerFn = async (entretien: Entretien) => {
    await validerEntretien(entretien, apiUrl, refreshEntretiens);
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
        <h1 class="text-3xl font-bold mb-4 text-center">Liste des Deuxiemes Entretiens</h1>

        <div class="flex justify-center mb-4">
            <UInput v-model="q" placeholder="Filtrer les entretiens..." class="w-full max-w-md px-4 py-2 rounded-lg" />
        </div>

        <template v-if="entretiens">
            <UTable :columns="headers" :rows="filteredEntretiens ?? []" v-model:expand="expand">
                <template #expand="{ row }">
                    <EntretienExpend :entretien="row" @valider="validerFn" @refuser="refuserFn"
                        @redirect="redirectFn" />
                </template>
            </UTable>
        </template>
    </div>
</template>
