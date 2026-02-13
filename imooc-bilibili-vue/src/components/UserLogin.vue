<template>
  <div class="user-login-form-container">
    <div class="user-login-form">
      <div class="user-login-form-title">
        密码登录-UserLogin
      </div>
      <div class="user-login-form-item">
        <div class="user-login-form-item-title">
          账号
        </div>
        <input placeholder="请输入账号" type="text" v-model="account">
      </div>
      <div class="user-login-form-item">
        <div class="user-login-form-item-title">
          密码
        </div>
        <input placeholder="请输入密码" type="password" v-model="password">
        <div class="forgetPwd">
          忘记密码？
        </div>
      </div>
      <div class="user-login-form-footer">
<!--        <button v-on:click="register(account, password)" class="user-login-form-footer-btn">-->
<!--          注册-->
<!--        </button>-->
<!--        <button @click="login" class="user-login-form-footer-btn">-->
<!--          登录-->
<!--        </button>-->
        <el-button type="primary" class="user-login-form-footer-btn"
                   @click="register">
          注册
        </el-button>
        <el-button type="primary" class="user-login-form-footer-btn"
                   @click="login">
          登录
        </el-button>
      </div>
    </div>
  </div>
</template>

<script>
import userUtils from "@/utils/userUtils";
import routerUtils from "@/utils/routerUtils";

export default {
  name: 'UserLogin',
  mixins:[userUtils, routerUtils],
  props: {
    msg: String
  },
  data(){
    return {
      account:'',
      password:''
    }
  },

  methods:{

    async register(){
      try {
        await this.userRegister(this.account,this.password);
        window.alert('注册成功，请点击登录按钮进行登录');
      }catch (error){
        window.alert('注册失败');
      }
    },

    async login(){
      try{
        await this.userLogin(this.account, this.password);
        await this.jumpToPath('/');
      }catch (e){
        window.alert('登录失败');
      }

    },

  },

  computed:{

    isUserLoggedIn(){
      return localStorage.getItem('token');
    }
  },

  beforeCreate() {
    console.log('beforeCreate');
  },

  created() {
    console.log('created');
  },

  beforeMount() {
    console.log('beforeMount');
  },

  mounted() {
    console.log('mounted');
  },

  beforeUpdate() {
    console.log(this.account);
    console.log('beforeUpdate');
  },

  updated() {
    console.log(this.account);
    console.log('updated');
  },

  beforeDestroy() {
    console.log(this.account);
    console.log('beforeDestroy');
  },

  destroyed() {
    console.log('destroyed');
  }

}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped lang="less">

.user-login-form-container{
  margin-top: 10%;
  margin-left: 20%;
  margin-right: 20%;
  background-color: #ececec;
  display: flex;
  align-items: center;
  justify-content: center;
  border-radius: 10px;

  .user-login-form {
    padding: 40px;
    display: flex;
    flex-direction: column;

    .user-login-form-title{
      font-size: 24px;
      text-align: center;
      margin-bottom: 40px;
      font-weight: bold;
    }

    .user-login-form-item{
      display: flex;
      font-size: 20px;
      margin-bottom: 20px;
      align-items: center;
      border: darkgray solid 1px;
      background-color: white;
      border-radius: 5px;
      padding: 6px;

      .user-login-form-item-title{
        margin-right: 10px;
        margin-bottom: 5px;
      }

      input{
        font-size: 18px;
        margin-right: 10px;
        border: none;
        outline: none;
      }

      .forgetPwd{
        font-size: 16px;
        color: dodgerblue;
        font-weight: bold;
        cursor: pointer;
      }

    }

    .user-login-form-footer{
      display: flex;
      justify-content: space-between;
      .user-login-form-footer-btn{
        width: 100%;
      }

    }
  }
}

</style>
