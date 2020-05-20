<template>
<div>
  <Head></Head>
  <div class="content">
    <div class="home">
      <article class="post" v-for="(value,index) in message" :key="value+index">
        <header class="post-header">
          <h1 class="post-title">
            <a class="post-link" @click="pushTopicId(index)">{{message[index].title}}</a>
          </h1>
          <time class="post-time">{{message[index].time}}</time>
         
        </header>
      </article>

      <nav class="pagination">
        <a class="next" href="#" v-if="judgeNext()" @click="hasBack()">
          <span class="prev-text">Next</span>
          <i class="iconfont icon-left"></i>
        </a>
        <a class="prev" href="#" v-if="judgePre()" @click="hasForward()">
          <span class="prev-text">prev</span>
          <i class="iconfont icon-left"></i>
        </a>
      </nav>
    </div>
    <div class="secondary">
      
      <div class="search">
        <input type="text" name="topic-search" placeholder="查询话题" v-model="queryInformation" />
        <!--昨天晚上是做到这里，然后是想改搜索框的样式，我们目前这个就省略掉线不弄-->
        <div class="index-search-btn">
          <button type="button" class="search-btn" @click="getQueryTopic()">
            <img src="../img/sousuo.png" style="height: 26px;" />
          </button>
        </div>
        <button class="add-btn" @click="addTopic()">+ 添加话题</button>
      </div>
      <section class="widget-area">
        <h3 class="widget-title">Tags</h3>
        <ul class="tag-list">
          <li class="tag-list-item" v-for="(item,m) in tags" :key="item+m">
            <a class="tag-list-link" @click="getTagTopic(m)">{{item.name}}</a>
          </li>
        </ul>
      </section>
    </div>
  </div>
  </div>
</template>

<script>
import { request } from "../network/request";
import Head from '../components/Head';
export default {
  name: "Home",
  components:{
    Head,
  },
  data() {
    return {
      queryInformation: "",
      message: [],
      pages: 1,
      pageNum: 1,
      pre: false,
      back: false,
      query: 0,
      tags: []
    };
  },
  methods: {
    getTag() {
      request({
        url: "/tag",
        method: "get",
        headers: {
          token: this.$store.state.token
        }
      })
        .then(res => {
          console.log(res);
          for (let i = 0; i < res.data.data.length; i++) {
            this.tags.push({
              tagid: res.data.data[i].tagid,
              name: res.data.data[i].name
            });
          }
        })
        .catch(err => {
          console.log(err);
        });
    },
    pushTopicId(index) {
      this.$router.push({
        name: "topic",
        params: {
          topicid: this.message[index].topicid
        }
      });
    },
    addTopic() {
      this.$router.push({
        name: "topicAdd"
      });
    },
    getTopic() {
      request({
        url: "/topic/page/" + this.pageNum,
        methods: "get",
        headers: {
          token: this.$store.state.token
        }
      })
        .then(res => {
          //那这样更改后，其实我每一次都是要重新请求一次信息的说了
          this.query = 0;
          this.pages = res.data.data.pages;
          for (let i = 0; i < res.data.data.size; i++) {
            this.message.push(res.data.data.list[i]);
          }
        })
        .catch(err => {
          console.log(err);
        });
    },
    getQueryTopic() {
      request({
        url: "/topic/title/" + this.queryInformation + "/page/" + this.pageNum,
        methods: "get",
        headers: {
          token: this.$store.state.token
        }
      })
        .then(res => {
          console.log(res);
          this.pageNum = 1;
          this.message = [];
          this.query = 1;
          this.pages = res.data.data.pages;
          for (let i = 0; i < res.data.data.size; i++) {
            this.message.push(res.data.data.list[i]);
          }
        })
        .catch(err => {
          console.log(err);
        });
    },
    getTagTopic(index) {
      request({
        url: "/topic/" + this.tags[index].tagid + "/page/" + this.pageNum,
        methods: "get",
        headers: {
          token: this.$store.state.token
        }
      })
        .then(res => {
          console.log(res);
          this.pageNum = 1;
          this.message = [];
          this.query = 2;
          this.pages = res.data.data.pages;
          for (let i = 0; i < res.data.data.size; i++) {
            this.message.push(res.data.data.list[i]);
          }
        })
        .catch(err => {
          console.log(err);
        });
    },
    hasBack() {
      this.message = [];
      this.pageNum++;
      if (this.query == 0) {
        this.getTopic();
      } else if (this.query == 1) {
        this.getQueryTopic();
      } else {
        this.getTagTopic();
      }
      this.judgeNext();
      this.judgePre();
    },
    hasForward() {
      this.message = [];
      this.pageNum--;
      if (this.query == 0) {
        this.getTopic();
      } else if (this.query == 1) {
        this.getQueryTopic();
      } else {
        this.getTagTopic();
      }
      this.judgeNext();
      this.judgePre();
    },
    judgeNext() {
      if (this.pageNum < this.pages) {
        return true;
      } else {
        return false;
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
  computed: {
    /*judgeInput(index) {
      if (index + this.browse < this.message.length) {
        return true;
      }
      return false;
    }*/
  },
  mounted() {
    //这个的话就是我们的token了嘛。
    this.getTopic();
    this.getTag();
    this.judgeNext();
    this.judgePre();
  }
};
</script>

<style scoped src="../style/topicList.css">
</style>
<style scoped src="../style/topicTag.css">
</style>
<style scoped src="../style/search1.css">
</style>