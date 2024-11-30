<template>
    <div class="employee-page">
      <!-- Filtrer par catégorie -->
      <div class="filter-container">
        <label for="categoryFilter">Filtrer par catégorie</label>
        <select v-model="selectedCategory" @change="fetchEmployeesByCategory">
          <option value="">--Choisir une catégorie--</option>
          <option v-for="category in categories" :key="category.id_categories" :value="category.id_categories">
            {{ category.nom }}
          </option>
        </select>
      </div>
  
      <!-- Liste des employés -->
      <div class="employee-list">
        <div v-for="employee in employees" :key="employee.id_employee" class="employee-card">
          <div class="employee-info">
            <h3>{{ employee.nom }}</h3>
            <p>{{ employee.email }}</p>
            <p>Embauché le : {{ employee.date_of_hire }}</p>
          </div>
  
          <!-- Bouton pour la promotion -->
          <div class="promotion-select">
            <select v-model="employee.selectedPromotion" @change="onPromotionSelect(employee)">
              <option value="">--Choisir une promotion--</option>
              <option v-for="promotion in employee.promotions" :key="promotion.id_poste" :value="promotion.id_poste">
                {{ promotion.title }} (Points: {{ promotion.point_categorie }})
              </option>
            </select>
          </div>
  
          <!-- Bouton de renvoi et formulaire de motif -->
          <div class="renvoie-button">
            <button @click="showMotifForm(employee)">Renvoi</button>
          </div>
          
          <!-- Formulaire de renvoi -->
          <div v-if="employee.showMotifForm" class="renvoi-form">
            <label for="motif">Motif du renvoi :</label>
            <textarea v-model="employee.motif" id="motif" placeholder="Raison du renvoi..."></textarea>
            <button @click="submitRenvoi(employee)">Envoyer</button>
          </div>
        </div>
      </div>
    </div>
  </template>

<script>
export default {
  data() {
    return {
      categories: [],
      employees: [],
      selectedCategory: "",
    };
  },
  async mounted() {
    await this.fetchCategories();
    await this.fetchEmployees();
  },
  methods: {
    async fetchCategories() {
      try {
        const response = await this.$axios.get("/api/categories");
        this.categories = response.data;
      } catch (error) {
        console.error("Error fetching categories:", error);
      }
    },
    async fetchEmployees() {
      try {
        const response = await this.$axios.get("/employees/all");
        this.employees = response.data.map((employee) => ({
          ...employee,
          selectedPromotion: "",
          promotions: [],
          showMotifForm: false, // Ajouter le contrôle d'affichage du formulaire de motif
          motif: "",
        }));
      } catch (error) {
        console.error("Error fetching employees:", error);
      }
    },
    async fetchEmployeesByCategory() {
      try {
        const response = await this.$axios.get(`/employees/by-category/${this.selectedCategory}`);
        this.employees = response.data.map((employee) => ({
          ...employee,
          selectedPromotion: "",
          promotions: [],
          showMotifForm: false, // Assurer que le formulaire n'est pas affiché par défaut
          motif: "",
        }));
      } catch (error) {
        console.error("Error fetching employees by category:", error);
      }
    },
    async onPromotionSelect(employee) {
      try {
        const response = await this.$axios.get(`/employees/${employee.id_employee}/promotions`);
        employee.promotions = response.data;
      } catch (error) {
        console.error("Error fetching promotions:", error);
      }
    },
    showMotifForm(employee) {
      employee.showMotifForm = !employee.showMotifForm;
    },
    async submitRenvoi(employee) {
      try {
        const response = await this.$axios.post("/employees/send-renvoi-email", {
          email: employee.email,
          motif: employee.motif,
        });
        console.log("Email envoyé avec succès", response);
        employee.showMotifForm = false;
        employee.motif = ""; 
      } catch (error) {
        console.error("Erreur d'envoi de l'email :", error);
      }
    },
  },
};
</script>

<style scoped>
.employee-page {
  padding: 20px;
}

.filter-container {
  margin-bottom: 20px;
  display: flex;
  justify-content: flex-start;
  align-items: center;
}

.filter-container label {
  margin-right: 10px;
}

.filter-container select {
  padding: 5px 10px;
}

.employee-list {
  display: flex;
  flex-wrap: wrap;
  gap: 20px;
}

.employee-card {
  background-color: #f9f9f9;
  padding: 20px;
  border-radius: 8px;
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
  width: 250px;
  display: flex;
  flex-direction: column;
  align-items: flex-start;
}

.employee-info {
  margin-bottom: 15px;
}

.employee-info h3 {
  margin: 0;
  font-size: 18px;
  font-weight: bold;
}

.employee-info p {
  margin: 5px 0;
  color: #555;
}

.promotion-select {
  margin-top: 15px;
}

.promotion-select select {
  padding: 5px 10px;
  width: 100%;
  margin-top: 10px;
}

.renvoie-button {
  margin-top: 20px;
}

.renvoi-form {
  margin-top: 15px;
  display: flex;
  flex-direction: column;
}

.renvoi-form textarea {
  width: 100%;
  height: 80px;
  margin-bottom: 10px;
  padding: 10px;
  border-radius: 4px;
  border: 1px solid #ccc;
}

.renvoi-form button {
  padding: 10px;
  background-color: #4CAF50;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

.renvoi-form button:hover {
  background-color: #45a049;
}
</style>
