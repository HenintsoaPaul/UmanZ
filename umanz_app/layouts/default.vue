<script setup lang="ts">
import AppSidebar from '~/components/layout/AppSidebar.vue';
</script>

<template>
  <div class="flex min-h-screen 
    bg-gray-50/50 dark:bg-slate-950 
    transition-colors duration-300">
    <AppSidebar />
    <main class="flex-1 p-8 overflow-y-auto">
      <div class="max-w-7xl mx-auto 
        bg-white dark:bg-slate-900 
        rounded-2xl shadow-sm 
        border border-gray-100 dark:border-slate-800 
        p-8 min-h-full 
        transition-colors duration-300">
        <slot />
      </div>
    </main>
  </div>
</template>