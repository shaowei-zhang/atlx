<template>
  <el-menu
      :default-active="activeIndex2"
      class="el-menu-demo"
      mode="horizontal"
      @select="handleSelect"
      background-color="#61739e"
      text-color="#fff"
      active-text-color="#ffd04b">
    <el-row class="menu-left" :span="3">
      <h1>安途旅行</h1>
    </el-row>
    <el-row class="menu-mid">
      <router-link to="/reception/index"><el-menu-item index="1">首页</el-menu-item></router-link>
      <router-link to="/reception/scenic"><el-menu-item index="2">景区信息</el-menu-item></router-link>
      <router-link to="/reception/plan"><el-menu-item index="3">路线信息</el-menu-item></router-link>
      <router-link to="/reception/hotel"><el-menu-item index="4">酒店信息</el-menu-item></router-link>
      <router-link to="/reception/food"><el-menu-item index="5">美食信息</el-menu-item></router-link>
      <el-submenu index="5">
        <template slot="title" style="width: 25px">地图信息</template>
        <router-link to="/reception/other/map" v-if="isLogin()"><el-menu-item index="6-1">路线导航地图</el-menu-item></router-link>
        <el-menu-item index="6-1" v-if="!isLogin()" @click="tishidenglu">路线导航地图</el-menu-item>
        <router-link to="/reception/other/epidemic" v-if="isLogin()"><el-menu-item index="6-2">疫情风险地图</el-menu-item></router-link>
        <el-menu-item index="6-2" v-if="!isLogin()" @click="tishidenglu">疫情风险地图</el-menu-item>
      </el-submenu>
    </el-row>
    <el-row class="menu-right">
      <strong v-if="isLogin()">欢迎你：</strong><h3 style="margin-right: 25px;color: #515152">{{userinfo.account}}</h3>
      <el-dropdown>
  <span class="el-dropdown-link">
    <el-avatar :src="userinfo.avatar" size="medium" v-if="isLogin()"></el-avatar>
    <i class="el-icon-caret-bottom el-icon--right" v-if="isLogin()"></i>
  </span>
        <el-dropdown-menu slot="dropdown">
         <router-link to="/backstage/indexBack"><el-dropdown-item v-if="hasAuth('admin:query')">后台</el-dropdown-item></router-link>
          <router-link to="/reception/person/collectionStory">
            <el-dropdown-item v-if="hasAuth('user:query')">个人中心</el-dropdown-item>
          </router-link>
        </el-dropdown-menu>
      </el-dropdown>
      <el-row style="align-items: center">
        <el-link :underline="false" href="/login" style="text-decoration: none;margin-left: 75px" v-if="!isLogin()">注册/登录</el-link>
        <el-link :underline="false" @click="logout" style="text-decoration: none;margin-left: 55px;font-size: 15px" v-if="isLogin()">退出<i class="el-icon-caret-right"></i></el-link>
      </el-row>
    </el-row>
  </el-menu>
</template>

<script>
export default {
  data() {
    return {
      activeIndex: '1',
      activeIndex2: '1',
      userinfo: {
        id: "",
        account: "",
        avatar: ""
      }
    };
  },
  created() {
    this.getUserInfo();
  },
  methods: {
    handleSelect(key, keyPath) {
      console.log(key, keyPath);
    },
    getUserInfo(){
      var account = localStorage.getItem("token")
      if(account !==null && account !== ''){
        this.$axios.post("/user/userInfo",{
          account: account
        }).then(res => {
          this.userinfo = res.data.data
        })
      }
    },
    logout() {
      this.$axios.post('/login/logout').then(res => {
        localStorage.clear();
        sessionStorage.clear()
        this.$store.commit("resetState")
        this.$router.push('/login')
      })
    },
    tishidenglu(){
      this.$message({
        showClose: true,
        message: "请登录",
        type: "success",
      })
    }
  }
}

</script>

<style scoped>

.el-dropdown-link {
  display: flex;
  align-items: center;
  cursor: pointer;
  color: white;
}
.menu-left{
  margin-left: 200px;
  margin-right: 50px;
}
.el-menu-demo,.menu-mid{
  padding: 0;
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100%;
}

.menu-right{
  margin-left: 100px;
  display: flex;
  height: 50px;
  align-items: center;
}
.el-dropdown-link {
  cursor: pointer;
  color: #409EFF;
}

.el-menu.el-menu--horizontal{
  border: none;
}

a{
  text-decoration: none;
}
/deep/.el-icon-arrow-down{
  display: none;
}
</style>