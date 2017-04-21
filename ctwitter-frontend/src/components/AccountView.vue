<template lang="html">
  <div class="col account-view">
    <a :href="'https://www.twitter.com/' + account.twitterHandle">
      <h2>@{{ account.twitterHandle }}</h2>
    </a>
    <b-btn v-if="!proposing" @click="proposeClick()" variant="primary">propose tweet</b-btn>
    <app-tweet-composer v-if="proposing" class="composer" :accountId="account.id"
      @done="proposing = false"></app-tweet-composer>
  </div>
</template>

<script>

import TweetComposer from './TweetComposer.vue'

export default {
  components: {
    AppTweetComposer: TweetComposer
  },

  data () {
    return {
      proposing: false,
      account: {
        twitterHandle: 'example',
        id: '0'
      }
    }
  },

  methods: {
    proposeClick () {
      this.proposing = true
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

.account-view {
  margin-top: 20px;
}

.composer {
  margin-top: 10px;
}

</style>
