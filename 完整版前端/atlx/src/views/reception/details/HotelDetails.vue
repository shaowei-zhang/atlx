<template>
  <div style="background-color: #f9fafc;height: 650px" >
    <el-row :gutter="20">
      <el-col :span="9"  v-for="h in hotelInfo">
        <el-image  :src="h.hotelImg" style="width: 350px;height: 260px;border: 1px solid gainsboro;float: left;border-radius: 3px"></el-image>
      </el-col>
      <el-col :span="10" style="height: 260px;width:650px;background-color: #e5e9f2 ;border-radius: 3px" v-for="h in hotelInfo">
        <ul>
          <li><strong >酒店名:</strong> &nbsp; <span>{{h.hotelName}}</span></li>
          <li><strong >价格:</strong> &nbsp; <span>{{h.price}}</span></li>
          <li><strong>简介:</strong>&nbsp; <span style="white-space:nowrap;overflow: hidden;text-overflow: ellipsis">{{ h.hotelRemark }}</span></li>
          <li v-if="isLogin()">
            <el-button type="success"  @click="submitCollection"><i class="el-icon-collection-tag"></i>收藏</el-button>
            <el-button type="warning"  @click="submitOrder"><i class="el-icon-collection-tag"></i>预定</el-button>
          </li>
        </ul>
      </el-col>
    </el-row>

    <el-row style="margin-top: 15px" :gutter="20">
      <el-col :span="9" v-if="isLogin()">
        <el-input
            type="textarea"
            placeholder="请输入评论内容"
            v-model="hotelComment.comment"
            style="width: 250px"
            :rows="12"
            resize='none'
            maxlength="50"
            show-word-limit
        >
        </el-input>
        <el-button  @click="uploadComment"  style="height:40px;width: 70px;margin-left: 5px">发布</el-button>
      </el-col>
      <el-col :span="10" style="height: 260px;width:650px ;border-radius: 3px;">

        <el-table
            :data="hotelMessage"
            style="width: 100%;background-color: #f1fffe">
          <el-table-column
              prop="commentDate"
              :formatter="dateFormat"
              label="时间"
              width="100">
          </el-table-column>

          <el-table-column
              prop="account"
              label="用户"
              width="100">
          </el-table-column>

          <el-table-column
              prop="comment"
              label="评论"
              width="410">
          </el-table-column>
        </el-table>


      </el-col>
    </el-row>

    <el-row style="margin-top: 15px"></el-row>

  </div>

</template>

<script>


export default {
  name:"HotelDetails",
  methods:{
    submitOrder(){
      var hotelName=this.hotelName
      this.$axios.post("/hotelOrder/submitOrder",{
        hotelName:hotelName,
        account: localStorage.getItem("token")
      }).then(res=>{
        this.$message({
          showClose: true,
          message: "预购成功",
          type: "success",
        })
      })
    },
    /*
    * 收藏按钮，跳转到收藏页面
    * */
    submitCollection(){
      var hotelName=this.hotelName
      this.$axios.post("/hotelCollection/insertCollection",{
        hotelName:hotelName,
        account: localStorage.getItem("token")
      }).then(res=>{
        this.$message({
          showClose: true,
          message: "收藏成功",
          type: "success",
        })
      })
    },
    /*
* 提交评论
* */
    uploadComment(){
      this.$axios.post('/hotelMessage/insertHotelMessage',{
        comment:this.hotelComment.comment,
        hotelName: this.hotelComment.hotelName,
        account: localStorage.getItem("token")
      }).then(res => {
        this.$message({
          showClose: true,
          message: "评论上传成功",
          type: "success",
          onClose:() => {
            this.hotelComment = '';
            this.getHotelComment();
          }
        })
      })
    },
    /*
    * 获取评论
    * */
    getHotelComment(){
      //查询美食评论信息列表
      var hotelName=this.hotelName
      this.$axios.post('/hotelMessage/findAllByName',{hotelName:hotelName}).then(res=>{
        this.hotelMessage=res.data.data
      })
    },

    /*
    *
    * 获取食物信息foodInfo
    * */
    getHotelInfo(){
      //查询美食评论信息列表
      var hotelName=this.hotelName
      this.$axios.post('/hotel/queryByMap',{hotelName:hotelName}).then(res=>{
        this.hotelInfo=res.data.data
      })
    },
    /*
    * 时间格式化
    * */
    dateFormat(row){
      const dater = row.commentDate
      if(dater!=null){
        const dateMat= new Date(dater);
        const y = dateMat.getFullYear();
        const m = dateMat.getMonth() + 1;
        const d = dateMat.getDate();
        return `${y}-${m}-${d}`;
      }
    }


  },
  created() {
    /*
    * 获取评论
    * */
    // this.getFoodComment();
    /*
    * 获取食物信息
    * */
    this.getHotelInfo();
    this.getHotelComment()
  },

  data(){
    return{
      /*
      * 这个是将评论传输到后台
      * */
      hotelName: this.$route.query.setHotelName,
      hotelComment:{
        comment:'',
        hotelName: this.$route.query.setHotelName,
      },
      hotelMessage:[] ,
      hotelInfo: [],
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
  margin-top:45px;
}
ul li {
  margin-top: 20px;
}
</style>