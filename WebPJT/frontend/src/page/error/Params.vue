<template>
  <div>
    <h1>요청하신 페이지를 찾을 수 없습니다.</h1>
    <h2 v-if="$route.params.name!=null">{{ $route.params.name }}</h2>
    <br>
    <!-- <a v-if="$route.params.name==null" href="http://localhost:3000">메인으로</a> -->
    <router-link v-if="$route.params.name==null" v-bind:to="{name:constants.URL_TYPE.POST.MAIN}" class="login-btn">메인으로</router-link>
  </div>
</template>