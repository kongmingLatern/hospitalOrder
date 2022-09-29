<template>
  <a-card style="width: 200px">
    <template #title>
      <Avatar size="medium">
        <template #icon>
          <UserOutlined />
        </template>
      </Avatar>
      <span class="ml-2">{{userInfo.userName}}</span>
    </template>
    <p>姓名：{{ userInfo.realName}}</p>
    <p>年齡：{{ userInfo.age}}</p>
    <p>取消預約次數：{{ userInfo.cancelCount}}</p>
    <p>是否违规：{{userInfo.isAllow ? '违规' : '正常'}}</p>
  </a-card>
</template>

<script setup lang='ts'>
import type { UserType } from '@/type';
import { UserOutlined } from '@ant-design/icons-vue';
import Avatar from './Avatar.vue';
const props = defineProps<{
  userInfo: UserType;
}>();
</script>

<style scoped>
.ml-2 {
  margin-left: 10px;
}
</style>
