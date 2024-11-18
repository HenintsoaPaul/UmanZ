import { navigateTo } from 'nuxt/app';

export default defineNuxtRouteMiddleware((to, from) => {
    if (process.client) {
        const isAdmin = localStorage.getItem("isAdmin") === 'true';

        if (!isAdmin) {
            return navigateTo('/NotAllowed');
        }
    }
});
