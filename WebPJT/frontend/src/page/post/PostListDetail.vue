<template>
  <div class="container col-md-7" style="margin-top: 100px">
    <div class="column">
      <div class="card mt-5 mb-3" style="max-width: 100%;">
        <div class="row no-gutters">
          <div class="col-md-5">
            <img
              :src="post.imgurl"
              class="card-img"
              style="height: 16rem;"
              alt
            />
          </div>
          <div class="col-md-7">
            <div class="card-body" style="padding: 0 0 0 20px">
              <!-- <h5
                class="card-title"
                style="font-size: 2.2rem; text-align: center; margin-bottom: 1rem;
                text-overflow:ellipsis;overflow: hidden;white-space: nowrap;"
              > {{post.title}}</h5> -->
              <div class="text">
                <div class="d-flex justify-content-between" >
                <p
                  class="card-text"
                  style="font-size: 1rem; color: rgb(168, 168, 168); text-align: left; text-overflow:ellipsis;overflow: hidden;white-space: nowrap;"
                >[{{post.location}}]{{post.sdate}}~{{post.edate}}</p>
                  <a href="javascript:;" @click="test()" id="kakao-link-btn">  
                <img src="//developers.kakao.com/assets/img/about/logos/kakaolink/kakaolink_btn_small.png" width="28px" />
                  </a>
                </div>
                <p
                  class="card-text font-weight-bold"
                  style="font-size: 1.5rem; text-align: left; text-overflow:ellipsis;overflow: hidden;white-space: nowrap;"
                >[{{post.activity}}]{{post.title}}</p>
                <!-- <p
                  class="card-text"
                  style="font-size: 1rem; text-align: left; text-overflow:ellipsis;overflow: hidden;white-space: nowrap;"
                >액티비티 : {{post.activity}}</p> -->
                <!-- <p
                  class="card-text"
                  style="font-size: 1rem; text-align: left; text-overflow:ellipsis;overflow: hidden;white-space: nowrap;"
                >{{post.location}}</p> -->
                <p
                  class="card-text"
                  style="font-size: 1rem; color: rgb(168, 168, 168); text-decoration:line-through; text-align: left; margin-bottom: 5px;
                  text-overflow:ellipsis;overflow: hidden;white-space: nowrap;"
                >{{post.price}}</p>
                <p
                  class="card-text font-weight-bold"
                  style="font-size: 1.5rem; text-align: left; margin-bottom: 5px;
                  text-overflow:ellipsis;overflow: hidden;white-space: nowrap;
                  border-bottom: 0.5px solid rgb(218,218,218);"
                >{{post.price*0.95}} 원</p>
                <div class="d-flex justify-content-end mr-0 mt-3 mb-3">
                    <div class="d-flex justify-content-start">
                      <i
                          class="fas fa-heart select-button mr-2"
                          style="text-align: right; font-size: 20px; color:crimson"
                        ></i> {{post.likecnt}}명이 좋아요를 눌렀습니다.
                    </div>

                </div>
                <div class="d-flex justify-content-end">
                  <button type="button" class="btn btn-primary mr-1" data-toggle="modal" data-target="#BasketModal"><i class="fas fa-shopping-basket mr-2"></i>장바구니</button>
                  <BasketModal />
                  <button class="btn btn-danger"><i class="far fa-hand-point-up mr-2"></i>바로구매</button>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <nav id="navbar-example2" class="navbar nav-info">
      <ul class="nav justify-content-between" style="width:100%;">
        <li class="nav-item">
          <a class="nav-link info-link" href="#item-info" @click="goinfo()">상세 정보</a>
        </li>
        <li class="nav-item">
          <a class="nav-link info-link" href="#store-info" @click="goinfo()">업체 정보</a>
        </li>
        <li class="nav-item">
          <a class="nav-link info-link" href="#review" @click="goinfo()">후기</a>
        </li>
        <li class="nav-item">
          <a class="nav-link info-link" href="#qna" @click="goinfo()">Q&A</a>
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


    <!-- 댓글 List -->
    <br v-if="this.$cookies.get('User')">
    <div class="d-flex bg-white">Comment : {{receiveComment.length}}</div>
    <CommentList v-for="comment in receiveComment" :key="comment.rid" :comment="comment" @comment-delete="commentDelete"/>

    <!-- 댓글 작성 -->
    <CommentInput class="mt-3" v-if="this.$cookies.get('User')" @create-comment="createcomment" />
  
    
      
      <!-- 글 수정 삭제 -->
      <div class="d-flex justify-content-end mt-3 mb-3">
        <button class="btn btn-success" @click="goModify"><i class="far fa-edit mr-2"></i>수정하기</button>
        <button class="btn btn-danger" @click="goDelete"><i class="far fa-trash-alt mr-2"></i>삭제하기</button>
      </div>
      
  </div>
</template>

<script>
import axios from "axios";
import '../../assets/css/postlistdetail.css'
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
        this.fetchComment(),
        Kakao.init('765ed14c0d508f8aa48c6d173446acba');
  },
  methods: {
         test(){
            Kakao.Link.createDefaultButton({
            container: '#kakao-link-btn',
            objectType: 'feed',
            content: {
              title: this.post.title, // 콘텐츠의 타이틀
              description: this.post.activity,  // 콘텐츠 상세설명
              imageUrl: document.images[0].src, // 썸네일 이미지
              link: {
                webUrl: 'http://localhost:3000/#/posts/' + this.pid,
                mobileWebUrl: 'https://developers.kakao.com'
              }
          },
          social: {
            likeCount: 286, // LIKE 개수
            commentCount: 45, // 댓글 개수
            sharedCount: 845
          },
          buttons: [
            {
              title: 'Open!',  // 버튼 제목
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
          commentData.content = ''
          this.fetchComment();
        }).catch((error) => {
          console.log(error)
        })
    },
    fetchComment() {
      axios.get(`${baseURL}/reply/list/${this.$route.params.ID}`)
        .then((response) => {
          this.receiveComment = response.data
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