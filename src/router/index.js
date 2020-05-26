import Vue from 'vue'
import Router from 'vue-router'
import List from "../components/topic/List"
import Tags from "../components/tag/Tags"
Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      redirect: '/topics'
    },
    {
      path: '/topics',
      component: List
    },
    {
      path: '/tags',
      component: Tags
    }
  ]
})
