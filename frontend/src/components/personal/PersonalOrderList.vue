<template>
  <div class="personal_order_container">
    <a-list bordered :data-source="data" class="left" :pagination="pagination">
      <template #renderItem="{ item, index }">
        <a-list-item>
          <span>{{index + 1}} :</span>
          <span @click="getInfo(item.orderId)">{{ item.orderTime }}</span>
          <a-tag v-if="item.isFinish ==='是'" color="#87d068">已完成</a-tag>
          <a-tag v-else-if="item.isCancel === '是'" color="#f50">已取消</a-tag>
          <a-tag v-else color="#108ee9">待完成</a-tag>
        </a-list-item>
      </template>
      <template #header>
        <div>我的预约单</div>
      </template>
    </a-list>
    <div class="right">
      <router-view />
    </div>
  </div>
</template>

<script setup lang='ts'>
import router from '@/router';
import type { OrderListType } from '@/type';
import { formatObject } from '@/utils';
import { getCurrentInstance, onMounted, reactive, ref } from 'vue';

const instance = getCurrentInstance()
const request = (instance?.proxy as any).$request!
const spinning = ref<Boolean>(true)
const data = reactive<OrderListType[]>([])

onMounted(() => {
  request.get('/api/order/selectByUid', {
    params: {
      uid: localStorage.getItem('uid') ?? ''
    }
  }).then((res: Record<string, any>) => {
    spinning.value = false
    const lists = res.data
    lists.forEach((list: OrderListType) => {
      data.push(formatObject(list) as OrderListType)
    })
    console.log(res.data);
  }).catch((e: any) => {
    console.log(e.message);
  })
})
const getInfo = (orderId: string) => {
  console.log(orderId);
  router.push({
    name: 'OrderInfo',
    params: {
      orderId
    }
  })
  setTimeout(() => {
    router.go(0)
  }, 500)
}
const pagination = {
  onChange: (page: number) => {
    console.log(page);
  },
  pageSize: 10,
};
</script>

<style scoped>
.left {
  float: left;
  width: 350px;
  height: 100%;
  height: auto;
}

.personal_order_container {
  width: 1300px;
  /* height: 600px; */
  margin: 20px auto;
}

.right {
  float: right;
  width: 800px;
  height: 100%;
}

span {
  -webkit-user-select: none;
  -moz-user-select: none;
  -ms-user-select: none;
  user-select: none;
  cursor: pointer;
}
</style>