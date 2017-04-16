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
          <h3>Collective Tweet</h3>
          <p>Create new one</p>
        </div>

      </div>

    </div>
  </div>
</template>

<script>
import { mapGetters, mapActions } from 'vuex'

export default {
  methods: {
    ...mapGetters(['profileImage']),
    ...mapActions(['logout'])
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
