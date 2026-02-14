<script setup lang="ts">
const props = defineProps({
    title: {
        type: String,
        default: 'Oups, une erreur s\'est produite.'
    },
    description: {
        type: String,
        default: 'Désolé, nous n\'avons pas pu charger les informations de cette page.'
    },
    to: {
        type: String,
        default: '/'
    }
});
</script>

<template>
    <div class="max-w-3xl mx-auto px-4 pt-20 text-center">
        <div class="bg-red-50 p-12 rounded-[2rem] border border-red-100">
            <UIcon name="i-heroicons-exclamation-triangle" class="w-16 h-16 text-red-400 mx-auto mb-4" />
            <h2 class="text-2xl font-bold text-slate-800 mb-2">{{ title }}</h2>
            <p class="text-slate-500 mb-8">{{ description }}</p>
            <UButton :to="to" variant="solid" color="primary" icon="i-heroicons-arrow-left">
                Retour à l'accueil
            </UButton>
        </div>
    </div>
</template>