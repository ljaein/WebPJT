<template>
  <div>
   
        <button id ="loginButton" @click="test">로그인</button>
    
  </div>
</template>

<script>
export default {
    
    methods:{
        test() {
            const loginButton = document.getElementById("loginButton");
            loginButton.onclick.href =
            'https://kauth.kakao.com/oauth/authorize?client_id=8940d7dfdaa8e8db4c0f4d73dd782a21&'+'redirect_uri=http://localhost:3000&'+'response_type=code';
        }, 
        // addEventListenerToButton(){
        //     const loginButton =
        //     document.getElementById("loginButton");
            
        // }
    }

}
</script>

<style>
#loginButton{
     background-color : red;
}
</style>