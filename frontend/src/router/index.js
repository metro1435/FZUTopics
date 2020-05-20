//这个地方的话是配置所有的路由信息
import VueRouter from 'vue-router'
import Vue from 'vue'
import Home from '../components/Home'
import Course from '../components/Course'
import News from '../components/News'
import Topic from '../components/Topic'
import NewArticle from '../components/NewsArticle'
import CourseContent from '../components/CourseContent'
import Login from '../components/Login'
import TopicAdd from '../components/TopicAdd'
Vue.use(VueRouter)
const routes=[
    {
        path:'',
        redirect:'/login'
    },
    {
        path:'/home',
        name:'home',
        component:Home
    },
    {
        path:'/course',
        component:Course
    },
    {
        path:'/news',
        component:News
    },
    {
        path:'/topic',
        name:'topic',
        component:Topic
    },
    {
        path:'/newsArticle',
        name:'newsArticle',
        component:NewArticle
    },
    {
        path:'/courseContent',
        name:'courseContent',
        component:CourseContent
    },
    {
        path:'/login',
        component:Login
    },
    {
        path:'/topicAdd',
        name:'topicAdd',
        component:TopicAdd
    }
]
const router=new VueRouter({
    routes,
    mode:'history'
})
export default router