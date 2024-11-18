<script setup lang="ts">
import { ref } from 'vue'
import axios from "axios";

interface Message {
  text: string;
  type: 'user' | 'bot';
}

const messages = ref<Message[]>([])

const userInput = ref('')

const apiUrl = useRuntimeConfig().public.apiUrl;

const sendMessage = () => {
  if (userInput.value.trim() === '') return

  const message = userInput.value.trim()
  messages.value.push({text: message, type: 'user'})

  axios.post(
      `${apiUrl}/chat`,
      {'message': message}
  )
  .then(response => {
      messages.value.push({text: response.data, type: 'bot'})
  })

  userInput.value = ''
}
</script>

<template>
  <div class="max-w-md mx-auto p-4 bg-gray-600 rounded-lg shadow-md flex flex-col h-600px">
    <!-- Messages -->
    <div class="flex-1 overflow-y-auto mb-4 space-y-2 flex flex-col px-1.5">
      <div
          v-for="(msg, index) in messages"
          :key="index"
          :class="[
          'p-2 rounded-lg max-w-xs',
          msg.type === 'user' ? 'bg-blue-500 text-white self-end' : 'bg-gray-700 text-white self-start'
        ]"
      >
        <p>{{ msg.text }}</p>
      </div>
    </div>

    <!-- Input Field -->
    <div class="flex items-center space-x-2">
      <input
          v-model="userInput"
          @keydown.enter="sendMessage"
          placeholder="Tapez votre message..."
          class="flex-1 p-2 rounded-lg border border-gray-300 focus:outline-none focus:ring-2 focus:ring-blue-500"
      />
      <button
          @click="sendMessage"
          class="px-4 py-2 bg-blue-500 text-white rounded-lg hover:bg-blue-600"
      >
        Envoyer
      </button>
    </div>
  </div>
</template>

<style scoped>

.h-600px {
  height: 600px;
}

</style>
