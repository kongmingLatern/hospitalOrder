<template>
  <a-tabs v-model:activeKey="activeKey">
    <a-tab-pane key="1" tab="我的预约单">
      <!-- 我的预约单 -->
      <PersonalOrderList />
    </a-tab-pane>
    <a-tab-pane key="2" tab="个人中心">
      <!-- 个人中心 -->
      <PersonalDesc />
    </a-tab-pane>
  </a-tabs>
</template>
<script lang="ts" setup>
import { ref } from 'vue';
import PersonalDesc from '@/components/personal/PersonalDesc.vue';
import PersonalOrderList from '@/components/personal/PersonalOrderList.vue';
const activeKey = ref('1')
</script>

<style lang='scss' scoped>
:deep(.ant-tabs-nav-wrap) {
  justify-content: center;
}
</style>
