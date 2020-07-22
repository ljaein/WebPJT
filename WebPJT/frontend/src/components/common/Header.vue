<template>
    <div id="header" class="p-0" v-if="isHeader">
        <nav class="navbar navbar-expand-lg navbar-light bg-dark fixed-top" >
            <!-- <a class="navbar-brand" href="#">Navbar</a> -->
            <router-link v-bind:to="{name:constants.URL_TYPE.POST.MAIN}">
                <h5 class="mb-1 text-white">Leisure</h5>
            </router-link>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav mr-auto">
                <!-- <li class="nav-item active">
                    <a class="nav-link" @click="goSpring">Spring <span class="sr-only">(current)</span></a>
                </li> -->
                <!-- <li class="nav-item">
                    <a class="nav-link" href="#">Link</a>
                </li> -->
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle text-white" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    Season
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <a class="dropdown-item" @click="goPost" href="#">Post</a>
                        <a class="dropdown-item" @click="goSpring" href="#">Spring</a>
                        <a class="dropdown-item" @click="goSummer" href="#">Summer</a>
                        <a class="dropdown-item" @click="goAutumn" href="#">Autumn</a>
                        <a class="dropdown-item" @click="goWinter" href="#">Winter</a>
                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item" href="#">Something else here</a>
                    </div>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle text-white" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    Activity
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <a class="dropdown-item" @click="groundAct" href="#">Ground Activity</a>
                        <a class="dropdown-item" @click="waterAct" href="#">Water Activity</a>
                        <a class="dropdown-item" @click="skyAct" href="#">Sky Activity</a>
                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item" href="#">Something else here</a>
                    </div>
                </li>
                </ul>
                <input class="my-2 my-lg-0 mr-2 bg-light" type="text" placeholder="Search" v-bind="keyword">
                <!-- <form class="form-inline my-2 my-lg-0">
                    <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
                    <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
                </form> -->
                <button v-if="!this.$cookies.isKey('Auth-Token')" type="button" class="login-btn btn-primary" data-toggle="modal" data-target="#LoginModal">
                로그인
                </button>
                <button v-if="this.$cookies.isKey('Auth-Token')" @click="logout" type="button" class="login-btn mr-2">
                    로그아웃
                </button>
                <button v-if="this.$cookies.isKey('Auth-Token')" @click="info" type="button" class="login-btn">
                    회원정보
                </button>
            </div>
        </nav>
        <!-- <h1>
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
        </div>  -->
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
            goPost: function() {
                this.$router.push('/posts/')
                this.$router.go();
            },
            goSpring: function() {
                this.$router.push('/spring/')
                this.$router.go();
            },
            goSummer: function() {
                this.$router.push('/summer/')
                this.$router.go();
            },
            goAutumn: function() {
                this.$router.push('/autumn/')
                this.$router.go();
            },
            goWinter: function() {
                this.$router.push('/winter/')
                this.$router.go();
            },
            groundAct: function() {
                this.$router.push('/ground/')
                this.$router.go();
            },
            waterAct: function() {
                this.$router.push('/water/')
                // this.$router.go();
            },
            skyAct: function() {
                this.$router.push('/sky/')
                this.$router.go();
            },
        },
        data: function() {
           return {
               constants,
               keyword : "",
           }
        },

    }
</script>