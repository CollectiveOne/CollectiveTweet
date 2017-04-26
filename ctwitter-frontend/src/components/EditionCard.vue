<template lang="html">
  <div  @dragover.prevent @dragenter="$emit('ed-dragenter',$event)" @dragleave="$emit('ed-dragleave',$event)" @drop="$emit('ed-drop',$event)">
    <div class="card" draggable="true" @dragstart="dragStart()">
      <div class="card-block">
        <p class="text">
          {{ edition.text }}
        </p>
        <div class="toolbar">
          <img @click="proposing = true" src="../assets/fork.png">
          <div class="badge badge-primary">{{ edition.myRank }}</div>
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
      proposing: false
    }
  },

  methods: {
    newEditionProposed (edition) {
      this.$emit('newEditionProposed', edition)
      this.proposing = false
    },

    dragStart () {
      this.$emit('dragging-me', this.edition)
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
