import http from '@/api';
import { STATUS } from '@/api/status';
import router from '@/router';
import LoginState from '@/utils/Login';
import { message } from 'ant-design-vue';
import { defineStore } from 'pinia'


export const useLogin = defineStore('login', {
  state: () => ({
    userData: null
  }),
  actions: {
    async loginUser(username: string, password: string) {

      try {
        this.userData = await http.post('users/login', {
          userName: username,
          password
        })


        if (this.userData) {

          const { code } = this.userData

          // 判断异常
          if (code === STATUS.LOGIN_FAIL) {
            return Promise.reject('账号或密码错误')
          } else if (code === STATUS.LOGIN_BAN) {
            return Promise.reject('账号异常，请联系管理员')
          } else if (code === STATUS.UNKNOWN_ERROR) {
            return Promise.reject('未知错误')
          }

          const { uid, userName, isAuth } = (this.userData as any).data

          const loginState = new LoginState(uid, userName, isAuth)

          loginState.setLocalStorage()

          console.log(code);

          if (code === STATUS.LOGIN_AUTH) {
            message.success('登录成功，即将跳转至后台')
            setTimeout(() => {
              router.push('/doctor')
            }, 1000)
          } else {
            message.success('登录成功')
            setTimeout(() => {
              router.push('/ordermanager')
            }, 1000)
          }

        } else {
          return Promise.reject("登录失败")
        }

      } catch (error: any) {
        console.log(error);
        if (error) {
          message.error(error)
        }
        // 让表单组件显示错误
        return error
      }
    },
  }

},
)