<template>
    <div class="container">
      <h1>Classement des Talents</h1>
  
      <div class="select-annonce">
        <label for="annonce-select">Sélectionner l'Annonce :</label>
        <select id="annonce-select" v-model="selectedAnnonceId" @change="fetchTalents">
          <option v-for="annonce in annonces" :key="annonce.id" :value="annonce.id">{{ annonce.nom }}</option>
        </select>
      </div>
  
      <div class="talent-list" v-if="talents.length > 0">
        <h2>Liste des Talents pour l'Annonce {{ selectedAnnonceId }}</h2>
        <table>
          <thead>
            <tr>
              <th>ID</th>
              <th>Nom</th>
              <th>Prénom</th>
              <th>Score</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="talent in talents" :key="talent.idTalent">
              <td>{{ talent.idTalent }}</td>
              <td>{{ talent.nom }}</td>
              <td>{{ talent.prenom }}</td>
              <td>{{ talent.scoreTotal }}</td>
            </tr>
          </tbody>
        </table>
      </div>
      <div v-else>
        <p>Aucun talent trouvé pour cette annonce.</p>
      </div>
    </div>
  </template>
  
  <script>
  import axios from 'axios';
  
  export default {
    data() {
      return {
        annonces: [], // Liste des annonces récupérées depuis l'API
        talents: [],  // Liste des talents à afficher
        selectedAnnonceId: null // L'ID de l'annonce sélectionnée
      };
    },
    mounted() {
      this.fetchAnnonces(); // Appel pour récupérer les annonces dès le chargement du composant
    },
    methods: {
      // Récupère la liste des annonces depuis l'API
      fetchAnnonces() {
        axios.get('http://localhost:911/annonce/all')
          .then(response => {
            // Mise à jour des annonces récupérées
            this.annonces = response.data.map(annonce => ({
              id: annonce.idAnnonce,              // ID de l'annonce
              nom: annonce.poste.nomPoste,       // Nom du poste
              description: annonce.poste.description // Description du poste
            }));
  
            // Si des annonces existent, sélectionnez la première par défaut
            if (this.annonces.length > 0) {
              this.selectedAnnonceId = this.annonces[0].id;
              this.fetchTalents(); // Charger les talents pour la première annonce
            }
          })
          .catch(error => {
            console.error('Erreur lors de la récupération des annonces:', error);
          });
      },
      // Récupère les talents pour l'annonce sélectionnée
      fetchTalents() {
        if (!this.selectedAnnonceId) return;
  
        axios.get(`http://localhost:911/tricandidats/trier?idAnnonce=${this.selectedAnnonceId}`)
          .then(response => {
            this.talents = response.data; // Met à jour les talents affichés
          })
          .catch(error => {
            console.error('Erreur lors de la récupération des talents:', error);
          });
      }
    }
  };
  </script>
  
  <style scoped>
  .container {
    max-width: 900px;
    margin: 0 auto;
    padding: 20px;
    font-family: Arial, sans-serif;
    background-color: #f4f7f9;
    border-radius: 8px;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  }
  
  h1 {
    text-align: center;
    color: #333;
  }
  
  .select-annonce {
    margin-bottom: 20px;
  }
  
  select {
    padding: 10px;
    font-size: 16px;
    margin-left: 10px;
    border-radius: 5px;
    border: 1px solid #ccc;
    outline: none;
  }
  
  select:focus {
    border-color: #007bff;
  }
  
  .table {
    width: 100%;
    margin-top: 20px;
    border-collapse: collapse;
  }
  
  th, td {
    padding: 12px;
    text-align: left;
  }
  
  th {
    background-color: #007bff;
    color: #fff;
  }
  
  td {
    background-color: #fff;
    border-bottom: 1px solid #ddd;
  }
  
  tr:nth-child(even) {
    background-color: #f9f9f9;
  }
  
  tr:hover {
    background-color: #f1f1f1;
  }
  
  p {
    text-align: center;
    color: #777;
  }
  </style>
  