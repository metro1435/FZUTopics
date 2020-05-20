<template>
<div>
   <Head></Head>
  <div class="addTopic-main" id="addTopic">
    <div class="name">
      <label for="topicName">标题</label>
      <input type="text" id="topicName" size="90" v-model="title" />
    </div>
    <div class="content">
      <label for="topicContent">正文</label>
      <textarea id="topicContent" v-model="text"></textarea>
    </div>
    <div class="addedTag">
      <div v-for="(item,m) in saveTags" :key="item+m">
        {{item.tagname}}
        <button type="button" @click="deleteTag(m)"></button>
      </div>
    </div>
    <div class="setTag">
      <div class="buttonName" @click="fanJudgeTag()">添加标签</div>
    </div>
    <!--这里的话是为了生成一个遮盖的窗口-->
  <div class="cover" v-if="judgeTag"></div>
    <div class="tags" v-if="judgeTag">
      <ul>
        <li v-for="(item,m) in tags" @click="addTag(m)" :key="item+m">{{tags[m].tagname}}</li>
      </ul>
      <button type="button" @click="fanJudgeTag()">添加完成</button>
    </div>
    <button class="topic-submit" @click="submit()">提交</button>
  </div>
  </div>
</template>

<script>
import Head from '../components/Head';
import { request } from "../network/request";
export default {
  name: "AddTopic",
  components:{
    Head,
  },
  data() {
    return {
      userId: "",
      title: "",
      text: "",
      times: "",
      times1:"",
      judgeTag: false,
      tags: [],
      saveTags: []
    };
  },
  methods: {
    getTag() {
      request({
        url: "/allTag",
        methods: "get",
        headers: {
          token: this.$store.state.token
        }
      })
        .then(res => {
          this.tags = [];
          for (let i = 0; i < res.data.data.length; i++) {
            this.tags.push({
              tagname:res.data.data[i].name,
              tagid:res.data.data[i].tagid
            });
          }
        })
        .catch(err => {
          console.log(err);
        });
    },
    submitTopic() {
      request({
        url: "/user/topic",
        method: "post",
        headers: {
          token: this.$store.state.token
        },
        data: {
          topicid: "t" + this.userId+this.times1,
          title: this.title,
          text: this.text,
          time: this.times,
          isanony: "0",
          auditstatus: "1"
        }
      })
        .then(res => {
          console.log(res);
        })
        .catch(err => {
          console.log(err);
        });
    },
    submitTag() {
      let a = [];
      for(let i=0;i<this.saveTags.length;i++){
        a.push({
          tagid:this.saveTags[i].tagid,
          topicid:"t"+this.userId+this.times1,
        })
      }
      request({
        url: "/topic/tag",
        method: "post",
        headers: {
          token: this.$store.state.token
        },
        data: a
      })
        .then(res => {
          console.log(res);
        })
        .catch(err => {
          console.log(err);
        });
    },
    submit() {
      this.submitTopic();
      this.submitTag();
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
          //console.log(res);
        })
        .catch(err => {
          console.log(err);
        });
      return this.userId;
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
    fanJudgeTag() {
      console.log(123);
      this.judgeTag = !this.judgeTag;
    },
    addTag(index) {
      let t = true;
      for (let i = 0; i < this.saveTags.length; i++) {
        if (this.saveTags[i].tagid == this.tags[index].tagid) {
          t = false;
          break;
        }
      }
      if (t == true) {
        this.saveTags.push(this.tags[index]);
      }
    },
    deleteTag(index) {
      this.saveTags.splice(index, 1);
    }
  },
  mounted() {
    this.getUserId();
    this.times = this.getTime();
    this.times1=this.getTime1();
    this.getTag();
  }
};
</script>

<style scoped src="../style/topicAdd.css">
</style>