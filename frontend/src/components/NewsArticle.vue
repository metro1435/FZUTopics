<template>
  <div>
    <Head></Head>
    <TopicArticle :article="article"></TopicArticle>
  </div>
</template>

<script>
import TopicArticle from "./TopicArticle";
import {request} from '../network/request';
import Head from '../components/Head';
export default {
  name: "NewsArticle",
  components: {
    TopicArticle,Head
  },
  data() {
    return {
      newsid:"",
      article: {
        title: "",
        time: "",
        content:""
      }
    };
  },
  methods:{
    getNewsArticle(){
      request({
        url: "/news/" + this.newsid,
        method: "get",
        headers: {
          token: this.$store.state.token
        }
      })
        .then(res => {
          console.log(res);
          this.article.content =
            "<pre style='word-Wrap:break-word;white-space: pre-wrap;'>" +
            res.data.data.text +
            "</pre>";
          this.article.title = res.data.data.title;
          this.article.time = res.data.data.time;
        })
        .catch(err => {
          console.log(err);
        });
    }
  },
  mounted(){
     this.newsid = this.$route.params.newsid;
     this.getNewsArticle();
  }
};
</script>

<style scoped src="../style/topicArticle.css">
</style>