<template>
  <div id="app">
    <!--这里的话我是想要把导航栏先放在上首页那么这里应该用一个component-->
    <router-view></router-view>
  </div>
</template>

<script>
  export default {
    name: 'App',
   
    components:{
    },
    created(){
      if (sessionStorage.getItem("token")) {
      this.$store.state.token = sessionStorage.getItem("token");
      /*this.$store.replaceState(
        Object.assign(
          {},
          this.$store.state,
          JSON.parse(sessionStorage.getItem('store'))
        )
      )*/
    }
    // 在页面刷新时将vuex里的信息保存到sessionStorage里
    // beforeunload事件在页面刷新时先触发
    window.addEventListener("beforeunload", () => {
      sessionStorage.setItem("token", this.$store.state.token);
    });

    }
   
  }
</script>

<style>
</style>