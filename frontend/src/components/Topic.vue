<template>
  <div>
    <Head></Head>
    <TopicArticle :article="article"></TopicArticle>
    <div class="col-md-9 rightBox comment">
      <CommentBox :comment="comment" v-on:change="changCommmer"></CommentBox>
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
import TopicArticle from "./TopicArticle";
import CommentBox from "./CommentBox";
import CommentText from "./CommentText";
import { request } from "../network/request";
import Head from '../components/Head';
export default {
  name: "Topic",
  components: {
    TopicArticle,
    CommentBox,
    CommentText,
    Head
  },
  data() {
    return {
      userId: "",
      topicid: "",
      commenter: "session", //评论人
      type: 0, //0为评论作者1为评论别人的评论2为评论别人的别人
      oldComment: null,
      chosedIndex: -1, //被选中的评论的index
      article: {
        title: "",
        time: "",
        content: ""
      },
      comment: []
    };
  },
  //那其实是我在这个地方我就需要去使用按个了
  methods: {
    //这个地方的话不应该是这样子歇
    getArticle() {
      request({
        url: "/topic/topicid/" + this.topicid,
        method: "get",
        headers: {
          token: this.$store.state.token
        }
      })
        .then(res => {
          this.article.content =
            "<pre style='word-Wrap:break-word;white-space: pre-wrap;'>" +
            res.data.data[0].text +
            "</pre>";
          this.article.title = res.data.data[0].title;
          this.article.time = res.data.data[0].time;
        })
        .catch(err => {
          console.log(err);
        });
    },
    getComment() {
      request({
        url: "/topic/" + this.topicid + "/comment",
        method: "get",
        headers: {
          token: this.$store.state.token
        }
      })
        .then(res => {
          console.log(res);
          for (let i = 0; i < res.data.data.length; i++) {
            this.comment.push({
              name: res.data.data[i].userName,
              time: res.data.data[i].comment.time,
              content: res.data.data[i].comment.text,
              reply: res.data.data[i].replies,
              commentid:res.data.data[i].comment.commentid
              //那假设我们这个样子就把一个comment给封装完成了是吧，然后其实就是传递过去这个好想就可以了的样子
            });
          }
        })
        .catch(err => {
          console.log(err);
        });
    },
    getTime() {
      var now = new Date();
      var year = now.getFullYear();
      var month = now.getMonth() + 1;
      var day = now.getDate();
      var hour = now.getHours();
      var mm = now.getMinutes();
      var s = now.getSeconds();
      month = month < 10 ? "0" + month : month;
      day = day < 10 ? "0" + day : day;
      return year + "-" + month + "-" + day + " " + hour + ":" + mm + ":" + s;
    },
    getTime1() {
      var now = new Date();
      var year = now.getFullYear();
      var month = now.getMonth() + 1;
      var day = now.getDate();
      var hour = now.getHours();
      var mm = now.getMinutes();
      var s = now.getSeconds();
      month = month < 10 ? "0" + month : month;
      day = day < 10 ? "0" + day : day;
      return ""+year  + month  + day  + hour  + mm  + s;
    },
    addComment(datas) {
      console.log(datas);
      if (this.type == 0) {
        this.comment.push({
          commentid:this.userId+this.getTime1(),
          name: "session",
          time: this.getTime(),
          content: datas,
          reply: []
        });
        request({
          url:"/user/topic/comment",
          method:"post",
          headers:{
            token:this.$store.state.token
          },
          data:{
            commentid:this.userId+this.getTime1(),
            text:datas,
            time:this.getTime(),
            isanony:0,
            topicid:this.topicid,
            isreply:0,
            auditstatus:1
          }
        }).then(res=>{
          console.log(res);
        }).catch(err=>{
          console.log(err);
        })
        //服务器端变
      } else if (this.type == 1) {
        this.comment[this.chosedIndex].reply.push({
          answerer: "session",
          reply:{
            time: this.getTime(),
            text: datas
          }
        });
        this.type = 0;
        request({
          url:'/user/topic/comment/reply',
          method:'post',
          headers:{
            token:this.$store.state.token,
          },
          data:{
            commentid:this.comment[this.chosedIndex].commentid,
            text:datas,
            time:this.getTime(),
            isanony:"0",
            replyid:this.userId+this.getTime1(),
            auditstatus:"1",
          }
        }).then(res=>{
          console.log(res);
        }).then(err=>{
          console.log(err);
        })
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
    getUserId() {
      request({
        url: "/token",
        method: "get",
        headers: {
          token: this.$store.state.token
        }
      })
        .then(res => {
          this.userId = res.data.message;
        })
        .catch(err => {
          console.log(err);
        });
    }
  },
  mounted() {
    this.topicid = this.$route.params.topicid;
    this.getUserId();
    this.getArticle();
    this.getComment();
  }
};
</script>

<style scoped src="../style/comment.css">
</style>