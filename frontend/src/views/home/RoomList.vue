<template>
  <RoomForm @addRoom="add" text-right />
  <a-spin :spinning="spinning">
    <header color-green>
      科室管理
    </header>
    <a-table bordered :data-source="dataSource" :columns="columns">
      <template #bodyCell="{ column, text, record }">
        <template v-if="column.dataIndex === 'name'">
          <div class="editable-cell">
            <div v-if="editableData[record.id]" class="editable-cell-input-wrapper">
              <a-input v-model:value="editableData[record.rid].rname" @pressEnter="save(record.id)" />
              <check-outlined class="editable-cell-icon-check" @click="save(record.rid)" />
            </div>
            <div v-else class="editable-cell-text-wrapper">
              {{ text || ' ' }}
              <edit-outlined class="editable-cell-icon" @click="edit(record.rid)" />
            </div>
          </div>
        </template>
        <template v-else-if="column.dataIndex === 'operation'">
          <a-popconfirm v-if="dataSource.length" title="Sure to delete?" @confirm="onDelete(record.rid)">
            <a>Delete</a>
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
import type { RoomType } from '@/type';
import { message } from 'ant-design-vue';
import router from '@/router';
import RoomForm from './RoomForm.vue';


const columns = [
  {
    title: 'rid',
    dataIndex: 'rid',
  },
  {
    title: 'rname',
    dataIndex: 'rname',
    width: '30%',
  },
  {
    title: 'operation',
    dataIndex: 'operation',
  },
];
const spinning = ref<Boolean>(true)
let dataSource: RoomType[] = reactive([]);
const instance = getCurrentInstance()
const request = (instance?.proxy as any).$request!

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
const editableData: Record<string, RoomType> = reactive({});

const edit = (rid: string) => {
  editableData[rid] = cloneDeep(dataSource.filter(item => rid === item.rid)[0]);
};
const save = (rid: string) => {
  Object.assign(dataSource.filter(item => rid === item.rid)[0], editableData[rid]);
  delete editableData[rid];
};

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
