<template>
  <a-list bordered :data-source="data" class="list-container">
    <template #renderItem="{ item }">
      <a-list-item>
        <router-link to="/personal/1">{{ item }}</router-link>
      </a-list-item>
    </template>
    <template #header>
      <div>Header</div>
    </template>
    <template #footer>
      <div>Footer</div>
    </template>
  </a-list>

  <router-view />
</template>

<script setup lang='ts'>
const data: string[] = [
  'Racing car sprays burning fuel into crowd.',
  'Japanese princess to wed commoner.',
  'Australian walks 100km after outback crash.',
  'Man charged over missing wedding girl.',
  'Los Angeles battles huge wildfires.',
];
</script>

<style scoped>
.list-container {
  width: 350px;
  height: auto;
  margin: 0 auto;
}
</style>