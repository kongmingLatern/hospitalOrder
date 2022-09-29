<template>
  <a-list item-layout="horizontal" :data-source="data" :pagination="pagination">
    <template #renderItem="{ item }">
      <a-list-item>
        <a-list-item-meta>
          <!-- 姓名 -->
          <template #title>
            <a href="https://www.antdv.com/">{{ item.name }}</a>
          </template>
          <!-- 描述 -->
          <template #description class="description">
            <p>{{item.description}}</p>
            <OrderTime />
          </template>
          <!-- 照片 -->
          <template #avatar>
            <Avatar size="large" shape="square">
              <template #icon>
                <img :src="item.img" />
              </template>
            </Avatar>
          </template>
        </a-list-item-meta>
      </a-list-item>
    </template>
  </a-list>
</template>
<script lang="ts" setup>
import { watchEffect } from 'vue';
import { useRoute } from 'vue-router';
import Avatar from './Avatar.vue';
import OrderTime from '@/views/order/OrderTime.vue'
import type { RoomInfoType } from '../../type';

let data: RoomInfoType[] = [
  {
    roomId: 'roomId1',
    name: '医生1',
    description: '视网膜若离、糖尿病视网膜病变、黄斑疾病及眼外伤等眼底病的诊断与手术治疗。视网膜若离、糖尿病视网膜病变、黄斑疾病及眼外伤等眼底病的诊断与手术治疗。视网膜若离、糖尿病视网膜病变、黄斑疾病及眼外伤等眼底病的诊断与手术治疗。',
    img: 'https://guahao.shgh.cn/yygh/UploadFiles/Doctor/000048%E9%83%91%E5%BF%97.jpg'
  },
  {
    roomId: 'roomId1',
    name: '医生1 - roomId1',
    description: '视网膜若离、糖尿病视网膜病变、黄斑疾病及眼外伤等眼底病的诊断与手术治疗。视网膜若离、糖尿病视网膜病变、黄斑疾病及眼外伤等眼底病的诊断与手术治疗。视网膜若离、糖尿病视网膜病变、黄斑疾病及眼外伤等眼底病的诊断与手术治疗。',
    img: 'https://guahao.shgh.cn/yygh/UploadFiles/Doctor/000048%E9%83%91%E5%BF%97.jpg'
  },
  {
    roomId: 'roomId1',
    name: '医生1 - roomId2',
    description: '视网膜若离、糖尿病视网膜病变、黄斑疾病及眼外伤等眼底病的诊断与手术治疗。视网膜若离、糖尿病视网膜病变、黄斑疾病及眼外伤等眼底病的诊断与手术治疗。视网膜若离、糖尿病视网膜病变、黄斑疾病及眼外伤等眼底病的诊断与手术治疗。',
    img: 'https://guahao.shgh.cn/yygh/UploadFiles/Doctor/000048%E9%83%91%E5%BF%97.jpg'
  },
  {
    roomId: 'roomId1',
    name: '医生1 - roomId2',
    description: '视网膜若离、糖尿病视网膜病变、黄斑疾病及眼外伤等眼底病的诊断与手术治疗。视网膜若离、糖尿病视网膜病变、黄斑疾病及眼外伤等眼底病的诊断与手术治疗。视网膜若离、糖尿病视网膜病变、黄斑疾病及眼外伤等眼底病的诊断与手术治疗。',
    img: 'https://guahao.shgh.cn/yygh/UploadFiles/Doctor/000048%E9%83%91%E5%BF%97.jpg'
  },
  {
    roomId: 'roomId1',
    name: '医生1 - roomId2',
    description: '视网膜若离、糖尿病视网膜病变、黄斑疾病及眼外伤等眼底病的诊断与手术治疗。视网膜若离、糖尿病视网膜病变、黄斑疾病及眼外伤等眼底病的诊断与手术治疗。视网膜若离、糖尿病视网膜病变、黄斑疾病及眼外伤等眼底病的诊断与手术治疗。',
    img: 'https://guahao.shgh.cn/yygh/UploadFiles/Doctor/000048%E9%83%91%E5%BF%97.jpg'
  },
  {
    roomId: 'roomId1',
    name: '医生1 - roomId2',
    description: '视网膜若离、糖尿病视网膜病变、黄斑疾病及眼外伤等眼底病的诊断与手术治疗。视网膜若离、糖尿病视网膜病变、黄斑疾病及眼外伤等眼底病的诊断与手术治疗。视网膜若离、糖尿病视网膜病变、黄斑疾病及眼外伤等眼底病的诊断与手术治疗。',
    img: 'https://guahao.shgh.cn/yygh/UploadFiles/Doctor/000048%E9%83%91%E5%BF%97.jpg'
  },
  {
    roomId: 'roomId1',
    name: '医生1 - roomId2',
    description: '视网膜若离、糖尿病视网膜病变、黄斑疾病及眼外伤等眼底病的诊断与手术治疗。视网膜若离、糖尿病视网膜病变、黄斑疾病及眼外伤等眼底病的诊断与手术治疗。视网膜若离、糖尿病视网膜病变、黄斑疾病及眼外伤等眼底病的诊断与手术治疗。',
    img: 'https://guahao.shgh.cn/yygh/UploadFiles/Doctor/000048%E9%83%91%E5%BF%97.jpg'
  },
  {
    roomId: 'roomId2',
    name: '郑志2',
    description: '视网膜若离、糖尿病视网膜病变、黄斑疾病及眼外伤等眼底病的诊断与手术治疗。',
    img: 'https://guahao.shgh.cn/yygh/UploadFiles/Doctor/000048%E9%83%91%E5%BF%97.jpg'
  },
  {
    roomId: 'roomId3',
    name: '郑志3',
    description: '视网膜若离、糖尿病视网膜病变、黄斑疾病及眼外伤等眼底病的诊断与手术治疗。',
    img: 'https://guahao.shgh.cn/yygh/UploadFiles/Doctor/000048%E9%83%91%E5%BF%97.jpg'
  },
  {
    roomId: 'roomId3',
    name: '医生1 - roomId3',
    description: '视网膜若离、糖尿病视网膜病变、黄斑疾病及眼外伤等眼底病的诊断与手术治疗。',
    img: 'https://guahao.shgh.cn/yygh/UploadFiles/Doctor/000048%E9%83%91%E5%BF%97.jpg'
  },
  {
    roomId: 'roomId3',
    name: '医生2 - roomId3',
    description: '视网膜若离、糖尿病视网膜病变、黄斑疾病及眼外伤等眼底病的诊断与手术治疗。',
    img: 'https://guahao.shgh.cn/yygh/UploadFiles/Doctor/000048%E9%83%91%E5%BF%97.jpg'
  },
  {
    roomId: 'roomId3',
    name: '医生3 - roomId3',
    description: '视网膜若离、糖尿病视网膜病变、黄斑疾病及眼外伤等眼底病的诊断与手术治疗。',
    img: 'https://guahao.shgh.cn/yygh/UploadFiles/Doctor/000048%E9%83%91%E5%BF%97.jpg'
  }


];
const pagination = {
  onChange: (page: number) => {
    console.log(page);
  },
  pageSize: 5,
};
const { params } = useRoute()
watchEffect(() => {
  console.log(params);
  data = data.filter(item => item.roomId === params.roomId)
})

console.log(data);


</script>


<style lang='scss' scoped >
.ant-list-item {
  overflow: hidden;
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
</style>