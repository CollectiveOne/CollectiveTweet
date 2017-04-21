<template lang="html">
  <div class="">
    <b-navbar toggleable type="inverse" variant="inverse">
      <b-nav-toggle target="nav_collapse"></b-nav-toggle>
      <b-link class="navbar-brand" to="#">
        <img src="../assets/logo.png" class="logo">
      </b-link>
      <b-collapse is-nav id="nav_collapse">
        <b-nav is-nav-bar>
          <b-nav-item>accounts</b-nav-item>
        </b-nav>
        <b-nav is-nav-bar class="ml-auto">
          <b-nav-item right-alignment>{{ userNickname() }}</b-nav-item>
          <b-nav-item right-alignment>logout</b-nav-item>
        </b-nav>
      </b-collapse>
    </b-navbar>
    <b-modal id="newAccountModal" :hide-header="true" :hide-footer="true">
      <app-new-account></app-new-account>
    </b-modal>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'

import NewAccount from './NewAccount.vue'

export default {
  components: {
    AppNewAccount: NewAccount
  },

  methods: {
    ...mapGetters(['userNickname'])
  }
}
</script>

<style scoped>

.navbar {
  background-color: #026AA7 !important;
}

.logo {
  width: 200px;
  margin-bottom: 5px;
}

</style>
