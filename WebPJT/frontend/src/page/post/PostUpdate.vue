<template>
  <div>
    <h1>{{ this.PostUpdate.email }}</h1>
    제목: <input type="text"  v-model="PostUpdate.title" placeholder="PostCreate.Ctitle"><br>
    위치: <input type="text"  v-model="PostUpdate.location" placeholder="PostCreate.Clocation"><br>
    이미지: <input type="text"  v-model="PostUpdate.imgurl" placeholder="PostCreate.Cimgurl"><br>
    가격: <input type="text"  v-model="PostUpdate.price" placeholder="PostCreate.Cprice"><br>
    시작일: <input type="text"  v-model="PostUpdate.sdate" placeholder="PostCreate.Csdate"><br>
    마감일: <input type="text"  v-model="PostUpdate.edate" placeholder="PostCreate.Cedate"><br>
    회사 정보: <input type="text"  v-model="PostUpdate.company_info" placeholder="PostCreate.Ccompany_info"><br>
    세부 정보: <input type="text"  v-model="PostUpdate.detail" placeholder="PostCreate.Cdetail"><br>
    활동: <input type="text"  v-model="PostUpdate.activity" placeholder="PostCreate.Cactivity"><br>
    <button class="btn btn-primary">수정하기</button>

  </div>
</template>

<script>
import axios from "axios";

const baseURL = "http://localhost:8080/post";

export default {
  data(){
    return{
      PostUpdate: {
        email:'',
        title:'',
        location:'',
        imgurl:'',
        price:'',
        sdate:'',
        edate:'',
        company_info:'',
        detail:'',
        activity:'',
    },
      PostCreate: {
        Ctitle:'',
        Clocation:'',
        Cimgurl:'',
        Cprice:'',
        Csdate:'',
        Cedate:'',
        Ccompany_info:'',
        Cdetail:'',
        Cactivity:'',
      },
    }
  },
 created() {
  this.PostUpdate.email  = this.$cookies.get('User')
 },

}
</script>

<style>

</style>