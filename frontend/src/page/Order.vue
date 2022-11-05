<template>
  <div id="container">
    <div class="order-container">
      <aside mr-2>
        <!-- 用户卡片 -->
        <UserCard class="card-container" />
        <!-- 快捷菜单 -->
        <MenuCard />
      </aside>
      <main mr-2>
        <!-- 搜索 -->
        <Search class="search" @search="getValue" />
        <!-- 预约科室 以及 展示列表 -->
        <Tabs />
      </main>
      <aside>
        <!-- 公告栏 -->
        <NoticeCard />
      </aside>
    </div>
  </div>
</template>

<script lang="ts" setup>
import UserCard from '@/views/order/UserCard.vue'
import Search from '../views/order/Search.vue'
import NoticeCard from '../views/order/NoticeCard.vue'
import Tabs from '../views/order/Tabs.vue'
import MenuCard from '../views/order/MenuCard.vue'
import router from '@/router'

const getValue = (value: string) => {
  // 查询结果
  router.push({
    name: 'doctorSearch',
    query: {
      doctorName: value,
    },
    force: true,
  })
}
</script>

<style lang="scss" scoped>
.ant-tabs > .ant-tabs-nav,
.ant-tabs > div > .ant-tabs-nav {
  margin: unset;
}

#container {
  height: 100%;
  background-image: url('../assets/background.jpg');
  background-size: 100% 100%;
  background-attachment: fixed;
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

.order-container {
  display: flex;
  width: 950px;
  overflow: hidden;
  margin: 0 auto;
  padding-top: 10px;
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
  width: 500px;
  margin-bottom: 20px;
}

.ant-tabs {
  background-color: #fff;
}

.ant-card-head {
  padding: 0 10px;
}

main {
  width: 500px;
}
</style>
