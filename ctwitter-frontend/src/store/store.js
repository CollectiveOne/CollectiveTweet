import Vue from 'vue'
import Vuex from 'vuex'

import user from './modules/user'
import proposals from './modules/proposals'

Vue.use(Vuex)

export const store = new Vuex.Store({
  state: {},

  modules: {
    user,
    proposals
  }
})
