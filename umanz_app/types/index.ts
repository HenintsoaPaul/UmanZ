export interface Formation {
    idFormation: number,
    nomFormation: string,
    date_debut: string,
    date_fin: string
}


export interface Conge {
    idConge: number,
    dateDebut: string,
    nbJour: number,
    motif?: string,
    dateValidation?: string,
    contrat: Contrat
}

export interface Contrat {
    idContrat: number,
    contrat: string,
    dateDebut: string,
    dateFin?: string,
    salaireHoraire: number,
    nbJourSemaine: number,
    nbJourCongeAn: number,
    nbHeureJour: number,
    idPoste: number,
    talent: Talent,
    idTypeContrat: number
}


export interface Talent {
    idTalent: number,
    nom: string,
    prenom: string,
    mail: string,
    password: string,
    isAdmin: boolean
}

export interface Competence {
    idCompetence: number,
    competence: string
}

export interface CompetenceAnnonce {
    competence: Competence,
    point: number
}

export interface Poste {
    idPoste: number,
    description: string
}

export interface ExperiencePoste {
    poste: Poste,
    ans: number
}

export interface Annonce {
    idAnnonce: number,
    poste: Poste,
    dateAnnonce: string,
    dateExpiration: string,
    dateRupture: string | null,
    experiencePostes: ExperiencePoste[],
    competenceAnnonces: CompetenceAnnonce[]
}


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

export interface Entretien {
    id_entretien: number,
    date_creation: Date,
    date_validation: Date,
    note?: number,
    motif: string,
    id_enfant?: number,
    id_annonce: number,
    id_etat_entretien: number,
    id_talent: number,
}