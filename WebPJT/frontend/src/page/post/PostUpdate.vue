<template>
  <div class="container p-5 taeduri">
    <div class="form-group">
      <label class="d-flex">IMG_URL</label>
      <input type="text" class="form-control" id="imgurl" v-model="PostUpdate.imgurl">
      <small class="form-text text-muted d-flex">원하는 사진을 업로드해주세요.</small>
    </div>
    <div class="form-group">
      <label class="d-flex">TITLE</label>
      <input type="text" class="form-control" id="title" v-model="PostUpdate.title">
      <small class="form-text text-muted d-flex">원하는 제목을 입력해주세요.</small>
    </div>
    <div class="form-group">
      <label class="d-flex justify-content-start">Location</label>
      <input type="text" class="form-control" id="location" v-model="PostUpdate.location" >
      <small class="form-text text-muted d-flex">해당 위치를 상세히 적어주세요.</small>
    </div>
    <div class="form-group">
      <label class="d-flex justify-content-start">Price</label>
      <input type="text" class="form-control" id="price" v-model="PostUpdate.price" >
      <small class="form-text text-muted d-flex">해당 엑티비티의 가격을 책정해주세요.</small>
    </div>
    <div class="form-group">
      <label class="d-flex justify-content-start">Start-Date</label>
      <input type="text" class="form-control" id="start-date" v-model="PostUpdate.sdate">
      <small class="form-text text-muted d-flex">시작일을 지정해주세요.</small>
    </div>
    <div class="form-group">
      <label class="d-flex justify-content-start">End-Date</label>
      <input type="text" class="form-control" id="end-date" v-model="PostUpdate.edate" >
      <small class="form-text text-muted d-flex">마감일을 지정해주세요.</small>
    </div>
    <div class="form-group">
      <label class="d-flex justify-content-start">Corporation-Detail</label>
      <input type="textarea" class="form-control" id="company-information" v-model="PostUpdate.companyInfo" >
      <small class="form-text text-muted d-flex">업체 정보를 입력해주세요.</small>
    </div>
    <div class="form-group">
      <label class="d-flex justify-content-start">Detail-Information</label>
      <input type="text" class="form-control" id="detail" v-model="PostUpdate.detail" >
      <small class="form-text text-muted d-flex">세부 정보를 입력해주세요.</small>
    </div>
    <div class="form-group">
      <label class="d-flex justify-content-start">Activity</label>
      <input type="text" class="form-control" id="activity" v-model="PostUpdate.activity" >
      <small class="form-text text-muted d-flex">활동명을 입력해주세요.</small>
    </div>
    <button type="submit" class="btn btn-primary d-flex justify-content-start" @click="modify">제출</button>
  </div>
</template>

<script>
import axios from "axios";

const baseURL = "http://localhost:8080/post";

export default {
  data(){
    return{
      PostUpdate: [],
      pid:''
      // Instance_Date: []
    }
  },
  methods: {
    fetchDetail:function() {
      axios.get(`${baseURL}/detail/${this.pid}`)
        .then((response) => {
          this.PostUpdate = response.data
        }).catch((error) => {
          console.log(error.response.data)
        })
    },
    modify: function() {
      axios.put(`${baseURL}/modify`,this.PostUpdate)
        .then(() => {
          alert('수정 완료!!')
          this.$router.push({
            name: "PostListDetailView",
            params: { ID: this.pid }
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