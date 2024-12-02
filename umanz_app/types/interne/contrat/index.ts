import type { Poste, Talent } from "~/types";

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