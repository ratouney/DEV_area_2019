import Vue from 'vue'
import App from './App.vue'
import './registerServiceWorker'
import router from './router'
import store from './store'
import GAuth from 'vue-google-oauth2'
import Spotify from 'spotify-web-api-node'
import VueSpotify from 'vue-spotify'


Vue.use(VueSpotify, new Spotify())
/**
* You should first need to place these 2 lines of code in your APP ENTRY file, e.g. src/main.js
*
* import GAuth from 'vue-google-oauth2'
* Vue.use(GAuth, {clientId: '4584XXXXXXXX-2gqknkvdjfkdfkvb8uja2k65sldsms7qo9.apps.googleusercontent.com'})
*
*/
const gauthOption = {
  clientId: '87061903784-oplbf08ai2kgc6gb8v4lolhual8go4o0.apps.googleusercontent.com',
}
Vue.use(GAuth, gauthOption)


Vue.config.productionTip = false

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
