<template>
  <div class="content">
    <div class="teacherContent">
      <p>教师姓名:&nbsp;&nbsp;{{course.teacherName}}</p>
      <!--<p>教师ID:&nbsp;&nbsp;{{course.teacherId}}</p>-->
      <p>学院:&nbsp;&nbsp;{{course.college}}</p>
      <p>教师简介:&nbsp;&nbsp;{{course.info}}</p>
      <p>教师电话:&nbsp;&nbsp;{{course.tel}}</p>
      <p>教师QQ:&nbsp;&nbsp;{{course.QQ}}</p>
    </div>
    <div class="courseContent">
      <p>课程名称:&nbsp;&nbsp;{{course.courseName}}</p>
      <p>课程ID:&nbsp;&nbsp;{{course.courseId}}</p>
      <p>学分:&nbsp;&nbsp;{{course.credits}}</p>
    </div>
  </div>
</template>
<script>
export default {
  name: "CourseInformation",
  props: ["course"]
};
</script>
<style scoped src="../style/courseInformation.css">
</style>