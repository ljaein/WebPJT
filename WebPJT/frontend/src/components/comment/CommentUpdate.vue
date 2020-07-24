<template>
  <div class="input-group mb-3">
    <textarea
      type="text"
      class="form-control"
      placeholder="Please write modify content"
      aria-label="Recipient's username"
      aria-describedby="button-addon2"
      v-model="comment.content"
    />
    <!-- <h1 class="text-dark">{{this.updateCommnet}}</h1> -->
    <div class="input-group-append">
      <button class="btn btn-outline-primary" type="button" id="button-addon2" @click="commentUpdate">수정</button>
    </div>
  </div>
</template>

<script>
import axios from 'axios'

const baseURL = "http://localhost:8080";

export default {
  props: {
    comment: Object,
  },
  data: function() {
    return {
      updateCommnet: [],
    }
  },
  methods: {
    commentUpdate() {
      axios.put(`${baseURL}/reply/modify`,this.comment)
        .then(() => {
          alert('댓글 수정 완료!')
          this.$router.go()
        }).catch((error) => {
          console.log(error.response.data)
        })
    },
    fetchComment() {
      axios.get(`${baseURL}/reply/list/${this.$route.params.ID}`)
        .then((response) => {
          this.updateCommnet = response.data
        }).catch((error) => {
          console.log(error.response.data)
        })
    },
  },
  created() {
    this.fetchComment()
  },
}
</script>

<style>

</style>