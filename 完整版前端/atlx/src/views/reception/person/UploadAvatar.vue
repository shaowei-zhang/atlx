<template>
<div>
  <el-form :model="addAvatarForm" ref="addAvatarForm" label-width="100px" style="margin-top: 50px" class="demo-addAvatarForm">
            <el-form-item>
              <el-upload
                  class="avatar-uploader"
                  action="http://localhost:8088/user/uploadAvatar"
                  list-type="picture-card"
                  :show-file-list="false"
                  :on-success="handleAvatarSuccess">
                <img v-if="addAvatarForm.avatar" :src="addAvatarForm.avatar" class="avatar">
                <i v-else class="el-icon-plus avatar-uploader-icon"></i>
              </el-upload>
            </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="submitForm('addAvatarForm')">确定</el-button>
      <el-button @click="resetForm('addAvatarForm')">重置</el-button>
    </el-form-item>
  </el-form>
</div>
</template>

<script>
export default {
  name: "UploadAvatar",
  data(){
    return{
      addAvatarForm: {
        avatar: ''
      }
    }
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {

          // console.info(localStorage.getItem("token"))
            //操作
            this.$axios.post('/user/avatar', {
              avatar: this.addAvatarForm.avatar,
              account: localStorage.getItem("token")
            }).then(res => {
              this.$message({
                showClose: true,
                message: "上传成功",
                type: "success",
                onClose:()=>{
                  this.$router.go(0);
                }
              })

            })

        } else {
          console.log('error submit!!');
          return false;
        }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
      this.addAvatarForm = {}
    },
    handleAvatarSuccess(res, file) {
      this.addAvatarForm.avatar = res;
    },
  }
}
</script>

<style scoped>
.avatar{
  width: 100%;
  height: 100%;
  display: block;
}
</style>