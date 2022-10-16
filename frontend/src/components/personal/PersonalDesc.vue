<template>
  <a-descriptions title="基本信息" class="desc-container" :column="1" bordered>
    <a-descriptions-item label="用户名">{{ result.userName }}</a-descriptions-item>
    <a-descriptions-item label="年龄">{{ result.age }}</a-descriptions-item>
    <a-descriptions-item label="真实姓名">{{ result.realName }}</a-descriptions-item>
    <a-descriptions-item label="取消预约次数">{{ result.cancelCount }}</a-descriptions-item>
    <a-descriptions-item label="账号是否禁用">
      {{ !result.isAllow ? '否' : '是' }}
    </a-descriptions-item>
    <a-descriptions-item label="是否是管理员" v-if="result.isAuth">管理员</a-descriptions-item>
  </a-descriptions>
</template>

<script lang="ts" setup>
import type { UserType } from '@/type'
import { getCurrentInstance, onMounted, reactive } from 'vue'
import { hasOwnProperty } from '../../utils'
const uid = localStorage.getItem('uid')
const result = reactive<UserType>({
  uid: '',
  userName: '',
  password: '',
  age: 0,
  realName: '',
  cancelCount: 0,
  isAllow: 0,
  isAuth: 0,
})
const instance = getCurrentInstance()
const request = (instance?.proxy as any).$request!
onMounted(() => {
  request
    .get('/users/getByUid/' + uid)
    .then((res: Record<string, any>) => {
      console.log(res)
      let key: string
      for (key in res.data.data) {
        if (hasOwnProperty(result, key)) {
          result[key] = res.data.data[key]
        }
      }
    })
    .catch((e: any) => {
      console.log(e.message)
    })
})
</script>

<style lang="scss" scoped>
.desc-container {
  width: 500px;
  margin: 0 auto;
}

:deep(.ant-descriptions-header) {
  height: 50px;
  margin: unset;
}

:deep(.ant-descriptions-title) {
  font-size: 18px;
}

:deep(.ant-descriptions-item-label) {
  width: 170px;
}
</style>
