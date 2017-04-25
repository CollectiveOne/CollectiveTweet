<template lang="html">
  <div class="card" draggable="true">
    <div class="card-block">
      <p class="text">
        {{ tweet.text }}
      </p>
    </div>
  </div>
</template>

<script>
export default {
  components: {
  },

  props: {
    tweet: {
      type: Object,
      default: {
        text: 'tweet text'
      }
    }
  },

  data () {
    return {
    }
  },

  methods: {
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
