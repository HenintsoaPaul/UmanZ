import { navigateTo } from 'nuxt/app';

export default defineNuxtRouteMiddleware(() => {
    if (process.client) {
        const isNotEmp = localStorage.getItem("umanz-idContrat") === '';

        if (isNotEmp) {
            return navigateTo('/NotAllowed');
        }
    }
});
