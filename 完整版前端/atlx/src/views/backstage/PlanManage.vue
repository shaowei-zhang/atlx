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

        <el-input
            size="small"
            v-model="searchForm.startName"
            placeholder="请输入起点或终点"
            clearable
        ></el-input>
      </el-form-item>
      <el-form-item style="float: right;" >
        <el-button plain @click="getUserOne(searchForm.startName)" size="small">
          <i class="el-icon-search"></i></el-button>

      </el-form-item>
      <el-form-item style="float: right;" >
        <i class="el-icon-refresh" @click="getNew"></i>
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
          prop="id"
          label="编号"
          width="100"
          current-row-key="id"
      >
      </el-table-column>

      <el-table-column
          prop="touristName"
          label="旅游名称"
          width="100">
      </el-table-column>



      <el-table-column
          prop="startName"
          label="起点"
      >
      </el-table-column>

      <el-table-column
          prop="endName"
          label="终点"
      >
      </el-table-column>


<!--      <el-table-column label="图片" align="center" prop="avatar">-->
<!--        <template slot-scope="scope">-->
<!--          <img :src="scope.row.avatar" width="40" height="40" style="border-radius: 2px"/>-->
<!--        </template>-->
<!--      </el-table-column>-->

      <el-table-column
          prop="dataTime"
          label="收藏时间"
          width="100"
      >
      </el-table-column>


      <el-table-column prop="caozuo" label="操作">
        <template slot-scope="scope">
          <el-button type="primary" @click="editHandle(scope.row.id)" size="mini" icon="el-icon-edit" circle></el-button>
          <el-popconfirm title="确定删除吗？" style="margin-left: 10px"  @confirm="delHandle(scope.row.id)">
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
        title="添加路线"
        :visible.sync="dialogVisible"
        width="450px"
        :before-close="handleClose"
    >
      <el-form :model="addForm" ref="addForm" :rules="FormRules" label-width="100px" class="demo-addForm">
        <el-form-item label="旅游名称" prop="touristName" style="width:350px;">
          <el-input v-model="addForm.touristName"></el-input>
        </el-form-item>
        <el-form-item label="起点" prop="startName" style="width:350px;">
          <el-input v-model="addForm.startName"></el-input>
        </el-form-item>
        <el-form-item label="终点" prop="endName" style="width:350px;">
          <el-input v-model="addForm.endName"></el-input>
        </el-form-item>
<!--        <el-form-item label="上传头像">-->
<!--          <el-upload-->
<!--              class="avatar-uploader"-->
<!--              action="http://localhost:8088/file/upload/"-->
<!--              :show-file-list="false"-->
<!--              :on-success="handleAvatarSuccessAdd">-->
<!--            <img v-if="addForm.avatar" :src="addForm.avatar" class="avatar">-->
<!--            <i v-else class="el-icon-loading avatar-uploader-icon"></i>-->
<!--          </el-upload>-->
<!--        </el-form-item>-->

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
        <el-form-item label="姓名" prop="touristName" style="width:350px;">
          <el-input v-model="editForm.touristName"></el-input>
        </el-form-item>
        <el-form-item label="起点" prop="startName" style="width:350px;">
          <el-input v-model="editForm.startName"></el-input>
        </el-form-item>
        <el-form-item label="终点" prop="endName" style="width:350px;">
          <el-input v-model="editForm.endName"></el-input>
        </el-form-item>
<!--        <el-form-item label="点击修改">-->
<!--          <el-upload-->
<!--              class="avatar-uploader"-->
<!--              action="http://localhost:8088/file/uploadAvatar/"-->
<!--              :show-file-list="false"-->
<!--              :on-success="handleAvatarSuccessEdit">-->
<!--            <img v-if="editForm.avatar" :src="editForm.avatar" class="avatar">-->
<!--            <i v-else class="el-icon-loading avatar-uploader-icon"></i>-->
<!--          </el-upload>-->
<!--        </el-form-item>-->


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
  name: "PlanManage",
  created() {
    this.getUserList();
  },
  methods: {
    getUserList(){
      this.$axios.post('/touristRoutes/selectList').then(res => {

        //列表展示
        console.log(res.data.data)
        this.tableData = res.data.data
        this.total = this.tableData.length


      })
    },
    getUserOne(startName){

      this.$axios.post('/touristRoutes/selectOne',{
        startName: startName
      }).then(res => {


        //搜索或查询列表展示
        // console.log(res.data.data)
        this.tableData = res.data.data
        this.total = this.tableData.length


      })
    },
//刷新
    getNew() {
        this.getUserList()
    },
      delHandle(id){
      let dels = []
      if(id){
        dels.push(id)
      }else {
        this.multipleSelection.forEach(row => {
          dels.push(row.id)
        })
      }
      this.$axios.post('/touristRoutes/deleteById/'+dels).then(res => {


        //删除（不用管批量删除）操作
        res.data.data
        this.$message({
          showClose: true,
          message: "删除成功",
          type: "success",
          // 刷新页面
          onClose:() => {
            this.getUserList()
          }
        })



      })
    },
    editHandle(id){
      this.$axios.post('/user/editData'+id).then(res => {


        //在编辑框填充数据用的方法
        this.editForm = res.data.data
        this.dialogVisible2 = true



      })
    },
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          console.log(formName)
          if(formName === "addForm"){


            //添加操作
            this.$axios.post('/touristRoutes/insert',this.addForm).then(res => {
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
          }else {

            this.$axios.get('/user/edit').then(res => {

              //编辑操作
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
    filterHandler(value, row, column) {
      const property = column['property'];
      return row[property] === value;
    },
    handleSelectionChange(val) {
      this.multipleSelection = val
      this.delSomeBtnstatu = val.length===0;
    },
    handleAvatarSuccessAdd(res, file) {
      this.addForm.avatar = res;
    },
    handleAvatarSuccessEdit(res, file) {
      this.editForm.avatar = res;
    }
  },
  data() {
    return {
      current: 1,
      size: 10,
      total: 0,

      searchForm: {
        id: '',
        startName:'',//查询
      },
      addForm: {
        touristName: '',
        startName: '',
        endName: '',
      },
      dialogVisible: false,
      editForm: {
        touristName: '',
        startName: '',
        endName: '',
      },
      dialogVisible2: false,
      delSomeBtnstatu: true ,
      tableData: [],
      FormRules: {
        touristName: [
          { required: true, message: '请输入姓名', trigger: 'blur' }
        ],
        id: [
          { required: true, message: '请输入编号名，这将是登录的唯一标识', trigger: 'blur' },
          { min: 1,max: 15, message: '长度在1~15个字符', trigger: 'blur' }
        ],
        password: [
          { validator: validatePassword,message: '请输入密码', trigger: 'blur' }
        ],
        checkPassword: [
          { validator: validatePassword2, trigger: 'blur' }
        ],
        startName: [
          {required: true, message: '请输入起点', trigger: 'blur' }
        ],
        endName: [
          {required: true, message: '请输入终点', trigger: 'blur' }
        ]
      },
      multipleSelection: []
    };
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
  }
}
</script>

<style>
.el-pagination {
  margin-top: 5px;
  margin-left: 50px;
}
.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}
.avatar-uploader .el-upload:hover {
  border-color: #409EFF;
}
.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 100px;
  height: 100px;
  line-height: 100px;
  text-align: center;
}
.avatar {
  width: 100px;
  height: 100px;
  display: block;
}
</style>