// vuex
import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

// 创建vuex的store
const store = new Vuex.Store({
   state:{
       token:"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJhZG1pbjAwMDEiLCJleHAiOjE1OTA4MzE2OTB9.RKX9NT4bEeb0x2vil_6AprhM8duZuAbkN1mQSqP4KtM"
   }
})

export default store