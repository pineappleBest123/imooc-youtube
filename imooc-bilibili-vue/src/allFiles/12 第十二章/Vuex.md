
# Vuex

Vuex 是一个专为 Vue.js 应用程序开发的状态管理模式。
它在应用中集中管理和维护状态，确保状态变更的可追踪性，以及在组件之间共享状态的一致性。
一言以概之：Vuex 用来管理组件间的共享内容。

### 1、使用npm安装Vuex

    //vue2使用vuex3的版本
    npm install vuex@3

### 2、Vuex的基础概念

State（状态）:
代表应用程序中的状态或数据。
存储在 Vuex 的状态树中，是唯一的数据源。


Mutation（变更）:
用于修改状态的唯一途径。
必须是同步函数，确保状态的变更是可追踪的。


Action（动作）:
用于提交 mutation。
可包含异步操作，可以在 action 中进行数据获取、处理等。
通过提交 mutation 来改变状态。

### 3、在项目中引入Vuex

新建vuex配置：index.js

    import Vue from 'vue'
    import Vuex from 'vuex'
    
    Vue.use(Vuex)
    
    export default new Vuex.Store({
    
        state: {
            userInfo:null,
            showVideoCommentComponent:true
        },
    
        mutations: {
    
        },
    })

在main.js里挂载vuex store：

    new Vue({
        store,
        router,
        render: h => h(App),
    }).$mount('#app')
