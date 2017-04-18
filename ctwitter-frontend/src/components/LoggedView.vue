<template lang="html">
  <div class="col">
    <div class="row">
      <div class="col-md-3 col-lg-2">

        <div class="bootstrap-vertical-nav">

          <button
            class="btn btn-primary hidden-md-up" type="button"
            data-toggle="collapse" data-target="#collapseExample"
            aria-expanded="false" aria-controls="collapseExample">
            <span class="">Menu</span>
          </button>

          <div class="collapse" id="collapseExample">
            <ul class="nav flex-column" id="exCollapsingNavbar3">
              <li class="nav-item">
                <a class="nav-link active" href="#">accounts</a>
              </li>
              <li class="nav-item">
                <a class="nav-link active" href="#" @click="logout">logout</a>
              </li>
            </ul>
          </div>
        </div>

      </div>

      <div class="col-md-9 col-lg-10">
        <div class="jumbotron">
          <button @click="createNewAccount()" class="btn btn-primary">Create new one</button>
        </div>
        <textarea class="form-control" v-model="tweet" placeholder="tweet from here"></textarea>
        <br>
        <button class="btn btn-primary" @click="sendTweet()">Tweet</button>

      </div>

    </div>
  </div>
</template>

<script>
import { mapGetters, mapActions } from 'vuex'

export default {
  data () {
    return {
      tweet: ''
    }
  },

  methods: {
    ...mapGetters(['profileImage']),
    ...mapActions(['logout']),

    createNewAccount () {
      this.axios.get('/1/account/auth/url', {}).then((response) => {
        window.location.replace(response.data)
      })
    },

    sendTweet () {
      this.axios.post('/1/account/action/2/tweet', { text: this.tweet })
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

</style>
