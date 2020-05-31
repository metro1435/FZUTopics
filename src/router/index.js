import Vue from 'vue'
import Router from 'vue-router'
import List from "../components/topic/List"
import Tags from "../components/tag/Tags"
import Comment from '../components/comment/Comment'
import Reply from '../components/reply/Reply'
import News from '../components/news/News'
import Ctcomment from '../components/ctcomment/Ctcomment'
import Course from '../components/course/Course'
import AdminLogin from "../components/adminlogin/AdminLogin"

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      redirect: '/adminlogin'
    },
    {
      path: '/adminlogin',
      component: AdminLogin
    },
    {
      path: '/topics',
      component: List
    },
    {
      path: '/comments',
      component: Comment
    },
    {
      path: '/ctcomments',
      component: Ctcomment
    },
    {
      path: '/replys',
      component: Reply
    },
    {
      path: '/news',
      component: News
    },
    {
      path: '/courses',
      component: Course
    },
    {
      path: '/tags',
      component: Tags
    }
  ]
})
