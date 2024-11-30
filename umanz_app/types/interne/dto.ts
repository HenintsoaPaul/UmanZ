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