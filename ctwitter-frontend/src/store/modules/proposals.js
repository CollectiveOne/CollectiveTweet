import Vue from 'vue'

const state = {
  proposals: [],
  activeId: 0,
  editionBeingDragged: null
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
  },
  proposalGetEditionBeingDragged: state => {
    return state.editionBeingDragged
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
  },
  proposalSetEditionBeingDragged: (state, payload) => {
    state.editionBeingDragged = payload
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
  },

  proposalMoveDraggedToNoGo: (context, payload) => {
    debugger
    let edition = context.getters.proposalGetEditionBeingDragged
    let proposal = context.getters.proposalGetActive

    let ix = proposal.editions.findIndex(e => { e.id === edition.id })

    proposal.editions[ix].myvote = 'nogo'
  }
}

export default {
  state,
  getters,
  mutations,
  actions
}
