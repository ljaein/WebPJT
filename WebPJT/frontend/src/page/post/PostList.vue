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
          class="col-12 col-sm-12 col-md-4 card-deck"
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
              <div class="card-img-overlay" @click="getdetail(post.pid)" style="padding:60px; text-align:center; font-size:1.3rem; font-weight:bold; color: white;">
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
                  <div id="heart" @click="registlike(post.pid)">
                  {{post.likecnt}}
                  <i
                    v-if="check(post.pid)"
                    class="fas fa-heart select-button like-button"
                    style="text-align: right; font-size: 20px; color:red;"
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
  </div>
  </div>
</template>
 
<script>
import "../../assets/css/postlist.css";
import axios from "axios";


const baseURL = "http://localhost:8080";

// const likeButtons = document.querySelectorAll('.like-button');

export default {
  data() {
    return {
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
    check(pid) {
      for (var i = 0; i < this.postLike.length; i++) {
        if (this.postLike[i] == pid) {
          return true;
        }
      }
      return false;
    },
    // gocreate() {
    //   this.$router.push({
    //     name: "PostCreate",
    //   })
    //   this.$router.go();
    // },
    getdetail(pid) {
      this.$router.push({
        name: "PostListDetail",
        params: { ID: pid },
      });
    },
    search() {
      if (this.key == "") {
        axios
          .get(`${baseURL}/post/list`)
          .then((res) => {
            this.posts = res.data;
            this.word = "";
          })
          .catch((err) => {
            console.log(err);
          });
      } else {
        if (this.word == "") {
          alert("검색어를 입력하세요.");
        } else {
          axios
            .get(`${baseURL}/post/search/${this.key}/${this.word}`)
            .then((res) => {
              this.posts = res.data;
              this.word = "";
            })
            .catch((err) => {
              alert("올바른 값을 입력하세요.");
            });
        }
      }
    },
    registlike(pid) {
      axios
        .get(`${baseURL}/like/registDelete/${this.email}/${pid}`)
        .then((res) => {
          this.checklike();
          this.init();
          if (this.check(pid) == false) {
            this.$toasted.show('좋아요!', {
            theme: 'bubble',
            position: 'top-right',
            duration:1000,
          })
          } else {
            this.$toasted.show('싫어졌어요!', {
            theme: 'bubble',
            position: 'top-right',
            duration:1000,
          })
          }
        })
        .catch((err) => {
          alert(err);
        });
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
      axios
        .get(`${baseURL}/post/list/`)
        .then((res) => {
          this.posts = res.data;
        })
        .catch((err) => {
          console.log(err);
        });
    },
  },
  created() {
    this.email = this.$cookies.get("User");
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
</style>
