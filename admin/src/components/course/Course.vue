
<!--有个规定：只能选择已存在的教师-->

<template>
<div>
  <h1 class="page-title">课程管理</h1>
  <el-table
    :data="tableData"
    stripe
    style="width: 100%">
    <el-table-column
      prop="courseID"
      label="课程ID"
      width="120">
    </el-table-column>
    <el-table-column
      prop="name"
      label="课程名"
      width="200">
    </el-table-column>
    <el-table-column
     prop="credit"
      label="学分"
      width="200">
      <template slot-scope="prop">
      
        <input
            v-model="prop.row.credit"
            placeholder=""
            size="2"
            maxlength="3"
            class="new-credit-input"
          />    
      </template>

    </el-table-column>
     <el-table-column
      prop="teacher"
      label="任课教师">
    <template slot-scope="prop">
          <el-select  size="small" v-model="prop.row.teacher" filterable placeholder="任课教师">
            <el-option v-for="(item) in teacherList" :key="item.teacherID" :label="item.name" :value="item.teacherID" >{{item.name}}</el-option>
          </el-select>
      </template>    
    </el-table-column>
     <el-table-column label="操作">
      <template slot-scope="prop">
        <el-button
          size="mini"
          type="primary"
          @click="updateCourse(prop.row.courseID, prop.row.credit,prop.row.teacher)">确定修改</el-button>
        <el-button
          size="mini"
          type="danger"
          @click="deleteCourse(prop.row.courseID,prop.row.teacher)">删除</el-button>
      </template>
    </el-table-column>
  </el-table>

<br/>
<div class="page-bar">
  <el-pagination
     background
     @current-change="handleCurrentChange"
      :current-page="currentPage"
    layout="total, prev, pager, next, jumper"
    :total="topicNum"
    :page-size="3">
  </el-pagination>
</div>

  <!--新增课程-->
  <div id="newCourse">
    <h1>新增课程</h1>
      <el-form :inline="true" :model="courseForm" class="demo-form-inline">
        <el-form-item label="新课程名">
        <el-input v-model="courseForm.name" placeholder="新课程名"></el-input>
        </el-form-item>
        <el-form-item label="学分">
        <el-input v-model="courseForm.credit" placeholder="学分"></el-input>
        </el-form-item>
       <el-form-item label="任课教师">
          <el-select filterable v-model="courseForm.teacherID" placeholder="任课教师">
            <el-option v-for="(item) in teacherList" :key="item.teacherID" :label="item.name" :value="item.teacherID" >{{item.name}}</el-option>
          </el-select>
        </el-form-item>
       <el-form-item>
    <el-button type="primary" @click="addCourse(courseForm.name,courseForm.credit,courseForm.teacherID)">新增课程</el-button>
  </el-form-item>
</el-form>
  </div>

  

</div>
</template>

<script>
import { request } from "../../network/request";
export default {
  name: "Course",
  data() {  //这里的teacher其实是teacherID
      return {

         currentPage: 1, //当前页
         topicNum: 5, //总条数

        tableData: [{
          courseID: 'c123123',
          name: '程序设计',
          credit: '3',
          teacher: 't12345',
        }, {
           courseID: 'c123011',
          name: '算法设计',
          credit: '3',
          teacher: 't01345',
        }, {
          courseID: 'c120001',
          name: '界面设计',
          credit: '3',
          teacher: 't79680',
          }, {
          courseID: 'c120001',
          name: '界面设计',
          credit: '3',
          teacher: 't79680',
          }, {
          courseID: 'c120001',
          name: '界面设计',
          credit: '3',
          teacher: 't79680',
          }, {
          courseID: 'c120001',
          name: '界面设计',
          credit: '3',
          teacher: 't79680',
          }, {
          courseID: 'c120001',
          name: '界面设计',
          credit: '3',
          teacher: 't79680',
          }, {
          courseID: 'c120001',
          name: '界面设计',
          credit: '3',
          teacher: 't79680',
        }],

        teacherList:[{
            teacherID: 't12345',
            name:'wch'
        },
        {
           teacherID: 't01345',
            name:'wjf'
        },
        {
           teacherID: 't79680',
            name:'lyh'
        }],

        courseForm:{
          courseID:'c100112',
          name:'',
          credit:'',
          teacherID:'',
        }
        
      }
  },
  methods:{
    updateCourse(courseID,credit,teacherID){console.log(courseID, credit,teacherID)},
    addCourse(name,credit,teacher){
      console.log(name,credit,teacher)
    },


     deleteCourse(courseID,teacherID) {
        this.$confirm('确认删除此课程?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          //调用删除函数
          this.$message({
            type: 'success',
            message: '删除成功!'
          });
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          });          
        });
      },

       handleCurrentChange(val){
      this.currentPage = val;
     
      console.log(`当前页: ${val}`);
    },
    

  }
}
</script>
<style scoped>
.page-title{
  margin-bottom: 50px;
  font-family: sans-serif;
  letter-spacing: 2px;
  font-size: 26px;
  color: #545c64;
}
.new-credit-input{
  display: inline-block;
  margin: 0px 3px;
}
#newCourse{
  margin-top: 80px;
}
#newCourse h1{
   margin-bottom: 30px;
  font-family: sans-serif;
  letter-spacing: 2px;
  font-size: 26px;
  color: #545c64;
}
.page-bar{
  margin: 10px auto;
  text-align: center;
}
</style>