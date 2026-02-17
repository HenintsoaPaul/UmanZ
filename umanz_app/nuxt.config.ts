// https://nuxt.com/docs/api/configuration/nuxt-config
export default defineNuxtConfig({
  ssr: false,
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
  colorMode: {
    preference: 'light', // default value of $colorMode.preference
    fallback: 'light', // fallback value if not system preference found
    classSuffix: '',
    storageKey: 'nuxt-color-mode'
  },
  devServer: {
    https: true
  },
  runtimeConfig: {
    public: {
      apiUrl: "https://localhost:8443",
      currency: "Ariary",
    }
  },
})