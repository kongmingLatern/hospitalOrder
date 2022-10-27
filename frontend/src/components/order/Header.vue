<template>
  <header>
    <div flex justify-between items-center h-20 bg-green px-4>
      <h2 font-semibold>医生介绍</h2>
      <a-button type="primary" danger @click="$router.go(-1)">返回</a-button>
    </div>
  </header>
</template>

<script setup lang="ts"></script>

<style scoped></style>
