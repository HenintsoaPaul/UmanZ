export interface LoginResponse {
    idTalent: number,
    idContrat?: number,
    email: string,
    admin: boolean,
    mfaRequired?: boolean,
}