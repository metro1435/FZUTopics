import Vue from 'vue'
import Router from 'vue-router'

import List from "../components/topic/List"
import Course from "../components/course/Course"
import AdminLogin from "../components/adminlogin/AdminLogin"
Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      redirect: '/adminlogin'

    },
    {
      path: '/topics',
      component: List,
      name:'TopicList'
    },
    {
      path: '/course',
      component: Course,
      name: 'Course'
    },
    {
      path: '/adminlogin',
      component: AdminLogin
    },
  ]
})
