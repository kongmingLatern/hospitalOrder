<template>
  <div class="personal-container">
    <!-- 头部 -->
    <Header />
    <!-- Tab 栏 -->
    <NavTab text-center />
  </div>
</template>

<script setup lang='ts'>
import Header from '@/views/personal/Header.vue'
import NavTab from '../views/personal/NavTab.vue';
</script>

<style scoped>
.personal_container {
  width: 1200px;
  margin: 0 auto;
}
</style>
