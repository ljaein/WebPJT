<template>
  <div class="container col-md-6">
      <!-- 이름 입력칸  -->
      <div class="form-group">
        <label for="exampleInputEmail1" class="d-flex">Name</label>
        <input v-model="name" type="text" class="form-control" id="name" aria-describedby="emailHelp" placeholder="이름을 입력해주세요.">
        <small id="emailHelp" class="form-text text-muted d-flex">본인의 성함을 기재해주세요.</small>
      </div>

      <!-- 닉네임 입력칸 -->
      <div class="form-group">
        <label for="exampleInputPassword1" class="d-flex">Nickname</label>
        <input v-model="nickname" type="text" class="form-control" id="nickname" placeholder="닉네임을 입력해주세요.">
        <small id="emailHelp" class="form-text text-muted d-flex">원하는 닉네임을 설정해주세요.</small>
        <i class="fas fa-user-check d-flex"><span class="error-text d-flex" v-if="error.nickname">{{error.nickname}}</span></i>
      </div>
      
      <!-- email 입력칸 -->
      <div class="form-group">
        <label for="exampleInputPassword1" class="d-flex">E-mail</label>
        <input v-model="email" v-bind:class="{error : error.email, complete:!error.email&&email.length!==0}" type="email" class="form-control" id="email" placeholder="이메일을 입력해주세요.">
        <small id="emailHelp" class="form-text text-muted d-flex">회원가입을 위한 이메일을 입력해주세요.</small>
        <i class="fas fa-at d-flex"><span class="error-text" v-if="error.email">{{error.email}}</span></i>
      </div>
      
      <!-- 비밀번호 입력칸 -->
      <div class="form-group">
        <label for="exampleInputPassword1" class="d-flex">Password</label>
        <input v-model="password" v-bind:class="{error : error.password, complete:!error.password&&password.length!==0}" :type="passwordType" class="form-control" id="password" placeholder="비밀번호를 입력해주세요.">
        <small id="emailHelp" class="form-text text-muted d-flex">회원가입을 위한 비밀번호를 설정해주세요.</small>
        <span :class="{active : passwordType==='text'}">
          <i class="fas fa-eye d-flex"><span class="error-text" v-if="error.password">{{error.password}}</span></i>
        </span>
      </div>

      <!-- 비밀번호 확인 입력칸 -->
      <div class="form-group">
        <label for="exampleInputPassword1" class="d-flex">Password</label>
        <input v-model="passwordconfirm" v-bind:class="{error : error.passwordconfirm, complete:!error.passwordconfirm&&passwordconfirm.length!==0}" :type="passwordConfirmType" class="form-control" id="passwordconfirm" placeholder="비밀번호를 확인해주세요.">
        <small id="emailHelp" class="form-text text-muted d-flex">비밀번호 확인을 위해 다시 작성해주세요.</small>
        <span :class="{active : passwordConfirmType==='text'}">
          <i class="fas fa-eye d-flex"><span class="error-text" v-if="error.passwordconfirm">{{error.passwordconfirm}}</span></i>
        </span>
      </div>
      
      <!-- 주소 입력칸 -->
      <div class="form-group">
        <label for="exampleInputEmail1" class="d-flex">Address</label>
        <input v-model="clocation" type="text" class="form-control" id="location" aria-describedby="emailHelp" placeholder="주소를 입력해주세요.">
        <small id="emailHelp" class="form-text text-muted d-flex">업체의 주소를 기재해주세요.</small>
      </div>
      
      <!-- 핸드폰 번호 입력칸 -->
      <div class="form-group">
        <label for="exampleInputEmail1" class="d-flex">Phone Number</label>
        <input v-model="cphone" type="text" class="form-control" id="phone" aria-describedby="emailHelp" placeholder="연락처를 입력해주세요.">
        <small id="emailHelp" class="form-text text-muted d-flex">업체 및 본인 연락처를 기재해주세요.</small>
      </div>
    
    <!-- img upload -->
    <button type="button" class = "btn btn-primary d-flex" @click="onClickImageUpload">이미지 업로드</button><br>
    <i v-if="!this.imgurl" class="fas fa-images d-flex"><span>사진을 등록해주세요.</span></i>
    <div class="col-md-8 p-0" align="left">
      <input ref="imageInput" type="file" hidden @change="onChangeImages" />
      <img class="card-img mb-2" style="height: 15rem; width: 15rem;" v-if="this.imgurl" :src="this.imgurl" />
    </div>

    <!-- 제출 버튼 -->
    <button @click="join" class="btn d-flex ml-auto">
      <i class="fas fa-pen mr-1"></i>
          <span>작성완료</span>
    </button>
  </div>
</template>

<script>
import PV from "password-validator";
import * as EmailValidator from "email-validator";

import axios from 'axios'

const baseURL = "http://localhost:8080/account";

export default {
  props: {
    checkType: String,
  },
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
    this.checkForm()
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
  },
  methods: {
    checkForm() {
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
      this.$emit('join-create-business', this.email, this.nickname, this.password, this.name, this.checkType, this.imgurl, this.clocation, this.cphone)
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
    createImage(file){
      this.imgurl = new Image();
      var reader = new FileReader();
      reader.onload = e =>{
        this.imgurl = e.target.result;
      };
      reader.readAsDataURL(file);
    }
  },
  data() {
    return {
      email: "",
      name: "",
      nickname: "",
      password: "",
      passwordconfirm: "",
      imgurl: "",
      clocation: "",
      cphone: "",
      passwordSchema: new PV(),
      error: {
        email: false,
        password: false,
        nickname: false,
        passwordconfirm: false,
      },
      isTerm: false,
      passwordType: "password",
      passwordConfirmType: "password"
    }
  },
}
</script>

<style>

</style>