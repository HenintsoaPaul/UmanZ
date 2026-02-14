import type { LoginResponse } from "~/features/auth/auth.types";

export function useAuth() {
    const authenticate = async (email: string, password: string, apiUrl: string): Promise<LoginResponse> => {
        try {
            const response = await $fetch<LoginResponse>(`${apiUrl}/auth`, {
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

    const beginUserSession = (loginResponse: LoginResponse): void => {
        localStorage.setItem('umanz-idUser', loginResponse.idTalent.toString());
        localStorage.setItem('umanz-emailUser', loginResponse.email.toString());
        localStorage.setItem('umanz-isAdmin', loginResponse.admin.toString());
        localStorage.setItem('umanz-idContrat', loginResponse.idContrat?.toString() || '');
    }

    return {
        authenticate,
        beginUserSession
    };
}