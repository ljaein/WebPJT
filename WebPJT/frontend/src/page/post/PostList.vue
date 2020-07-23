<template>
  <div class="post">
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
    <button type="button" @click="search">검색</button>
    <div class="d-flex justify-content-end">
      <button class="btn btn-primary" @click="createpost">새 글 작성</button>
    </div>
    <div class="container" v-for="(post, index) in posts" :key="index">
      <div class="column">
        <div class="card mt-5 mb-3 postlist" style="max-width: 100%;" @click="getdetail(post.pid)">
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
                  style="font-size: 2.2rem; text-align: center; margin-bottom: 1rem; text-overflow:ellipsis;overflow: hidden;white-space: nowrap;"
                >{{post.title}}</h5>
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
                    style="font-size: 1.1rem; text-align: left; text-overflow:ellipsis;overflow: hidden;white-space: nowrap;"
                  >가격 : {{post.price}}</p>
                  <div class="d-flex justify-content-end">
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
        price: ""
      },
      key: "",
      word: ""
    };
  },
  methods: {
    getdetail(pid) {
      this.$router.push({
        name: "PostListDetailView",
        params: { ID: pid }
      });
    },
    search() {
      if (this.key == "all" || this.key=="") {
        axios
          .get(`${baseURL}/list/`)
          .then(res => {
            this.posts = res.data;
          })
          .catch(err => {
            console.log(err);
          });
      } else {
        if (this.word == "") {
          alert("검색어를 입력하세요.");
        } else {
          axios
            .get(`${baseURL}/search/${this.key}/${this.word}`)
            .then(res => {
              this.posts = res.data;
            })
            .catch(err => {
              alert("올바른 값을 입력하세요.");
            });
        }
      }
    },
    createpost() {
      this.$router.push('/postcreate')
    },
  },
  created() {
    axios
      .get(`${baseURL}/list/`)
      .then(res => {
        this.posts = res.data;
      })
      .catch(err => {
        console.log(err);
      });
  }
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
/* listhover:hover {
  color: burlywood;
} */
</style>