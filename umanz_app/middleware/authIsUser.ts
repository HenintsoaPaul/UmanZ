import { navigateTo } from 'nuxt/app';

export default defineNuxtRouteMiddleware(() => {
    if (process.client) {
        const isAdmin = localStorage.getItem("umanz-isAdmin") === 'false';

        if (!isAdmin) {
            return navigateTo('/NotAllowed');
        }
    }
});
