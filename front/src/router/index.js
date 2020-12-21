import { createRouter, createWebHistory } from 'vue-router'
import Home from '../views/Home.vue'

const routes = [
  {    path: '/', name: 'Home', component: Home  },
  {    path: '/board', name: 'Board',component: () => import(/* webpackChunkName: "about" */ '../views/board/Board.vue')},
  {    path: '/board/:boardSeq', name: 'View', component: () => import('../views/board/View.vue')},
  {    path: '/board/write', name: 'Write', component: () => import('../views/board/Write.vue')},
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    /* which is lazy-loaded when the route is visited.*/
]

const router = createRouter({
  history: createWebHistory(),
  mode: 'history',
  routes
})

export default router
