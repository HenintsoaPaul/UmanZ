<script setup>
import { ref } from 'vue'

const messages = ref([
  { text: 'Hello', type: 'user' },
  { text: 'World XD', type: 'bot' },
  { text: 'How are you?', type: 'user' },
])

const userInput = ref('')

const sendMessage = () => {
  if (userInput.value.trim() === '') return

  const message = userInput.value.trim()
  messages.value.push({ text: message, type: 'user' })

  $axios.$post('/api/chat', { message }).then(response => {
    // Ajouter réponse du bot
    messages.value.push({ text: response.answer, type: 'bot' })
  })

  userInput.value = ''
}
</script>

<template>
  <div class="chat-box">
    <div v-for="(msg, index) in messages" :key="index" :class="['message', msg.type]">
      <p>{{ msg.text }}</p>
    </div>
    <input v-model="userInput" @keydown.enter="sendMessage" placeholder="Tapez votre message..." />
  </div>
</template>

<style>
.chat-box { /* Styles de base pour la zone de chat */ }
.message { /* Styles de base pour chaque message */ }
.user { /* Styles pour messages utilisateur */ }
.bot { /* Styles pour messages bot */ }
</style>
