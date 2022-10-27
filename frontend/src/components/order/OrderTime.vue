<template>
  <a-table :columns="columns" :data-source="data" :pagination="false">
    <template #headerCell="{ column }">
      <template v-if="column.key === 'time'">
        <span>时间</span>
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
          <a-popconfirm
            title="是否确定预约"
            ok-text="是"
            cancel-text="否"
            @confirm="confirm(record.key)"
            @cancel="cancel"
          >
            <a-button type="danger" ghost :disabled="leftCount === 0">预约</a-button>
          </a-popconfirm>
        </span>
      </template>
    </template>
  </a-table>
</template>
<script lang="ts" setup>
import { STATUS } from '@/api/status'
import { message } from 'ant-design-vue'
import { getCurrentInstance, reactive, ref, toRaw } from 'vue'
import { getStartEndTime, randomString } from '../../utils'

const props = defineProps<{
  doctorId: string
  rid: string
  leftCount: number
}>()

const columns = [
  {
    name: '预约时间',
    dataIndex: 'time',
    key: 'time',
  },
  {
    title: '操作',
    key: 'action',
  },
]

const data = [
  {
    key: '1',
    time: '8:00 - 12:00',
  },
  {
    key: '2',
    time: '13:00 - 18:00',
  },
]

const instance = getCurrentInstance()
const request = (instance?.proxy as any).$request!
const result: Record<string, any>[] = reactive([])
const confirm = (selectId: string) => {
  const uid = localStorage.getItem('uid')
  const time: number = Number(selectId) === 1 ? 8 : 13

  const { doctorId, rid, leftCount } = props
  if (leftCount === 0) {
    message.error('当前预约人数已满')
    return
  }
  result.push({
    orderId: randomString(),
    uid,
    orderTime: getStartEndTime(-1)[0] + time * 60 * 60 * 1000,
    isCancel: 0,
    isFinish: 0,
    rid,
    doctorId,
  })
  // selectId: 预约的时间段
  // 1: 8:00 - 12:00
  // 2: 13:00 - 18:00
  if (selectId === '1') {
    // message.success('您预约时间为：8:00 - 12:00');
  } else if (selectId === '2') {
    // message.success('您预约时间为：13:00 - 18:00');
  }

  request
    .post('/orders', toRaw(result[0]))
    .then((res: Record<string, any>) => {
      const { code } = res
      if (code === STATUS.POST_FAIL) {
        message.error(res.msg)
      } else {
        message.success(res.msg)
      }
    })
    .catch((err: string) => {
      message.error('预约失败')
    })
}

const cancel = (e: MouseEvent) => {
  message.success('已取消预约')
}
</script>

<style lang="scss" scoped>
:deep(.ant-table-cell) {
  padding: 5px;
  text-align: center;
}
</style>
