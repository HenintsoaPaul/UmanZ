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
    <div class="max-w-3xl mx-auto flex flex-col h-[700px] bg-white rounded-2xl border border-gray-100 shadow-sm overflow-hidden">
        <!-- Header -->
        <div class="px-6 py-4 border-bottom border-gray-100 bg-gray-50/50 flex items-center justify-between">
            <div class="flex items-center gap-3">
                <div class="w-10 h-10 bg-umanz-purple rounded-xl flex items-center justify-center text-white">
                    <UIcon name="i-heroicons-chat-bubble-left-right" class="text-xl" />
                </div>
                <div>
                    <h3 class="font-bold text-gray-900">UmanZ AI Assistant</h3>
                    <p class="text-xs text-gray-500">Posez vos questions sur la gestion RH</p>
                </div>
            </div>
            <div class="flex items-center gap-2">
                <span class="w-2 h-2 bg-green-500 rounded-full"></span>
                <span class="text-xs text-gray-500 font-medium">En ligne</span>
            </div>
        </div>

        <!-- Messages -->
        <div class="flex-1 overflow-y-auto p-6 space-y-4 custom-scrollbar">
            <div v-if="messages.length === 0" class="text-center py-12">
                <div class="w-16 h-16 bg-gray-50 rounded-2xl flex items-center justify-center mx-auto mb-4">
                    <UIcon name="i-heroicons-chat-bubble-bottom-center-text" class="text-3xl text-gray-300" />
                </div>
                <p class="text-gray-400">Aucun message pour le moment.<br>Commencez la conversation !</p>
            </div>
            <div
                v-for="(msg, index) in messages"
                :key="index"
                :class="[
          'px-4 py-2.5 rounded-2xl max-w-md text-sm leading-relaxed transition-all duration-200',
          msg.type === 'user' 
            ? 'bg-umanz-purple text-white self-end ml-auto rounded-tr-none' 
            : 'bg-gray-100 text-gray-900 self-start mr-auto rounded-tl-none'
        ]"
            >
                <p v-html="msg.text"></p>
            </div>
        </div>

        <!-- Input Field -->
        <div class="p-6 bg-gray-50/30 border-t border-gray-100">
            <div class="relative flex items-center gap-2">
                <UInput
                    v-model="userInput"
                    @keydown.enter="sendMessage"
                    placeholder="Tapez votre message ici..."
                    class="flex-1"
                    :disabled="loading"
                    icon="i-heroicons-chat-bubble-oval-left"
                    size="xl"
                    :ui="{ rounded: 'rounded-2xl' }"
                />
                <UButton
                    @click="sendMessage"
                    :loading="loading"
                    size="xl"
                    class="rounded-xl px-6"
                    color="primary"
                >
                    Envoyer
                </UButton>
            </div>
        </div>
    </div>
</template>

<style scoped>

.h-600px {
    height: 600px;
}

</style>
