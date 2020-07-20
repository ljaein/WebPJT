<template>
  <div class="post">

    <!-- {{this.$cookies.get("User").name}} -->

    <div class="wrapB">
      <h2>전체글</h2>

      <section class="post-list">
        <div>
          <div class="post-card">
            <a>
              <div
                :style="{backgroundImage:'url(https://www.ipcc.ch/site/assets/uploads/sites/3/2019/10/img-placeholder.png)'}"
                class="post-img"
              />

              <div class="contents">
                <h3>제목</h3>
                <p class="content">컨텐츠</p>
                <span class="date">2020-06-19ㆍ</span>
                <span class="comment">댓글 0개</span>
              </div>
            </a>

            <div class="writer-wrap">
              <a>닉네임</a>
              <span>♥ 2</span>
            </div>
          </div>
        </div>
        <div>
          <div class="post-card">
            <a>
              <div
                :style="{backgroundImage:'url(https://www.ipcc.ch/site/assets/uploads/sites/3/2019/10/img-placeholder.png)'}"
                class="post-img"
              />

              <div class="contents">
                <h3>제목</h3>
                <p class="content">컨텐츠</p>
                <span class="date">2020-06-19ㆍ</span>
                <span class="comment">댓글 0개</span>
              </div>
            </a>

            <div class="writer-wrap">
              <a>닉네임</a>
              <span>♥ 2</span>
            </div>
          </div>
        </div>
        <div>
          <div class="post-card">
            <a>
              <div
                :style="{backgroundImage:'url(https://www.ipcc.ch/site/assets/uploads/sites/3/2019/10/img-placeholder.png)'}"
                class="post-img"
              />

              <div class="contents">
                <h3>제목</h3>
                <p class="content">컨텐츠</p>
                <span class="date">2020-06-19ㆍ</span>
                <span class="comment">댓글 0개</span>
              </div>
            </a>

            <div class="writer-wrap">
              <a>닉네임</a>
              <span>♥ 2</span>
            </div>
          </div>
        </div>
        <div>
          <div class="post-card">
            <a>
              <div
                :style="{backgroundImage:'url(https://www.ipcc.ch/site/assets/uploads/sites/3/2019/10/img-placeholder.png)'}"
                class="post-img"
              />

              <div class="contents">
                <h3>제목</h3>
                <p class="content">컨텐츠</p>
                <span class="date">2020-06-19ㆍ</span>
                <span class="comment">댓글 0개</span>
              </div>
            </a>

            <div class="writer-wrap">
              <a>닉네임</a>
              <span>♥ 2</span>
            </div>
          </div>
        </div>
        <div>
          <div class="post-card">
            <a>
              <div
                :style="{backgroundImage:'url(https://www.ipcc.ch/site/assets/uploads/sites/3/2019/10/img-placeholder.png)'}"
                class="post-img"
              />

              <div class="contents">
                <h3>제목</h3>
                <p class="content">컨텐츠</p>
                <span class="date">2020-06-19ㆍ</span>
                <span class="comment">댓글 0개</span>
              </div>
            </a>

            <div class="writer-wrap">
              <a>닉네임</a>
              <span>♥ 2</span>
            </div>
          </div>
        </div>
        <div>
          <div class="post-card">
            <a>
              <div
                :style="{backgroundImage:'url(https://www.ipcc.ch/site/assets/uploads/sites/3/2019/10/img-placeholder.png)'}"
                class="post-img"
              />

              <div class="contents">
                <h3>제목</h3>
                <p class="content">컨텐츠</p>
                <span class="date">2020-06-19ㆍ</span>
                <span class="comment">댓글 0개</span>
              </div>
            </a>

            <div class="writer-wrap">
              <a>닉네임</a>
              <span>♥ 2</span>
            </div>
          </div>
        </div>
      </section>

      <div class="tag-list-wrap">
        <h4>인기태그</h4>
        <ul class="tag-list">
          <li>#태그1 (8)</li>
          <li>#태그2 (5)</li>
          <li>#태그3 (2)</li>
        </ul>
      </div>
    </div>
  </div>
</template>
 
<script>
import "../../assets/css/post.scss";
import "../../assets/css/user.scss";
import PV from "password-validator";
import * as EmailValidator from "email-validator";
import axios from "axios";

const baseURL = "http://localhost:8080/account";

export default {
  name: "Post",
  components: {},
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
    login() {
      axios
        .get(
          `${baseURL}/login/${this.email}/${this.password}`,
          {
            params: {
              email: this.email,
              password: this.password
            }
          }
        )
        .then(response => {
          if (response.status == 200) {
            var jwt = require("jsonwebtoken");
            var token = jwt.sign(
              { sub: this.email },
              this.password
            );
            this.$cookies.set("Auth-Token", token);
            this.$cookies.set("User", response.data);
            console.log(response.data);
            this.name = response.data.name;
            this.$router.push("/");
            this.$router.go();
          }
        })
        .catch(err => {
          console.log(err.response);
          alert("아이디 및 비밀번호를 확인해주세요.");
          this.email = "";
          this.password = "";
        });
    },
    join: function() {
      this.$router.push("/user/join/");
    }
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
      passwordType: "password"
    };
  }
};
</script>