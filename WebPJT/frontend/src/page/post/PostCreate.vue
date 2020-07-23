<template>
  <div class="container p-5 taeduri">
    <div class="form-group">
      <label class="d-flex">IMG_URL</label>
      <input type="text" class="form-control" id="imgurl" v-model="PostCreate.imgurl">
      <small class="form-text text-muted d-flex">원하는 사진을 업로드해주세요.</small>
    </div>
    <div class="form-group">
      <label class="d-flex">TITLE</label>
      <input type="text" class="form-control" id="title" v-model="PostCreate.title">
      <small class="form-text text-muted d-flex">원하는 제목을 입력해주세요.</small>
    </div>
    <div class="form-group">
      <label class="d-flex justify-content-start">Location</label>
      <input type="text" class="form-control" id="location" v-model="PostCreate.location">
      <small class="form-text text-muted d-flex">해당 위치를 상세히 적어주세요.</small>
    </div>
    <div class="form-group">
      <label class="d-flex justify-content-start">Price</label>
      <input type="text" class="form-control" id="price" v-model="PostCreate.price">
      <small class="form-text text-muted d-flex">해당 엑티비티의 가격을 책정해주세요.</small>
    </div>
    <div class="form-group">
      <label class="d-flex justify-content-start">Start-Date</label>
      <input type="text" class="form-control" id="start-date" v-model="PostCreate.sdate">
      <small class="form-text text-muted d-flex">시작일을 지정해주세요.</small>
    </div>
    <div class="form-group">
      <label class="d-flex justify-content-start">End-Date</label>
      <input type="text" class="form-control" id="end-date" v-model="PostCreate.edate">
      <small class="form-text text-muted d-flex">마감일을 지정해주세요.</small>
    </div>
    <div class="form-group">
      <label class="d-flex justify-content-start">Corporation-Detail</label>
      <input type="textarea" class="form-control" id="company-information" v-model="PostCreate.companyInfo">
      <small class="form-text text-muted d-flex">업체 정보를 입력해주세요.</small>
    </div>
    <div class="form-group">
      <label class="d-flex justify-content-start">Detail-Information</label>
      <input type="text" class="form-control" id="detail" v-model="PostCreate.detail">
      <small class="form-text text-muted d-flex">세부 정보를 입력해주세요.</small>
    </div>
    <div class="form-group">
      <label class="d-flex justify-content-start">Activity</label>
      <input type="text" class="form-control" id="activity" v-model="PostCreate.activity">
      <small class="form-text text-muted d-flex">활동명을 입력해주세요.</small>
    </div>
    <button type="submit" class="btn btn-primary d-flex justify-content-start" @click="regist">제출</button>
  </div>
</template>

<script>

const baseURL = "http://localhost:8080/post"

import axios from 'axios'

export default {
  data: function() {
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
    }
  },
  methods: {
    regist: function() {
      axios.post(`${baseURL}/regist`,this.PostCreate)
        .then((response) => {
          console.log(response.data)
        }).catch((error) => {
          console.log('error입니다.')
        })
    },
  },
  created() {
    this.PostCreate.email = this.$cookies.get('User')
  },
}
</script>

<style>
.taeduri {
  border: 2px solid black;
}
</style>