<template lang="html">
  <div class="container">
    <input type="text" v-model="username" class="form-control" placeholder="add user">
    <div class="list-container">
      <div class="user-suggestion" v-for="user in userSuggestions">
        <div class="user-suggestion-picture">
          <img :src="user.picture">
        </div>
        <div class="user-suggestion-data">
          <p>{{ user.nickname }}</p>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data () {
    return {
      username: '',
      userSuggestions: []
    }
  },

  created () {
    this.axios('/1/secured/user/suggestions', {
      params: {
        q: this.username
      }
    }).then((response) => {
      this.userSuggestions = response.data
    })
  }
}
</script>

<style scoped>

.container {
  height: 30px;
}

.user-suggestion {
  position: relative;
  clear: both;
  display: none;
}

.user-suggestion-picture {
  width: 20px;
  height: 20px;
  float: left;
}

.user-suggestion img {
  width: 100%;
}

.user-suggestion-data {
  float: left;
  margin-left: 10px;
}

</style>
