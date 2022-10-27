<template>
  <a-input-search
    v-model:value="value"
    placeholder="请输入医生姓名"
    enter-button
    @search="onSearch"
  />
</template>

<script setup lang="ts">
import { ref } from 'vue'
const value = ref<string>('')
const emit = defineEmits<{
  (event: 'search', value: string): void
}>()
const onSearch = (value: string) => {
  emit('search', value)
}
</script>

<style lang="scss" scoped></style>
