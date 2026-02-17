import { useAuth } from "~/features/auth/useAuth";

export default defineNuxtRouteMiddleware((to, from) => {
    // List of public paths that don't require authentication
    const publicPaths = ['/login', '/register'];

    // If navigating to a public path, skip authentication check
    if (publicPaths.includes(to.path)) {
        return;
    }

    const { getSession } = useAuth();

    // Auth check - mainly client-side due to localStorage usage
    if (process.client) {
        const session = getSession();

        if (!session) {
            console.warn(`Unauthorized access to ${to.path}: redirecting to /login`);
            return navigateTo('/login');
        }
    }
});
