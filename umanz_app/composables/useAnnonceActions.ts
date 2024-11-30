export const useAnnonceActions = () => {
    const canditerFn = async (idAnnonce: number, idTalent: string, apiUrl: string): Promise<string> => {
        if (idTalent) {
            try {
                const response = await $fetch(`${apiUrl}/entretien/candiat`, {
                    method: 'POST',
                    body: {
                        idTalent: Number(idTalent),
                        idAnnonce: idAnnonce
                    }
                });
                console.log('Candidature data sent successfully', response);
                return 'Candidature data sent successfully';
            } catch (error) {
                console.error('Failed to send candidature data', error);
                return 'Failed to send candidature data';
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