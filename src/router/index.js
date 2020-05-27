import Vue from 'vue'
import Router from 'vue-router'
import List from "../components/topic/List"
import Tags from "../components/tag/Tags"
import Comment from '../components/comment/Comment'
import Reply from '../components/reply/Reply'
import News from '../components/news/News'

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
    },
    {
      path: '/news',
      component: News
    },
    {
      path: '/tags',
      component: Tags
    }
  ]
})
