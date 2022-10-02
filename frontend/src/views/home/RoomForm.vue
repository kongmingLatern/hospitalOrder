<template>
  <div>
    <a-button type="primary" @click="visible = true">添加</a-button>
    <a-modal v-model:visible="visible" title="Add" ok-text="Create" cancel-text="Cancel" @ok="onOk">
      <a-form ref="formRef" :model="formState" v-bind="layout" userName="nest-messages" @finish="onFinish" flex
        flex-wrap flex-col content-start>
        <a-form-item name="rname" label="rname">
          <a-input v-model:value="formState.rname" />
        </a-form-item>
      </a-form>
    </a-modal>
  </div>
</template>
<script lang="ts" setup>
import { getCurrentInstance, reactive, ref, toRaw } from 'vue';
import { message, type FormInstance } from 'ant-design-vue';
import { randomString } from '../../utils';
import type { RoomType } from '../../type';

const layout = {
  labelCol: { span: 8 },
  wrapperCol: { span: 16 },
};

const formRef = ref<FormInstance>();
const visible = ref<Boolean>(false);
const instance = getCurrentInstance()
const request = (instance?.proxy as any).$request!

let formState: RoomType = reactive({
  rid: randomString(),
  rname: ''
});

const emit = defineEmits<{
  (msg: string, formState: RoomType): void
}>()

const onOk = () => {
  (formRef as any)?.value
    .validateFields()
    .then((values: any) => {
      // TODO: 表单不为空 判断
      request.post('/api/room/add', toRaw(formState)).then((res: any) => {
        emit('addRoom', toRaw(formState));
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
