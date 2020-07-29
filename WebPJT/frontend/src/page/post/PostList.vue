<template>
  <div class="post">
    <div class="container col-md-6">
      <div class="input-group mb-5">
        <div class="input-group-prepend">
          <select
            class="btn dropdown-toggle text-black"
            style="border: 1px solid gray; z-index: 1;"
            aria-haspopup="true"
            aria-expanded="false"
            v-model="key"
          >
            <div role="separator" class="dropdown-divider"></div>
            <option value="">All</option>
            <!-- <option value="all">All</option> -->
            <option value="title">Title</option>
            <option value="activity">Activity</option>
            <option value="price">Price</option>
          </select>
        </div>
        <input
          type="text"
          class="form-control"
          placeholder="Search"
          v-model="word"
          @keypress.enter="search"
        />
      </div>

      <!-- <div class="d-flex justify-content-end">
        <a type="button" class="btn btn-outline form-check mb-2" @click="gocreate">
          <i class="fas fa-pen"></i> 상품 등록
        </a>
      </div> -->
      <div class="row justify-content-left">
        <div
          class="col-12 col-sm-12 col-md-3 card-deck"
          style="margin:auto 0;"
          v-for="(post, index) in posts"
          :key="index"
        >
          <div class="card mb-3 profile-post mr-0 ml-0">
            <div class="card-body" style="padding: 0;">
              <img
                :src="post.imgurl"
                class="card-img"
                
                style="height:10rem"
              />
              <div class="card-img-overlay" @click="getdetail(post.pid)" style="padding:4rem 0; text-align:center; font-size:1.3rem; font-weight:bold; color: white;">
                <!-- <button class="location-button">{{post.location}}</button> -->
                <p>{{post.location}}</p>
            </div>
            <div class="col-md-12 p-0">
              <div class="card-body" style="padding: 5px;">
                  <p
                    class="card-text mb-2"
                    style="font-size: 1rem; text-align: left; text-overflow:ellipsis;overflow: hidden;white-space: nowrap; color:gray"
                  >{{post.sdate}}~{{post.edate}}</p>
                <h5
                  class="card-title"
                  @click="getdetail(post.pid)"
                  style="font-size: 1rem; text-align: left; margin-bottom: 1rem; text-overflow:ellipsis;overflow: hidden;white-space: nowrap;"
                >{{post.title}}</h5>
                <div class="text d-flex justify-content-between">
                  <p
                    class="card-text"
                    style="font-size: 1rem; text-align: left; text-overflow:ellipsis;overflow: hidden;white-space: nowrap;"
                  >가격 : {{post.price}}</p>

                  <!-- heart like -->
                  <div id="heart" @click="registlike(post.pid)">
                  {{post.likecnt}}
                  <i
                    v-if="check(post.pid)"
                    class="fas fa-heart select-button like-button"
                    style="text-align: right; font-size: 20px; color:crimson;"
                  ></i>
                  <i
                    v-if="!check(post.pid)"
                    class="far fa-heart"
                    style="text-align: right; font-size: 20px;"
                  ></i>
                </div>


                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>

    <!-- top button -->
    <i class="fas fa-2x fa-angle-double-up upBtn" @click="toTop" style="cursor:pointer;"></i>
    <!-- infinite loading -->
    <infinite-loading :identifier="infiniteId" @infinite="infiniteHandler" spinner="waveDots">
      <div slot="no-more">
        <a @click="toTop">Top</a>
      </div>
      <div slot="no-result"></div>
    </infinite-loading>
  </div>
  </div>
</template>
 
<script>
import "../../assets/css/postlist.css";
import axios from "axios";
import InfiniteLoading from 'vue-infinite-loading'
import Swal from 'sweetalert2'

// const Swal = require('sweetalert2')

const baseURL = "http://localhost:8080";


export default {
  components: {
    InfiniteLoading
  },
  data() {
    return {
      page: 1,
      infiniteId: 0,
      posts: {
        pid: "",
        email: "",
        activity: "",
        title: "",
        location: "",
        imgurl: "",
        price: "",
        sdate:"",
        edate:"",
        likecnt:""
      },
      key: "",
      word: "",
      email: "",
      postLike: [],
      cntLike: [],
    };
  },
  methods: {
    toTop() {
      scroll(0, 0);
    },
    infiniteHandler($state) {
      if(this.key==""){
        axios.get(`${baseURL}/post/getList?page=` + this.page)
        .then( res => {
          setTimeout(() => {
            if(res.data.length) {
              this.posts = this.posts.concat(res.data);
              $state.loaded();
              this.page += 1;
              if(this.posts.length / 9 == 0) {
                $state.complete();
              }
            } else {
              $state.complete();
            }
          }, 1000);
        })
        .catch( err => {
          console.log(err);
        })
      } else {
        axios.get(`${baseURL}/post/search/${this.key}/${this.word}?page=` + this.page)
        .then( res => {
          setTimeout(() => {
            if(res.data.length) {
              this.posts = this.posts.concat(res.data);
              $state.loaded();
              this.page += 1;
              if(this.posts.length / 9 == 0) {
                $state.complete();
              }
            } else {
              $state.complete();
            }
          }, 1000);
        })
        .catch( err => {
          console.log(err);
        })
      }
    },
    check(pid) {
      for (var i = 0; i < this.postLike.length; i++) {
        if (this.postLike[i] == pid) {
          return true;
        }
      }
      return false;
    },
    getdetail(pid) {
      this.$router.push({
        name: "PostListDetail",
        params: { ID: pid },
      });
    },
    search() {
      this.page = 1;
      this.infiniteId += 1;

      if (this.key == "") {
        this.word = "";
        this.init();
      } else {
        if (this.word == "") {
          alert("검색어를 입력하세요.");
        } else {
          this.page = 1;
          this.init();
        }
      }
    },
    registlike(pid) {
      if (this.$cookies.get('Auth-Token')) {
        axios
          .get(`${baseURL}/like/registDelete/${this.email}/${pid}`)
          .then((res) => {
            this.checklike();
            this.init();
            if (this.check(pid) == false) {
              this.$toasted.show('좋아좋아요!', {
              theme: 'bubble',
              position: 'top-right',
              duration:1000,
            })
            } else {
              this.$toasted.show('싫어싫어요!', {
              theme: 'bubble',
              position: 'top-right',
              duration:1000,
            })
            }
          })
          .catch((err) => {
            alert(err);
          });
      } else {
        Swal.fire({
          icon: 'error',
          text: '로그인 후 이용해주세요...',
          confirmButtonColor: '#fff',
          width:350,
          confirmButtonText: '<a data-toggle="modal" data-target="#LoginModal" style="font-size:1rem; color:black" >Login</a>',
          showCancelButton: true,
          cancelButtonText: '<a style="font-size:1rem; color:black">Cancel</a>',
          cancelButtonColor: '#fff',
        }).then((result) => {
          Swal.close()
        })
      }
    },
    checklike() {
      axios
        .get(`${baseURL}/like/check/${this.email}`)
        .then((res) => {
          this.postLike = res.data;
        })
        .catch((err) => {
          alert(err);
        });
    },
    cntlike() {
      axios
        .get(`${baseURL}/like/cnt`)
        .then((res) => {
          this.cntLike = res.data;
        })
        .catch((err) => {
          alert(err);
        });
    },
    init() {
      // axios
      //   .get(`${baseURL}/post/list/`)
      //   .then((res) => {
      //     this.posts = res.data;
      //   })
      //   .catch((err) => {
      //     console.log(err);
      //   });
       if(this.key ==""){
        axios
        .get(`${baseURL}/post/getList?page=0`)
        .then((res) => {
          this.posts = res.data;
        })
        .catch((err) => {
          console.log(err);
        });
      } else {
        axios
        .get(`${baseURL}/post/search/${this.key}/${this.word}?page=0`)
        .then((res) => {
          this.posts = res.data;
        })
        .catch((err) => {
          console.log(err);
        })
      }
    },
  },
  created() {
    this.email = this.$cookies.get("User");
    // if(this.email == null) {
    //   this.flag = false;
    // } else {
    //   this.flag = true;
    // }
    this.init();
    this.checklike();
  },
};
</script>

<style>
.carousel-inner {
  width: 100%;
  height: 25rem;
}
.carousel-inner img {
  height: 25rem;
}

.card-img-left {
  width: 15rem;
}
.post-title {
  font-size: 3rem;
  text-align: center;
}
.postlist {
  cursor: pointer;
}
.card-title, .card-img-overlay{
  cursor:pointer;
}
.upBtn { 
  position:fixed;
  right:5%;
  top:90%;
  color: red;
}
.confirmButton {
  border: red;
}
</style>
