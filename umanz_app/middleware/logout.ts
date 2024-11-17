export default defineNuxtRouteMiddleware((to, from) => {
    if (process.client) {
        localStorage.removeItem('idUser');
        localStorage.removeItem('isAdmin');
    }
});
