<script setup lang="ts">
import { ref, computed } from 'vue';
import { useRoute } from 'vue-router';
import type { Conge } from '~/types/interne/conge';
import CalendarConge from '~/components/conge/CalendarConge.vue';
import SoldeConge from '~/components/conge/SoldeConge.vue';

const route = useRoute();
const apiUrl = useRuntimeConfig().public.apiUrl as string;
const idContrat = computed(() => route.params.id);

const { data: conges, error: congesError } = useFetch<Conge[]>(`${apiUrl}/conges/taken-by/${idContrat.value}`);

// ==============
const headers = [
    { key: 'idConge', label: 'ID', sortable: true },
    { key: 'dateDebut', label: 'Date Debut', sortable: true },
    { key: 'dateFin', label: 'Date Fin', sortable: true },
    { key: 'contrat.poste.nomPoste', label: 'Poste' },
    { key: 'typeConge.nomTypeConge', label: 'Type' },
];

const { q, filteredRows: filteredConges } = useFilteredRows(conges);
const expand = ref({
    openedRows: [],
    row: {}
});
</script>

<template>
    <div class="container mx-auto">

        <div v-if="conges">
            <h1 class="text-3xl font-bold mb-4 text-center">Liste des Congés Pris par le contrat {{ idContrat }}</h1>

            <div class="flex gap-4">
                <div class="w-1/4">
                    <client-only>
                        <CalendarConge :conges="conges" />
                    </client-only>
                </div>
                <div class="w-3/4">
                    <div class="flex justify-center mb-4">
                        <UInput v-model="q" placeholder="Filtrer les congés..."
                            class="w-full max-w-md px-4 py-2 rounded-lg" />
                    </div>

                    <UTable :columns="headers" :rows="filteredConges ?? []" />
                </div>
            </div>
        </div>
        <div v-else>
            Loading Details...
        </div>

        <div v-if="congesError" class="text-red-500">
            Erreur lors du chargement des conges: {{ congesError.message }}
        </div>
    </div>
</template>
