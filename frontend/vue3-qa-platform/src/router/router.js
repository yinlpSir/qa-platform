import { createRouter, createWebHistory } from "vue-router";

const router = createRouter({
  history: createWebHistory(),//路由器的工作模式
  routes: [
    {
      path: "/login",
      name: 'login',
      component: () => import("../components/Login.vue"),
    },
    {
      path: "/register",
      component: () => import("../components/Register.vue"),
    },
    {
      path: "/aside",
      component: () => import("../pages/Aside.vue"),
    },
    {
      path: "/",
      component: () => import("../components/User.vue"),
      children: [
        {
          path: "",
          component: () => import("../pages/Main.vue")
        },
        {
          path: "mine",
          component: () => import("../pages/Mys.vue")
        },
        {
          path: "community",
          component: () => import("../pages/Community.vue")
        },
        {
          path: "qwenda",
          component: () => import("../pages/Qwenda.vue"),
        },
        {
          path: "answers/:questionId",
          name: 'answers',
          component: () => import("../pages/answers.vue"),
        },
      ]
    },

  ]
});

import auth from "@/store/authentication"

router.beforeEach((to, from, next) => {

  const pre = auth()

  // localStorage.clear()

  const token = pre.token

  if (token == null && to.path !== '/login' && to.path !== '/register') {
    router.push('/login');
    return;
  }

  next(true)

})


export default router;