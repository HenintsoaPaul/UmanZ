export default defineNuxtRouteMiddleware((to, from) => {
    if (process.client) {
        localStorage.removeItem('umanz-idUser');
        localStorage.removeItem('umanz-emailUser');
        localStorage.removeItem('umanz-isAdmin');

        localStorage.removeItem('umanz-idContrat');
    }
});
