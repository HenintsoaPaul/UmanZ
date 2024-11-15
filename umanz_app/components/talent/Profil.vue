<script setup lang="ts">
import type { Talent } from '~/types';

const props = defineProps<{
    talent: Talent;
    photo: string,
}>();
</script>

<template>
    <div class="profile-header">
        <img :src="photo" alt="Profile Picture" class="profile-picture" />
        <h1 class="profile-name">{{ talent.nom }} {{ talent.prenom }}</h1>
    </div>
    <div class="profile-details">
        <p><strong>Email:</strong> {{ talent.mail }}</p>
    </div>
</template>

<style scoped>
.profile {
    max-width: 600px;
    margin: 0 auto;
    padding: 20px;
    border: 1px solid #ccc;
    border-radius: 10px;
}

.profile-header {
    display: flex;
    align-items: center;
    margin-bottom: 20px;
}

.profile-picture {
    width: 100px;
    height: 100px;
    border-radius: 50%;
    margin-right: 20px;
}

.profile-name {
    font-size: 24px;
    font-weight: bold;
}

.profile-details p {
    margin: 5px 0;
}
</style>