<template>

    <div class="modal fade" id="LoginModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
        <div class="modal-header">
            <h5 class="modal-title" id="exampleModalLabel">로그인</h5>
            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">&times;</span>
            </button>
        </div>
        <div class="modal-body">
            E-mail : <input type="text" v-model="LogInData.email" id="email" placeholder="이메일을 입력해주세요."><br>
            Password : <input type="password" v-model="LogInData.password" id="password" placeholder="비밀번호를 입력해주세요.">
        </div>
        <div class="modal-footer">
            <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
            <button type="button" class="btn btn-primary" @click="login" data-dismiss="modal" >로그인 하기</button>
            <button type="button" class="btn btn-primary" @click="join" data-dismiss="modal" >회원 가입 하기</button>
        </div>
        </div>
    </div>
    </div>
</template>

<script>

import axios from 'axios'

const baseURL = "http://localhost:8080/account"

export default {
        data: () => {
        return {
            LogInData: {
                eamil:"",
                password:"",
            },
        }
    },
    props:{
        LoginModal:'',
    },
    methods: {
        login() {
        axios
            .get(`${baseURL}/login/${this.LogInData.email}/${this.LogInData.password}`, {
            params: {
                email: this.LogInData.email,
                password: this.LogInData.password
            }
            })
            .then(response => {
            if (response.status == 200) {
                var jwt = require("jsonwebtoken");
                var token = jwt.sign({ sub: this.LogInData.email }, this.LogInData.password);
                this.$cookies.set('Auth-Token', token);
                this.$cookies.set('User-Email',this.email);
                this.$router.push("/");
                this.$router.go();
                console.log(response.data);
            }
            })
            .catch(err => {
            console.log(err.response);
            alert("아이디 및 비밀번호를 확인해주세요.");
            this.LogInData.email = "";
            this.LogInData.password = "";
            });
        },
        join: function() {
            this.$router.push('/user/join/')
        },
        
    },
    created() {

    },
}

</script>

<style>

</style>