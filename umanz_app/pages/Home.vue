<script>
export default {
  name: 'Home',
  methods: {
    getStarted() {
      this.$router.push( '/signup' );
    }
  }
}
</script>

<template>
  <div class="space-y-8">
    <div class="text-center">
      <h1 class="text-4xl font-extrabold text-gray-900 mb-4 tracking-tight">Bienvenue sur <span class="text-umanz-purple">UmanZ</span></h1>
      <p class="text-gray-500 text-lg max-w-2xl mx-auto">
        Gérez vos ressources humaines facilement et efficacement avec notre plateforme unifiée.
      </p>
    </div>

    <div class="grid grid-cols-1 md:grid-cols-3 gap-6">
      <UCard class="hover:border-umanz-purple transition-colors cursor-pointer group">
        <template #header>
          <div class="flex items-center gap-3">
            <div class="w-10 h-10 rounded-xl bg-umanz-purple/10 flex items-center justify-center text-umanz-purple">
              <UIcon name="i-heroicons-user-group" class="text-2xl" />
            </div>
            <h3 class="font-bold text-gray-900">Employés</h3>
          </div>
        </template>
        <p class="text-sm text-gray-500">Gérez vos talents et leurs informations personnelles.</p>
      </UCard>

      <UCard class="hover:border-umanz-green transition-colors cursor-pointer group">
        <template #header>
          <div class="flex items-center gap-3">
            <div class="w-10 h-10 rounded-xl bg-umanz-green/10 flex items-center justify-center text-umanz-green">
              <UIcon name="i-heroicons-calendar" class="text-2xl" />
            </div>
            <h3 class="font-bold text-gray-900">Congés</h3>
          </div>
        </template>
        <p class="text-sm text-gray-500">Suivez les demandes de congés et les absences.</p>
      </UCard>

      <UCard class="hover:border-umanz-orange transition-colors cursor-pointer group">
        <template #header>
          <div class="flex items-center gap-3">
            <div class="w-10 h-10 rounded-xl bg-umanz-orange/10 flex items-center justify-center text-umanz-orange">
              <UIcon name="i-heroicons-megaphone" class="text-2xl" />
            </div>
            <h3 class="font-bold text-gray-900">Annonces</h3>
          </div>
        </template>
        <p class="text-sm text-gray-500">Diffusez des offres et gérez les recrutements.</p>
      </UCard>
    </div>

    <div class="flex justify-center pt-4">
      <UButton size="xl" @click="getStarted" class="rounded-full px-8">
        Commencer
      </UButton>
    </div>
  </div>
</template>
