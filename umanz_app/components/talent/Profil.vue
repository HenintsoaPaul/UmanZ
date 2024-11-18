<script setup lang="ts">
import type { Talent } from '~/types';

const props = defineProps<{
    talent: Talent;
}>();
</script>

<template>
    <div class="profile">
        <div class="profile-header">
            <h1 class="profile-name">{{ talent.nom }} {{ talent.prenom }}</h1>
        </div>
        <div class="profile-details">
            <p><strong>Email:</strong> {{ talent.mail }}</p>
            <p><strong>Admin:</strong> {{ talent.isAdmin ? 'Yes' : 'No' }}</p>
        </div>
    </div>
</template>

<style scoped>
.profile {
    max-width: 600px;
    margin: 0 auto;
    padding: 20px;
    border: 1px solid #ccc;
    border-radius: 10px;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
    background-color: #fff;
}

.profile-header {
    display: flex;
    align-items: center;
    margin-bottom: 20px;
}

.profile-name {
    font-size: 24px;
    font-weight: bold;
    color: #333;
}

.profile-details p {
    margin: 5px 0;
    font-size: 16px;
    color: #555;
}

.profile-details p strong {
    color: #000;
}
</style>
