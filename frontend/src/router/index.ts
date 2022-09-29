import { createRouter, createWebHistory } from 'vue-router'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      redirect: '/order',
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
      path: '/order',
      name: 'order',
      component: () => import('@/page/Order.vue'),
      children: [
        {
          path: ':roomId',
          name: 'roomId',
          component: () => import('@/views/order/RoomInfo.vue'),
        }
      ]
    }
  ],
})

export default router
