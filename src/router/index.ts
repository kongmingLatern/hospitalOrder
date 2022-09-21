import { createRouter, createWebHistory } from 'vue-router'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      redirect: '/doctor',
      component: () => import('@/components/home.vue'),
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
      ]
    },
  ],
})

export default router
