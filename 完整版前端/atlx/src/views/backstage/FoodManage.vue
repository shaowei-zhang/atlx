<template>
<div>
  <el-row :gutter="20">
    <el-col :span="24">
        <div>
          <!--####################  头部的批量删除 ，新增和搜索  ###########################-->
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
                  v-model="searchForm.foodName"
                  placeholder="请输入菜名"
                  clearable
              ></el-input>
            </el-form-item>
            <el-form-item style="float: right;" >
              <el-button plain @click="getUserByName(searchForm.foodName)" size="small"><i class="el-icon-search"></i></el-button>
            </el-form-item>
          </el-form>
          <!--####################  end ###########################-->
          <!--####################  表格管理  ###########################-->
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
                label="id"
                width="100"
                v-if="false"
            >
            </el-table-column>

            <el-table-column
                prop="region.regionName"
                label="地区名"
                width="120">
            </el-table-column>

            <el-table-column
                prop="foodName"
                label="美食名称"
                width="120"
                current-row-key="id"
            >
            </el-table-column>
            <el-table-column
                prop="foodClass"
                label="美食类别"
                width="120"
                current-row-key="id"
            >
            </el-table-column>
            <el-table-column
                prop="foodIntroduce"
                label="美食介绍"
                width="400"
                current-row-key="id"
            >
            </el-table-column>

            <el-table-column
                prop="price"
                label="价格"
                width="100"
                current-row-key="id"
            >
            </el-table-column>

            <el-table-column label="美食图片" align="center" width="80" prop="foodImg">
              <template slot-scope="scope">
                <img :src="scope.row.foodImg" width="40" height="40" style="border-radius: 2px"/>
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
              background
              layout="prev, pager, next"
              :total="total"
              :page-size= "4"
              @current-change="page">
          </el-pagination>
          <!--####################  end  ###########################-->
          <!--####################  新增对话框  ###########################-->
          <el-dialog
              title="添加用户"
              :visible.sync="dialogVisible"
              width="450px"
              :before-close="handleClose"
          >
            <el-form :model="addForm" ref="addForm"  label-width="100px" class="demo-addForm">

              <el-form-item label="美食名" prop="foodName" style="width:350px;">
                <el-input v-model="addForm.foodName"></el-input>
              </el-form-item>
              <el-form-item label="价格" prop="price" style="width:350px;">
                <el-input v-model="addForm.price"></el-input>
              </el-form-item>

            <!--=========================下拉框显示地名==================-->
              <el-form-item label="地区名" prop="region.regionName" style="width:350px;">
                <el-select v-model="addForm.regionName" placeholder="活动区域">
                  <el-option label="芜湖" value="芜湖"></el-option>
                  <el-option label="北京" value="北京"></el-option>
                  <el-option label="蒙城" value="蒙城"></el-option>
                </el-select>
              </el-form-item>
              <!--=========================end==================-->
              <!--=========================下拉框显示食物分类==================-->
              <el-form-item label="美食类别" prop="foodClass" style="width:350px;">
                <el-select v-model="addForm.foodClass" placeholder="请输入分类内容">
                  <el-option label="面食类" value="面食类"></el-option>
                  <el-option label="肉食类" value="肉食类"></el-option>
                  <el-option label="鱼类" value="鱼类"></el-option>
                  <el-option label="汤水类" value="汤水类"></el-option>
                  <el-option label="烧烤类" value="烧烤类"></el-option>
                  <el-option label="小吃快餐类" value="小吃快餐类"></el-option>
                  <el-option label="火锅" value="火锅"></el-option>
                  <el-option label="甜点饮品" value="甜点饮品"></el-option>
                </el-select>
              </el-form-item>
              <el-form-item label="上传美食图片">
                <el-upload
                    class="avatar-uploader"
                    action="http://localhost:8088/food/uploadFoodImg/"
                    :show-file-list="false"
                    :on-success="handleAvatarSuccessAdd">
                  <img v-if="addForm.foodImg" :src="addForm.foodImg" class="avatar">
                  <i v-else class="el-icon-loading avatar-uploader-icon"></i>
                </el-upload>
              </el-form-item>
              <el-form-item>
                <el-button type="primary" @click="submitForm('addForm')">确定</el-button>
                <el-button @click="resetForm('addForm')">重置</el-button>
              </el-form-item>
            </el-form>
          </el-dialog>
          <!--####################  编辑对话框  ###########################-->
          <el-dialog
              title="编辑用户"
              :visible.sync="dialogVisible2"
              width="450px"
              :before-close="handleClose2"
          >
            <el-form :model="editForm" ref="editForm"  label-width="100px" class="demo-editForm">
             <!--=========================下拉框显示地名==================-->
              <el-form-item label="地区名" prop="region.regionName" style="width:350px;">
                <el-select v-model="editForm.regionName" placeholder="活动区域">
                  <el-option label="芜湖" value="芜湖"></el-option>
                  <el-option label="北京" value="北京"></el-option>
                  <el-option label="蒙城" value="蒙城"></el-option>
                </el-select>
              </el-form-item>
              <!--=========================end==================-->
              <el-form-item label="点击修改">
                <el-upload
                    class="avatar-uploader"
                    action="http://localhost:8088/food/uploadFoodImg/"
                    :show-file-list="false"
                    :on-success="handleAvatarSuccessEdit">
                  <img v-if="editForm.foodImg" :src="editForm.foodImg" class="avatar">
                  <i v-else class="el-icon-loading avatar-uploader-icon"></i>
                </el-upload>
              </el-form-item>
              <el-form-item label="美食名称" prop="foodName" style="width:350px;">
                <el-input v-model="editForm.foodName"></el-input>
              </el-form-item>
              <el-form-item label="价格" prop="foodName" style="width:350px;">
                <el-input v-model="editForm.price"></el-input>
              </el-form-item>
              <el-form-item label="分类" prop="foodClass" style="width:350px;">
                <el-input v-model="editForm.foodClass"></el-input>
              </el-form-item>
              <el-form-item label="美食介绍" prop="foodIntroduce" style="width:350px;">
                <el-input v-model="editForm.foodIntroduce"></el-input>
              </el-form-item>
              <el-form-item>
                <el-button type="primary" @click="submitForm('editForm')">确定</el-button>
                <el-button @click="resetForm('editForm')">重置</el-button>
              </el-form-item>
            </el-form>
          </el-dialog>
        </div>
    </el-col>

  </el-row>
</div>
</template>

<script>
export default {
  name: "FoodManage",
  created() {
    this.getUserList();
  },
  methods: {
    /*
    * 查询
    *
    * */
    //当被点击页数的时候，跳转
    page(currPage){
      var that=this;
      this.$axios.get('/food/queryAllMessage/'+(currPage-1)*4+'/4').then(res => {
        that.tableData=res.data.data
      })
    },
    /*
    * 第一页的初始值
    * */
    getUserList(){
      this.$axios.get('/food/queryAllMessage/'+0+'/'+4).then(res => {
        //搜索或查询列表展示
        this.tableData = res.data.data
      })
      /*
      * 查询数据的总共的数量，将它放在total中，进行分页
      * */
      this.$axios.get('/food/queryFoodLocalLength').then(res => {
        this.total = res.data.data.length
      })
    },
    /*
    * 单个的查询
    *
    * */
    getUserByName(foodName) {
      this.$axios.post('/food/queryByMap/',{foodName:foodName}, {
        params: {
          foodName: this.searchForm.foodName
        }
      }).then(res => {
        //搜索或查询列表展示
        this.tableData = res.data.data
      })
    },
    /*
    * 删除操作，可以进行单个的或者多个的
    * */
    delHandle(id){
      let dels = []
      if(id){
        dels.push(id)
      }else {
        this.multipleSelection.forEach(row => {
          dels.push(row.id)
        })
      }
      this.$axios.delete('/food/deleteFoodLocalById/'+dels).then(res => {
        //删除（不用管批量删除）操作
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
    /*
    *
    * 这是对数据进行编辑
    * */
    editHandle(id){
      let editId= []
      if(id){
        editId.push(id)
      }
      // 想要编辑的话首相要做的是，从数据库中取出数据填充到表单中
      this.$axios.get('/food/queryById/'+editId).then(res => {
        this.editForm = res.data.data
        this.dialogVisible2 = true
      })
    },
    /*
    * 提交表单
    *
    * 1：提交新增的表单
    * 2。提交编辑过后的表单
    *
    * */
    submitForm(formName) {
      var that=this;
      this.$refs[formName].validate((valid) => {
        if (valid) {
          console.log(formName)
          if(formName === "addForm"){
            // 1：提交新增的表单
            this.$axios.post('/food/insertFoodLocal',that.addForm).then(res => {
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
            // 2。提交编辑过后的表单
            this.$axios.put('/food/updateFoodLocal',that.editForm).then(res => {
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
    handleSelectionChange(val) {
      this.multipleSelection = val
      this.delSomeBtnstatu = val.length==0;
    },
    handleAvatarSuccessAdd(res, file) {
      this.addForm.foodImg = res;
    },
    handleAvatarSuccessEdit(res, file) {
      this.editForm.foodImg = res;
    }
  },
  /*
  * data数据
  *
  *
  * */

  data() {
    return {
      total:0,
      searchForm: {
       foodName:''
      },
      addForm: {
        regionName:'',
        foodName: '',
        foodClass:'',
        foodIntroduce:'',
        price: 0,
        foodImg:''
      },
      dialogVisible: false,
      editForm: {
        id:'',
        regionName:'',
        price: 0,
        foodName: '',
        foodClass:'',
        foodIntroduce:'',
        foodImg:'',
      },
      dialogVisible2: false,
      delSomeBtnstatu: true ,
      tableData: [{
      }
      ],
      multipleSelection: []
    };
  }
}
</script>

<style scoped>
.el-row {
  margin-bottom: 20px;
}
:last-child {
   margin-bottom: 0;
 }
.el-col {
  border-radius: 4px;
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


/*这里放一些卡片来显示信息*/
.text {
  font-size: 14px;

}

.item {
  padding: 18px 0;
}

.box-card {
  width: 100px;
  height: 100px;
  background-color: #99a9bf;
  margin: 10px 10px 10px 10px;
}
/*这里放一些卡片来显示信息*/


/*这个样式是组长负责的，看不懂。。。。*/
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
  font-size: 15px;
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
/*这个样式是组长负责的，看不懂。。。。*/

/*=========这是地区名的下来框设计*/
.el-dropdown-link {
  cursor: pointer;
  color: #409EFF;
}
.el-icon-arrow-down {
  font-size: 12px;
}
/*=========这是地区名的下来框设计*/
</style>