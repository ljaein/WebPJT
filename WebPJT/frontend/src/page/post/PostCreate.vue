<template>
  <div class="container taeduri col-md-6">
    <div class="form-group">
      <label class="d-flex">Image</label>
      <!-- <input type="text" class="form-control" id="imgurl" v-model="PostCreate.imgurl"> -->
      <div class="col-md-8 p-0" align="left">
        <img
          class="card-img mb-2"
          v-if="this.PostCreate.imgurl"
          :src="this.PostCreate.imgurl"
          style="height: 16rem;"
        />
        <button type="button" class="btn btn-primary btn-sm" @click="onClickImageUpload">이미지 업로드</button>
      </div>
      <input ref="imageInput" type="file" hidden @change="onChangeImages" />
      <!-- <small class="form-text text-muted d-flex">원하는 사진을 업로드해주세요.</small> -->
    </div>
    <div class="form-group">
      <label class="d-flex">Title</label>
      <input type="text" class="form-control" id="title" v-model="PostCreate.title" />
      <small class="form-text text-muted d-flex" v-if="!error.title">상품명을 입력하세요.</small>
      <small class="form-text d-flex" style="color:red;" v-if="error.title">{{error.title}}</small>
    </div>
    <div class="form-group">
      <label class="d-flex justify-content-start">Activity</label>
      <input type="text" class="form-control" id="activity" v-model="PostCreate.activity" />
      <small class="form-text text-muted d-flex" v-if="!error.activity">활동명을 입력하세요.</small>
      <small class="form-text d-flex" style="color:red;" v-if="error.activity">{{error.activity}}</small>
    </div>
    <div class="form-group">
      <label class="d-flex justify-content-start">Location</label>
      <input type="text" class="form-control" id="location" v-model="PostCreate.location" />
      <small class="form-text text-muted d-flex">주소를 입력하세요.</small>
    </div>
    <div class="form-group">
      <label class="d-flex justify-content-start">Price</label>
      <input type="text" class="form-control" id="price" v-model="PostCreate.price" />
      <small class="form-text text-muted d-flex" v-if="!error.price">가격을 입력하세요.</small>
      <small class="form-text d-flex" style="color:red;" v-if="error.price">{{error.price}}</small>
    </div>
    <div class="form-group">
      <label class="d-flex justify-content-start">Expiration-Date</label>
      <!-- <div class="d-flex justify-content-between"> -->
        <div>
          <small class="form-text text-muted d-flex">시작일</small>
          <b-form-datepicker id="sdate" v-model="PostCreate.sdate" class="col-md-5"></b-form-datepicker>
        </div>
        <div>
          <small class="form-text text-muted d-flex">마감일</small>
          <b-form-datepicker id="edate" v-model="PostCreate.edate" class="col-md-5"></b-form-datepicker>
        </div>
      <!-- </div> -->
      <small class="form-text text-muted d-flex">상품 유효기간을 지정해주세요.</small>
    </div>
    <div class="form-group">
      <label class="d-flex justify-content-start">Corporation-Detail</label>
      <textarea
        class="form-control"
        id="company-information"
        v-model="PostCreate.companyInfo">
      </textarea>
      <small class="form-text text-muted d-flex" v-if="!error.companyInfo">업체 정보를 입력하세요.</small>
      <small class="form-text d-flex" style="color:red;" v-if="error.companyInfo">{{error.companyInfo}}</small>
    </div>
    <div class="form-group">
      <label class="d-flex justify-content-start">Detail-Info</label>
      <textarea class="form-control" id="detail" v-model="PostCreate.detail"></textarea>
      <small class="form-text text-muted d-flex" v-if="!error.detail">상품 상세정보를 입력하세요.</small>
      <small class="form-text d-flex" style="color:red;" v-if="error.detail">{{error.detail}}</small>
    </div>
    
    <!-- <a type="button" class="btn btn-outline form-check mb-2" href="#" @click="gocreate()"> -->
    <div class="d-flex justify-content-end mb-5">
      <button
        type="submit"
        class="btn btn-outline mr-1"
        style="font-size: 1rem; color: gray;"
        @click="tempSave"
      >임시저장</button>
      <button type="submit" class="btn btn-outline pr-0" style="font-size: 1.1rem;" @click="regist">
        <i class="fas fa-pen mr-1"></i>등록
      </button>
    </div>
  </div>
</template>

<script>
const baseURL = "http://localhost:8080/";

import axios from "axios";
import Swal from 'sweetalert2'

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
      error: {
        activity: false,
        detail: false,
        price: false,
        companyInfo: false,
        title: false
      },
    };
  },
  watch:{
    PostCreate: {
      handler: function(val){
        if(!/^[0-9]+$/g.test(val.price) && val.price.length>0){
          this.error.price = "가격은 숫자만 입력 가능합니다.";
        }else{
          this.error.price=false;
        }
        // const reg = /[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/;
        // if(reg.exec(val.price)!==null){
        //   this.error.price = "가격은 숫자만 입력 가능합니다.";
        // }
      },
      deep: true
    },
  },
  methods: {
    regist: function () {
      var flag = 0;
      if (this.PostCreate.activity == "") {
        this.error.activity = "활동명은 빈칸일 수 없습니다.";
        flag = 1;
      }
      if (this.PostCreate.detail == "") {
        this.error.detail = "상품 세부정보는 빈칸일 수 없습니다.";
        flag = 1;
      }
      if (this.PostCreate.companyInfo == "") {
        this.error.companyInfo = "업체 정보는 빈칸일 수 없습니다.";
        flag = 1;
      }
      if (this.PostCreate.price == "") {
        this.error.price = "가격은 빈칸일 수 없습니다.";
        flag = 1;
      }
      if (this.PostCreate.title == "") {
        this.error.title = "상품명은 빈칸일 수 없습니다.";
        flag = 1;
      }
      if(flag==1){
        alert("정보를 모두 입력해주세요.")
        return;
      }

      const Toast = Swal.mixin({
        toast: true,
        position: 'top-end',
        showConfirmButton: false,
        timer: 3000,
        timerProgressBar: true,
        onOpen: (toast) => {
          toast.addEventListener('mouseenter', Swal.stopTimer)
          toast.addEventListener('mouseleave', Swal.resumeTimer)
        }
      })

      axios
        .post(`${baseURL}/post/regist`, this.PostCreate)
        .then((response) => {
          console.log(response.data);
          Toast.fire({
            icon: 'success',
            title:'작성이 완료되었습니다.'
          })
          this.$router.push("/posts");
        })
        .catch((error) => {
          console.log("error입니다.");
        });
    },
    tempSave() {
      //임시저장 메소드
      if (this.PostCreate.title == "") {
        this.error.title = "상품명은 빈칸일 수 없습니다.";
        alert("정보를 확인해주세요");
        return;
      }
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
<style>
.sdate{
  margin: 0 !important;
}
</style>