import type { FormConge, JustificatifDTO } from "~/types/interne/conge";

export const useCongeActions = () => {
    const validerCongerFn = async (idConge: number, apiUrl: string) => {
        try {
            const response = await $fetch(`${apiUrl}/conges/validate/${idConge}`, {
                method: 'PUT',
            });
            console.log('CongeValidation data sent successfully', response);
        } catch (error) {
            console.error('Failed to send congeValidation data', error);
            throw error;
        }
    }

    const refuserCongerFn = async (idConge: number, motifRefus: string, apiUrl: string) => {
        try {
            const response = await $fetch(`${apiUrl}/conges/refuse/${idConge}`, {
                method: 'PUT',
                body: motifRefus
            });
            console.log('CongeRefus data sent successfully', response);
        } catch (error) {
            console.error('Failed to send congeRefus data', error);
            throw error;
        }
    }

    const demanderCongerFn = async (data: FormConge, idContrat: number, apiUrl: string) => {
        try {
            const playLoad = {
                idContrat: idContrat,
                ...data
            }
            console.log(playLoad);
            const response = await $fetch(`${apiUrl}/conges/demandes`, {
                method: 'POST',
                body: playLoad
            });
            console.log('Conge data sent successfully', response);
        } catch (error) {
            console.error('Failed to send conge data', error);
            throw error;
        }
    }

    const validateJustificatif = (justificatif: JustificatifDTO): string => {
        let errs = "";
        if (justificatif.dateJustificatif.length === 0) {
            errs += "DateJustificatif is not set.";
        }
        if (justificatif.imageJustificatif.length === 0) {
            errs += "ImageJustificatif is not set.";
        }
        return errs;
    }

    return {
        validerCongerFn,
        refuserCongerFn,
        demanderCongerFn,
        validateJustificatif
    }
}