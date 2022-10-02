<template>
  <div class="personal_order_container">
    <a-list bordered :data-source="data" class="left" :pagination="pagination">
      <template #renderItem="{ item }">
        <a-list-item>
          <router-link to="/personal/1">{{ item }}</router-link>
        </a-list-item>
      </template>
      <template #header>
        <div>我的预约单</div>
      </template>
    </a-list>

    <div class="right">
      <router-view />
    </div>
  </div>
</template>

<script setup lang='ts'>
const data: string[] = [
  'Racing car sprays burning fuel into crowd.',
  'Japanese princess to wed commoner.',
  'Australian walks 100km after outback crash.',
  'Man charged over missing wedding girl.',
  'Los Angeles battles huge wildfires.',
  'Racing car sprays burning fuel into crowd.',
  'Japanese princess to wed commoner.',
  'Australian walks 100km after outback crash.',
  'Man charged over missing wedding girl.',
  'Los Angeles battles huge wildfires.',
  'Racing car sprays burning fuel into crowd.',
  'Japanese princess to wed commoner.',
  'Australian walks 100km after outback crash.',
  'Man charged over missing wedding girl.',
  'Los Angeles battles huge wildfires.',
];
const pagination = {
  onChange: (page: number) => {
    console.log(page);
  },
  pageSize: 10,
};
</script>

<style scoped>
.left {
  float: left;
  width: 350px;
  height: 100%;
  height: auto;
}

.personal_order_container {
  width: 1300px;
  height: 600px;
  margin: 20px auto;
}

.right {
  float: right;
  width: 800px;
  height: 100%;
}
</style>