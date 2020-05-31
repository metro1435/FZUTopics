<template>
  <div>
    <Navbar></Navbar>
    <div id="tag-content">
    <p class="tip">点击回车创建标签</p>
     <el-input
      class="input-new-tag"
      v-if="inputVisible"
      v-model="inputValue"
      ref="saveTagInput"
      size="small"
      @keyup.enter.native="handleInputConfirm"
      @blur="handleInputConfirm"
    ></el-input>
    <el-button v-else class="button-new-tag" size="medium" type="primary" @click="showInput">+ New Tag</el-button>
    <br/>
    <el-tag
      :key="tag.name"
      v-for="tag in dynamicTags"
      closable
      :disable-transitions="false"
      effect="dark"
      type="info"
      @close="handleClose(tag)"
    >{{tag.name}}</el-tag>
   
    </div>
  </div>
</template>
<script>
import { request } from "../../network/request";
import Navbar from "../Navbar";
export default {
  name: "Tags",
  components:{
    Navbar
  },
  data() {
    return {
      dynamicTags: [
        // {
        //   tagid: "10000",
        //   name: "傻逼",
        //   times: 7
        // },
        // {
        //   tagid: "100",
        //   name: "艺迷术",
        //   times: 1
        // }
      ],
      inputVisible: false,
      inputValue: ""
    };
  },
  methods: {
    handleClose(tag) {
      request({
        url: "/admin/tag?tagid=" + tag.tagid,
        method: "delete",
        headers: {
          token: this.$store.state.token
        }
      })
        .then(res => {
          // console.log(123);
          // console.log(res);
          this.dynamicTags.splice(this.dynamicTags.indexOf(tag), 1);
          this.$message({
            showClose: true,
            message: "标签已删除"
          });
        })
        .catch(err => {
          console.log(err);
        });
    },
    showInput() {
      this.inputVisible = true;
      this.$nextTick(_ => {
        this.$refs.saveTagInput.$refs.input.focus();
      });
    },
    handleInputConfirm() {
      let inputValue = this.inputValue;
      if (inputValue) {
        request({
          url: "/admin/tag",
          method: "post",
          headers: {
            token: this.$store.state.token
          },
          data: {
            name: inputValue
          }
        })
          .then(res => {
            console.log(res.data.data);
            this.dynamicTags.push(res.data.data);
            this.inputVisible = false;
            this.inputValue = "";
          })
          .catch(err => {
            console.log(err);
          });
      }
    },
    getTags() {
      request({
        url: "/admin/tag",
        method: "get",
        headers: {
          token: this.$store.state.token
        }
      })
        .then(res => {
           console.log(123);
           console.log(res);
           console.log(456);
          this.dynamicTags = [];
          console.log(res.data.data);
          for (let i = 0; i < res.data.data.length; i++) {
              
            this.dynamicTags.push(res.data.data[i]);
            console.log(1);
          }
        })
        .catch(err => {
          console.log(err);
        });
    }
  },
  created() {
    console.log(this.$store.state.token);
    this.getTags();
  }
};
</script>
<style>
.el-tag {
  margin-top: 10px;
  margin-right: 10px;
}
#tag-content{
    position: absolute;
    top:20px;
    left: 250px;
}
.button-new-tag {
  margin-top: 10px;
  margin-right: 10px;
  height: 32px;
  line-height: 30px;
  padding-top: 0;
  padding-bottom: 0;
}
.input-new-tag {
  width: 90px;
  margin-top: 10px;
  margin-right: 10px;
  vertical-align: bottom;
}
p.tip{
    color: #aaa;
    font-weight: bold;
}
</style>