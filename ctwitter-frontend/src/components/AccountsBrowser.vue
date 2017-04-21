<template lang="html">
  <div class="col">
    <b-btn v-b-modal="'newAccountModal'" class="rounded-circle create-new-btn">
      +
    </b-btn>
  </div>
</template>

<script>
export default {
}
</script>

<style scoped>

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
