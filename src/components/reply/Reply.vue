<template>
<div>
  <el-table
    :data="tableData"
    style="width: 100%"
    :default-sort = "{prop: 'date', order: 'descending'}"
    >
    <el-table-column type="expand">
      <template slot-scope="props">
        <el-form label-position="left" inline class="demo-table-expand">
          <el-form-item label="回复 ID" >
            <span>{{ props.row.replyid }}</span>
          </el-form-item>
          <el-form-item label="回复评论 ID">
            <span>{{ props.row.commentid }}</span>
          </el-form-item>
          <el-form-item label="发布者">
            <span>{{ props.row.userid }}</span>
          </el-form-item>
          <el-form-item label="回复时间">
            <span>{{ props.row.time }}</span>
          </el-form-item>
          <el-form-item label="回复内容">
            <span>{{ props.row.text }}</span>
          </el-form-item>
        </el-form>
      </template>
    </el-table-column>
    <el-table-column
      label="回复内容"
      prop="text"
      show-overflow-tooltip
      width='320'>
    </el-table-column>
    <el-table-column
      label="发布者"
      prop="userid">
    </el-table-column>
    <el-table-column
      label="回复时间"
      prop="time"
      sortable>
    </el-table-column>
    <el-table-column 
      label="审核">
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
      :page-size="10"
      layout="total, prev, pager, next, jumper"
      :total="replysNum">
    </el-pagination>
  </div>
</div>
</template>

<style>
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
</style>

<script>
import { request } from "../../network/request";
  export default {
    name: "Reply",
    data() {
      return {
        tableData: [],
        replysNum: 0,
        currentPage: 1,
        search: ''
      }
    },

     mounted: function() {
      this.findAllTableData();
    },

    methods: {
      handlePass(index, row) {
        console.log(index, row);
        request({
        url: "/admin/reply/unaudited?replyid="+row.replyid+"&auditstatus=1",
        method: "put",
        headers: {
          token: this.$store.state.token
        }
      }).then(res=>{
          this.$message({
          showClose: true,
          message: '该回复已成功通过'
          });
          this.findAllTableData();
        })
      },
      handleFail(index, row) {
        console.log(index, row);
        request({
        url: "/admin/reply/unaudited?replyid="+row.replyid+"&auditstatus=1",
        method: "put",
        headers: {
          token: this.$store.state.token
        }
      }).then(res=>{
          this.$message({
          showClose: true,
          message: '该回复已删除'
          });
          this.findAllTableData();
        })
      },

      findAllTableData(){
        request({
          url: "/admin/reply/unaudited/page/"+ this.currentPage,
          method: "get",
          headers: {
            token: this.$store.state.token
          }
        }).then(res=>{
          this.tableData=res.data.data.list;
          this.replysNum=res.data.data.total;
          console.log(res.data.data);
        },
        res => console.log(res)
        );
      },

      handleCurrentChange: function(val) {
        this.currentPage = val;
        request({
          url: "/admin/reply/unaudited/page/"+ this.currentPage,
          method: "get",
          headers: {
            token: this.$store.state.token
          }
        }).then(res=>{
          this.tableData=res.data.data.list;
          this.replysNum=res.data.data.total;
          console.log(res.data.data);
        },
        res => console.log(res)
        );
      }
    }
  }
</script>