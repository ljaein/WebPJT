<template>
  <!-- Modal -->
  <div
    class="modal fade"
    id="LoginModal"
    tabindex="-1"
    role="dialog"
    aria-labelledby="exampleModalLabel"
    aria-hidden="true"
  >
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="exampleModalLabel">로그인</h5>
          <button type="button" class="close" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">&times;</span>
          </button>
        </div>
        <div class="modal-body">
          <div class="user" id="login">
            <div class="wrapC table">
              <!-- <div class="middle"> -->
              <h1>SS_log</h1>
              <div class="input-wrap">
                <input
                  v-model="email"
                  v-bind:class="{error : error.email, complete:!error.email&&email.length!==0}"
                  id="email"
                  placeholder="이메일을 입력해주세요"
                  type="text"
                />
                <div class="error-text" v-if="error.email">{{error.email}}</div>
              </div>
              <div class="input-wrap">
                <input
                  v-model="password"
                  v-bind:class="{error : error.password, complete:!error.password&&password.length!==0}"
                  :type="passwordType"
                  id="password"
                  placeholder="영문, 숫자 혼용 8자 이상"
                />
                <span :class="{active : passwordType==='text'}">
                  <i class="fas fa-eye"></i>
                </span>
                <div class="error-text" v-if="error.password">{{error.password}}</div>
              </div>
              <button class="btn btn--back btn--login" @click="login" data-dismiss="modal">로그인</button>
              <div class="add-option">
                <div class="wrap">
                  <p>아직 회원이 아니신가요?</p>
                  <button type="button" class="btn--text" @click="join" data-dismiss="modal">회원가입</button>
                  <!-- <router-link v-bind:to="{name:constants.URL_TYPE.USER.JOIN}" class="btn--text">회원가입</router-link> -->
                </div>
              </div>
              <!-- </div> -->
            </div>
          </div>
        </div>
        <div class="modal-footer">
          <button type="button" class="login-btn" data-dismiss="modal">Close</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import "../../assets/css/post.scss";
import "../../assets/css/user.scss";
import PV from "password-validator";
import * as EmailValidator from "email-validator";
import axios from "axios";

const baseURL = "http://localhost:8080/account";
const storage = window.sessionStorage;

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
