<template>
  <div>
    <el-form :inline="true">
      <el-form-item>
        <el-popconfirm title="确定删除吗？" @confirm="delHandle(null)">
          <el-button type="danger" size="small" slot="reference" :disabled="delSomeBtnstatu">批量删除</el-button>
        </el-popconfirm>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" size="small" @click="dialogVisible = true" >添加</el-button>
      </el-form-item>

      <el-form-item style="float: right;margin-right: 0px;">
        <el-input
            v-model="searchForm.scenicName"
            placeholder="请输入景区名称"
            size="small"
            clearable
        ></el-input>
      </el-form-item>
      <el-form-item style="float: right;" >
        <el-button size="small" plain @click="getscenicName(searchForm.scenicName)" ><i class="el-icon-search"></i></el-button>
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
          v-if="false">
      </el-table-column>

      <el-table-column
          prop="scenicName"
          label="景区名称"
          width="100"
      >
      </el-table-column>
      <el-table-column
          prop="price"
          label="门票价格"
          width="100"
      >
      </el-table-column>
      <el-table-column
          prop="region.regionName"
          label="所属区域"
          width="100"
          current-row-key="regionName"
      >

      </el-table-column>
      <el-table-column
          prop="scenicIntroduce"
          label="景区简介"

          width="500"
          current-row-key="regionName"
      >

      </el-table-column>

      <el-table-column label="景区图片" align="center" prop="scenicImg">
        <template slot-scope="scope">
          <img :src="scope.row.scenicImg" width="40" height="40" style="border-radius: 2px"/>
        </template>
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
        title="添加景区"
        :visible.sync="dialogVisible"
        width="450px"
        :before-close="handleClose"
    >
      <el-form :model="addForm" ref="addForm" :rules="FormRules" label-width="100px" class="demo-addForm">
        <el-form-item label="景区名称" prop="scenicName" style="width:350px;">
          <el-input v-model="addForm.scenicName"></el-input>
        </el-form-item>
        <el-form-item label="门票价格" prop="price" style="width:350px;">
          <el-input v-model="addForm.price"></el-input>
        </el-form-item>
        <el-form-item label="所属地区" prop="regionName" style="width:350px;">
          <el-input v-model="addForm.regionName"></el-input>
        </el-form-item>
        <el-form-item label="景区简介" prop="scenicIntroduce" style="width:350px;">
          <el-input v-model="addForm.scenicIntroduce"></el-input>
        </el-form-item>

        <el-form-item label="上传景区图片">
          <el-upload
              class="avatar-uploader"
              action="http://localhost:8088/scenic/scenicImg"
              :show-file-list="false"
              :on-success="handleAvatarSuccessAdd">
            <img v-if="addForm.scenicImg" :src="addForm.scenicImg" class="avatar">
            <i v-else class="el-icon-loading avatar-uploader-icon"></i>
          </el-upload>
        </el-form-item>
        <!--        <el-form-item label="图片" prop="scenicImg" style="width:350px;">-->
        <!--          <el-input v-model="addForm.scenicImg"></el-input>-->
        <!--        </el-form-item>-->

        <el-form-item>
          <el-button type="primary" @click="submitForm('addForm')">确定</el-button>
          <el-button @click="resetForm('addForm')">重置</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>


    <!---------------------------------------编辑对话框--------------------------------------->
    <el-dialog
        title="编辑景区信息"
        :visible.sync="dialogVisible2"
        width="450px"
        :before-close="handleClose2"
    >

      <el-form :model="editForm" ref="editForm" :rules="FormRules" label-width="100px" class="demo-editForm">

        <el-form-item label="景区名称" prop="scenicName" style="width:350px;">
          <el-input v-model="editForm.scenicName"></el-input>
        </el-form-item>
        <el-form-item label="门票价格" prop="price" style="width:350px;">
          <el-input v-model="editForm.price"></el-input>
        </el-form-item>
        <el-form-item label="所属区域" prop="regionName" style="width:350px;">
          <el-input v-model="editForm.regionName"></el-input>
        </el-form-item>
        <el-form-item label="景区简介" prop="scenicIntroduce" style="width:350px;">
          <el-input v-model="editForm.scenicIntroduce"></el-input>
        </el-form-item>


        <el-form-item label="点击修改">
          <el-upload
              class="avatar-uploader"
              action="http://localhost:8088/scenic/scenicImg"
              :show-file-list="false"
              :on-success="handleAvatarSuccessEdit">
            <img v-if="editForm.scenicImg" :src="editForm.scenicImg" class="avatar">
            <i v-else class="el-icon-loading avatar-uploader-icon"></i>
          </el-upload>
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
  name: "ScenicManage",
  created() {
    this.getScenicList();
  },
  methods: {
    getScenicList(){
      this.$axios.get('/scenic/scenicList').then(res => {
        //搜索或查询列表展示
        this.tableData = res.data.data
        this.total = this.tableData.length


      })
    },
    getscenicName(scenicName){
      this.$axios.post('/scenic/queryScenicbyName/',{scenicName:scenicName},{
        params:{
          scenicName: this.searchForm.scenicName
        }

      }).then(res=>{
        this.tableData=res.data.data
      })

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
      this.$axios.post('/scenic/deleteScenic/'+dels).then(res => {


        //删除（不用管批量删除）操作
        res.data.data
        this.$message({
          showClose: true,
          message: "删除成功",
          type: "success",
          onClose:() => {
            this.getScenicList()
          }
        })

      })
    },
    editHandle(id){
      this.$axios.post('/scenic/queryScenicById/'+id).then(res => {

        //在编辑框填充数据用的方法

        this.editForm = res.data.data[0]
        this.editForm.regionName = res.data.data[0].region.regionName

        this.dialogVisible2 = true



      })
    }
    ,
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          console.log(formName)
          if(formName === "addForm"){

            //添加操作
            this.$axios.post('/scenic/insertScenic',this.addForm).then(res => {
              this.$message({
                showClose: true,
                message: "添加成功",
                type: "success",
                onClose:() => {
                  this.getScenicList()
                }
              })

              this.dialogVisible = false;
              this.handleClose()


            })
          }else {

            this.$axios.put('/scenic/updateScenic/',this.editForm).then(res => {

              //编辑操作
              this.$message({
                showClose: true,
                message: "编辑成功",
                type: "success",
                onClose:() => {
                  this.getScenicList();
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
      this.delSomeBtnstatu = val.length==0;
    },
    handleAvatarSuccessAdd(res, file) {
      this.addForm.scenicImg = res;
    },
    handleAvatarSuccessEdit(res, file) {
      this.editForm.scenicImg= res;
    }
  },
  data() {
    return {
      current: 1,
      size: 10,
      total: 0,
      searchForm: {
        scenicName: ''
      },
      addForm: {
        regionName:'',
        scenicName:'',
        scenicImg:'',
        price: 0,
        scenicIntroduce:''
      },
      dialogVisible: false,
      editForm: {
        scenicName: '',
        regionName: '',
        price: 0,
        scenicImg: '',
        scenicIntroduce: ''

      },
      dialogVisible2: false,
      delSomeBtnstatu: true ,
      tableData: [],
      FormRules: {
        scenicName: [
          { required: true, message: '请输入景区名称', trigger: 'blur' }
        ],
        regionName: [
          { required: true, message: '请输入所属区域', trigger: 'blur' }
        ],



      },
      multipleSelection: []
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
