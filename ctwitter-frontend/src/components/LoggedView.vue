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
        <br>
        <p>Logged user: {{ userNickname() }}</p>
        <br>
        <button @click="createNewAccount()" class="btn btn-primary">Link new one</button>
        <br>
        <br>
        <textarea class="form-control" v-model="tweet" placeholder="tweet from here"></textarea>
        <br>
        <div v-if="showSentSuccess" class="alert alert-info">
          Tweet sent
        </div>
        <div v-if="showSentError" class="alert alert-danger">
          Error while sending tweet
        </div>
        <button class="btn btn-primary" @click="sendTweet()">Tweet</button>
        <br>
        <br>
        <button class="btn btn-primary" @click="getRecentTweets()">Get Recent Tweets</button>
        <button class="btn btn-primary" @click="getTest()">Get Non Secured </button>

        <div class="">
          {{ response }}
        </div>

        <div class="tweets-container">
          <div class="card" v-for="tweet in recentTweets" >
            <div class="card-block">
              <div class="card-title">
                <h4>{{ tweet.text}}</h4>
              </div>
            </div>
          </div>
        </div>
      </div>

    </div>
  </div>
</template>

<script>
import { mapGetters, mapActions } from 'vuex'

export default {
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

    createNewAccount () {
      this.axios.get('/1/account/auth/url', {}).then((response) => {
        var newwindow = window.open(response.data, 'Link twitter account', 'left=300,height=200,width=400')
        if (window.focus) {
          newwindow.focus()
        }
      })
    },

    sendTweet () {
      this.axios.post('/1/secure/account/1/tweet', { text: this.tweet }, {
        headers: {'Authorization': 'Bearer i3MISgXFYus6qyJA'}
      })
        .catch((data) => {
          this.showSentError = true
        })
        .then((response) => {
          if (response.data === 'done') {
            this.tweet = ''
            this.showSentSuccess = true
          } else {
            this.showSentError = true
          }
        })
    },

    getRecentTweets () {
      // this.axios.get('/1/secure/account/1/recentTweets', {
      // this.axios.get('/1/account/1/recentTweets', {
      //   headers: {
      //     'Authorization': 'Bearer ' + localStorage.getItem('id_token')
      //   }
      // }).then((response) => {
      //   this.recentTweets = response.data
      // })
      this.axios.get('/1/secure/ping', {
        headers: {
          'Authorization': 'Bearer ' + localStorage.getItem('id_token')
        }
      })
      .catch((data) => {
        this.response = data
      })
      .then((response) => {
        this.response = response.data
      })
    },

    getTest () {
      this.axios.get('/1/ping', {
        headers: {
          'Authorization': 'Bearer ' + localStorage.getItem('id_token')
        }
      })
      .catch((data) => {
        this.response = data
      })
      .then((response) => {
        this.response = response.data
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
