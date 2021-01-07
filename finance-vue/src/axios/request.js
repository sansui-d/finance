import axios from 'axios';
import ElementUI, { MessageBox } from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import router from '../router'
import { getToken } from "@/cookie/cookie.js"

axios.defaults.timeout = 5000;
// 环境的切换
if (process.env.NODE_ENV == 'development') { 
    axios.defaults.baseURL = '/api';
   } else if (process.env.NODE_ENV == 'debug') { 
    axios.defaults.baseURL = '';
   } else if (process.env.NODE_ENV == 'production') { 
    axios.defaults.baseURL = 'http://localhost:8088/';
   }

//http request 拦截器
axios.interceptors.request.use(
  config => {
    const token = getToken();
    config.data = JSON.stringify(config.data);
    config.headers = {
      'Content-Type':'application/json',
      'token':token
    }
    return config;
  },
  error => {
    return Promise.reject(err);
  }
);


//http response 拦截器
axios.interceptors.response.use(
  response => {
    if(response.data == 88){
      ElementUI.MessageBox({
        title:'警告',
        message:'登录异常，请重新登录',
        type:'warning',
        confirmButtonText:'重新登录',
        showClose:false,
        closeOnClickModal:false,
        callback:()=>{
          return new Promise((resolve, reject) => {
            router.push('/')
          }).catch(function (reason) {
            console.log('catch:', reason)
          })
        }
      })

    }
    return response;
  },
  error => {
    return Promise.reject(error)
  }
)


/**
 * 封装get方法
 * @param url
 * @param data
 * @returns {Promise}
 */

export function get(url,params={}){
  return new Promise((resolve,reject) => {
    axios.get(url,{
      params,
    })
    .then(response => {
      resolve(response.data);
    })
    .catch(err => {
      reject(err)
    })
  })
}


/**
 * 封装post请求
 * @param url
 * @param data
 * @returns {Promise}
 */

 export function post(url,data = {}){
   return new Promise((resolve,reject) => {
     axios.post(url,data)
          .then(response => {
            resolve(response.data);
          },err => {
            reject(err)
          })
   })
 }

 /**
 * 封装patch请求
 * @param url
 * @param data
 * @returns {Promise}
 */

export function patch(url,data = {}){
  return new Promise((resolve,reject) => {
    axios.patch(url,data)
         .then(response => {
           resolve(response.data);
         },err => {
           reject(err)
         })
  })
}

 /**
 * 封装put请求
 * @param url
 * @param data
 * @returns {Promise}
 */

export function put(url,data = {}){
  return new Promise((resolve,reject) => {
    axios.put(url,data)
         .then(response => {
           resolve(response.data);
         },err => {
           reject(err)
         })
  })
}