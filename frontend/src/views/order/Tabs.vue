<template>
  <a-tabs v-model:activeKey="activeKey" type="card">
    <a-tab-pane v-for="item in tabs" :key="item.key">
      <template #tab>
        <div @click="goTab(item.key)">{{item.tab}}</div>
      </template>
      <!-- 展示内容 -->
      <router-view class="view" />
    </a-tab-pane>
  </a-tabs>
</template>
<script lang="ts" setup>
import router from '@/router';
import { ref } from 'vue';
const props = defineProps<{
  tabs: {
    tab: string
    key: string
  }[];
}>();

const activeKey = ref('1')

const goTab = (key: string) => {
  router.push('/order/roomId' + key)
}
</script>

<style lang='scss' scoped>
.view {
  padding: 10px;
}
</style>

