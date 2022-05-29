const Mock = require('mockjs')

const Random = Mock.Random

let Result = {
    code: 200,
    reason: '操作成功',
    data: null
}

Mock.mock('/captcha','get',() => {

    Result.data = {
        token: Random.string(32),
        captchaImg: Random.dataImage('100x40',"ch521")
    }

    return Result;
})
Mock.mock('/login','post',() => {

    Result.data = {
        name: '张三',
        password: '123'
    }

    return Result;
})

Mock.mock('/logout','post',() => {

    return Result;
})
Mock.mock('/backstage/userInfo','get',() => {

    Result.data = {
        id: "1",
        account: "爸爸",
        avatar: "https://img.jbzj.com/file_images/article/202004/2020040108440021.jpg",
    }

    return Result;
})

Mock.mock('/menu/nav','get',() => {

    let nav = [
        {
            title: '系统管理',
            name: 'SysManage',
            icon: 'el-icon-s-tools',
            children: [
                {
                    title: '用户管理',
                    name: 'UserManage',
                    icon: 'el-icon-user-solid',
                    component: '/backstage/UserManage',
                    path: '/backstage/userManage',
                },
                {
                    title: '住宿管理',
                    name: 'HotelManage',
                    icon: 'el-icon-s-release',
                    component: '/backstage/HotelManage',
                    path: '/backstage/hotelManage',
                },
                {
                    title: '美食管理',
                    name: 'FoodManage',
                    icon: 'el-icon-s-shop',
                    component: '/backstage/FoodManage',
                    path: '/backstage/foodManage',
                },
                {
                    title: '路线管理',
                    name: 'planManage',
                    icon: 'el-icon-s-promotion',
                    component: '/backstage/PlanManage',
                    path: '/backstage/planManage',
                },
                {
                    title: '景区管理',
                    name: 'scenicManage',
                    icon: 'el-icon-s-flag',
                    component: '/backstage/ScenicManage',
                    path: '/backstage/scenicManage',
                }
            ]
        },
        {
            title: '订单管理',
            name: 'OrderManage',
            icon: 'el-icon-menu',
            children:[
                {
                    title: '景区预定管理',
                    name: 'scenicOrderManage',
                    icon: 'el-icon-tickets',
                    component: '/backstage/order/ScenicOrderManage',
                    path: '/backstage/order/scenicOrderManage',
                },
                {
                    title: '酒店预定管理',
                    name: 'hotelOrderManage',
                    icon: 'el-icon-school',
                    component: '/backstage/order/HotelOrderManage',
                    path: '/backstage/order/hotelOrderManage',
                },
                {
                    title: '美食预定管理',
                    name: 'foodOrderManage',
                    icon: 'el-icon-dish',
                    component: '/backstage/order/FoodOrderManage',
                    path: '/backstage/order/foodOrderManage',
                }
            ]
        }

    ]
    let authoritys = []//权限
    Result.data = {
        nav: nav,
        authoritys: authoritys
    }

    return Result;
})

Mock.mock(RegExp('/user/list'),'get',() => {

    Result.data = [{
        username: '张三',
        account: 'zhangsan',
        email: '4654165316@.com',
        role: '管理员',
        avatar: 'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fup.enterdesk.com%2Fedpic_source%2Fc0%2F8e%2F28%2Fc08e28eb52da5a2b5b451ad6272a8197.jpg&refer=http%3A%2F%2Fup.enterdesk.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1654747170&t=5904a6d1fed20a0e71d300a69c07200f'
    }, {
        username: '李四',
        account: 'lisi',
        email: 'fssasca@.com',
        role: '普通用户',
        avatar: 'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fup.enterdesk.com%2F2021%2Fedpic%2F01%2F4b%2F7d%2F014b7d465ef76e7a3477e749f7be6fc2_1.jpg&refer=http%3A%2F%2Fup.enterdesk.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1654747171&t=84762939c423602661586cafabc3a871'
    }, {
        username: '王五',
        account: 'wangwu',
        email: 'sdsdfsca@.com',
        role: '普通用户',
        avatar: 'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fup.enterdesk.com%2F2021%2Fedpic%2F01%2F4b%2F7d%2F014b7d465ef76e7a3477e749f7be6fc2_1.jpg&refer=http%3A%2F%2Fup.enterdesk.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1654747171&t=84762939c423602661586cafabc3a871'
    }, {
        username: '麻子',
        account: 'mazi',
        email: 'saddsca@.com',
        role: '普通用户',
        avatar: 'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fup.enterdesk.com%2F2021%2Fedpic%2F01%2F4b%2F7d%2F014b7d465ef76e7a3477e749f7be6fc2_1.jpg&refer=http%3A%2F%2Fup.enterdesk.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1654747171&t=84762939c423602661586cafabc3a871'
    }]

    return Result;
})

Mock.mock(RegExp('/user/add'),'get',() => {

    return Result;
})

Mock.mock(RegExp('/user/delete'),'get',() => {

    return Result;
})

Mock.mock(RegExp('/user/edit'),'get',() => {

    return Result;
})

Mock.mock(RegExp('/user/selectOne'),'get',() => {

    Result.data = {
        username: '张三',
        account: 'zhangsan',
        email: 'sssssss@com',
        role: '普通用户'
    }
    return Result;
})
Mock.mock('/user/editData','post',() => {

    Result.data = {
        username: '张三',
        account: 'zhangsan',
        email: 'sssssss@com',
        role: '普通用户',
        avatar: 'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fup.enterdesk.com%2Fedpic_source%2Fc0%2F8e%2F28%2Fc08e28eb52da5a2b5b451ad6272a8197.jpg&refer=http%3A%2F%2Fup.enterdesk.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1654747170&t=5904a6d1fed20a0e71d300a69c07200f'
    }
    return Result;
})

Mock.mock('/file/upload/','post',() => {

    Result.data = {
        username: '张三',
        account: 'zhangsan',
        email: 'sssssss@com',
        role: '普通用户'
    }
    return Result;
})
