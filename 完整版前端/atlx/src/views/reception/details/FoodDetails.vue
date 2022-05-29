<template>
  <div style="background-color: #f9fafc;height: 650px" >
    <el-row :gutter="20">
      <el-col :span="9"  v-for="foodInfo in foodInfo">
          <el-image  :src="foodInfo.foodImg" style="width: 350px;height: 260px;border: 1px solid gainsboro;float: left;border-radius: 3px"></el-image>
      </el-col>
      <el-col :span="10" style="height: 260px;width:650px;background-color: #e5e9f2 ;border-radius: 3px" v-for="foodInfo in foodInfo">
        <ul>
          <li><strong >美食:</strong> &nbsp; <span>{{foodName}}</span></li>
          <li><strong>类型:</strong>&nbsp; <span>{{ foodInfo.foodClass }}</span></li>
          <li><strong>价格:</strong>&nbsp; <span>{{ foodInfo.price }}</span></li>
          <li><strong>简介:</strong>&nbsp; <span style="white-space:nowrap;overflow: hidden;text-overflow: ellipsis">{{ foodInfo.foodIntroduce }}</span></li>
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
            v-model="foodComment.comment"
            style="width: 250px"
            :rows="12"
            resize='none'
            maxlength="50"
            show-word-limit
        >

        </el-input>
        <el-button  @click="uploadComment"  style="height:40px;width: 70px;margin-left: 5px">发布</el-button>
      </el-col>
     <el-col :span="10" style="height: 260px;width:650px ;border-radius: 3px">

       <el-table
           :data="foodMessage"
           style="width: 100%;background-color: #f1fffe">
         <el-table-column
             :formatter="dateFormat"
             prop="commentDate"
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
  name:"FoodDetails",
  methods:{

    /*
    * 收藏按钮，跳转到收藏页面
    * */
    submitCollection(){
      var foodName=this.foodName
      this.$axios.post("/foodCollection/collectionFood",{
        foodName:foodName,
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
    * 提交表单
    * */
    submitOrder(){
      var foodName=this.foodName
      this.$axios.post("/foodOrder/submitOrder",{
        foodName:foodName,
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
* 提交评论
* */
    uploadComment(){
      this.$axios.post('/foodMessage/insertFoodMessage',{
        comment:this.foodComment.comment,
        foodName: this.foodComment.foodName,
        account: localStorage.getItem("token")
      }).then(res => {

        this.$message({
          showClose: true,
          message: "评论上传成功",
          type: "success",
          onClose:() => {
            this.foodComment.comment = '';
            this.getFoodComment();
          }
        })
      })
    },
    /*
    * 获取评论
    * */
    getFoodComment(){
      //查询美食评论信息列表
      var foodName=this.foodName
      this.$axios.post('/foodMessage/findAllByName',{foodName:foodName}).then(res=>{
        this.foodMessage=res.data.data
      })
    },

/*
*
* 获取食物信息foodInfo
* */
    getFoodInfo(){
      //查询美食评论信息列表
      var foodName=this.foodName
      this.$axios.post('/food/queryByMap',{foodName:foodName}).then(res=>{
        this.foodInfo=res.data.data
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
      this.getFoodComment();
      /*
      * 获取食物信息
      * */
      this.getFoodInfo();
  },

  data(){
    return{
      /*
      * 这个是将评论传输到后台
      * */
      foodName: this.$route.query.setFoodName,

      foodComment:{
        comment:'',
        foodName: this.$route.query.setFoodName,
      },
      foodMessage:[] ,
      foodInfo: [],
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
  margin-top: 20px;
}
</style>