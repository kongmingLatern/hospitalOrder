<template>
  <a-table :columns="columns" :data-source="data" :pagination="false">
    <template #headerCell="{ column }">
      <template v-if="column.key === 'time'">
        <span>
          Time
        </span>
      </template>
    </template>

    <template #bodyCell="{ column, record }">
      <template v-if="column.key === 'name'">
        <a>
          {{ record.name }}
        </a>
      </template>
      <template v-else-if="column.key === 'action'">
        <span>
          <a-popconfirm title="是否确定预约" ok-text="是" cancel-text="否" @confirm="confirm(record.key)" @cancel="cancel">
            <a href="#">预约</a>
          </a-popconfirm>
        </span>
      </template>
    </template>
  </a-table>
</template>
<script lang="ts" setup>
import { message } from 'ant-design-vue';
import { getCurrentInstance, reactive, toRaw } from 'vue';
import { randomString } from '../../utils';

const props = defineProps<{
  doctorId: string,
  rid: string
}>()

const columns = [
  {
    name: '预约时间',
    dataIndex: 'time',
    key: 'time',
  },
  {
    title: 'Action',
    key: 'action',
  },
];

const data = [
  {
    key: '1',
    time: '8:00 - 12:00',
  },
  {
    key: '2',
    time: '13:00 - 18:00',
  }
];

const instance = getCurrentInstance()
const request = (instance?.proxy as any).$request!
const confirm = (selectId: string) => {
  const result: any[] = reactive([])
  const uid = localStorage.getItem('uid')
  const { doctorId, rid } = props
  result.push({
    orderId: randomString(),
    uid,
    orderTime: Date.now(),
    isCancel: 0,
    isFinish: 0,
    rid,
    doctorId
  })
  // selectId: 预约的时间段
  // 1: 8:00 - 12:00
  // 2: 13:00 - 18:00
  if (selectId === '1') {
    message.success('您预约时间为：8:00 - 12:00');
    // console.log(props.doctorId);
  } else if (selectId === '2') {
    message.success('您预约时间为：13:00 - 18:00');
    // console.log(props.doctorId);
  }
  request.post('api/order/add', toRaw(result)).then((res: Record<string, any>) => {
    message.success('预约成功');
  }).catch((err: string) => {
    message.success('预约失败');
  })
};

const cancel = (e: MouseEvent) => {
  console.log(e);
  message.error('Click on No');
};
</script>


<style lang='scss' scoped>
:deep(.ant-table-cell) {
  padding: 5px;
  text-align: center;
}
</style>