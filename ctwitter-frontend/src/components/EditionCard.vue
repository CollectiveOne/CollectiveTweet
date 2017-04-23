<template lang="html">
  <div class="card" draggable="true" @dragstart="dragStart()">
    <div class="card-block">
      <p class="text">
        {{ edition.text }}
      </p>
    </div>
  </div>
</template>

<script>
import { mapMutations } from 'vuex'

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
    ...mapMutations(['proposalSetEditionBeingDragged']),

    dragStart () {
      this.proposalSetEditionBeingDragged(this.edition)
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

.account-card {
  background-color: rgb(48, 67, 168);
  color: white;
  border-radius: 10px;
}

.account-card a {
  color: white;
}

.toolbar {
  overflow: auto;
}

.toolbar img {
  cursor: pointer;
  width: 25px;
  float: right;
}

.composer {
  margin-top: 10px;
}

</style>
