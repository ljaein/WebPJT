<template>
  <div class="container taeduri col-md-6">
    <div class="form-group">
      <label class="d-flex">IMG_URL</label>
      <!-- <input type="text" class="form-control" id="imgurl" v-model="PostCreate.imgurl"> -->
      <div class="card col-sm-12 mt-1" align="left">
        <input ref="imageInput" type="file" hidden @change="onChangeImages" />
        <img v-if="this.PostCreate.imgurl" :src="this.PostCreate.imgurl" />
        <button type="button" class @click="onClickImageUpload">이미지 업로드</button>
      </div>
      <small class="form-text text-muted d-flex">원하는 사진을 업로드해주세요.</small>
    </div>
    <div class="form-group">
      <label class="d-flex">TITLE</label>
      <input type="text" class="form-control" id="title" v-model="PostCreate.title" />
      <small class="form-text text-muted d-flex">원하는 제목을 입력해주세요.</small>
    </div>
    <div class="form-group">
      <label class="d-flex justify-content-start">Location</label>
      <input type="text" class="form-control" id="location" v-model="PostCreate.location" />
      <small class="form-text text-muted d-flex">해당 위치를 상세히 적어주세요.</small>
    </div>
    <div class="form-group">
      <label class="d-flex justify-content-start">Price</label>
      <input type="text" class="form-control" id="price" v-model="PostCreate.price" />
      <small class="form-text text-muted d-flex">해당 엑티비티의 가격을 책정해주세요.</small>
    </div>
    <div class="form-group">
      <label class="d-flex justify-content-start">Start-Date</label>
      <input type="text" class="form-control" id="start-date" v-model="PostCreate.sdate" />
      <small class="form-text text-muted d-flex">시작일을 지정해주세요.</small>
    </div>
    <div class="form-group">
      <label class="d-flex justify-content-start">End-Date</label>
      <input type="text" class="form-control" id="end-date" v-model="PostCreate.edate" />
      <small class="form-text text-muted d-flex">마감일을 지정해주세요.</small>
    </div>
    <div class="form-group">
      <label class="d-flex justify-content-start">Corporation-Detail</label>
      <input
        type="textarea"
        class="form-control"
        id="company-information"
        v-model="PostCreate.companyInfo"
      />
      <small class="form-text text-muted d-flex">업체 정보를 입력해주세요.</small>
    </div>
    <div class="form-group">
      <label class="d-flex justify-content-start">Detail-Information</label>
      <input type="text" class="form-control" id="detail" v-model="PostCreate.detail" />
      <small class="form-text text-muted d-flex">세부 정보를 입력해주세요.</small>
    </div>
    <div class="form-group">
      <label class="d-flex justify-content-start">Activity</label>
      <input type="text" class="form-control" id="activity" v-model="PostCreate.activity" />
      <small class="form-text text-muted d-flex">활동명을 입력해주세요.</small>
    </div>
    <!-- <a type="button" class="btn btn-outline form-check mb-2" href="#" @click="gocreate()"> -->
    <div class="d-flex justify-content-end mb-5">
      <button
        type="submit"
        class="btn btn-outline mr-1"
        style="font-size: 1rem; color: gray;"
        @click="tempSave"
      >저장</button>
      <button type="submit" class="btn btn-outline pr-0" style="font-size: 1.1rem;" @click="regist">
        <i class="fas fa-pen mr-1"></i>등록
      </button>
    </div>
  </div>
</template>

<script>
const baseURL = "http://localhost:8080/";

import axios from "axios";

export default {
  data: function () {
    return {
      PostCreate: {
        email: "",
        title: "",
        locations: "",
        imgurl: "",
        price: "",
        sdate: "",
        edate: "",
        companyInfo: "",
        detail: "",
        activity: "",
      },
    };
  },
  methods: {
    regist: function () {
      axios
        .post(`${baseURL}/post/regist`, this.PostCreate)
        .then((response) => {
          console.log(response.data);
          this.$router.push("/posts");
        })
        .catch((error) => {
          console.log("error입니다.");
        });
    },
    tempSave() {
      //임시저장 메소드
      axios
        .post(`${baseURL}/temp/regist`, this.PostCreate)
        .then((response) => {
          console.log(response.data);
          this.$router.push("/posts");
        })
        .catch((error) => {
          console.log("error입니다.");
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
      this.PostCreate.imgurl = new Image();
      var reader = new FileReader();
      reader.onload = (e) => {
        this.PostCreate.imgurl = e.target.result;
      };
      reader.readAsDataURL(file);
    },
  },
  created() {
    this.PostCreate.email = this.$cookies.get("User");
  },
};
</script>