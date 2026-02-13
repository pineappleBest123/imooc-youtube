
# Vue Router
Vue Router 是 Vue.js 官方的路由管理器。它与 Vue.js 核心深度集成，
允许你通过简单的配置就可以为 Vue.js 应用添加路由功能。

### 一、Vue Router 提供了以下主要功能：

1、路由映射

将 URL 映射到组件，使得在不同的URL地址下展示不同的Vue组件。

2、嵌套路由

支持嵌套的路由结构，允许你在一个组件中嵌套另一个组件，并在URL中反映这种嵌套关系。

3、路由参数

允许定义动态的路由参数，使得你可以根据不同的参数显示不同的内容。
还可以传递路由参数，将前一个页面的参数传递给下一个页面。

### 二、在项目中引入Vue Router

1、在terminal执行以下命令：

    npm install vue-router@3


2、在package.json中查看是否下载成功：

![](../images/引入Vue%20Router/9340eb4e.png)

3、在项目src目录下新建router文件夹，在router文件夹下新建index.js文件

![](../images/引入Vue%20Router/13f76161.png)

4、在index.js文件里配置如下内容：

    import Vue from 'vue'
    import VueRouter from 'vue-router'
    import HelloWorld from "@/components/HelloWorld.vue";
    
    Vue.use(VueRouter)
    
    const routes = [
        {
            // 首页
            path:'/',
            component:HelloWorld
        }
    ]
    
    const router = new VueRouter({
    routes
    })
    
    export default router

5、在main.js中添加router：

![](../images/引入Vue%20Router/c126e123.png)

6、在APP.vue中添加如下配置：

    <router-view></router-view>

如图：
![](../images/引入Vue%20Router/455dd95b.png)


### 三、使用示例
使用 Vue Router 可以更好地组织和管理 Vue.js 应用的路由，使得应用的结构更加清晰，
用户体验更加流畅。以下是一个简单的 Vue Router 的例子：

路由映射和嵌套路由：

    const routes = [
        {
            path:'/',
            component:MainPage
        },
        {
            path:'/userLogin',
            component:UserLogin
        },
        {
            path:'/mySpace',
            redirect:'/mySpace/home',
            component:MySpaceContent,
            children:[
                {
                    path: 'home',
                    component: MySpaceHome
                },
                {
                    path: 'post',
                    component: MySpacePost
                },
                {
                    path: 'following',
                    component: MySpaceFollowing
                }
            ]
        }
    ]

路由参数：

    



