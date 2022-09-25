<template>
  <div flex justify-between items-center pl-5 pr-5 h-15 bg-f1e0D9 class="header-container">
    <div font-bold class="font">欢迎来到
      <span color-red>常熟理工学院</span>
      医院官网
    </div>
    <div flex justify-evenly items-center>
      <div w-60 mr-5>
        <a-input-search w-50 v-model:value="value" placeholder="请输入专家姓名" enter-button @search="onSearch" />
      </div>
      <router-link to="/login">登录</router-link>
    </div>
  </div>
</template>

<script setup lang="ts" >
import { ref } from 'vue';
const value = ref<string>('')

const onSearch = (searchValue: string) => {
  console.log('use value', searchValue);
  console.log('or use this.value', value.value);
};
</script>

<style scoped lang="scss">
.header-container {
  background-color: #ECE2D9;
}

.font {
  color: #38992e;
  font-size: 20px;
}
</style>

