import Vue from 'vue'
import Router from 'vue-router'

import AccountsBrowser from '../components/AccountsBrowser.vue'

Vue.use(Router)

export default new Router({
  routes: [
    { path: '/home', name: 'Home', component: AccountsBrowser }
  ]
})
