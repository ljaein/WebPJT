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
      <button class="btn btn-outline-success" type="button" id="button-addon2" @click="commentUpdate">수정</button>
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
      Swal.fire({
        width: 350,
        text: "댓글을 수정하시겠습니까?",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: '<a style="font-size:1rem; color:black">Udate</a>',
        cancelButtonText: '<a style="font-size:1rem; color:black">Cancel</a>'
        }).then((result) => {
          if (result.value) {
            const Toast = Swal.mixin({
              toast: true,
              position: 'top-end',
              showConfirmButton: false,
              timer: 1000,
              timerProgressBar: true,
              onOpen: (toast) => {
                toast.addEventListener('mouseenter', Swal.stopTimer)
                toast.addEventListener('mouseleave', Swal.resumeTimer)
              }
            })
          axios.put(`${baseURL}/reply/modify`,this.updateComment)
            .then(() => {
              Toast.fire({
                icon: 'success',
                title: 'Update completed!'
              })
              setTimeout(() => {
                this.$router.go()
              },1000)
            }).catch((error) => {
              console.log(error.response.data)
            })
          }
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