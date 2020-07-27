import Vue from 'vue'
import App from './App.vue'
import router from './router'
import VueCookies from 'vue-cookies'
import VueBootstrapToasts from 'vue-bootstrap-toasts'
import Toasted from 'vue-toasted'

Vue.use(Toasted)

Vue.use(VueBootstrapToasts);

Vue.use(VueCookies)
    /* eslint-disable no-new */
new Vue({
    el: '#app',
    router,
    components: { App },
    template: '<App/>'
})