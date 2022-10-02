<template>
  <div id="container">
    <div class="order-container">
      <aside mr-2>
        <UserCard class="card-container" />
        <MenuCard />
      </aside>
      <main mr-2>
        <Search class="search" @search="getValue" />
        <a-spin :spinning="spinning">
          <Tabs :tabs="tabs" />
        </a-spin>
      </main>
      <aside>
        <NoticeCard />
      </aside>
    </div>
  </div>
</template>

<script lang='ts' setup>
import type { UserType } from '@/type';
import UserCard from '@/views/order/UserCard.vue';
import { getCurrentInstance, reactive, ref } from 'vue';
import Search from '../views/order/Search.vue';
import Tabs from '../views/order/Tabs.vue';
import type { TabType } from '../type';
import NoticeCard from '../views/order/NoticeCard.vue';
import MenuCard from '../views/order/MenuCard.vue';

const instance = getCurrentInstance()
const request = (instance?.proxy as any).$request!


const tabs = reactive<TabType[]>([]);
const spinning = ref<Boolean>(false);


request.get('api/room/selectAll').then((res: Record<string, any>) => {
  spinning.value = false
  console.log(res);
  const lists = res.data
  lists.forEach((list: TabType) => {
    tabs.push(list)
  })
}).catch((e: any) => {
  console.log(e.message);
})
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