<template>
  <div class="container">
    <table class="table" >
      <tr>
        <td>제목</td>
        <td>작성자</td>
        <td>작성일시</td>
        </tr>
      <tr v-for="(temp, index) in temps" :key="index">
        <td>
          <router-link v-if="temp.pid"
            v-bind:to="{name:'PostTempDetail',params:{ID:temp.pid}}"
            class="btn--text"
          >{{temp.title}}</router-link>
        </td>
        <td>{{email}}</td>
        <td>{{temp.createDate}}</td>
      </tr>
    </table>
  </div>
</template>


<script>
import "../../assets/css/postlist.css";
import axios from "axios";

const baseURL = "http://localhost:8080/temp";

export default {
  data() {
    return {
      temps: {
        pid: "",
        email: "",
        activity: "",
        title: "",
        location: "",
        imgurl: "",
        price: "",
        sdate: "",
        edate: "",
        likecnt: "",
        createDate: "",
      },
      email: "",
    };
  },
  methods: {
    init() {
      axios
        .get(`${baseURL}/list/${this.email}`)
        .then((res) => {
          this.temps = res.data;
        })
        .catch((err) => {
          console.log(err);
        });
    },
  },
  created() {
    this.email = this.$cookies.get("User");
    this.init();
  },
};
</script>