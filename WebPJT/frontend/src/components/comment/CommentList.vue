<template>
  <div>
    <div class="card mt-4">
      <div class="card-header d-flex">
        <strong class="mr-auto">{{comment.nickname}}</strong>
        <small class="my-auto ml-auto">{{comment.createDate}}</small>
      </div>
      <ul class="list-group list-group-flush">
        <li class="list-group-item d-flex">
          <CommentUpdate v-if="isUpdated" :comment="comment" @update-comment="commentModify" />
          <span v-else>{{comment.content}}</span>
        </li>
      </ul>
    </div>
    <div v-if="!NickNameCheck" class="d-flex">
    <small
        class="ml-auto mr-2 badge commentModify btn btn-outline-success"
        @click="commentModify"
    >
        <span v-if="isUpdated">취소</span>
        <span v-else>수정</span>
    </small>
    <small class="badge commentDelete btn btn-outline-danger" @click="commentDelete">삭제</small>
    </div>
    {{this.NickName}}
  </div>
</template>

<script>
import axios from 'axios'

const baseURL = "http://localhost:8080";

export default {
  props: {
    comment: Object,
  },
  data: function(){
    return {
      NickName: "",
      NickNameCheck: false,
      isUpdated: false,
      email: '',
    }
  },
  methods: {
    commentDelete: function() {
      if (confirm('댓글을 삭제하시겠습니다?')) {
        this.$emit('comment-delete',this.comment)
      }
    },
    fetchNickName(){
        axios.get(`${baseURL}/account/getNickname/${this.email}`)
            .then((response) => {
              this.NickName = response.data
              console.log(response.data)
            })
            .catch((err) => {
              console.log(err.response.data)
            });
    },
  },
  created() {
    this.email = this.$cookies.get('User')
    this.fetchNickName()
  },
}
</script>

<style>

</style>