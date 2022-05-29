<template>
    <el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm" style="margin-top: 50px" label-width="100px" class="demo-ruleForm">
      <el-form-item label="原密码" prop="password"  style="width:350px;">
        <el-input v-model="ruleForm.password" type="password"></el-input>
      </el-form-item>
      <el-form-item label="新密码" prop="pass" style="width:350px;">
        <el-input type="password" v-model="ruleForm.pass" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="确认密码" prop="checkPass" style="width:350px;">
        <el-input type="password" v-model="ruleForm.checkPass" autocomplete="off" ></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitForm('ruleForm')">确认修改</el-button>
        <el-button @click="resetForm('ruleForm')">重新设置</el-button>
      </el-form-item>
    </el-form>
</template>

<script>
export default {
  data() {
    var validatePass = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入密码'));
      } else {
        if (this.ruleForm.checkPass !== '') {
          this.$refs.ruleForm.validateField('checkPass');
        }
        callback();
      }
    };
    var validatePass2 = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请再次输入密码'));
      } else if (value !== this.ruleForm.pass) {
        callback(new Error('两次输入密码不一致!'));
      } else {
        callback();
      }
    };
    return {
      ruleForm: {
        pass: '',
        checkPass: '',
        password: ''
      },
      rules: {
        pass: [
          { validator: validatePass, trigger: 'blur' }
        ],
        checkPass: [
          { validator: validatePass2, trigger: 'blur' }
        ],
        password: [
          { required: true, message: '原密码不能为空' }
        ]
      }
    };
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {

          this.$axios.post('http://localhost:8088/user/updatePwd',{
            pass: this.ruleForm.pass,
            password: this.ruleForm.password,
            account: localStorage.getItem("token"),

          }).then(res => {

            //编辑操作
            this.$message({
              showClose: true,
              message: "修改密码成功,请重新登陆！",
              type: "success",
              onClose:() =>{
                localStorage.clear();
                sessionStorage.clear()
                this.$store.commit("resetState")
                this.$router.push('/login')
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
    }
  }
}
</script>

<style scoped>

</style>