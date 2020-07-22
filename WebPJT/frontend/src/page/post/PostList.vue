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
    <!-- <div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">
      <ol class="carousel-indicators">
        <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
        <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
        <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
        <li data-target="#carouselExampleIndicators" data-slide-to="3"></li>
      </ol>
      <div class="carousel-inner">
        <div class="carousel-item active">
          <img
            src="https://img1.daumcdn.net/thumb/R800x0/?scode=mtistory2&fname=https%3A%2F%2Ft1.daumcdn.net%2Fcfile%2Ftistory%2F997560335A1C150A27"
            class="d-block w-100"
            alt="..."
          />
        </div>
        <div class="carousel-item">
          <img
            src="https://lh3.googleusercontent.com/proxy/gkQKSCqih08jNeQrDx-D347lJyC1eC_ltW7Ex4DqBARyXAzoYkmRiWIS1pmzKauFXgQPEoGeoNqbXmqk1QXN6Twb0QWSf0vl8zutIimTaK-rsgwWLs8SCI4Bi4RwposDzw9PGJ5YUILkcyZoYLMRWI0vWTYRiDQXca_sC7RmhB9_OOH1KfO0E9Cl5tVdUg"
            class="d-block w-100"
            alt="..."
          />
        </div>
        <div class="carousel-item">
          <img
            src="https://img.redbull.com/images/q_auto,f_auto/redbullcom/2016/04/26/1331791076615_2/%EC%95%94%EB%B2%BD%EB%93%B1%EB%B0%98-%EC%BD%94%EC%8A%A4-top7.jpg"
            class="d-block w-100"
            alt="..."
          />
        </div>
        <div class="carousel-item">
          <img
            src="https://cdn0000.airklass.com/classes/340/new_cover-w1920-h1080?v=-1153120733"
            class="d-block w-100"
            alt="..."
          />
        </div>
      </div>
      <a
        class="carousel-control-prev"
        href="#carouselExampleIndicators"
        role="button"
        data-slide="prev"
      >
        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
        <span class="sr-only">Previous</span>
      </a>
      <a
        class="carousel-control-next"
        href="#carouselExampleIndicators"
        role="button"
        data-slide="next"
      >
        <span class="carousel-control-next-icon" aria-hidden="true"></span>
        <span class="sr-only">Next</span>
      </a>
    </div>-->

    <div class="container" v-for="(post, index) in posts" :key="index">
      <div class="column">
        <div class="card mt-5 mb-3" style="max-width: 100%;" @click="getdetail(post.pid)">
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
    }
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
</style>