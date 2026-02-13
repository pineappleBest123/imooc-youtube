
# 安装配置ElementUI
Element UI是一套基于 Vue 2.0 的开源 UI 组件库，由电商平台饿了么开发和维护。
Element UI 提供了一系列的 UI 组件，可以帮助开发者快速搭建现代化的用户界面。


### 一、使用npm安装ElementUI
在webstorm中打开terminal，使用以下命令安装ElementUI：

    npm install element-ui

### 二、在项目中配置ElementUI
在main.js文件里添加如下内容：

    //引入ElementUI
    import ElementUI from 'element-ui';
    import 'element-ui/lib/theme-chalk/index.css';

    Vue.use(ElementUI);

