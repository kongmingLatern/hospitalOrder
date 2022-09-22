<template>
  <a-spin :spinning="spinning">
    <header color-green>
      医生管理
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
            <div v-if="editableData[record.key]" class="editable-cell-input-wrapper">
              <a-input v-model:value="editableData[record.key].name" @pressEnter="save(record.key)" />
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

interface DoctorType {
  id: string | number;
  name: string;
  age: number;
  position: string;
  department: string;
}

const columns = [
  {
    title: 'name',
    dataIndex: 'name',
    width: '30%',
  },
  {
    title: 'age',
    dataIndex: 'age',
  },
  {
    title: 'position',
    dataIndex: 'position',
  },
  {
    title: 'department',
    dataIndex: 'department',
  },
  {
    title: 'operation',
    dataIndex: 'operation',
  },
];
const spinning = ref<Boolean>(true)
let dataSource: DoctorType[] = reactive([]);
onMounted(() => {
  const instance = getCurrentInstance()
  const request = (instance?.proxy as any).$request!

  request.get('/doctor').then((res: Record<string, any>) => {
    spinning.value = false
    const lists = res.data
    lists.forEach((list: DoctorType) => {
      dataSource.push(list)
    })
  }).catch((e: any) => {
    console.log(e.message);
  })

})
const count = computed(() => dataSource.length + 1);
const editableData: Record<string, DoctorType> = reactive({});

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
    position: '皮肤科',
    department: '皮肤科'
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
