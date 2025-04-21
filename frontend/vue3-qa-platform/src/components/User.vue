<template>
  <div class="user">
    <div style="display: flex;justify-content: center;">
      <div class="header">
      <div class="box">
        <img src="../assets/imgs/login2.png" style="cursor: pointer" alt="" @click="() => router.push('/')" />
        <nav>
          <router-link active-class="active" to="/" class="router-link-custom">首页</router-link>
          <router-link active-class="active" :to="{ path: '/qwenda' }" class="router-link-custom">问答</router-link>
          <!-- <router-link active-class="active" to="/community" class="router-link-custom"
            >社区</router-link
          > -->
        </nav>
        <!-- 搜索框 -->
        <div style="margin: 0 40px; display: flex;align-items: center;">
          <input class="text" @input="handleInput" @focus="handleFocus" @blur="handleBlur" type="text"
          placeholder="请输入关键词~~~" name="search" maxlength="2000" autocomplete="off" v-model="searchKeyword" />

          <input class="button" type="button" @click="performSearch" value="搜索" />
        </div>
        <!-- <div class="search_box" v-show="isFocused">11111</div> -->
      </div>
      <div class="box">
        <div class="user_img">
          <el-avatar :src="us.headPortrait" />
        </div>
        <nav class="one">
          <!-- input一但聚焦nav中的a标签只显示第一个button标签显示，其他不显示 -->
          <!-- 创作中心 -->
          <el-popover placement="top-start" :width="100" trigger="hover">
            <template #reference>
              <a class="center" @click="mys_center"><b>个人中心</b></a>
            </template>
            <div class="exitMys" @click="exit">
              <el-icon>
                <SwitchButton />
              </el-icon>
              <span style="margin-left: 4px;">退出</span>
            </div>
          </el-popover>
          <!-- 消息 -->
          <el-popover style="background-color: yellow" placement="top-start" :width="100" trigger="hover">
            <template #reference>
              <a class="mge" style="cursor: pointer;font-weight: bold;">消息</a>
            </template>
            <template #default>
              <div class="message">
                <ul>
                  <li>评论和@</li>
                  <li>消息</li>
                </ul>
              </div>
            </template>
          </el-popover>
          <!-- 私信 -->
          <!-- 历史 -->
          <el-popover placement="top-start" :width="100" trigger="hover">
            <template #reference>
              <a class="hty" style="cursor: pointer;font-weight: bold;">历史</a>
            </template>
            <div>随便定义什么</div>
          </el-popover>

          <div class="issuse">
            <el-button type="primary" class="release" :icon="CirclePlus" @click="publishedIssues">发布</el-button>
          </div>
        </nav>
      </div>
    </div>
    </div>
    <div class="contents">
      <div class="content">
        <!-- 指定组件位置 -->
        <router-view></router-view>
      </div>
    </div>

    <el-dialog v-model="ps.publishProblemVisible" style="border-radius: 20px;" title="发布问题" width="750" center>
      <PublishProblem />
    </el-dialog>

  </div>
</template>

<script setup>
import { onMounted, ref } from "vue";
import { RouterView } from "vue-router";
import {
  UserFilled,
  CirclePlus,
  Search,
  CirclePlusFilled,
  SwitchButton
} from "@element-plus/icons-vue";
import router from "@/router/router";
import { useRoute } from "vue-router";
import authentication from "@/store/authentication";
import problemStore from "@/store/problem.store";
import userStore from "@/store/user.store";
import { getHeadPortrait } from "@/api/api.user";
import { ElMessage } from "element-plus";
import PublishProblem from "@/pages/PublishProblem.vue";

const auth = authentication();

const ps = problemStore()

const us = userStore()

onMounted(() => {
  getHeadPortrait(auth.id).then((res) => {
    us.setHeadPortrait(res.data)
  });
});

//发布问题
function publishedIssues() {
  ps.publishProblemVisible = true
}

// 退出登录
function exit() {
  localStorage.clear();
  router.push({ path: "/login" });
  ElMessage.success("退出成功")
}

// 问答页面跳转

// 个人中心页面跳转
function mys_center() {
  router.push("/mine");
}

// 获取路由参数
const input = ref("");
// const isFocused = ref(false);
// const isShow = ref(false);

// function handleFocus() {
//   // console.log("聚焦了");
//   isFocused.value = true;
//   this.$nextTick = () => {
//     isShow.value = true;
//   };
// }
// function handleBlur() {
//   // console.log("失去焦点了");
//   isFocused.value = false;
// }
const activeIndex = ref("1");

const handleSelect = (key, keyPath) => {
  console.log(key, keyPath);
};
</script>

<style scoped>
.user {
  width: 100%;
  /* height: 1300px; */
  /* margin-bottom: 20px; */
  /* background-color: palegoldenrod; */
}

.user .header {
  background-color: white;
  display: flex;
  align-items: center;
  /* justify-content: flex-start; */
  justify-content: center;
  width: 100%;
  height: 55px;
  border-radius: 10px 10px 0 0;
  position: fixed;
  top: 0;
  z-index: 20;
  /* background-color: plum; */
}

.user .contents {
  /* background-color: rgb(253, 158, 190); */
  width: 100%;
  /* height: 1245px; */
  /* margin-bottom: 20px; */
  position: relative;
  top: 55px;
}

.user .header .box {
  display: flex;
  align-items: center;
  /* justify-content: flex-start; */
  justify-content: center;
  /* width: 100%; */
  height: 100%;
  position: relative;
  /* background-color: palevioletred; */
}

.user .header .box img {
  width: 100px;
  height: 50px;
  padding-top: 9px;
  /* background-color: palevioletred; */
}

.user .header .box .text {
  margin-left: 20px;
  width: 250px;
  height: 30px;
  font-size: 14px;
  /* border: 1px solid #ccc; */
  border: 1px solid #ccc;
  padding-left: 20px;
  border-bottom-left-radius: 20px;
  border-top-left-radius: 20px;
  transition: 0.8s;
  /* background-color: pink; */
}

.user .header .box .text:focus {
  outline: none;
  border: 1px solid rgb(82, 168, 236);
  width: 350px;
}

.user .header .box .button {
  width: 60px;
  height: 33.5px;
  font-size: 14px;
  /* margin-right: 50px; */
  color: white;
  border: 1px solid #608bd2;
  background-color: rgb(82, 168, 236);
  border-top-right-radius: 20px;
  border-bottom-right-radius: 20px;
}

/* 盒子 */

.user .header .box nav {
  display: flex;
  align-items: center;
  margin-left: 10px;
  /* margin: 0px 40px; */
  /* background-color: paleturquoise; */
}

/* a.router-link-exact-active{
  color: plum;
} */
.active {
  /* font-weight: bold; */
  color: red;
}

.user .header .box nav .router-link-custom {
  padding-left: 30px;
  text-decoration: none;
  width: 80px;
  font-weight: bold;
  /* font-size: 16px;
  font-style: normal; */
  color: #666;
}

.user .header .box nav .router-link-custom:hover {
  color: black;
  transition: 0.8s;
}

.user .header .box .one {
  /* background-color: palevioletred; */
  transition: all 1s;
}

.message {
  background-color: powderblue;
  width: 100%;
}

.message ul {
  padding: 0;
  margin: 0;
}

.message ul>li {
  /* background-color: rgb(239, 195, 210); */
  list-style: none;
  padding: 10px;
  margin: 0;
  /* border: 1px solid #000; */
}

.message ul>li:hover {
  background-color: #f1f1f1;
}

:deep(.el-popper .el-popover) {
  padding: 0;
}

:deep(.el-popover) {
  padding: 0 !important;
}

.user .header .box .one a {
  padding-left: 10px;
  padding-right: 15px;
  width: 32px;
  /* background-color: palevioletred; */
  text-decoration: none;
  /* color: #ffffff; */
  font-size: 16px;
  font-weight: normal;
  /* opacity: 0.9; */
}

/* .user .header .box .one .el-popover .message{
  display: block;
  background-color: pink;
} */
.user .header .box .one .center {
  width: 64px;
  cursor: pointer;
}

.exitMys {
  cursor: pointer;
  color: rgb(111, 161, 255);
  margin-left: 15px;
  display: flex;
  align-items: center;
}

.exitMys:hover {
  font-weight: bold;
}

.user .header .box .two {
  /* background-color: paleturquoise; */
  transition: all 1s;
}

.user .header .box .two a {
  padding-left: 10px;
  padding-right: 15px;
  width: 32px;
  /* background-color: palevioletred; */
  text-decoration: none;
  /* color: #ffffff; */
  font-size: 16px;
  font-weight: normal;
  /* opacity: 0.9; */
}

.user .header .box nav .issuse .release {
  width: 70px;
  padding-left: 10px;
  margin-left: 20px;
  height: 33.5px;
  font-size: 14px;
  /* margin-right: 50px; */
  color: white;
  border: 1px solid #608bd2;
  background-color: rgb(82, 168, 236);
  border-radius: 20px;
}

/* .user .header .box nav a:hover {
  opacity: 1;
} */
.user .header .box .user_img {
  margin-left: 60px;
  width: 45px;
  height: 45px;
  /* background-color: palegreen; */
}
</style>
