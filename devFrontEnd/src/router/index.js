import {createRouter, createWebHistory} from "vue-router";

const routes = [
    {
        path: '/',
        name: 'index',
        component: () => import("../views/index.vue"),
        children: [
            {
                path: '/user_behavior',
                name: 'user_behavior',
                component: () => import("../views/user_behavior.vue")
            },
            {
                path: '/date_stat',
                name: 'date_stat',
                component: () => import("../views/date_stat.vue")
            },
            {
                path: '/hourly_behavior',
                name: 'hourly_behavior',
                component: () => import("../views/hourly_behavior.vue")
            },
            {
                path: '/product_stat01',
                name: 'product_stat01',
                component: () => import("../views/product/product_stat01.vue")
            },
            {
                path: '/product_stat02',
                name: 'product_stat02',
                component: () => import("../views/product/product_stat02.vue")
            },
        ]
    },

]

const router = createRouter({
    history: createWebHistory(),
    routes
})

export default router
