<template>
  <div>
    <a-button type="primary" @click="visible = true" mb-2>添加用户</a-button>
    <a-modal v-model:visible="visible" title="Add" ok-text="Create" cancel-text="Cancel" @ok="onOk">
      <a-form ref="formRef" :model="formState" v-bind="layout" userName="nest-messages"
        :validate-messages="validateMessages" @finish="onFinish" flex flex-wrap flex-col content-start>
        <a-form-item userName="userName" label="Username">
          <a-input v-model:value="formState.userName" />
        </a-form-item>
        <a-form-item name="password" label="Password">
          <a-input-password v-model:value="formState.password" />
        </a-form-item>
        <a-form-item name='age' label="Age" :rules="[{ type: 'number', min: 0, max: 99 }]">
          <a-input-number v-model:value="formState.age" />
        </a-form-item>
        <a-form-item name="realName" label="realName">
          <a-input v-model:value="formState.realName" />
        </a-form-item>
        <a-form-item name="isAuth" label="IsAuth">
          <a-input-number v-model:value="formState.isAuth" />
        </a-form-item>
      </a-form>
    </a-modal>
  </div>
</template>
<script lang="ts" setup>
import { getCurrentInstance, reactive, ref, toRaw } from 'vue';
import { message, type FormInstance } from 'ant-design-vue';
import { randomString } from '../../utils';
import type { UserType } from '@/type';

const layout = {
  labelCol: { span: 8 },
  wrapperCol: { span: 16 },
};

const validateMessages = {
  required: '${label} is required!',
  number: {
    type: '${label} is not a validate number!',
  }
};
const formRef = ref<FormInstance>();
const visible = ref<Boolean>(false);
const instance = getCurrentInstance()
const request = (instance?.proxy as any).$request!
const formState: UserType = reactive({
  uid: randomString(),
  userName: '',
  age: undefined,
  password: '',
  realName: '',
  isAuth: 0,
  cancelCount: 0,
  isAllow: 0
});

const emit = defineEmits<{
  (msg: string, formState: UserType): void
}>()

const onOk = () => {
  (formRef as any)?.value
    .validateFields()
    .then((values: any) => {
      console.log('Received values of form: ', values);
      console.log('formState: ', toRaw(formState));
      request.post('/api/user/add', toRaw(formState)).then((res: any) => {
        console.log(formState);
        emit('addUser', toRaw(formState));
        message.success(res.data.message)
      }).catch((err: any) => {
        message.error(err.data.message)
      })
      visible.value = false;
    })
    .catch((info: string) => {
      console.log('Validate Failed:', info);
    });
};

const onFinish = (e: MouseEvent) => {
  console.log(e);
  console.log('onFinish');
}

</script>
<style>
.collection-create-form_last-form-item {
  margin-bottom: 0;
}
</style>
