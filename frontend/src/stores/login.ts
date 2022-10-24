import http from '@/api';
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
          const { uid, userName, isAuth } = (this.userData as any).data
          console.log(userName);

          const loginState = new LoginState(uid, userName, isAuth)

          loginState.setLocalStorage()
          message.success('登录成功')

        } else {
          return Promise.reject("登录失败")
        }

      } catch (error: any) {
        if (error) {
          message.error(error.message)
        }
        // 让表单组件显示错误
        return error
      }
    },
  }

},
)