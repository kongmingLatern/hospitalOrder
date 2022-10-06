<template>
  <UserForm @addUser="add" text-right />
  <a-modal v-model:visible="visible" title="Add" ok-text="Create" cancel-text="Cancel" @ok="onOk">
    <a-form ref="formRef" :model="formState" v-bind="layout" userName="nest-messages"
      :validate-messages="validateMessages" @finish="onFinish" flex flex-wrap flex-col content-start>
      <a-form-item userName="userName" label="Username">
        <a-input v-model:value="formState.userName" />
      </a-form-item>
      <a-form-item name="password" label="Password">
        <a-input v-model:value="formState.password" />
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
  <a-spin :spinning="spinning">
    <a-table bordered :data-source="dataSource" :columns="columns">
      <template #bodyCell="{ column, text, record }">
        <template v-if="column.dataIndex === 'name'">
          <div class="editable-cell">
            <div v-if="editableData[record.uid]" class="editable-cell-input-wrapper">
              <a-input v-model:value="editableData[record.uid].userName" @pressEnter="save(record.uid)" />
              <check-outlined class="editable-cell-icon-check" @click="save(record.uid)" />
            </div>
            <div v-else class="editable-cell-text-wrapper">
              {{ text || ' ' }}
              <edit-outlined class="editable-cell-icon" @click="edit(record.uid)" />
            </div>
          </div>
        </template>
        <template v-else-if="column.dataIndex === 'operation'">
          <a-space>
            <a-popconfirm v-if="dataSource.length" title="Sure to delete?" @confirm="onDelete(record.uid)">
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
import { CheckOutlined, EditOutlined } from '@ant-design/icons-vue';
import { cloneDeep } from 'lodash-es';
import type { UserType } from '@/type';
import UserForm from './UserForm.vue';
import { message, type FormInstance } from 'ant-design-vue';
import { formatObject, hasOwnProperty } from '../../utils';
import router from '@/router';

const columns = [
  {
    title: 'uid',
    dataIndex: 'uid',
  },
  {
    title: '用户名',
    dataIndex: 'userName',
  },
  {
    title: '密码',
    dataIndex: 'password',
  },
  {
    title: '年龄',
    dataIndex: 'age',
  },
  {
    title: '真实姓名',
    dataIndex: 'realName',
  },
  {
    title: '取消次数',
    dataIndex: 'cancelCount',
  },
  {
    title: '是否禁用',
    dataIndex: 'isAllow',
  },
  {
    title: '是否是管理员',
    dataIndex: 'isAuth',
  },
  {
    title: '操作',
    dataIndex: 'operation',
  },
];
const spinning = ref<Boolean>(true)
let dataSource: UserType[] = reactive([]);
const instance = getCurrentInstance()
const request = (instance?.proxy as any).$request!
const visible = ref<Boolean>(false)
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
let formState: UserType = reactive({
  uid: '',
  userName: '',
  age: undefined,
  password: '',
  realName: '',
  isAuth: undefined,
});
onMounted(() => {
  request.get('/api/user/selectAll').then((res: Record<string, any>) => {
    spinning.value = false
    const lists = res.data
    lists.forEach((list: UserType) => {
      dataSource.push(formatObject(list) as UserType)
    })
  }).catch((e: any) => {
    console.log(e.message);
  })
})
const editableData: Record<string, UserType> = reactive({});

const edit = (uid: string) => {
  editableData[uid] = cloneDeep(dataSource.filter(item => uid === item.uid)[0]);
};
const save = (uid: string) => {
  Object.assign(dataSource.filter(item => uid === item.uid)[0], editableData[uid]);
  delete editableData[uid];
};
const onDelete = (uid: string) => {
  request.get('api/user/delete', {
    params: {
      uid
    }
  }).then((res: Record<string, any>) => {
    const { message: msg } = res.data
    dataSource = dataSource.filter(item => item.uid !== uid)
    message.success(msg)
    setTimeout(() => {
      router.go(0)
    }, 0)
  }).catch((err: Record<string, any>) => {
    message.error(err.message)
  })
};

const add = (formState: UserType) => {
  console.log('formState', formState);
  dataSource.push(formatObject(formState) as UserType)
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
const onOk = () => {
  (formRef as any)?.value
    .validateFields()
    .then((values: any) => {
      console.log('Received values of form: ', values);
      console.log('formState: ', toRaw(formState));
      request.post('/api/user/change', toRaw(formState)).then((res: any) => {
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
