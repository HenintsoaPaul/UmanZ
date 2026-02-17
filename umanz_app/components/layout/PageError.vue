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
        <div class="bg-red-50 dark:bg-red-900/10 
            p-12 rounded-[2rem] border 
            border-red-100 dark:border-red-900/20 
            transition-colors duration-300">
            <UIcon name="i-heroicons-exclamation-triangle" 
                class="w-16 h-16 
                text-red-400 dark:text-red-500 
                mx-auto mb-4" />
            <h2 class="text-2xl font-bold text-slate-800 dark:text-slate-100 mb-2">{{ title }}</h2>
            <p class="text-slate-500 dark:text-slate-400 mb-8">{{ description }}</p>
            <UButton :to="to" variant="solid" color="primary" 
                icon="i-heroicons-arrow-left" 
                class="shadow-lg shadow-umanz-purple/20">
                Retour à l'accueil
            </UButton>
        </div>
    </div>
</template>