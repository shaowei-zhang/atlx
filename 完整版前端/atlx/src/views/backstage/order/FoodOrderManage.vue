<template>
  <div>
    <el-form :inline="true">

      <el-form-item>
        <el-popconfirm title="确定删除吗？" @confirm="delHandle(null)">
          <el-button type="danger" size="small" slot="reference" :disabled="delSomeBtnstatu">批量删除</el-button>
        </el-popconfirm>
      </el-form-item>

      <el-form-item style="float: right;margin-right: 0px;">
        <el-input
            size="small"
            v-model="searchForm.account"
            placeholder="请输入用户名"
            clearable
        ></el-input>
      </el-form-item>
      <el-form-item style="float: right;margin-right: 2.5px;" >
        <el-button type="info" plain @click="getUserList" size="small"><i class="el-icon-search"></i></el-button>
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
          label="订单ID"
          width="100">
      </el-table-column>

      <el-table-column
          prop="number"
          label="订单编号"
          width="250">
      </el-table-column>

      <el-table-column
          prop="foodLocal.foodName"
          label="美食名称"
          width="150"
      >
      </el-table-column>

      <el-table-column
          prop="placeOrderDatetime"
          label="下单时间"
          :formatter="dateFormat"
          width="150"
      >
      </el-table-column>

      <el-table-column
          prop="user.account"
          label="用户名"
          width="150"
      >
      </el-table-column>

      <el-table-column label="美食图片" align="center" width="80" prop="foodLocal.foodImg">
        <template slot-scope="scope">
          <img :src="scope.row.foodLocal.foodImg" width="50" height="50" style="border-radius: 2px"/>
        </template>
      </el-table-column>

      <el-table-column prop="caozuo" label="操作">
        <template slot-scope="scope">
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

  </div>
</template>
<script>
export default {
  name: "FoodOrderManage",
  created() {
    this.getUserList();
  },
  methods: {
    getUserList(){
      this.$axios.post('/foodOrder/orderList',{
        account: this.searchForm.account
      }).then(res => {

        // 搜索或查询列表展示
        this.tableData = res.data.data
        this.total = this.tableData.length


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
      this.$axios.post('/foodOrder/delOrder/'+dels).then(res => {

        //删除（不用管批量删除）操作
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
    dateFormat(row, column){
      const daterc = row[column.property]
      if(daterc!=null){
        const dateMat= new Date(daterc);
        const year = dateMat.getFullYear();
        const month = dateMat.getMonth() + 1;
        const day = dateMat.getDate();
        const hh = dateMat.getHours();
        const mm = dateMat.getMinutes();
        const ss = dateMat.getSeconds();
        return `${year}-${month}-${day} ${hh}:${mm}:${ss}`;
      }

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
        account: ''
      },
      delSomeBtnstatu: true ,
      tableData: [],
      multipleSelection: []
    }
  }
}
</script>

<style>
.el-pagination {
  margin-top: 5px;
  margin-left: 50px;
}
</style>