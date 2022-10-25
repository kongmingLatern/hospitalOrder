import { createApp } from 'vue'
import Antd from 'ant-design-vue'
import App from './App.vue'
import router from './router'
import http from '@/api'
import { createPinia } from 'pinia'
import 'uno.css'
import 'ant-design-vue/dist/antd.css'

const app = createApp(App)
const store = createPinia()

app.config.globalProperties.$request = http

app.use(Antd)
  .use(router)
  .use(store)
  .mount('#app')
