<template>
  <a-spin :spinning="spinning">
    <a-descriptions title="预约信息" bordered class="desc-container" v-for="item in orderList" :key="item.uid">
      <a-descriptions-item label="预约单号" :span="3">{{item.orderId}}</a-descriptions-item>
      <a-descriptions-item label="预约时间" :span="3">{{item.orderTime}}</a-descriptions-item>
      <a-descriptions-item label="预约医生" :span="3">{{item.doctorName}}</a-descriptions-item>
      <a-descriptions-item label="是否取消预约" :span="3">{{item.isCancel ? '是' : '否'}}</a-descriptions-item>
      <a-descriptions-item label="是否完成">
        <a-badge v-if="item.isFinish" status="processing" text="Loading" />
        <a-badge v-else="item.isFinish" status="processing" text="Finish" />
      </a-descriptions-item>
    </a-descriptions>
  </a-spin>
</template>

<script lang='ts' setup>
import { getCurrentInstance, reactive, ref } from 'vue';
import type { OrderListType } from '../../type';

const orderList = reactive<OrderListType[]>([])
const spinning = ref<Boolean>(true)
const instance = getCurrentInstance()
const request = (instance?.proxy as any).$request!
// 根据 orderId 查询预约信息
request.get('api/order/selectByUid', {
  params: {
    uid: localStorage.getItem('uid') ?? ''
  }
}).then((res: Record<string, any>) => {
  spinning.value = false
  const lists = res.data
  console.log(res);
  lists.forEach((list: OrderListType) => {
    orderList.push(list)
  })

})


</script>

<style lang='scss' scoped>

</style>