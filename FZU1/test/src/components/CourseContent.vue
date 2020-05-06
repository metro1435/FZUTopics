<template>
  <div>
    <CourseInformation :course="course"></CourseInformation>
    <div class="col-md-9 rightBox comment">
      <CommentBox v-bind:comment="comment" v-on:change="changCommmer"></CommentBox>
      <CommentText
        v-bind:type="type"
        v-bind:name="oldComment"
        v-on:submit="addComment"
        v-on:canel="canelCommit"
      ></CommentText>
    </div>
  </div>
</template>

<script>
import CommentBox from "./CommentBox";
import CommentText from "./CommentText";
import CourseInformation from "./CourseInformation";
export default {
  name: "CourseContent",
  components: {
    CommentBox,
    CommentText,
    CourseInformation
  },
  data() {
    return {
      commenter: "session", //评论人
      type: 0, //0为评论作者1为评论别人的评论2为评论别人的别人
      oldComment: null,
      chosedIndex: -1, //被选中的评论的index
      comment: [
        {
          name: "墨黎",
          time: "2019-12-17",
          content: "好,讲得非常好，good",
          reply: [
            {
              responder: "傲娇的",
              reviewers: "墨黎",
              time: "2019-12-17",
              content: "很强"
            },
            {
              responder: "墨黎",
              reviewers: "傲娇的",
              time: "2019-12-17",
              content: "你说得对"
            }
          ]
        },
        {
          name: "Freedom小黄",
          time: "2019-08-17",
          content: "好,讲得非常好，good",
          reply: []
        }
      ],
      course: {
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
    readCount() {
      this.article.read++;
      console.log(this.article.read);
    }
  },
  mounted() {
    this.readCount();
  }
};
</script>

<style scoped src="../style/comment.css">
</style>