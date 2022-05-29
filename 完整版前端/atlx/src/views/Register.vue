
<template>
  <div class='building'>
    <el-row type="flex" class="row-bg" justify="center">
        <el-form :model="registerForm" :rules="rules" ref="registerForm" class="demo-registerForm">
          <el-form-item prop="account" style="width: 245px">
            <el-input placeholder="用户名" type="text" clearable v-model="registerForm.account"></el-input>
          </el-form-item>

          <el-form-item prop="password" style="width:245px;">
            <el-input type="password" placeholder="密码" v-model="registerForm.password" autocomplete="off"></el-input>
          </el-form-item>

          <el-form-item prop="checkPassword" style="width:245px;">
            <el-input type="password" placeholder="确认密码" v-model="registerForm.checkPassword" autocomplete="off"></el-input>
          </el-form-item>

          <el-form-item prop="email" style="width:245px;">
            <el-input type="text" placeholder="邮址" v-model="registerForm.email" autocomplete="off"></el-input>
          </el-form-item>

          <el-form-item prop="emailCode" style="width: 245px;">
            <el-input placeholder="邮箱验证码" type="text" clearable v-model="registerForm.emailCode" style="width: 125px;float: left;"></el-input>
            <el-button style="border-radius: 5px;width: 110px;height: 40px;float: right" @click="getEmailCode"  type="info">获取验证码</el-button>
          </el-form-item>

          <el-form-item style="width: 245px;margin-bottom: 0px;">
            <el-button type="primary" style="width: 100%;" plain @click="submitForm('registerForm')">注册</el-button>
          </el-form-item>

          <el-form-item style="width: 245px;margin-bottom: 0px;">
            <router-link to="/login" style="float: right">去登录？</router-link>
          </el-form-item>
        </el-form>
    </el-row>
  </div>
</template>

<script>
export default {
  name: "Register",
  data() {
    var validatePassword = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入密码'));
      } else {
        if (this.registerForm.checkPassword !== '') {
          this.$refs.registerForm.validateField('checkPassword');
        }
        callback();
      }
    };
    var validatePassword2 = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请再次输入密码'));
      } else if (value !== this.registerForm.password) {
        callback(new Error('两次输入密码不一致!'));
      } else {
        callback();
      }
    };
    return {
      registerForm: {
        account: '',
        password: '',
        checkPassword: '',
        email: '',
        emailCode: '',
      },
      rules: {
        account: [
          { required: true, message: '请输入用户名,唯一的登录凭证', trigger: 'blur' }
        ],
        password: [
          { validator: validatePassword, trigger: 'blur' }
        ],
        checkPassword: [
          { validator: validatePassword2, trigger: 'blur' }
        ],
      },
    }
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.$axios.post('/login/register',this.registerForm).then(res => {

            this.$message({
              showClose: true,
              message: "注册成功",
              type: "success",
              onClose:() => {
                this.$router.push("/login")
              }
            })

          })
        } else {
          console.log('error submit!!');
          return false;
        }
      });
    },
    getEmailCode(){
      this.$axios.get('/login/registerVerifyCode'+this.registerForm.email).then(res => {
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

.el-divider{
  height: 200px;
}

.building{
  width:100%;
  height:100%;
  position:fixed;
  background-size:100% 100%;
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
