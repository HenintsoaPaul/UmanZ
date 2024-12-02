export interface Formation {
    idFormation: number,
    nomFormation: string,
    dateDebut: string,
    dateFin: string,
    estFini: boolean,
    competenceCibles: CompetenceCible[]
}

export interface CompetenceCible {
    competence: Competence,
    formation: Formation,
    pointGagne: number,
}

export interface Talent {
    idTalent: number,
    nom: string,
    prenom: string,
    mail: string,
    password: string,
    isAdmin: boolean,
    competenceTalents: CompetenceTalent[],
    experienceTalents: ExperienceTalent[],
    diplomes: DiplomeAvecNiveau[],
    talentLangues: LangueAvecNiveau[]
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

export interface Chat {
    idChat: number;
    motCle: string;
    reponse: string;
}

export interface Compatibilite {
    talent: Talent;
    annonce: Annonce;
    pourcentage: number;
}

export interface Langue {
    idLangue: number;
    langue: string;
}

export interface NiveauLangue {
    idNiveauLangue: number;
    niveauLangue: string;
    niveau: number;
}

export interface LangueAvecNiveau {
    langue: Langue;
    niveauLangue: NiveauLangue | null
}

export interface DiplomeAvecNiveau {
    diplome: Diplome;
    selected: boolean;
    niveauDiplome: NiveauDiplome | null
}

export interface AnnonceLangue {
    annonce: Annonce;
    langue: Langue;
    niveauLangue: NiveauLangue;
}

export interface TalentLangue {
    talent: Talent;
    langue: Langue;
    niveauLangue: NiveauLangue;
}

export interface Diplome {
    idDiplome: number;
    diplome: string;
    niveauDiplome: NiveauDiplome
}

export interface NiveauDiplome {
    idNiveauDiplome: number;
    niveau: string;
    niveauDiplome: number;
}

export interface AnnonceDiplome {
    annonce: Annonce;
    diplome: Diplome;
}

export interface TalentDiplome {
    talent: Talent;
    diplome: Diplome;
}

export interface QuestionProjet {
    idQuestionProjet: number,
    question: string
}

export interface QuestionEvaluation {
    questionProjet: QuestionProjet,
    note: number,
    reponse: string
}

export interface Evaluation {
    idEvaluation: number,
    dateEvaluation: string,
    talent: Talent,
    moyenne: number,
    questionEvaluations: QuestionEvaluation[]
}

export interface QuestionEvaluationNote {
    idQuestionProjet: number,
    note: number
}

export interface EvaluationNote {
    idEvaluation?: number,
    idTalent?: number,
    dateEvaluation?: string,
    evaluations?: QuestionEvaluationNote[]
}

export interface PaysLipsHeader {
    nomPrenom: string;
    matricule: number,
    fonction: string;
    dateEmbauche: string;
    anciennete: string;
    classification: string;
    idCnaps: string;
    salaire: number;
    tauxJournalier: number;
    tauxHoraire: number;
    indice: number;
    retenueCnaps: number;
    retenueSanitaire: number;
}

export interface PaysLipsDetails {
    designation: string;
    taux: number;
    montant: number;
    nombre: number;
}

export interface PaysLipsBody {
    brute: PaysLipsDetails[];
    retenue: PaysLipsDetails[];
}
