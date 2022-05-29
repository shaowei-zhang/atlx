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

      <!--      这里是搜索框-->
      <el-form-item style="float: right;margin-right: 0px;">
        <el-input
            size="small"
            v-model="searchForm.hotelName"
            placeholder="请输酒店名"
            clearable
        ></el-input>
      </el-form-item>
      <el-form-item style="float: right;" >
        <el-button plain @click="getHotelName(searchForm.hotelName)" size="small"><i class="el-icon-search"></i></el-button>
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
          label="ID"
          v-if="false"
          width="60">
      </el-table-column>

      <el-table-column
          prop="hotelName"
          label="酒店名称"
          width="120">
      </el-table-column>

      <el-table-column
          prop="hotelAddr"
          label="地址"
          width="120"
      >
      </el-table-column>

      <el-table-column
          prop="hotelRemark"
          label="描述"
          width="300"
      >
      </el-table-column>

      <el-table-column
          prop="region.regionName"
          label="地区"
          width="120"
      >
      </el-table-column>

      <el-table-column
          prop="price"
          label="价格"
          width="120">
      </el-table-column>

      <el-table-column label="图片" align="center" prop="hotelImg">
        <template slot-scope="scope">
          <img :src="scope.row.hotelImg" width="50" height="50" style="border-radius: 2px"/>
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
        :page-sizes="[5,10,15,20,25]"
        :page-size="size"
        :total="total">
    </el-pagination>

    <!-------------------------------------------- 新增对话框 ----------------------------------------->
    <el-dialog
        title="添加旅馆"
        :visible.sync="dialogVisible"
        width="450px"
        :before-close="handleClose"
    >
      <el-form :model="addForm" ref="addForm" :rules="FormRules" label-width="100px" class="demo-addForm">
        <el-form-item label="旅馆名称" prop="hotelName" style="width:350px;">
          <el-input v-model="addForm.hotelName"></el-input>
        </el-form-item>
        <el-form-item label="地址" prop="hotelAddr" style="width:350px;">
          <el-input v-model="addForm.hotelAddr"></el-input>
        </el-form-item>
        <el-form-item label="描述" prop="hotelRemark" style="width:350px;">
          <el-input type="text" v-model="addForm.hotelRemark" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="价格" prop="hotelRemark" style="width:350px;">
          <el-input type="price" v-model="addForm.price" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="地区ID" prop="regionId" style="width:350px;">
          <el-input type="text" v-model="addForm.regionId" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="上传酒店图片">
          <el-upload
              class="avatar-uploader"
              action="http://localhost:8088/hotel/hotelImg"
              :show-file-list="false"
              :on-success="handleAvatarSuccessAdd">
            <img v-if="addForm.hotelImg" :src="addForm.hotelImg" class="avatar">
            <i v-else class="el-icon-loading avatar-uploader-icon"></i>
          </el-upload>
        </el-form-item>

        <el-form-item>
          <el-button type="primary" @click="submitForm('addForm')">确定</el-button>
          <el-button @click="resetForm('addForm')">重置</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>


    <!---------------------------------------编辑对话框--------------------------------------->
    <el-dialog
        title="编辑旅馆"
        :visible.sync="dialogVisible2"
        width="450px"
        :before-close="handleClose2"
    >

      <el-form :model="editForm" ref="editForm" :rules="FormRules" label-width="100px" class="demo-editForm">

        <el-form-item label="旅馆名称" prop="hotelName" style="width:350px;">
          <el-input v-model="editForm.hotelName"></el-input>
        </el-form-item>
        <el-form-item label="地址" prop="hotelAddr" style="width:350px;">
          <el-input v-model="editForm.hotelAddr"></el-input>
        </el-form-item>
        <el-form-item label="价格" prop="hotelAddr" style="width:350px;">
          <el-input v-model="editForm.price"></el-input>
        </el-form-item>
        <el-form-item label="描述" prop="hotelRemark" style="width:350px;">
          <el-input type="text" v-model="editForm.hotelRemark" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="地区ID" prop="regionId" style="width:350px;">
          <el-input type="text" v-model="editForm.regionId" autocomplete="off"></el-input>
        </el-form-item>

        <el-form-item label="上传酒店图片">
          <el-upload
              class="avatar-uploader"
              action="http://localhost:8088/hotel/hotelImg"
              :show-file-list="false"
              :on-success="handleAvatarSuccessEdit">
            <img v-if="editForm.hotelImg" :src="editForm.hotelImg" class="avatar">
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
  name: "HotelManage",
  created() {
    this.getHotelList();
  },
  methods: {
    getHotelList(){
      this.$axios.get('/hotel/hotelList').then(res => {

        console.log(res.data.data)
        //搜索或查询列表展示
        this.tableData = res.data.data
        this.total = this.tableData.length
      })
    },

    /*
    * 这是搜索框可以进行单个查询的
    * */

    getHotelName(hotelName) {
      this.$axios.post('/hotel/queryByMap',{hotelName:hotelName}, {
        params: {
          hotelName: this.searchForm.hotelName
        }
      }).then(res => {
        //搜索或查询列表展示
        this.tableData = res.data.data
      })


    },





    delHandle(id){
      let dels = []
      console.log(dels);
      if(id){
        dels.push(id)
      }else {
        this.multipleSelection.forEach(row => {
          dels.push(row.id)
        })
      }
      this.$axios.post('/hotel/delHotel/'+dels).then(res => {


        //删除（不用管批量删除）操作
        this.$message({
          showClose: true,
          message: "删除成功",
          type: "success",
          onClose:() => {
            this.getHotelList()
          }
        })



      })
    },

    /*
    *
    * 将表中的数据提出到editform 中
    *
    *
    * */
    editHandle(id){
      let editId = []
      if(id){
        editId.push(id)
      }
      this.$axios.get('/hotel/queryHotelById/'+editId).then(res => {

        //在编辑框填充数据用的方法
        this.editForm = res.data.data
        this.dialogVisible2 = true

      })
    },

    /*
    *
    *
    *
    * 增加功能
    *
    *
    * */

    submitForm(formName) {
      var that=this
      this.$refs[formName].validate((valid) => {
        if (valid) {
          console.log(formName)
          if(formName === "addForm"){


            //添加操作
            this.$axios.post('/hotel/addHotel',that.addForm).then(res => {
              this.$message({
                showClose: true,
                message: "添加成功",
                type: "success",
                onClose:() => {
                  this.getHotelList()
                }
              })

              this.dialogVisible = false;
              this.handleClose()


            })
          }else {
            this.$axios.put('/hotel/updateHotel',that.editForm).then(res => {

              //编辑操作
              this.$message({
                showClose: true,
                message: "编辑成功",
                type: "success",
                onClose:() => {
                  this.getHotelList();
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
      this.addForm.hotelImg = res;
    },
    handleAvatarSuccessEdit(res, file) {
      this.editForm.hotelImg = res;
    }
  },
  data() {
    return {
      current: 1,
      size: 5,
      total: 0,
      searchForm: {
        hotelName: ''
      },
      addForm: {
        id:'',
        hotelName: '',
        hotelAddr: '',
        price: 0,
        hotelRemark: '',
        regionId: '',
        hotelImg: ''
      },
      dialogVisible: false,
      editForm: {
        hotelName:'',
        price: 0,
        hotelAddr:'',
        hotelRemark:'',
        regionId: '',
        hotelImg: ''
      },
      dialogVisible2: false,
      delSomeBtnstatu: true ,
      tableData: [],
      FormRules: {
        hotelName: [
          { required: true, message: '请输入酒店名称', trigger: 'blur' }
        ],
        hotelAddr: [
          { required: true, message: '请输入地址', trigger: 'blur' },
        ],
        hotelRemark: [
          { required: true, message: '请输入描述', trigger: 'blur' }
        ],
        RegionName: [
          { required: true, message: '请输入地区', trigger: 'blur' }
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