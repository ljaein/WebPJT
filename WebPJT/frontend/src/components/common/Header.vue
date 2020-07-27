<template>
  <div id="header" class="p-0" v-if="isHeader">
    <nav class="navbar navbar-expand-lg navbar-light fixed-top header-nav col-md-7" >
      <router-link class="nav-title" v-bind:to="{name:constants.URL_TYPE.POST.MAIN}">
        <h5 class="mt-3 font-weight-bold nav-title">Linkivity</h5>
      </router-link>
      <button
        class="navbar-toggler navbar-toggler-right text-black"
        type="button"
        data-toggle="collapse"
        data-target="#navbarSupportedContent"
        aria-controls="navbarSupportedContent"
        aria-expanded="false"
        aria-label="Toggle navigation"
      >
        <span class="navbar-toggler-icon"></span>
      </button>

      <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav ml-auto">
          <li class="nav-item">
            <a class="nav-link mt-3 mr-2" @click="goPost"><i class="fas fa-stream mr-1"></i><br>Post</a>
          </li>
          <li class="nav-item">
            <a v-if="this.$cookies.isKey('Auth-Token')" @click="info" class="nav-link mt-3 mr-2"><i class="far fa-user mr-1"></i><br>MyPage</a>
          </li>
          <li class="nav-item">
            <a v-if="this.$cookies.isKey('Auth-Token')" @click="logout" class="nav-link mt-3"><i class="fas fa-sign-out-alt mr-1"></i><br>Logout</a>
          </li>
          <li class="nav-item">
            <a v-if="!this.$cookies.isKey('Auth-Token')" data-toggle="modal" data-target="#LoginModal" class="nav-link mt-3"><i class="fas fa-sign-in-alt mr-1"></i><br>Login</a>
          </li>
            <!-- <a
              class="nav-link mt-2 mr-3 header-link"
              id="navbarDropdown"
              role="button"
              data-toggle="dropdown"
              aria-haspopup="true"
              aria-expanded="false"
            >Season</a>
            <div class="dropdown-menu dropdown-menu-left" aria-labelledby="navbarDropdown">
              <a class="dropdown-item" style="padding-top:0;" @click="goPost">Post</a>
              <a class="dropdown-item" @click="goSpring">Spring</a>
              <a class="dropdown-item" @click="goSummer">Summer</a>
              <a class="dropdown-item" @click="goAutumn">Autumn</a>
              <a class="dropdown-item" style="padding-bottom:0;" @click="goWinter">Winter</a>
            </div>
          </li>
          <li class="nav-item dropdown">
            <a
              class="nav-link dropdown-toggle text-black mt-2 mr-3 header-link"
              href="#"
              id="navbarDropdown"
              role="button"
              data-toggle="dropdown"
              aria-haspopup="true"
              aria-expanded="false"
            >Activity</a>
            <div class="dropdown-menu dropdown-menu-left" aria-labelledby="navbarDropdown">
              <a class="dropdown-item" @click="groundAct">Ground Activity</a>
              <a class="dropdown-item" @click="waterAct">Water Activity</a>
              <a class="dropdown-item" @click="skyAct">Sky Activity</a>
            </div>
          </li>
          <a v-if="!this.$cookies.isKey('Auth-Token')" data-toggle="modal" data-target="#LoginModal" class="nav-link mt-2" style="font-size:0.8rem;" href="#"><i class="fas fa-sign-in-alt mr-1"></i>Login</a>
          <li v-if="this.$cookies.isKey('Auth-Token')" class="nav-item dropdown">
            <a
              class="nav-link dropdown-toggle text-black mt-2 header-link"
              href="#"
              id="navbarDropdown"
              role="button"
              data-toggle="dropdown"
              aria-haspopup="true"
              aria-expanded="false"
            >User</a>
            <div class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdown">
              <a v-if="this.$cookies.isKey('Auth-Token')" @click="logout" class="dropdown-item"><i class="fas fa-sign-out-alt mr-1"></i>Logout</a>
              <a v-if="this.$cookies.isKey('Auth-Token')" @click="info" class="dropdown-item pageclick"><i class="far fa-user mr-1"></i> Page</a>
            </div>
          </li> -->

        </ul>
        <!-- <a
          v-if="!this.$cookies.isKey('Auth-Token')"
          data-toggle="modal"
          data-target="#LoginModal"
        >로그인</a>
        <a
          v-if="this.$cookies.isKey('Auth-Token')"
          @click="logout"
          class="login mr-4 mt-3"
        >로그아웃</a>
        <a
          v-if="this.$cookies.isKey('Auth-Token')"
          @click="info"
          class="login mt-3"
        >회원정보</a> -->
      </div>
    </nav>

  </div>
</template>   

<script>
import '../../assets/css/header.css'
import constants from "../../lib/constants";

import axios from "axios";
const baseURL = "http://localhost:8080/account";

export default {
  name: "Header",
  components: {},
  props: {
    isHeader: Boolean
  },
  computed: {},
  watch: {},
  created() {},
  methods: {
    
    logout: function() {
      alert("로그아웃 되었습니다.");
      this.$cookies.remove("Auth-Token");
      this.$cookies.remove("User");
      this.$router.push("/");
      this.$router.go();
    },
    info: function() {
      this.$router.push("/user/info/");
      // this.$router.go();
    },
    goPost: function() {
      this.$router.push("/posts/");
      this.$router.go();
    },
    goSpring: function() {
      this.$router.push("/spring/");
      this.$router.go();
    },
    goSummer: function() {
      this.$router.push("/summer/");
      this.$router.go();
    },
    goAutumn: function() {
      this.$router.push("/autumn/");
      this.$router.go();
    },
    goWinter: function() {
      this.$router.push("/winter/");
      this.$router.go();
    },
    groundAct: function() {
      this.$router.push("/ground/");
      this.$router.go();
    },
    waterAct: function() {
      this.$router.push("/water/");
      // this.$router.go();
    },
    skyAct: function() {
      this.$router.push("/sky/");
      this.$router.go();
    }
  },
  data: function() {
    return {
      constants,
      keyword: ""
    };
  }
};
</script>

<style>
/* .pageclick {
  cursor: pointer;
} */
</style>