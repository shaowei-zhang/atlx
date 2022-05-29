<template>
    <el-form ref="form" :model="form">
      <el-form-item prop="address">
        <el-button type="primary" @click="chaxun">显示路线</el-button>
        <el-autocomplete
            v-model="form.address"
            style="width:90%;margin-left: 15px"
            popper-class="autoAddressClass"
            :fetch-suggestions="querySearchAsync"
            :trigger-on-focus="false"
            placeholder="详细地址"
            clearable
            @select="handleSelect"
        >
          <template slot-scope="{ item }">
            <i class="el-icon-search fl mgr10" />
            <div style="overflow:hidden;">
              <div class="title">{{ item.title }}</div>
              <span class="address ellipsis">{{ item.address }}</span>
            </div>
          </template>
        </el-autocomplete>
      </el-form-item>
      <el-form-item>
        <div id="map-container" style="width:1150px;height:500px;" />
      </el-form-item>
    </el-form>
</template>

<script>
/* eslint-disable */
import loadBMap from '@/assets/js/loadBMap.js'
export default {
  name: "Map",
  data() {
    return {
      form: {
        address: '', // 详细地址
        addrPoint: { // 详细地址经纬度
          lng: 0,
          lat: 0
        }
      },
      map: '', // 地图实例
      mk: '', // Marker实例,标记点
      locationPoint: null,//本地地址
      startAddressName:  { // 起点地址经纬度
        lng: 0,
        lat: 0
      },
      endAddressName:  { // 终点地址经纬度
        lng: 0,
        lat: 0
      },
    }
  },
  async mounted() {
    await loadBMap('UohMCbWjIvk1eG65cIIlGbe0BqLKdmcH') // 加载引入BMap
    this.initMap()
  },
  methods: {//拖动标记、输入地址，调用逆地址解析器(终点地址)
    // 初始化地图
    initMap() {
      var that = this;
      // 1、挂载地图,，enableMapClick:false ：禁用地图默认点击弹框
      this.map = new BMap.Map('map-container', { enableMapClick: false })//创建地图实例，构造底图时，关闭底图可点功能
      var point = new BMap.Point(113.3324436, 23.1315381)//地图中心点,创建点坐标
      this.map.centerAndZoom(point, 19)//初始化地图，设置中心点坐标和地图级别
      this.map.enableScrollWheelZoom(true);//缩放


      // 3、设置图像标注并绑定拖拽标注结束后事件,,dragstart（开始拖拽时触发）、dragging（拖拽过程中触发）和dragend（拖拽结束时触发）
      this.mk = new BMap.Marker(point, { enableDragging: true })
      this.map.addOverlay(this.mk) // 将标注添加到地图中
      //监听
      this.mk.addEventListener('dragend', function(e) {

        that.getAddrByPoint(e.point)//拖拽结束后调用逆地址解析函数，e.point为拖拽后的地理坐标
      })

      // 4、添加（右上角）平移缩放控件
      this.map.addControl(new BMap.NavigationControl({ anchor: BMAP_ANCHOR_TOP_RIGHT, type: BMAP_NAVIGATION_CONTROL_SMALL }))

      // 创建城市选择控件
      var cityControl = new BMap.CityListControl({
        // 控件的停靠位置（可选，默认左上角）
        anchor: BMAP_ANCHOR_TOP_LEFT,
        // 控件基于停靠位置的偏移量（可选）
        offset: new BMap.Size(0, 0)
      });
      // 将控件添加到地图上
      this.map.addControl(cityControl);

      // 5、添加（左下角）定位控件, //创建一个地图定位控件
      var geolocationControl = new BMap.GeolocationControl({ anchor: BMAP_ANCHOR_BOTTOM_LEFT })
      geolocationControl.addEventListener('locationSuccess', function(e) {
        that.getAddrByPoint(e.point)
      })
      geolocationControl.addEventListener('locationError', function(e) {
        alert(e.message)
      })
      this.map.addControl(geolocationControl)//将控件添加到地图

      // 6、浏览器定位
      this.geolocation()

      // 7、绑定点击地图任意点事件
      this.map.addEventListener('click', function(e) {

        that.getAddrByPoint(e.point)

      })

    },
    // // 获取两点间的距离
    // getDistancs(pointA, pointB) {
    //   return this.map.getDistance(pointA, pointB).toFixed(2)
    // },

    // 浏览器定位函数，优先调用浏览器H5定位接口，如果失败会调用IP定位
    geolocation() {
      var that = this
      var geolocation = new BMap.Geolocation()
      //表示调用getCurrentPosition函数成功以后的回调函数，该函数带有一个参数，对象字面量格式，表示获取到的用户位置数据。
      geolocation.getCurrentPosition(function(res) {
        if (this.getStatus() === BMAP_STATUS_SUCCESS) {
          //============================-起点地址====================================
          that.startAddressName = res.point
          that.getAddrByPoint(res.point)
//---------------------------------------------------------------------------
          that.locationPoint = res.point
        } else {
          // alert('failed' + this.getStatus())
          that.locationPoint = new BMap.Point(113.3324436, 23.1315381)
        }
      }, { enableHighAccuracy: true })
    },
    // 2、逆地址解析函数
    getAddrByPoint(point) {
      var that = this
      // 创建地理编码实例
      var geco = new BMap.Geocoder()
      // 根据坐标得到地址描述
      geco.getLocation(point, function(res) {
        console.log(res)
        that.mk.setPosition(point)//重新设置标注的地理坐标
        that.map.panTo(point)//将地图的中心点更改为给定的点
        that.form.address = res.address //记录该点的详细地址信息
        that.form.addrPoint = point //记录当前坐标点
//==================终点地址=====================================
        that.endAddressName = point

      })
    },
    // 8-1、地址搜索
    querySearchAsync(str, cb) {
      var options = {
        onSearchComplete: function(res) {
          var s = []
          if (local.getStatus() === BMAP_STATUS_SUCCESS) {
            for (var i = 0; i < res.getCurrentNumPois(); i++) {
              s.push(res.getPoi(i))
            }
            cb(s)
          } else {
            cb(s)
          }
        }
      }
      var local = new BMap.LocalSearch(this.map, options)//创建LocalSearch构造函数
      local.search(str)//调用search方法，根据检索词str发起检索
    },
    // 8-2、选择地址
    handleSelect(item) {
      this.form.address = item.address + item.title
      this.form.addrPoint = item.point
      this.map.clearOverlays()
      this.mk = new BMap.Marker(item.point)
      this.map.addOverlay(this.mk)
      this.map.panTo(item.point)
      //-------------------------------------------------------------
      this.getAddrByPoint(item.point)

    },
    onSubmit() {
      console.log(this.form);

    },
    onEnd() {
      console.log(this.form);

    },
    chaxun(){
      var p1 = new BMap.Point(this.startAddressName.lng,this.startAddressName.lat);
      var p2 = new BMap.Point(this.endAddressName.lng,this.endAddressName.lat);

      var driving = new BMap.DrivingRoute(this.map, {renderOptions:{map: this.map, autoViewport: true}});
      driving.search(p1, p2);
      // this.startAddressName=this.endAddressName;

    },



  }
}
</script>

<style  scoped>
/deep/.anchorBL {
  display: none!important;
}
</style>

