import axios from "axios";
import type { Talent } from "~/types";

export function useAuth() {
    const authenticate = async (email: string, password: string, url: string): Promise<Talent | null> => {
        try {
            const response = await axios.get<Talent>(url, {
                params: { email, password }
            });

            return response.data;
        } catch (err) {
            console.error('Erreur lors de l\'authentication de l\'utilisateur:', err);
            return null;
        }
    }

    return { authenticate };
}