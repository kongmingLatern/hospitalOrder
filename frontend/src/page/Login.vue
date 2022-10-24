<template>
  <div class="login-container">
    <div w-120 m-auto class="center">
      <a-spin :spinning="spinning">
        <a-card :border="false">
          <template #title>
            <h3>登录</h3>
          </template>
          <a-form
            :model="formState"
            name="normal_login"
            class="login-form"
            @finish="onFinish"
            @finishFailed="onFinishFailed"
          >
            <a-form-item
              label="用户名"
              name="username"
              :rules="[{ required: true, message: 'Please input your username!' }]"
            >
              <a-input v-model:value="formState.username"></a-input>
            </a-form-item>

            <a-form-item
              label="密码"
              name="password"
              :rules="[{ required: true, message: 'Please input your password!' }]"
            >
              <a-input-password v-model:value="formState.password"></a-input-password>
            </a-form-item>

            <a-form-item text-center>
              <a-button
                :disabled="disabled"
                :loading="loading"
                type="primary"
                html-type="submit"
                data-test="loginButton"
              >
                登录
              </a-button>
              <router-link to="/regist" class="right">注册</router-link>
            </a-form-item>
          </a-form>
        </a-card>
      </a-spin>
    </div>
  </div>
</template>
<script lang="ts" setup>
import type { LoginType } from '@/type'
import { message } from 'ant-design-vue'
import { reactive, computed, ref, getCurrentInstance, toRaw } from 'vue'
import router from '../router'
import { useLogin } from '../stores/login'
const formState = reactive<LoginType>({
  username: '',
  password: '',
})

const spinning = ref<Boolean>(false)
const loading = ref<Boolean>(false)

const onFinish = async (values: any) => {
  const store = useLogin()
  const { username: userName, password } = formState
  try {
    await store.loginUser(userName, password)
    message.success('登录成功')
    setTimeout(() => {
      router.push('/doctor')
    }, 1000)
  } catch (err: any) {
    message.error(err.msg)
  }
  // request
  //   .post('/users/login', {
  //     userName,
  //     password,
  //   })
  //   .then((res: any) => {
  //     console.log(res)
  //     const { code } = res.data
  //     console.log(code)
  //     // 登录成功
  //     if (code === STATUS.LOGIN_SUCCESS || code === STATUS.LOGIN_AUTH) {
  //       // 展示 Loading 信息
  //       loading.value = true
  //       const { uid, isAuth, userName } = res.data.data
  //       // 存入缓存信息
  //       localStorage.setItem('uid', uid)
  //       localStorage.setItem('isAuth', isAuth)
  //       localStorage.setItem('username', userName)

  //       setTimeout(() => {
  //         if (isAuth) {
  //           message.success('登录成功，欢迎您管理员，即将跳转到后台管理')
  //           router.push('/doctor')
  //         } else {
  //           message.success('登录成功')
  //           router.push('/ordermanager')
  //         }
  //       }, 2000)
  //     } else if (code === STATUS.LOGIN_FAIL) {
  //       message.error('账号或密码错误')
  //     } else if (code === STATUS.LOGIN_BAN) {
  //       message.error('账号异常，请联系管理员')
  //     } else if (code === STATUS.UNKNOWN_ERROR) {
  //       message.error('未知错误')
  //     }
  //   })
  //   .catch((err: Record<string, any>) => {
  //     const { status } = err.response
  //     if (status === 400) {
  //       console.log(err.response)
  //       const { message: msg } = err.response.data
  //       message.error(msg)
  //     }
  //   })
}

const onFinishFailed = (errorInfo: any) => {
  console.log('Failed:', errorInfo)
}
const disabled = computed(() => {
  return !(formState.username && formState.password) || loading.value
})
</script>
<style lang="scss" scoped>
.login-container {
  height: 100vh;
  background-color: skyblue;
}

#components-form-demo-normal-login .login-form {
  max-width: 300px;
}

#components-form-demo-normal-login .login-form-forgot {
  float: right;
}

#components-form-demo-normal-login .login-form-button {
  width: 100%;
}

:deep(.ant-card-head-title) {
  text-align: center;
}

.center {
  position: absolute;
  left: 50%;
  top: 50%;
  transform: translate(-50%, -50%);
  overflow: hidden;
  border-radius: 15%;
}

.right {
  position: absolute;
  right: 10px;
  top: 5px;
  display: flex;
  align-items: center;
}

.right:hover {
  color: red;
}

:deep(.ant-form-item-control-input-content) {
  position: relative;
}
</style>
