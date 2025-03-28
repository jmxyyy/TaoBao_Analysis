<script setup>

import {onMounted, ref} from "vue";
import axios from "axios";
import * as echarts from "echarts";

const dataList = ref([]);

// 创建一个响应式引用来保存DOM元素
const chartDom = ref();

// 创建一个对象保存Echarts实例
let chartInstance = null;

async function initData(){
    // const apiRes = await axios.get('http://123.56.20.7:8080/user_behavior').then(response => {
    //         console.log(response);
    //         alert(response.data.msg);
    //     }).catch(error => {	
    //         console.log(error);
    //     }
    // );
    const apiRes = await axios.get('http://123.56.20.7:8080/user_behavior');
    dataList.value = apiRes.data;
    initEcharts();
}

function initEcharts(){
    const xList = dataList.value.map(v => v.name);
    const yList = dataList.value.map(v => v.value);
    chartInstance = echarts.init(chartDom.value);
    const option = {
        title: {
            text: '柱状图'
        },
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
                    const productId = dataList.value[dataIndex].productId;
                    return productId;
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
    <div ref="chartDom" style="width: 800px;height: 500px"></div>
</template>

<style scoped>

</style>

<!-- <script setup>

</script>

<template>
123
</template>

<style scoped>

</style> -->