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
        <span class="exit" @click="exitLogin">退出登录</span>
      </div>
    </a-layout-header>
    <a-layout>
      <a-layout-sider width="200" style="background: #fff">
        <a-menu v-model:selectedKeys="selectedKeys1" mode="inline" :style="{ height: '100%', borderRight: 0 }">
          <a-menu-item key="2">
            <pie-chart-outlined />
            <router-link to="/doctor" ml-2>医生管理</router-link>
          </a-menu-item>
          <a-menu-item key="5">
            <pie-chart-outlined />
            <router-link to="/user" ml-2>用户管理</router-link>
          </a-menu-item>
          <a-menu-item key="3">
            <pie-chart-outlined />
            <router-link to="/room" ml-2>科室管理</router-link>
          </a-menu-item>
          <a-menu-item key="4">
            <pie-chart-outlined />
            <router-link to="/order" ml-2>预约管理</router-link>
          </a-menu-item>
          <a-menu-item key="5">
            <router-link to="/ordermanager">去预约</router-link>
          </a-menu-item>
        </a-menu>
      </a-layout-sider>
      <a-layout style="padding: 0 24px 24px">
        <a-layout-content :style="{ background: '#fff', padding: '24px', margin: 0, minHeight: '280px' }">
          <router-view />
        </a-layout-content>
      </a-layout>
    </a-layout>
  </a-layout>
</template>
<script lang="ts" setup>
import { PieChartOutlined } from '@ant-design/icons-vue';
import { ref } from 'vue';
import { UserOutlined } from '@ant-design/icons-vue';
import router from '@/router';
const selectedKeys1 = ref<string[]>(['2'])
const exitLogin = () => {
  localStorage.clear()
  router.push('/login')
}
</script>
<style lang="scss" scoped>
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
