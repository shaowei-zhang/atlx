<template>
  <div>
    <el-form :inline="true">

      <el-form-item>
        <el-popconfirm title="确定删除吗？" @confirm="delHandle(null)">
          <el-button type="danger" size="small" slot="reference" :disabled="delSomeBtnstatu">批量删除</el-button>
        </el-popconfirm>
      </el-form-item>

    </el-form>

    <el-table
        ref="multipleTable"
        :data="tableData"
        tooltip-effect="dark"
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
          width="100"
          v-if="false"
      >
      </el-table-column>

      <el-table-column
          prop="number"
          label="订单编号"
          width="100">
      </el-table-column>

      <el-table-column
          prop="hotel.hotelName"
          label="酒店名称"
          width="120"
      >
      </el-table-column>

      <el-table-column
          prop="hotel.hotelAddr"
          label="酒店地址"
          width="120"
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
          prop="hotel.price"
          label="价格"
          width="100"
      >
      </el-table-column>


      <el-table-column prop="caozuo" label="操作">
        <template slot-scope="scope">
          <el-popconfirm title="确定取消吗？" style="margin-left: 10px"  @confirm="delHandle(scope.row.id)">
            <el-button type="danger" size="mini" slot="reference">取消</el-button>
          </el-popconfirm>
          <el-button type="success" size="mini" slot="reference" @click="pay(scope.row)"  style="margin-left: 10px">支付</el-button>
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
  name: "PersonHotelOrder",
  created() {
    this.getUserList();
  },
  methods: {
    getUserList(){
      this.$axios.post('/hotelOrder/personOrderList',{
        account: localStorage.getItem('token')
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
      this.$axios.post('/hotelOrder/delOrder/'+dels).then(res => {

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
    pay(row){
      window.open(`http://localhost:8088/alipay/pay?subject=${row.hotel.hotelName}&traceNo=${row.number}&totalAmount=${row.hotel.price}`);
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