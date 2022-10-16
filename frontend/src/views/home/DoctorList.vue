<template>
  <DoctorForm @addDoctor="add" text-right />
  <a-modal v-model:visible="visible" title="Add" ok-text="Create" cancel-text="Cancel" @ok="onOk">
    <a-form
      ref="formRef"
      :model="formState"
      v-bind="layout"
      userName="nest-messages"
      :validate-messages="validateMessages"
      @finish="onFinish"
      flex
      flex-wrap
      flex-col
      content-start
    >
      <a-form-item name="userName" label="DoctorName">
        <a-input v-model:value="formState.doctorName" />
      </a-form-item>
      <a-form-item name="age" label="DoctorAge" :rules="[{ type: 'number', min: 0, max: 99 }]">
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
    <a-table bordered :data-source="dataSource" :columns="columns">
      <template #bodyCell="{ column, record }">
        <template v-if="column.dataIndex === 'operation'">
          <a-space>
            <a-popconfirm
              v-if="dataSource.length"
              title="Sure to delete?"
              @confirm="onDelete(record.doctorId)"
            >
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
import { getCurrentInstance, onMounted, reactive, ref, toRaw } from 'vue'
import type { DoctorType } from '@/type'
import DoctorForm from '../../components/home/DoctorForm.vue'
import router from '@/router'
import { message, type FormInstance } from 'ant-design-vue'
import { formatObject, hasOwnProperty } from '../../utils'
const layout = {
  labelCol: { span: 8 },
  wrapperCol: { span: 16 },
}

const validateMessages = {
  required: '${label} is required!',
  number: {
    type: '${label} is not a validate number!',
  },
}
const columns = [
  {
    title: 'doctorId',
    dataIndex: 'doctorId',
  },
  {
    title: '科室',
    dataIndex: 'rname',
  },
  {
    title: '姓名',
    dataIndex: 'doctorName',
  },
  {
    title: '年龄',
    dataIndex: 'doctorAge',
  },
  {
    title: '职位',
    dataIndex: 'position',
  },
  {
    title: '简介',
    dataIndex: 'info',
  },
  {
    title: '限制人数',
    dataIndex: 'limitCount',
  },
  {
    title: '操作',
    dataIndex: 'operation',
  },
]
const visible = ref<Boolean>()
const spinning = ref<Boolean>(true)
let dataSource: DoctorType[] = reactive([])
const instance = getCurrentInstance()
const request = (instance?.proxy as any).$request!
const formRef = ref<FormInstance>()
let formState: DoctorType = reactive({
  doctorId: '',
  doctorName: '',
  doctorAge: undefined,
  rid: '',
  position: '',
  info: '',
  limitCount: undefined,
})
onMounted(() => {
  request
    .get('/doctors')
    .then((res: Record<string, any>) => {
      spinning.value = false
      const lists = res.data.data
      lists.forEach((list: DoctorType) => {
        dataSource.push(list)
      })
    })
    .catch((e: any) => {
      console.log(e.message)
    })
})

const onDelete = (doctorId: string) => {
  request
    .delete('/doctors/' + doctorId)
    .then((res: Record<string, any>) => {
      console.log(res)
      const { message: msg } = res.data
      dataSource = dataSource.filter(item => item.doctorId !== doctorId)
      message.success(msg)
      setTimeout(() => {
        router.go(0)
      }, 0)
    })
    .catch((err: Record<string, any>) => {
      message.error(err.message)
    })
}

const add = (formState: DoctorType) => {
  dataSource.push(formatObject(formState) as DoctorType)
  setTimeout(() => {
    router.go(0)
  }, 0)
}
const onOk = () => {
  ;(formRef as any)?.value
    .validateFields()
    .then((values: any) => {
      request
        .post('/api/doctor/change', toRaw(formState))
        .then((res: any) => {
          message.success(res.data.message)
          setTimeout(() => {
            router.go(0)
          }, 0)
        })
        .catch((err: any) => {
          message.error(err.data.message)
        })
      visible.value = false
    })
    .catch((info: string) => {
      console.log('Validate Failed:', info)
    })
}

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
  console.log(e)
  console.log('onFinish')
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
