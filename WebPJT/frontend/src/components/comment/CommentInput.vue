<template>
  <div class="input-group">
    <div class="input-group-prepend">
        <span class="input-group-text bg-white" style="height: 2.5rem; border: none;">Comment</span>
    </div>
    <textarea class="form-control my-1" style="height: 2.5rem; border:none !important; " aria-label="With textarea" v-model="commentData.content" placeholder="댓글을 자유롭게 남겨주세요^^"></textarea>
    <div class="input-group-prepend buttonComment" style="border:none;" >
        <span class="input-group-text bg-white" style="height: 2.5rem; border:none; border-top-right-radius: 5px; border-bottom-right-radius: 5px;" @click="createComment"><i class="far fa-keyboard" style="font-size:25px"></i></span>
    </div>
  </div> 
</template>

<script>
import axios from "axios";
const baseURL = "http://localhost:8080";
export default {
    data: function () {
        return {
            commentData : {
                pid: '',
                nickname: '',
                content: '',
            },
            email:''
        }
    },
    methods: {
        fetchNickName(){
            axios.get(`${baseURL}/account/getNickname/${this.email}`)
                .then((response) => {
                    this.commentData.nickname = response.data;
                })
                .catch((err) => {
                    console.log(err.response.data)
                });
        },
        createComment: function() {
            this.$emit('create-comment',this.commentData)
        }
    },
    created(){
        this.commentData.pid = this.$route.params.ID
        this.email = this.$cookies.get('User');
        this.fetchNickName()
    }
}
</script>

<style>
.buttonComment {
    cursor: pointer;
}
</style>