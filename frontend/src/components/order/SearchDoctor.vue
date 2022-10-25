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
          <!-- <template #actions>
            <a-rate :value="randNum(5)" class="rate-size" />
            <span color-red font-semibold class="size">￥{{ randNum(10) * 10 }}</span>
          </template> -->
        </a-list-item>
      </template>
    </a-list>
  </a-spin>
</template>

<script setup lang="ts">
import Avatar from './Avatar.vue'
import OrderTime from '@/components/order/OrderTime.vue'
import type { DoctorType } from '@/type'
import { reactive, ref } from 'vue'

const spinning = ref<boolean>(false)
let data: DoctorType[] = reactive([])

const pagination = {
  onChange: (page: number) => {
    console.log(page)
  },
  pageSize: 3,
}
</script>

<style scoped></style>
