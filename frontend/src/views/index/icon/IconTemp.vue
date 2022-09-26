<template>
  <div class="box">
    <slot name="svg"></slot>
    <slot name="desc"></slot>
  </div>
</template>

<script setup lang='ts'>

</script>

<style scoped lang="scss">
.box {
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 70px;
  height: 100px;
}
</style>
