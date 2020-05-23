<template>
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
            <span>{{ props.row.posterid }}</span>
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
      prop="posterid">
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
    name: "Reply",
    data() {
      return {
        tableData: [{
          replyid: '12345678220200501135809',
          commentid: '12345678020200501134641',
          posterid: '123456782',
          time: '2020-05-01 13:58:09',
          text: '真的是高！'
        }, {
          replyid: '12345678020200501201138',
          commentid: 't12345678020200501121742',
          posterid: '123456780',
          time: '2020-04-01 20:11:38',
          text: '曾经我以为我的普通话是可以上新闻联播的，直到我上了大学'
        }]
      }
    },

    methods: {
      handlePass(index, row) {
        console.log(index, row);
      },
      handleFail(index, row) {
        console.log(index, row);
      }
    }
  }
</script>