<template>
<div>
    <Navbar></Navbar>
    <div id="list-content" >
      <div id="comments" class="comment-module">
      <p class="subtitle">评论审核</p>
  <el-table
    :data="tableData"
    style="width: 100%"
    :default-sort = "{prop: 'date', order: 'descending'}"
    >
    <el-table-column type="expand">
      <template slot-scope="props">
        <el-form label-position="left" inline class="demo-table-expand">
          <el-form-item label="评论 ID" >
            <span>{{ props.row.commentid }}</span>
          </el-form-item>
          <el-form-item label="评论话题 ID">
            <span>{{ props.row.topicid }}</span>
          </el-form-item>
          <el-form-item label="发布者">
            <span>{{ props.row.posterid }}</span>
          </el-form-item>
          <el-form-item label="评论时间">
            <span>{{ props.row.time }}</span>
          </el-form-item>
          <el-form-item label="评论内容">
            <span>{{ props.row.text }}</span>
          </el-form-item>
        </el-form>
      </template>
    </el-table-column>
    <el-table-column
      label="评论内容"
      prop="text"
      show-overflow-tooltip
      width='320'>
    </el-table-column>
    <el-table-column
      label="发布者"
      prop="posterid"
      width="140">
    </el-table-column>
    <el-table-column
      label="评论时间"
      prop="time"
      width="220"
      sortable>
    </el-table-column>
    <el-table-column 
      label="审核"
      width="200">
        <template slot-scope="scope">
          <el-button size="mini" type="primary" plain @click="handlePass(scope.$index, scope.row)">通过</el-button>
          <el-button size="mini" type="danger" plain @click="handleFail(scope.$index, scope.row)">不通过</el-button>
        </template>
    </el-table-column>
  </el-table>

  <br />
  <br />
  <div class="page">
    <el-pagination
      background
      @current-change="handleCurrentChange"
      :current-page="currentPage"
      :page-size="16"
      layout="total, prev, pager, next, jumper"
      :total="commentsNum">
    </el-pagination>
  </div>
</div>

<div id="ctcomments" class="comment-module">
  <hr/>
  <p class="subtitle">课程教师评论审核</p>
   <CTComment></CTComment>
</div>

<div id="reply" class="comment-module">
  <hr/>
  <p class="subtitle">回复信息审核</p>
   <CTComment></CTComment>
</div>

  </div>
</div>
</template>

<style>
#list-content{
  position: absolute;
  top:20px;
  left: 250px;

}
  .demo-table-expand {
    font-size: 0;
  }
  .demo-table-expand label {
    width: 90px;
    color: #99a9bf;
  }
  .demo-table-expand .el-form-item {
    margin-right: 0;
    margin-bottom: 0;
    width: 50%;
  }
  .page{
    text-align: center;
  }
  .comment-module{
    margin: 50px 0px;
  }
  .subtitle{
    font-size: 20px;
    font-weight: bold;
    color: #3b6978;
   
  }
</style>

<script>
import { request } from "../../network/request";
import Navbar from "../Navbar"
import CTComment from "../ctcomment/Ctcomment"
import Reply from "../reply/Reply"
  export default {
    name: "Comment",
     components:{
    Navbar,
    CTComment,
    Reply
  },
    data() {
      return {
        tableData: [],
        commentsNum: 0,
        currentPage: 1,
        search: ''
      };
    },
    
    mounted: function() {
      this.findAllTableData();
    },
    methods: {
      handlePass(index, row) {
        console.log(index, row);
        request({
        url: "/admin/comment/unaudited?commentid="+row.commentid+"&auditstatus=1",
        method: "put",
        headers: {
          token: this.$store.state.token
        }
      }).then(res=>{
          this.$message({
          showClose: true,
          message: '该评论已成功通过'
          });
          this.findAllTableData();
        })
      },
      handleFail(index, row) {
        console.log(index, row);
        request({
        url: "/admin/comment/unaudited?commentid="+row.commentid+"&auditstatus=0",
        method: "put",
        headers: {
          token: this.$store.state.token
        }
      }).then(res=>{
          this.$message({
          showClose: true,
          message: '该评论已删除'
          });
          this.findAllTableData();
        })
      },
      findAllTableData(){
        request({
          url: "/admin/comment/unaudited/page/"+ this.currentPage,
          method: "get",
          headers: {
            token: this.$store.state.token
          }
        }).then(res=>{
          this.tableData=res.data.data.list;
          this.commentsNum=res.data.data.total;
          console.log(res.data.data);
        },
        res => console.log(res)
        );
      },
      handleCurrentChange: function(val) {
        this.currentPage = val;
        request({
          url: "/admin/comment/unaudited/page/"+ this.currentPage,
          method: "get",
          headers: {
            token: this.$store.state.token
          }
        }).then(res=>{
          this.tableData=res.data.data.list;
          this.commentsNum=res.data.data.total;
          console.log(res.data.data);
        },
        res => console.log(res)
        );
      }
    }
  }
</script>