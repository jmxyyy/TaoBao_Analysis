<template>
    <a-layout style="min-height: 100vh">
        <a-layout-sider v-model:collapsed="collapsed" collapsible>
            <div v-show="!collapsed" class="logo" style="text-align: center;padding: 20px">
                <span style="font-size: 23px;color: white;">数据平台</span>
            </div>
            <a-menu :selectedKeys="[route.path]" theme="dark" mode="inline">
                <a-menu-item v-for="item in menuList" :key="item.path" @click="clickMenu(item)">
                    <pie-chart-outlined />
                    <span>{{item.name}}</span>
                </a-menu-item>
            </a-menu>
        </a-layout-sider>
        <a-layout>
            <a-layout-header style="background: #fff; padding: 0" />
            <a-layout-content style="margin: 20px 16px 0;">
                <div :style="{ padding: '24px', background: '#fff', minHeight: '360px' }">
                    <router-view />
                </div>
            </a-layout-content>
            <a-layout-footer style="text-align: center">
                Ant Design ©2018 Created by Ant UED
            </a-layout-footer>
        </a-layout>
    </a-layout>
</template>

<script lang="ts" setup>
import {
    PieChartOutlined,
} from '@ant-design/icons-vue';
import {onMounted, ref} from 'vue';
import {useRoute, useRouter} from "vue-router";
const collapsed = ref(false);

const route = useRoute();
const router = useRouter();

const menuList = [
    {
        name: '用户行为统计',
        path: '/user_behavior'
    },
    {
        name: '按日期统计',
        path: '/date_stat'
    },
    {
        name: '按小时统计',
        path: '/hourly_behavior'
    },
    {
        name: '浏览次数、收藏次数和加⼊购物⻋次数最多的商品',
        path: '/product_stat01'
    },
    {
        name: '商品销量和收藏统计',
        path: '/product_stat02'
    }
]

const clickMenu = (item) => {
    router.push({path: item.path})
}

</script>
<style scoped>
</style>
