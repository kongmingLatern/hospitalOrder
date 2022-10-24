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
    loading.value = true
  } catch (err: any) {
    message.error(err)
  }
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
