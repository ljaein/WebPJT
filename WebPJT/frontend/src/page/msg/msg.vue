<template>
        <div>
    <a href="javascript:;" @click="test" id="kakao-link-btn">
    <img src="//developers.kakao.com/assets/img/about/logos/kakaolink/kakaolink_btn_small.png" width="100px" />
    </a>
    </div>
</template>
     <script type="text/javascript">
    import axios from "axios";
export default {
    created(){
          Kakao.init('765ed14c0d508f8aa48c6d173446acba');

    },
    methods: {
        test(){
        Kakao.Link.createDefaultButton({
          container: '#kakao-link-btn',
          objectType: 'feed',
          content: {
            title: '상세페이지 제목 호출',
            description: '내용, 주로 해시태그',
            imageUrl: document.images[0].src,
            link: {
              webUrl: 'http://localhost:3000/#/posts/3',
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
                webUrl: 'http://localhost:3000/#/posts/3'     
              }
            }  
          ]
        });
        }
    }
}
    </script>

