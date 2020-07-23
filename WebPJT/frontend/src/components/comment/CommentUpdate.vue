<template>
  <div class="input-group mb-3">
    <input
      type="text"
      class="form-control"
      placeholder="Please write modify content"
      aria-label="Recipient's username"
      aria-describedby="button-addon2"
      v-model="comment.content"
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
  props: {
    comment: Object,
  },
  methods: {
    commentUpdate() {
      // console.log(this.comment)
      axios.put(`${baseURL}/reply/modify`,this.comment)
        .then((response) => {
          console.log(response.data)
        }).catch((error) => {
          console.log(error.response.data)
        })
    },
  },
}
</script>

<style>

</style>