<template>

    <div class="modal fade" id="LoginModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
        <div class="modal-header">
            <h5 class="modal-title" id="exampleModalLabel">로그인</h5>
            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">&times;</span>
            </button>
        </div>
        <div class="modal-body">
            E-mail : <input type="text" v-model="email" id="email" placeholder="이메일을 입력해주세요."><br>
            Password : <input type="password" v-model="password" id="password" placeholder="비밀번호를 입력해주세요.">
        </div>
        <div class="modal-footer">
            <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
            <button type="button" class="btn btn-primary" @click="login" data-dismiss="modal" >로그인 하기</button>
            <button type="button" class="btn btn-primary" @click="join" data-dismiss="modal" >회원 가입 하기</button>
        </div>
        </div>
    </div>
    </div>
</template>

<script>
import PV from "password-validator";
import * as EmailValidator from "email-validator";
import axios from 'axios'

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
      else this.error.email = false;

      if (
        this.password.length > 0 &&
        !this.passwordSchema.validate(this.password)
      )
        this.error.password = "영문,숫자 포함 8 자리이상이어야 합니다.";
      else this.error.password = false;
    },
    login() {
      axios
        .get(`${baseURL}/login/${this.email}/${this.password}`)
        .then(response => {
          console.log(response.data);
          this.$router.push("/");
          this.$router.go();
          // alert(this.$session.get("login_user"));
          // axios.get(`${baseURL}/getuserinfo`)
          // .then(response => {
          //     alert(response);
          // })
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