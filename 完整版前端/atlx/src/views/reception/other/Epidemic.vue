<template>

    <div id="chart" style="width: 1000px;height: 520px;background-color: #fff"></div>

</template>

<script>
import echarts from "echarts"
import 'echarts/map/js/china'
import jsonp from "jsonp"

let option={
  title:{
    text:'安途旅行疫情地图',
    show:true,
    // x轴居中
    x:'center',

  },
  tooltip:{
    //   鼠标移入时的提示信息，  一定要知道的是它和series数据是平级的
    tigger:'item',
    formatter:'地区：{b}省<br />感染人口: {c}'
  },
  series:[
    {
      type: 'map',
      map: 'china',
      data:[],
      label: {
        show: true,
        fontSize: 12,
        color: '#fff'
      },
      zoom:1.25,
      itemStyle:{//地图多边形边框设置
        borderColor:'green'
      },
      emphasis: {//高亮状态下的设置
        label:{
          color:'red',
          fontSize: 15,
        },
        itemStyle:{//地图多边形边框设置
          borderColor:'red'
        }
      }
    }
  ],
  visualMap: {//视觉地图
    type: 'piecewise',
    show: true,
    pieces: [
      {min: 10000},  //不指定max,表示max为无限大(Infinity)
      {min: 1000,max: 9999},
      {min: 100,max: 999},
      {min: 1,max: 99},
      {value: 0}
    ],
    inRange: {
      color:['#00ff73','orangered']
    }
  }
};
export default {
  name: "Epidemic",
  data(){
    return{
      myCharts: ''
    }
  },
  mounted() {
    this.getData();
    this.myCharts = echarts.init(document.getElementById("chart"))
    // myCharts.setOption(option)
  },
  methods:{
    getData(){
      jsonp('http://interface.sina.cn/news/wap/fymap2020_data.d.json?_=1580892522427',(err,res)=>{
        // console.info(res.data)
        var lists = res.data.list.map(item=>{
          return {
            name: item.name,
            value: item.value
          }
        });
        option.series[0].data = lists;
        this.myCharts.setOption(option);
      })
    }
  }
}
</script>

<style scoped>

</style>