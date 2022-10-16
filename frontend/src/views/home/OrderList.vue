<template>
  <a-spin :spinning="spinning">
    <a-table bordered :data-source="dataSource" :columns="columns">
      <template #bodyCell="{ column, record }">
        <template v-if="column.dataIndex === 'operation'">
          <a-popconfirm
            v-if="dataSource.length"
            title="Sure to delete?"
            @confirm="onDelete(record.orderId)"
          >
            <a-button type="danger">删除</a-button>
          </a-popconfirm>
        </template>
      </template>
    </a-table>
  </a-spin>
</template>
<script lang="ts" setup>
import { getCurrentInstance, onMounted, reactive, ref } from 'vue'
import type { OrderListType } from '@/type'
import { message } from 'ant-design-vue'
import { formatObject } from '../../utils'
import router from '@/router'

const columns = [
  {
    title: '订单号',
    dataIndex: 'orderId',
  },
  {
    title: '真实姓名',
    dataIndex: 'realName',
  },
  {
    title: '预约医生',
    dataIndex: 'doctorName',
  },
  {
    title: '预约时间',
    dataIndex: 'orderTime',
  },
  {
    title: '预约科室',
    dataIndex: 'rname',
  },
  {
    title: '是否取消',
    dataIndex: 'isCancel',
  },
  {
    title: '是否完成',
    dataIndex: 'isFinish',
  },
  {
    title: 'operation',
    dataIndex: 'operation',
  },
]
const spinning = ref<Boolean>(true)
let dataSource: OrderListType[] = reactive([])
const instance = getCurrentInstance()
const request = (instance?.proxy as any).$request!
onMounted(() => {
  request
    .get('/orders')
    .then((res: Record<string, any>) => {
      console.log(res)
      spinning.value = false
      const lists = res.data.data
      lists.forEach((list: OrderListType) => {
        dataSource.push(formatObject(list) as OrderListType)
      })
    })
    .catch((e: any) => {
      console.log(e.message)
    })
})

const onDelete = (orderId: string) => {
  request
    .delete('/orders/' + orderId)
    .then((res: Record<string, any>) => {
      dataSource = dataSource.filter(item => item.orderId !== orderId)
      message.success('删除成功')
      setTimeout(() => {
        router.go(0)
      }, 0)
    })
    .catch((err: string) => {
      message.error(err)
    })
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
