<script setup>
const router = useRouter();
const idUserStr = computed( () => localStorage.getItem( "idUser" ) );
const loggedIn = ref( false );

const idUser = ref(0);

onMounted( () => {
  if ( idUserStr ) {
    idUser.value = Number( idUserStr.value );
    loggedIn.value = true;
  } else {
    logout();
  }
} );

const navLinks = [
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
  {
    label: "Contrats",
    children: [
      { label: "Voir Liste", link: "/interne/contrat" },
      // { label: "Ajouter", link: "/interne/contrat/add" },
    ]
  },
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
      { label: "Voir Liste Projet", link: "/interne/evaluation/projet" },
      { label: "Faire une évaluation", link: "/talent/evaluation/projet" }
    ]
  },
  {
    label: "Chat",
    children: [
      { label: "Chat Guide", link: "/chat" }
    ]
  }
];

const showProfile = () => {
    router.push(`/talent/${idUser.value}`);
}

const logout = () => {
  router.push( "/" );
};
</script>

<template>
      <header class="bg-gray-800 text-white p-4 mb-5">
            <nav class="container mx-auto flex justify-between items-center">
                  <div class="text-xl font-bold">UmanZ</div>
                  <ul class="flex space-x-4">
                        <li>
                            <p class="hover:cursor-pointer" @click="showProfile">Mon profil</p>
                        </li>
                        <li v-for="navLink in navLinks" class="relative group">
                              <p>{{ navLink.label }}</p>
                              <ul
                                    class="absolute z-index-50 left-0 mt-2 w-48 bg-white text-black rounded-lg shadow-lg opacity-0 group-hover:opacity-100 transition-opacity duration-300">
                                    <li v-for="sub in navLink.children">
                                          <router-link :to="sub.link" class="block px-4 py-2 hover:bg-gray-200 hover:rounded-lg z-index-50">
                                                {{ sub.label }}
                                          </router-link>
                                    </li>
                              </ul>
                        </li>
                  </ul>
                  <div>
                        <template v-if="loggedIn">
                              <button @click="logout" class="bg-red-500 hover:bg-red-700 text-white font-bold py-2 px-4 rounded">Log
                                out</button>
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