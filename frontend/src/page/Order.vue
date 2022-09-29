<template>
  <div class="order-container">
    <aside mr-2>
      <UserCard :userInfo="userInfo" class="card-container" />
      <MenuCard />
    </aside>
    <main mr-2>
      <Search class="search" @search="getValue" />
      <Tabs :tabs="tabs" />
    </main>
    <aside>
      <NoticeCard />
    </aside>
  </div>
</template>

<script lang='ts' setup>
import type { UserType } from '@/type';
import UserCard from '@/views/order/UserCard.vue';
import { reactive } from 'vue';
import Search from '../views/order/Search.vue';
import Tabs from '../views/order/Tabs.vue';
import type { TabType } from '../type';
import NoticeCard from '../views/order/NoticeCard.vue';
import MenuCard from '../views/order/MenuCard.vue';
const userInfo = reactive<UserType>({
  uid: '',
  userName: 'ccccle',
  age: 10,
  password: '',
  realName: '陈楷豪',
  cancelCount: 0,
  isAllow: 0,
  isAuth: 0,
});

const tabs = reactive<TabType[]>([
  {
    tab: '眼科',
    key: '1',
  },
  {
    tab: '骨科',
    key: '2',
  },
  {
    tab: '呼吸科',
    key: '3',
  },
]);
const getValue = (value: string) => {
  console.log('getValue', value);
}
</script>

<style lang='scss'>
.ant-tabs>.ant-tabs-nav,
.ant-tabs>div>.ant-tabs-nav {
  margin: unset;
}

body {
  // background-image: url('https://img2.baidu.com/it/u=2037147439,3680943017&fm=253&fmt=auto&app=138&f=JPG?w=889&h=500');
  // background-repeat: no-repeat;
  // background-size: cover;
  background-color: skyblue;
}

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

.ant-card-head {
  padding: 0 10px;
}
</style>