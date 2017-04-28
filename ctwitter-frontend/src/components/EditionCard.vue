<template lang="html">
  <div @dragover.prevent @dragenter="" @dragleave="" @drop="$emit('ed-drop',$event)">
    <div v-if="draggingOver" class="dragging-over">
      <hr>
    </div>
    <div class="card" draggable="true" @dragstart="dragStart()">
      <div class="card-block">
        <p class="text">
          {{ edition.text }}
        </p>
        <div class="toolbar">
          <img @click="proposing = true" src="../assets/fork.png">
          <div v-if="edition.myRankType !== 'NOTRANKED'" class="badge badge-primary">{{ edition.myRank }}</div>
        </div>
      </div>
    </div>
    <app-tweet-composer v-if="proposing" :textInit="edition.text"
      @ok="newEditionProposed($event)" @cancel="proposing = false"></app-tweet-composer>
  </div>

</template>

<script>
import TweetComposer from './TweetComposer.vue'

export default {
  components: {
    AppTweetComposer: TweetComposer
  },

  props: {
    edition: {
      type: Object,
      default: {
        id: 0,
        proposalId: 0,
        creatorId: 'creatorId',
        text: 'edition text'
      }
    }
  },

  data () {
    return {
      proposing: false,
      draggingOver: false
    }
  },

  methods: {
    newEditionProposed (edition) {
      this.$emit('newEditionProposed', edition)
      this.proposing = false
    },

    dragStart () {
      this.$emit('dragging-me', this.edition)
    },

    dragEnter () {
      console.log('enter')
      this.draggingOver = true
    },

    dragLeave () {
      console.log('leave')
      this.draggingOver = false
    }
  }
}
</script>

<style scoped>

.card {
  margin-bottom: 10px;
  padding-bottom: 5px;
  background-color: rgb(236, 236, 236);

  box-shadow: 1px 2px 4px rgba(0, 0, 0, .5);
  padding: 10px;
  background: white;
}

.card-block {
  padding-top: 10px;
  padding-bottom: 10px;
}

.card-block p {
  margin-bottom: 5px;
}

.toolbar .badge {
  float: right;
  margin-right: 10px;
}

.toolbar img {
  cursor: pointer;
  width: 15px;
  float: right;
}


</style>
