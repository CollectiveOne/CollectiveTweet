import { lock } from '../../main'

const state = {
  authenticated: false,
  profile: null
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
  }
}

const mutations = {
  authenticate: (state, payload) => {
    state.authenticated = payload
  },
  setProfile: (state, payload) => {
    state.profile = payload
  }
}

const updateProfileHelper = (commit) => {
  if (localStorage.getItem('access_token')) {
    lock.getUserInfo(localStorage.getItem('access_token'), (error, profile) => {
      if (error) {
        return
      }
      commit('setProfile', profile)
    })
  }
}

const actions = {
  updateAuthenticated: ({ commit }) => {
    commit('authenticate', !!localStorage.getItem('access_token'))
    // TODO: call action (updateProfile) within action (updateAuthenticated)
    updateProfileHelper(commit)
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

export default {
  state,
  getters,
  mutations,
  actions
}
