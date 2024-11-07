<template>
    <div class="annonce-form">
        <h1>Ajouter Annonce</h1>
        <form @submit.prevent="submitForm">
            <!-- Daty -->
            <div class="form-group">
                <label for="dateAnnonce">Date d'Annonce:</label>
                <input type="date" id="dateAnnonce" v-model="form.dateAnnonce" required />
            </div>
            <div class="form-group">
                <label for="dateExpiration">Date d'Expiration:</label>
                <input type="date" id="dateExpiration" v-model="form.dateExpiration" required />
            </div>

            <!-- Poste -->
            <div class="form-group">
                <label for="poste">Poste:</label>
                <input type="text" id="poste" v-model="form.poste" required />
            </div>
            <div class="form-group">
                <label for="descPoste">Description du Poste:</label>
                <textarea id="descPost" v-model="form.descPoste"></textarea>
            </div>

            <!-- Competences -->
            <div class="form-group">
                <label>Competences:</label>
                <div v-for="option in competences" :key="option.value" class="checkbox-group">
                    <input type="checkbox" :id="option.value" :value="option.value" v-model="form.competences" />
                    <label :for="option.value">{{ option.label }}</label>
                </div>
            </div>

            <!-- Experiences -->
            <div class="form-group">
                <label>Experiences:</label>
                <div v-for="(option, index) in experiences" :key="option.value" class="checkbox-group">
                    <input type="checkbox" :id="option.value" :value="option.value"
                        v-model="form.experiences[index].value" />
                    <label :for="option.value">{{ option.label }}</label>
                    <input type="number" v-if="form.experiences[index]?.value" v-model="form.experiences[index].duree"
                        placeholder="Durée (années)" />
                </div>
            </div>

            <button type="submit">Soumettre</button>
        </form>
    </div>
</template>

<script>
export default {
    data() {
        return {
            form: {
                dateAnnonce: '',
                dateExpiration: '',
                poste: '',
                descPoste: '',
                competences: [],
                experiences: [
                    { value: 'option1', duree: 0 },
                    { value: 'option2', duree: 0 },
                ],
            },
            competences: [
                { value: 'option1', label: 'Conduite' },
                { value: 'option2', label: 'Dynamisme' },
                { value: 'option3', label: 'Ponctualite' },
            ],
            experiences: [
                { value: 'option1', label: 'Informaticien', duree: 0 },
                { value: 'option2', label: 'Gardien', duree: 0 },
            ],
        };
    },
    methods: {
        submitForm() {
            console.log( this.form );
            // Handle form submission
        }
    }
};
</script>

<style scoped>
.annonce-form {
    max-width: 600px;
    margin: 0 auto;
    padding: 20px;
    border: 1px solid #ccc;
    border-radius: 8px;
}

.form-group {
    margin-bottom: 15px;
}

.checkbox-group {
    display: flex;
    align-items: center;
}

.checkbox-group input {
    margin-right: 10px;
}
</style>