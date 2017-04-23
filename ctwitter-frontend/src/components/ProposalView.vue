<template lang="html">
  <div class="col account-view">
    <div class="row header">
      <div class="col">
        <h1>Proposal</h1>
      </div>
    </div>
    <hr>
    <div class="row">
      <div class="col">
        <app-tweet-composer v-if="proposing" class="composer"
          textInit="" @ok="newEditionReceived($event)" @cancel="proposing = false"></app-tweet-composer>
      </div>
    </div>
    <div class="row editions-container">
      <div class="col-4 ranking-container no-go-container" @ondrop="dropOnNoGo">
        <h5>no-go</h5>
        <app-edition-card
          v-for="edition in proposalGetNoGoEditions()" class="edition-card"
          :key="edition.id" :edition="edition"></app-edition-card>
      </div>
      <div class="col-4 ranking-container neutral-container" @ondrop="dropOnNeutral()">
        <h5>neutral</h5>
        <app-edition-card
          v-for="edition in proposalGetNeutralEditions()" class="edition-card"
          :key="edition.id" :edition="edition"></app-edition-card>
      </div>
      <div class="col-4 ranking-container go-container" @ondrop="dropNoGoNeutral()">
        <h5>go</h5>
        <app-edition-card
          v-for="edition in proposalGetGoEditions()" class="edition-card"
          :key="edition.id" :edition="edition"></app-edition-card>
      </div>

    </div>
  </div>
</template>

<script>
import { mapGetters, mapActions } from 'vuex'

import TweetComposer from './TweetComposer.vue'
import EditionCard from './EditionCard.vue'

export default {
  components: {
    AppEditionCard: EditionCard,
    AppTweetComposer: TweetComposer
  },

  data () {
    return {
      id: 0,
      proposing: false
    }
  },

  methods: {
    ...mapGetters(['proposalGetGoEditions', 'proposalGetNeutralEditions', 'proposalGetNoGoEditions']),
    ...mapActions(['proposalSetActiveAndFetchIfNeeded', 'proposalMoveDraggedToNoGo']),

    dropOnNoGo () {
      debugger
      this.proposalMoveDraggedToNoGo()
    }
  },

  created () {
    this.id = parseInt(this.$route.params.id)
    this.proposalSetActiveAndFetchIfNeeded(this.id)
  }
}
</script>

<style scoped>

.account-view {
  margin-top: 20px;
}

.ranking-container {
  padding-top: 30px;
  padding-bottom: 40px;
  min-height: 150px;
}

.ranking-container h5 {
  text-align: center;
  color: rgb(142, 142, 142);
}

.go-container {
  background-color: rgb(152, 207, 129)
}

.neutral-container {
  background-color: rgb(230, 230, 230)
}

.no-go-container {
  background-color: rgb(255, 172, 172)
}

</style>
