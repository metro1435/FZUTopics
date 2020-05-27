<template>
  <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
  <el-form-item label="新闻标题" prop="title">
    <el-input v-model="ruleForm.title"></el-input>
  </el-form-item>
   <el-form-item label="新闻内容" prop="text">
    <el-input type="textarea" v-model="ruleForm.text"></el-input>
  </el-form-item>
  <el-form-item>
    <el-button type="primary" @click="onSubmit">立即添加</el-button>
    <el-button>取消</el-button>
  </el-form-item>
</el-form>
</template>

<script>
import { request } from "../../network/request";
export default {
    name: "News",
    data() {
      return {
        newsid: '',
        adminid: '',
        time: '',
        ruleForm: {
          title: '',
          text: ''
        },
        rules: {
          title: [
            { required: true, message: '请输入新闻标题', trigger: 'blur' }
          ],
          text: [
            { required: true, message: '请填写新闻内容', trigger: 'blur' }
          ]
        }
      }
    },

    mounted() {
    this.getAdminId();
  },
    methods: {
      onSubmit() {
        console.log('submit!');
         request({
          url: "/admin/news",
          method: "post",
          headers: {
            token: this.$store.state.token
          },data:{
            newsid: 'n'+this.adminid+this.getTime1(),
            adminid: this.adminid,
            title: this.ruleForm.title,
            text: this.ruleForm.text,
            time: this.getTime()
          }
        }).then(res=>{
          console.log(res);
            if(res.data.status=200){
              this.$message({
              showClose: true,
              message: '已成功添加该新闻'
              });
              this.ruleForm={};
            }else{
              this.$message({
              showClose: true,
              message: '添加新闻失败,请稍候再试'
              });
            }
          })
      },

      getTime() {
      var now = new Date();
      var year = now.getFullYear();
      var month = now.getMonth() + 1;
      var day = now.getDate();
      var hour = now.getHours();
      var mm = now.getMinutes();
      var s = now.getSeconds();
      month = month < 10 ? "0" + month : month;
      day = day < 10 ? "0" + day : day;
      return year + "-" + month + "-" + day + " " + hour + ":" + mm + ":" + s;
      },
      getTime1() {
        var now = new Date();
        var year = now.getFullYear();
        var month = now.getMonth() + 1;
        var day = now.getDate();
        var hour = now.getHours();
        var mm = now.getMinutes();
        var s = now.getSeconds();
        month = month < 10 ? "0" + month : month;
        day = day < 10 ? "0" + day : day;
        return ""+year  + month  + day  + hour  + mm  + s;
      },
      getAdminId() {
      request({
        url: "/token",
        method: "get",
        headers: {
          token: this.$store.state.token
        }
      })
        .then(res => {
          this.adminid = res.data.message;
        })
        .catch(err => {
          console.log(err);
        });
      }
    }
}
</script>