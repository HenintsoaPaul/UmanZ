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
  <div class="min-h-screen bg-gray-100 flex flex-col justify-center items-center">
    <div class="bg-white p-8 rounded-lg shadow-lg max-w-lg w-full">
      <h1 class="text-4xl font-bold text-center mb-4">Bienvenue sur UmanZ</h1>
      <p class="text-gray-700 text-center mb-6">
        Gérez vos ressources humaines facilement et efficacement avec notre application.
      </p>
      <button @click="getStarted" class="bg-blue-500 text-white px-4 py-2 rounded hover:bg-blue-600">
        Commencer
      </button>
    </div>
  </div>
</template>
