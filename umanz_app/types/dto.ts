import type { Justificatif } from "./interne";

export interface JustificatifDTO {
    dateJustificatif: string;
    imageJustificatif: string;
    idTypeJustificatif: number;
}

export interface FormConge {
    dateDebut: string;
    nbJour: number;
    motif: string;
    idTypeConge: number;
    justificatif: JustificatifDTO;
}