import Vue from 'vue'

const state = {
  proposals: [],
  activeId: 0
}

const getActiveProposalHelper = (state) => {
  return state.proposals.find((e) => { return e.id === state.activeId })
}

const getters = {
  proposalGetActive: state => {
    return getActiveProposalHelper(state)
  },
  proposalGetGoEditions: state => {
    let proposal = getActiveProposalHelper(state)
    if (proposal != null) {
      return proposal.editions.filter(e => { return e.myvote === 'go' })
    }
    return []
  },
  proposalGetNeutralEditions: state => {
    let proposal = getActiveProposalHelper(state)
    if (proposal != null) {
      return proposal.editions.filter(e => { return e.myvote === 'neutral' })
    }
    return []
  },
  proposalGetNoGoEditions: state => {
    let proposal = getActiveProposalHelper(state)
    if (proposal != null) {
      return proposal.editions.filter(e => { return e.myvote === 'nogo' })
    }
    return []
  }
}

const mutations = {
  proposalAdd: (state, payload) => {
    state.proposals.push(payload)
  },
  proposalSetActiveId: (state, payload) => {
    if (typeof payload === 'string') {
      payload = parseInt(payload)
    }
    state.activeId = payload
  }
}

const actions = {
  proposalSetActiveAndFetchIfNeeded: (context, payload) => {
    context.commit('proposalSetActiveId', payload)
    var proposal = context.getters.proposalGetActive

    if (proposal == null) {
      context.dispatch('proposalFetch', payload)
    }
  },

  proposalFetch: ({ commit }, payload) => {
    Vue.axios.get('1/secured/proposal/' + payload).then((response) => {
      commit('proposalAdd', response.data)
    })
  }
}

export default {
  state,
  getters,
  mutations,
  actions
}
