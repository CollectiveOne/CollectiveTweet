<template lang="html">
  <div class="form tweet-composer" @submit.prevent>
    <div class="twitter-text-group form-group">
      <textarea class="form-control" v-model="text" rows="6"></textarea>
      <small>{{ nChars }} / 140</small>
    </div>
    <div class="btns-group">
      <b-btn @click="ok()" variant="primary">Propose</b-btn>
      <b-btn @click="$emit('cancel')">Cancel</b-btn>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    accountId: {
      type: Number,
      default: 0
    }
  },

  data () {
    return {
      text: ''
    }
  },

  computed: {
    nChars () {
      return this.text.length
    }
  },

  methods: {
    ok () {
      this.$emit('ok', {
        text: this.text
      })
    }
  }
}
</script>

<style scoped>

.tweet-composer button {
  float: right;
  width: 100px;
  margin-right: 10px;
}

.btns-group {
  width: 100%;
  overflow: auto;
  margin-top: 30px;
}

.twitter-text-group small{
  float: right;
  margin-right: 3px;
}

</style>
