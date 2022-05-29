
<template>
  <div class='building' :style="{backgroundImage:'url('+loginBackground+')'}">
    <el-row type="flex" class="row-bg" justify="center">
      <el-col :xl="6" :lg="7" style="align-items: center;">
        <h2 style="margin-top: 0px;color: #409EFF">
          安途旅行欢迎你！
        </h2>
        <el-image :src="require('../assets/images/loginLogo.png')" style="width: 185px;"></el-image>
      </el-col>

      <el-col :xl="6" :lg="7">
        <el-form :model="loginForm" :rules="rules" ref="loginForm" class="demo-loginForm">
          <el-form-item prop="account" style="width: 220px">
            <el-input placeholder="用户名" type="text" clearable v-model="loginForm.account"></el-input>
          </el-form-item>

          <el-form-item prop="password" style="width: 220px">
            <el-input placeholder="密码"  type="password" clearable v-model="loginForm.password"></el-input>
          </el-form-item>

          <el-form-item prop="code" style="width: 220px;">
            <el-input placeholder="验证码" type="text" clearable v-model="loginForm.code" style="width: 100px;float: left;"></el-input>
            <el-image :src="codeImage" class="codeImage" @click="getCaptcha" style="border-radius: 5px;width: 110px;height: 40px"></el-image>
          </el-form-item>

          <el-form-item style="width: 220px;margin-bottom: 0px;">
            <el-button type="primary" style="width: 100%;" plain @click="submitForm('loginForm')">登录</el-button>
          </el-form-item>

          <el-form-item style="width: 220px;margin-bottom: 0px;">
            <router-link to="/register" style="float: left">未注册？</router-link>
            <router-link to="/changePwd" style="float: right">密码呢？</router-link>
          </el-form-item>
        </el-form>

      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  name: "Login",
  data() {
    return {
      loginBackground: require("../assets/images/loginBackground.png"),
      loginForm: {
        account: '',
        password: '',
        code: '',
      },
      rules: {
        account: [
          { required: true, message: '请输入用户名', trigger: 'blur' }
        ],
        password: [
          { required: true, message: '请输入密码', trigger: 'blur' },
          { min: 1, max: 15, message: '长度在 3 到 15 个字符', trigger: 'blur' }
        ]
      },
      codeImage: 'http://localhost:8088/login/loginValidateCode'
    };
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.$axios.post('/login/toLogin',this.loginForm).then(res => {

            this.$message({
              showClose: true,
              message: "欢迎登录安途旅行",
              type: "success",
              onClose:() => {

                // console.log(res)
                const account = res.data.data
                this.$store.commit('SET_TOKEN', account)

                this.$router.replace("/reception/index")
                // this.$router.push()
              }
            })

          })
        } else {
          console.log('error submit!!');
          return false;
        }
      });
    },
    getCaptcha(){
      let num=Math.ceil(Math.random()*10)
      this.codeImage = 'http://localhost:8088/login/loginValidateCode?'+num
    }
  },
  created(){
    // this.getCaptcha();
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

.codeImage{
  float: left;
  margin-left: 8px;
  border-right: 4px;
}

.building{
  width:100%;
  height:100%;
  position:fixed;
  background-size:100% 100%;
}
.el-form{
  width: 220px;
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
