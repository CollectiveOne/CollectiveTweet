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
      <div class="col-4 ranking-container no-go-container">
        <h5>no-go</h5>
        <app-edition-card
          v-for="edition in noGoEditions" class="edition-card"
          :key="edition.id" :edition="edition"></app-edition-card>
      </div>
      <div class="col-4 ranking-container neutral-container">
        <h5>neutral</h5>
        <app-edition-card
          v-for="edition in neutralEditions" class="edition-card"
          :key="edition.id" :edition="edition"></app-edition-card>
      </div>
      <div class="col-4 ranking-container go-container">
        <h5>go</h5>
        <app-edition-card
          v-for="edition in goEditions" class="edition-card"
          :key="edition.id" :edition="edition"></app-edition-card>
      </div>

    </div>
  </div>
</template>

<script>
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
      proposing: false,
      proposal: null
    }
  },

  methods: {
    dropOnNoGo () {
      debugger
      this.proposalMoveDraggedToNoGo()
    }
  },

  computed: {
    goEditions () {
      if (this.proposal) {
        return this.proposal.editions.filter(e => { return e.myvote === 'go' })
      } else {
        return []
      }
    },
    neutralEditions () {
      if (this.proposal) {
        return this.proposal.editions.filter(e => { return e.myvote === 'neutral' })
      } else {
        return []
      }
    },
    noGoEditions () {
      if (this.proposal) {
        return this.proposal.editions.filter(e => { return e.myvote === 'nogo' })
      } else {
        return []
      }
    }
  },

  created () {
    this.axios.get('1/secured/proposal/' + this.$route.params.id).then((response) => {
      this.proposal = response.data
    })
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
