<template>
  <a-descriptions :title="'姓名：' + result.realName" class="desc-container" bordered>
    <a-descriptions-item label="用户名">{{ result.userName}}</a-descriptions-item>
    <a-descriptions-item label="年龄">{{result.age}}</a-descriptions-item>
    <a-descriptions-item label="取消预约次数">{{ result.cancelCount}}</a-descriptions-item>
    <a-descriptions-item label="账号是否禁用">{{!result.isAllow ? '否': '是'}}</a-descriptions-item>
    <a-descriptions-item label="是否是管理员" v-if="result.isAuth">
      管理员
    </a-descriptions-item>
  </a-descriptions>
</template>

<script lang='ts' setup>
import type { UserType } from '@/type';
import { getCurrentInstance, onMounted, reactive } from 'vue';
import { useRoute } from 'vue-router';
const result = reactive<UserType>({
  uid: 'CJPBWmSSemJGcMF76PwM26WpcyhQBKcw',
  userName: '陈楷豪',
  age: 21,
  password: '123456',
  realName: '陈楷豪',
  cancelCount: 0,
  isAllow: 0,
  isAuth: 1,
})
// const { params } = useRoute()

// const instance = getCurrentInstance()
// const request = (instance?.proxy as any).$request!
// onMounted(() => {
  // request.get('api/user/selectByUid', {
  //   params: {
  //     uid: params.uid
  //   }
  // }).then((res: Record<string, any>) => {
  //   console.log(res);
  //   result.push(res.data)
  // }).catch((e: any) => {
  //   console.log(e.message);
  // })
// }),

</script>


<style lang='scss' scoped>
.desc-container {
  width: 1000px;
  margin: 0 auto;
  text-align: center;
  background-color: #c5d7ff;
}

:deep(.ant-descriptions-header) {
  height: 50px;
  margin: unset;
}

:deep(.ant-descriptions-title) {
  font-size: 18px;
}
</style>