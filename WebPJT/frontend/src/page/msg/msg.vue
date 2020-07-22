<template>
        <div>
    <a href="javascript:;" @click="test" id="kakao-link-btn">
    <img src="//developers.kakao.com/assets/img/about/logos/kakaolink/kakaolink_btn_small.png" width="10px" />
    </a>

    </div>
</template>
   
    
    
     <script type="text/javascript">
    import axios from "axios";

    const baseURL = "http://localhost:8080/callback";

      //<![CDATA[
        // // 사용할 앱의 JavaScript 키를 설정해 주세요.
        
      //]]>

      
export default {
    created(){
        
    },
    methods: {

        test(){
            axios
            .get(`${baseURL}/msg`)
            .then(response => {
                alert("통과");
                console.log(response.data);
                
            })
            
            Kakao.init('765ed14c0d508f8aa48c6d173446acba');
        // // 카카오링크 버튼을 생성합니다. 처음 한번만 호출하면 됩니다.
        Kakao.Link.createDefaultButton({
          container: '#kakao-link-btn',
          objectType: 'feed',
          content: {
            title: '상세페이지 제목 호출',
            description: '내용, 주로 해시태그',
            imageUrl: document.images[0].src,
            link: {
              webUrl: 'https://developers.kakao.com',
              mobileWebUrl: 'https://developers.kakao.com'
            }
          },
          social: {
            likeCount: 286,
            commentCount: 45,
            sharedCount: 845
          },
          buttons: [
            {
              title: 'Open!',
              link: {
                mobileWebUrl: 'https://developers.kakao.com',
                webUrl: 'https://developers.kakao.com'     
              }
            }  
          ]
        });
        }
    }
}

    </script>

