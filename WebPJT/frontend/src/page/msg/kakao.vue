

<template>
  <div>
<img id="kakao-login-btn" @click="test()" src="https://kauth.kakao.com/public/widget/login/kr/kr_02_medium.png" style="cursor: pointer" onmouseover="this.src='https://kauth.kakao.com/public/widget/login/kr/kr_02_medium_press.png'" onmouseout="this.src='https://kauth.kakao.com/public/widget/login/kr/kr_02_medium.png'">

  
         <!-- <button id="kakao-login-btn" @click="test()"></button> -->
    <a href="http://developers.kakao.com/logout"></a>

    <h1>ㅇㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴㄴ</h1>
  </div>
</template>

<script>
import axios from "axios";
const baseURL = "http://localhost:8080/account";
export default {
    

    created(){
        Kakao.init('765ed14c0d508f8aa48c6d173446acba'); 
    },
   methods: {
       test(){
             Kakao.Auth.createLoginButton({

            container: '#kakao-login-btn',

            success: function(authObj) {

             Kakao.API.request({

                url: '/v2/user/me',

            success: function(res) {

      console.log(res);
                  console.log(res.id);
                  console.log(res.kakao_account);
                  console.log(JSON.stringify(res.properties.nickname));
                  console.log(JSON.stringify(res.kakao_account.email));
                  console.log(JSON.stringify(res.kakao_account.gender));
                  console.log(JSON.stringify(res.kakao_account.birthday));


          // alert(JSON.stringify(res)); //<---- kakao.api.request 에서 불러온 결과값 json형태로 출력

          // alert(JSON.stringify(authObj)); //<----Kakao.Auth.createLoginButton에서 불러온 결과값 json형태로 출력

          // console.log(res.id);//<---- 콘솔 로그에 id 정보 출력(id는 res안에 있기 때문에  res.id 로 불러온다)

          // console.log(res.kaccount_email);//<---- 콘솔 로그에 email 정보 출력 (어딨는지 알겠죠?)

          // console.log(res.properties.nickname);//<---- 콘솔 로그에 닉네임 출력(properties에 있는 nickname 접근 

      // res.properties.nickname으로도 접근 가능 )

          console.log(authObj.access_token);//<---- 콘솔 로그에 토큰값 출력

        }

      })

    },

    fail: function(error) {

      alert(JSON.stringify(error));

    },

  });
       }
   }
}
</script>