<template>
  <div class="container col-md-7">
    <button class="btn btn-spring btn-circle btn-xl mb-5 mr-5">
      <br />
      <br />
      <br />Spirng
    </button>
    <button class="btn btn-summer btn-circle btn-xl mb-5 mr-5">
      <br />
      <br />
      <br />Summer
    </button>
    <button class="btn btn-fall btn-circle btn-xl mb-5 mr-5">
      <br />
      <br />
      <br />Fall
    </button>
    <button class="btn btn-winter btn-circle btn-xl mb-5 mr-5">
      <br />
      <br />
      <br />Winter
    </button>
    <button class="btn btn-ground btn-circle btn-xl mb-5 mr-5">
      <br />
      <br />
      <br />Ground
    </button>
    <button class="btn btn-water btn-circle btn-xl mb-5 mr-5">
      <br />
      <br />
      <br />Water
    </button>
    <button class="btn btn-sky btn-circle btn-xl mb-5">
      <br />
      <br />
      <br />Sky
    </button>
    <div
      id="carouselExampleControls"
      class="carousel slide"
      style="margin: auto;"
      data-ride="carousel"
    >
      <div class="carousel-inner">
        <div class="carousel-item active">
          <img
            src="https://t2.daumcdn.net/thumb/R720x0/?fname=http://t1.daumcdn.net/brunch/service/user/2OhT/image/O8cC0T4PchZkbqSc09Fvv8kVHOc.jpg"
            class="d-block w-100"
            alt="paragliding"
          />
        </div>
        <div class="carousel-item">
          <img
            src="https://img1.daumcdn.net/thumb/R800x0/?scode=mtistory2&fname=https%3A%2F%2Ft1.daumcdn.net%2Fcfile%2Ftistory%2F997560335A1C150A27"
            class="d-block w-100"
            alt="snowboarding"
          />
        </div>
        <div class="carousel-item">
          <img
            src="https://img1.daumcdn.net/thumb/R800x0/?scode=mtistory2&fname=https%3A%2F%2Ft1.daumcdn.net%2Fcfile%2Ftistory%2F997560335A1C150A27"
            class="d-block w-100"
            alt="wingsuit"
          />
        </div>
      </div>
      <a
        class="carousel-control-prev"
        href="#carouselExampleControls"
        role="button"
        data-slide="prev"
      >
        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
        <span class="sr-only">Previous</span>
      </a>
      <a
        class="carousel-control-next"
        href="#carouselExampleControls"
        role="button"
        data-slide="next"
      >
        <span class="carousel-control-next-icon" aria-hidden="true"></span>
        <span class="sr-only">Next</span>
      </a>
    </div>
    <p class="hot-item mt-3">HOT ITEM</p>
    <div class="row justify-contetn-left">
    <div class="card col-12 col-sm-12 col-md-3" v-for="(post, index) in posts" :key="index" style="width: 18rem;">
      <div v-if="index < 4">
      <img :src="post.imgurl" class="card-img-top" style="height:13rem;" />
      <div class="card-body">
        <p
          class="card-text" style="color: black;"
        >{{post.likecnt}}</p>
      </div>
      </div>
    </div>
    </div>
  </div>
</template>
 
<script>
import axios from "axios";
import "../../assets/css/list.css";

const baseURL = "http://localhost:8080";

export default {
  name: "Post",
  components: {},
  created() {
    this.init();
  },
  watch: {},

  methods: {
    init() {
      axios
        .get(`${baseURL}/post/list/`)
        .then((res) => {
          this.posts = res.data;
          console.log(res.data);
        })
        .catch((err) => {
          console.log(err);
        });
    },
  },
  data: () => {
    return {
      email: "",
      password: "",
      itemcount: 0,
      posts:{
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
    };
  },
};
</script>

