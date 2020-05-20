<template>
  <div id="app">
    <!--这里的话我是想要把导航栏先放在上首页那么这里应该用一个component-->
    <router-view></router-view>
    <Footer></Footer>
  </div>
</template>

<script>
  import Footer from './components/Footer'
  export default {
    name: 'App',
    components:{
    Footer,
    }
  }
</script>

<style>
</style>
