<template>
  <div class="container col-md-7" id="join">
    <div class="wrapC table">
      <div class="middle">
        <!-- <h1 v-if="validated == 1">My Page</h1> -->
        <h1 v-if="validated == 0">회원정보 수정</h1>
        <!-- img -->
        <div class="card col-sm-12 mt-1 p-0" align="left">
          <div class="d-flex justify-content-start">
            <input ref="imageInput" type="file" hidden @change="onChangeImages" />
            <img
              v-if="this.imgurl"
              :src="this.imgurl"
              style="height:15rem; width:15rem; margin-left:7%; border-radius:20px"
            />
            <button
              type="button"
              class="btn btn-primary"
              @click="onClickImageUpload"
              v-if="validated == 0"
            >이미지 업로드</button>
            <div style="margin-left:20%; margin-right:5rem; width: 100%">
              <div class="form-group" align="left">
                <label for="email">이메일</label>
                <input
                  class="form-control mb-3"
                  disabled="false"
                  v-model="email"
                  id="email"
                  type="text"
                />
              </div>
              <div class="form-group" align="left">
                <label for="name">이름</label>
                <input
                  class="form-control mb-3"
                  disabled="false"
                  v-model="name"
                  id="name"
                  type="text"
                />
              </div>

              <div class="form-group" align="left">
                <label for="nickname">닉네임</label>
                <input
                  class="form-control mb-3"
                  :disabled="validated == 1"
                  v-model="nickname"
                  id="nickname"
                  type="text"
                />
              </div>
              <button
                @click="passwordModify"
                v-if="pwvalidated==0"
                class="btn btn-link btn-sm"
              >비밀번호 변경</button>
              <button
                @click="cancel"
                v-if="pwvalidated==1"
                class="btn btn-link btn-sm mb-2"
              >비밀번호 변경 취소</button>
              <div class="form-group" align="left" v-if="pwvalidated==1">
                <label for="pw">비밀번호</label>
                <input
                  class="form-control mb-3"
                  v-model="password"
                  v-bind:class="{error : error.password, complete:!error.password&&password.length!==0}"
                  id="password"
                  :type="passwordType"
                />
                <span :class="{active : passwordType==='text'}">
                  <i class="fas fa-eye"></i>
                </span>
                <div class="error-text" v-if="error.password">{{error.password}}</div>
              </div>

              <div class="form-group" align="left" v-if="pwvalidated==1">
                <label for="name">비밀번호 확인</label>
                <input
                  class="form-control mb-3"
                  v-model="passwordconfirm"
                  v-bind:class="{error : error.passwordconfirm, complete:!error.passwordconfirm&&passwordconfirm.length!==0}"
                  id="passwordconfirm"
                  :type="passwordConfirmType"
                />
                <span :class="{active : passwordConfirmType==='text'}">
                  <i class="fas fa-eye"></i>
                </span>
                <div class="error-text" v-if="error.passwordconfirm">{{error.passwordconfirm}}</div>
              </div>
            </div>
          </div>
        </div>

        <div class="card col-sm-12 mt-1"></div>
        <button @click="deluser" class="btn">
          <span>탈퇴하기</span>
        </button>
        <button v-if="validated==1" @click="gomodify" class="btn">
          <span>수정하기</span>
        </button>
        <button v-if="validated==0" @click="modify" class="btn">
          <span>완료</span>
        </button>
        <button @click="templist" class="btn">
          <span>임시저장 리스트</span>
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
    this.email = this.$cookies.get("User");
    axios
      .get(`${baseURL}/viewInfo/${this.email}`)
      .then((response) => {
        this.name = response.data.name;
        this.nickname = response.data.nickname;
        this.imgurl = response.data.imgurl;
      })
      .catch((err) => {
        this.$router.push({
          name: "Params",
          params: { name: err.response.status },
        });
      });
  },

  watch: {
    password: function (v) {
      this.checkForm();
    },
    passwordconfirm: function (v) {
      this.checkForm();
    },
  },
  methods: {
    passwordModify() {
      this.pwvalidated = 1;
    },
    cancel() {
      this.pwvalidated = 0;
    },
    templist() {
      this.$router.push("/posttemp");
    },
    checkForm() {
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
    gomodify() {
      this.validated = 0;
    },
    deluser() {
      axios
        .delete(`${baseURL}/delete/${this.$cookies.get("User")}`)
        .then((response) => {
          alert("탈퇴 완료");
          this.$cookies.remove("Auth-Token");
          this.$cookies.remove("User");
          this.$router.push("/");
          this.$router.go();
        })
        .catch(() => {
          alert(baseURL);
        });
    },
    modify() {
      let { email, nickname, password, name, imgurl } = this;
      let data = {
        email,
        nickname,
        password,
        name,
        imgurl,
      };
      axios
        .put(`${baseURL}/modify/${this.pwvalidated}`, data)
        .then((response) => {
          alert("수정 완료");
          this.$router.push("/user/info");
          this.$router.go();
        })
        .catch(() => {
          alert("정보를 입력하세요.");
          // this.$router.push({name: 'Params', params: {name: err.response.status}});
        });
    },
    onClickImageUpload() {
      this.$refs.imageInput.click();
    },
    onChangeImages(e) {
      const file = e.target.files[0];
      var img = new Image(file);
      img = e.target.files[0];
      this.createImage(img);
      // this.imgurl = URL.createObjectURL(file);
    },
    createImage(file) {
      this.imgurl = new Image();
      var reader = new FileReader();
      reader.onload = (e) => {
        this.imgurl = e.target.result;
      };
      reader.readAsDataURL(file);
    },
  },
  data: () => {
    return {
      email: "",
      name: "",
      nickname: "",
      password: "",
      passwordconfirm: "",
      passwordSchema: new PV(),
      error: {
        password: false,
        passwordconfirm: false,
      },
      isTerm: false,
      passwordType: "password",
      passwordConfirmType: "password",
      imgurl: null,
      validated: 1,
      pwvalidated: 0,
    };
  },
};
</script>


