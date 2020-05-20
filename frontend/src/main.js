import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'

Vue.config.productionTip = false

new Vue({
  render: h => h(App),
  router:router,
  store:store,
}).$mount('#app')


//这个地方的话我有个涂疑惑就是我一定得在这个地方引用么，是不是不一定，我可以在其他地方引用这个数据吧，这个只是在一个js部分的引用