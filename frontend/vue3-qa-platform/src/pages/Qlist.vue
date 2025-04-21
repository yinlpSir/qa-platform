<template>
  <div class="yoLikeContent">
    <div class="yoLike_main">
      <span class="maContent1">最近受到 {{p.recentComments}} 人回答</span>
      <div class="big_title">
        <span>{{p.title}}</span>
      </div>
      <div class="yoLike_three">
        <span
          >{{p.pageView}} 浏览
          <span class="iconfont" style="font-size: 12px; color: blank">&#xec1e;</span>
        </span>
        <span
          >{{p.comments}} 回答
          <span class="iconfont" style="font-size: 12px; color: blank">&#xec1e;</span>
        </span>
        <span>{{p.collects}} 收藏 </span>
      </div>
      <router-link :to="{ name: 'answers', params: { questionId: p.id } }">
        <el-button type="primary " color="rgb(23,114,246)">
          <span class="iconfont" style="font-size: 12px; color: blank">&#xec7c;</span>
          <span>写回答</span></el-button
        >
      </router-link>
    </div>
  </div>
  <!-- 复制 -->
</template>

<script setup>
import { ref } from "vue";
defineProps(["p"]);

</script>

<style scoped>
.yoLikeContent {
  width: 100%;
  height: 100%;
}
/* 一个问答列表 */
.yoLikeContent .yoLike_main {
  /* background-color: purple; */
  width: 100%;
  height: 100px;
  margin-bottom: 16px;
  padding-bottom: 20px;
  border-bottom: 1px solid #ccc;
  /* border-bottom: none; */
  position: relative;
}
.yoLikeContent .yoLike_main .el-button {
  position: absolute;
  right: 20px;
  top: 35px;
}
.yoLikeContent .yoLike_main .el-button .iconfont {
  padding-right: 5px;
}
.yoLikeContent .yoLike_main .maContent1 {
  /* background-color: rgba(255, 255, 255, 0.8); */
  color: rgb(132, 145, 165);
  font-size: 14px;
  display: block;
  margin-left: 15px;
  padding-top: 12px;
  /* position: relative; */
}
.yoLikeContent .yoLike_main .big_title span {
  /* background-color: plum; */
  font-weight: bold;
  font-size: 16px;
  margin-top: 15px;
  margin-left: 15px;
  margin-bottom: 10px;
  display: block;
}

.yoLikeContent .yoLike_main .yoLike_three {
  /* background-color: palevioletred; */
  margin-left: 15px;
  width: 100%;
  height: 20px;
  color: rgb(132, 145, 165);

  /* border-bottom: 1px solid #000; */
}
</style>
