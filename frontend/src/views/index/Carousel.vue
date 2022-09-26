<template>
  <a-carousel autoplay>
    <div>
      <img src="@/assets/carousel/1.jpg" alt="">
    </div>
    <div>
      <img src="@/assets/carousel/2.jpg" alt="">
    </div>
    <div>
      <img src="@/assets/carousel/3.jpg" alt="">
    </div>
    <div>
      <img src="@/assets/carousel/4.png" alt="">
    </div>
  </a-carousel>
</template>

<style scoped>
img {
  width: 100%;
  height: 100%;
}

.ant-carousel :deep(.slick-slide) {
  height: 400px;
  overflow: hidden;
}
</style>

