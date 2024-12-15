// https://nuxt.com/docs/api/configuration/nuxt-config
export default defineNuxtConfig({
  compatibilityDate: '2024-04-03',
  devtools: {
    enabled: true,

    timeline: {
      enabled: true
    }
  },
  app: {
    pageTransition: {
      name: 'page',
      mode: 'out-in'
    }
  },
  modules: [
    '@nuxt/ui',
    'nuxt-auth-utils',
    '@samk-dev/nuxt-vcalendar',
  ],
  runtimeConfig: {
    public: {
      apiUrl: "http://localhost:911",
      currency: "Ariary",
    }
  },
})