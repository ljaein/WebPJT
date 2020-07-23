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
    <!-- <div class="column" style="width: 100%;">
        <button class="btn bg-dark text-white" style="width:25%">상세 정보</button>
        <button class="btn bg-dark text-white" style="width:25%">후기</button>
        <button class="btn bg-dark text-white" style="width:25%">업체 정보</button>
        <button class="btn bg-dark text-white" style="width:25%">Q&A</button>
    </div>-->
    <div class="column" style="width: 100%;">
      <hr />
      <h5>Comments</h5>
      <div class="list-group">
        <p class="list-group-item list-group-item-action">댓글</p>
      </div>
      <div class="input-group mb-3">
        <input
          type="text"
          class="form-control"
          placeholder="리뷰 작성해주세요."
          aria-label="Recipient's username"
          aria-describedby="button-addon2"
        />
        <div class="input-group-append">
          <button
            class="btn btn-outline-secondary bg-dark text-white"
            type="button"
            id="button-addon2"
          >작성</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
const baseURL = "http://localhost:8080/post";

export default {
  data(){
    return{
      post:[],
      // pid:"",
      // email:"",
      // title:"",
      // location:"",
      // imgurl:"",
      // price:"",
      // sdate:"",
      // edate:"",
      // companyInfo:"",
      // detail:"",
      // activity:""
    }

  },
  methods: {
    goinfo() {
      this.$router.go();
    },
    getPost() {
      axios
        .get(`${baseURL}/detail/${this.$route.params.ID}`)
        .then(res => {
          // this.email = res.data.email;
          // this.title =res.data.title;
          // this.location =res.data.location;
          // this.imgurl = res.data.imgurl;
          // this.price = res.data.price;
          // this.sdate = res.data.sdate;
          // this.edate = res.data.edate;
          // this.companyInfo = res.data.companyInfo;
          // this.detail = res.data.detail;
          // this.activity = res.data.activity;
          this.post = res.data;
        })
        .catch(err => {
          console.log(err);
        });
    }
  },
  created() {
    this.getPost();
  }
};
</script>

<style>
</style>