<template>
  <div>
    <el-row :gutter="20">
      <el-col :span="24">
        <div class="showImg" >
          <!--    //轮播图片-->
          <img  @mouseover="changeInterval(true)"
                @mouseleave="changeInterval(false)"
                v-for="(item) in imgArr"
                :key="item.id"
                :src="item.url"
                alt="暂无图片"
                v-show="item.id===currentIndex"
          >
          <!--    //左侧按钮-->
          <div  @click="clickIcon('up')"   class="iconDiv icon-left">
            <i class="el-icon-caret-left"></i>
          </div>
          <!--    //右侧按钮-->
          <div  @click="clickIcon('down')"  class="iconDiv icon-right">
            <i class="el-icon-caret-right"></i>
          </div>
          <!--    //控制圆点-->
          <div class="banner-circle">
            <ul>
              <li @click="changeImg(item.id)"
                  v-for="(item) in imgArr"
                  :key="item.id"
                  :class="item.id===currentIndex? 'active': '' "
              ></li>
            </ul>
          </div>
        </div>
      </el-col>
    </el-row>
    <!--    =====================美食=======================-->
    <el-row>
      <el-col :span="24" >
        <el-card :body-style="{ padding: '0px' }" style="width: 850px;height: 300px">
          <h3 style="margin-bottom: 15px;margin-left: 15px;margin-top: 15px">美食模块</h3>
          <el-divider></el-divider>
          <el-row :gutter="20" >
            <div v-for="(img,index) in foodInfo" v-if="index<4" >
              <el-col :span="6" align="middle" >
                <router-link :to="{name:'foodDetails',query:{setFoodName: img.foodName}}">
                  <img :src="img.foodImg" class="image" >
                </router-link>
                <div style="padding: 4px;">
                  <div class="bottom clearfix">
                    <span style="float: left;margin-left: 17px; ">{{ img.foodName }}</span>
                  </div>
                </div>
              </el-col>
            </div>
          </el-row>
        </el-card>
      </el-col>
    </el-row>
    <!--    ===============================住宿=======================-->
    <el-row>
      <el-col :span="24" >
        <el-card :body-style="{ padding: '0px' }" style="width: 850px;height: 300px">
          <h3 style="margin-bottom: 15px;margin-left: 15px;margin-top: 15px">住宿模块</h3>
          <el-divider></el-divider>
          <el-row :gutter="20" >
            <div v-for="(img,index) in hotelInfo" v-if="index<4">
              <el-col :span="6" align="middle">
                <router-link :to="{name:'hotelDetails',query:{setHotelName: img.hotelName}}">
                  <img :src="img.hotelImg" class="image">
                </router-link>
                <div style="padding: 4px;">
                  <div class="bottom clearfix">
                    <span style="float: left;margin-left: 17px; ">{{ img.hotelName }}</span>
                  </div>
                </div>
              </el-col>
            </div>
          </el-row>
        </el-card>
      </el-col>
    </el-row>
    <!--    =======================景区========================-->
    <el-row>
      <el-col :span="24" >
        <el-card :body-style="{ padding: '0px' }" style="width: 850px;height: 300px">
          <h3 style="margin-bottom: 15px;margin-left: 15px;margin-top: 15px">景区模块</h3>
          <el-divider></el-divider>
          <el-row :gutter="20" >
            <div v-for="(img,index) in ScenicInfo" v-if="index<4">
              <el-col :span="6" align="middle">
                <!--                路由跳转详情页面-->
                <router-link :to="{name:'scenicDetails',query:{setScenicName: img.scenicName}}">
                  <img :src="img.scenicImg" class="image">
                </router-link>
                <div style="padding: 4px;">
                  <div class="bottom clearfix">
                    <span style="float: left;margin-left: 17px; ">{{ img.scenicName }}</span>
                  </div>
                </div>
              </el-col>
            </div>
          </el-row>
        </el-card>
      </el-col>
    </el-row>

  </div>


</template>

<script>
export default {
  name: "Index",
  created() {
    // 轮播图的启动
    this.startInterval()
    this.getFoodInfo();
    this.getHotelInfo();
    this.getScenicInfo();
  },
  data(){
    return {
      /*
      * 存放美食信息的
      * */
      foodInfo:{
        foodImg:'',
        foodName:'',
        foodClass:''
      },

      /*
           * 存放美食信息的
           * 往下依次类推
           * * */
      hotelInfo:{
        hotelName:"",
        hotelImg:"",
      },
      ScenicInfo:{
      },
      /*
      * 卡片的取当前时间
      * */
      currentDate: new Date(),

      /*
      * 轮播图
      * */
      currentIndex :0,//当前所在图片下标
      timer:null,//定时轮询
      imgArr:[
        {	id:0,
          url:"https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F01e28e5543db700000019ae931c302.jpg%401280w_1l_2o_100sh.jpg&refer=http%3A%2F%2Fimg.zcool.cn&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1655644779&t=70445e098152f9c16b4f2f7c7cc2b450",
        },{
          id:1,
          url:"https://img2.baidu.com/it/u=991022146,977047238&fm=253&fmt=auto&app=138&f=JPEG?w=1920&h=400",
        },{
          id:2,
          url:"https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F014c7e5541af5b000001a64b0768b2.jpg%401280w_1l_2o_100sh.png&refer=http%3A%2F%2Fimg.zcool.cn&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1655644779&t=17c9c2e0db3f60714b0b002b8c3157b4",
        },{
          id:3,
          url:"https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F01591e55423b3a0000019ae914c46f.jpg%401280w_1l_2o_100sh.jpg&refer=http%3A%2F%2Fimg.zcool.cn&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1655644779&t=f155c29f0d5d0369d805ee70c00f2fd4",
        },
      ]
    }
  },
  methods:{
    /*
    *获取美食图片
    * */
    getFoodInfo(){
      this.$axios.get('/food/queryAllFoodInfo').then(res=>{
        this.foodInfo=res.data.data
      })
    },


    /*
*获取酒店信息
* */
    getHotelInfo(){
      this.$axios.get('/hotel/hotelList').then(res=>{
        this.hotelInfo=res.data.data
      })
    },

    /*
*获取景区信息
* */
    getScenicInfo(){
      this.$axios.get('/scenic/scenicList').then(res=>{
        this.ScenicInfo=res.data.data
      })
    },

    //开启定时器
    startInterval(){
      // 事件里定时器应该先清除在设置，防止多次点击直接生成多个定时器
      clearInterval(this.timer);
      this.timer = setInterval(()=>{
        this.currentIndex++;
        if(this.currentIndex > this.imgArr.length-1){
          this.currentIndex = 0
        }
      },3000)
    },
    // 点击左右箭头
    clickIcon(val){
      if(val==='down'){
        this.currentIndex++;
        if(this.currentIndex===this.imgArr.length){
          this.currentIndex = 0;
        }
      }else{
        /* 第一种写法
        this.currentIndex--;
        if(this.currentIndex < 0){
          this.currentIndex = this.imgArr.length-1;
        } */
        // 第二种写法
        if(this.currentIndex === 0){
          this.currentIndex = this.imgArr.length;
        }
        this.currentIndex--;
      }
    },
    // 点击控制圆点
    changeImg(index){
      this.currentIndex = index
    },
    //鼠标移入移出控制
    changeInterval(val){
      if(val){
        clearInterval(this.timer)
      }else{
        this.startInterval()
      }
    }
  },
}
</script>

<style scoped>
* {
  padding: 0;
  margin: 0;
}
/* 清除li前面的圆点 */
li {
  list-style-type: none;
}
.showImg{
  position: relative;
  width: 1100px;
  height: 220px;
  /*box-shadow: 5px 5px 5px 5px rgba(0, 0, 0, 0.2);*/
  overflow: hidden;
  /*border-radius: 4px;*/

}
/* 轮播图片 */
.showImg img{
  width: 100%;
  height: 100%;

}

/* 箭头图标 */
.iconDiv{
  position: absolute;
  top: 50%;
  transform: translateY(-50%);
  width: 30px;
  height: 30px;
  /*border: 1px solid #666;*/
  /*border-radius: 15px;*/
  background-color: rgba(125,125,125,.2);
  line-height: 30px;
  text-align: center;
  font-size: 25px;
  cursor: pointer;
}
.iconDiv:hover{
  background-color: white;
}
.icon-left{
  left: 10px;
}
.icon-right{
  right: 10px;
}

/* 控制圆点 */
.banner-circle{
  position: absolute;
  bottom: 0;
  width: 100%;
  height: 20px;
}
.banner-circle ul{
  margin: 0 50px;
  height: 100%;
  text-align: right;
}
.banner-circle ul li{
  display: inline-block;
  width: 14px;
  height: 14px;
  margin: 0 5px;
  /*border-radius: 7px;*/
  /*background-color: rgba(125,125,125,.8);*/
  cursor: pointer;
}
.active{
  background-color: black !important;
}

/*layout布局*/
.el-row {
  margin-bottom: 20px;

}
:last-child {
  margin-bottom: 0;
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


/*卡片*/
.el-card{
  margin:10px auto;

}
.time {
  font-size: 13px;
  color: #999;
}

.bottom {
  margin-top: 13px;
  line-height: 12px;
}

.button {
  padding: 0;
  float: right;
}

.image {
  width: 160px;
  height: 160px;
  display: block;
  border-radius: 3px;
  margin-top: 35px;

}
.image:hover{
  box-shadow: 4px 4px 4px 4px grey;
}


.clearfix:before,
.clearfix:after {
  display: table;
  content: "";
}

.clearfix:after {
  clear: both
}
</style>

