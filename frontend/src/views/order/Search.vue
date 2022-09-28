<template>
  <a-input-search v-model:value="value" placeholder="請輸入醫生姓名" enter-button @search="onSearch" />
</template>

<script setup lang='ts'>
import { ref } from 'vue';
const value = ref<string>('')
const onSearch = (value: string) => {
  console.log(value);
}
</script>

<style lang="scss" scoped>

</style>