<template>
  <div style="background-color: #f9fafc;height: 650px" >
    <el-row :gutter="20">
      <el-col :span="9"  v-for="s in scenicInfo">
        <el-image  :src="s.scenicImg" style="width: 350px;height: 260px;border: 1px solid gainsboro;float: left;border-radius: 3px"></el-image>
      </el-col>
      <el-col :span="10" style="height: 260px;width:650px;background-color: #e5e9f2 ;border-radius: 3px" v-for="s in scenicInfo">
        <ul>
          <li><strong >景区名称:</strong> &nbsp; <span>{{s.scenicName}}</span></li>
          <li><strong >门票:</strong> &nbsp; <span>{{s.price}}</span></li>
          <li><strong>景区简介:</strong>&nbsp; <span style="white-space: nowrap;overflow: hidden;text-overflow: ellipsis; width: 300px">{{ s.scenicIntroduce }}</span></li>
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
            placeholder="请输入内容"
            v-model="scenicComment.comment"
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
            :data="scenicMessage"
            style="width: 100%;background-color: #f1fffe;">
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
  name:"ScenicDetails",
  methods:{
    submitOrder(){
      var scenicName=this.scenicName
      this.$axios.post("/scenicOrder/submitOrder",{
        scenicName:scenicName,
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
      var scenicName=this.scenicName
      this.$axios.post("/scenicCollection/collectionScenic",{
        scenicName:scenicName,
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
      this.$axios.post('/scenicMessage/insertScenicMessage',{
        comment:this.scenicComment.comment,
        scenicName: this.scenicComment.scenicName,
        account: localStorage.getItem("token")
      }).then(res => {
        this.$message({
          showClose: true,
          message: "评论上传成功",
          type: "success",
          onClose:() => {
            this.scenicComment.comment = '';
            this.getScenicComment();
          }
        })
      })
    },
    /*
    * 获取评论
    * */
    getScenicComment(){
      //查询美食评论信息列表
      var scenicName=this.scenicName
      this.$axios.post('/scenicMessage/findAllByName',{scenicName:scenicName}).then(res=>{
        this.scenicMessage=res.data.data
      })
    },

    /*
    *
    * 获取食物信息foodInfo
    * */
    getScenicInfo(){
      //查询美食评论信息列表
      var scenicName=this.scenicName
      this.$axios.post('/scenic/queryScenicbyName',{scenicName:scenicName}).then(res=>{
        this.scenicInfo=res.data.data
      })
    },


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
 
    this.getScenicInfo();
    this.getScenicComment();
  },

  data(){
    return{
   
      scenicName: this.$route.query.setScenicName,
      scenicComment:{
        comment:'',
        scenicName:this.$route.query.setScenicName
      },
      
      scenicMessage:[],
   
      scenicInfo:[],
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