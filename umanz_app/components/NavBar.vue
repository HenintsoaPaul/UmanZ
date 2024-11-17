<template>
  <header class="border-white">
    <template v-if="loggedIn">
      <p>
        Hi
        {{ user?.username }}
      </p>
      <button @click="logout">Log out</button>
    </template>
    <p v-else>Guest</p>
  </header>
</template>

<script setup>
const { loggedIn, user, clear } = useUserSession();

const logout = () => {
  clear();
};
</script>

<style lang="css">
header {
  border-bottom: 1px;
}
</style>