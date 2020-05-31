// vuex
import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

// 创建vuex的store
const store = new Vuex.Store({
   state:{
       token:"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJhZG1pbjAwMDEiLCJleHAiOjE1OTE0NTQ2NTJ9.6Je8mmJ5GVPiFbM-x1j32sMGU4iyx6jjza0Bkgk_LTM"
   }
})

export default store