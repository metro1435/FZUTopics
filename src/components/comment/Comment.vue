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
      prop="posterid">
    </el-table-column>
    <el-table-column
      label="评论时间"
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
  <el-pagination
    background
    @current-change="handleCurrentChange"
    :current-page="currentPage"
    :page-size="16"
    layout="total, prev, pager, next, jumper"
    :total="commentsNum">
  </el-pagination>
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
</style>

<script>
  export default {
    name: "Comment",
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
        this.$http.put("http://localhost:8686/admin/comment/unaudited/1/"+row.commentid).then(res=>{
          this.$message({
          showClose: true,
          message: '该评论已成功通过'
          });
          this.findAllTableData();
        })
      },
      handleFail(index, row) {
        console.log(index, row);
        this.$http.put("http://localhost:8686/admin/comment/unaudited/0/"+row.commentid).then(res=>{
          this.$message({
          showClose: true,
          message: '该评论已删除'
          });
          this.findAllTableData();
        })
      },

      findAllTableData(){
        this.$http.get("http://localhost:8686/admin/comment/unaudited/page/"+ this.currentPage).then(res=>{
          this.tableData=res.data.data.list;
          this.commentsNum=res.data.data.total;
          console.log(res.data.data);
        },
        res => console.log(res)
        );
      },

      handleCurrentChange: function(val) {
        this.currentPage = val;
        this.$http.get("http://localhost:8686/admin/comment/unaudited/page/"+ this.currentPage).then(res=>{
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