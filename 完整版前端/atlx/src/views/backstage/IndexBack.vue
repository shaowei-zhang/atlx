<template>
  <div>
    <el-form :inline="true">

      <el-form-item>
        <el-popconfirm title="确定删除吗？" @confirm="delHandle(null)">
          <el-button type="danger" size="small" slot="reference" :disabled="delSomeBtnstatu">批量删除</el-button>
        </el-popconfirm>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="dialogVisible = true" size="small">添加</el-button>
      </el-form-item>
      <el-form-item style="float: right;margin-right: 0px;">
        <el-button type="info" @click="getUserList" size="small">查询</el-button>
      </el-form-item>
      <el-form-item style="float: right;">
        <el-input
            size="small"
            v-model="searchForm.name"
            placeholder="账号名"
            clearable
        ></el-input>
      </el-form-item>

    </el-form>

    <el-table
        ref="multipleTable"
        :data="tableData"
        tooltip-effect="dark"
        border
        @selection-change="handleSelectionChange"
        stripe
        style="width: 100%">

      <el-table-column
          type="selection"
          width="45"
      >
      </el-table-column>

      <el-table-column
          ref="filterTable"
          prop="role.description"
          label="角色"
          sortable
          width="100"
          column-key="role.description"
          :filters="[{text: '管理员', value: '管理员'}, {text: '普通用户', value: '普通用户'}]"
          :filter-method="filterHandler"
      >
      </el-table-column>
      <el-table-column
          prop="username"
          label="姓名"
          width="100">
      </el-table-column>

      <el-table-column
          prop="account"
          label="账号"
          width="100"
      >
      </el-table-column>

      <el-table-column
          prop="email"
          label="邮址"
      >
      </el-table-column>

      <el-table-column label="头像" align="center" prop="avatar">
        <template slot-scope="scope">
          <img :src="scope.row.avatar" width="40" height="40" style="border-radius: 2px"/>
        </template>
      </el-table-column>

      <el-table-column prop="caozuo" label="操作">
        <template slot-scope="scope">
          <el-button type="primary" @click="editHandle(scope.row.account)" size="mini" icon="el-icon-edit" circle></el-button>
          <el-popconfirm title="确定删除吗？" style="margin-left: 10px"  @confirm="delHandle(scope.row.account)">
            <el-button type="danger" size="mini" icon="el-icon-delete" slot="reference" circle></el-button>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>

    <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        layout="total, sizes, prev, pager, next, jumper"
        :current-page="current"
        :page-sizes="[10,20,30,40,50]"
        :page-size="size"
        :total="total">
    </el-pagination>

    <!-------------------------------------------- 新增对话框 ----------------------------------------->
    <el-dialog
        title="添加用户"
        :visible.sync="dialogVisible"
        width="450px"
        :before-close="handleClose"
    >
      <el-form :model="addForm" ref="addForm" :rules="FormRules" label-width="100px" class="demo-addForm">
        <el-form-item label="姓名" prop="username" style="width:350px;">
          <el-input v-model="addForm.username"></el-input>
        </el-form-item>
        <el-form-item label="账号" prop="account" style="width:350px;">
          <el-input v-model="addForm.account"></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="password" style="width:350px;">
          <el-input type="password" v-model="addForm.password" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="确认密码" prop="checkPassword" style="width:350px;">
          <el-input type="password" v-model="addForm.checkPassword" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="邮址" prop="email" style="width:350px;">
          <el-input v-model="addForm.email"></el-input>
        </el-form-item>

        <el-form-item label="角色" prop="role">
          <el-radio-group v-model="addForm.role">
            <el-radio label="管理员"></el-radio>
            <el-radio label="普通用户"></el-radio>
          </el-radio-group>
        </el-form-item>

        <el-form-item>
          <el-button type="primary" @click="submitForm('addForm')">确定</el-button>
          <el-button @click="resetForm('addForm')">重置</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>


    <!---------------------------------------编辑对话框--------------------------------------->
    <el-dialog
        title="编辑用户"
        :visible.sync="dialogVisible2"
        width="450px"
        :before-close="handleClose2"
    >

      <el-form :model="editForm" ref="editForm" :rules="FormRules" label-width="100px" class="demo-editForm">
        <el-form-item label="姓名" prop="username" style="width:350px;">
          <el-input v-model="editForm.username"></el-input>
        </el-form-item>
        <el-form-item label="账号" prop="account" style="width:350px;">
          <el-input v-model="editForm.account" disabled></el-input>
        </el-form-item>
        <el-form-item label="邮址" prop="email" style="width:350px;">
          <el-input v-model="editForm.email"></el-input>
        </el-form-item>

        <el-form-item label="角色" prop="role">
          <el-radio-group v-model="editForm.role">
            <el-radio label="管理员"></el-radio>
            <el-radio label="普通用户"></el-radio>
          </el-radio-group>
        </el-form-item>

        <el-form-item>
          <el-button type="primary" @click="submitForm('editForm')">确定</el-button>
          <el-button @click="resetForm('editForm')">重置</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>

  </div>
</template>
<script>
export default {
  name: "IndexBack",
  data() {
    var validatePassword = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入密码'));
      } else {
        if (this.addForm.checkPassword !== '') {
          this.$refs.addForm.validateField('checkPassword');
        }
        callback();
      }
    };
    var validatePassword2 = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请再次输入密码'));
      } else if (value !== this.addForm.password) {
        callback(new Error('两次输入密码不一致!'));
      } else {
        callback();
      }
    };
    return {
      current: 1,
      size: 10,
      total: 0,
      addForm: {
        username: '',
        account: '',
        email: '',
        role: '',
        //avatar: '',
        checkPassword: '',
        password: ''
      },
      dialogVisible: false,
      editForm: {
        username: '',
        account: '',
        email: '',
        // avatar: '',
        role: '',
      },
      dialogVisible2: false,
      searchForm: {
        name: ''
      },
      delSomeBtnstatu: true ,
      tableData: [],
      FormRules: {
        username: [
          { required: true, message: '请输入姓名', trigger: 'blur' }
        ],
        account: [
          { required: true, message: '请输入账号名，这将是登录的唯一标识', trigger: 'blur' },
          { min: 1,max: 15, message: '长度在1~15个字符', trigger: 'blur' }
        ],
        password: [
          { validator: validatePassword,message: '请输入密码', trigger: 'blur' }
        ],
        checkPassword: [
          { validator: validatePassword2, trigger: 'blur' }
        ],
        email: [
          {required: true, message: '请输入邮址', trigger: 'blur' }
        ]
      },
      multipleSelection: []
    }
  },
  created() {
    this.getUserList();
  },
  methods: {
    getUserList(){
      var account = this.searchForm.name
      this.$axios.post('/user/queryUser',{
        account: account
      }).then(res => {
        this.tableData = res.data.data
        this.total = this.tableData.length
      })
    },
    delHandle(account){
      let dels = []
      if(account){
        dels.push(account)
      }else {
        this.multipleSelection.forEach(row => {
          dels.push(row.account)
        })
      }

      this.$axios.post('/user/deleteUser',{
        dels: dels
      }).then(res => {

        res.data.data
        this.$message({
          showClose: true,
          message: "删除成功",
          type: "success",
          onClose:() => {
            this.getUserList()
          }
        })
      })
    },
    editHandle(account){
      this.$axios.post('/user/editUserData',{account:account}).then(res => {

        // this.editForm = res.data.data
        this.editForm.email = res.data.data.email;
        this.editForm.account = res.data.data.account;
        this.editForm.username = res.data.data.username;
        this.editForm.role = res.data.data.role.description;

        this.dialogVisible2 = true
      })
    }
    ,
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          if(formName === "addForm"){
            //操作u
            this.$axios.post('/user/insertUser',this.addForm).then(res => {
              this.$message({
                showClose: true,
                message: "添加成功",
                type: "success",
                onClose:() => {
                  this.getUserList()
                }
              })

              this.dialogVisible = false;
              this.handleClose()

            })

          }else {//post提交表单
            this.$axios.post('/user/updateUser',this.editForm).then(res => {

              //操作
              this.$message({
                showClose: true,
                message: "编辑成功",
                type: "success",
                onClose:() => {
                  this.getUserList()
                }
              })

              this.dialogVisible2 = false;
              this.handleClose2();

            })
          }
        } else {
          console.log('error submit!!');
          return false;
        }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
      this.addForm = {}
    },
    handleClose(done) {
      this.dialogVisible = false;
      this.resetForm('addForm')
    },
    handleClose2(done) {
      this.dialogVisible2 = false;
      this.resetForm('editForm')
    },
    handleSizeChange(val) {
      console.log(`每页 ${val} 条`);
    },
    handleCurrentChange(val) {
      console.log(`当前页: ${val}`);
    },
    handleSelectionChange(val) {
      this.multipleSelection = val
      this.delSomeBtnstatu = val.length==0;
    },
    filterHandler(value, row, column) {
      return row.role.description === value;
    }
  }
}
</script>

<style scoped>
.el-pagination {
  margin-top: 5px;
  margin-left: 50px;
}
</style>