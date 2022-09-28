<template>
  <div class="order-container">
    <aside>
      <UserCard :userInfo="userInfo" class="card-container" />
    </aside>
    <main>
      <Search class="search" />
      <Tabs :tabs="tabs" />
    </main>
    <aside>
      <UserCard :userInfo="userInfo" class="card-container" />
    </aside>
  </div>
</template>

<script lang='ts' setup>
import type { UserType } from '@/type';
import UserCard from '@/views/order/UserCard.vue';
import { reactive } from 'vue';
import Search from '../views/order/Search.vue';
import Tabs from '../views/order/Tabs.vue';
import { TabType } from '../type';
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
    key: 'all',
    content: '眼科醫生'
  },
  {
    tab: '骨科',
    key: 'finish',
    content: '骨科醫生'
  },
  {
    tab: '呼吸科',
    key: 'unfinish',
    content: '呼吸科醫生'
  },
]);
</script>

<style lang='scss' scoped>
.order-container {
  display: flex;
  width: 1000px;
  margin: 0 auto;
  padding: 10px;
}

.search {
  width: 300px;
  margin-bottom: 20px;
}
</style>