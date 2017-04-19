<template>
  <div id="app">
    <component :is="view"></component>
  </div>
</template>

<script>
import Vue from 'vue'
import { lock } from './main'
import { mapGetters, mapActions } from 'vuex'

import LandingView from '@/components/LandingView.vue'
import LoggedView from '@/components/LoggedView.vue'

export default {
  name: 'app',

  methods: {
    ...mapGetters(['authenticated']),
    ...mapActions(['updateAuthenticated'])
  },

  computed: {
    view () {
      if (this.authenticated()) {
        return 'app-logged-view'
      } else {
        return 'app-landing-view'
      }
    }
  },

  components: {
    AppLandingView: LandingView,
    AppLoggedView: LoggedView
  },

  mounted () {
    var self = this

    Vue.nextTick(function () {
      self.updateAuthenticated()

      lock.on('authenticated', (authResult) => {
        localStorage.setItem('access_token', authResult.accessToken)
        localStorage.setItem('id_token', authResult.idToken)
        self.updateAuthenticated()
      })

      lock.on('authorization_error', (error) => {
        console.log(error)
      })
    })
  }

}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;

}

button {
  cursor: pointer;
}

a {
  cursor: pointer;
}

</style>
