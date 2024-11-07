<script setup lang="ts">
definePageMeta({
    pageTransition: {
        name: 'rotate'
    }
});
</script>

<template>
    <section>
        <p>This page will be displayed at the /about route.</p>

        <NuxtLink to="/">home</NuxtLink>
    </section>
</template>