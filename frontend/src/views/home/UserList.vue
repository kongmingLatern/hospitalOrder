<template>
  <Form :visible="visible" @ok="changeStatus" />
  <a-spin :spinning="spinning">
    <header color-green>
      用户管理
    </header>
    <div mb-5>
      <a-button class="editable-add-btn" float-right ml-2 @click="handleAdd">查询</a-button>
      <a-button class="editable-add-btn" float-right ml-2 @click="handleAdd">删除</a-button>
      <a-button class="editable-add-btn" float-right ml-2 @click="handleAdd">添加</a-button>
    </div>
    <a-table bordered :data-source="dataSource" :columns="columns">
      <template #bodyCell="{ column, text, record }">
        <template v-if="column.dataIndex === 'name'">
          <div class="editable-cell">
            <div v-if="editableData[record.uid]" class="editable-cell-input-wrapper">
              <a-input v-model:value="editableData[record.id].userName" @pressEnter="save(record.id)" />
              <check-outlined class="editable-cell-icon-check" @click="save(record.id)" />
            </div>
            <div v-else class="editable-cell-text-wrapper">
              {{ text || ' ' }}
              <edit-outlined class="editable-cell-icon" @click="edit(record.id)" />
            </div>
          </div>
        </template>
        <template v-else-if="column.dataIndex === 'operation'">
          <a-popconfirm v-if="dataSource.length" title="Sure to delete?" @confirm="onDelete(record.id)">
            <a>Delete</a>
          </a-popconfirm>
        </template>
      </template>
    </a-table>
  </a-spin>
</template>
<script lang="ts" setup>
import { computed, getCurrentInstance, onMounted, provide, reactive, ref } from 'vue';
import { CheckOutlined, EditOutlined } from '@ant-design/icons-vue';
import { cloneDeep } from 'lodash-es';
import Form from '@/views/home/Form.vue'

interface UserType {
  uid: string;
  userName: string,
  age: number,
  password: string,
  realName: string,
  cancelCount: number,
  isAllow: number,
  isAuth: number
}

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
const visible = ref<Boolean>(false)

onMounted(() => {
  const instance = getCurrentInstance()
  const request = (instance?.proxy as any).$request!


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

  // axios.get('/room').then(res => {
  //   console.log(res);
  //   res.data.forEach((item: RoomType) => {
  //     dataSource.push(item)
  //   })
  // }).catch(e => {
  //   console.log(e);
  // })
})
const count = computed(() => dataSource.length + 1);
const editableData: Record<string, UserType> = reactive({});

const edit = (uid: string) => {
  editableData[uid] = cloneDeep(dataSource.filter(item => uid === item.uid)[0]);
};
const save = (uid: string) => {
  Object.assign(dataSource.filter(item => uid === item.uid)[0], editableData[uid]);
  delete editableData[uid];
};
const onDelete = (uid: string) => {
  dataSource = dataSource.filter(item => item.uid !== uid);
};
const changeStatus = (status: boolean) => {
  visible.value = status
}
const handleAdd = () => {
  visible.value = true
  const newData: UserType = {
    uid: `${count.value}`,
    userName: `陈楷豪${count.value}`,
    password: '123',
    age: 10,
    realName: '陈楷豪',
    cancelCount: 0,
    isAllow: 0,
    isAuth: 0,
  };
  dataSource.push(newData);
};
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
