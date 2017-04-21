import Vue from 'vue'
import Router from 'vue-router'

import AccountsBrowser from '../components/AccountsBrowser.vue'
import AccountView from '../components/AccountView.vue'

Vue.use(Router)

export default new Router({
  routes: [
    { path: '/home', name: 'Home', component: AccountsBrowser },
    { path: '/account/:id', name: 'Account', component: AccountView }

  ]
})
