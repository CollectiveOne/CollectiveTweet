<template lang="html">
  <div class="col">
    <b-btn v-b-modal="'newAccountModal'" class="rounded-circle create-new-btn">
      +
    </b-btn>
    <div class="row">
      <div class="col accounts-container">
        <app-account-card v-for="account in myAccounts()" :account="account" :key="account.id" class="col-8 account-card"></app-account-card>
      </div>
    </div>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'

import AccountCard from './AccountCard.vue'

export default {
  components: {
    AppAccountCard: AccountCard
  },

  methods: {
    ...mapGetters(['myAccounts'])
  }
}
</script>

<style scoped>

.accounts-container {
  margin-top: 20px;
}

.account-card {
  margin-bottom: 20px;
}

.create-new-btn {
  padding: 0px;
  width: 40px;
  height: 40px;
  background-color: #026aa7;
  color: white;
  text-align: center;
  font-weight: bold;
  padding-top: 0px;
  font-size: 26px;
  z-index: 1100;

  position: absolute;
  margin-top: 20px;
  margin-left: auto;
  right: 30px;
  cursor: pointer;
  user-select: none;
}

.create-new-btn:hover {
  background-color: rgb(32, 154, 63);
  color: white;
}

</style>
