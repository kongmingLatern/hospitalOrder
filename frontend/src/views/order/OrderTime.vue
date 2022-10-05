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
            <a-button :disabled=" disabled">
              预约
            </a-button>
          </a-popconfirm>
        </span>
      </template>
    </template>
  </a-table>
</template>
<script lang="ts" setup>
import { message } from 'ant-design-vue';
import { getCurrentInstance, reactive, ref, toRaw } from 'vue';
import { getStartEndTime, randomString } from '../../utils';

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
const result: Record<string, any>[] = reactive([])
const disabled = ref<Boolean>(false)
const confirm = (selectId: string) => {
  const uid = localStorage.getItem('uid')
  const time: number = Number(selectId) === 1 ? 8 : 13

  const { doctorId, rid } = props
  result.push({
    orderId: randomString(),
    uid,
    orderTime: getStartEndTime(-1)[0] + time * 60 * 60 * 1000,
    isCancel: 0,
    isFinish: 0,
    rid,
    doctorId,
  })
  disabled.value = true
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
  message.error('已取消预约');
};

</script>


<style lang='scss' scoped>
:deep(.ant-table-cell) {
  padding: 5px;
  text-align: center;
}
</style>