<template>
  <div class="order-container">
    <aside mr-2>
      <UserCard :userInfo="userInfo" class="card-container" />
      <MenuCard />
    </aside>
    <main mr-2>
      <Search class="search" />
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
    content: '眼科醫生'
  },
  {
    tab: '骨科',
    key: '2',
    content: '骨科醫生'
  },
  {
    tab: '呼吸科',
    key: '3',
    content: '呼吸科醫生'
  },
]);
</script>

<style lang='scss' scoped>
.order-container {
  display: flex;
  width: 820px;
  margin: 0 auto;
  padding: 10px;
}

.card-container {
  margin-bottom: 10px;
}

.search {
  width: 400px;
  margin-bottom: 20px;
}
</style>