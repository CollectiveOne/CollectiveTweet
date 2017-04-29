<template lang="html">
  <div class="">
    <input type="text" v-model="username" class="form-control">
  </div>
</template>

<script>
export default {
  data () {
    return {
      username: ''
    }
  },

  created () {
    this.axios('/1/secured/user/suggestions', {
      params: {
        q: this.username
      }
    }).then((response) => {
      console.log(response)
    })
  }
}
</script>

<style lang="css">
</style>
