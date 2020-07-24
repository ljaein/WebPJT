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
                >가격 : {{post.price}}원 </p>
                <div class="d-flex justify-content-end mr-0 mt-0">
                    <div class="d-flex justify-content-start">
                      <i
                          class="fas fa-heart select-button mr-2"
                          style="text-align: right; font-size: 20px; color:red"
                        ></i> {{post.likecnt}}명이 좋아요를 눌렀습니다.
                    </div>
                        <div>
        
                           <a href="javascript:;" @click="test()" id="kakao-link-btn">
                             
                          <img src="//developers.kakao.com/assets/img/about/logos/kakaolink/kakaolink_btn_small.png" width="50px" />
                           </a>
                          </div>
                  <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#BasketModal">장바구니</button>
                  <BasketModal />
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
    <hr>
    <div class="d-flex bg-white">댓글 수 : {{receiveComment.length}}</div>
    <CommentList v-for="comment in receiveComment" :key="comment.rid" :comment="comment" @comment-delete="commentDelete"/>
  
    
      
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
import BasketModal from '../../components/modal/BasketModal.vue'

import CommentInput from '../../components/comment/CommentInput.vue'
import CommentList from '../../components/comment/CommentList.vue'

const baseURL = "http://localhost:8080";

export default {
  components: {
    CommentInput,
    CommentList,
    BasketModal
  },
  data(){
    return{
      post:[],
      pid:"",
      receiveComment: [],
    }
  },
  created() {
        this.pid = this.$route.params.ID,
        this.getPost();
        this.fetchComment()
  },
  methods: {
         test(){
            Kakao.init('765ed14c0d508f8aa48c6d173446acba'); 
            Kakao.Link.createDefaultButton({
            container: '#kakao-link-btn',
            objectType: 'feed',
            content: {
              title: '상세페이지 제목 호출',
              description: '내용, 주로 해시태그',
              imageUrl: document.images[0].src,
              link: {
                webUrl: 'http://localhost:3000/#/posts/' + this.pid,
                mobileWebUrl: 'https://developers.kakao.com'
              }
          },
          social: {
            likeCount: 286,
            commentCount: 45,
            sharedCount: 845
          },
          buttons: [
            {
              title: 'Open!',
              link: {
                mobileWebUrl: 'https://developers.kakao.com',
                webUrl: 'http://localhost:3000/#/posts/' + this.pid     
              }
            }  
          ]
        });
        },
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
      axios.post(`${baseURL}/reply/register`,commentData)
        .then((response) => {
          this.fetchComment();

          console.log(response.data)
        }).catch((error) => {
          console.log(error)
        })
    },
    fetchComment() {
      axios.get(`${baseURL}/reply/list/${this.$route.params.ID}`)
        .then((response) => {
          this.receiveComment = response.data
          console.log(response.data)
        }).catch((error) => {
          console.log(error.response.data)
        })
    },
    commentDelete(comment) {
      axios.delete(`${baseURL}/reply/delete/${comment.rid}`)
        .then((response) => {
          this.fetchComment()
        }).catch((error) => {
          console.log(error.response.data)
        })
    },
  },
 
};
</script>







<style>
</style>