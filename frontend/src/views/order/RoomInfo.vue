<template>
  <a-list item-layout="horizontal" :data-source="data">
    <template #renderItem="{ item }">
      <a-list-item>
        <a-list-item-meta :description="item.description">
          <template #title>
            <a href="https://www.antdv.com/">{{ item.name }}</a>
          </template>
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

interface DataItem {
  roomId: string
  name: string,
  description: string
  img: string
}
let data: DataItem[] = [
  {
    roomId: 'roomId1',
    name: '医生1',
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
  }
];
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
  height: 140px;
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
  -webkit-line-clamp: 2;
}
</style>