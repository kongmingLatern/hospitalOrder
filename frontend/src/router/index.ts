import { message } from 'ant-design-vue'
import { createRouter, createWebHistory } from 'vue-router'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      redirect: '/index',
      component: () => import('@/page/Home.vue'),
      children: [
        {
          path: 'doctor',
          name: 'doctor',
          component: () => import('@/views/home/DoctorList.vue'),
        },

        {
          path: 'room',
          name: 'room',
          component: () => import('@/views/home/RoomList.vue'),
        },
        {
          path: 'order',
          name: 'order',
          component: () => import('@/views/home/OrderList.vue'),
        },
        {
          path: 'user',
          name: 'user',
          component: () => import('@/views/home/UserList.vue'),
        },
      ]
    },
    {
      path: '/login',
      name: 'login',
      component: () => import('@/page/Login.vue'),
    },
    {
      path: '/regist',
      name: 'regist',
      component: () => import('@/page/Regist.vue'),
    },
    {
      path: '/index',
      name: 'index',
      component: () => import('@/page/Index.vue'),
    },
    {
      path: '/ordermanager',
      name: 'ordermanager',
      component: () => import('@/page/Order.vue'),
      children: [
        {
          path: ':rid',
          name: 'rid',
          component: () => import('@/views/order/RoomInfo.vue'),
        },
        {
          path: 'search/:doctorId',
          name: 'doctorSearch',
          component: () => import('@/views/order/SearchDoctor.vue'),
        }
      ]
    },
    {
      path: '/personal',
      name: 'personal',
      component: () => import('@/page/Personal.vue'),
      children: [
        {
          path: '/:orderId',
          name: 'OrderInfo',
          component: () => import('@/views/personal/OrderInfo.vue'),
        }
      ]
    },
  ],
})

router.beforeEach((to, from, next) => {
  if (to.path === '/login' || to.path === '/regist') {
    next()
  } else {
    const uid = localStorage.getItem('uid')
    if (uid === null || uid === '') {
      message.error('请先登录')
      next('/login')
    } else {
      next()
    }
  }
})

export default router
