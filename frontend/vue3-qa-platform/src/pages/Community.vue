<template>
  <!-- <div>这里是问答区！</div> -->
  <div class="contentArea">
    <!-- 问答区域 -->
    <div class="questionArea">
      <nav class="questionNav">
        <ul>
          <li>
            <span class="iconfont" style="font-size: 17px; color: rgb(23, 114, 246)"
              >&#xe6ac;</span
            >
            <span class="yoLike">猜你喜欢</span>
          </li>
          <li>
            <span class="iconfont" style="font-size: 17px; color: rgb(132, 145, 165)"
              >&#xe66e;</span
            >
            <span class="hots">人气问答</span>
          </li>
          <li>
            <span class="iconfont" style="font-size: 17px; color: rgb(132, 145, 165)"
              >&#xe628;</span
            >
            <span class="neT">最新挑战</span>
          </li>
        </ul>
      </nav>
      <!-- 一个问答列表 -->
      <div class="yoLikeContent">
        <div class="yoLike_main">
          <span class="maContent1">最近受到23人关注</span>
          <div class="big_title">
            <span>可以分享你最喜欢的书里的一句话吗?</span>
          </div>
          <div class="yoLike_three">
            <span
              >2349浏览
              <span class="iconfont" style="font-size: 12px; color: blank">&#xec1e;</span>
            </span>
            <span
              >2349浏览
              <span class="iconfont" style="font-size: 12px; color: blank">&#xec1e;</span>
            </span>
            <span>2349浏览 </span>
          </div>
          <el-button type="primary">
            <span class="iconfont" style="font-size: 12px; color: blank">&#xec7c;</span>
            <span>写回答</span></el-button
          >
        </div>
      </div>
      <!-- 第二个 -->

      <!-- 你问我答 -->
    </div>

    <!-- 创作中心 -->
    <div class="asideList">
      <!-- 1 -->
      <div class="createCenter">
        <div class="users">
          <div class="createUser">
            <el-icon><Edit /></el-icon>
            <h5>创作中心</h5>
          </div>
          <div class="nav_user">
            <div class="q1">
              <img src="" alt="" />
              <p>回答问题</p>
            </div>
            <div class="q2">
              <img src="" alt="" />
              <p>发布问题</p>
            </div>
          </div>
          <!-- 这里放一个随机图片广告 -->
          <div class="random_advertising"><img src="" alt="" />随机广告</div>
          <!-- 去创作吧 -->
          <div class="go_create">
            <!-- 图标 -->
            <el-icon><Plus /></el-icon>
            <!-- 文字 -->
            <span>开始创作</span>
          </div>
        </div>
      </div>
      <!-- 2 -->
      <div class="suggested_follows">
        <div class="follows">
          <div class="followsUser">
            <el-icon><UserFilled /></el-icon>
            <h5>推荐关注</h5>
          </div>
          <div class="followsUserLists">
            <div class="u1">
              <div class="avatar1">
                <img src="" alt="" />
              </div>
              <!-- 名字 -->
              <span class="followsUname">张小梅</span>
              <!-- 个人签名 -->
              <p>你好，我叫张小梅</p>
              <!-- +关注 -->
              <div class="addFollows">
                <el-icon><Plus /></el-icon>
                <span>关注</span>
              </div>
            </div>
            <div class="u2">
              <div class="avatar2">
                <img src="" alt="" />
              </div>
              <!-- 名字 -->
              <span class="followsUname">吃个栗子</span>
              <!-- 个人签名 -->
              <p>美食问题找栗子</p>
              <!-- +关注 -->
              <div class="addFollows">
                <el-icon><Plus /></el-icon>
                <span>关注</span>
              </div>
            </div>
            <div class="u3">
              <div class="avatar3">
                <img src="" alt="" />
              </div>
              <!-- 名字 -->
              <span class="followsUname">徐笑笑</span>
              <!-- 个人签名 -->
              <p>笑着健身</p>
              <!-- +关注 -->
              <div class="addFollows">
                <el-icon><Plus /></el-icon>
                <span>关注</span>
              </div>
            </div>
          </div>
          <div class="followsRefresh">
            <el-icon><Refresh /></el-icon>
            <span>换一换</span>
          </div>
        </div>
      </div>
      <!-- <div class="b3"></div> -->
      <CreationCenter />
    </div>
  </div>
</template>

<script setup>
import { ref } from "vue";
import {
  Female,
  Promotion,
  ChatLineSquare,
  StarFilled,
  UserFilled,
  Plus,
  Edit,
  Refresh,
} from "@element-plus/icons-vue";

import Question from "./Question.vue";

const hotData = ref([
  {
    headPortrait: "https://b0.bdstatic.com/e84b3d688467be9ac1d76d0150c8b7d7.jpg@h_1280",
    username: "lx",
    gender: "女",
    question: {
      id: "asdfghjkl123", // 问题的id
      cover:
        "https://img1.baidu.com/it/u=871473204,2065805747&fm=253&fmt=auto&app=120&f=JPEG?w=889&h=500",
      title: "黑洞的内部究竟是什么样子的？",
      content:
        "黑洞是宇宙中最神秘的天体之一，它的内部结构和性质一直是科学家们研究的热点。黑洞是宇宙中最神秘的天体之一，它的内部结构和性质一直是科学家们研究的热点。那么，黑洞的内部究竟是什么样子的呢？",
      question_like: 88,
      createTime: "2024-10-11 11:11:11",
    },
    comment_count: 123,
    isCollect: true, // 表示这条问题是否被当前用户收藏过.true表示已被当前用户收藏
  },
  {
    headPortrait: "https://b0.bdstatic.com/e84b3d688467be9ac1d76d0150c8b7d7.jpg@h_1280",
    username: "张三",
    gender: "男",
    question: {
      id: "asdfghjkl123", // 问题的id
      cover:
        "https://img1.baidu.com/it/u=871473204,2065805747&fm=253&fmt=auto&app=120&f=JPEG?w=889&h=500",
      title: "黑洞的内部究竟是什么样子的？",
      content:
        "黑洞是宇宙中最神秘的天体之一，它的内部结构和性质一直是科学家们研究的热点。那么，黑洞的内部究竟是什么样子的呢？",
      question_like: 666,
      createTime: "2024-10-11 11:11:11",
    },
    comment_count: 123,
    isCollect: false, // 表示这条问题是否被当前用户收藏过
  },
]);

//获取问答盒子,剩余距离不够时,会循环复制第一个问答内容
/*
const box = document.getElementById('questionList')
const html=document.documentElement

console.log(box+'成功获得了');
document.addEventListener("scroll",()=>{
            // console.log(html.getBoundingClientRect().bottom)
            // console.log(html.clientHeight)
            if(html.getBoundingClientRect().bottom - html.clientHeight < 5){
                console.log("到底了")
                let h1=document.createElement("h1")
                h1.innerHTML = new Date()
                box.appendChild(h1)
            }
        })

*/
</script>

<style scoped>
.icon {
  width: 4em;
  height: 4em;
  vertical-align: -0.15em;
  fill: currentColor;
  overflow: hidden;
}
.contentArea {
  margin: 0 auto;
  position: relative;
  /* background-color: lightblue; */
  height: 500px;
  width: 1100px;
}

.contentArea .questionArea {
  background: pink;
  position: absolute;
  left: 0;
  width: 74%;
  height: 100%;
  border-radius: 8px;
  border: 1px solid rgb(132, 145, 165);
}
.contentArea .questionArea .questionNav {
  /* position: absolute;
    left: 0; */
  border-bottom: 1px solid rgb(132, 145, 165);
  /* background-color: rgb(232, 247, 172); */
  width: 100%;
}
.contentArea .questionArea .questionNav ul {
  list-style: none;
  /* background-color: purple; */
  /* margin-left: 0; */
}
.contentArea .questionArea .questionNav ul li {
  padding-top: 10px;
  padding-bottom: 10px;
}
.contentArea .questionArea .questionNav ul li:first-child {
  /* background: rgb(155, 140, 240); */
  margin-left: -18px;
}
.contentArea .questionArea .questionNav ul li {
  display: inline-block;
  margin-left: 10px;

  margin-right: 35px;
}

.contentArea .questionArea .questionNav ul li .yoLike {
  margin-left: 10px;
  color: rgb(23, 114, 246);
}
.contentArea .questionArea .questionNav ul li .hots {
  margin-left: 7px;
  color: rgb(132, 145, 165);
}
.contentArea .questionArea .questionNav ul li .neT {
  margin-left: 7px;
  color: rgb(132, 145, 165);
}
/* 一个问答列表 */
.contentArea .questionArea .yoLikeContent {
  background-color: peru;
  width: 100%;
  height: 100%;
}
/* 一个问答列表 */
.contentArea .questionArea .yoLikeContent .yoLike_main {
  background-color: purple;
  width: 100%;
  height: 100px;
  border: 1px solid rgb(132, 145, 165);
  position: relative;
}
.contentArea .questionArea .yoLikeContent .yoLike_main .el-button {
  position: absolute;
  right: 20px;
  top: 20px;
}
.contentArea .questionArea .yoLikeContent .yoLike_main .el-button .iconfont {
  padding-right: 5px;
}
.contentArea .questionArea .yoLikeContent .yoLike_main .maContent1 {
  /* background-color: rgba(255, 255, 255, 0.8); */
  color: rgb(132, 145, 165);
  font-size: 14px;
  display: block;
  margin-left: 15px;
  padding-top: 20px;
  /* position: relative; */
}
.contentArea .questionArea .yoLikeContent .yoLike_main .big_title span {
  background-color: plum;
  font-weight: bold;
  font-size: 16px;
  margin-top: 15px;
  margin-left: 15px;
  display: block;
}
/* .contentArea .questionArea .yoLikeContent .yoLike_main .big_title .div {
  background-color: darkblue;
  width: 100px;
  color: rgb(185, 100, 100);
} */
.contentArea .questionArea .yoLikeContent .yoLike_main .yoLike_three {
  background-color: palevioletred;
  margin-left: 15px;
  width: 100%;
  height: 20px;
}
/* .contentArea .questionArea .yoLikeContent .yoLike_main .div {
  background-color: lightgreen;
  width: 100px;
  height: 30px;
} */
/* 边导航 */
.contentArea .asideList {
  background: palevioletred;
  position: absolute;
  right: 0;

  /* padding-top: 65px; */
  width: 25%;
  /* height: 100%; */
}
.contentArea .asideList .createCenter {
  background-color: gold;
  border-radius: 3px;
  width: 100%;
  height: 300px;
  position: relative;
  margin-bottom: 10px;
}
.contentArea .asideList .createCenter .users {
  background-color: powderblue;
  width: 95%;
  height: 100%;
  position: relative;
  left: 7px;
}
/* 创作中心的logo */
.contentArea .asideList .createCenter .users .createUser {
  background-color: rgb(198, 166, 198);
  width: 240px;
  height: 50px;
  margin-left: 10px;
}
.contentArea .asideList .createCenter .users .createUser .el-icon {
  color: rgb(55, 58, 64);
  font-size: 20px;
  margin-top: 15px;
  margin-left: 10px;
}
.contentArea .asideList .createCenter .users .createUser h5 {
  font-size: 15px;
  font-weight: 400;
  position: absolute;
  top: 15px;
  left: 45px;
}
/* 小导航user */
.contentArea .asideList .createCenter .users .nav_user {
  background: peru;
  width: 240px;
  height: 95px;
  position: relative;

  left: 10px;
}
.contentArea .asideList .createCenter .users .nav_user .q1 {
  background-color: palegreen;
  position: relative;
  width: 120px;
  height: 95px;
}
.contentArea .asideList .createCenter .users .nav_user .q1 img {
  width: 45px;
  height: 45px;
  border-radius: 50%;
  background-color: palegoldenrod;
  position: absolute;
  top: 15px;
  left: 35px;
}
.contentArea .asideList .createCenter .users .nav_user .q1 p {
  font-size: 14px;
  position: absolute;
  top: 50px;
  left: 30px;
}

.contentArea .asideList .createCenter .users .nav_user .q2 {
  background-color: rgb(25, 112, 25);
  position: relative;
  top: -95px;
  left: 120px;
  width: 120px;
  height: 95px;
}
.contentArea .asideList .createCenter .users .nav_user .q2 img {
  width: 45px;
  height: 45px;
  border-radius: 50%;
  background-color: palegoldenrod;
  position: absolute;
  top: 15px;
  left: 40px;
}
.contentArea .asideList .createCenter .users .nav_user .q2 p {
  font-size: 14px;
  position: absolute;
  top: 50px;
  left: 30px;
}
/* 随机图片广告 */
.contentArea .asideList .createCenter .users .random_advertising {
  background: rgb(225, 213, 83);
  width: 240px;
  height: 80px;
  position: relative;
  left: 10px;
}
/* 去创作吧 */
.contentArea .asideList .createCenter .users .go_create {
  background: rgb(161, 155, 92);
  width: 240px;
  height: 50px;
  position: relative;
  left: 10px;
  top: 10px;
}
.contentArea .asideList .createCenter .users .go_create .el-icon {
  color: white;
  font-size: 20px;
  position: absolute;
  left: 75px;
  top: 15px;
}
.contentArea .asideList .createCenter .users .go_create span {
  font-size: 16px;
  color: white;
  position: absolute;
  left: 100px;
  top: 15px;
}

/* 
.contentArea .asideList .createCenter .users .nav_user .q1{
  background-color: rgb(198, 166, 198);
  width: 100%;
  height: 50px;
  position: relative;
  margin-bottom: 10px;
} */
.contentArea .asideList .suggested_follows {
  background-color: gold;
  border-radius: 3px;
  width: 100%;
  height: 300px;
  position: relative;
  margin-bottom: 10px;
}
.contentArea .asideList .suggested_follows .follows {
  background-color: powderblue;
  width: 95%;
  height: 100%;
  position: relative;
  left: 7px;
}

.contentArea .asideList .suggested_follows .follows .followsUser {
  background-color: rgb(198, 166, 198);
  width: 240px;
  height: 50px;
  margin-left: 10px;
}
.contentArea .asideList .suggested_follows .follows .followsUser .el-icon {
  color: rgb(55, 58, 64);
  font-size: 20px;
  margin-top: 15px;
  margin-left: 10px;
}
.contentArea .asideList .suggested_follows .follows .followsUser h5 {
  font-size: 15px;
  font-weight: 400;
  position: absolute;
  top: 15px;
  left: 45px;
}
.contentArea .asideList .suggested_follows .follows .followsUserLists {
  background-color: rgb(219, 57, 111);
  width: 240px;
  height: 200px;
  margin-left: 10px;
}
.contentArea .asideList .suggested_follows .follows .followsUserLists .u1 {
  background-color: palegreen;
  width: 100%;
  height: 50px;
  position: relative;
  top: 15px;
}
.contentArea .asideList .suggested_follows .follows .followsUserLists .u1 .avatar1 {
  background-color: rgb(244, 143, 244);
  position: absolute;
  width: 50px;
  height: 50px;
}
.contentArea .asideList .suggested_follows .follows .followsUserLists .u1 .avatar1 img {
  width: 100%;
  height: 100%;
}
.contentArea .asideList .suggested_follows .follows .followsUserLists .u1 .followsUname {
  font-size: 14px;
  position: absolute;
  top: 5px;
  left: 55px;
}
.contentArea .asideList .suggested_follows .follows .followsUserLists .u1 p {
  font-size: 12px;
  color: rgb(132, 130, 130);
  position: absolute;
  top: 15px;
  left: 55px;
}
.contentArea .asideList .suggested_follows .follows .followsUserLists .u1 .addFollows {
  background-color: rgb(244, 143, 244);
  width: 80px;
  height: 50px;
  position: absolute;
  right: 0px;
}
.contentArea
  .asideList
  .suggested_follows
  .follows
  .followsUserLists
  .u1
  .addFollows
  .el-icon {
  /* color: white; */
  font-size: 18px;
  position: absolute;
  top: 15px;
  right: 40px;
}
.contentArea
  .asideList
  .suggested_follows
  .follows
  .followsUserLists
  .u1
  .addFollows
  span {
  font-size: 14px;
  position: absolute;
  top: 15px;
  right: 10px;
}

.contentArea .asideList .suggested_follows .follows .followsUserLists .u2 {
  background-color: rgb(79, 174, 79);
  width: 100%;
  height: 50px;
  position: relative;
  top: 25px;
}
.contentArea .asideList .suggested_follows .follows .followsUserLists .u2 .avatar2 {
  background-color: rgb(244, 143, 244);
  position: absolute;
  width: 50px;
  height: 50px;
}
.contentArea .asideList .suggested_follows .follows .followsUserLists .u2 .avatar2 img {
  width: 100%;
  height: 100%;
}
.contentArea .asideList .suggested_follows .follows .followsUserLists .u2 .followsUname {
  font-size: 14px;
  position: absolute;
  top: 5px;
  left: 55px;
}
.contentArea .asideList .suggested_follows .follows .followsUserLists .u2 p {
  font-size: 12px;
  color: rgb(132, 130, 130);
  position: absolute;
  top: 15px;
  left: 55px;
}
.contentArea .asideList .suggested_follows .follows .followsUserLists .u2 .addFollows {
  background-color: rgb(244, 143, 244);
  width: 80px;
  height: 50px;
  position: absolute;
  right: 0px;
}
.contentArea
  .asideList
  .suggested_follows
  .follows
  .followsUserLists
  .u2
  .addFollows
  .el-icon {
  /* color: white; */
  font-size: 18px;
  position: absolute;
  top: 15px;
  right: 40px;
}
.contentArea
  .asideList
  .suggested_follows
  .follows
  .followsUserLists
  .u2
  .addFollows
  span {
  font-size: 14px;
  position: absolute;
  top: 15px;
  right: 10px;
}

.contentArea .asideList .suggested_follows .follows .followsUserLists .u3 {
  background-color: palegreen;
  width: 100%;
  height: 50px;
  position: relative;
  top: 35px;
}
.contentArea .asideList .suggested_follows .follows .followsUserLists .u3 .avatar3 {
  background-color: rgb(244, 143, 244);
  position: absolute;
  width: 50px;
  height: 50px;
}
.contentArea .asideList .suggested_follows .follows .followsUserLists .u3 .avatar3 img {
  width: 100%;
  height: 100%;
}
.contentArea .asideList .suggested_follows .follows .followsUserLists .u3 .followsUname {
  font-size: 14px;
  position: absolute;
  top: 5px;
  left: 55px;
}
.contentArea .asideList .suggested_follows .follows .followsUserLists .u3 p {
  font-size: 12px;
  color: rgb(132, 130, 130);
  position: absolute;
  top: 15px;
  left: 55px;
}
.contentArea .asideList .suggested_follows .follows .followsUserLists .u3 .addFollows {
  background-color: rgb(244, 143, 244);
  width: 80px;
  height: 50px;
  position: absolute;
  right: 0px;
}
.contentArea
  .asideList
  .suggested_follows
  .follows
  .followsUserLists
  .u3
  .addFollows
  .el-icon {
  /* color: white; */
  font-size: 18px;
  position: absolute;
  top: 15px;
  right: 40px;
}
.contentArea
  .asideList
  .suggested_follows
  .follows
  .followsUserLists
  .u3
  .addFollows
  span {
  font-size: 14px;
  position: absolute;
  top: 15px;
  right: 10px;
}

.contentArea .asideList .suggested_follows .follows .followsRefresh {
  background-color: rgb(230, 163, 185);
  width: 240px;
  height: 50px;
  margin-left: 10px;
  position: relative;
}
.contentArea .asideList .suggested_follows .follows .followsRefresh span {
  font-size: 14px;
  position: absolute;
  top: 15px;
  left: 100px;
}
.contentArea .asideList .suggested_follows .follows .followsRefresh .el-icon {
  font-size: 18px;
  position: absolute;
  left: 75px;
  top: 15px;
}

.contentArea .asideList .b3 {
  background-color: gold;
  border-radius: 3px;
  width: 100%;
  height: 300px;
  position: relative;
  margin-bottom: 10px;
}
</style>
