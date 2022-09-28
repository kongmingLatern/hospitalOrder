<template>
  <a-card :title="userInfo.userName" style="width: 300px" :bordered="false">
    <p>姓名：{{ userInfo.realName}}</p>
    <p>年齡：{{ userInfo.age}}</p>
    <p>取消預約次數:{{ userInfo.cancelCount}}</p>
    <p>是否违规：{{userInfo.isAllow ? '违规' : '正常'}}</p>
  </a-card>
</template>

<script setup lang='ts'>
import type { UserType } from '@/type';
const props = defineProps<{
  userInfo: UserType;
}>();
</script>

<style scoped>

</style>
