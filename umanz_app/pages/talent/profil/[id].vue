<script setup lang="ts">
import { ref, onMounted } from 'vue';
import { useRoute } from 'vue-router';
import axios from 'axios';
import { useRuntimeConfig } from '#imports'; // Ensure you have this import if you're using Nuxt.js
import type { Talent } from '~/types';

// State
const talent = ref<Talent | null>(null);
const profilePicture = ref('/default-profile.png');
const phone = ref('');
const address = ref('');
const bio = ref('');

// Get the route
const route = useRoute();

// Fetch talent data
const fetchTalent = async (id: number) => {
    try {
        const apiUrl: string = useRuntimeConfig().public.apiUrl as string;
        const response = await axios.get(`${apiUrl}/talents/${id}`);
        if (response.status === 200) {
            talent.value = response.data;
            profilePicture.value = response.data.profilePicture || '/default-profile.png';
            phone.value = response.data.phone || 'N/A';
            address.value = response.data.address || 'N/A';
            bio.value = response.data.bio || 'N/A';
        } else {
            console.error('Erreur lors de la récupération du talent');
        }
    } catch (error) {
        console.error('Erreur lors de la requête API:', error);
    }
};

// On component mount
onMounted(() => {
    const talentId = Number(route.params.id);
    if (!isNaN(talentId)) {
        fetchTalent(talentId);
    } else {
        console.error('Invalid talent ID');
    }
});
</script>

<template>
    <div class="profile">
        <div v-if="talent" class="profile-header">
            <img :src="profilePicture" alt="Profile Picture" class="profile-picture" />
            <h1 class="profile-name">{{ talent.nom }} {{ talent.prenom }}</h1>
        </div>
        <div v-if="talent" class="profile-details">
            <p><strong>Email:</strong> {{ talent.mail }}</p>
            <p><strong>Phone:</strong> {{ phone }}</p>
            <p><strong>Address:</strong> {{ address }}</p>
            <p><strong>Bio:</strong> {{ bio }}</p>
        </div>
        <div v-else>
            <p>Loading...</p>
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