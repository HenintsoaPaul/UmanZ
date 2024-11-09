export interface Absence {
    id_absence: number,
    motif?: string ,
    date_absence: Date,
    id_contrat: number,
}

export interface Talent {
    id_talent: number,
    nom: string,
    prenom: string,
    mail: string,
    password: string,
}