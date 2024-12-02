<script setup lang="ts">
    import { ref } from '#imports'
    const date = ref(new Date())

    const attrs = ref([
        {
            key: 'today',
            highlight: {
                color: 'green',
                fillMode: 'solid'
            },
            dates: new Date(2024, 11, 11)
        },
    ])
</script>

<template>
    <div>
        <client-only>
            <h2>Calendar</h2>
            <VCalendar v-model="date" :attributes="attrs" />
        </client-only>
    </div>
</template>