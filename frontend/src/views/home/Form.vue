<template>
  <a-modal v-model:visible="visible" title="添加" @ok="handleOk">
    <a-form :model="formState" v-bind="layout" name="nest-messages" :validate-messages="validateMessages"
      @finish="onFinish" flex flex-wrap flex-col content-start>
      <a-form-item :name="['user', 'name']" label="Username">
        <a-input v-model:value="formState.user.name" />
      </a-form-item>
      <a-form-item :name="['user', 'email']" label="Password">
        <a-input v-model:value="formState.user.password" />
      </a-form-item>
      <a-form-item :name="['user', 'age']" label="Age" :rules="[{ type: 'number', min: 0, max: 99 }]">
        <a-input-number v-model:value="formState.user.age" />
      </a-form-item>
      <a-form-item :name="['user', 'realName']" label="realName">
        <a-input v-model:value="formState.user.realName" />
      </a-form-item>
      <a-form-item :name="['user', 'isAuth']" label="IsAuth">
        <a-input v-model:value="formState.user.isAuth" />
      </a-form-item>
    </a-form>
  </a-modal>
</template>
<script lang="ts" setup>
import { inject, reactive, ref, watchEffect } from 'vue';
const props = defineProps<{
  visible: Boolean;
}>();

const emit = defineEmits<{
  (e: 'ok', key: boolean): void,
}>()
watchEffect(() => {
  props.visible
})
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

const formState = reactive({
  user: {
    name: '',
    age: undefined,
    password: '',
    realName: '',
    isAuth: undefined,
  },
});

const handleOk = (e: MouseEvent) => {
  emit('ok', false);
  console.log(formState);
}
const onFinish = (values: any) => {
  console.log('Success:', values);
};

</script>

<style lang="scss" scoped>
.ant-input {
  width: 280px;
}
</style>
