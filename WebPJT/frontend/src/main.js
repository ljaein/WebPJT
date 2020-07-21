import Vue from 'vue'
import App from './App.vue'
import router from './router'
import VueCookies from 'vue-cookies'
import VueSession from 'vue-session'

var sessionOptions = {
  persist: true
}
Vue.use(VueSession, sessionOptions)
Vue.use(VueCookies)
/* eslint-disable no-new */
new Vue({
  el: '#app',
  router, 
  components: { App }, 
  template: '<App/>'
})
 