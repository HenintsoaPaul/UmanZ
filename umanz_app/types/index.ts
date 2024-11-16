export interface Formation {
    idFormation: number,
    nomFormation: string,
    dateDebut: string,
    dateFin: string,
    competenceCibles: CompetenceCible[]
}

export interface CompetenceCible {
    competence: Competence,
    formation: Formation,
    pointGagne: number,
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
    poste: Poste,
    talent: Talent,
    idTypeContrat: number
}


export interface Talent {
    idTalent: number,
    nom: string,
    prenom: string,
    mail: string,
    password: string,
    isAdmin: boolean,
    competenceTalents: CompetenceTalent[],
    experienceTalents: ExperienceTalent[]
}

export interface Competence {
    idCompetence: number,
    competence: string
}

export interface CompetenceAnnonce {
    competence: Competence,
    point: number
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
    idAbsence: number,
    motif?: string,
    dateAbsence: string,
    idContrat: number,
}

export interface Poste {
    idPoste: number,
    nomPoste: string,
    description: string,
}

export interface ExperienceTalent {
    poste: Poste,
    talent: Talent,
    ans: number,
}

export interface CompetenceTalent {
    competence: Competence,
    talent: Talent,
    point: number,
}

export interface EtatEntretien {
    idEtatEntretien: number,
    etatEntretien: string
}

export interface Entretien {
    idEntretien: number,
    dateCreation: Date,
    dateValidation: Date,
    note: number,
    motif: string,
    talent: Talent
    annonce: Annonce,
    etatEntretien: EtatEntretien
}

export interface Rupture {
    dateRupture: string,
    idContrat: number
}

export interface TypeContrat {
    idTypeContrat: number,
    typeContrat: string,
}