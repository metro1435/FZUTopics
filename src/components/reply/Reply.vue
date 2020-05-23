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
        }, {
          replyid: '12345678220200501135155',
          commentid: 't12345678920200501121932',
          posterid: '123456782',
          time: '2020-05-01 13:51:55',
          text: '这种事吧，预防为主，到这一步了给或不给都有损失。比如一开始你就不要让他们知道你认真做了，这样你就可以假托说也是网上找的，或者装作在赶制，到最后一刻才交，或者也装作像他们一样到处找同学抄，总之不要让他们产生找你的想法，不过这些能不能有效就取决于你平常的为人了。或者你偷偷和老师商量一下，让老师在群里夸大查重的准确度和抄袭的严重性，这样你就可以摆出厉害关系以及你的顾虑，他们一般也会放弃抄的念头。总之不可能所有事都能找到两全之策的，碰到了就要自己均衡取舍。我给你的建议是在情理上表达清楚你的想法，同时尽力帮助他们完成而不是给他们抄。'
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