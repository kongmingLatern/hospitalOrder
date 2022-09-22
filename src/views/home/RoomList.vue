<template>
  <header color-green>
    科室管理
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
          <div v-if="editableData[record.id]" class="editable-cell-input-wrapper">
            <a-input v-model:value="editableData[record.id].name" @pressEnter="save(record.id)" />
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
</template>
<script lang="ts" setup>
import { computed, reactive, ref } from 'vue';
import type { Ref, UnwrapRef } from 'vue';
import { CheckOutlined, EditOutlined } from '@ant-design/icons-vue';
import { cloneDeep } from 'lodash-es';

interface RoomType {
  id: string;
  name: string;
  room: string
}

const columns = [
  {
    title: 'room',
    dataIndex: 'room',
  },
  {
    title: 'name',
    dataIndex: 'name',
    width: '30%',
  },
  {
    title: 'operation',
    dataIndex: 'operation',
  },
];
let dataSource: RoomType[] = reactive([
  {
    id: '0',
    name: '内科负责人 —— 张三',
    room: '内科'
  },
  {
    id: '1',
    name: '外科负责人 —— 李四',
    room: '外科'
  },
]);
const count = computed(() => dataSource.length + 1);
const editableData: UnwrapRef<Record<string, RoomType>> = reactive({});

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
    id: `${count.value}`,
    name: `Edward King ${count.value}`,
    room: '外科'
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
