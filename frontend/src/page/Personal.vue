<template>
  <div class="personal-container">
    <!-- 头部 -->
    <Header />
    <!-- 个人信息 -->
    <PersonalInfo />
    <!-- 预约单号 -->
    <PersonalOrderList />
  </div>
</template>

<script setup lang='ts'>
import Header from '@/views/personal/Header.vue'
import PersonalInfo from '@/views/personal/PersonalInfo.vue'
import PersonalOrderList from '@/views/personal/PersonalOrderList.vue';
</script>

<style scoped>
.personal_container {
  width: 1200px;
  margin: 0 auto;
}
</style>
