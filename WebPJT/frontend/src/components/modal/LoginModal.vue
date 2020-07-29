<template>

    <div class="modal fade" id="LoginModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content" style="margin:auto;height:20rem; width:25rem;">
        <div class="modal-header border-0 pl-0">
            <h4 class="modal-title w-100 text-center font-weight-bold position-absolute" id="exampleModalLabel">Login</h4>
            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">&times;</span>
            </button>
        </div>
        <div class="modal-body pb-0">
            <i class="fas fa-envelope" style="font-size:20px"></i> <input class="text-center ml-1 mt-4 bg-white" style="width:60%; border: none;" type="text" v-model="email" id="email" placeholder="이메일을 입력해주세요.">
            <div class="error-text" v-if="error.email">{{error.email}}</div>
            <p class="mb-4"></p>
            <i class="fas fa-lock" style="font-size:20px"></i> <input class="text-center ml-1 mt-4" style="width:60%; border: none;" type="password" v-model="password" id="password" placeholder="비밀번호를 입력해주세요." @keypress.enter="login">

        </div>
        <div class="modal-footer border-0 pt-0">
            <!-- <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button> -->
            <button type="button" class="btn" @click="join" data-dismiss="modal" ><i class="far fa-user mr-1"></i><br>회원 가입 하기</button>
            <button type="button" class="btn" @click="login" data-dismiss="modal" ><i class="fas fa-sign-in-alt mr-1"></i><br>로그인 하기</button>
        </div>
        </div>
    </div>
    </div>
</template>

<script>
import PV from "password-validator";
import * as EmailValidator from "email-validator";
import axios from 'axios'
import Swal from 'sweetalert2'

const baseURL = "http://localhost:8080/account"

export default {
  name: "Post",
  components: {},
  created() {
    this.passwordSchema
      .is()
      .min(8)
      .is()
      .max(100)
      .has()
      .digits()
      .has()
      .letters();
  },
  watch: {
    password: function(v) {
      this.checkForm();
    },
    email: function(v) {
      this.checkForm();
    }
  },

  methods: {
    checkForm() {
      if (this.email.length > 0 && !EmailValidator.validate(this.email))
        this.error.email = "이메일 형식이 아닙니다.";
      else if (this.email.length > 0 && EmailValidator.validate(this.email)) {
        axios
          .get(`${baseURL}/checkEmail/${this.email}`)
          .then(response => {
            this.error.email = "";
          })
          .catch(() => {
            alert("에러");
          });
      } else this.error.email = false;

    },
    login() {
      axios
        .get(`${baseURL}/login/${this.email}/${this.password}`)
        .then(response => {
          console.log(response.data);
          // this.$router.push("/");
          // this.$router.go();
            if (response.status == 200) {
              var jwt = require("jsonwebtoken");
              var token = jwt.sign({ sub: this.email }, this.password);
              this.$cookies.set("Auth-Token", token);
              this.$cookies.set("User", response.data);
              console.log(response.data);
              this.name = response.data.name;
              this.$router.push("/");
              this.$router.go();
            }
        })
        .catch(err => {
          console.log(err.response);
          alert("아이디 및 비밀번호를 확인해주세요.");
          this.email = "";
          this.password = "";
        });
    },
    join: function() {
      this.$router.push("/user/join/");
    }
  },
  data: () => {
    return {
      email: "",
      password: "",
      passwordSchema: new PV(),
      error: {
        email: false,
        password: false
      },
      passwordType: "password"
    };
  }
};

</script>

<style>

</style>