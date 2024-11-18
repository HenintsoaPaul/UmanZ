export const useFormationActions = () => {
    const participerFn = async (idContrat: number, idFormation: number, apiUrl: string) => {
        try {
            const response = await $fetch(`${apiUrl}/participations`, {
                method: 'POST',
                body: {
                    idContrat: idContrat,
                    idFormation: idFormation
                }
            });
            console.log('Pariticipation data sent successfully', response);
        } catch (error) {
            console.error('Failed to send participation data', error);
        }
    }

    const headers = [
        {
            key: 'idFormation',
            label: 'ID',
            sortable: true,
        },
        {
            key: 'dateDebut',
            label: 'Date Debut',
            sortable: true
        },
        {
            key: 'dateFin',
            label: 'Date Fin',
            sortable: true
        }
    ];

    return {
        participerFn,
        headers
    }
}