export const useAnnonceActions = () => {
    const canditerFn = async (idAnnonce: number, idTalent: string, apiUrl: string): Promise<string> => {
        if (idTalent) {
            try {
                const response = await $fetch(`${apiUrl}/entretien/candidats`, {
                    method: 'POST',
                    body: {
                        idTalent: Number(idTalent),
                        idAnnonce: idAnnonce
                    }
                });
                console.log('Candidature envoyé avec succés.', response);
                return 'Candidature envoyé avec succés.';
            } catch (error) {
                console.error('Une erreur est survenue lors de l\'envoi de la candidature.', error);
                return "Une erreur est survenue lors de l'envoi de la candidature.";
            }
        } else {
            console.error('Utilisateur non connecté');
            return 'Utilisateur non connecté';
        }
    }

    const validerFn = async (talentId: number, annonceId: number, apiUrl: string) => {
        try {
            const response = await $fetch(`${apiUrl}/entretien/validate`, {
                method: 'POST',
                body: {
                    idAnnonce: annonceId,
                    idTalent: talentId,
                }
            });
            console.log('Candidat validé successfully:', response);
        } catch (error) {
            const msg = 'Erreur lors de la validation du candidat';
            console.error(msg, error);
            throw Error(msg);
        }
    }

    const refuserFn = async (talentId: number, annonceId: number, apiUrl: string) => {
        try {
            const response = await $fetch(`${apiUrl}/entretien/deny`, {
                method: 'POST',
                body: {
                    idAnnonce: annonceId,
                    idTalent: talentId,
                }
            });
            console.log('Candidat refusé successfully:', response);
        } catch (error) {
            const msg = 'Erreur lors du refus du candidat';
            console.error(msg, error);
            throw Error(msg);
        }
    }

    const headers = [
        {
            key: 'idAnnonce',
            label: 'ID',
        },
        {
            key: 'poste.nomPoste',
            label: 'Poste',
        },
        {
            key: 'dateAnnonce',
            label: 'Date Annonce',
            sortable: true
        },
        {
            key: 'dateExpiration',
            label: 'Date Expiration',
            sortable: true
        }
    ];

    return {
        canditerFn,
        validerFn,
        refuserFn,
        headers
    }
}