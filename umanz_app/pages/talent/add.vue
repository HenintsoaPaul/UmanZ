<script setup lang="ts">
import { z } from 'zod'
import { reactive, ref, toRaw } from 'vue';

import FormTalent from '~/components/talent/FormTalent.vue';
import type { Competence, CompetenceAnnonce, Diplome, DiplomeAvecNiveau, ExperiencePoste, Langue, LangueAvecNiveau, NiveauDiplome, NiveauLangue, Poste } from '~/types';

const schema = z.object({
    nom: z.string().min(1, 'Le nom est obligatoire'),
    prenom: z.string().min(1, 'Le prénom est obligatoire'),
    mail: z.string().email('Email incorrect'),
    password: z.string().min(6, 'Le mot de passe doit contenir au moins 6 caractères'),
    isAdmin: z.boolean()
});

interface Form {
    nom: string;
    prenom: string;
    mail: string;
    password: string;
    isAdmin: Boolean;
    competences: CompetenceAnnonce[];
    experiences: ExperiencePoste[];
    languesAvecNiveaux: LangueAvecNiveau[];
    diplomesAvecNiveaux: DiplomeAvecNiveau[];
}
const form = reactive<Form>({
    nom: '',
    prenom: '',
    mail: '',
    password: '',
    isAdmin: false,
    competences: [],
    experiences: [],
    languesAvecNiveaux: [],
    diplomesAvecNiveaux: [],
});

const apiUrl = useRuntimeConfig().public.apiUrl as string;
const { data: postes, refresh: refreshPostes } = useFetch<Poste[]>(`${apiUrl}/postes`);
const { data: competences, refresh: refreshCompetences } = useFetch<Competence[]>(`${apiUrl}/competences`);
const { data: langues, refresh: refreshLangues } = useFetch<Langue[]>(`${apiUrl}/langues`);
const { data: niveaulangues, refresh: refreshNiveauLangues } = useFetch<NiveauLangue[]>(`${apiUrl}/niveau_langues`);
const { data: diplomes, refresh: refreshDiplomes } = useFetch<Diplome[]>(`${apiUrl}/diplomes`);
const { data: niveauDiplomes, refresh: refreshniveauDiplomes } = useFetch<NiveauDiplome[]>(`${apiUrl}/niveau_diplomes`);

const errorMessage = ref('');
const successMessage = ref('');
const loading = ref(false);

async function onSubmit() {
    const isValid = schema.safeParse(form).success;

    if (!isValid) {
        errorMessage.value = 'Une erreur s\'est produite lors de la soumission du formulaire.';
        return;
    }

    loading.value = true;
    try {
        const { languesAvecNiveaux, diplomesAvecNiveaux, ...f } = form;
        const formKdj = toRaw({
            ...f,
            competences: f.competences.filter(cp => cp.point > 0).map(e => toRaw(e)),
            experiences: f.experiences.filter(exp => exp.ans > 0).map(e => toRaw(e)),
            langues: languesAvecNiveaux.filter(lg => lg.niveauLangue != null).map(e => toRaw(e)),
            diplomes: diplomesAvecNiveaux.filter(dp => dp.selected === true).map(e => toRaw(e)),
        });

        console.log(toRaw(formKdj));    

        const apiUrl: string = useRuntimeConfig().public.apiUrl as string;
        const response = await $fetch(`${apiUrl}/talents`, {
            method: 'POST',
            body: toRaw(formKdj)
        });

        console.log('Form submitted successfully:', response);
        errorMessage.value = '';
        successMessage.value = 'Le formulaire a été soumis avec succès.';
    } catch (error) {
        console.error('Error submitting form:', error);
        errorMessage.value = 'Une erreur s\'est produite lors de la soumission du formulaire.';
        successMessage.value = '';
    } finally {
        loading.value = false;
    }
}

onMounted(async () => {
    await refreshPostes();
    await refreshCompetences();
    await refreshDiplomes();
    await refreshLangues();
    await refreshniveauDiplomes();
    await refreshNiveauLangues();
    if (postes.value && competences.value && langues.value && diplomes.value) {
        form.experiences = postes.value.map(pt => ({
            ans: 0,
            poste: toRaw(pt)
        }));
        form.competences = competences.value.map(cp => ({
            point: 0,
            competence: toRaw(cp)
        }));
        form.languesAvecNiveaux = langues.value.map(lg => ({
            langue: toRaw(lg),
            niveauLangue: null
        }));
        form.diplomesAvecNiveaux = diplomes.value.map(dp => ({
            diplome: toRaw(dp),
            selected: false,
            niveauDiplome: null
        }));
    }
});
</script>

<template>
    <div class="talent-form">
        <h1 class="text-2xl font-bold mb-6">Profil Talent</h1>

        <template v-if="niveauDiplomes && niveaulangues">
            <FormTalent :form="form" :schema="schema" :loading="loading" :niveau-diplomes="niveauDiplomes"
                :niveau-langues="niveaulangues" :error-message="errorMessage" :success-message="successMessage"
                @submit="onSubmit" />
        </template>
    </div>
</template>

<style scoped>
.talent-form {
    max-width: 800px;
    margin: 0 auto;
    padding: 20px;
    border: 1px solid #ccc;
    border-radius: 8px;
}
</style>
