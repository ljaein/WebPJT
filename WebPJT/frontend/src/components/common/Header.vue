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
            <button v-if="!this.$cookies.isKey('Auth-Token')" type="button" class="login-btn" data-toggle="modal" data-target="#exampleModal">
                로그인
            </button>
            <button v-if="this.$cookies.isKey('Auth-Token')" @click="logout" type="button" class="login-btn">
                로그아웃
            </button>
            <button v-if="this.$cookies.isKey('Auth-Token')" @click="info" type="button" class="login-btn">
                회원정보
            </button>

        </div> 
    </div>
    
</template>   

<script> 
    import constants from '../../lib/constants'

    import axios from 'axios'
    const baseURL = "http://localhost:8080/account"

    export default {
        name: 'Header',
        components: {
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
                this.$router.push('/user/info/')
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