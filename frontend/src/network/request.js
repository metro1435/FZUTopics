//然后的话我们先来学一下内容
//那这个的其实如果我们不在拦截区做东西的话，我们是不会被影响的是么
import axios from 'axios';
//这样子写的话是为了可能会创建多个实例的说
export function request(config){
    //这个的话是先定义基本的
    const instance=axios.create({
        baseURL:'http://localhost:8686',
        timeout:5000
    })

    //然后的话是我们的一个拦截器
    //这个的话是我们的请求拦截
    instance.interceptors.request.use(config=>{
        return config;
    },err=>{
        return err;
    })
    //接着的话是我们的响应拦截
    instance.interceptors.response.use(res=>{
        return res;
    },err=>{
        return err;
    })

    return instance(config);
}