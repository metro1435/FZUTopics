<template>
  <div>
    <Navbar></Navbar>
    <div id="list-content">
    <el-table :data="tableData" style="width: 100%">
      <el-table-column type="expand">
        <template slot-scope="props">
          <el-form label-position="left" inline class="demo-table-expand">
            <el-form-item label="话题 ID">
              <span>{{ props.row.topicid }}</span>
            </el-form-item>
            <el-form-item label="话题标题" >
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
      <el-table-column label="话题标题" prop="title" show-overflow-tooltip width="420"></el-table-column>
      <el-table-column label="发布者" prop="userid" width="140"></el-table-column>
      <el-table-column label="发布时间" prop="time" width="300"></el-table-column>
      <el-table-column label="审核" width="400">
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
    <br />
    <br />
    <el-pagination
      background
      @current-change="handleCurrentChange"
      :current-page="currentPage"
      :page-size="6"
      layout="total, prev, pager, next, jumper"
      :total="topicNum"
      class="pagination"
    ></el-pagination>
    </div>
  </div>
</template>

<script>
import { request } from "../../network/request";
import Navbar from "../Navbar"
export default {
  name: "List",
  components:{
    Navbar
  },
  data() {
    return {
      currentPage: 1, //当前页
      topicNum: 1, //总条数
      pre: false,
      back: false,
      topicid: "",
      type: 0, //1是审核通过，0是不通过
      tableData: [
        // {
        //   topicid: "t12345678020200501121742",
        //   title: "有哪些关于福建的冷知识？",
        //   text:
        //     "我是福建人，感觉对自己的家乡也不是特别了解，闽东闽南闽北还是有很大差距的，想听听对于福建的冷知识，勿地域黑，谢谢",
        //   userid: "123456780",
        //   time: "2020-05-01"
        // },
        // {
        //   topicid: "t12345678120200501134819",
        //   title: "有肖战道歉",
        //   text:
        //     "肖战视频专访道歉，就这两个月的事件和曾经的网络言论道歉了，你们接受吗？",
        //   userid: "123456781",
        //   time: "2020-05-01"
        // }
      ]
    };
  },
  methods: {
    getTopics() {
      request({
        url: "/admin/topic/unaudited/page/" + this.currentPage,
        method: "get",
        headers: {
          token: this.$store.state.token
        }
      })
        .then(res => {
          this.tableData = [];
          // console.log(123);
          this.topicNum = res.data.data.total;
          // console.log(res.data.date.total);
          this.pages = res.data.data.pages;
          for (let i = 0; i < res.data.data.size; i++) {
            // console.log(res.data.data.list[i]);
            this.tableData.push(res.data.data.list[i]);
          }
        })
        .catch(err => {
          console.log(err);
        });
    },
    auditTopic() {
      request({
        url: "/admin/topic/unaudited?topicid="+this.topicid+"&auditstatus="+this.type,
        method: "put",
        headers: {
          token: this.$store.state.token
        }
      })
        .then(res => {
          console.log(res);
        })
        .catch(err => {
          console.log(err);
        });
    },
    handleCurrentChange(val) {
      this.currentPage = val;
      this.getTopics();
      console.log(`当前页: ${val}`);
    },
    handlePass(index, row) {
      this.type = 1;
      this.topicid = row.topicid;
      this.auditTopic();
      // console.log(row.topicid);
      this.$message({
        showClose: true,
        message: "该话题已成功通过"
      });
      this.tableData.splice(index, 1);
      this.topicNum--;
    },
    handleFail(index, row) {
      this.type = 0;
      this.topicid = row.topicid;
      this.auditTopic();
      // console.log(row.topicid);
      this.$message({
        showClose: true,
        message: "该评论已删除"
      });
      this.tableData.splice(index, 1);
      this.topicNum--;
    }
  },
  created() {
    this.getTopics();
  }
};
</script>
<style scoped>
#list-content{
  position: absolute;
  top:20px;
  left: 250px;

}
.pagination{
  text-align: center;
  margin-top: 20px;
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
</style>