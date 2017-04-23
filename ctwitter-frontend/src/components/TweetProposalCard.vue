<template lang="html">
  <div class="card">
    <div class="card-block">
      <div class="card-title">
        <router-link :to="'/proposal/'+proposal.id">
          <h4>proposed by {{ proposal.creatorId }}</h4>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    proposal: {
      type: Object,
      default: () => {
        return {
          id: 0,
          creatorId: 'id'
        }
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
