<template>
  <div class="user" id="join">
    <div class="wrapC table">
      <div class="middle">
        <h1>회원가입</h1>
        <div class="form-wrap">
          <!-- 라디오 박스 -->
          <input type="radio" id="normal" name="type" value="normal" v-model="checkType">
            <label for="normal">일반 사용자</label>
          <input type="radio" id="business" name="type" value="business" v-model="checkType">
            <label for="business">사업자 </label><br>
          <div class="error-text" v-if="error.checkType">{{error.checkType}}</div>

          <Business v-if="checkType == 'business'" :checkType="checkType" @join-create-business="joinBusiness"/>
          <Normal v-if="checkType == 'normal'" :checkType="checkType" @join-create-normal="joinNormal" />

        </div>

      </div>
    </div>
  </div>
</template>

<script>
import PV from "password-validator";
import * as EmailValidator from "email-validator";
import axios from "axios";

import Business from '../../components/joinform/Business.vue'
import Normal from '../../components/joinform/Normal.vue'

const baseURL = "http://localhost:8080/account";

export default {
  components: {
    Business,
    Normal,
  },
  created() {
    this.checkForm();
  },

  watch: {
    checkType: function(v) {
      this.checkForm();
    }
  },
  methods: {
    checkForm() {
      if(this.checkType == "") {
        this.error.checkType = "사용자 구분을 해주세요.";
      } else {
        this.error.checkType = false;
      }
    },
    joinBusiness(email, nickname, password, name, checkType, imgurl){
      let data = {
        name,
        nickname,
        email,
        password,
        checkType,
        imgurl,
      };
      axios
        .post(`${baseURL}/signup`, data)
        .then(response => {
          alert("회원가입 인증 메일이 발송되었습니다. 이메일을 확인해주세요.");
          this.$router.push("/");
        })
        .catch(() => {
          alert("회원 정보를 모두 입력해주세요.");
          this.$router.push({
            name: "Params",
            params: { name: err.response.status }
          });
        });
    },
    joinNormal(email, name, nickname, password, checkType, imgurl) {
      let data = {
        name,
        nickname,
        email,
        password,
        checkType,
        imgurl,
      };
      axios
        .post(`${baseURL}/signup`, data)
        .then(response => {
          alert("회원가입 인증 메일이 발송되었습니다. 이메일을 확인해주세요.");
          this.$router.push("/");
        })
        .catch(() => {
          alert("회원 정보를 모두 입력해주세요.");
          this.$router.push({
            name: "Params",
            params: { name: err.response.status }
          });
        });
    },
  },
  data: () => {
    return {
      checkType: "",
      // email: "",
      // name: "",
      // nickname: "",
      // password: "",
      // passwordconfirm: "",
      // passwordSchema: new PV(),
      error: {
        // email: false,
        // password: false,
        // nickname: false,
        // passwordconfirm: false,
        checkType: false
      },
      // isTerm: false,
      // passwordType: "password",
      // passwordConfirmType: "password"
    };
  }
};
</script>

