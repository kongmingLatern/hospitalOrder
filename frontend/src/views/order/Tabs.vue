<template>
  <a-tabs v-model:activeKey="activeKey" type="card" @tabScroll="callback">
    <a-tab-pane v-for="item in tabs" :key="item.rid">
      <template #tab>
        <div @click="goTab(item.rid)">{{ item.rname }}</div>
      </template>
      <!-- 展示内容 -->
      <router-view />
    </a-tab-pane>
  </a-tabs>
</template>
<script lang="ts" setup>
import router from '@/router'
import type { TabType } from '@/type'
import type { TabsProps } from 'ant-design-vue'
import { getCurrentInstance, onMounted, reactive, ref } from 'vue'

const instance = getCurrentInstance()
const request = (instance?.proxy as any).$request!
const activeKey = ref<string>('')
const tabs = reactive<TabType[]>([])

onMounted(async () => {
  const res = await request.get('/rooms')
  const lists = res.data.data
  lists.forEach((list: TabType) => {
    tabs.push(list)
  })

  const { rid } = tabs[0] ?? ''
  activeKey.value = rid
  router.push('/ordermanager/' + rid)
})

const goTab = (key: string) => {
  router.push({
    name: 'rid',
    params: {
      rid: key,
    },
  })
}

const callback: TabsProps['onTabScroll'] = val => {
  console.log(val)
}
</script>

<style lang="scss" scoped>
.view {
  padding: 10px;
}
</style>
