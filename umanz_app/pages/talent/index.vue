<template>
    <div class="profile">
        <div class="profile-header">
            <img :src="profilePicture" alt="Profile Picture" class="profile-picture" />
            <h1 class="profile-name">{{ name }}</h1>
        </div>
        <div class="profile-details">
            <p><strong>Email:</strong> {{ email }}</p>
            <p><strong>Phone:</strong> {{ phone }}</p>
            <p><strong>Address:</strong> {{ address }}</p>
            <p><strong>Bio:</strong> {{ bio }}</p>
        </div>
    </div>
</template>

<script>
export default {
    data() {
        return {
            profilePicture: '/john.png',
            name: 'John Doe',
            email: 'john.doe@example.com',
            phone: '123-456-7890',
            address: '123 Main St, Anytown, USA',
            bio: 'A short bio about John Doe.'
        };
    }
};
</script>

<style scoped>
.profile {
    max-width: 600px;
    margin: 0 auto;
    padding: 20px;
    border: 1px solid #ccc;
    border-radius: 10px;
}

.profile-header {
    text-align: center;
}

.profile-picture {
    width: 150px;
    height: 150px;
    border-radius: 50%;
    object-fit: cover;
}

.profile-name {
    margin-top: 10px;
    font-size: 24px;
}

.profile-details {
    margin-top: 20px;
}

.profile-details p {
    margin: 5px 0;
}
</style>