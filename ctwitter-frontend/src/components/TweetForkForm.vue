<template lang="html">
    <div class="fork-form">
      <div class="badge badge-default">original</div>
      <app-tweet-card :tweet="originalTweet" class="tweetBase"></app-tweet-card>
      <div class="badge badge-default">new proposal</div>
      
    </div>
</template>

<script>
import TweetComposer from './TweetComposer.vue'
import TweetCard from './TweetCard.vue'

export default {
  components: {
    AppTweetComposer: TweetComposer,
    AppTweetCard: TweetCard
  },

  props: {
    originalTweet: {
      type: Object,
      default: () => {
        return {
          text: 'tweet text'
        }
      }
    }
  },

  data () {
    return {
    }
  },

  computed: {
  },

  methods: {
    newEditionReceived (edition) {
      this.$emit('editionProposed', edition)
    }
  },

  created () {
  }
}
</script>

<style scoped>

.fork-form {
  max-width: 300px;
}

.fork-form .badge {
  margin-bottom: 10px;
}
.tweetBase {
    margin-bottom: 15px;
}

</style>
