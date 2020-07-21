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
    // 포스트
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
  ]
})
