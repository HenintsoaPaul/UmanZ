<template>
  <div>
    <h1>Historique des Candidats</h1>
    <div v-for="candidate in historique" :key="candidate.idTalent">
      <h2>{{ candidate.nom }} {{ candidate.prenom }}</h2>
      <ul>
        <li v-for="entry in candidate.historique" :key="entry.id">
          <strong>{{ entry.action }}</strong> : {{ entry.description }} 
          ({{ new Date(entry.dateAction).toLocaleString() }})
        </li>
      </ul>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      historique: []  
    };
  },
  async mounted() {
    try {
      const response = await this.$axios.$get('/api/candidats/historique');
      this.historique = response; 
    } catch (error) {
      console.error('Erreur lors de la récupération des historiques:', error);
    }
  }
};
</script>
