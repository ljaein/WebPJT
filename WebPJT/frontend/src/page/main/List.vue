<template>
  <div class="container">
    <div id="carouselExampleControls" class="carousel slide col-md-9" style="margin: auto;" data-ride="carousel">
      <div class="carousel-inner">
        <div class="carousel-item active">
          <img src="https://t2.daumcdn.net/thumb/R720x0/?fname=http://t1.daumcdn.net/brunch/service/user/2OhT/image/O8cC0T4PchZkbqSc09Fvv8kVHOc.jpg" class="d-block w-100" alt="paragliding">
        </div>
        <div class="carousel-item">
          <img src="https://img1.daumcdn.net/thumb/R800x0/?scode=mtistory2&fname=https%3A%2F%2Ft1.daumcdn.net%2Fcfile%2Ftistory%2F997560335A1C150A27" class="d-block w-100" alt="snowboarding">
        </div>
        <div class="carousel-item">
          <img src="https://img1.daumcdn.net/thumb/R800x0/?scode=mtistory2&fname=https%3A%2F%2Ft1.daumcdn.net%2Fcfile%2Ftistory%2F997560335A1C150A27" class="d-block w-100" alt="wingsuit">
        </div>
      </div>
      <a class="carousel-control-prev" href="#carouselExampleControls" role="button" data-slide="prev">
        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
        <span class="sr-only">Previous</span>
      </a>
      <a class="carousel-control-next" href="#carouselExampleControls" role="button" data-slide="next">
        <span class="carousel-control-next-icon" aria-hidden="true"></span>
        <span class="sr-only">Next</span>
      </a>
    </div>
  </div>

  
</template>
 
<script>
import axios from "axios";

const baseURL = "http://localhost:8080/account";


export default {
  name: "Post",
  components: {
    // FontAwesomeIcon
  },
  created() {
    
  },
  watch: {
    
  },

  methods: {
    
    
  },
  data: () => {
    return {
      email: "",
      password: "",
      itemcount : 0,
      // faMapMarkerAlt,
      // faQuestionCircle,
      // faUser,
      // faHeart,
      // faShoppingBag
    };
  }
};
</script>

