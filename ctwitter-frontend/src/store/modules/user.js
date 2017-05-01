import Vue from 'vue'

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

  updateProfile: (context) => {
    /* user profile */
    if (context.getters.authenticated) {
      Vue.axios.get('/1/secured/user/myProfile').then((response) => {
        context.commit('setProfile', response.data)
      })

      /* accounts data */
      Vue.axios.get('/1/secured/account/mines').then((response) => {
        context.commit('setAccounts', response.data)
      }).catch((error) => {
        if (error.response.status === 401) {
          context.dispatch('logout')
        }
      })
    }
  },

  logout: ({ commit }) => {
    localStorage.removeItem('access_token')
    localStorage.removeItem('id_token')
    commit('authenticate', false)
    commit('setProfile', null)
  }
}

export default {
  state,
  getters,
  mutations,
  actions
}
