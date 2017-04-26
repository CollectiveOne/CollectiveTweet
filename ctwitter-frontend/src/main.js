// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import { store } from './store/store'
import Auth0Lock from 'auth0-lock'

import axios from 'axios'
import VueAxios from 'vue-axios'

import BootstrapVue from 'bootstrap-vue/dist/bootstrap-vue.esm'
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'

Vue.config.productionTip = false

Vue.use(VueAxios, axios)
Vue.use(BootstrapVue)

axios.interceptors.request.use(
  function (config) {
    config.headers.common.Authorization = 'Bearer ' + localStorage.getItem('id_token')
    return config
  },
  function (error) {
    return Promise.reject(error)
  }
)

var options = {
  auth: {
    params: {
      connectionScopes: {
        connectionName: [ 'openid', 'email', 'user_metadata', 'app_metadata', 'picture' ]
      }
    }
  }
}
export var lock = new Auth0Lock(process.env.AUTH0_CLIENT_ID, process.env.AUTH0_DOMAIN, options)
// export var lock = new Auth0Lock(process.env.AUTH0_CLIENT_ID, process.env.AUTH0_DOMAIN)

/* eslint-disable no-new */
new Vue({
  el: '#app',
  store,
  router,
  template: '<App/>',
  components: { App }
})
