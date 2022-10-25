<template>
  <a-spin :spinning="spinning">
    <a-descriptions
      title="预约信息"
      bordered
      class="desc-container"
      v-for="item in orderList"
      :key="item.uid"
      :column="1"
    >
      <a-descriptions-item label="预约单号">{{ item.orderId }}</a-descriptions-item>
      <a-descriptions-item label="预约时间">{{ item.orderTime }}</a-descriptions-item>
      <a-descriptions-item label="预约医生">{{ item.doctorName }}</a-descriptions-item>
      <a-descriptions-item label="是否取消预约" :span="3">
        <span>{{ item.isCancel ? '是' : '否' }}</span>
        <a-button
          v-if="!item.isCancel"
          type="danger"
          class="position"
          @click="cancelOrder(item.orderId)"
        >
          取消预约
        </a-button>
      </a-descriptions-item>
      <a-descriptions-item label="是否完成">
        <a-badge v-if="item.isFinish" status="processing" text="Loading" />
        <a-badge v-else-if="item.isCancel" status="processing" text="Cancel" />
        <a-badge v-else="item.isFinish" status="processing" text="Finish" />
      </a-descriptions-item>
    </a-descriptions>
  </a-spin>
</template>

<script lang="ts" setup>
import router from '@/router'
import { message, notification } from 'ant-design-vue'
import { getCurrentInstance, reactive, ref } from 'vue'
import { useRoute } from 'vue-router'
import type { OrderListType } from '../../type'

const orderList = reactive<OrderListType[]>([])
const spinning = ref<Boolean>(true)
const instance = getCurrentInstance()
const request = (instance?.proxy as any).$request!
// 根据 orderId 查询预约信息

const { params } = useRoute()

getData()
function getData() {
  request.get('/orders/getByOrderId/' + params.orderId).then((res: Record<string, any>) => {
    spinning.value = false
    orderList.push(res.data)
  })
}
const cancelOrder = async (orderId: string) => {
  const res = await request.post('/orders/cancelOrder', {
    orderId,
  })
  const result = await request.get('/users/getByUid/' + localStorage.getItem('uid') ?? '')

  const { cancelCount } = result.data

  if (cancelCount >= 3) {
    message.error('未知错误，请联系管理员,错误码：1001')
    localStorage.clear()
    router.push('/index')
  } else if (cancelCount === 2) {
    // 提示警告信息
    openNotification()
  }

  message.success(res.msg)
  setTimeout(() => {
    router.go(0)
  }, 3000)
}
const openNotification = () => {
  notification.open({
    message: '警告',
    description: '您已取消两次预约了，再次取消预约您的账号会遭到封禁。',
  })
}
</script>

<style lang="scss" scoped>
.position {
  position: absolute;
  right: 50px;
  top: 20%;
}

:deep(.ant-descriptions-item-content) {
  position: relative;
}
</style>
