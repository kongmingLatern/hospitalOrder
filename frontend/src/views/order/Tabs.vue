<template>
  <a-tabs v-model:activeKey="activeKey" type="card" @tabScroll="callback">
    <a-tab-pane v-for="item in tabs" :key="item.rid">
      <template #tab>
        <div @click="goTab(item.rid)">{{item.rname}}</div>
      </template>
      <!-- 展示内容 -->
      <router-view />
    </a-tab-pane>
  </a-tabs>
</template>
<script lang="ts" setup>
import router from '@/router';
import type { TabsProps } from 'ant-design-vue';
import { ref } from 'vue';
const props = defineProps<{
  tabs: {
    rid: string
    rname: string
  }[];
}>();

const activeKey = ref('1')

const goTab = (key: string) => {
  router.push({
    name: 'rid',
    params: {
      rid: key
    }
  })
}

const callback: TabsProps['onTabScroll'] = val => {
  console.log(val);
};
</script>

<style lang='scss' scoped>
.view {
  padding: 10px;
}
</style>

