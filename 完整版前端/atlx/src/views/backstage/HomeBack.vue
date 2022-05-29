<template>
  <el-container>
    <el-aside width="200px">
      <AsideMenu/>
    </el-aside>
    <el-container>
      <el-header>
        <strong style="font-size: 22px">安途旅行后台管理</strong>
        <div class="header-avatar">
          <el-avatar size="medium" :src="userinfo.avatar" v-if="hasAuth('admin:query')"></el-avatar>
          <el-dropdown>
  <span class="el-dropdown-link" style="color: #fafafa" v-if="isLogin()">
    {{ userinfo.account }}<i class="el-icon-arrow-down el-icon--right"></i>
  </span>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item @click.native="logout">退出</el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>

          <el-link href="/reception/index" title="前台" style="color: #fafafa"><i class="el-icon-message-solid
"></i>去前台</el-link>
          <el-link :underline="false" href="/login" style="text-decoration: none;margin-left: 25px" v-if="!isLogin()">注册/登录</el-link>
        </div>
      </el-header>

      <el-main v-if="hasAuth('admin:query')">
        <Tabs></Tabs>
        <div style="margin: 0 15px;">
          <router-view/>
        </div>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
import Tabs from '@/components/menu/Tabs.vue'
import AsideMenu from '@/components/menu/AsideMenu.vue'

export default {
  name: "HomeBack",
  components:{
    Tabs,AsideMenu
  },
  data(){
    return{
      userinfo: {
        id: '',
        account: '',
        avatar: ''
      },
    }
  },
  created() {
    this.getUserInfo();
  },
  methods: {
    getUserInfo(){
      var account = localStorage.getItem("token")
      if(account !==null && account !== ''){
        this.$axios.post("/user/userInfo", {
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
    }
  }
}
</script>

<style scoped>

.header-avatar{
  float: right;
  width: 210px;
  display: flex;
  justify-content: space-around;
  align-items: center;

}

.el-dropdown-link {
  cursor: pointer;
}

.el-header{
  background-color: #61739e;
  color: #333;
  text-align: center;
  line-height: 60px;
}

.el-aside {
  background-color: #D3DCE6;
  color: #333;
  line-height: 200px;
}

.el-main {
  background-color: #E9EEF3;
  color: #333;
  padding: 0;
}

.el-container{
  margin: 0;
  padding: 0;
  height: 100%;
}
.el-menu-vertical-demo{
  height: 100%;
}
a{
  text-decoration: none;
}
</style>