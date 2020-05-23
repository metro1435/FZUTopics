import Vue from 'vue'
import Router from 'vue-router'
import List from "../components/topic/List"
import Comment from '../components/comment/Comment'
import Reply from '../components/reply/Reply'

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
      path: '/comments',
      component: Comment
    },
    {
      path: '/replys',
      component: Reply
    }
  ]
})
