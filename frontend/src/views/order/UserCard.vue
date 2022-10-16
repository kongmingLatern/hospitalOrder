<template>
  <a-card style="width: 200px">
    <template #title>
      <Avatar size="medium">
        <template #icon>
          <UserOutlined />
        </template>
      </Avatar>
      <span class="ml-2">{{ userInfo.userName }}</span>
    </template>
    <p>姓名：{{ userInfo.realName }}</p>
    <p>年齡：{{ userInfo.age }}</p>
    <p>取消預約次數：{{ userInfo.cancelCount }}</p>
    <p>是否违规：{{ userInfo.isAllow ? '违规' : '正常' }}</p>
  </a-card>
</template>

<script setup lang="ts">
import type { UserType } from '@/type'
import { UserOutlined } from '@ant-design/icons-vue'
import { getCurrentInstance, reactive } from 'vue'
import Avatar from '../../components/order/Avatar.vue'
import { hasOwnProperty } from '../../utils'
let userInfo = reactive<UserType>({
  uid: '',
  userName: '',
  age: undefined,
  password: '',
  realName: '',
  cancelCount: 0,
  isAllow: 0,
  isAuth: 0,
})
const instance = getCurrentInstance()
const request = (instance?.proxy as any).$request!
request
  .get('/users/getByUid/' + localStorage.getItem('uid') ?? '')
  .then((res: Record<string, any>) => {
    console.log(res)
    let key: string
    for (key in res.data.data) {
      if (hasOwnProperty(userInfo, key)) {
        userInfo[key] = res.data.data[key]
      }
    }
  })
</script>

<style scoped>
.ml-2 {
  margin-left: 10px;
}
</style>
