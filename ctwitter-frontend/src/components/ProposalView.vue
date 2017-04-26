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
      <div class="col-4 ranking-column">
        <h5>no-go</h5>
        <div class="ranking-dropzone no-go-dropzone">
          <app-edition-card
            v-for="edition in noGoEditions" class="edition-card" :data-edition-id="edition.id"
            :key="edition.id" :edition="edition" @newEditionProposed="newEdition($event)"
            @dragging-me="draggingEditionStart($event)" @ed-drop="dropOnEdition($event)">
          </app-edition-card>
          <div class="empty-drop-zone" data-zone-type="NOGO"
            @dragover.prevent @dragenter="dragEnterDropZone($event)" @dragleave="dragLeaveDropZone($event)" @drop="dropOnZone($event)">
          </div>
        </div>
      </div>
      <div class="col-4 ranking-column">
        <h5>neutral</h5>
        <div class="ranking-dropzone neutral-dropzone">
          <app-edition-card
            v-for="edition in neutralEditions" class="edition-card" :data-edition-id="edition.id"
            :key="edition.id" :edition="edition" @newEditionProposed="newEdition($event)"
            @dragging-me="draggingEditionStart($event)" @ed-drop="dropOnEdition($event)">
          </app-edition-card>
          <div class="empty-drop-zone" data-zone-type="NEUTRAL"
            @dragover.prevent @dragenter="dragEnterDropZone($event)" @dragleave="dragLeaveDropZone($event)" @drop="dropOnZone($event)">
          </div>
        </div>
      </div>
      <div class="col-4 ranking-column">
        <h5>go</h5>
        <div class="ranking-dropzone go-dropzone">
          <app-edition-card
            v-if="goEditions.length > 0" v-for="edition in goEditions" class="edition-card" :data-edition-id="edition.id"
            :key="edition.id" :edition="edition" @newEditionProposed="newEdition($event)"
            @dragging-me="draggingEditionStart($event)" @ed-drop="dropOnEdition($event)">
          </app-edition-card>
          <div class="empty-drop-zone" data-zone-type="GO"
            @dragover.prevent @dragenter="dragEnterDropZone($event)" @dragleave="dragLeaveDropZone($event)" @drop="dropOnZone($event)">
          </div>
        </div>
      </div>

    </div>
  </div>
</template>

<script>
import EditionCard from './EditionCard.vue'

const sortByMyRank = (a, b) => {
  if (a.myRank > b.myRank) {
    return 1
  } else if (a.myRank < b.myRank) {
    return -1
  }
  return 0
}

export default {
  components: {
    AppEditionCard: EditionCard
  },

  data () {
    return {
      id: 0,
      proposing: false,
      originalTweetToEdit: null,
      textInit: '',
      proposal: null,
      draggingEdition: null
    }
  },

  computed: {
    goEditions () {
      if (this.proposal) {
        let editions = this.proposal.editions.filter(e => { return e.myRankType === 'GO' })
        return editions.sort(sortByMyRank)
      } else {
        return []
      }
    },
    neutralEditions () {
      if (this.proposal) {
        let editions = this.proposal.editions.filter(e => { return e.myRankType === 'NEUTRAL' })
        return editions.sort(sortByMyRank)
      } else {
        return []
      }
    },
    noGoEditions () {
      if (this.proposal) {
        let editions = this.proposal.editions.filter(e => { return e.myRankType === 'NOGO' })
        return editions.sort(sortByMyRank)
      } else {
        return []
      }
    }
  },

  methods: {
    getEditionById (id) {
      return this.proposal.editions.find(e => { return e.id === id })
    },

    draggingEditionStart (edition) {
      this.draggingEdition = edition
    },

    dropOnEdition (event) {
      var droppedInEditionId = parseInt(event.currentTarget.attributes['data-edition-id'].value)
      var droppedInEdition = this.getEditionById(droppedInEditionId)
      var draggingEdition = this.getEditionById(this.draggingEdition.id)

      var newRank = droppedInEdition.myRank
      var newRankType = droppedInEdition.myRankType

      this.removeEditionFromZone(draggingEdition)
      this.moveEditionDown(droppedInEdition)

      draggingEdition.myRank = newRank
      draggingEdition.myRankType = newRankType
      this.saveRankEdition(draggingEdition)
    },

    dropOnZone (event) {
      var droppedInZoneType = event.currentTarget.attributes['data-zone-type'].value
      event.currentTarget.classList.remove('empty-drop-zone-over')

      var lastRank = 0
      switch (droppedInZoneType) {
        case 'NOGO':
          lastRank = this.getNoGoLastRank()
          break
        case 'NEUTRAL':
          lastRank = this.getNeutralLastRank()
          break
        case 'GO':
          lastRank = this.getGoLastRank()
          break
      }

      var edition = this.getEditionById(this.draggingEdition.id)
      this.removeEditionFromZone(edition)

      if (this.draggingEdition.myRankType === droppedInZoneType) {
        /* dropping within the same list */
        edition.myRank = lastRank
      } else {
        edition.myRank = lastRank + 1
      }

      edition.myRankType = droppedInZoneType
      this.saveRankEdition(edition)
    },

    removeEditionFromZone (edition) {
      /* move editions below upwards */
      var editionsBelow = this.proposal.editions.filter(e => { return ((e.myRank > edition.myRank) && (e.myRankType === edition.myRankType)) })
      editionsBelow.forEach(e => { e.myRank-- })
      editionsBelow.forEach(e => { this.saveRankEdition(e) })
    },

    moveEditionDown (edition) {
      /* move this and editions below down */
      var editionsBelow = this.proposal.editions.filter(e => { return ((e.myRank >= edition.myRank) && (e.myRankType === edition.myRankType)) })
      editionsBelow.forEach(e => { e.myRank++ })
      editionsBelow.forEach(e => { this.saveRankEdition(e) })
    },

    getNoGoLastRank () {
      var last = this.noGoEditions.slice(-1)[0]
      if (last) {
        return last.myRank
      } else {
        return 0
      }
    },
    getNeutralLastRank () {
      var last = this.neutralEditions.slice(-1)[0]
      if (last) {
        return last.myRank
      } else {
        return 0
      }
    },
    getGoLastRank () {
      var last = this.goEditions.slice(-1)[0]
      if (last) {
        return last.myRank
      } else {
        return 0
      }
    },

    newEdition (edition) {
      this.axios.post('/1/secured/proposal/' + this.proposal.id + '/edition', edition, {
        params: {
          parentId: 0
        }
      }).then((response) => {
        this.updateData()
      })
    },

    saveRankEdition (edition) {
      this.axios.put('/1/secured/proposal/' + this.proposal.id + '/edition/' + edition.id + '/rank', {}, {
        params: {
          'myRank': edition.myRank,
          'myRankType': edition.myRankType
        }
      })
    },

    updateData () {
      this.axios.get('1/secured/proposal/' + this.$route.params.id).then((response) => {
        this.proposal = response.data
      })
    },

    dragEnterDropZone (e) {
      e.currentTarget.classList.add('empty-drop-zone-over')
    },

    dragLeaveDropZone (e) {
      e.currentTarget.classList.remove('empty-drop-zone-over')
    }
  },

  created () {
    this.updateData()
  }
}
</script>

<style scoped>

.account-view {
  margin-top: 20px;
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

.empty-drop-zone {
  width: 100%;
  height: 100%;
}

.empty-drop-zone-over {
  background-color: rgb(227, 227, 227)
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

.edition-card {
  margin-bottom: 10px;
}

</style>
