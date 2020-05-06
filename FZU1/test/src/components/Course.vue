<template>
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
      <input placeholder="css搜索框代码测试" name="cname" type="text" />
      <button type="submit"></button>
    </div>
    <div class="home">
      <article class="post" v-for="(value,index) in course" :key="value">
        <header class="post-header" v-if="judgeInput(index)">
          <h1 class="post-title">
            <router-link to="/CourseContent">{{getCourseName(index)}}</router-link>
            <!--<a class="post-link" href="#">{{getCourseName(index)}}</a>-->
          </h1>
          <time class="post-time">{{getTeacherName(index)}}</time>
        </header>
        <hr />
      </article>
      <nav class="pagination">
        <a class="next" href="#" v-if="backward" @click="hasBack()">
          <span class="prev-text">Next</span>
          <i class="iconfont icon-left"></i>
        </a>
        <a class="prev" href="#" v-if="forward" @click="hasForward()">
          <span class="prev-text">prev</span>
          <i class="iconfont icon-left"></i>
        </a>
      </nav>
    </div>
  </div>
</template>

<script>
export default {
  name: "Course",
  data() {
    return {
      show: 0,
      courseQuery: false,
      teacherQuery: false,
      judgeShouw: false,
      course: [
        {
          courseName: "软件工程",
          courseId: "c010101",
          teacherId: "h010101",
          teacherName: "汪你妹",
          info: "",
          tel: "",
          QQ: "",
          college: "数学与计算机科学学院",
          credits: "2"
        },
        {
          courseName: "软件工程",
          courseId: "c010101",
          teacherId: "h010101",
          teacherName: "汪你妹",
          info: "",
          tel: "",
          QQ: "",
          college: "数学与计算机科学学院",
          credits: "2"
        },
        {
          courseName: "软件工程",
          courseId: "c010101",
          teacherId: "h010101",
          teacherName: "汪你妹",
          info: "",
          tel: "",
          QQ: "",
          college: "数学与计算机科学学院",
          credits: "2"
        },
        {
          courseName: "软件工程",
          courseId: "c010101",
          teacherId: "h010101",
          teacherName: "汪你妹",
          info: "",
          tel: "",
          QQ: "",
          college: "数学与计算机科学学院",
          credits: "2"
        },
        {
          courseName: "软件工程",
          courseId: "c010101",
          teacherId: "h010101",
          teacherName: "汪你妹",
          info: "",
          tel: "",
          QQ: "",
          college: "数学与计算机科学学院",
          credits: "2"
        },
        {
          courseName: "软件工程",
          courseId: "c010101",
          teacherId: "h010101",
          teacherName: "汪你妹",
          info: "",
          tel: "",
          QQ: "",
          college: "数学与计算机科学学院",
          credits: "2"
        },
        {
          courseName: "软件工程",
          courseId: "c010101",
          teacherId: "h010101",
          teacherName: "汪你妹",
          info: "",
          tel: "",
          QQ: "",
          college: "数学与计算机科学学院",
          credits: "2"
        },
        {
          courseName: "软件工程",
          courseId: "c010101",
          teacherId: "h010101",
          teacherName: "汪你妹",
          info: "",
          tel: "",
          QQ: "",
          college: "数学与计算机科学学院",
          credits: "2"
        },
        {
          courseName: "软件工程",
          courseId: "c010101",
          teacherId: "h010101",
          teacherName: "汪你妹",
          info: "",
          tel: "",
          QQ: "",
          college: "数学与计算机科学学院",
          credits: "2"
        },
        {
          courseName: "软件工程",
          courseId: "c010101",
          teacherId: "h010101",
          teacherName: "汪你妹",
          info: "",
          tel: "",
          QQ: "",
          college: "数学与计算机科学学院",
          credits: "2"
        },
        {
          courseName: "软件工程",
          courseId: "c010101",
          teacherId: "h010101",
          teacherName: "汪你妹",
          info: "",
          tel: "",
          QQ: "",
          college: "数学与计算机科学学院",
          credits: "2"
        }
      ],
      forward: 0,
      backward: 0,
      browse: 0,
      turnPage: false
    };
  },
  methods: {
    setJudgeShow() {
      this.judgeShouw = !this.judgeShouw;
    },
    setCourseQuery() {
      console.log(1);
      this.courseQuery = true;
      this.teacherQuery = false;
    },
    setTeacherQuery() {
      console.log(2);
      this.teacherQuery = true;
      this.courseQuery = false;
    },
    judgeNext() {
      //判断是否内容超过十条
      if (this.course.length - this.browse > 10) {
        this.backward = true;
      } else {
        this.backward = false;
      }
    },
    hasBack() {
      this.forward = true;
      this.browse += 10;
      this.judgeNext();
    },
    hasForward() {
      this.browse -= 10;
      if (this.browse == 0) {
        this.forward = false;
      }
      this.backward = true;
    },
    getCourseName(index) {
      return this.course[index + this.browse].courseName;
    },
    getTeacherName(index) {
      return this.course[index + this.browse].teacherName;
    },
    judgeInput(index) {
      if (index + this.browse < this.course.length) {
        return true;
      }
      return false;
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
    }
  },
  computed: {
  },
  mounted() {
    this.judgeNext();
  }
};
</script>
<style scoped src="../style/search2.css">
</style>
<style scoped src="../style/courseList.css">
</style>