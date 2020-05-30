<template>
  <div id="app">
    <!--这里的话我是想要把导航栏先放在上首页那么这里应该用一个component-->
    <router-view></router-view>
  </div>
</template>

<script>
  export default {
    name: 'App',
     provide () {    //父组件中通过provide来提供变量，在子组件中通过inject来注入变量。                                             
            return {
                reload: this.reload                                              
            }
        },
    components:{
    }
  }
</script>

<style>
</style>