<template>
  <a-modal v-model:visible="visible" title="添加" @ok="handleOk">
    <Form />
  </a-modal>
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
          <a-popconfirm v-if="dataSource.length" title="Sure to delete?" @confirm="onDelete(record.key)">
            <a-button class="editable-add-btn" @click="handleAdd" mr-5>删除</a-button>
            <a-button class="editable-add-btn" @click="handleAdd">修改</a-button>
          </a-popconfirm>
        </template>
      </template>
    </a-table>
  </a-spin>
</template>
<script lang="ts" setup>
import { computed, getCurrentInstance, onMounted, reactive, ref } from 'vue';
import { CheckOutlined, EditOutlined } from '@ant-design/icons-vue';
import Form from '@/views/home/Form.vue'
import { cloneDeep } from 'lodash-es';

interface DoctorType {
  doctorId: string | number;
  doctorName: string;
  doctorAge: number;
  rid: string,
  position: string;
  info: string,
  limitCount: number
}

const columns = [
  {
    title: 'doctorName',
    dataIndex: 'doctorName'
  },
  {
    title: 'doctorAge',
    dataIndex: 'doctorAge',
  },
  {
    title: 'rid',
    dataIndex: 'rid',
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
const spinning = ref<Boolean>(true)
let dataSource: DoctorType[] = reactive([]);
const visible = ref<Boolean>(false)
onMounted(() => {
  const instance = getCurrentInstance()
  const request = (instance?.proxy as any).$request!

  // request.get('/doctor').then((res: Record<string, any>) => {
  //   spinning.value = false
  //   const lists = res.data
  //   lists.forEach((list: DoctorType) => {
  //     dataSource.push(list)
  //   })
  // }).catch((e: any) => {
  //   console.log(e.message);
  // })
  request.get('api/doctor/selectAll').then((res: Record<string, any>) => {
    spinning.value = false
    const lists = res.data
    console.log(res.data);
    lists.forEach((list: DoctorType) => {
      dataSource.push(list)
    })
  }).catch((e: any) => {
    console.log(e.message);
  })
})
const count = computed(() => dataSource.length + 1);
const editableData: Record<string, DoctorType> = reactive({});
const edit = (doctorId: string) => {
  editableData[doctorId] = cloneDeep(dataSource.filter(item => doctorId === item.doctorId)[0]);
};
const save = (doctorId: string) => {
  Object.assign(dataSource.filter(item => doctorId === item.doctorId)[0], editableData[doctorId]);
  delete editableData[doctorId];
};

const onDelete = (doctorId: string) => {
  dataSource = dataSource.filter(item => item.doctorId !== doctorId);
};
const handleAdd = () => {
  visible.value = true
  // const newData = {
  //   doctorId: `${count}`,
  //   doctorName: `Edward King ${count}`,
  //   doctorAge: 32,
  //   rid: '123',
  //   position: '皮肤科',
  //   info: '简单的介绍',
  //   limitCount: 10,
  // };
  // dataSource.push(newData);
};
const handleOk = (e: MouseEvent) => {
  visible.value = false
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
