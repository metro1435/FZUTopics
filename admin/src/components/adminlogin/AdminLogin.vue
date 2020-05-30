<template>
<div id="login-body">
  <div id="login-main">
    <div class="title">
      
      <h1>福大热话</h1>
      <h4>管理员登录</h4>
    </div>
    <div id="login">
      <div class="row">
        <input type="text" id="username" placeholder=" " v-model="userid" />
        <label for="username">用户名</label>
      </div>
      <div class="row">
        <input type="password" id="password" placeholder=" " v-model="password" />
        <label for="password">密码</label>
        <button @click="existId()">
          <svg
            width="24px"
            height="24px"
            viewBox="0 0 180 180"
            version="1.1"
            xmlns="http://www.w3.org/2000/svg"
            xmlns:xlink="http://www.w3.org/1999/xlink"
          >
            <!-- Generator: Sketch 63.1 (92452) - https://sketch.com -->
            <g id="Page-1" stroke="none" stroke-width="1" fill="none" fill-rule="evenodd">
              <g id="Artboard">
                <g id="Group">
                  <circle id="Oval" stroke="#2E3131" stroke-width="15" cx="90" cy="90" r="82.5" />
                  <polygon
                    id="Triangle"
                    fill="#2E3131"
                    transform="translate(98.000000, 90.000000) rotate(90.000000) translate(-98.000000, -90.000000) "
                    points="98 62 140 118 56 118"
                  />
                </g>
              </g>
            </g>
          </svg>
        </button>
      </div>
    </div>
  </div>
  </div>
</template>

<script>
//这一块是我们的js部分，那么我们就通过这个来进行
import { request } from "../../network/request";
export default {
  name: "AdminLogin",
  data() {
    return {
      userid: "",
      password: ""
    };
  },
  methods: {
    existId() {
      request({
        url: "/login",
        method: "post",
        data: {
          userid: this.userid,
          password: this.password
        }
      })
        .then(res => {
          if (res.data.code == 200) {
            this.$store.state.token = res.data.token;
            this.$router.push({
              name: "Course"
            });
          }else{
            alert("您的账户或密码错误");
          }
        })
        .catch(err => {
          console.log(err);
        });
    }
  }
};

//这个地方的话就是我们的请求部分
</script>

<style scoped src="../../style/login.css" >
</style>