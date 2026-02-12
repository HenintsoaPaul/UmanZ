<script setup lang="ts">
const router = useRouter();
const loggedIn = ref(false);
const isAdmin = ref(false);

interface NavLinkChild {
    label: string;
    link: string;
    icon?: string;
}

interface NavLink {
    label: string;
    icon: string;
    children?: NavLinkChild[];
    link?: string;
}

const navLinks: NavLink[] = [
    {
        label: "Annonces",
        icon: "i-heroicons-megaphone",
        children: [
            { label: "Voir Liste", link: "/recrutement/annonce" },
            { label: "Ajouter", link: "/recrutement/annonce/add" },
        ]
    },
    {
        label: "Entretiens",
        icon: "i-heroicons-user-group",
        children: [
            { label: "Premiers Entretiens", link: "/recrutement/entretien" },
            { label: "Premiers Entretiens Valides", link: "/recrutement/entretien/first-valid" },
            { label: "Seconds Entretiens", link: "/recrutement/entretien/second" },
            { label: "Seconds Entretiens Valides", link: "/recrutement/entretien/second-valid" },
        ]
    },
    {
        label: "Formations",
        icon: "i-heroicons-academic-cap",
        children: [
            { label: "Voir Liste", link: "/interne/formation" },
            { label: "Ajouter", link: "/interne/formation/add" },
        ]
    },
    {
        label: "Conges",
        icon: "i-heroicons-calendar",
        children: [
            { label: "Voir Liste", link: "/interne/conge" },
            { label: "Ajouter", link: "/interne/conge/add" },
        ]
    },
    {
        label: "Absences",
        icon: "i-heroicons-clock",
        children: [
            { label: "Voir Liste", link: "/interne/absence" },
            { label: "Ajouter", link: "/interne/absence/add" },
        ]
    },
    {
        label: "Evaluation",
        icon: "i-heroicons-chart-bar",
        children: [
            { label: "Voir Les résultats", link: "/talent/evaluation/resultat" },
            { label: "Faire une évaluation", link: "/talent/evaluation/domaine" }
        ]
    },
    {
        label: "Paie",
        icon: "i-heroicons-banknotes",
        children: [
            { label: "Etat de paie", link: "/interne/paie" },
        ]
    },
    {
        label: "Chat",
        icon: "i-heroicons-chat-bubble-left-right",
        link: "/chat"
    },
    {
        label: "Heure supplementaire",
        icon: "i-heroicons-plus-circle",
        link: "/interne/heure_sup/add"
    }
];

onMounted(() => {
    if (process.client) {
        const adminStatus = localStorage.getItem("umanz-isAdmin");
        isAdmin.value = adminStatus === 'true' || adminStatus === '1';
        loggedIn.value = !!localStorage.getItem("umanz-idUser");
    }
});

const showProfile = () => {
    if (process.client) {
        const idUser = localStorage.getItem("umanz-idUser");
        router.push(`/talent/${idUser}`);
    }
};

const showEmpList = () => {
    router.push(`/interne/emp`);
};

const logout = async () => {
    if (process.client) {
        localStorage.removeItem("umanz-idUser");
        localStorage.removeItem("umanz-isAdmin");
    }
    await router.push("/");
};

const openMenus = ref<string[]>([]);

const toggleMenu = (label: string) => {
    if (openMenus.value.includes(label)) {
        openMenus.value = openMenus.value.filter(l => l !== label);
    } else {
        openMenus.value.push(label);
    }
};

const isMenuActive = (label: string) => openMenus.value.includes(label);
</script>

<template>
    <aside class="w-64 bg-white border-r border-gray-100 flex flex-col h-screen sticky top-0">
        <div class="p-6">
            <div class="text-2xl font-bold flex items-center gap-2">
                <span class="w-8 h-8 bg-umanz-purple rounded-lg flex items-center justify-center text-white text-sm">U</span>
                <span class="text-gray-900">UmanZ</span>
            </div>
        </div>

        <nav class="flex-1 overflow-y-auto px-4 py-2 space-y-1 custom-scrollbar">
            <!-- Profile Section -->
            <div class="mb-6">
                <p class="text-xs font-semibold text-gray-400 uppercase tracking-wider px-2 mb-2">Compte</p>
                <button @click="showProfile" class="w-full flex items-center gap-3 px-3 py-2 text-gray-600 hover:bg-gray-50 rounded-xl transition-all duration-200 group">
                    <UIcon name="i-heroicons-user-circle" class="text-xl group-hover:text-umanz-purple" />
                    <span class="font-medium text-sm">Mon profil</span>
                </button>
                <button v-if="isAdmin" @click="showEmpList" class="w-full flex items-center gap-3 px-3 py-2 text-gray-600 hover:bg-gray-50 rounded-xl transition-all duration-200 group">
                    <UIcon name="i-heroicons-users" class="text-xl group-hover:text-umanz-green" />
                    <span class="font-medium text-sm">Nos employés</span>
                </button>
            </div>

            <!-- Main Navigation -->
            <p class="text-xs font-semibold text-gray-400 uppercase tracking-wider px-2 mb-2">Navigation</p>
            <div v-for="item in navLinks" :key="item.label">
                <template v-if="item.children">
                    <button @click="toggleMenu(item.label)" 
                        class="w-full flex items-center justify-between px-3 py-2 text-gray-600 hover:bg-gray-50 rounded-xl transition-all duration-200 group"
                        :class="{'bg-gray-50 text-gray-900 font-semibold': isMenuActive(item.label)}">
                        <div class="flex items-center gap-3">
                            <UIcon :name="item.icon" class="text-xl group-hover:text-umanz-orange" />
                            <span class="text-sm">{{ item.label }}</span>
                        </div>
                        <UIcon name="i-heroicons-chevron-right" 
                            class="transition-transform duration-200"
                            :class="{'rotate-90': isMenuActive(item.label)}" />
                    </button>
                    <div v-if="isMenuActive(item.label)" class="mt-1 space-y-1 ml-4 pl-4 border-l border-gray-100">
                        <router-link v-for="sub in item.children" :key="sub.link" :to="sub.link"
                            class="block px-3 py-2 text-sm text-gray-500 hover:text-gray-900 hover:bg-gray-50 rounded-lg transition-all">
                            {{ sub.label }}
                        </router-link>
                    </div>
                </template>
                <router-link v-else :to="item.link!"
                    class="flex items-center gap-3 px-3 py-2 text-gray-600 hover:bg-gray-50 rounded-xl transition-all duration-200 group">
                    <UIcon :name="item.icon" class="text-xl group-hover:text-umanz-purple" />
                    <span class="text-sm">{{ item.label }}</span>
                </router-link>
            </div>
        </nav>

        <!-- Logout Section -->
        <div class="p-4 border-t border-gray-100">
            <button @click="logout" class="w-full flex items-center gap-3 px-3 py-2 text-red-500 hover:bg-red-50 rounded-xl transition-all duration-200 group">
                <UIcon name="i-heroicons-arrow-left-on-rectangle" class="text-xl" />
                <span class="font-medium text-sm">Déconnexion</span>
            </button>
        </div>
    </aside>
</template>

<style scoped>
.custom-scrollbar::-webkit-scrollbar {
    width: 4px;
}
.custom-scrollbar::-webkit-scrollbar-track {
    background: transparent;
}
.custom-scrollbar::-webkit-scrollbar-thumb {
    background: #f1f5f9;
    border-radius: 10px;
}
.custom-scrollbar::-webkit-scrollbar-thumb:hover {
    background: #e2e8f0;
}
</style>
