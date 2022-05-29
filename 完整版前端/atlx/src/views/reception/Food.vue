<template>
  <div>
    <!-- ====================================轮播图模块=======================================-->
    <el-row>
      <el-col :span="20">
          <!-- 这里放一个轮播图-->
          <div class="showImg" >
            <el-image  @mouseover="changeInterval(true)"
                  @mouseleave="changeInterval(false)"
                  v-for="(item) in imgArr"
                  :key="item.id"
                  :src="item.url"
                  v-show="item.id===currentIndex"
                       style="width: 1100px;height: 200px"
                       fit="none"
            />
<!--            //左侧按钮-->
            <div  @click="clickIcon('up')"   class="iconDiv icon-left">
              <i class="el-icon-caret-left"></i>
            </div>
<!--            //右侧按钮-->
            <div  @click="clickIcon('down')"  class="iconDiv icon-right">
              <i class="el-icon-caret-right"></i>
            </div>
<!--            //控制圆点-->
            <div class="banner-circle">
              <ul>
                <li @click="changeImg(item.id)"
                    v-for="item in imgArr"
                    :key="item.id"
                    :class="item.id===currentIndex? 'active':'' "
                ></li>
              </ul>
            </div>
          </div>
          <!-- 轮播图到这里截止-->
      </el-col>
    </el-row>
    <!--    ====================================搜索模块=======================================-->
    <el-row>
      <el-col :span="7" :offset="17">
        <el-form :inline="true"  class="demo-form-inline">
          <el-form-item >
            <el-input v-model="foodInfo.foodName" placeholder="请输入美食名"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="onSubmit">查询</el-button>
          </el-form-item>
        </el-form>
      </el-col>
    </el-row>

    <!--    ====================================美食列表展示=======================================-->
    <el-row :gutter="20">
      <el-col :span="22">
        <div v-for="f in foodInfo">
          <el-col :span="2" :offset="3"  style="margin-bottom: 30px">
            <router-link :to="{name:'foodDetails',query:{setFoodName: f.foodName}}">
              <el-image :src="f.foodImg" style="width: 180px;height: 150px; border-radius: 4px;"></el-image>
            </router-link>
            <h3>{{f.foodName}}</h3>
          </el-col>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  name: "Food",
  created() {
    this.foodCarousel();
    this.getFoodInfo();

  },
  data(){
    return {
      foodInfo:{},
      currentIndex :0,//当前所在图片下标
      timer:'',//定时轮询
      imgArr: [{id:'', url:'',}, {id:'', url:'',},{id:'', url:'',},{id:'', url:'',},{id:'', url:'',},{id:'', url:'',},{id:'', url:'',},{id:'', url:'',}],
    }
  },
  methods:{
    /*
    * 这个是点击搜索 ，跳转页面
    *
    *我想的是，先将foodName的名字穿过去，
    * 然后，在新的页面接受参数，查询信息，然后再展示
    *
    * */
    onSubmit() {
     var foodName=this.foodInfo.foodName
      //直接跳转
      // this.$router.push({path:'/test',query:{setFoodName:foodName}});
      this.$axios.post('/food/queryByMap',{foodName:foodName}).then(res=>{
        this.foodInfo=res.data.data
      })

    },
    /*
   * 获取美食信息
   *
   * */
    getFoodInfo(){
      this.$axios.get("/food/queryAllFoodInfo").then(res=>{
        console.info(res.data.data)
        this.foodInfo=res.data.data
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
    },
    foodCarousel(){
      for(let i=0;i<8;i++){
        this.imgArr[i].id = i;
        this.imgArr[i].url = require("@/assets/images/foodCarousel/"+(i+1)+".png")
      }
    }
  },
  //进入页面后启动定时轮询
  mounted(){
    this.startInterval()
  }

}
</script>


<style scoped>

.el-row {
  margin-bottom: 20px;
}
:last-child {
  margin-bottom: 0;
}

.el-col {
  border-radius: 4px;
  padding-left: 10px;
  padding-right: 20px;
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
  padding: 10px 20px;
  background-color: #f9fafc;
}

/*这是轮播图的css*/
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
  height: 200px;
  margin: 0 auto;

  /*box-shadow: 0 2px 12px 0;*/
}
ol{
  width: 100px;
}
/* 轮播图片 */

/* 箭头图标 */
.iconDiv{
  position: absolute;
  top: 50%;
  transform: translateY(-50%);
  width: 30px;
  height: 30px;
  border: 1px solid #666;
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
  background-color: rgba(125,125,125,.8);
  cursor: pointer;
}
.active{
  background-color: black !important;
}

/*轮播图的css到这里截止*/

/*这是卡片的css*/

.item {
  float: left;
}

.box-card {
  width: 100%;
  height: 298px;
}
.el-image{
  box-shadow: 3px 3px #888888;
}
/*.el-image:active{*/
/* box-shadow: 0 0;*/
/*}*/
.el-image:hover{
  box-shadow: 5px 5px #888888;
}
</style>
