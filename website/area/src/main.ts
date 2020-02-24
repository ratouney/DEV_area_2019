import Vue from 'vue'
import App from './App.vue'
import './registerServiceWorker'
import router from './router'
import store from './store'
import GAuth from 'vue-google-oauth2'

const gauthOption = {
  clientId: '87061903784-oplbf08ai2kgc6gb8v4lolhual8go4o0.apps.googleusercontent.com',
}
Vue.use(GAuth, gauthOption)
Vue.config.productionTip = false

Vue.mixin({
  data: function() {
    return {
      googleAccessToken:'at'
    }
  }
})
new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
