import type { AuthDTO } from "~/types/dto";

export function useAuth() {
    const authenticate = async (email: string, password: string, apiUrl: string): Promise<AuthDTO> => {
        try {
            const response = await $fetch<AuthDTO>(`${apiUrl}/auth`, {
                method: 'POST',
                body: {
                    email: email,
                    password: password
                }
            });
            // console.log('Authentication data sent successfully', response);
            return response;
        } catch (error) {
            console.error('Failed to send Authentication data', error);
            throw error;
        }
    }

    const saveUser = (authDto: AuthDTO): void => {
        localStorage.setItem('umanz-idUser', authDto.idTalent.toString());
        localStorage.setItem('umanz-emailUser', authDto.email.toString());
        localStorage.setItem('umanz-isAdmin', authDto.admin.toString());
        localStorage.setItem('umanz-idContrat', authDto.idContrat?.toString() || '');
    }

    return { 
        authenticate,
        saveUser
     };
}