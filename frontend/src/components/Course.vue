<template>
<div>
  <Head></Head>
  <div class="content">
    <div class="bar">
      <div class="selete">
        <p @click="setJudgeShow()" v-if="hasShow()==0">搜索条件</p>
        <p @click="setJudgeShow()" v-if="hasShow()==1">课程</p>
        <p @click="setJudgeShow()" v-if="hasShow()==2">教师</p>
        <!--还是自己设计一个下拉列表吧-->
        <div
          class="option"
          v-if="judgeShouw&&hasShow()!=1"
          @click="setJudgeShow();setCourseQuery()"
        >课程</div>
        <div
          class="option"
          v-if="judgeShouw&&hasShow()!=2"
          @click="setJudgeShow();setTeacherQuery()"
        >教师</div>
      </div>
      <input placeholder="" name="cname" type="text" v-model="information" />
      <button type="submit" @click="getCourse()"></button>
    </div>

    <div class="home">
      <article class="post" v-for="(value,index) in course" :key="value+index">
        <header class="post-header">
          <h1 class="post-title">
            <a @click="pushCourseId(index)">
              {{course[index].courseName}}
            </a>
            <!--<a class="post-link" href="#">{{getCourseName(index)}}</a>-->
          </h1>
          <time class="post-time">
              {{course[index].teacherName}}
          </time>
        </header>
        <hr />
      </article>
      <nav class="pagination">
        <a class="next" href="#" v-if="back" @click="hasBack()">
          <span class="prev-text">Next</span>
          <i class="iconfont icon-left"></i>
        </a>
        <a class="prev" href="#" v-if="judgePre()" @click="hasForward()">
          <span class="prev-text">prev</span>
          <i class="iconfont icon-left"></i>
        </a>
      </nav>
    </div>
  </div>
  </div>
</template>

<script>
import { request } from "../network/request";
import Head from './Head';
export default {
  name: "Course",
  components:{
    Head,
  },
  data() {
    return {
      information: "课",
      message: [],
      pages: 1,
      pageNum: 1,
      pre: false,
      back: false,
      show: 0,
      courseQuery: false,
      teacherQuery: false,
      judgeShouw: false,
      course: []
    };
  },
  methods: {
    pushCourseId(index){
      this.$router.push({
        name:'courseContent',
        params:{
          courseId:this.course[index].courseId,
          courseName:this.course[index].courseName,
          teacherId:this.course[index].teacherId,
          teacherName:this.course[index].teacherName,
          tel:this.course[index].tel,
          info:this.course[index].info,
          qq:this.course[index].qq,
          college:this.course[index].college,
          credits:this.course[index].credits
        }
      })
    },
    getCourse() {
      request({
        url:
          "/course/" +
          this.information +
          "/sort/" +
          this.show +
          "/page/" +
          this.pageNum,
        method: "get",
        headers: {
          token: this.$store.state.token
        }
      })
        .then(res => {
          console.log(res);
          this.course=[];
          for (let i = 0; i < res.data.data.list.length; i++) {
            this.course.push({
              courseName: res.data.data.list[i].coursename,
              courseId:res.data.data.list[i].courseid,
              teacherName:res.data.data.list[i].teachername,
              teacherId:res.data.data.list[i].teacherid,
              info:res.data.data.list[i].info,
              tel:res.data.data.list[i].tel,
              qq:res.data.data.list[i].qq,
              college:res.data.data.list[i].college,
              credits:res.data.data.list[i].credits,
            });
          }
          this.pages=res.data.data.pages;
          if(this.pageNum<this.pages){
            this.back=true;
          }else{
            this.back=false;
          }
        })
        .catch(err => {
          console.log(err);
        });
    },
    setJudgeShow() {
      this.judgeShouw = !this.judgeShouw;
    },
    setCourseQuery() {
      this.courseQuery = true;
      this.teacherQuery = false;
    },
    setTeacherQuery() {
      this.teacherQuery = true;
      this.courseQuery = false;
    },
    hasShow() {
      if (this.courseQuery == true) {
        this.show = 1;
      } else if (this.teacherQuery == true) {
        this.show = 2;
      } else {
        this.show = 0;
      }
      return this.show;
    },
    hasBack() {
      this.course = [];
      this.pageNum++;
      this.getCourse();
      this.judgeNext();
      this.judgePre();
    },
    hasForward() {
      this.course = [];
      this.pageNum--;
      this.getCourse();
      this.judgeNext();
      this.judgePre();
    },
    judgeNext() {
      if (this.pageNum < this.pages) {
        this.back=true;
        //return true;
      } else {
        this.back=false;
        //return false;
      }
    },
    judgePre() {
      if (this.pageNum == 1) {
        return false;
      } else {
        return true;
      }
    }
  },
  computed: {},
  mounted() {
    //this.judgeNext();
    //this.judgePre();
  }
};
</script>
<style scoped src="../style/search2.css">
</style>
<style scoped src="../style/courseList.css">
</style>