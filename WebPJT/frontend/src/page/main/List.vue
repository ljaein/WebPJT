<template>
  <div>
    <div id="carouselExampleControls" class="carousel slide" data-ride="carousel">
      <div class="carousel-inner">
        <div class="carousel-item active">
          <img src="https://t2.daumcdn.net/thumb/R720x0/?fname=http://t1.daumcdn.net/brunch/service/user/2OhT/image/O8cC0T4PchZkbqSc09Fvv8kVHOc.jpg" class="d-block w-100" alt="paragliding">
        </div>
        <div class="carousel-item">
          <img src="https://img1.daumcdn.net/thumb/R800x0/?scode=mtistory2&fname=https%3A%2F%2Ft1.daumcdn.net%2Fcfile%2Ftistory%2F997560335A1C150A27" class="d-block w-100" alt="snowboarding">
        </div>
        <div class="carousel-item">
          <img src="https://lh3.googleusercontent.com/proxy/gkQKSCqih08jNeQrDx-D347lJyC1eC_ltW7Ex4DqBARyXAzoYkmRiWIS1pmzKauFXgQPEoGeoNqbXmqk1QXN6Twb0QWSf0vl8zutIimTaK-rsgwWLs8SCI4Bi4RwposDzw9PGJ5YUILkcyZoYLMRWI0vWTYRiDQXca_sC7RmhB9_OOH1KfO0E9Cl5tVdUg" class="d-block w-100" alt="wingsuit">
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
    
    <div>
  </div>


  </div>

  
</template>
 
<script>
import PV from "password-validator";
import * as EmailValidator from "email-validator";
import axios from "axios";

const baseURL = "http://localhost:8080/account";


export default {
  name: "Post",
  components: {
    // FontAwesomeIcon
  },
  created() {
    this.passwordSchema
      .is()
      .min(8)
      .is()
      .max(100)
      .has()
      .digits()
      .has()
      .letters();
  },
  watch: {
    password: function(v) {
      this.checkForm();
    },
    email: function(v) {
      this.checkForm();
    }
  },

  methods: {
    checkForm() {
      if (
        this.email.length > 0 &&
        !EmailValidator.validate(this.email)
      )
        this.error.email = "이메일 형식이 아닙니다.";
      else this.error.email = false;

      if (
        this.password.length > 0 &&
        !this.passwordSchema.validate(this.password)
      )
        this.error.password = "영문,숫자 포함 8 자리이상이어야 합니다.";
      else this.error.password = false;
    },
    
  },
  data: () => {
    return {
      email: "",
      password: "",
      passwordSchema: new PV(),
      error: {
        email: false,
        password: false
      },
      passwordType: "password",
      itemcount : 0,
      faMapMarkerAlt,
      faQuestionCircle,
      faUser,
      faHeart,
      faShoppingBag
    };
  }
};
</script>