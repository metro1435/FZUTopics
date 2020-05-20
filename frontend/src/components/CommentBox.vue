<template>
  <div class="commentBox">
    <h3>评论</h3>
    <p v-if="comment.length==0">暂无评论，我来发表第一篇评论！</p>
    <div v-else>
      <div class="comment" v-for="(item,index) in comment" :index="index" :key="item+index">
        <b>
          {{item.name}}
          <span>{{item.time}}</span>
        </b>
        <pre @click="changeCommenter(item.name,index)" style='word-Wrap:break-word;white-space: pre-wrap;'>{{item.content}}</pre>
        <div v-if="item.reply.length > 0">
          <div class="reply" v-for="(replyt,m) in item.reply" :key="replyt+m">
            <b>
              {{replyt.answerer}}&nbsp;&nbsp;回复&nbsp;&nbsp;{{item.name}}
              <span>{{replyt.reply.time}}</span>
            </b>
            <p @click="changeCommenter(replyt.answerer,index)">{{replyt.reply.text}}</p>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "CommentBox",
  props: ["comment"],
  methods: {
    changeCommenter: function(name, index) {
      this.$emit("change", name, index);
    }
  }
};
</script>

<style scoped src="../style/commentBox.css">
</style>