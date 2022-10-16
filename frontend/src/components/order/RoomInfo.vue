<template>
  <a-spin :spinning="spinning">
    <a-list item-layout="horizontal" :data-source="data" :pagination="pagination">
      <template #renderItem="{ item }">
        <a-list-item>
          <a-list-item-meta>
            <!-- 姓名 -->
            <template #title>
              <a href="https://www.antdv.com/">{{ item.doctorName }}</a>
            </template>
            <!-- 描述 -->
            <template #description class="description">
              <p>{{ item.info }}</p>
              <OrderTime :doctorId="item.doctorId" :rid="item.rid" />
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
          <template #actions>
            <a-rate :value="randNum(5)" class="rate-size" />
            <span color-red font-semibold class="size">￥{{ randNum(10) * 10 }}</span>
          </template>
        </a-list-item>
      </template>
    </a-list>
  </a-spin>
</template>
<script lang="ts" setup>
import { getCurrentInstance, reactive, ref, watchEffect, computed } from 'vue'
import Avatar from './Avatar.vue'
import OrderTime from '@/components/order/OrderTime.vue'
import type { DoctorType } from '../../type'
import { useRoute } from 'vue-router'

const instance = getCurrentInstance()
const request = (instance?.proxy as any).$request!
const spinning = ref<Boolean>(true)
let data: DoctorType[] = reactive([])
const { params } = useRoute()
watchEffect(() => {
  request
    .get('/doctors/getByRid/', {
      params: {
        rid: params.rid,
      },
    })
    .then((res: Record<string, any>) => {
      console.log(res)
      spinning.value = false
      const lists = res.data.data
      lists.forEach((list: DoctorType) => {
        data.push(list)
      })
    })
    .catch((e: any) => {
      console.log(e.message)
    })
})
const pagination = {
  onChange: (page: number) => {
    console.log(page)
  },
  pageSize: 3,
}

const randNum = (num: number) => {
  return Math.floor(Math.random() * num + 1)
}
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
  width: 90%;
  height: 90%;
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

:deep(.ant-list-item-meta-content) {
  height: 162px;
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
</style>
