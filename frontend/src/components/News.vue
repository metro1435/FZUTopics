<template>
  <div>
    <Head></Head>
    <div class="content">
      <div class="home">
        <article class="post" v-for="(value,index) in message" :key="value+index">
          <header class="post-header">
            <h1 class="post-title">
              <!--就是这个地方我们需要变成router-link来弄会比较好-->
              <a @click="pushNewsId(index)">{{message[index].title}}</a>
              <!--<a class="post-link" href="#">{{getTitle(index)}}</a>-->
            </h1>
            <time class="post-time">{{message[index].time}}</time>
            <hr />
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
    </div>
  </div>
</template>

<script>
import Head from "../components/Head";
import { request } from "../network/request";
export default {
  name: "News",
  components:{
    Head,
  },
  data() {
    return {
      message: [],
      pages: 1,
      pageNum: 1,
      pre: false,
      back: false
    };
  },
  methods: {
    pushNewsId(index) {
      console.log(12345);
      this.$router.push({
        name: "newsArticle",
        params: {
          newsid: this.message[index].newsid
        }
      });
    },
    getNews() {
      request({
        url: "/news/page/" + this.pageNum,
        methods: "get",
        headers: {
          token: this.$store.state.token
        }
      })
        .then(res => {
          //那这样更改后，其实我每一次都是要重新请求一次信息的说了
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
      this.getNews();
      this.judgeNext();
      this.judgePre();
    },
    hasForward() {
      this.message = [];
      this.pageNum--;
      this.getNews();
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
  mounted() {
    //这个的话就是我们的token了嘛。
    this.getNews();
    this.judgeNext();
    this.judgePre();
  }
};
</script>
<style scoped src="../style/newsList.css">
</style>