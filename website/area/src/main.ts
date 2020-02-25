import Vue from 'vue'
import App from './App.vue'
import './registerServiceWorker'
import router from './router'
import store from './store'
import GAuth from 'vue-google-oauth2'

Vue.use(GAuth, {clientId: '602804385318-hj4udn9f6rg3u3gb5ds45tiv3amdc5vr.apps.googleusercontent.com'})
Vue.config.productionTip = false

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
