<template>
  <a-card title="快捷菜单" style="width: 200px">
    <div>
      <a-button type="primary" @click="$router.push('/personal')">个人中心</a-button>
    </div>
    <div mt-5>
      <a-button type="primary" @click="$router.push('/login')">退出登录</a-button>
    </div>
    <div mt-5 v-if="isAuth">
      <a-button type="danger" @click="$router.push('/doctor')">后台管理</a-button>
    </div>
  </a-card>
</template>

<script setup lang='ts'>
const isAuth = localStorage.getItem('isAuth')
</script>

<style scoped>

</style>
