<template>
  <div id="login-main">
    <div class="title">
      <h1>福大热话</h1>
      <p>FZUTopics</p>
    </div>
    <div id="login">
      <div class="row">
        <input type="text" id="username" v-model="form_id" placeholder=" " />
        <label for="username">用户名</label>
      </div>
      <div class="row">
        <input type="password" id="password" v-model="form_password" placeholder=" " />
        <label for="password">密码</label>
        <button @click="getForm">
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
</template>

<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
<script>
import axios from 'axios';
export default {
    name:'login',
    data(){
        return{
            form_id:'',
            form_password:'',
        }
    } ,
     methods:{
        getForm(){
        axios.get('http://localhost:8686/login',{
           userid:this.form_id,
           password:this.form_password
          }).then(res=>(console.log(res.data))).catch(err=>console.log(err))
        }    
    }   
}
</script>
<style scoped src="../style/login.css" >
body{
  background-image: url('../img/fzubg2.jpg');
}
</style>
