<script setup lang="ts">
import AppSidebar from '~/components/layout/AppSidebar.vue';
</script>

<template>
  <div class="flex min-h-screen bg-gray-50/50">
    <AppSidebar />
    <main class="flex-1 p-8 overflow-y-auto">
      <div class="max-w-7xl mx-auto bg-white rounded-2xl shadow-sm border border-gray-100 p-8 min-h-full">
        <slot />
      </div>
    </main>
  </div>
</template>