<script>
import userApi from "@/api/userApi";
import CommonHeader from "@/components/CommonHeader.vue";
import MainPageContent from "@/components/MainPageContent.vue";

export default {
  name: "MainPage",
  components: {MainPageContent, CommonHeader},
  data(){
    return{
      pk:''
    }
  },
  methods:{

    async getRsaPublicKey(){
      let response = await userApi.getRsaPublicKey();
      if(response.data){
        this.pk = response.data;
      }
    },

    async addUser(){
      let params = {
        phone:'3131',
        password:'dada'
      }
      let response = await userApi.addUser(params);
      console.log(response);
    },

    async updateUser(){
      let params = {
        phone:'3131',
        password:'dada'
      }
      let response = await userApi.updateUser(params);
      console.log(response);
    },

    async logout(){
      let response = await userApi.logout();
      console.log(response);
    }



    // async demo(){
    //   let params = {
    //     p1:1,
    //     p2:2
    //   };
    //   try{
    //     let response = await httpRequest.get('/rsa-pks', {params});
    //     console.log('response1',response);
    //     if(response){
    //       let response2 = await httpRequest.post('/users', params);
    //       console.log('response2',response2);
    //       if(response2){
    //         let response3 = await httpRequest.delete('/refresh-tokens', {params});
    //         console.log('response3',response3);
    //       }
    //     }
    //   }catch (e){
    //     console.log('遇到错误了，需要处理', e);
    //   }
    //
    //
    // }

  },
  mounted() {
    // this.getRsaPublicKey();

    // this.addUser();

    // this.updateUser();

    // this.logout();

    // this.demo();
    // httpRequest.get('/rsa-pks');
    // let params = {
    //   p1:1,
    //   p2:2
    // };
    // httpRequest.get('/rsa-pks', {params});
    //
    // httpRequest.post('/users', params);
    //
    // httpRequest.put('/file-slices', params, {
    //   headers: {
    //     'Content-Type': 'multipart/form-data'
    //   }
    // });
    //
    // const response = httpRequest.delete('/refresh-tokens', {params});
    // console.log('response', response);
    //
    // let myPromise = new Promise((resolve, reject) => {
    //   // 异步操作，例如从服务器获取数据
    //   let success = false;
    //   if (success) {
    //     resolve('成功时的数据');
    //
    //   } else {
    //     reject('失败时的原因');
    //   }
    // });
    //
    // // 处理成功情况
    // myPromise.then((data) => {
    //   console.log('成功:', data);
    // })
    // // 处理失败情况
    // .catch((error) => {
    //   console.error('失败:', error);
    // });
    //
    // httpRequest.get('/api').then(response => {
    //   httpRequest.get('/rsa-pks', {params:response});
    // }).then(response => {
    //   httpRequest.post('/users', response).then().catch();
    // }).catch()

  }

}
</script>

<template>
  <div>
    <CommonHeader/>
    <MainPageContent/>
  </div>
</template>

<style scoped lang="less">

</style>