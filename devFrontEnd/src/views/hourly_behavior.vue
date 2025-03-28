<script setup>

import {onMounted, ref} from "vue";
import axios from "axios";
import * as echarts from "echarts";

const dataList = ref([]);

// 创建一个响应式引用来保存DOM元素
const chartDom = ref();

// 创建一个对象，保存Echarts实例
let chartInstance = null;

async function initData(){
    const apiRes = await axios.get('http://123.56.20.7:8080/hourly_behavior');
    dataList.value = apiRes.data;
    initEcharts();
}

function initEcharts(){
    chartInstance = echarts.init(chartDom.value);
    const seriesList = [];
    const typeList = ["浏览","收藏","加入购物车","购买"]
    for (let i = 0; i < dataList.value.eventCnt.length; i++) {
        const obj = {
            name: typeList[i],
            data: dataList.value.eventCnt[i],
            type: 'bar',
            stack: 'x'
        }
        seriesList.push(obj);
    }
    const option = {
        xAxis: {
            data: dataList.value.hourList
        },
        tooltip: {},
        yAxis: {},
        series: seriesList,
        legend: {
            data: ['浏览', '收藏', '加入购物车', '购买']
        },

    };
    chartInstance.setOption(option);
}

onMounted(() => {
    initData();
})
</script>

<template>
    <div ref="chartDom" style="width: 1600px;height: 500px"></div>
</template>

<style scoped>

</style>