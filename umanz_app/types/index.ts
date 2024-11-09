export interface Absence {
    id_absence: number,
    motif?: string,
    date_absence: Date,
    id_contrat: number,
}

export interface Talent {
    id_talent: number,
    nom: string,
    prenom: string,
    mail: string,
    password: string,
}

export interface Competence {
    id_competence: number;
    competence: string;
}

export interface TalentCompetence {
    id_competence: number,
    id_talent: number,
    point: number,
}

export interface Poste {
    id_poste: number,
    nom_poste: string,
    description_poste?: string,
}

export interface ExperienceTalent {
    id_poste: number,
    id_talent: number,
    ans: number,
}

export interface CV {
    id_talent: number;
    competences: TalentCompetence[],
    experiences: ExperienceTalent[]
}