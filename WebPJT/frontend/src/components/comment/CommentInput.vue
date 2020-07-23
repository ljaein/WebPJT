<template>
  <div class="input-group">
    <div class="input-group-prepend">
        <span class="input-group-text">Comment</span>
    </div>
    <textarea class="form-control" aria-label="With textarea" v-model="commentData.content" placeholder="댓글을 자유롭게 남겨주세요^^"></textarea>
    <div class="input-group-prepend buttonComment">
        <span class="input-group-text" @click="createComment">작성</span>
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