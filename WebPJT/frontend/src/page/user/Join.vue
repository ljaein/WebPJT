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

          <div class="input-wrap">
            <input v-model="name" id="name" placeholder="이름을 입력해주세요" type="text" />
          </div>

          <div class="input-wrap">
            <input v-model="nickname" id="nickname" placeholder="닉네임을 입력해주세요" type="text" />
            <div class="error-text" v-if="error.nickname">{{error.nickname}}</div>
          </div>

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

          <div class="input-wrap password-wrap">
            <input
              v-model="password"
              v-bind:class="{error : error.password, complete:!error.password&&password.length!==0}"
              id="password"
              :type="passwordType"
              placeholder="비밀번호를 입력해주세요"
            />
            <span :class="{active : passwordType==='text'}">
              <i class="fas fa-eye"></i>
            </span>
            <div class="error-text" v-if="error.password">{{error.password}}</div>
          </div>

          <div class="input-wrap password-wrap">
            <input
              v-model="passwordconfirm"
              v-bind:class="{error : error.passwordconfirm, complete:!error.passwordconfirm&&passwordconfirm.length!==0}"
              id="passwordconfirm"
              :type="passwordConfirmType"
              placeholder="비밀번호를 한번 더 입력해주세요"
            />
            <span :class="{active : passwordConfirmType==='text'}">
              <i class="fas fa-eye"></i>
            </span>
            <div class="error-text" v-if="error.passwordconfirm">{{error.passwordconfirm}}</div>
          </div>
        </div>

        <!-- <label>
                    <input v-model="isTerm" type="checkbox" id="term"/>
                    <span>약관에 동의합니다</span>
                </label>

        <span class="go-term">약관 보기</span>-->

        <button @click="join" class="btn">
          <span>작성완료</span>
        </button>
      </div>
    </div>
  </div>
</template>

<script>
import PV from "password-validator";
import * as EmailValidator from "email-validator";
import axios from "axios";

const baseURL = "http://localhost:8080/account";

export default {
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

      this.checkForm();
  },

  watch: {
    password: function(v) {
      this.checkForm();
    },
    email: function(v) {
      this.checkForm();
    },
    passwordconfirm: function(v) {
      this.checkForm();
    },
    nickname: function(v) {
      this.checkForm();
    },
    checkType: function(v) {
      this.checkForm();
    }
  },
  methods: {
    checkForm() {
      if(this.checkType == null) {
        this.error.checkType = "사용자 구분을 해주세요.";
      } else {
        this.error.checkType = false;
      }

      if (this.nickname.length > 0) {
        axios
          .get(`${baseURL}/checkNickname/${this.nickname}`)
          .then(response => {
            this.error.nickname = response.data;
          })
          .catch(() => {
            alert("에러");
          });
      } else this.error.nickname = false;
      if (this.email.length > 0 && !EmailValidator.validate(this.email))
        this.error.email = "이메일 형식이 아닙니다.";
      else if (this.email.length > 0 && EmailValidator.validate(this.email)) {
        axios
          .get(`${baseURL}/checkEmail/${this.email}`)
          .then(response => {
            this.error.email = response.data;
          })
          .catch(() => {
            alert("에러");
          });
      } else this.error.email = false;

      if (
        this.password.length > 0 &&
        !this.passwordSchema.validate(this.password)
      )
        this.error.password = "영문,숫자 포함 8 자리이상이어야 합니다.";
      else this.error.password = false;
      if (
        this.passwordconfirm.length > 0 &&
        this.passwordconfirm != this.password
      )
        this.error.passwordconfirm = "비밀번호를 다시 확인해주세요.";
      else this.error.passwordconfirm = false;
    },
    join() {
      let { email, nickname, password, name, checkType } = this;
      let data = {
        email,
        nickname,
        password,
        name,
        checkType
      };
      axios
        .post(`${baseURL}/signup`, data)
        .then(response => {
          // console.log(data);
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
    }
  },
  data: () => {
    return {
      checkType: "",
      email: "",
      name: "",
      nickname: "",
      password: "",
      passwordconfirm: "",
      passwordSchema: new PV(),
      error: {
        email: false,
        password: false,
        nickname: false,
        passwordconfirm: false,
        checkType: false
      },
      isTerm: false,
      passwordType: "password",
      passwordConfirmType: "password"
    };
  }
};
</script>


