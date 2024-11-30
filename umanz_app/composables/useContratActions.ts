export const useContratActions = () => {
    const promotionFn = async (idContrat: number) => {
        // fin contrat
        // recuperer idContrat venant de l'API

        // Asina dropDown eo akaikin'ilay boutton pour choisir le nouveau poste

        // TODO: Implement
        console.log("Promotion function not defined");
    }

    const finContratFn = async (idContrat: number, apiUrl: string) => {
        ruptureFn(idContrat, 1, apiUrl);
    }
    const demissionFn = async (idContrat: number, apiUrl: string) => {
        ruptureFn(idContrat, 2, apiUrl);
    }
    const expulsionFn = async (idContrat: number, apiUrl: string) => {
        ruptureFn(idContrat, 3, apiUrl);
    }
    const retraiteFn = async (idContrat: number, apiUrl: string) => {
        ruptureFn(idContrat, 5, apiUrl);
    }

    const ruptureFn = async (idContrat: number, idTypeRupture: number, apiUrl: string) => {
        try {
            const response = await $fetch(`${apiUrl}/rupture`, {
                method: 'POST',
                body: {
                    idContrat: idContrat,
                    idTypeRupture: idTypeRupture
                }
            });
            console.log('Rupture data sent successfully', response);
        } catch (error) {
            console.error('Failed to send rupture data', error);
        }
    }

    return {
        promotionFn,
        finContratFn,
        expulsionFn,
        demissionFn,
        retraiteFn,
        ruptureFn
    }
}