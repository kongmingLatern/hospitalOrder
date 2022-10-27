<template>
  <Header />
  <a-spin :spinning="spinning">
    <a-list item-layout="horizontal" :data-source="data">
      <template #renderItem="{ item }">
        <a-list-item w-200 my-0 mx-auto>
          <a-list-item-meta>
            <!-- 姓名 -->
            <template #title>
              <div h-10 text-lg>
                姓名：
                <span font-bold>{{ item.doctorName }}</span>
              </div>
            </template>
            <!-- 描述 -->
            <template #description class="description">
              <div flex text-lg h-10>
                <p color-black>职位：</p>
                <span font-bold color-red>{{ item.position }}</span>
              </div>
              <div h-10 text-lg>
                <span color-black>描述：</span>
                <span>{{ item.info }}</span>
              </div>
              <div h-10 text-lg>
                <span color-black>当前预约人数：</span>
                <span>{{ item.limitCount - item.leftCount }}</span>
              </div>
              <div h-10 text-lg>
                <span color-black>剩余预约人数：</span>
                <span>{{ item.leftCount }}</span>
              </div>
              <OrderTime :doctorId="item.doctorId" :rid="item.rid" :leftCount="item.leftCount" />
            </template>
            <!-- 照片 -->
            <template #avatar>
              <Avatar size="large" shape="square">
                <template #icon>
                  <img
                    src="https://guahao.shgh.cn/yygh/UploadFiles/Doctor/000048%E9%83%91%E5%BF%97.jpg"
                  />
                </template>
              </Avatar>
            </template>
          </a-list-item-meta>
        </a-list-item>
      </template>
    </a-list>
  </a-spin>

  <a-tabs v-model:activeKey="activeKey" w-200>
    <a-tab-pane key="1">
      <template #tab>
        <span text-lg>用户评论</span>
      </template>
      <!-- 评论 -->
      <Comment />
    </a-tab-pane>
  </a-tabs>
</template>

<script setup lang="ts">
import Avatar from '../components/order/Avatar.vue'
import OrderTime from '@/components/order/OrderTime.vue'
import type { DoctorType } from '@/type'
import http from '../api'
import Header from '../components/order/Header.vue'
import Comment from '../components/order/Comment.vue'
import { useRoute } from 'vue-router'
import { message } from 'ant-design-vue'
import { onMounted, reactive, ref } from 'vue'

const spinning = ref<boolean>(false)
let data: DoctorType[] = reactive([])
const { query } = useRoute()
const activeKey = ref<string>('1')

onMounted(async () => {
  if (query.doctorName) {
    try {
      const res = await http.get('/doctors/getByDoctorName/' + query.doctorName)
      for (const key in res.data) {
        const element = res.data[key]
        data[key as any] = element
        // message.success('查找成功')
      }
    } catch (e: any) {
      message.error('查找失败')
    }
  } else {
    message.error('查找失败')
  }
})
</script>

<style lang="scss" scoped>
.ant-list-item {
  position: relative;
  overflow: hidden;
  padding: 10px;
}

.ant-list-item-meta {
  height: 100%;
}

.ant-list-item-meta-avatar {
  height: 100%;
}

.ant-avatar-lg.ant-avatar-icon {
  width: 100%;
  height: 100%;
}

.ant-avatar {
  background-color: #fff;
}

:deep(.ant-list-item-meta-avatar) {
  margin-right: 0;
}

:deep(.ant-list-item-meta-description) {
  height: 100%;
}

p {
  text-overflow: -o-ellipsis-lastline;
  overflow: hidden; //溢出内容隐藏
  text-overflow: ellipsis; //文本溢出部分用省略号表示
  display: -webkit-box; //特别显示模式
  -webkit-line-clamp: 2; //行数
  line-clamp: 2;
  -webkit-box-orient: vertical; //盒子中内容竖直排列
}

:deep(.ant-list-item-action) {
  position: absolute;
  top: 10px;
  right: 0;
  display: flex;
  align-items: baseline;
}

.size {
  font-size: 20px;
}

.rate-size {
  font-size: 16px;
}
:deep(.ant-list-item-meta-content) {
  padding-left: 15px;
}
.ant-tabs {
  margin: 0 auto;
}
</style>
