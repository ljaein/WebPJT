<template>
  <div class="post">
    <div class="input-group mb-3">
      <div class="input-group-prepend">
        <select class="btn btn-outline-secondary dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" v-model="key">
          <option  value="all">All</option>
          <div role="separator" class="dropdown-divider"></div>
          <option  value="title">Title</option>
          <option value="activity">Activity</option>
          <option  value="price">Price</option>
        </select>
      </div>
      <input type="text" class="form-control"  placeholder="Search" v-model="word" @keypress.enter="search">
    </div>

<!-- 
    <select v-model="key">
      <option value="all">전체검색</option>
      <option value="title">제목</option>
      <option value="activity">액티비티</option>
      <option value="price">가격</option>
    </select>
    <input
      class="my-2 my-lg-0 mr-2 bg-light"
      type="text"
      placeholder="Search"
      v-model="word"
      @keypress.enter="search"
    />
    <button type="button" @click="search"><i class="fas fa-search mr-1"></i>검색</button> -->

    <div class="container">
      <div class="d-flex justify-content-end">
        <a type="button" class="btn btn-outline-light form-check mb-2" href="#" @click="gocreate"><i class="fas fa-pen"></i> 포스트 작성</a>
      </div>
      <div class="row justify-content-between" >
        <div class="col-12 col-sm-12 col-md-4 card-deck" v-for="(post, index) in posts" :key="index">
          <div class="card mb-3 bg-dark profile-post"
              @click="getdetail(post.pid)">
              <div class="card-body" style="padding: 0;">
              <img
                src="https://cdn0000.airklass.com/classes/340/new_cover-w1920-h1080?v=-1153120733"
                class="card-img"
                style="height: 15rem;"
                alt
              />
            </div>
            <div class="col-md-12">
              <div class="card-body" style="padding: 20px 0px;" >
                <h5
                  class="card-title"
                  style="font-size: 1.2rem; text-align: left; margin-bottom: 1rem; text-overflow:ellipsis;overflow: hidden;white-space: nowrap;"
                >{{post.title}}</h5>
                <div class="text">
                  <p
                    class="card-text"
                    style="font-size: 1rem; text-align: left; text-overflow:ellipsis;overflow: hidden;white-space: nowrap;"
                  >가격 : {{post.price}}</p>
                  <div class="d-flex justify-content-end mt-0">
                    <i
                      class="fas fa-bookmark select-button mr-2"
                      style="text-align: right; font-size:20px;"
                    ></i>
                    <i
                      class="fas fa-heart select-button like-button"
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
</template>
 
<script>
import '../../assets/css/postlist.css'
import axios from "axios";

const baseURL = "http://localhost:8080/post";

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
      },
      key: "",
      word: "",
    };
  },
  methods: {
    gocreate(){
      this.$router.push({
        name: "PostCreate"
      })
    },
    getdetail(pid) {
      this.$router.push({
        name: "PostListDetail",
        params: { ID: pid },
      });
    },
    search() {
      if (this.key == "all" || this.key == "") {
        axios
          .get(`${baseURL}/list/`)
          .then((res) => {
            this.posts = res.data;
          })
          .catch((err) => {
            console.log(err);
          });
      } else {
        if (this.word == "") {
          alert("검색어를 입력하세요.");
        } else {
          axios
            .get(`${baseURL}/search/${this.key}/${this.word}`)
            .then((res) => {
              this.posts = res.data;
            })
            .catch((err) => {
              alert("올바른 값을 입력하세요.");
            });
        }
      }
    },
  },
  created() {
    axios
      .get(`${baseURL}/list/`)
      .then((res) => {
        this.posts = res.data;
      })
      .catch((err) => {
        console.log(err);
      });
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
</style>