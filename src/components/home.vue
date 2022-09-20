<template>
  <a-layout>
    <a-layout-header class="header">
      <h1 color-white bg-blue>医院管理系统</h1>
      <div color-white>
        <a-avatar :size="40" mr-5>
          <template #icon>
            <UserOutlined />
          </template>
        </a-avatar>
        <span ml-2 mr-5>管理员: XXX</span>
        <span class="exit">退出登录</span>
      </div>
    </a-layout-header>
    <a-layout>
      <a-layout-sider width="200" style="background: #fff">
        <a-menu v-model:selectedKeys="selectedKeys2" v-model:openKeys="openKeys" mode="inline"
          :style="{ height: '100%', borderRight: 0 }">
          <a-menu-item key="2">
            <pie-chart-outlined />
            <span>医生管理</span>
          </a-menu-item>
          <a-menu-item key="3">
            <pie-chart-outlined />
            <span>科室管理</span>
          </a-menu-item>
          <a-menu-item key="4">
            <pie-chart-outlined />
            <span>预约管理</span>
          </a-menu-item>
        </a-menu>
      </a-layout-sider>
      <a-layout style="padding: 0 24px 24px">
        <a-layout-content :style="{ background: '#fff', padding: '24px', margin: 0, minHeight: '280px' }">
          <List />
        </a-layout-content>
      </a-layout>
    </a-layout>
  </a-layout>
</template>
<script lang="ts">
import { PieChartOutlined } from '@ant-design/icons-vue';
import { defineComponent, ref } from 'vue';
import { UserOutlined } from '@ant-design/icons-vue';
import List from '@/views/home/List.vue'
export default defineComponent({
  components: {
    PieChartOutlined,
    UserOutlined,
    List
  },
  setup() {
    return {
      selectedKeys1: ref<string[]>(['2']),
      selectedKeys2: ref<string[]>(['1']),
      collapsed: ref<boolean>(false),
      openKeys: ref<string[]>(['sub1']),
    };
  },
});
</script>
<style>
.header {
  display: flex;
  justify-content: space-between;
  background-color: #60A5FA
}

.exit:hover {
  color: red;
  cursor: pointer;
}
</style>
