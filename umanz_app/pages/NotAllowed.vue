<script setup lang="ts">
definePageMeta({
    layout: 'default'
});
</script>

<template>
    <div class="not-allowed w-full flex justify-center align-center">
        <div id="ct" class="text-center mt-12">
            <h1 class="text-4xl font-bold mb-4">Vous n'êtes pas autorisé à voir ce contenu</h1>
            <router-link to="/">
                <button class="mt-5 px-5 py-2 bg-blue-500 text-white rounded hover:bg-blue-700 mr-4">
                    Retour à l'accueil
                </button>
            </router-link>
        </div>
    </div>
</template>
