import Vue from 'vue'
import Router from 'vue-router'

import constants from '../lib/constants'

// user
import Login from '../page/user/Login.vue'
import Join from '../page/user/Join.vue'
import Info from '../page/user/Info.vue'

// error
import NotFound from '../page/error/NotFound.vue'
import Params from '../page/error/Params.vue'
import Msg from '../page/msg/msg.vue'

// main
import List from '../page/main/List.vue'

// post
import PostList from '../page/post/PostList.vue'
import PostListDetailView from '../page/post/PostListDetail.vue'
import PostCreate from '../page/post/PostCreate.vue'
import PostUpdate from '../page/post/PostUpdate.vue'

// activity
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
            name: 'PostList',
            component: PostList,
        }, 
        {
            path: '/posts/:ID',
            name: 'PostListDetailView',
            component: PostListDetailView,
        },

        //계절
        // {
        //     path: '/spring',
        //     name: 'Spring',
        //     component: Spring,

        // },
        // {
        //     path: '/postcreate',
        //     name: 'PostCreate',
        //     component: PostCreate,
        // },
        // {
        //     path: '/postupdate',
        //     name: 'PostUpdate',
        //     component: PostUpdate,
        // },

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

        // kakao share
        {
            path: '/msg/msg',
            component: Msg,
          },

    ]
})