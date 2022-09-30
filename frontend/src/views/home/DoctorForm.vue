<template>
  <div>
    <a-button type="primary" @click="visible = true">添加</a-button>
    <a-modal v-model:visible="visible" title="Add" ok-text="Create" cancel-text="Cancel" @ok="onOk">
      <a-form ref="formRef" :model="formState" v-bind="layout" userName="nest-messages"
        :validate-messages="validateMessages" @finish="onFinish" flex flex-wrap flex-col content-start>
        <a-form-item name="userName" label="DoctorName">
          <a-input v-model:value="formState.doctorName" />
        </a-form-item>
        <a-form-item name="password" label="Position">
          <a-input v-model:value="formState.position" />
        </a-form-item>
        <a-form-item name='age' label="DoctorAge" :rules="[{ type: 'number', min: 0, max: 99 }]">
          <a-input-number v-model:value="formState.doctorAge" />
        </a-form-item>
        <a-form-item name="info" label="Info">
          <a-input v-model:value="formState.info" />
        </a-form-item>
        <a-form-item name="limitCount" label="limitCount">
          <a-input-number v-model:value="formState.limitCount" />
        </a-form-item>
      </a-form>
    </a-modal>
  </div>
</template>
<script lang="ts" setup>
import { getCurrentInstance, reactive, ref, toRaw } from 'vue';
import { message, type FormInstance } from 'ant-design-vue';
import { randomString } from '../../utils';
import type { DoctorType } from '@/type';

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

let formState: DoctorType = reactive({
  doctorId: randomString(),
  doctorName: '',
  doctorAge: undefined,
  rid: randomString(),
  position: '',
  info: '',
  limitCount: undefined
});

const emit = defineEmits<{
  (msg: string, formStaet: DoctorType): void
}>()

const onOk = () => {
  (formRef as any)?.value
    .validateFields()
    .then((values: any) => {
      request.post('/api/doctor/add', toRaw(formState)).then((res: any) => {
        emit('addDoctor', toRaw(formState));
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
