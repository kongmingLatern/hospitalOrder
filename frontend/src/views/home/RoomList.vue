<template>
  <RoomForm @addRoom="add" text-right mb-2 />
  <a-modal v-model:visible="visible" title="Add" ok-text="Create" cancel-text="Cancel" @ok="onOk">
    <a-form ref="formRef" :model="formState" v-bind="layout" userName="nest-messages" @finish="onFinish" flex flex-wrap
      flex-col content-start>
      <a-form-item name="rname" label="rname">
        <a-input v-model:value="formState.rname" />
      </a-form-item>
    </a-form>
  </a-modal>
  <a-spin :spinning="spinning">
    <a-table bordered :data-source="dataSource" :columns="columns">
      <template #bodyCell="{ column, record }">
        <template v-if="column.dataIndex === 'operation'">
          <a-space>
            <a-popconfirm v-if="dataSource.length" title="Sure to delete?" @confirm="onDelete(record.rid)">
              <a-button type="danger">删除</a-button>
            </a-popconfirm>
            <a-button type="primary" @click="changeInfo(record)">修改</a-button>
          </a-space>
        </template>
      </template>
    </a-table>
  </a-spin>
</template>
<script lang="ts" setup>
import { getCurrentInstance, onMounted, reactive, ref, toRaw } from 'vue';
import type { RoomType } from '@/type';
import { message, type FormInstance } from 'ant-design-vue';
import router from '@/router';
import RoomForm from '../../components/home/RoomForm.vue';
import { hasOwnProperty } from '@/utils';

const layout = {
  labelCol: { span: 8 },
  wrapperCol: { span: 16 },
};

const formRef = ref<FormInstance>();

const columns = [
  {
    title: 'rid',
    dataIndex: 'rid',
  },
  {
    title: '科室',
    dataIndex: 'rname',
    width: '30%',
  },
  {
    title: 'operation',
    dataIndex: 'operation',
  },
];
const spinning = ref<Boolean>(true)
const visible = ref<Boolean>(false)
let dataSource: RoomType[] = reactive([]);
const instance = getCurrentInstance()
const request = (instance?.proxy as any).$request!

let formState: RoomType = reactive({
  rid: '',
  rname: ''
})
onMounted(() => {

  request.get('api/room/selectAll').then((res: Record<string, any>) => {
    spinning.value = false
    const lists = res.data
    console.log(res);
    lists.forEach((list: RoomType) => {
      dataSource.push(list)
    })
  }).catch((e: any) => {
    console.log(e.message);
  })
})


const onDelete = (rid: string) => {
  request.get('api/room/delete', {
    params: {
      rid
    }
  }).then((res: Record<string, any>) => {
    const { message: msg } = res.data
    dataSource = dataSource.filter(item => item.rid !== rid)
    message.success(msg)
    setTimeout(() => {
      router.go(0)
    }, 0)
  }).catch((err: Record<string, any>) => {
    message.error(err.message)
  })
};

const add = (formState: RoomType) => {
  dataSource.push(formState)
  console.log(dataSource);
}
const onOk = () => {
  (formRef as any)?.value
    .validateFields()
    .then((values: any) => {
      // TODO: 表单不为空 判断
      request.post('/api/room/change', toRaw(formState)).then((res: any) => {
        message.success(res.data.message)
        setTimeout(() => {
          router.go(0)
        }, 1000)
      }).catch((err: any) => {
        message.error(err.data.message)
      })
      visible.value = false;
    })
    .catch((info: string) => {
      console.log('Validate Failed:', info);
    });
};

const changeInfo = (item: Record<string, any>) => {
  visible.value = true
  for (const key in item) {
    if (hasOwnProperty(formState, key)) {
      formState[key] = item[key]
    }
  }
}
const onFinish = (e: MouseEvent) => {
  console.log(e);
  console.log('onFinish');
}
</script>
<style lang="scss" scoped>
.editable-cell {
  position: relative;

  .editable-cell-input-wrapper,
  .editable-cell-text-wrapper {
    padding-right: 24px;
  }

  .editable-cell-text-wrapper {
    padding: 5px 24px 5px 5px;
  }

  .editable-cell-icon,
  .editable-cell-icon-check {
    position: absolute;
    right: 0;
    width: 20px;
    cursor: pointer;
  }

  .editable-cell-icon {
    margin-top: 4px;
    display: none;
  }

  .editable-cell-icon-check {
    line-height: 28px;
  }

  .editable-cell-icon:hover,
  .editable-cell-icon-check:hover {
    color: #108ee9;
  }

  .editable-add-btn {
    margin-bottom: 8px;
  }
}

.editable-cell:hover .editable-cell-icon {
  display: inline-block;
}
</style>
