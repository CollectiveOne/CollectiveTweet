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
        <app-tweet-fork-form v-if="proposing" :originalTweet="originalTweetToEdit"
          @editionProposed="newEdition($event)"></app-tweet-fork-form>
      </div>
    </div>
    <div class="row editions-container">
      <div class="col-4 ranking-column" :class="{ 'dropping': draggingOverNoGo }"
        @dragover.prevent @dragenter="draggingOverNoGo = true" @dragleave="draggingOverNoGo = false" @drop="dropOnNoGo">
        <h5>no-go</h5>
        <div class="ranking-dropzone no-go-dropzone" :class="{ 'dropping': draggingOverNoGo }">
          <app-edition-card
            v-for="edition in noGoEditions" class="edition-card"
            :key="edition.id" :edition="edition" @fork-me="forkEdition($event)"
            @dragging-me="draggingEditionStart($event)"></app-edition-card>
        </div>
      </div>
      <div class="col-4 ranking-column"
        @dragover.prevent @dragenter="draggingOverNeutral = true" @dragleave="draggingOverNeutral = false" @drop="dropOnNeutral">
        <h5>neutral</h5>
        <div class="ranking-dropzone neutral-dropzone" :class="{ 'dropping': draggingOverNeutral }">
          <app-edition-card
            v-for="edition in neutralEditions" class="edition-card"
            :key="edition.id" :edition="edition" @fork-me="forkEdition($event)"
            @dragging-me="draggingEditionStart($event)"></app-edition-card>
        </div>
      </div>
      <div class="col-4 ranking-column"
        @dragover.prevent @dragenter="draggingOverGo = true" @dragleave="draggingOverGo = false" @drop="dropOnGo">
        <h5>go</h5>
        <div class="ranking-dropzone go-dropzone" :class="{ 'dropping': draggingOverGo }">
          <app-edition-card
            v-for="edition in goEditions" class="edition-card"
            :key="edition.id" :edition="edition" @fork-me="forkEdition($event)"
            @dragging-me="draggingEditionStart($event)"></app-edition-card>
          </div>
      </div>

    </div>
  </div>
</template>

<script>
import TweetForkForm from './TweetForkForm.vue'
import EditionCard from './EditionCard.vue'

export default {
  components: {
    AppEditionCard: EditionCard,
    AppTweetForkForm: TweetForkForm
  },

  data () {
    return {
      id: 0,
      proposing: false,
      originalTweetToEdit: null,
      textInit: '',
      proposal: null,
      draggingOverNoGo: false,
      draggingOverNeutral: false,
      draggingOverGo: false,
      draggingEdition: null
    }
  },

  computed: {
    goEditions () {
      if (this.proposal) {
        return this.proposal.editions.filter(e => { return e.myRank > 0 })
      } else {
        return []
      }
    },
    neutralEditions () {
      if (this.proposal) {
        return this.proposal.editions.filter(e => { return e.myRank === 0 })
      } else {
        return []
      }
    },
    noGoEditions () {
      if (this.proposal) {
        return this.proposal.editions.filter(e => { return e.myRank < 0 })
      } else {
        return []
      }
    }
  },

  methods: {
    dropOnNoGo () {
      this.draggingOverNoGo = false
      let edition = this.proposal.editions.find(e => { return e.id === this.draggingEdition.id })
      edition.myRank = -1
      this.updateEdition(edition)
    },

    dropOnNeutral () {
      this.draggingOverNeutral = false
      let edition = this.proposal.editions.find(e => { return e.id === this.draggingEdition.id })
      edition.myRank = 0
      this.updateEdition(edition)
    },

    dropOnGo () {
      this.draggingOverGo = false
      let edition = this.proposal.editions.find(e => { return e.id === this.draggingEdition.id })
      edition.myRank = 1
      this.updateEdition(edition)
    },

    draggingEditionStart (edition) {
      this.draggingEdition = edition
    },

    newEdition (edition) {
      this.axios.post('/1/secured/proposal/' + this.proposal.id + '/edition', edition)
    },

    updateEdition (edition) {
      this.axios.put('/1/secured/proposal/' + this.proposal.id + '/edition/' + edition.id + '/rank', {}, {
        params: {
          'myRank': edition.myRank
        }
      })
    },

    forkEdition (edition) {
      this.originalTweetToEdit = edition
      this.proposing = true
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

.editions-container {
}

.ranking-column {
}

.ranking-dropzone {
  padding: 15px;
  width: 100%;
  height: 100%;
  border-style: dotted;
  border-radius: 10px;
  border-width: thick;
}

.ranking-column h5 {
  text-align: center;
  color: rgb(142, 142, 142);
}

.go-dropzone {
  border-color: rgb(107, 194, 113)
}

.neutral-dropzone {
  border-color: rgb(222, 222, 222)
}

.no-go-dropzone {
  border-color: rgb(255, 84, 84);
}

.dropping {
  border-color: rgb(255, 227, 129)
}

.edition-card {
  margin-bottom: 10px;
}

</style>
