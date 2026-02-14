import { useAuth } from "~/features/auth/useAuth";

export default defineNuxtRouteMiddleware((to, from) => {
    const { logout } = useAuth();

    if (process.client) {
        logout();
    }
});
