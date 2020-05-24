<template>
  <div>
    <el-table :data="tableData" style="width: 100%">
      <el-table-column type="expand">
        <template slot-scope="props">
          <el-form label-position="left" inline class="demo-table-expand">
            <el-form-item label="话题 ID">
              <span>{{ props.row.topicid }}</span>
            </el-form-item>
            <el-form-item label="话题标题">
              <span>{{ props.row.title }}</span>
            </el-form-item>
            <el-form-item label="发布者">
              <span>{{ props.row.userid }}</span>
            </el-form-item>
            <el-form-item label="发布时间">
              <span>{{ props.row.time }}</span>
            </el-form-item>
            <el-form-item label="话题内容">
              <span style="width:924px;display:inline-block">{{ props.row.text }}</span>
            </el-form-item>
          </el-form>
        </template>
      </el-table-column>
      <el-table-column label="话题标题" prop="title"></el-table-column>
      <el-table-column label="发布者" prop="userid"></el-table-column>
      <el-table-column label="发布时间" prop="time"></el-table-column>
      <el-table-column label="审核">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="primary"
            plain
            @click="handlePass(scope.$index, scope.row)"
          >通过</el-button>
          <el-button
            size="mini"
            type="danger"
            plain
            @click="handleFail(scope.$index, scope.row)"
          >不通过</el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
import { request } from "../../network/request";
export default {
  name: "List",
  data() {
    return {
      pages: 1,
      pageNum: 1,
      pre: false,
      back: false,
      tableData: [
        {
          topicid: "t12345678020200501121742",
          title: "有哪些关于福建的冷知识？",
          text:
            "我是福建人，感觉对自己的家乡也不是特别了解，闽东闽南闽北还是有很大差距的，想听听对于福建的冷知识，勿地域黑，谢谢",
          userid: "123456780",
          time: "2020-05-01"
        },
        {
          topicid: "t12345678120200501134819",
          title: "有肖战道歉",
          text:
            "肖战视频专访道歉，就这两个月的事件和曾经的网络言论道歉了，你们接受吗？",
          userid: "123456781",
          time: "2020-05-01"
        }
      ]
    };
  },
  methods: {
    getTopics() {
      request({
        url: "/admin/topic/unaudited/page/" + this.pageNum,
        method: "get",
        headers: {
          token: this.$store.state.token
        }
      }).then(res => {
        console.log(123);
        this.pages = res.data.data.pages;
        for (let i = 0; i < res.data.data.size; i++) {
          console.log(res.data.data.list[i]);
          this.tableData.push(res.data.data.list[i]);
        }
      })
      .catch(err =>{
        console.log(err);
      });
    },
    handlePass(index, row) {
      console.log(index, row);
    },
    handleFail(index, row) {
      console.log(index, row);
    }
  },
  created() {
    this.getTopics();
  },
};
</script>
<style scoped>
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