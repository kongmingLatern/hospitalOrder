<template>
  <a-avatar :size="size" :shape="shape">
    <template #icon>
      <slot name="icon"></slot>
    </template>
  </a-avatar>
</template>

<script setup lang='ts'>
const props = defineProps<{
  size: string | number,
  shape?: string
}>()
</script>

<style scoped>

</style>
