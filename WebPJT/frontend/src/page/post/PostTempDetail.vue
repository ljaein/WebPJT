<template>
  <div class="container p-5 taeduri">
    <div class="form-group">
      <label class="d-flex">IMG_URL</label>
      <input type="text" class="form-control" id="imgurl" v-model="PostTemp.imgurl">
      <small class="form-text text-muted d-flex">원하는 사진을 업로드해주세요.</small>
    </div>
    <div class="form-group">
      <label class="d-flex">TITLE</label>
      <input type="text" class="form-control" id="title" v-model="PostTemp.title">
      <small class="form-text text-muted d-flex">원하는 제목을 입력해주세요.</small>
    </div>
    <div class="form-group">
      <label class="d-flex justify-content-start">Location</label>
      <input type="text" class="form-control" id="location" v-model="PostTemp.location" >
      <small class="form-text text-muted d-flex">해당 위치를 상세히 적어주세요.</small>
    </div>
    <div class="form-group">
      <label class="d-flex justify-content-start">Price</label>
      <input type="text" class="form-control" id="price" v-model="PostTemp.price" >
      <small class="form-text text-muted d-flex">해당 엑티비티의 가격을 책정해주세요.</small>
    </div>
    <div class="form-group">
      <label class="d-flex justify-content-start">Start-Date</label>
      <input type="text" class="form-control" id="start-date" v-model="PostTemp.sdate">
      <small class="form-text text-muted d-flex">시작일을 지정해주세요.</small>
    </div>
    <div class="form-group">
      <label class="d-flex justify-content-start">End-Date</label>
      <input type="text" class="form-control" id="end-date" v-model="PostTemp.edate" >
      <small class="form-text text-muted d-flex">마감일을 지정해주세요.</small>
    </div>
    <div class="form-group">
      <label class="d-flex justify-content-start">Corporation-Detail</label>
      <input type="textarea" class="form-control" id="company-information" v-model="PostTemp.companyInfo" >
      <small class="form-text text-muted d-flex">업체 정보를 입력해주세요.</small>
    </div>
    <div class="form-group">
      <label class="d-flex justify-content-start">Detail-Information</label>
      <input type="text" class="form-control" id="detail" v-model="PostTemp.detail" >
      <small class="form-text text-muted d-flex">세부 정보를 입력해주세요.</small>
    </div>
    <div class="form-group">
      <label class="d-flex justify-content-start">Activity</label>
      <input type="text" class="form-control" id="activity" v-model="PostTemp.activity" >
      <small class="form-text text-muted d-flex">활동명을 입력해주세요.</small>
    </div>
    <button type="submit" class="btn btn-primary d-flex justify-content-start" @click="tempSave">임시저장</button>
  </div>
</template>

<script>
import axios from "axios";

const baseURL = "http://localhost:8080/temp";

export default {
  data(){
    return{
      PostTemp: [],
      pid:''
      // Instance_Date: []
    }
  },
  methods: {
    fetchDetail:function() {
      axios.get(`${baseURL}/detail/${this.pid}`)
        .then((response) => {
          this.PostTemp = response.data
        }).catch((error) => {
          console.log(error.response.data)
        })
    },
    tempSave: function() {
      axios.put(`${baseURL}/modify`,this.PostTemp)
        .then(() => {
          alert('수정 완료!!')
          this.$router.push({
            name: "PostTemp"
          });
        }).catch((error) => {
          console.log(error.response.data)
        })
    },
  },
  created() {
    this.pid = this.$route.params.ID
    this.fetchDetail()
  },

}
</script>

<style>

</style>