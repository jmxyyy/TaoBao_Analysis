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
    const apiRes = await axios.get('http://123.56.20.7:8080/product_stat01');
    dataList.value = apiRes.data;
    initEcharts();
}

function initEcharts(){
    const xList = dataList.value.map(v => v.eventType);
    const yList = dataList.value.map(v => v.total);
    chartInstance = echarts.init(chartDom.value);
    const option = {
        xAxis: {
            data: xList
        },
        yAxis: {},
        series: [
            {
                type: 'bar',
                data: yList,
                label: {
                  show: true,
                  position: 'top',
                  formatter: (params) => {
                    const { dataIndex } = params
                    const productId = "商品编号: " + dataList.value[dataIndex].productId;
                    const total = " 次数: " + dataList.value[dataIndex].total;
                    const res = productId + total;
                    return res;
                  },
                },
            }
        ]
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