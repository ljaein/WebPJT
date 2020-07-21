<template>
    <div id="header" v-if="isHeader">
        <h1>
            <router-link v-bind:to="{name:constants.URL_TYPE.POST.MAIN}">
                SS_log
            </router-link>
        </h1>
        <div class="right">
            <div class="search-input">
                <i class="fas fa-search"></i>
                <input v-model="keyword" type="text"/>
            </div>
            <!-- <router-link v-bind:to="{name:constants.URL_TYPE.USER.LOGIN}" class="login-btn">
                로그인
            </router-link>   -->
            <button v-if="!this.$cookies.isKey('Auth-Token')" type="button" class="login-btn" data-toggle="modal" data-target="#LoginModal">
                로그인
            </button>
            <button v-if="this.$cookies.isKey('Auth-Token')" @click="logout" type="button" class="login-btn">
                로그아웃
            </button>
            <button v-if="this.$cookies.isKey('Auth-Token')" @click="info" type="button" class="login-btn">
                회원정보
            </button>


            <!-- Modal -->
            <!-- <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">로그인</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <div class="input-wrap">
                        E-mail : <input type="text" v-model="LogInData.email" id="email" placeholder="이메일을 입력해주세요."><br>
                    </div>
                    <div class="input-wrap">
                        Password : <input type="password" v-model="LogInData.password" id="password" placeholder="비밀번호를 입력해주세요.">
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                    <button type="button" class="btn btn-primary" @click="login" data-dismiss="modal" >로그인 하기</button>
                    <button type="button" class="btn btn-primary" @click="join" data-dismiss="modal" >회원 가입 하기</button>
                </div>
                </div>
            </div>
            </div> -->


        </div> 
    </div>
    
</template>   

<script> 
    import constants from '../../lib/constants'

    // import ModalLogin from './ModalLogin.vue'

    import axios from 'axios'
    const baseURL = "http://localhost:8080/account"

    export default {
        name: 'Header',
        components: {
            // ModalLogin,
        },
        // props: ['isHeader'],
        props: {
            isHeader: Boolean,
        },
        computed:{
        },
        watch: {
        },
        created() {
        },
        methods : {
            logout: function() {
                alert('로그아웃 되었습니다.')
                this.$cookies.remove("Auth-Token")
                this.$cookies.remove("User")
                this.$cookies.remove("imgurl")
                this.$router.push('/')
                this.$router.go()
            },
            info: function() {
                this.$router.push('/user/info/').catch(()=>{});
            },
        },
        data: function() {
           return {
               constants,
               keyword : "",
            //    isLogged: false,
            //     LogInData: {
            //         eamil:"",
            //         password:"",
            //     },
            //     useremail: "",
           }
        },

    }
</script>