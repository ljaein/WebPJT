<template>
  <div class="input-group mb-3">
    <input
      type="text"
      class="form-control"
      placeholder="Please write modify content"
      aria-label="Recipient's username"
      aria-describedby="button-addon2"
      v-model="updateComment.content"
    />
    <div class="input-group-append">
      <button class="btn btn-outline-primary" type="button" id="button-addon2" @click="commentUpdate">수정</button>
    </div>
  </div>
</template>

<script>
import axios from 'axios'

const baseURL = "http://localhost:8080";

export default {
  data: function() {
    return {
      updateComment: []
    }
  },
  props: {
    comment: Object,
  },
  methods: {
    commentUpdate() {
      axios.put(`${baseURL}/reply/modify`,this.updateComment)
        .then(() => {
          alert('댓글 수정 완료!')
          this.$router.go()
        }).catch((error) => {
          console.log(error.response.data)
        })
    },
    fetchComment() {
      axios.get(`${baseURL}/reply/rlist/${this.$route.params.ID}/${this.comment.rid}`)
        .then((response) => {
          this.updateComment = response.data[0]
          // console.log(this.updateComment)
        }).catch((error) => {
          console.log(error.response.data)
        })
    },
  },
  created(){
    this.fetchComment()
  },
}
</script>

<style>

</style>