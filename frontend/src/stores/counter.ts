import { ref, computed } from 'vue'
import { defineStore } from 'pinia'

export const useCounterStore = defineStore('counter', () => {
  const cancelCount = ref<number>(0)
  function increment() {
    cancelCount.value++
  }

  return { cancelCount, increment }
})
