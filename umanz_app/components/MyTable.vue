<script setup lang="ts">
import { ref, watch, onMounted } from 'vue'
import axios from 'axios'

interface Column {
    key: string
    label: string
    sortable?: boolean
}

interface RowData {
    [key: string]: any
}

interface TableProps {
    columns: Column[]
    rows: RowData[]
    apiEndpoint?: string
    onRowClick?: (row: RowData) => void
    onExpandChange?: (expandedRows: { openedRows: string[], row: RowData }) => void
}

const props = defineProps<TableProps>()

const emit = defineEmits(['update:expand'])

const headers = computed(() => props.columns)
const lignes = ref(props.rows)

const expand = ref({
    openedRows: [],
    row: {}
})

const loadRowsFromApi = async () => {
    if (!props.apiEndpoint) return

    try {
        const response = await axios.get(props.apiEndpoint)

        if (response.status === 200 && Array.isArray(response.data)) {
            lignes.value = response.data
        } else {
            console.error('Erreur lors de la récupération des données', response.data)
        }
    } catch (error) {
        console.error('Erreur lors de la requête API:', error)
    }
}

onMounted(() => {
    loadRowsFromApi()
})

watch(() => props.rows, () => {
    lignes.value = props.rows
}, { deep: true })

watch(expand, (newExpand) => {
    emit('update:expand', newExpand)
})
</script>

<template>
    <div class="container mx-auto">
        <h1 class="text-3xl font-bold mb-6 text-center">{{ headers[0].label }}</h1>

        <UTable :columns="headers" :rows="lignes" v-model:expand="expand"
            class="w-full shadow-md rounded-lg overflow-hidden">
            <template #expand="{ row }">
                <div class="p-4">
                    <h3 class="text-xl font-semibold mb-2">Description:</h3>
                    <p class="text-gray-700">{{ row[headers[0].key] }}</p>

                    <button @click="props.onRowClick ? props.onRowClick(row) : null">
                        Action
                    </button>
                </div>
            </template>
        </UTable>
    </div>
</template>
