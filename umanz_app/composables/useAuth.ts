import type { Talent } from "~/types";

export function useAuth() {
    const authenticate = async (email: string, password: string): Promise<Talent | null> => {
        try {
            const apiUrl = useRuntimeConfig().public.apiUrl;
            const url = `${apiUrl}/users`;
            const { data, error } = useFetch<Talent>(url, {
                params: { email, password }
            });

            if (error.value) {
                console.error('Erreur lors de l\'authentication de l\'utilisateur:', error.value);
                return null;
            }

            return data.value;
        } catch (err) {
            console.error('Erreur lors de l\'authentication de l\'utilisateur:', err.value);
            return null;
        }
    }

    return { authenticate };
}