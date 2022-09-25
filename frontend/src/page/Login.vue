<template>
  <div class="login-container">
    <div w-120 m-auto class="center">
      <a-spin :spinning="spinning">
        <a-card :border="false">
          <template #title>
            <h3>Login</h3>
          </template>
          <a-form :model="formState" name="normal_login" class="login-form" @finish="onFinish"
            @finishFailed="onFinishFailed">
            <a-form-item label="Username" name="username"
              :rules="[{ required: true, message: 'Please input your username!' }]">
              <a-input v-model:value="formState.username">
              </a-input>
            </a-form-item>

            <a-form-item label="Password" name="password"
              :rules="[{ required: true, message: 'Please input your password!' }]">
              <a-input-password v-model:value="formState.password">
              </a-input-password>
            </a-form-item>

            <a-form-item>
              <a-form-item name="remember" no-style>
                <a-checkbox v-model:checked="formState.remember">Remember me</a-checkbox>
              </a-form-item>
              <a href="#" float-right>Forgot password</a>
            </a-form-item>

            <a-form-item text-center>
              <a-button :disabled="disabled" type="primary" html-type="submit">
                Log in
              </a-button>
              <router-link to="/regist" class="right">Register now!</router-link>
            </a-form-item>
          </a-form>
        </a-card>
      </a-spin>
    </div>
  </div>
</template>
<script lang="ts" setup>
import type { LoginType } from '@/type';
import { reactive, computed, ref, getCurrentInstance } from 'vue';
import router from '../router';
const formState = reactive<LoginType>({
  username: '',
  password: '',
  remember: true,
});

const spinning = ref<Boolean>(false)
const instance = getCurrentInstance()
const request = (instance?.proxy as any).$request!

const onFinish = (values: any) => {
  console.log('Success:', values);
  request.post('/api/user/Login', values, ((res: Record<string, any>) => {
    console.log(res);
    spinning.value = true
    if (res.data.length === 1) {
      setTimeout(() => {
        router.push('/doctor')
      }, 1000)
    }
  }))
};

const onFinishFailed = (errorInfo: any) => {
  console.log('Failed:', errorInfo);
};
const disabled = computed(() => {
  return !(formState.username && formState.password);
});
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

