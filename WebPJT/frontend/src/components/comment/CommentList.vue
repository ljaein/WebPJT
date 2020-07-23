<template>
  <div>
    <div class="card mt-4">
      <div class="card-header d-flex">
        <strong class="mr-auto text-dark">{{comment.nickname}}</strong>
        <small class="my-auto ml-auto text-dark">{{comment.createDate}}</small>
      </div>
      <ul class="list-group list-group-flush">
        <li class="list-group-item d-flex">
          <CommentUpdate v-if="isUpdated" :comment="comment" @update-comment="commentModify" />
          <span v-else class="text-dark">{{comment.content}}</span>
        </li>
      </ul>
    </div>
    <div v-if="NickNameCheck" class="d-flex">
    <small
        class="ml-auto mr-2 badge commentModify btn btn-outline-success"
        @click="commentModify"
    >
        <span v-if="isUpdated">취소</span>
        <span v-else>수정</span>
    </small>
    <small class="badge commentDelete btn btn-outline-danger" @click="commentDelete">삭제</small>
    </div>
  </div>
</template>

<script>
import axios from 'axios'
import CommentUpdate from './CommentUpdate'

const baseURL = "http://localhost:8080";

export default {
  components: {
    CommentUpdate,
  },
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
    commentModify() {
      this.isUpdated = !this.isUpdated
    },
    fetchNickName(){
        axios.get(`${baseURL}/account/getNickname/${this.email}`)
            .then((response) => {
              this.NickName = response.data
              if (this.NickName == this.comment.nickname) {
                this.NickNameCheck = true
              } else {
                this.NickNameCheck = false
              }
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