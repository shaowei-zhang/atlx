import axios from "axios"
import router from "../../router";
import  Element from "element-ui"

axios.defaults.baseURL = "http://localhost:8088"
const request = axios.create({
    timeout: 5000,
    headers: {
        'Content-Type': "application/json; charset=utf-8"
    }
})

 // request.interceptors.request.use(config =>{
 //     config.headers['Authorization'] = localStorage.getItem("token")
 //       return config;
 // })


/**
 * 请求结果的拦截
 */
request.interceptors.response.use(response =>{//拦截自定义返回的code
    let res = response.data
    // console.info('@',response.data)
    if(res.code === 200){
        return response;
    }else {
        Element.Message.error(!res.reason?'系统异常':res.reason, {duration: response.data.reason})
        return Promise.reject(response.data.reason)
    }
}
,error => {//拦截状态码

    if(error.response.data){
        error.massage = error.response.data.reason
    }

    if (error.response.status === 401){
        router.push('/login')
    }

    Element.Message.error(error.massage,{duration:3000})
    return Promise.reject(error.massage)
}

)

//使得axios.js暴露出去，让其他文件去引用
export default request