import { defineStore } from 'pinia'

export const useState = defineStore('tab', {
  state: () => ({
    activeKey: '',
  }),
  getters: {
    // 自动将返回类型推断为数字
  },
})