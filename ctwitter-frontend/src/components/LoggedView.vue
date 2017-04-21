<template lang="html">
  <div class="">
    <app-header></app-header>
    <div class="row">
      <router-view></router-view>
    </div>
  </div>
</template>

<script>
import { mapGetters, mapActions } from 'vuex'

import Header from './Header.vue'

export default {
  components: {
    AppHeader: Header
  },

  data () {
    return {
      tweet: '',
      recentTweets: {},
      showSentSuccess: false,
      showSentError: false,
      response: ''
    }
  },

  methods: {
    ...mapGetters(['profileImage', 'userNickname']),
    ...mapActions(['logout']),

    getRecentTweets () {
      this.axios.get('/1/secured/account/1/recentTweets', {
        headers: {
          'Authorization': 'Bearer ' + localStorage.getItem('id_token')
        }
      }).then((response) => {
        this.recentTweets = response.data
      })
    }
  }
}
</script>

<style scoped>

.logo {
  width: 200px;
  margin-bottom: 5px;
}

.avatar {
  width: 30px;
}

@media (min-width: 768px) {
  .bootstrap-vertical-nav .collapse {
    display: block;
  }
}

@media (min-width: 768px) {
  .bootstrap-vertical-nav {
    margin-top: 50px;
  }
}

.bootstrap-vertical-nav {
  height: 100%;
  overflow: auto;
}

.tweets-container {
  margin-top: 20px;
  overflow-y: auto;
  max-height: 400px;
  width: 400px;
}

.card {
  margin-top: 15px;
  background-color: rgb(29, 161, 242);
  color: white;
  border-radius: 10px;
}

</style>
