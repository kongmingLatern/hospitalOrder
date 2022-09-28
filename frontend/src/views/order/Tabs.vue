<template>
  <a-tabs v-model:activeKey="activeKey" type="card">
    <a-tab-pane v-for="item in tabs" :key="item.key" :tab="item.tab">{{item.content}}</a-tab-pane>
  </a-tabs>
</template>
<script lang="ts" setup>
import { ref } from 'vue';
const props = defineProps<{
  tabs: {
    tab: string;
    key: string;
    content: string
  }[];
}>();
const activeKey = ref('1')
</script>

