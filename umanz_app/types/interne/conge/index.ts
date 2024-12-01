import type { Contrat } from "../contrat";

export interface TypeJustificatif {
    idTypeJustificatif: number;
    nomTypeJustificatif: string;
}

export interface Justificatif {
    idJustificatif: number;
    dateJustificatif: string;
    imageJustificatif: string;
    typeJustificatif: TypeJustificatif;
}

export interface TypeConge {
    idTypeConge: number;
    nomTypeConge: string;
}

export interface Conge {
    idConge: number,
    dateDebut: string,
    nbJour: number,
    motif?: string,
    dateValidation?: string,
    dateRefus?: string,
    motifRefus?: string,
    typeConge: TypeConge,
    contrat: Contrat
}

export interface JustificatifDTO {
    dateJustificatif: string;
    imageJustificatif: string;
}

export interface FormConge {
    dateDebut: string;
    nbJour: number;
    motif: string;
    idTypeConge: number;
    justificatif: JustificatifDTO;
}

export interface PendingCongeDTO {
    conge: Conge;
    soldeConge: number;
}
