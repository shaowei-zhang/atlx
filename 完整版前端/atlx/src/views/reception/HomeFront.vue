<template>
  <el-container>
    <el-header>
      <NavMenu/>
    </el-header>

    <el-main>
      <router-view/>
    </el-main>
  </el-container>
</template>

<script>
import NavMenu from '@/components/menu/NavMenu.vue'
export default {
  name: "HomeFront",
  components:{
    NavMenu
  }
}
</script>

<style scoped>
.el-container{
  margin: 0;
  padding: 0;
  height: 100%;
  white-space: nowrap;
  align-items: center;
}
.el-header {
  width: 100%;
  color: #333;
  line-height: 5px;
  /*background-color: red;*/
  padding: 0;
  padding-bottom: 0;
}
/*注意样式最好添加scoped，防止污染其他界面*/
  ::-webkit-scrollbar {
  /*滚动条宽高，如果不需要显示滚动条可设置宽高为0*/
  width: 0px;
    height: 0px;
  }
::-webkit-scrollbar-thumb {
/*滚动条颜色和圆角*/
background-color: #dbd9d9;
  border-radius: 3px;
}
</style>