import type { FormConge } from "~/types/dto";

export const useCongeActions = () => {
    const demanderCongerFn = async (data: FormConge, idContrat: number, apiUrl: string) => {
        try {
            const response = await $fetch(`${apiUrl}/conges`, {
                method: 'POST',
                body: {
                    idContrat: idContrat,
                    ...data
                }
            });
            console.log('Rupture data sent successfully', response);
        } catch (error) {
            console.error('Failed to send rupture data', error);
        }
    }

    return {
        demanderCongerFn,
    }
}