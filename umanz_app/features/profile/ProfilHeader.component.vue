<script setup lang="ts">
import type { Talent } from '~/types';

defineProps<{
    talent: Talent;
}>();
</script>

<template>
    <div class="max-w-3xl mx-auto px-4 pt-8">
        <div class="relative bg-white rounded-[2rem] shadow-sm border border-slate-100 overflow-hidden">
            <div class="p-8 flex flex-col md:flex-row items-center gap-10 relative z-10">
                <!-- Avatar / Initials -->
                <div class="flex-shrink-0">
                    <div class="w-24 h-24 md:w-32 md:h-32 bg-gradient-to-br from-umanz-purple to-umanz-purple/80 rounded-2xl shadow-lg shadow-umanz-purple/20 flex items-center justify-center text-white text-4xl md:text-5xl font-bold transform transition-transform hover:scale-105 duration-300">
                        {{ talent.nom.charAt(0) }}{{ talent.prenom.charAt(0) }}
                    </div>
                </div>

                <!-- Info -->
                <div class="flex-grow text-center md:text-left space-y-4">
                    <div>
                        <div class="flex flex-wrap items-center justify-center md:justify-start gap-3 mb-2">
                            <h1 class="text-3xl md:text-4xl font-extrabold text-slate-900 tracking-tight">
                                {{ talent.nom }} {{ talent.prenom }}
                            </h1>
                            <UBadge v-if="talent.isAdmin" color="primary" variant="soft" class="rounded-full px-3 py-1 font-bold">
                                Admin
                            </UBadge>
                        </div>
                        <div class="flex flex-wrap items-center justify-center md:justify-start gap-4 text-slate-500 font-medium">
                            <div class="flex items-center gap-2">
                                <UIcon name="i-heroicons-envelope" class="w-5 h-5 text-umanz-purple/70" />
                                <span>{{ talent.mail }}</span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>
