<template>
  <header text-center>
    <div class="p-6">
      <Avatar :size="50">
        <template #icon>
          <UserOutlined />
        </template>
      </Avatar>
      <div mt-2 class="font">
        {{ realName }}
      </div>
      <a-space>
        <a-button type="primary" @click="$router.push('/ordermanager')">预约</a-button>
        <a-button type="danger" @click="exit">退出</a-button>
      </a-space>
    </div>
    <Calander class="position" />
  </header>
</template>

<script setup lang="ts">
import { UserOutlined } from '@ant-design/icons-vue'
import { getCurrentInstance, onMounted, ref } from 'vue'
import router from '@/router'
import Avatar from '../../components/order/Avatar.vue'
import Calander from '../../components/personal/Calander.vue'
import { message } from 'ant-design-vue'
const uid = localStorage.getItem('uid')
const instance = getCurrentInstance()
const request = (instance?.proxy as any).$request!
const realName = ref<string>('')

onMounted(async () => {
  const res = await request.get('/users/getByUid/' + uid)
  console.log(res)
  realName.value = res.data.realName
})

const exit = () => {
  localStorage.clear()
  router.push('/index')
  message.success('退出成功')
}
</script>

<style scoped>
header {
  font-size: 30px;
  height: 150px;
  font-size: 30px;
  padding-left: 20px;
  padding-right: 20px;
  color: #000;
  font-weight: 600;
  margin-bottom: 10px;
  background-image: url('../../assets/background.jpg');
  background-repeat: no-repeat;
  background-size: cover;
}

.p-6 {
  padding: 6px;
}

.font {
  color: white;
  font-size: 18px;
}

.position {
  position: fixed;
  top: 0;
  right: 0;
  z-index: 2;
  text-align: right;
}
</style>
