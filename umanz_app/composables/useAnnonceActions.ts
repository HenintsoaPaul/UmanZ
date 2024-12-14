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
        headers
    }
}