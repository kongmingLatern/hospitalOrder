<template>
  <a-spin :spinning="spinning">
    <header color-green>
      医生管理
    </header>
    <a-table bordered :data-source="dataSource" :columns="columns">
      <template #bodyCell="{ column, text, record }">
        <template v-if="column.dataIndex === 'name'">
          <div class="editable-cell">
            <div v-if="editableData[record.key]" class="editable-cell-input-wrapper">
              <a-input v-model="editableData[record.key].name" @pressEnter="save(record.key)" />
              <check-outlined class="editable-cell-icon-check" @click="save(record.key)" />
            </div>
            <div v-else class="editable-cell-text-wrapper">
              {{ text || ' ' }}
              <edit-outlined class="editable-cell-icon" @click="edit(record.key)" />
            </div>
          </div>
        </template>
        <template v-else-if="column.dataIndex === 'operation'">
          <a-popconfirm v-if="dataSource.length" title="Sure to delete?" @confirm="onDelete(record.key)">
            <a>Delete</a>
          </a-popconfirm>
        </template>
      </template>
    </a-table>
  </a-spin>
</template>
<script lang="ts" setup>
import { computed, getCurrentInstance, onMounted, reactive, ref } from 'vue';
import { CheckOutlined, EditOutlined } from '@ant-design/icons-vue';
import { cloneDeep } from 'lodash-es';
import type { OrderListType } from '@/type';


const columns = [
  {
    title: 'orderId',
    dataIndex: 'orderId',
  },
  {
    title: 'uid',
    dataIndex: 'uid',
  },
  {
    title: 'orderTime',
    dataIndex: 'orderTime',
  },
  {
    title: 'orderRoom',
    dataIndex: 'orderRoom',
  },
  {
    title: 'isCancel',
    dataIndex: 'isCancel',
  },
  {
    title: 'isFinish',
    dataIndex: 'isFinish',
  },
  {
    title: 'operation',
    dataIndex: 'operation',
  },
];
const spinning = ref<Boolean>(true)
let dataSource: OrderListType[] = reactive([]);
onMounted(() => {
  const instance = getCurrentInstance()
  const request = (instance?.proxy as any).$request!

  request.get('api/order/selectAll').then((res: Record<string, any>) => {
    console.log(res);
    spinning.value = false
    const lists = res.data
    lists.forEach((list: OrderListType) => {
      dataSource.push(list)
    })
  }).catch((e: any) => {
    console.log(e.message);
  })

  // request.get('api/doctor/selectAll').then((res: Record<string, any>) => {
  //   // dataSource.push(res.data[0])
  //   const lists = res.data
  //   lists.forEach((list: OrderListType) => {
  //     dataSource.push(list)
  //   })
  // }).catch((e: any) => {
  //   console.log(e.message);
  // })
  // console.log(dataSource);

})
const count = computed(() => dataSource.length + 1);
const editableData: Record<string, OrderListType> = reactive({});

const edit = (id: string) => {
  editableData[id] = cloneDeep(dataSource.filter(item => id === item.id)[0]);
};
const save = (id: string) => {
  Object.assign(dataSource.filter(item => id === item.id)[0], editableData[id]);
  delete editableData[id];
};

const onDelete = (id: string) => {
  dataSource = dataSource.filter(item => item.id !== id);
};
const handleAdd = () => {
  const newData = {
    id: `${count}`,
    name: `Edward King ${count}`,
    age: 32,
    order: '皮肤科',
    dateTime: new Date().toLocaleDateString()
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
