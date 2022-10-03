<template>
  <div id="container">
    <div class="order-container">
      <aside mr-2>
        <UserCard class="card-container" />
        <MenuCard />
      </aside>
      <main mr-2>
        <Search class="search" @search="getValue" />
        <Tabs />
      </main>
      <aside>
        <NoticeCard />
      </aside>
    </div>
  </div>
</template>

<script lang='ts' setup>
import UserCard from '@/views/order/UserCard.vue';
import Search from '../views/order/Search.vue';
import NoticeCard from '../views/order/NoticeCard.vue';
import Tabs from '../views/order/Tabs.vue';
import MenuCard from '../views/order/MenuCard.vue';

const getValue = (value: string) => {
  console.log('getValue', value);
}
</script>

<style lang='scss' scoped>
.ant-tabs>.ant-tabs-nav,
.ant-tabs>div>.ant-tabs-nav {
  margin: unset;
}

#container {
  width: 100vw;
  height: auto;
  background-color: skyblue;
}

:deep(.ant-card-body) {
  padding: 10px 0 10px 10px;
}

:deep(.ant-spin-container) {
  padding: unset;
}

:deep(.ant-list-item-meta-title) {
  overflow: hidden; //超出的文本隐藏
  text-overflow: ellipsis; //溢出用省略号显示
  display: -webkit-box;
  -webkit-line-clamp: 1; // 超出多少行
  -webkit-box-orient: vertical;
}

:deep(.ant-list-item-meta-description) {
  font-size: 12px;
  overflow: hidden; //超出的文本隐藏
  text-overflow: ellipsis; //溢出用省略号显示
  display: -webkit-box;
  -webkit-line-clamp: 1; // 超出多少行
  -webkit-box-orient: vertical;
}

// :deep(#app) {
// background-image: url('https://img2.baidu.com/it/u=2037147439,3680943017&fm=253&fmt=auto&app=138&f=JPG?w=889&h=500');
// background-repeat: no-repeat;
// background-size: cover;
// }

.order-container {
  display: flex;
  width: 830px;
  overflow: hidden;
  margin: 0 auto;
  padding-top: 10px;
}

aside {
  background-color: skyblue;
}

.ant-list {
  background-color: #fff;
}

.card-container {
  margin-bottom: 10px;
}

.ant-spin-container {
  background-color: #fff;
}

.search {
  width: 400px;
  margin-bottom: 20px;
}

.ant-tabs {
  background-color: #fff;
}

.ant-card-head {
  padding: 0 10px;
}
</style>