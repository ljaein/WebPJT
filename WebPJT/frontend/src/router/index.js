import Vue from 'vue'
import Router from 'vue-router'

import constants from '../lib/constants'

// 유저
import Login from '../page/user/Login.vue'
import Join from '../page/user/Join.vue'
import Info from '../page/user/Info.vue'

// 포스트
import List from '../page/post/List.vue'
import NotFound from '../page/error/NotFound.vue'
import Params from '../page/error/Params.vue'

// 시즌
import PostListView from '../page/Season/PostListView.vue'
import PostListDetailView from '../page/Season/PostListDetailView.vue'

import Spring from '../page/Season/Spring.vue'
import Summer from '../page/Season/Summer.vue'
import Autumn from '../page/Season/Autumn.vue'
import Winter from '../page/Season/Winter.vue'

// Activity
import Ground from '../page/activity/Ground.vue'
import Water from '../page/activity/Water.vue'
import Sky from '../page/activity/Sky.vue'


Vue.use(Router)

export default new Router({
    routes: [
        // 로그인/가입
        {
            path: '/user/login',
            name: constants.URL_TYPE.USER.LOGIN,
            component: Login
        },
        {
            path: '/user/join',
            name: constants.URL_TYPE.USER.JOIN,
            component: Join
        },
        //회원정보
        {
            path: '/user/info',
            name: constants.URL_TYPE.USER.INFO,
            component: Info
        },
        // 메인
        {
            path: '/',
            name: constants.URL_TYPE.POST.MAIN,
            component: List,
        },
        {
            path: '*',
            component: NotFound,
        },
        {
            path: '/params',
            component: Params,
            name: 'Params',
            props: true
        },

        // 포스트
        {
            path: '/posts',
            name: 'PostListView',
            component: PostListView,
        }, 
        // 포스트 디테일
        {
            path: '/posts/:post_pk',
            name: 'PostListDetailView',
            component: PostListDetailView,
        },

        //계절
        {
            path: '/spring',
            name: 'Spring',
            component: Spring,
        },
        {
            path: '/summer',
            name: 'Summer',
            component: Summer,
        },
        {
            path: '/autumn',
            name: 'Autumn',
            component: Autumn,
        },
        {
            path: '/winter',
            name: 'Winter',
            component: Winter,
        },

        //activity
        {
            path: '/ground',
            name: 'Ground',
            component: Ground,
        },
        {
            path: '/water',
            name: 'Water',
            component: Water,
        },
        {
            path: '/sky',
            name: 'Sky',
            component: Sky,
        },


    ]
})