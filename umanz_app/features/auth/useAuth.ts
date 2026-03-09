import type { LoginResponse } from "~/features/auth/auth.types";

export function useAuth() {
    const login = async (email: string, password: string, apiUrl: string): Promise<LoginResponse> => {
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

    const setSession = (loginResponse: LoginResponse): void => {
        localStorage.setItem('umanz-idUser', loginResponse.idTalent.toString());
        localStorage.setItem('umanz-emailUser', loginResponse.email.toString());
        localStorage.setItem('umanz-isAdmin', loginResponse.admin.toString());
        localStorage.setItem('umanz-idContrat', loginResponse.idContrat?.toString() || '');
    }

    const getSession = () => {
        if (!process.client) return null;

        const idUser = localStorage.getItem('umanz-idUser');
        if (!idUser) return null;

        return {
            idUser: Number(idUser),
            email: localStorage.getItem('umanz-emailUser'),
            isAdmin: localStorage.getItem('umanz-isAdmin') === 'true',
            idContrat: localStorage.getItem('umanz-idContrat') ? Number(localStorage.getItem('umanz-idContrat')) : null
        };
    }

    const logout = () => {
        localStorage.clear();
        navigateTo('/login');
    }

    const verifyMfa = async (email: string, code?: number, scratchCode?: string, apiUrl?: string): Promise<LoginResponse> => {
        try {
            const config = useRuntimeConfig();
            const baseUrl = apiUrl || config.public.apiUrl;
            const response = await $fetch<LoginResponse>(`${baseUrl}/auth/mfa/verify`, {
                method: 'POST',
                body: {
                    email,
                    code,
                    scratchCode
                }
            });
            return response;
        } catch (error) {
            console.error('Failed to verify MFA', error);
            throw error;
        }
    }

    const setupMfa = async (email: string, apiUrl: string) => {
        return await $fetch<{ secret: string, qrCodeUri: string }>(`${apiUrl}/auth/mfa/setup`, {
            method: 'POST',
            body: { email }
        });
    }

    const confirmMfa = async (email: string, code: number, apiUrl: string) => {
        return await $fetch<{ scratchCodes: string[] }>(`${apiUrl}/auth/mfa/confirm`, {
            method: 'POST',
            body: { email, code }
        });
    }

    return {
        login,
        verifyMfa,
        setupMfa,
        confirmMfa,
        setSession,
        getSession,
        logout
    };
}