<template>
  <div id="header" class="p-0" v-if="isHeader">
    <nav class="navbar navbar-expand-lg navbar-dark fixed-top header-nav" >
      <router-link class="nav-title" v-bind:to="{name:constants.URL_TYPE.POST.MAIN}">
        <h5 class="m-2 ml-4 font-weight-bold nav-title">Leisure</h5>
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
        <ul class="navbar-nav ml-auto mr-4">
          <li class="nav-item dropdown">
            <a
              class="nav-link dropdown-toggle mt-2 mr-3 header-link"
              href="#"
              id="navbarDropdown"
              role="button"
              data-toggle="dropdown"
              aria-haspopup="true"
              aria-expanded="false"
            >Season</a>
            <div class="dropdown-menu text-white dropdown-menu-left" aria-labelledby="navbarDropdown">
              <a class="dropdown-item" style="padding-top:0;" @click="goPost" href="#">Post</a>
              <div class="dropdown-divider m-0"></div>
              <a class="dropdown-item" @click="goSpring" href="#">Spring</a>
              <a class="dropdown-item" @click="goSummer" href="#">Summer</a>
              <a class="dropdown-item" @click="goAutumn" href="#">Autumn</a>
              <a class="dropdown-item" style="padding-bottom:0;" @click="goWinter" href="#">Winter</a>
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
              <a class="dropdown-item" @click="groundAct" href="#">Ground Activity</a>
              <a class="dropdown-item" @click="waterAct" href="#">Water Activity</a>
              <a class="dropdown-item" @click="skyAct" href="#">Sky Activity</a>
            </div>
          </li>
          <a v-if="!this.$cookies.isKey('Auth-Token')" data-toggle="modal" data-target="#LoginModal" class="nav-link mt-3" href="#"><i class="fas fa-sign-in-alt mr-1"></i>Login</a>
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
              <a v-if="this.$cookies.isKey('Auth-Token')" @click="logout" class="dropdown-item" href="#"><i class="fas fa-sign-out-alt mr-1"></i>Logout</a>
              <a v-if="this.$cookies.isKey('Auth-Token')" @click="info" class="dropdown-item" href="#"><i class="far fa-user mr-1"></i> Page</a>
              <!-- <div class="dropdown-divider"></div> -->
            </div>
          </li>
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