<template>
  <div>
    <label for="example-datepicker">Choose a date</label>
    <b-form-datepicker id="example-datepicker1" v-model="value1" class="mb-2"></b-form-datepicker>
    <p>Value: '{{ value1}}'</p>
    <b-form-datepicker id="example-datepicker1" v-model="value2" class="mb-2"></b-form-datepicker>
    <p>Value: '{{ value2 }}'</p>
    <!-- <h1 v-if="value1 > va "> </h1> -->
  </div>


</template>

<script>
  export default {
    data() {
      return {
        value1: '',
        value2: ''
      }
    }
  }
</script>