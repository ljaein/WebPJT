<template>
  <div class="container" style="margin-top: 100px">
    <div class="column">
      <div class="card mt-5 mb-3" style="max-width: 100%;">
        <div class="row no-gutters">
          <div class="col-md-4">
            <img
              src="https://cdn0000.airklass.com/classes/340/new_cover-w1920-h1080?v=-1153120733"
              class="card-img"
              style="height: 16rem;"
              alt
            />
          </div>
          <div class="col-md-8">
            <div class="card-body">
              <h5
                class="card-title"
                style="font-size: 2.2rem; text-align: center; margin-bottom: 1rem;
                text-overflow:ellipsis;overflow: hidden;white-space: nowrap;"
              > {{post.title}}</h5>
              <div class="text">
                <p
                  class="card-text"
                  style="font-size: 1.1rem; text-align: left; text-overflow:ellipsis;overflow: hidden;white-space: nowrap;"
                >액티비티 : {{post.activity}}</p>
                <p
                  class="card-text"
                  style="font-size: 1.1rem; text-align: left; text-overflow:ellipsis;overflow: hidden;white-space: nowrap;"
                >지역 : {{post.location}}</p>
                <p
                  class="card-text"
                  style="font-size: 1.1rem; text-align: left; margin-bottom: 5px;
                  text-overflow:ellipsis;overflow: hidden;white-space: nowrap;"
                >가격 : {{post.price}}원</p>
                <div class="d-flex justify-content-end mr-0 mt-0">
                  <button class="btn btn-primary">장바구니</button>
                  <button class="btn btn-primary">구매하기</button>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <nav id="navbar-example2" class="navbar navbar-light bg-light">
      <ul class="nav justify-content-between" style="width:100%;">
        <li class="nav-item">
          <a class="nav-link" href="#item-info" @click="goinfo()">상세 정보</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#review" @click="goinfo()">후기</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#store-info" @click="goinfo()">업체 정보</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#qna" @click="goinfo()">Q&A</a>
        </li>
      </ul>
    </nav>
    <br />
    <div data-spy="scroll" data-target="#navbar-example2" data-offset="0">
      <h4 id="item-info">상세 정보</h4>
      <p>{{post.detail}}</p>
      <h4 id="store-info">업체 정보</h4>
      <p>{{post.companyInfo}}</p>
      <h4 id="review">후기</h4>
      <p>Ad leggings keytar, brunch id art party dolor labore. Pitchfork yr enim lo-fi before they sold out qui. Tumblr farm-to-table bicycle rights whatever. Anim keffiyeh carles cardigan. Velit seitan mcsweeney's photo booth 3 wolf moon irure. Cosby sweater lomo jean shorts, williamsburg hoodie minim qui you probably haven't heard of them et cardigan trust fund culpa biodiesel wes anderson aesthetic. Nihil tattooed accusamus, cred irony biodiesel keffiyeh artisan ullamco consequat.</p>
      <p>Ad leggings keytar, brunch id art party dolor labore. Pitchfork yr enim lo-fi before they sold out qui. Tumblr farm-to-table bicycle rights whatever. Anim keffiyeh carles cardigan. Velit seitan mcsweeney's photo booth 3 wolf moon irure. Cosby sweater lomo jean shorts, williamsburg hoodie minim qui you probably haven't heard of them et cardigan trust fund culpa biodiesel wes anderson aesthetic. Nihil tattooed accusamus, cred irony biodiesel keffiyeh artisan ullamco consequat.</p>
      <p>Ad leggings keytar, brunch id art party dolor labore. Pitchfork yr enim lo-fi before they sold out qui. Tumblr farm-to-table bicycle rights whatever. Anim keffiyeh carles cardigan. Velit seitan mcsweeney's photo booth 3 wolf moon irure. Cosby sweater lomo jean shorts, williamsburg hoodie minim qui you probably haven't heard of them et cardigan trust fund culpa biodiesel wes anderson aesthetic. Nihil tattooed accusamus, cred irony biodiesel keffiyeh artisan ullamco consequat.</p>
      <p>Ad leggings keytar, brunch id art party dolor labore. Pitchfork yr enim lo-fi before they sold out qui. Tumblr farm-to-table bicycle rights whatever. Anim keffiyeh carles cardigan. Velit seitan mcsweeney's photo booth 3 wolf moon irure. Cosby sweater lomo jean shorts, williamsburg hoodie minim qui you probably haven't heard of them et cardigan trust fund culpa biodiesel wes anderson aesthetic. Nihil tattooed accusamus, cred irony biodiesel keffiyeh artisan ullamco consequat.</p>
      
      <h4 id="qna">Q&A</h4>
      <p>Ad leggings keytar, brunch id art party dolor labore. Pitchfork yr enim lo-fi before they sold out qui. Tumblr farm-to-table bicycle rights whatever. Anim keffiyeh carles cardigan. Velit seitan mcsweeney's photo booth 3 wolf moon irure. Cosby sweater lomo jean shorts, williamsburg hoodie minim qui you probably haven't heard of them et cardigan trust fund culpa biodiesel wes anderson aesthetic. Nihil tattooed accusamus, cred irony biodiesel keffiyeh artisan ullamco consequat.</p>
    </div>

    <hr>
    <!-- 댓글 작성 -->
    <CommentInput @create-comment="createcomment" />

    <!-- 댓글 List -->
      
      <!-- 글 수정 삭제 -->
      <hr>
      <div class="d-flex justify-content-end">
        <button class="btn btn-success" @click="goModify">글 수정하기</button>
        <button class="btn btn-danger" @click="goDelete">글 삭제하기</button>
      </div>
      
  </div>
</template>

<script>
import axios from "axios";
import PostUpdateVue from './PostUpdate.vue';

import CommentInput from '../../components/comment/CommentInput.vue'

const baseURL = "http://localhost:8080";

export default {
  components: {
    CommentInput,
  },
  data(){
    return{
      post:[],
      pid:"",
    }
  },
  methods: {
    goinfo() {
      this.$router.go();
    },
    getPost() {
      axios
        .get(`${baseURL}/post/detail/${this.$route.params.ID}`)
        .then(res => {
          this.post = res.data;
        })
        .catch(err => {
          console.log(err);
        });
    },
    goModify() {
      this.$router.push({
        name: "PostUpdate",
        params: {ID : this.pid},
      })
    },
    goDelete() {
      axios.delete(`${baseURL}/post/delete/${this.$route.params.ID}`)
        .then(() => {
          alert('삭제 완료')
          this.$router.push(`/posts`)
        }).catch((error) => {
          console.log(error.response.data)
        })
    },
    createcomment(commentData) {
      alert(commentData.nickname)
      alert(commentData.content)
      alert(commentData.pid)
      axios.post(`${baseURL}/reply/register`,commentData)
        .then((response) => {
          console.log(response.data)
        }).catch((error) => {
          console.log(error)
        })
    },
  },
  created() {
    this.pid = this.$route.params.ID,
    this.getPost();
  }
};
</script>

<style>
</style>