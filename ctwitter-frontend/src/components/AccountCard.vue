<template lang="html">
  <div class="card">
    <div class="card-block">
      <div class="card-title">
        <router-link :to="'/account/'+account.id">
          <h4>@{{ account.twitterHandle }}</h4>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    account: {
      type: Object,
      default: {
        twitterHandle: 'example',
        creatorUsername: 'creator'
      }
    }
  }
}
</script>

<style scoped>

.account-card {
  background-color: rgb(48, 67, 168);
  color: white;
  border-radius: 10px;
}

.account-card a {
  color: white;
}

</style>
