<template>
  <div>
    <h1>美食收藏</h1>
    <el-table
        ref="multipleTable"
        :data="foodData"
        tooltip-effect="dark"
        stripe
        height="300px"
        style="width: 100%">

      <el-table-column
          prop="id"
          label="ID"
          width="100"
          v-if="false"
      >
      </el-table-column>

      <el-table-column
          prop="foodLocal.foodName"
          label="美食名称"
          width="120"
      >
      </el-table-column>

      <el-table-column label="美食图片" align="center" prop="foodImg">
        <template slot-scope="scope">
          <img :src="scope.row.foodLocal.foodImg" width="40" height="40" style="border-radius: 2px"/>
        </template>
      </el-table-column>


      <el-table-column prop="caozuo" label="操作">
        <template slot-scope="scope">
          <el-popconfirm title="确定移除吗？" style="margin-left: 10px"  @confirm="delFoodHandle(scope.row.id)">
            <el-button type="danger" size="mini" slot="reference">移除</el-button>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>


    <!--====================================酒店收藏=============================================-->

    <h1>酒店收藏</h1>
    <el-table
        ref="multipleTable"
        :data="hotelData"
        tooltip-effect="dark"
        stripe
        height="300px"
        style="width: 100%">

      <el-table-column
          prop="id"
          label="ID"
          width="100"
          v-if="false"
      >
      </el-table-column>

      <el-table-column
          prop="hotel.hotelName"
          label="酒店名称"
          width="120"
      >
      </el-table-column>

      <el-table-column label="酒店图片" align="center" prop="foodImg">
        <template slot-scope="scope">
          <img :src="scope.row.hotel.hotelImg" width="40" height="40" style="border-radius: 2px"/>
        </template>
      </el-table-column>


      <el-table-column prop="caozuo" label="操作">
        <template slot-scope="scope">
          <el-popconfirm title="确定移除吗？" style="margin-left: 10px"  @confirm="delHotelHandle(scope.row.id)">
            <el-button type="danger" size="mini" slot="reference">移除</el-button>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>

<!--====================================风景区收藏=============================================-->

    <h1>风景区收藏</h1>
    <el-table
        ref="multipleTable"
        :data="scenicData"
        tooltip-effect="dark"
        stripe
        height="300px"
        style="width: 100%">

      <el-table-column
          prop="id"
          label="ID"
          width="100"
          v-if="false"
      >
      </el-table-column>

      <el-table-column
          prop="scenic.scenicName"
          label="景区名称"
          width="120"
      >
      </el-table-column>

      <el-table-column label="景区图片" align="center" prop="foodImg">
        <template slot-scope="scope">
          <img :src="scope.row.scenic.scenicImg" width="40" height="40" style="border-radius: 2px"/>
        </template>
      </el-table-column>


      <el-table-column prop="caozuo" label="操作">
        <template slot-scope="scope">
          <el-popconfirm title="确定移除吗？" style="margin-left: 10px"  @confirm="delSceniclHandle(scope.row.id)">
            <el-button type="danger" size="mini" slot="reference">移除</el-button>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>

  </div>


</template>
<script>
export default {
  name:"CollectionStory",
  created() {
    this.getFoodList();
    this.getHotelList();
    this.getScenicList();
  },
  methods: {
    /*
    * 美食
    * */
    getFoodList(){
      this.$axios.post('/foodCollection/queryAllMessage',{
        account: localStorage.getItem("token")
      }).then(res => {
        // 搜索或查询列表展示
        this.foodData = res.data.data
      })
    },
    /*
* 酒店
* */
    getHotelList(){
      this.$axios.post('/hotelCollection/queryAllMessage',{
        account: localStorage.getItem("token")
      }).then(res => {
        // 搜索或查询列表展示
        this.hotelData = res.data.data
      })
    },
    /*
* 风景区
* */
    getScenicList(){
      this.$axios.post('/scenicCollection/queryAllMessage',{
        account: localStorage.getItem("token")
      }).then(res => {
        // 搜索或查询列表展示
        this.scenicData = res.data.data

      })
    },
/*========================================================================================*/
/*
* 美食删除
* */
    delFoodHandle(id){
      let dels = []
      if(id){
        dels.push(id)
      }else {
        this.multipleSelection.forEach(row => {
          dels.push(row.id)
        })
      }
      this.$axios.delete('/foodCollection/deleteFoodById/'+dels).then(res => {
        //删除（不用管批量删除）操作
        this.$message({
          showClose: true,
          message: "删除成功",
          type: "success",
          onClose:() => {
            this.getFoodList()
          }
        })
      })
    },
    /*
* 酒店--删除
* */
    delHotelHandle(id){
      let dels = []
      if(id){
        dels.push(id)
      }else {
        this.multipleSelection.forEach(row => {
          dels.push(row.id)
        })
      }
      this.$axios.delete('/hotelCollection/deleteHotelById/'+dels).then(res => {
        //删除（不用管批量删除）操作
        this.$message({
          showClose: true,
          message: "删除成功",
          type: "success",
          onClose:() => {
            this.getHotelList();
          }
        })
      })
    },

    /*
* 风景区---删除
* */
    delSceniclHandle(id){
      let dels = []
      if(id){
        dels.push(id)
      }else {
        this.multipleSelection.forEach(row => {
          dels.push(row.id)
        })
      }
      this.$axios.delete('/scenicCollection/deleteFoodCollectionById/'+dels).then(res => {
        //删除（不用管批量删除）操作
        this.$message({
          showClose: true,
          message: "删除成功",
          type: "success",
          onClose:() => {
            this.getScenicList();
          }
        })
      })
    },


    dateFormat(row, column){
      const daterc = row[column.property]
      if(daterc!=null){
        const dateMat= new Date(parseInt(daterc.replace("/Date(", "").replace(")/", ""), 10));
        const year = dateMat.getFullYear();
        const month = dateMat.getMonth() + 1;
        const day = dateMat.getDate();
        const hh = dateMat.getHours();
        const mm = dateMat.getMinutes();
        const ss = dateMat.getSeconds();
        const timeFormat= year + "-" + month + "-" + day + "  " + hh + ":" + mm + ":" + ss;
        return timeFormat;
      }

    },
  },
  data() {
    return {
      foodData: [],
      hotelData:[],
      scenicData:[],
      multipleSelection: []
    }
  }
}
</script>

<style>
.el-pagination {
  margin-top: 5px;
  margin-left: 50px;
}
</style>