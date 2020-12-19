import { createRouter, createWebHistory } from 'vue-router'
import Home from '../views/Home.vue'

const routes = [
  {    path: '/', name: 'Home', component: Home  },
  {    path: '/board', name: 'Board',component: () => import(/* webpackChunkName: "about" */ '../views/Board.vue')},
  {    path: '/board/write', name: 'Write', component: () => import('../views/Write.vue')}
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
