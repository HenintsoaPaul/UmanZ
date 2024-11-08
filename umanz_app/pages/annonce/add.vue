<script setup lang="ts">
import { ref } from 'vue'

// Interfaces
interface Form {
    dateAnnonce: string;
    dateExpiration: string;
    poste: string;
    descPoste: string;
    competences: string[];
    experiences: ExperienceItem[];
}

interface CompetenceOption {
    value: string;
    label: string;
}

interface ExperienceItem {
    value: string;
    label: string;
    duree: number;
}

// Data
const competences: CompetenceOption[] = [
    { value: 'option1', label: 'Conduite' },
    { value: 'option2', label: 'Dynamisme' },
    { value: 'option3', label: 'Ponctualite' },
]

const experiences: ExperienceItem[] = [
    { value: 'option1', label: 'Informaticien', duree: 0 },
    { value: 'option2', label: 'Gardien', duree: 0 },
]

const form = ref<Form>({
    dateAnnonce: '',
    dateExpiration: '',
    poste: '',
    descPoste: '',
    competences: [],
    experiences: [...experiences]
})

// Method
const submitForm = () => {
    console.log(toRaw(form.value));
}
</script>

<template>
    <div class="absence-form">
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
                <div v-for="(item, index) in experiences" :key="index" class="checkbox-group">
                    <label :for="item.value">{{ item.label }}</label>
                    <input type="number" v-model="form.experiences.find(i => i.label === item.label)!.duree"
                        placeholder="Durée (années)" />
                </div>
            </div>

            <button type="submit">Soumettre</button>
        </form>
    </div>
</template>
