import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '../views/Login'
import Food from '../views/reception/Food'
import Hotel from '../views/reception/Hotel'
import Scenic from '../views/reception/Scenic'
import Map from '../views/reception/other/Map'
import Epidemic from '../views/reception/other/Epidemic'
import HomeBack from '../views/backstage/HomeBack'
import Plan from '../views/reception/Plan'
import HomeFront from '../views/reception/HomeFront'
import IndexBack from '../views/backstage/IndexBack'
import axios from "../assets/js/axios";
import store from "../store"
import Register from "../views/Register"
import ChangePwd from '../views/ChangePwd'
import PersonHome from '../views/reception/person/PersonHome'
import PersonFoodOrder from '../views/reception/person/PersonFoodOrder'
import PersonHotelOrder from '../views/reception/person/PersonHotelOrder'
import PersonScenicOrder from '../views/reception/person/PersonScenicOrder'
import CollectionStory from '../views/reception/person/CollectionStory'
import CommentStory from '../views/reception/person/CommentStory'
import HotelDetails from "../views/reception/details/HotelDetails"
import FoodDetails from "../views/reception/details/FoodDetails"
import PlanDetails from "../views/reception/details/PlanDetails"
import ScenicDetails from "../views/reception/details/ScenicDetails"
import UploadAvatar from '../views/reception/person/UploadAvatar'
import ResetPwd from '../views/reception/person/ResetPwd'

Vue.use(VueRouter)

//以下a、b两种加载形式都可以
const routes = [
  {
    path: '/homeBack',
    name: 'homeBack',
    component: HomeBack,
    children: [
      {
        path: '/backstage/indexBack',
        name: 'indexBack',
        component: IndexBack
      },
    ]
  },
  {   //a:在开头import时就加载了
    path: '/login',
    name: 'login',
    component: Login
  },
  {   //a:在开头import时就加载了
    path: '/register',
    name: 'register',
    component: Register
  },
  {   //a:在开头import时就加载了
    path: '/changePwd',
    name: 'changePwd',
    component: ChangePwd
  },
  {
    path: '/',
    name: 'homeFront',
    component: HomeFront,
    children: [

      {    //b:懒加载的形式
        path: '/reception/index',
        name: 'index',
        component: () => import('../views/reception/Index')
      },
      {
        path: '/reception/plan',
        name: 'plan',
        component: Plan
      },
      {
        path: '/reception/food',
        name: 'food',
        component: Food
      },
      {
        path: '/reception/hotel',
        name: 'hotel',
        component: Hotel
      },
      {
        path: '/reception/scenic',
        name: 'scenic',
        component: Scenic
      },
      {
        path: '/reception/other/map',
        name: 'map',
        component: Map
      },
      {
        path: '/reception/other/epidemic',
        name: 'epidemic',
        component: Epidemic
      },
      {
        path: '/reception/person/personHome',
        name: 'personHome',
        component: PersonHome,
        children:[
          {
            path: '/reception/person/personFoodOrder',
            name: 'personFoodOrder',
            component: PersonFoodOrder
          },
          {
            path: '/reception/person/personHotelOrder',
            name: 'personHotelOrder',
            component: PersonHotelOrder
          },
          {
            path: '/reception/person/personScenicOrder',
            name: 'personScenicOrder',
            component: PersonScenicOrder
          },
          {
            path: '/reception/person/collectionStory',
            name: 'collectionStory',
            component: CollectionStory
          },
          {
            path: '/reception/person/commentStory',
            name: 'commentStory',
            component: CommentStory
          },{
            path: '/reception/person/resetPwd',
            name: 'resetPwd',
            component: ResetPwd
          },{
            path: '/reception/person/uploadAvatar',
            name: 'uploadAvatar',
            component: UploadAvatar
          }
        ]
      },{
        path: '/scenicDetails',
        name: 'scenicDetails',
        component: ScenicDetails
      },
      {
        path: '/planDetails',
        name: 'planDetails',
        component: PlanDetails
      },
      {    //b:懒加载的形式
        path: '/hotelDetails',
        name: 'hotelDetails',
        component: HotelDetails
      },
      {
        path: '/foodDetails',
        name: 'foodDetails',
        component: FoodDetails
      },
    ]
  }
]


const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})


router.beforeEach((to, from, next)=>{

  let hasRoute = store.state.menus.hasRoutes
  let token = localStorage.getItem("token")

  // console.info("登录")
  // console.info(token)

  if(token !== null){
    if(!hasRoute){
      axios.post("/menu/userPermission",{
        account: token
      }).then(res => {

        // console.info(res.data.data)
        //拿到用户权限
        store.commit('setPermList',res.data.data)
      })



      axios.post("/menu/adminMenu",{
        account: token
      }).then(res => {

        // console.info(res.data.data)

        //拿到menuList
        store.commit('setMenuList',res.data.data)

        //动态绑定路由
        let newRoutes = router.options.routes

        res.data.data.forEach(menu => {

          if(menu.children){
            menu.children.forEach(one => {

              //生成转换成路由
              let route1 = menuToRoute(one)

              //将路由添加到路由管理器中
              if(route1){
                newRoutes[0].children.push(route1)
              }

            })
          }

        })
        router.addRoutes(newRoutes)


        hasRoute = true
        store.commit('changeRouteStatus',hasRoute)
      })
    }
  }

  next()
})


//导航装换成路由方法
const menuToRoute = (menu) => {
  if(!menu.component){
    return null;
  }else {
    let route = {
      name: menu.name,
      path: menu.path,
      meta: {
        icon: menu.icon,
        title: menu.title
      }
    }

    route.component = () => import('@/views'+menu.component+'.vue')
    return route

  }
}

export default router


