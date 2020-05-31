import Vue from 'vue'
import Router from 'vue-router'
import Tags from '../components/tag/Tags'
import List from "../components/topic/List"
import Comments from '../components/comment/Comment'
import Reply from '../components/reply/Reply'
import News from '../components/news/News'
import Ctcomment from '../components/ctcomment/Ctcomment'
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
      component: AdminLogin,
      name:'AdminLogin'
    },
    {
      path: '/comments',
      component: Comments,
      name:'Comments'
    },
    {
      path: '/ctcomments',
      component: Ctcomment,
      name:'Ctcomment'
    },
    {
      path: '/replys',
      component: Reply,
      name:'Reply'
    },
    {
      path: '/news',
      component: News,
      name:'News'
    },
    {
      path: '/tags',
      component: Tags,
      name:'Tags'
    },
  ]
})
