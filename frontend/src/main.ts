import { createApp } from 'vue'
import Antd from 'ant-design-vue'
import App from './App.vue'
import router from './router'
import axios from '@/api'
import 'uno.css'
import 'ant-design-vue/dist/antd.css'

const app = createApp(App)

app.config.globalProperties.$request = axios

app.use(Antd)
  .use(router)
  .mount('#app')
