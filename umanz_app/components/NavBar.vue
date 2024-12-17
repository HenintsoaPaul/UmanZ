<script setup lang="ts">
const router = useRouter();
const loggedIn = ref( false );

interface NavLinkChild {
    label: string;
    link: string;
}

interface NavLink {
    label: string;
    children: NavLinkChild[];
}

const navLinks : NavLink[] = [
  {
    label: "Annonces",
    children: [
      { label: "Voir Liste", link: "/recrutement/annonce" },
      { label: "Ajouter", link: "/recrutement/annonce/add" },
    ]
  },
  {
    label: "Entretiens",
    children: [
      { label: "Premiers Entretiens", link: "/recrutement/entretien" },
      { label: "Premiers Entretiens Valides", link: "/recrutement/entretien/first-valid" },
      { label: "Seconds Entretiens", link: "/recrutement/entretien/second" },
      { label: "Seconds Entretiens Valides", link: "/recrutement/entretien/second-valid" },
    ]
  },
  {
    label: "Formations",
    children: [
      { label: "Voir Liste", link: "/interne/formation" },
      { label: "Ajouter", link: "/interne/formation/add" },
    ]
  },
  // {
  //   label: "Contrats",
  //   children: [
  //     { label: "Voir Liste", link: "/interne/contrat" },
  //     // { label: "Ajouter", link: "/interne/contrat/add" },
  //   ]
  // },
  {
    label: "Conges",
    children: [
      { label: "Voir Liste", link: "/interne/conge" },
      { label: "Ajouter", link: "/interne/conge/add" },
    ]
  },
  {
    label: "Absences",
    children: [
      { label: "Voir Liste", link: "/interne/absence" },
      { label: "Ajouter", link: "/interne/absence/add" },
    ]
  },
  {
    label: "Evaluation",
    children: [
      { label: "Voir Les résultats", link: "/talent/evaluation/resultat" },
      { label: "Faire une évaluation", link: "/talent/evaluation/domaine" }
    ]
  },
    {
        label: "Paie",
        children: [
            { label: "Etat de paie", link: "/interne/paie" },
        ]
    },
  {
    label: "Chat",
    children: [
      { label: "Chat Guide", link: "/chat" }
    ]
  },
  {
    label: "Heure supp",
    children: [
      { label: "Insertion heure supplementaire", link: "/interne/heure_sup/add" }
    ]
  }
];

const isAdmin = ref(false);

onMounted(() => {
    if (process.client) {
        const bb = localStorage.getItem("umanz-isAdmin");
        isAdmin.value = bb ? Boolean(bb) : false;
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
    await router.push("/");
};
</script>

<template>
      <header class="bg-gray-800 text-white p-4 mb-5">
            <nav class="container mx-auto flex justify-between items-center">
              <div class="w-full flex-1 justify-center align-baseline">
                <div class="text-xl font-bold">UmanZ</div>
              </div>
              <div class="w-full ml-auto flex-1 justify-center align-baseline mx-5">
                <ul class="flex space-x-4">
                      <li>
                          <p class="hover:cursor-pointer" @click="showProfile">Mon profil</p>
                      </li>
                      <li v-if="isAdmin">
                          <p class="hover:cursor-pointer" @click="showEmpList">Nos employes</p>
                      </li>
                      <li v-for="navLink in navLinks" class="relative group">
                            <p class="cursor-pointer">{{ navLink.label }}</p>
                            <ul
                                  class="absolute z-50 left-0 mb-5 w-48 bg-white text-black rounded-lg shadow-lg group-hover:block hidden duration-300">
                                  <li v-for="sub in navLink.children">
                                        <router-link :to="sub.link" class="block px-4 py-2 hover:bg-gray-200 hover:rounded-lg z-index-50">
                                              {{ sub.label }}
                                        </router-link>
                                  </li>
                            </ul>
                      </li>
                </ul>
              </div>
                  <div class="w-full ml-auto flex-1 justify-center align-baseline">
                        <template v-if="loggedIn">
                              <button @click="logout" class="bg-red-500 hover:bg-red-700 text-white font-bold py-2 px-4 rounded">
                                  Me déconnecter
                              </button>
                        </template>
                  </div>
            </nav>
      </header>
</template>

<style lang="css">
header {
  border-bottom: 1px solid white;
}
</style>
