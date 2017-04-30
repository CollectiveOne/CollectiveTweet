<template lang="html">
  <div class="col account-view">
    <div class="row header">
      <div class="col">
        <a :href="'https://www.twitter.com/' + account.twitterHandle">
          <h2>@{{ account.twitterHandle }}</h2>
        </a>
      </div>
    </div>
    <div class="row members">
      <div class="col">
        <div class="badge badge-default">{{ nMembers }} members:</div>
        <div v-for="member in account.members">
          <p>{{ member.nickname }}</p>
        </div>
      </div>
    </div>
    <hr>
    <div class="row">
      <div class="col btn-row">
        <b-btn @click="proposeClick()" variant="primary">propose tweet</b-btn>
        <app-user-selector class="add-user-selector"
          @select="addUserSelected($event)"
          anchor="nickname" placeholder="add user"
          url="/1/secured/user/suggestions">
        </app-user-selector>
      </div>
    </div>
    <div class="row">
      <div class="col">
        <app-tweet-composer  v-if="proposing" class="composer" :accountId="account.id"
          @ok="newProposalReceived($event)" @cancel="proposing = false">
        </app-tweet-composer>
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
import UserSelector from './UserSelector.vue'

export default {
  components: {
    AppTweetComposer: TweetComposer,
    AppTweetProposalCard: TweetProposalCard,
    AppUserSelector: UserSelector
  },

  data () {
    return {
      proposing: false,
      account: {
        id: '0',
        twitterHandle: 'example',
        proposals: [],
        members: []
      }
    }
  },

  computed: {
    nMembers () {
      if (this.account.members) {
        return this.account.members.length
      } else {
        return 0
      }
    }
  },

  methods: {
    proposeClick () {
      this.proposing = true
    },

    addUserSelected (user) {
      console.log(user)
      // this.axios.post('/1/secured/account/addUser', {
      //   params: {
      //     userId: user.userId
      //   }
      // })
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
    var _this = this

    var getAccount = () => {
      return _this.axios.get('1/secured/account/' + _this.$route.params.id)
    }

    var getAccountMembers = () => {
      return _this.axios.get('1/secured/account/' + _this.$route.params.id + '/members')
    }

    this.axios.all([getAccount(), getAccountMembers()]).then(this.axios.spread((accountResponse, membersResponse) => {
      this.account = accountResponse.data
      this.account.members = membersResponse.data
    }))
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

.btn-row button {
  width: 155px;
  float: left;
}

.add-user-selector {
  float: right;
  width: 250px;
  margin-left: 10px;

}

.composer {
  margin-top: 10px;
}

.proposals-container {
  margin-top: 20px;
}


</style>
