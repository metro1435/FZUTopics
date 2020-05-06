<template>
  <div class="content">
    <div class="home">
      <article class="post" v-for="(value,index) in message" :key="value">
        <header class="post-header" v-if="judgeInput(index)" >
          <h1 class="post-title">
            <!--对应的话是要更改这个文件的一个数据-->
            <!--<a class="post-link" href="#">{{getTitle(index)}}</a>-->
             <router-link to="/Topic" class="post-link">{{getTitle(index)}}</router-link>
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
    <div class="secondary">
      <section class="widget-area">
        <h3 class="widget-title">Tags</h3>
        <ul class="tag-list">
          <li class="tag-list-item" v-for="(item,m) in tags" :key="item">
            <a class="tag-list-link" :href="tagHref[m]">{{item}}</a>
          </li>
        </ul>
      </section>
      <div class="search">
           <input type="text" name="topic-search" placeholder="查询话题">
           <!--昨天晚上是做到这里，然后是想改搜索框的样式，我们目前这个就省略掉线不弄-->
           <div class="index-search-btn">
               <button type="button" class="search-btn"><img src="../img/sousuo.png" style="height: 26px;"/></button>
           </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "Home",
  data() {
    return {
      message: [
        {
          title: "你是猪",
          year: 2019,
          month: 12,
          day: 19
        },
        {
          title: "你也是猪",
          year: 2019,
          month: 5,
          day: 28
        },
        {
          title: "你也是猪",
          year: 2019,
          month: 1,
          day: 28
        },
        {
          title: "你也是猪",
          year: 2019,
          month: 2,
          day: 28
        },
        {
          title: "你也是猪",
          year: 2019,
          month: 3,
          day: 28
        },
        {
          title: "你也是猪",
          year: 2019,
          month: 4,
          day: 28
        },
        {
          title: "你也是猪",
          year: 2019,
          month: 6,
          day: 28
        },
        {
          title: "你也是猪",
          year: 2019,
          month: 8,
          day: 28
        },
        {
          title: "你也是猪",
          year: 2019,
          month: 9,
          day: 28
        },
        {
          title: "你也是猪",
          year: 2019,
          month: 10,
          day: 28
        },
        {
          title: "你也是猪",
          year: 2019,
          month: 9,
          day: 28
        },
        {
          title: "你也是猪",
          year: 2019,
          month: 10,
          day: 28
        }
      ],
      forward: 0,
      backward: 0,
      browse: 0,
      turnPage: false,
      tags: ["校园", "青春", "生活", "运动"],
      tagHref: [
        "https://www.imooc.com",
        "https://www.runoob.com",
        "https://www.php.cn",
        "https://fengfu.space"
      ]
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
  computed:{
      /*judgeInput(index) {
      if (index + this.browse < this.message.length) {
        return true;
      }
      return false;
    }*/
  },
  mounted() {
    this.judgeNext();
  }
};
</script>

<style scoped src="../style/topicList.css">
</style>
<style scoped src="../style/topicTag.css">
</style>
<style scoped src="../style/search1.css">
</style>