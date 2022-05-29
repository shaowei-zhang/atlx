
<template>

  <el-row type="flex" class="row-bg" justify="center">

    <el-form :model="forgetForm" :rules="rules" ref="forgetForm" class="demo-forgetForm">
      <el-form-item prop="account" style="width: 245px">
        <el-input placeholder="用户名" type="text" clearable v-model="forgetForm.account"></el-input>
      </el-form-item>

      <el-form-item prop="email" style="width:245px;">
        <el-input type="text" placeholder="邮址" v-model="forgetForm.email" autocomplete="off"></el-input>
      </el-form-item>

      <el-form-item prop="emailCode" style="width: 245px;">
        <el-input placeholder="邮箱验证码" type="text" clearable v-model="forgetForm.emailCode" style="width: 125px;float: left;"></el-input>
        <el-button style="border-radius: 5px;width: 110px;height: 40px;float: right" @click="getEmailCode"  type="info">获取验证码</el-button>
      </el-form-item>

      <el-form-item style="width: 245px;margin-bottom: 0px;">
        <el-button type="primary" style="width: 100%;" plain @click="submitForm('forgetForm')">下一步</el-button>
      </el-form-item>

      <el-form-item style="width: 245px;margin-bottom: 0px;">
        <router-link to="/login" style="float: right">去登录？</router-link>
      </el-form-item>
    </el-form>


    <!----------------------------------重置密码对话框------------------------------------->
    <el-dialog
        title="重置密码"
        :visible.sync="dialogVisible"
        width="355px"
        height="400px"
        :before-close="handleClose"
    >
      <el-form :model="resetPwdForm" ref="resetPwdForm" :rules="rules" class="demo-resetPwdForm" style="box-shadow:none;background-color:white;margin-top: 0px">

        <el-form-item prop="account" style="width: 245px" v-show="false">
          <el-input placeholder="用户名" type="text" clearable v-model="resetPwdForm.account"></el-input>
        </el-form-item>
        <el-form-item prop="password" style="width:245px;">
          <el-input type="password" placeholder="新密码" v-model="resetPwdForm.password" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item prop="checkPassword" style="width:245px;">
          <el-input type="password" placeholder="确认密码" v-model="resetPwdForm.checkPassword" autocomplete="off"></el-input>
        </el-form-item>

        <el-form-item>
          <el-button type="primary" style="width: 100%;" @click="submitForm('resetPwdForm')">确定</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>

  </el-row>

</template>

<script>
export default {
  name: "ChangePwd",
  data() {
    var validatePassword = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入密码'));
      } else {
        if (this.resetPwdForm.checkPassword !== '') {
          this.$refs.resetPwdForm.validateField('checkPassword');
        }
        callback();
      }
    };
    var validatePassword2 = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请再次输入密码'));
      } else if (value !== this.resetPwdForm.password) {
        callback(new Error('两次输入密码不一致!'));
      } else {
        callback();
      }
    };
    return {
      dialogVisible: false,
      forgetForm: {
        account: '',
        email: '',
        emailCode: '',
      },
      resetPwdForm: {
        account: '',
        checkPassword: '',
        password: '',
      },
      rules: {
        account: [
          { required: true, message: '请输入用户名', trigger: 'blur' }
        ],password: [
          { validator: validatePassword, trigger: 'blur' }
        ],
        checkPassword: [
          { validator: validatePassword2, trigger: 'blur' }
        ]
      },
    }
  },
  methods: {
    resetForm(formName) {
      this.$refs[formName].resetFields();
    },
    handleClose(done) {
      this.dialogVisible = false;
      this.resetForm('resetPwdForm')
    },
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {

        if (valid) {
          if(formName === "forgetForm"){

            this.$axios.post('/login/forget',this.forgetForm).then(res => {
              this.$message({
                showClose: true,
                message: "请进行下一步！",
                type: "success",
                onClose:() => {
                  this.resetPwdForm.account = this.forgetForm.account
                  this.dialogVisible = true;
                }
              })
            })

          } else {

            this.$axios.post('/login/resetPwd',this.resetPwdForm).then(res => {
              this.$message({
                showClose: true,
                message: "密码修改成功！",
                type: "success",
                onClose:() => {
                  this.dialogVisible = false;
                }
              })
            })

          }
        }else {
          console.log('error submit!!');
          return false;
        }
      });
    },
    getEmailCode(){
      this.$axios.get('/login/registerVerifyCode'+this.forgetForm.email).then(res => {
        this.$message({
          showClose: true,
          message: "邮件发送成功",
          type: "success",
        })
      })
    }
  }
}
</script>

<style scoped>

.row-bg{
  background-color: #fafafa;
  height: 100%;
  align-items: center;
  text-align: center;
}

.el-form{
  width: 250px;
  padding: 35px 35px 15px 35px;
  border-radius: 5px;
  -webkit-border-radius: 5px;
  -moz-border-radius: 5px;
  box-shadow: 0 0 40px #bbbdbd;
  background-color: rgba(255, 255, 255, 0);
}
a{
  text-decoration: none;
  color: #bbbdbd;
}
a:hover{
  color: #62666c;
}
</style>
