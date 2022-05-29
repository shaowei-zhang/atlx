<template>
  <div style="background-color: #f9fafc;height: 800px;width: 1000px" >
    <el-row :gutter="20">
      <el-col :span="9"  v-for="t in touristInfo">
        <el-image  :src="t.avatar" style="width: 350px;height: 260px;border: 1px solid gainsboro;float: left;border-radius: 3px"></el-image>
      </el-col>
      <el-col :span="10" style="float: right;height: 260px;width:650px;background-color: #e5e9f2 ;border-radius: 3px" v-for="t in touristInfo">
        <ul>
          <li><strong >起点:</strong> &nbsp; <span>{{startName}}</span></li>
          <li><strong>中途点:</strong>&nbsp; <span>{{ t.passName }}</span></li>
          <li><strong>终点:</strong>&nbsp; <span style="white-space:pre-wrap">{{ t.endName }}</span></li>
          <li><strong>描述:</strong>&nbsp; <span style="white-space:pre-wrap;overflow: hidden;text-overflow: ellipsis">{{ t.description }}</span></li>
        </ul>
      </el-col>

    </el-row>

    <div style="margin-top: 10px;">
      <el-button type="success" plain @click="chaxun">起点 &nbsp;<i class="el-icon-right"></i>&nbsp; 中途点</el-button>
      <el-button type="success" plain @click="chaxun1">中途点 &nbsp;<i class="el-icon-right"></i>&nbsp; 终点</el-button>
    </div>
    <div style="margin-top: 10px;">
      <div class="container" id="container" style="height: 250px;width: 500px;"/>
    </div>
  </div>

</template>

<script>
/* eslint-disable */
import loadBMap from '@/assets/js/loadBMap.js'
export default {

  async mounted() {
    this.getTouristInfo();
    this.getLnglat();
    await loadBMap('UohMCbWjIvk1eG65cIIlGbe0BqLKdmcH') // 加载引入BMap
    this.initMap()
    this.chaxun()
    this.chaxun1()
  },
  methods: {

    getTouristInfo() {
      var startName = this.startName;
      this.$axios.post('/touristRoutes/selectOne' ,{startName:startName}).then(res => {
        // console.info(res.data.data[0].lnglat1)
        this.touristInfo = res.data.data
      })
    },
    initMap() {
      var that = this;
      // 1、挂载地图,，enableMapClick:false ：禁用地图默认点击弹框
      this.map = new BMap.Map('container', {enableMapClick: false})//创建地图实例，构造底图时，关闭底图可点功能
      var point = new BMap.Point(113.3324436, 23.1315381)//地图中心点,创建点坐标
      this.map.centerAndZoom(point, 19)//初始化地图，设置中心点坐标和地图级别
      this.map.enableScrollWheelZoom(true);//缩放
      this.map.addControl(new BMap.NavigationControl());

    },

    chaxun() {
      var p1 = new BMap.Point(this.lnglat.lng1,this.lnglat.lat1);//杭州市西湖区龙井路1号
      var p2 = new BMap.Point(this.lnglat.lng2,this.lnglat.lat2);//灵隐寺

      var driving = new BMap.DrivingRoute(this.map, {renderOptions: {map: this.map, autoViewport: true}});
      driving.search(p1, p2);
    },
    getLnglat() {
      var startName = this.startName;
      this.$axios.post('/touristRoutes/selectLnglat', {startName:startName}).then(res => {
        this.lnglat = res.data.data
      })
    },
    chaxun1() {
      var p1 = new BMap.Point(this.lnglat.lng2,this.lnglat.lat2);//灵隐寺
      // var p2 = new BMap.Point(120.071528, 30.27289);//浙江省杭州市西湖区天目山路518号
      var p2 = new BMap.Point(this.lnglat.lng3,this.lnglat.lat3);//浙江省杭州市西湖区天目山路518号
      var driving = new BMap.DrivingRoute(this.map, {renderOptions: {map: this.map, autoViewport: true}});
      driving.search(p1, p2);
    },


  },


  data() {
    return {
      /*
      * 这个是将评论传输到后台
      * */
      startName: this.$route.query.setStartName,
      touristInfo: [],
      map: '', // 地图实例
      mk: '', // Marker实例,标记点
      locationPoint: null,//本地地址
      l1: { // 详细地址经纬度
        lng: 0,
        lat: 0
      },
      l2: { // 详细地址经纬度
        lng: 0,
        lat: 0
      },
      l3: { // 详细地址经纬度
        lng: 0,
        lat: 0
      },
      lnglat:{}

    }


    }


}
</script>

<style scoped>

.el-row{
  width:1000px;
  display: flex;
}
.bg-purple-dark {
  background: #99a9bf;
}
.bg-purple {
  background: #d3dce6;
}
.bg-purple-light {
  background: #e5e9f2;
}
.grid-content {
  border-radius: 4px;
  min-height: 36px;
}
.row-bg {
  padding: 10px 0;
  background-color: #f9fafc;
}

ul{
  list-style: none;
  margin-top:30px;
}
ul li {
  margin-top: 5px;
}
/deep/.anchorBL {
  display: none!important;
}
</style>