<template>
  <div id="app">
    <el-container>
      <el-aside width="200px">
            <el-menu
              default-active="2"
              class="el-menu-vertical-demo"
              @select="handleSelect"
              background-color="#545c64"
              text-color="#fff"
              active-text-color="#ffd04b"
            >
              <el-menu-item index="/">          
                <span slot="title">福大热话</span>
              </el-menu-item>
              <el-menu-item index="/topics">
                <i class="el-icon-location"></i>
                <span slot="title">话题管理</span>
              </el-menu-item>
             <el-menu-item index="/comments">
                <i class="el-icon-menu"></i>
                <span slot="title">话题评论管理</span>
              </el-menu-item>
              <el-menu-item index="/ctcomments">
                <i class="el-icon-chat-dot-square"></i>
                <span slot="title">课程（教师）评论管理</span>
              </el-menu-item>
              <el-menu-item index="/replys">
                <i class="el-icon-chat-line-round"></i>
                <span slot="title">回复管理</span>
              </el-menu-item>
              <el-menu-item index="/news">
                <i class="el-icon-reading"></i>
                <span slot="title">新闻管理</span>
              </el-menu-item>
              <el-menu-item index="/courses">
                <i class="el-icon-document"></i>
                <span slot="title">课程管理</span>
              </el-menu-item>
              <el-menu-item index="/tags">
                <i class="el-icon-setting"></i>
                <span slot="title">标签管理</span>
              </el-menu-item>
              <el-menu-item index="6">
                <i class="el-icon-s-promotion"></i>
                <span slot="title">退出</span>
              </el-menu-item>
            </el-menu>
      </el-aside>
      <el-container>
        <el-main>
          <router-view/>
        </el-main>
        <!-- <el-footer>Footer</el-footer> -->
      </el-container>
    </el-container>
  </div>
</template>

<script>
export default {
  name: 'App',
  methods: {
    handleSelect(key, keyPath) {
      console.log(key, keyPath);
      this.$router.push(key);
    },
    created(){
      console.log(this.$store.state.token)
    }
  }
}
</script>

<style>

</style>
