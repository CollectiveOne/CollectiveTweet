import Vue from 'vue'
import { lock } from '../../main'

const state = {
  authenticated: false,
  profile: null,
  accounts: []
}

const getters = {
  authenticated: state => {
    return state.authenticated
  },
  profileImage: state => {
    // return 'https://www.w3schools.com/images/w3schools_green.jpg'
    if (state.profile) {
      return state.profile.picture
    } else {
      return ''
    }
  },
  userNickname: state => {
    if (state.profile) {
      return state.profile.nickname
    } else {
      return ''
    }
  },
  myAccounts: state => {
    return state.accounts
  }
}

const mutations = {
  authenticate: (state, payload) => {
    state.authenticated = payload
  },
  setProfile: (state, payload) => {
    state.profile = payload
  },
  setAccounts: (state, payload) => {
    state.accounts = payload
  }
}

const actions = {
  updateAuthenticated: (context) => {
    context.commit('authenticate', !!localStorage.getItem('access_token'))
    context.dispatch('updateProfile')
  },

  updateProfile: ({ commit }) => {
    updateProfileHelper(commit)
  },

  logout: ({ commit }) => {
    localStorage.removeItem('access_token')
    localStorage.removeItem('id_token')
    commit('authenticate', false)
    commit('setProfile', null)
  }
}

const updateProfileHelper = (commit) => {
  /* user profile */
  if (localStorage.getItem('access_token')) {
    lock.getUserInfo(localStorage.getItem('access_token'), (error, profile) => {
      if (error) {
        return
      }
      commit('setProfile', profile)
    })
  }

  /* accounts data */
  Vue.axios.get('/1/secured/account/mines').then((response) => {
    commit('setAccounts', response.data)
  })
}

export default {
  state,
  getters,
  mutations,
  actions
}
