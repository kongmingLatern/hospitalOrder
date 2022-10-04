<template>
  <DoctorForm @addDoctor="add" text-right />
  <a-modal v-model:visible="visible" title="Add" ok-text="Create" cancel-text="Cancel" @ok="onOk">
    <a-form ref="formRef" :model="formState" v-bind="layout" userName="nest-messages"
      :validate-messages="validateMessages" @finish="onFinish" flex flex-wrap flex-col content-start>
      <a-form-item name="userName" label="DoctorName">
        <a-input v-model:value="formState.doctorName" />
      </a-form-item>
      <a-form-item name='age' label="DoctorAge" :rules="[{ type: 'number', min: 0, max: 99 }]">
        <a-input-number v-model:value="formState.doctorAge" />
      </a-form-item>
      <a-form-item name="position" label="Position">
        <a-input v-model:value="formState.position" />
      </a-form-item>
      <a-form-item name="info" label="Info">
        <a-input v-model:value="formState.info" />
      </a-form-item>
      <a-form-item name="rid" label="rid">
        <a-input v-model:value="formState.rid" />
      </a-form-item>
      <a-form-item name="limitCount" label="limitCount">
        <a-input-number v-model:value="formState.limitCount" />
      </a-form-item>
    </a-form>
  </a-modal>
  <a-spin :spinning="spinning">
    <header color-green>
      医生管理
    </header>
    <a-table bordered :data-source="dataSource" :columns="columns">
      <template #bodyCell="{ column, text, record }">
        <template v-if="column.dataIndex === 'doctorName'">
          <div class="editable-cell">
            <div v-if="editableData[record.key]" class="editable-cell-input-wrapper">
              <a-input v-model:value="editableData[record.key].doctorName" @pressEnter="save(record.key)" />
              <check-outlined class="editable-cell-icon-check" @click="save(record.key)" />
            </div>
            <div v-else class="editable-cell-text-wrapper">
              {{ text || ' ' }}
              <edit-outlined class="editable-cell-icon" @click="edit(record.key)" />
            </div>
          </div>
        </template>
        <template v-else-if="column.dataIndex === 'operation'">
          <a-popconfirm v-if="dataSource.length" title="Sure to delete?" @confirm="onDelete(record.doctorId)">
            <a-button>删除</a-button>
          </a-popconfirm>
          <a-button @click="changeInfo(record)">修改</a-button>
        </template>
      </template>
    </a-table>
  </a-spin>
</template>
<script lang="ts" setup>
import { getCurrentInstance, onMounted, reactive, ref, toRaw } from 'vue';
import { CheckOutlined, EditOutlined } from '@ant-design/icons-vue';
import { cloneDeep } from 'lodash-es';
import type { DoctorType } from '@/type';
import DoctorForm from './DoctorForm.vue';
import router from '@/router';
import { message, type FormInstance } from 'ant-design-vue';
import { formatObject, hasOwnProperty } from '../../utils';
import { emit } from 'process';
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
const columns = [
  {
    title: 'doctorId',
    dataIndex: 'doctorId'
  },
  {
    title: 'rname',
    dataIndex: 'rname',
  },
  {
    title: 'doctorName',
    dataIndex: 'doctorName'
  },
  {
    title: 'doctorAge',
    dataIndex: 'doctorAge',
  },
  {
    title: 'position',
    dataIndex: 'position',
  },
  {
    title: 'info',
    dataIndex: 'info',
  },
  {
    title: 'limitCount',
    dataIndex: 'limitCount',
  },
  {
    title: 'operation',
    dataIndex: 'operation',
  },
];
const visible = ref<Boolean>()
const spinning = ref<Boolean>(true)
let dataSource: DoctorType[] = reactive([]);
const instance = getCurrentInstance()
const request = (instance?.proxy as any).$request!
const formRef = ref<FormInstance>();
let formState: DoctorType = reactive({
  doctorId: '',
  doctorName: '',
  doctorAge: undefined,
  rid: '',
  position: '',
  info: '',
  limitCount: undefined
});
onMounted(() => {
  request.get('api/doctor/selectAll').then((res: Record<string, any>) => {
    spinning.value = false
    const lists = res.data
    lists.forEach((list: DoctorType) => {
      dataSource.push(list)
    })
  }).catch((e: any) => {
    console.log(e.message);
  })
})
const editableData: Record<string, DoctorType> = reactive({});
const edit = (doctorId: string) => {
  editableData[doctorId] = cloneDeep(dataSource.filter(item => doctorId === item.doctorId)[0]);
};
const save = (doctorId: string) => {
  Object.assign(dataSource.filter(item => doctorId === item.doctorId)[0], editableData[doctorId]);
  delete editableData[doctorId];
};

const onDelete = (doctorId: string) => {
  request.get('api/doctor/delete', {
    params: {
      doctorId
    }
  }).then((res: Record<string, any>) => {
    const { message: msg } = res.data
    dataSource = dataSource.filter(item => item.doctorId !== doctorId)
    message.success(msg)
    setTimeout(() => {
      router.go(0)
    }, 0)
  }).catch((err: Record<string, any>) => {
    message.error(err.message)
  })
};

const add = (formState: DoctorType) => {
  dataSource.push(formatObject(formState) as DoctorType)
  setTimeout(() => {
    router.go(0)
  }, 0)
}
const onOk = () => {
  (formRef as any)?.value
    .validateFields()
    .then((values: any) => {
      request.post('/api/doctor/change', toRaw(formState)).then((res: any) => {
        message.success(res.data.message)
        setTimeout(() => {
          router.go(0)
        }, 0)
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
      if (key === 'isAuth') {
        formState[key] = item[key] === '是' ? 1 : 0
      } else {
        formState[key] = item[key]
      }
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
