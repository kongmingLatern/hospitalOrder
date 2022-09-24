<template>
  <div class="regist-container">
    <div w-120 m-auto class="center">
      <a-card :border="false">
        <template #title>
          <h3>Regist</h3>
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

          <a-form-item label="Realname" name="name"
            :rules="[{ required: true, message: 'Please input your real-name' }]">
            <a-input v-model:value="formState.name">
            </a-input>
          </a-form-item>

          <a-form-item label="Age" name="age" :rules="[{ required: true, message: 'Please input your Age' }]">
            <a-input v-model:value="formState.name">
            </a-input>
          </a-form-item>

          <a-form-item text-center>
            <a-button :disabled="disabled" type="primary" html-type="submit">
              Regist
            </a-button>
            <router-link to="/login" class="right">Go Login</router-link>
          </a-form-item>
        </a-form>
      </a-card>
    </div>
  </div>
</template>
<script lang="ts" setup>
import type { RegistType } from '@/type';
import { reactive, computed } from 'vue';
import router from '../router';
const formState = reactive<RegistType>({
  username: '',
  password: '',
  name: ''
});
const onFinish = (values: any) => {
  console.log('Success:', values);
  router.push('/login')
};

const onFinishFailed = (errorInfo: any) => {
  console.log('Failed:', errorInfo);
};
const disabled = computed(() => {
  return !(formState.username && formState.password);
});
</script>
<style lang="scss" scoped>
.regist-container {
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

