import type { Entretien } from "~/types";

export const useEntretienActions = () => {
    const validerEntretien = async (entretien: Entretien, apiUrl: string, refreshEntretiens: () => void) => {
        if (entretien.note < 0 || entretien.note > 20) return;

        try {
            const response = await $fetch(`${apiUrl}/entretien/validate`, {
                method: 'POST',
                body: {
                    idEntretien: entretien.idEntretien,
                    note: entretien.note,
                    motif: entretien.motif
                }
            });
            console.log('Entretien validé:', response);
            refreshEntretiens();
        } catch (error) {
            console.error('Erreur lors de la validation de l\' Entretien:', error);
        }
    }

    const prochainEntretien = async (entretien: Entretien, apiUrl: string, refreshEntretiens: () => void) => {
        try {
            const response = await $fetch(`${apiUrl}/entretien/validate`, {
                method: 'POST',
                body: {
                    idEntretien: entretien.idEntretien,
                    note: 0,
                    motif: ''
                }
            });
            console.log('Entretien validé:', response);
            refreshEntretiens();
        } catch (error) {
            console.error('Erreur lors de la validation de l\' Entretien:', error);
        }
    }

    const refuserEntretien = async (idEntretien: number, apiUrl: string, refreshEntretiens: () => void) => {
        try {
            const response = await $fetch(`${apiUrl}/entretien/deny`, {
                method: 'POST',
                body: {
                    idEntretien: idEntretien,
                }
            });
            console.log('Entretien refusé:', response);
            refreshEntretiens()
        } catch (error) {
            console.error('Erreur lors du refus de la validation:', error);
        }
    }

    const headers = [
        {
            key: 'idEntretien',
            label: 'ID',
            sortable: true
        },
        {
            key: 'talent.idTalent',
            label: 'Talent'
        },
        {
            key: 'etatEntretien.etatEntretien',
            label: 'Status'
        },
        {
            key: 'dateCreation',
            label: 'Date Creation',
            sortable: true
        },
        {
            key: 'dateValidation',
            label: 'Date Validation',
            sortable: true
        },
    ];

    return {
        validerEntretien,
        prochainEntretien,
        refuserEntretien,
        headers
    }
}