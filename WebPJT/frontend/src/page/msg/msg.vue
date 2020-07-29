<template>
  <div>
   우편번호 : <input type="text" name="zip" style="width:80px; height:26px;" />
<button type="button" class="btn btn-primary mr-1" @click="Search">검색</button><br>
주소 : <input type="text" name="addr1" style="width:300px; height:30px;" readonly /><br>
상세 : <input type="text" name="addr2" style="width:300px; height:30px;" />
  </div>
</template>

<script>
export default {
  methods: {
          Search() {
          new daum.Postcode({
            oncomplete: function(data) {
              $('[name=zip]').val(data.zonecode); // 우편번호 (5자리)
              $('[name=addr1]').val(data.address);
              $('[name=addr2]').val(data.buildingName);
            }
          }).open();
}
  }
}
</script>