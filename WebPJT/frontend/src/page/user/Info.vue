<template>
  <div class="container col-md-6 col-sm-12" id="join">
    <div class="wrapC table">
      <div class="middle">
        <!-- <h1 v-if="validated == 1">회원정보 조회</h1> -->
        <!-- <h1 v-if="validated == 0">회원정보 수정</h1> -->
        <!-- img -->
        <div class="card col-sm-12 mt-1">
          <div class="d-flex justify-content-start">
            <div class="inputimg">
              <input ref="imageInput" type="file" hidden @change="onChangeImages" />
              <img class="infoimg" v-if="this.imgurl" :src="this.imgurl" />
              <button
                type="button"
                class="btn btn-outline"
                @click="onClickImageUpload"
                v-if="validated == 0"
              >
                <i class="fas fa-image mr-2"></i>이미지 업로드
              </button>
            </div>
            <div style="margin-left:20%; width: 100%">
              <div class="form-group-info">
                <!-- <label for="nickname">닉네임</label> -->
                <input
                  class="form-control mb-1"
                  :disabled="validated == 1"
                  v-model="nickname"
                  id="nickname"
                  type="text"
                  style="font-size: 30px; font-weight:bold;"
                />
                <span class="nickname-edit" v-if="validated==0">
                  <!-- <i class="fas fa-arrow-up mr-2"></i> -->
                  닉네임 클릭하여 변경
                  <!-- <i class="fas fa-arrow-up"></i> -->
                </span>
              </div>
              <div class="form-group-info">
                <!-- <label for="email">이메일</label> -->
                <input
                  class="form-control mt-2 mb-3"
                  v-if="pwvalidated==0"
                  disabled="false"
                  v-model="email"
                  id="email"
                  type="text"
                  style="font-size: 20px;"
                />
              </div>
              <div class="form-group-info">
                <!-- <label for="name">이름</label> -->
                <input
                  class="form-control mb-3"
                  v-if="pwvalidated==0"
                  disabled="false"
                  v-model="name"
                  id="name"
                  type="text"
                  style="font-size: 20px;"
                />
              </div>

              <button
                @click="passwordModify"
                v-if="pwvalidated==0 && validated==0"
                class="btn btn-link btn-sm" style="font-size:1rem;"
              >비밀번호 변경</button>

              <div class="form-group-pw mb-2 mt-1" align="left" v-if="pwvalidated==1">
                <label class="mt-2" for="pw">비밀번호</label>
                <input
                  class="form-control mb-1"
                  v-model="password"
                  v-bind:class="{error : error.password, complete:!error.password&&password.length!==0}"
                  id="password"
                  :type="passwordType"
                />
                <div class="d-flex justify-contetn-between">
                  <span v-if="error.password" :class="{active : passwordType==='text'}">
                    <i class="fas fa-eye mr-2"></i>
                  </span>
                  <div class="error-text mt-1" v-if="error.password">{{error.password}}</div>
                </div>
              </div>

              <div class="form-group-pw" align="left" v-if="pwvalidated==1">
                <label for="name">비밀번호 확인</label>
                <input
                  class="form-control mb-1"
                  v-model="passwordconfirm"
                  v-bind:class="{error : error.passwordconfirm, complete:!error.passwordconfirm&&passwordconfirm.length!==0}"
                  id="passwordconfirm"
                  :type="passwordConfirmType"
                />
                <div class="d-flex justify-contetn-between">
                  <span v-if="error.passwordconfirm" :class="{active : passwordConfirmType==='text'}">
                    <i class="fas fa-eye mr-2"></i>
                  </span>
                  <div class="error-text mt-1" v-if="error.passwordconfirm">{{error.passwordconfirm}}</div>
                </div>
              </div>
              <div class="d-flex justify-content-end">
              <button v-if="pwvalidated==1" @click="modify" class="btn btn-link btn-sm mb-2"><i class="fas fa-check mr-2"></i>완료</button>
              <button @click="cancel" v-if="pwvalidated==1" class="btn btn-link btn-sm mb-2 ml-2 p-0"><i class="fas fa-times mr-2"></i>취소</button>
              </div>
            </div>
          </div>
        </div>
        <div class="d-flex justify-content-start">
          <a class="mypage-select"><i class="fas fa-shopping-basket mr-2"></i>장바구니</a>
          <a class="mypage-select"><i class="fas fa-heart mr-2"></i>좋아요</a>
          <a class="mypage-select"><i class="fas fa-list-ul mr-2"></i>구매목록</a>
          <a class="mypage-select" @click="templist"><i class="fas fa-save mr-2"></i>임시저장 리스트</a>
        </div>
        <hr class="border-bottom-1 border-black mt-1">

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
        <!-- <button @click="templist" class="btn">
          <span>임시저장 리스트</span>
        </button> -->
      </div>
    </div>
  </div>
</template>

<script>
import PV from "password-validator";
import * as EmailValidator from "email-validator";
import axios from "axios";
import "../../assets/css/info.css";

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


