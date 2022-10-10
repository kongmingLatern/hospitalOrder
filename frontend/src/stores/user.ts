import { defineStore } from 'pinia'

export const useUserInfo = defineStore('user', {
  state: () => ({
    username: '',
  }),
  getters: {
    // 自动将返回类型推断为数字
  },
})