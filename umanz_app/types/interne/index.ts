import type { Contrat } from "..";

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