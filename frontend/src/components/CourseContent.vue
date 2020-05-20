<template>
  <div>
     <Head></Head>
    <CourseInformation :course="course"></CourseInformation>
    <div class="col-md-9 rightBox comment">
      <!--<CommentBox v-bind:comment="comment" v-on:change="changCommmer"></CommentBox>-->
      <!--<CommentText
        v-bind:type="type"
        v-bind:name="oldComment"
        v-on:submit="addComment"
        v-on:canel="canelCommit"
      ></CommentText>-->
    </div>
  </div>
</template>

<script>
//import CommentBox from "./CommentBox";
//import CommentText from "./CommentText";
import CourseInformation from "./CourseInformation";
import Head from './Head';
export default {
  name: "CourseContent",
  components: {
    //CommentBox,
    //CommentText,
    Head,
    CourseInformation
  },
  data() {
    return {
      commenter: "session", //评论人
      type: 0, //0为评论作者1为评论别人的评论2为评论别人的别人
      oldComment: null,
      chosedIndex: -1, //被选中的评论的index
      comment: [],
      course: {
        courseName: "软件工程",
        courseId: "c010101",
        //teacherId: "h010101",
        teacherName: "汪你妹",
        info: "",
        tel: "",
        QQ: "",
        college: "数学与计算机科学学院",
        credits: "2"
      }
    };
  },
  methods: {
    getTime() {
      var now = new Date();
      var year = now.getFullYear();
      var month = now.getMonth() + 1;
      var day = now.getDate();
      month.length < 2 ? "0" + month : month;
      day.length < 2 ? "0" + day : day;
      return year + "-" + month + "-" + day;
    },
    addComment(data) {
      if (this.type == 0) {
        this.comment.push({
          name: "session",
          time: this.getTime(),
          content: data,
          reply: []
        });
        //服务器端变
      } else if (this.type == 1) {
        this.comment[this.chosedIndex].reply.push({
          responder: "session",
          reviewers: this.comment[this.chosedIndex].name,
          time: this.getTime(),
          content: data
        });
        this.type = 0;
      }
    },
    changCommmer(name, index) {
      this.oldComment = name;
      this.chosedIndex = index;
      this.type = 1;
    },
    canelCommit() {
      this.type = 0;
    },
  },
  mounted() {
    this.course.courseName = this.$route.params.courseName;
    this.course.courseId=this.$route.params.courseId;
    this.course.teacherName=this.$route.params.teacherName;
    //this.course.teacherId=this.$route.params.teacherId;
    this.course.tel=this.$route.params.tel;
    this.course.qq=this.$route.params.qq;
    this.course.info=this.$route.params.info;
    this.course.credits=this.$route.params.credits;
    this.course.college=this.$route.params.college;
  }
};
</script>

<style scoped src="../style/comment.css">
</style>