<template>
  <div class="content">
    <div class="home">
      <article class="post" v-for="(value,index) in message" :key="value">
        <header class="post-header" v-if="judgeInput(index)">
          <h1 class="post-title">
            <!--就是这个地方我们需要变成router-link来弄会比较好-->
            <router-link to="/NewsArticle">{{getTitle(index)}}</router-link>
            <!--<a class="post-link" href="#">{{getTitle(index)}}</a>-->
          </h1>
          <time class="post-time">{{date(index)}}</time>
          
        </header>
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
  name: "News",
  data() {
    return {
      message: [
        {
          title: "bbbbb",
          article: "笔记哈哈哈叫怕怕怕怕怕",
          year: 2019,
          month: 12,
          day: 19
        },
        {
          title: "哈哈哈哈",
          article: "",
          year: 2019,
          month: 5,
          day: 28
        },
        {
          title: "酷酷酷",
          article: "",
          year: 2019,
          month: 1,
          day: 28
        },
        {
          title: "你也是猪",
          article: "",
          year: 2019,
          month: 2,
          day: 28
        },
        {
          title: "lllll",
          article: "",
          year: 2019,
          month: 3,
          day: 28
        },
        {
          title: "你也是猪",
          article: "",
          year: 2019,
          month: 4,
          day: 28
        },
        {
          title: "你也是猪",
          article: "",
          year: 2019,
          month: 6,
          day: 28
        },
        {
          title: "你也是猪",
          article: "",
          year: 2019,
          month: 8,
          day: 28
        },
        {
          title: "你也是猪",
          article: "",
          year: 2019,
          month: 9,
          day: 28
        },
        {
          title: "你也是猪",
          article: "",
          year: 2019,
          month: 10,
          day: 28
        },
        {
          title: "你也是猪",
          article: "",
          year: 2019,
          month: 9,
          day: 28
        },
        {
          title: "你也是猪",
          article: "",
          year: 2019,
          month: 10,
          day: 28
        }
      ],
      forward: 0,
      backward: 0,
      browse: 0,
      turnPage: false
    };
  },
  methods: {
    date(index) {
      return (
        this.message[index].year +
        "." +
        this.message[index].month +
        "." +
        this.message[index].day
      );
    },
    judgeNext() {
      //判断是否内容超过十条
      if (this.message.length - this.browse > 10) {
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
    getTitle(index) {
      return this.message[index + this.browse].title;
    },
    judgeInput(index) {
      if (index + this.browse < this.message.length) {
        return true;
      }
      return false;
    }
  },
  mounted() {
    this.judgeNext();
  }
};
</script>
<style scoped src="../style/newsList.css">
</style>