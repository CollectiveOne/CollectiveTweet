<template lang="html">
  <div class="col account-view">
    <div class="row header">
      <div class="col">
        <a :href="'https://www.twitter.com/' + account.twitterHandle">
          <h2>@{{ account.twitterHandle }}</h2>
        </a>
      </div>
    </div>
    <hr>
    <div class="row">
      <div class="col">
        <b-btn v-if="!proposing" @click="proposeClick()" variant="primary">propose tweet</b-btn>
        <app-tweet-composer v-if="proposing" class="composer" :accountId="account.id"
          @ok="newProposalReceived($event)" @cancel="proposing = false"></app-tweet-composer>
      </div>
    </div>
    <hr>
    <div class="row proposals-container">
      <div class="col">
        <app-tweet-proposal-card v-for="proposal in account.proposals" :key="proposal.id" :proposal="proposal"></app-tweet-proposal-card>
      </div>
    </div>
  </div>
</template>

<script>

import TweetComposer from './TweetComposer.vue'
import TweetProposalCard from './TweetProposalCard.vue'

export default {
  components: {
    AppTweetComposer: TweetComposer,
    AppTweetProposalCard: TweetProposalCard
  },

  data () {
    return {
      proposing: false,
      account: {
        id: '0',
        twitterHandle: 'example',
        proposals: []
      }
    }
  },

  methods: {
    proposeClick () {
      this.proposing = true
    },

    newProposalReceived (data) {
      this.proposing = false
      this.axios.post('1/secured/proposal', data, {
        params: {
          'accountId': this.account.id
        }
      }).then((response) => {
        this.account = response.data
      })
    }
  },

  created () {
    this.axios.get('1/secured/account/' + this.$route.params.id).then((response) => {
      this.account = response.data
    })
  }
}
</script>

<style scoped>

.header {
  margin-bottom: 20px;
}

.account-view {
  margin-top: 20px;
}

.composer {
  margin-top: 10px;
}

.proposals-container {
  margin-top: 20px;
}

</style>
