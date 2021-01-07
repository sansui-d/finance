import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/zhuye',
      name: 'zhuye',
      component: resolve => (require(['@/views/home'], resolve)),
      children: [
        { path: '/account', component: resolve => (require(['@/views/account/accountManage'], resolve)) },
        { path: '/debt', component: resolve => (require(['@/views/debt/debtManage'], resolve)) },
        { path: '/income', component: resolve => (require(['@/views/income/incomeManage'], resolve)) },
        { path: '/pay', component: resolve => (require(['@/views/pay/payManage'], resolve)) },
        { path: '/insure', component: resolve => (require(['@/views/insure/insureManage'], resolve)) },
        { path: '/user', component: resolve => (require(['@/views/user/userManage'], resolve)) },
        { path: '/loan', component: resolve => (require(['@/views/loan/loanManage'], resolve)) },
        { path: '/budget', component: resolve => (require(['@/views/budget/budgetManage'], resolve)) },
        { path: '/home',name:"home", component: resolve => (require(['@/views/others/echarts'], resolve)) },
      ]
    },
    {
      path: '/',
      name: 'login',
      component: resolve => (require(['@/views/others/login'], resolve)),
    }
    // {
    //   path: '/index',
    //   name: 'index',
    //   component: resolve => (require(['@/views/index'], resolve)),
    //   children: [
    //     {path: '/user/student', component: resolve => (require(['@/views/user/student'], resolve))},
    //   ]
    // }
  ]
})
