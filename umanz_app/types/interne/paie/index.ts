import type { Talent } from "~/types";

export interface EtatPaie {
    anciennete: string;
    classification: string;
    dateEmbauche: string;
    fonction: string;
    idCnaps: string;
    indice: number;
    matricule: number;
    nomPrenom: string;
    talent: Talent;
    retenueCnaps: number;
    retenueSanitaire: number;
    salaire: number;
    tauxHoraire: number;
    tauxJournalier: number;
}