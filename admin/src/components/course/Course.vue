
<!--有个规定：只能选择已存在的教师-->

<template>
<div>
  <Navbar></Navbar>
  <div class="course-main">
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
      width="150">
    </el-table-column>
     <el-table-column
      prop="teacher"
      label="任课教师"
      width="300">
    <template slot-scope="prop">
          <el-select  size="small" v-model="prop.row.teacher" filterable placeholder="任课教师">
            <el-option v-for="(item) in teacherList" :key="item.teacherID" :label="item.name" :value="item.teacherID" >{{item.name}}</el-option>
          </el-select>
      </template>    
    </el-table-column>
     <el-table-column label="操作" width="200">
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
                    <el-option v-for="(item,index) in teacherList" :key="item.teacherID+index" :label="item.name" :value="item.teacherID" >{{item.name}}</el-option>
                  </el-select>
                </el-form-item>
              <el-form-item>
               <el-button type="primary" @click="addCourse(courseForm.name,courseForm.credit,courseForm.teacherID)">新增课程</el-button>
             </el-form-item>
              </el-form>
           </div>
      <!-- <el-button type="primary"  >test</el-button> -->

     </div>
</div>
</template>

<script>
import { request } from "../../network/request";
import Navbar from "../Navbar"
export default {
  name: "Course",
  components:{
    Navbar
  },
  data() {  //这里的teacher其实是teacherID
      return {

         currentPage: 1, //当前页
         topicNum: 5, //总条数

        tableData: [],
        //   courseID: 'c123123',
        //   name: '程序设计',
        //   credit: '3',
        //   teacher: 't12345',
        // }, {
         

      

        courseForm:{
          courseID:'c100112',
          name:'',
          credit:'',
          teacherID:'',
        },

          teacherList: [],
      }
  },
  methods:{

    getCourse(){
       request({
              url: "/admin/getcourse",
              method: "get",
              headers:{
            token:this.$store.state.token
          },
      }).then(res =>{
          console.log(res);
          //前清除本地表内容
          this.tableData=[];
          
           for (let i = 0; i < res.data.data.length; i++) {
           this.tableData.push({
               courseID: res.data.data[i].courseid,

               name: res.data.data[i].coursename,
               credit: res.data.data[i].credits,
               teacher:  res.data.data[i].teacherid
           })
           
          }
        }

      ).catch(err => {
          console.log(err);
        });
    },

    updateCourse(courseID,credit,teacherID){ console.log(courseID,credit,teacherID);
        request({
             //url: "/admin/updatecourse?courseid=c010103&credit=2.0&teacherid=h010103",
              url: "/admin/updatecourse?courseid="+courseID+"&credit="+credit+"&teacherid="+teacherID,
              method: "put",
              // data:{
              //      courseid: courseID,
              //      credit: credit,
              //      teacherid: teacherID
              //      },
              headers:{
            token:this.$store.state.token,
                   },
      }).then(res => {
        console.log(res);
         this.getCourse();
        }
      ).catch(err => {
          console.log(err);
        });
    
    },
    addCourse(name,credit,teacher){
    
       request({
              url: "/admin/addcourse",
              method: "post",
              data:{
                   courseid: '123',
                   credits: credit,
                   coursename: name,
                   teacherid: teacher,

                   },
              headers:{
            token:this.$store.state.token,
            
          },
      }).then(res => {
        console.log(res);
         this.getCourse();
        }
      ).catch(err => {
          console.log(err);
        });
        
    },


     deleteCourse(courseID,teacherID) {
        this.$confirm('确认删除此课程?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          //调用删除函数

          request({
              url: "/admin/deletecourse",
              method: "delete",
              data:{
                courseid:courseID,
                teacherid:teacherID
              },
              headers:{
                     token:this.$store.state.token,
                },
          }).then(res => {
            console.log(res);
            this.getCourse();

            }
          ).catch(err => {
              console.log(err);
            });

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

    
      getTeacher(){
        request({
              url: "/admin/getteacher",
              method: "get",
              headers:{
            token:this.$store.state.token
          },
      }).then(res => {
        console.log(res);
          for (let i = 0; i < res.data.data.length; i++) {
           this.teacherList.push({
               name: res.data.data[i].teachername,
               teacherID:  res.data.data[i].teacherid
           })
           
          }
        }
      ).catch(err => {
          console.log(err);
        });
      },


       handleCurrentChange(val){
      this.currentPage = val;
     
      console.log(`当前页: ${val}`);
    },

  
  },
    created(){
       this.getCourse();
      this.getTeacher();
     // this.updateCourse('c010102',3.0,'h010102');
    }

}
</script>
<style scoped>
.course-main{
  position: absolute;
  top: 2px;
  left: 250px;
  margin: auto;
 
}
.page-title{
  margin-bottom: 50px;
  font-family: sans-serif;
  letter-spacing: 2px;
  font-size: 26px;
  color: #545c64;
}

#newCourse{
  margin-top: 80px;
  margin-bottom: 50px;
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