import axios from 'axios';
// import type { AxiosInstance, AxiosRequestConfig, AxiosResponse } from 'axios'
// import axios, from 'axios';
// import qs from 'qs'
// import { showMessage } from "./status";
// import { ElMessage } from 'element-plus'
// import { IResponse, ILogin } from './type';
const instance = axios.create({
  baseURL: 'https://www.fastmock.site/mock/9bbe2ba655977b9b564d9bfa05371dcc/api',
  // baseURL: 'http://localhost:8080'
});

export default instance

// // axios实例拦截响应
// axiosInstance.interceptors.response.use(
//   (response: AxiosResponse) => {
//     if (response.headers.authorization) {
//       localStorage.setItem('app_token', response.headers.authorization);
//     } else {
//       if (response.data && response.data.token) {
//         localStorage.setItem('app_token', response.data.token);
//       }
//     }

//     if (response.status === 200) {
//       return response;
//     } else {
//       showMessage(response.status);
//       return response;
//     }
//   },
//   // 请求失败
//   (error: any) => {
//     const { response } = error;
//     if (response) {
//       // 请求已发出，但是不在2xx的范围
//       showMessage(response.status);
//       return Promise.reject(response.data);
//     } else {
//       ElMessage.warning('网络连接异常,请稍后再试!');
//     }
//   }
// );

// // axios实例拦截请求
// axiosInstance.interceptors.request.use(
//   (config: AxiosRequestConfig) => {
//     // const { user } = store.state
//     // if (token) {
//     //   config.headers.Authorization = `Bearer ${token}`
//     // }
//     return config;
//   },
//   (error: any) => {
//     return Promise.reject(error);
//   }
// )

// /**
//  * @description: 用户登录
//  * @params {ILogin} params
//  * @return {Promise}
//  */
// export const login = (params: ILogin): Promise<IResponse> => {
//   return axiosInstance.post('user/login', params).then(res => res.data);
// };
