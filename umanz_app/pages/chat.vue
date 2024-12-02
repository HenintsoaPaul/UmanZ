<script setup lang="ts">
import {ref} from 'vue'
import axios from "axios";

interface Message {
    text: string;
    type: 'user' | 'bot';
}

const messages = ref<Message[]>([])

const userInput = ref('')
const loading = ref<boolean>(false);

const apiUrl = useRuntimeConfig().public.apiUrl;

const sendMessage = () => {
    if (userInput.value.trim() === '') return

    loading.value = true;
    const message = userInput.value.trim()
    messages.value.push({text: message, type: 'user'})

    const botMessage = {
        text: '<div role="status">' +
            '        <svg aria-hidden="true" class="w-8 h-8 text-gray-200 animate-spin dark:text-gray-600 fill-blue-600" viewBox="0 0 100 101" fill="none" xmlns="http://www.w3.org/2000/svg"><path d="M100 50.5908C100 78.2051 77.6142 100.591 50 100.591C22.3858 100.591 0 78.2051 0 50.5908C0 22.9766 22.3858 0.59082 50 0.59082C77.6142 0.59082 100 22.9766 100 50.5908ZM9.08144 50.5908C9.08144 73.1895 27.4013 91.5094 50 91.5094C72.5987 91.5094 90.9186 73.1895 90.9186 50.5908C90.9186 27.9921 72.5987 9.67226 50 9.67226C27.4013 9.67226 9.08144 27.9921 9.08144 50.5908Z" fill="currentColor"/><path d="M93.9676 39.0409C96.393 38.4038 97.8624 35.9116 97.0079 33.5539C95.2932 28.8227 92.871 24.3692 89.8167 20.348C85.8452 15.1192 80.8826 10.7238 75.2124 7.41289C69.5422 4.10194 63.2754 1.94025 56.7698 1.05124C51.7666 0.367541 46.6976 0.446843 41.7345 1.27873C39.2613 1.69328 37.813 4.19778 38.4501 6.62326C39.0873 9.04874 41.5694 10.4717 44.0505 10.1071C47.8511 9.54855 51.7191 9.52689 55.5402 10.0491C60.8642 10.7766 65.9928 12.5457 70.6331 15.2552C75.2735 17.9648 79.3347 21.5619 82.5849 25.841C84.9175 28.9121 86.7997 32.2913 88.1811 35.8758C89.083 38.2158 91.5421 39.6781 93.9676 39.0409Z" fill="currentFill"/></svg>' +
            '        <span class="sr-only">Loading...</span>' +
            '    </div>',
        type: "bot"
    } as Message;
    messages.value.push(botMessage)

    axios.post(
        `${apiUrl}/chat`,
        {'message': message}
    )
        .then(response => {
            botMessage.text = response.data;
            loading.value = false;
        })
        .catch(error => {
            console.error(error.message);
            loading.value = false;
        });

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
                <p v-html="msg.text"></p>
            </div>
        </div>

        <!-- Input Field -->
        <div class="flex items-center space-x-2">
            <input
                v-model="userInput"
                @keydown.enter="sendMessage"
                placeholder="Tapez votre message..."
                class="flex-1 p-2 rounded-lg border border-gray-300 focus:outline-none focus:ring-2 focus:ring-blue-500"
                :disabled="loading"
            />
            <button
                @click="sendMessage"
                class="text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center me-2 dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800 inline-flex items-center"
                type="button"
                :disabled="loading"
            >
                <svg v-if="loading" aria-hidden="true" role="status" class="inline w-4 h-4 me-3 text-white animate-spin" viewBox="0 0 100 101" fill="none" xmlns="http://www.w3.org/2000/svg">
                    <path d="M100 50.5908C100 78.2051 77.6142 100.591 50 100.591C22.3858 100.591 0 78.2051 0 50.5908C0 22.9766 22.3858 0.59082 50 0.59082C77.6142 0.59082 100 22.9766 100 50.5908ZM9.08144 50.5908C9.08144 73.1895 27.4013 91.5094 50 91.5094C72.5987 91.5094 90.9186 73.1895 90.9186 50.5908C90.9186 27.9921 72.5987 9.67226 50 9.67226C27.4013 9.67226 9.08144 27.9921 9.08144 50.5908Z" fill="#E5E7EB"/>
                    <path d="M93.9676 39.0409C96.393 38.4038 97.8624 35.9116 97.0079 33.5539C95.2932 28.8227 92.871 24.3692 89.8167 20.348C85.8452 15.1192 80.8826 10.7238 75.2124 7.41289C69.5422 4.10194 63.2754 1.94025 56.7698 1.05124C51.7666 0.367541 46.6976 0.446843 41.7345 1.27873C39.2613 1.69328 37.813 4.19778 38.4501 6.62326C39.0873 9.04874 41.5694 10.4717 44.0505 10.1071C47.8511 9.54855 51.7191 9.52689 55.5402 10.0491C60.8642 10.7766 65.9928 12.5457 70.6331 15.2552C75.2735 17.9648 79.3347 21.5619 82.5849 25.841C84.9175 28.9121 86.7997 32.2913 88.1811 35.8758C89.083 38.2158 91.5421 39.6781 93.9676 39.0409Z" fill="currentColor"/>
                </svg>
                {{ loading ? 'Chargement...' : 'Envoyer' }}
            </button>
        </div>
    </div>
</template>

<style scoped>

.h-600px {
    height: 600px;
}

</style>
