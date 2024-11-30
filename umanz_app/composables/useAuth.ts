import type { Talent } from "~/types";

export function useAuth() {
    const authenticate = async (email: string, password: string, apiUrl: string): Promise<Talent | null> => {
        try {
            const response = await $fetch<Talent>(`${apiUrl}/talents/auth`, {
                method: 'POST',
                body: {
                    email: email,
                    password: password
                }
            });
            console.log('Authentication data sent successfully', response);
            return response;
        } catch (error) {
            console.error('Failed to send Authentication data', error);
            return null;
        }
    }

    const saveUser = (user: Talent): void => {
        localStorage.setItem('idUser', user.idTalent.toString());
        localStorage.setItem('emailUser', user.mail.toString());
        localStorage.setItem('isAdmin', user.isAdmin.toString());
    }

    return { 
        authenticate,
        saveUser
     };
}