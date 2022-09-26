<template>
  <div class="regist-container">
    <div w-120 m-auto class="center">
      <a-spin :spinning="spinning">
        <a-card :border="false">
          <template #title>
            <h3>Regist</h3>
          </template>
          <a-form :model="formState" name="normal_login" class="login-form" @finish="onFinish"
            @finishFailed="onFinishFailed">
            <a-form-item label="Username" name="userName">
              <a-input v-model:value="formState.userName">
              </a-input>
            </a-form-item>

            <a-form-item label="Password" name="password">
              <a-input-password v-model:value="formState.password">
              </a-input-password>
            </a-form-item>

            <a-form-item label="Realname" name="realName">
              <a-input v-model:value="formState.realName">
              </a-input>
            </a-form-item>

            <a-form-item label="Age" name="age">
              <a-input v-model:value="formState.age">
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
      </a-spin>
    </div>
  </div>
</template>
<script lang="ts" setup>
import type { RegistType } from '@/type';
import { reactive, computed, getCurrentInstance, ref } from 'vue';
import router from '../router';
const formState = reactive<RegistType>({
  userName: '',
  password: '',
  age: '',
  realName: ''
});
const spinning = ref<Boolean>(false)
const instance = getCurrentInstance()
const request = (instance?.proxy as any).$request!

const onFinish = (values: any) => {
  console.log('Success:', values);
  request.post('api/user/register', values).then((res: Record<string, any>) => {
    if ((res.data as string) === '') {
      spinning.value = true
      setTimeout(() => {
        router.push('/login')
      }, 1000)
    }
  }).catch((e: any) => {
    console.log(e.message);
  })
};

const onFinishFailed = (errorInfo: any) => {
  console.log('Failed:', errorInfo);
};
const disabled = computed(() => {
  return !(formState.userName && formState.password && formState.realName && formState.age);
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

