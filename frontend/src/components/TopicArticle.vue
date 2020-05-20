<template>
<div class="content">
  <div class="primary">
    <article class="post">
      <div class="post-content">
        <h1 class="post-title">{{article.title}}</h1>
        <time class="post-time">{{article.time}}</time>
        <div v-html="article.content" class="prep"></div>
      </div>
    </article>
  </div>
</div>
</template>
<script>
export default {
  name: "TopicArticle",
  props: ["article"]
};
</script>
<style scoped src="../style/topicArticle.css">
</style>