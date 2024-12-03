import type { Contrat } from "~/types";

export interface Absence {
    idAbsence: number,
    motif?: string,
    contrat: Contrat,
    dateAbsence: string,
    idContrat: number,
}
