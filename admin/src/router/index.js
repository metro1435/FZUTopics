import Vue from 'vue'
import Router from 'vue-router'
import List from "../components/topic/List"
import Course from "../components/course/Course"
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
      path: '/course',
      component: Course
    },
  ]
})
