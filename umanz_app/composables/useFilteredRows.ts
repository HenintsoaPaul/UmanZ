import { ref, computed } from 'vue';

interface Row {
    [key: string]: any;
}

export function useFilteredRows(rows: Ref<Row[] | null>) {
    const q = ref('');

    const filteredRows = computed(() => {
        if (!q.value) {
            return rows.value;
        }

        return rows.value?.filter((row) => {
            return Object.values(row).some((value) => {
                return String(value).toLowerCase().includes(q.value.toLowerCase());
            });
        });
    });

    return {
        q,
        filteredRows
    };
}