<template>

  <div style="width: 1000px;height:500px;align-items: center;">
    <h3 style="margin-bottom: 5px;margin-top: 0px"><i class="el-icon-user-solid"></i>个人中心</h3>
    <el-container>
      <el-aside width="150px">
        <el-menu
            default-active="1"
            class="el-menu-vertical-demo"
            background-color="#eaecee"
            active-text-color="ffb700">

          <router-link to="/reception/person/collectionStory">
            <el-menu-item index="1">
              <i class="el-icon-collection-tag"></i>
              <span slot="title">收藏夹</span>
            </el-menu-item>
          </router-link>

          <router-link to="/reception/person/commentStory">
            <el-menu-item index="2">
              <i class="el-icon-chat-line-round"></i>
              <span slot="title">评论历史</span>
            </el-menu-item>
          </router-link>

          <router-link to="/reception/person/resetPwd">
            <el-menu-item index="3">
              <i class="el-icon-setting"></i>
              <span slot="title">重置密码</span>
            </el-menu-item>
          </router-link>

          <router-link to="/reception/person/uploadAvatar">
            <el-menu-item index="4">
              <i class="el-icon-picture-outline-round"></i>
              <span slot="title">上传头像</span>
            </el-menu-item>
          </router-link>

          <el-submenu index="5">
            <template slot="title">
              <i class="el-icon-menu"></i>
              <span>订单</span>
            </template>
            <router-link to="/reception/person/personFoodOrder">
              <el-menu-item index="5-1">
                <i class="el-icon-dish"></i>
                <span slot="title">美食订单</span>
              </el-menu-item>
            </router-link>

            <router-link to="/reception/person/personScenicOrder">
              <el-menu-item index="5-2">
                <i class="el-icon-tickets"></i>
                <span slot="title">门票订单</span>
              </el-menu-item>
            </router-link>

            <router-link to="/reception/person/personHotelOrder">
              <el-menu-item index="5-3">
                <i class="el-icon-school"></i>
                <span slot="title">酒店订单</span>
              </el-menu-item>
            </router-link>
          </el-submenu>

        </el-menu>
      </el-aside>
      <el-main style="background-color: #eaecee">
        <router-view/>
      </el-main>
    </el-container>
  </div>
</template>

<script>
export default {
  name: "PersonHome"
}
</script>

<style scoped>
a{
  text-decoration: none;
}
.el-container{
  height: 100%;
}
.el-aside {
  background-color: #eaecee;
  color: black;
  line-height: 500px;
}
</style>