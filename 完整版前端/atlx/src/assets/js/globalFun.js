import Vue from 'vue'
Vue.mixin({
    methods: {
        hasAuth(perm) {
            var authority = this.$store.state.menus.permList
            // console.info("==========")
            // console.log(authority)
            return authority.indexOf(perm) > -1
        },
        isLogin() {
            var account = localStorage.getItem("token")
            if(!account){
                return false;
            }else {
                return true;
            }
        }
    }
})