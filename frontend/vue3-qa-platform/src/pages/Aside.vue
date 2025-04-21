<template>
  <!-- 创作中心 -->
  <div class="create-center">
    <div class="create-header">
      <el-icon size="20"><Edit /></el-icon>
      <span>创作中心</span>
    </div>

    <div class="nav-user">
      <div class="qq" @click="answerQuestion">
        <div class="q">
          <el-icon size="16"><Document /></el-icon>
        </div>
        <span>回答问题</span>
      </div>
      <div class="qq" @click="publishedIssues">
        <div class="q">
          <el-icon size="16"><EditPen /></el-icon>
        </div>
        <span>发布问题</span>
      </div>
    </div>

    <div class="random-advertising">
      <img
        src="https://img2.baidu.com/it/u=98141472,4156036501&fm=253&fmt=auto&app=138&f=JPEG?w=890&h=500"
        alt="ad"
      />
    </div>

    <div class="go-create" @click="publishedIssues">
      <!-- 图标 -->
      <el-icon size="28"><Plus /></el-icon>
      <!-- 文字 -->
      <span>开始创作</span>
    </div>
  </div>
</template>

<script setup>
import { onMounted, reactive, ref, toRaw } from "vue";
import { Document, EditPen, Plus, Edit } from "@element-plus/icons-vue";
import problemStore from "@/store/problem.store";
import { useRoute, useRouter } from "vue-router";

const router = useRouter();

const ps = problemStore()

//发布问题
function publishedIssues() {
  ps.publishProblemVisible = true
}
// 回答问题
function answerQuestion() {
  router.push({
    path: "/qwenda",
  });
}

// 开始创作
const goCreate = () => {
  console.log("goCreate");
};
</script>

<style scoped>
.create-center {
  border: 1px solid #ccc;
  border-radius: 6px;
}
.create-center .create-header {
  display: flex;
  align-items: center;
  border-bottom: 1.5px solid #e4e7ed;
  padding: 11px 20px;
}
.create-center .create-header span {
  margin-left: 6px;
  font-weight: bold;
}
.create-center .nav-user {
  display: flex;
  justify-content: space-around;
  margin-top: 20px;
}
.create-center .nav-user .qq {
  width: 85px;
  display: flex;
  justify-content: center;
  align-items: center;
  flex-wrap: wrap;
  cursor: pointer;
}
.create-center .nav-user .qq span {
  margin-top: 4px;
}
.q {
  width: 40px;
  height: 40px;
  border: 1px solid #ccc;
  border-radius: 50%;
  display: flex;
  justify-content: center;
  align-items: center;
  background-color: #caa8b8;
}

.create-center .random-advertising {
  width: 85%;
  height: 110px;
  margin: 0 auto;
  border-radius: 8px;
  margin-top: 10px;
}
.create-center .random-advertising img {
  width: 100%;
  height: 100%;
  border-radius: 8px;
}
.create-center .go-create {
  width: 85%;
  height: 80px;
  border: 1px solid #ccc;
  margin: 10px auto;
  border-radius: 8px;
  display: flex;
  justify-content: center;
  align-items: center;
  font-size: 22px;
  background-color: #caa8b8;
}
.create-center .go-create:hover {
  box-shadow: 0px 0px 10px 4px pink;
  cursor: pointer;
}
.create-center .go-create span {
  margin-left: 8px;
}

:deep(.el-tabs__nav-wrap) {
  z-index: 0;
}
</style>
