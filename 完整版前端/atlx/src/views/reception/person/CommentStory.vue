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
          :formatter="dateFormat"
          prop="commentDate"
          label="时间"
          width="180"
      >
      </el-table-column>


      <el-table-column
          prop="comment"
          label="评论"
          width="400"
      >
      </el-table-column>

      <el-table-column
          prop="foodLocal.foodName"
          label="美食名称"
          width="120"
      >
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
          :formatter="dateFormat"
          prop="commentDate"
          label="时间"
          width="180"
      >
      </el-table-column>


      <el-table-column
          prop="comment"
          label="评论"
          width="400"
      >
      </el-table-column>
      <el-table-column
          prop="hotel.hotelName"
          label="酒店名称"
          width="120"
      >
      </el-table-column>



      <el-table-column prop="caozuo" label="操作">
        <template slot-scope="scope">
          <el-popconfirm title="确定移除吗？" style="margin-left: 10px"  @confirm="delHotelHandle(scope.row.id)">
            <el-button type="danger" size="mini" slot="reference">移除</el-button>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>

    <!--    &lt;!&ndash;====================================风景区收藏=============================================&ndash;&gt;-->

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
          :formatter="dateFormat"
          prop="commentDate"
          label="时间"
          width="180"
      >
      </el-table-column>


      <el-table-column
          prop="comment"
          label="评论"
          width="400"
      >
      </el-table-column>
      <el-table-column
          prop="scenic.scenicName"
          label="景区名称"
          width="120"
      >
      </el-table-column>



      <el-table-column prop="caozuo" label="操作">
        <template slot-scope="scope">
          <el-popconfirm title="确定移除吗？" style="margin-left: 10px"  @confirm="delScenicHandle(scope.row.id)">
            <el-button type="danger" size="mini" slot="reference">移除</el-button>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>

  </div>


</template>
<script>
export default {
  name:"CommentStory",
  created() {
    this.getFoodMessage();
    this.getHotelMessage();
    this.getScenicMessage();
  },
  methods: {
    /*
    * 美食
    * */
    getFoodMessage(){
      this.$axios.post('/foodMessage/findAllByAccount',{
        account: localStorage.getItem("token")
      }).then(res => {
        // 搜索或查询列表展示
        this.foodData = res.data.data

        console.info(this.foodData)
      })
    },
    /*
* 酒店
* */
    getHotelMessage(){
      this.$axios.post('/hotelMessage/findAllByAccount',{
        account: localStorage.getItem("token")
      }).then(res => {
        // 搜索或查询列表展示
        this.hotelData = res.data.data
      })
    },
    /*
* 风景区
* */
    getScenicMessage(){
      this.$axios.post('/scenicMessage/findAllByAccount',{
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
      this.$axios.delete('/foodMessage/deleteFoodMessageById/'+dels).then(res => {
        //删除（不用管批量删除）操作
        this.$message({
          showClose: true,
          message: "删除成功",
          type: "success",
          onClose:() => {
            this.getFoodMessage()
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
      this.$axios.delete('/hotelMessage/deleteHotelMessageById/'+dels).then(res => {
        //删除（不用管批量删除）操作
        this.$message({
          showClose: true,
          message: "删除成功",
          type: "success",
          onClose:() => {
            this.getHotelMessage();
          }
        })
      })
    },

    /*
* 风景区---删除
* */
    delScenicHandle(id){
      let dels = []
      if(id){
        dels.push(id)
      }else {
        this.multipleSelection.forEach(row => {
          dels.push(row.id)
        })
      }
      this.$axios.delete('/scenicMessage/deleteScenicMessageById/'+dels).then(res => {
        //删除（不用管批量删除）操作
        this.$message({
          showClose: true,
          message: "删除成功",
          type: "success",
          onClose:() => {
            this.getScenicMessage();
          }
        })
      })
    },
    dateFormat(row){
      const dater = row.commentDate
      const dateMat= new Date(dater);
      const year = dateMat.getFullYear();
      const month = dateMat.getMonth() + 1;
      const day = dateMat.getDate();
      const hh = dateMat.getHours();
      const mm = dateMat.getMinutes();
      const ss = dateMat.getSeconds();
      return `${year}-${month}-${day} ${hh}:${mm}:${ss}`;
    }

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