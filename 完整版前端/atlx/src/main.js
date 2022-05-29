import Vue from 'vue'
import router from './router'
import App from './App'
import store from './store'
import Element from 'element-ui'
import "element-ui/lib/theme-chalk/index.css"
import qs from 'qs'
import gobal from "./assets/js/globalFun"
Vue.prototype.$qs = qs

Vue.use(Element)
import axios from './assets/js/axios'
Vue.prototype.$axios = axios //

Vue.config.productionTip = false

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
