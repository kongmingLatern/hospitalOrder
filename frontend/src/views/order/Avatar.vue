<template>
  <a-avatar :size="size">
    <template #icon>
      <slot name="icon"></slot>
    </template>
  </a-avatar>
</template>

<script setup lang='ts'>
const props = defineProps<{
  size: string | number
}>()
</script>

<style scoped>

</style>
