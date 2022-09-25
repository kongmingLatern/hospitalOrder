<template>
  <div class="index-container">
    <Header />
    <Carousel />
    <Content />
  </div>
</template>

<script setup lang='ts'>
import Header from '@/views/index/Header.vue'
import Carousel from '@/views/index/Carousel.vue'
import Content from '../views/index/Content.vue'
</script>

<style scoped lang="scss">
.index-container {
  width: 100%;
}
</style>
