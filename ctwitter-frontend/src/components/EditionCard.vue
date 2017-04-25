<template lang="html">
  <div class="">
    <div class="card" draggable="true" @dragstart="dragStart()">
      <div class="card-block">
        <p class="text">
          {{ edition.text }}
        </p>
        <div class="toolbar">
          <img @click="forkMe()" src="../assets/fork.png">
        </div>
      </div>
    </div>
    <app-tweet-composer :textInit="originalTweet.text"
      @ok="newEditionReceived($event)" @cancel="proposing = false"></app-tweet-composer>
  </div>

</template>

<script>
export default {
  components: {
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
    forkMe () {
      this.$emit('fork-me', this.edition)
    },
    dragStart () {
      this.$emit('dragging-me', this.edition)
    }
  }
}
</script>

<style scoped>

.card-block {
  padding-top: 10px;
  padding-bottom: 10px;
}

.card-block p {
  margin-bottom: 5px;
}

.toolbar img {
  cursor: pointer;
  width: 25px;
  float: right;
}


</style>
