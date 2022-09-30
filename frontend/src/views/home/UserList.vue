<template>
  <UserForm @addUser="add" text-right />
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
          <a-popconfirm v-if="dataSource.length" title="Sure to delete?" @confirm="onDelete(record.uid)">
            <a-button>删除</a-button>
          </a-popconfirm>
          <a-popconfirm v-if="dataSource.length" title="Sure to change?" @confirm="onDelete(record.uid)">
            <a-button>修改</a-button>
          </a-popconfirm>
        </template>
      </template>
    </a-table>
  </a-spin>
</template>
<script lang="ts" setup>
import { getCurrentInstance, onMounted, reactive, ref } from 'vue';
import { CheckOutlined, EditOutlined } from '@ant-design/icons-vue';
import { cloneDeep } from 'lodash-es';
import type { UserType } from '@/type';
import UserForm from './UserForm.vue';
import { message } from 'ant-design-vue';
import router from '@/router';

const columns = [
  {
    title: 'uid',
    dataIndex: 'uid',
  },
  {
    title: 'userName',
    dataIndex: 'userName',
  },
  {
    title: 'age',
    dataIndex: 'age',
  },
  {
    title: 'password',
    dataIndex: 'password',
  },
  {
    title: 'realName',
    dataIndex: 'realName',
  },
  {
    title: 'cancelCount',
    dataIndex: 'cancelCount',
  },
  {
    title: 'isAllow',
    dataIndex: 'isAllow',
  },
  {
    title: 'isAuth',
    dataIndex: 'isAuth',
  },
  {
    title: 'operation',
    dataIndex: 'operation',
  },
];
const spinning = ref<Boolean>(true)
let dataSource: UserType[] = reactive([]);
const instance = getCurrentInstance()
const request = (instance?.proxy as any).$request!
onMounted(() => {
  request.get('/api/user/selectAll').then((res: Record<string, any>) => {
    console.log(res.data);
    spinning.value = false
    const lists = res.data
    lists.forEach((list: UserType) => {
      dataSource.push(list)
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
  dataSource.push(formState)
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
