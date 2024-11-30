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

export interface CongeDTO {
    conge: Conge;
    soldeConge: number;
}
