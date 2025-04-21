<template>
  <div
    style="padding: 0px 10px;margin: 16px 0px; cursor: pointer"
    @click="toZhihuProblem(question.problemLink)"
  >
    <div class="questionTitle">
      <h4>{{ question.title }}</h4>
    </div>

    <div class="questionContent">
      <img
        v-if="question.cover == null ? false : true"
        :src="question.cover"
        width="200"
        alt="问题封面"
        style="margin-right: 10px"
      />
      <div class="mainContent">
        <span>
          {{ question.content }}
        </span>
        <div class="threeIcon">
          <span class="share">
            <img
              src="/src/assets/icon/hot.png"
              alt="热度"
              style="width: 18px; height: 18px"
            />
            <span id="shareIcon">{{ question.heat }}</span>
          </span>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import {
  Female,
  Male,
  Promotion,
  ChatLineSquare,
  StarFilled,
  UserFilled,
  Plus,
  Edit,
  Refresh,
} from "@element-plus/icons-vue";

defineProps(["question"]);

const toZhihuProblem = (link) => {
  window.open(link, "_blank");
};
</script>

<style scoped>
.userIfor {
  display: flex;
  align-items: center;
}
.userIfor img {
  width: 35px;
  border-radius: 30px;
}
.userIfor .personalContent {
  display: flex;
  align-items: center;
  margin-left: 10px;
}
.userIfor .personalContent .uname {
  font-size: 14px;
  font-weight: bold;
}
.userIfor .personalContent .time {
  color: rgb(143, 136, 136);
  font-size: 12px;
  margin-left: 10px;
}
.userIfor .personalContent .el-icon {
  color: rgb(143, 136, 136);
  font-size: 14px;
  margin-left: 4px;
}

/* 问题标题 */
.questionTitle {
  margin: 6px 0;
}

/* 问题内容 */
.questionContent {
  display: flex;
  flex-direction: row;
  height: 120px;
  overflow: hidden;
}

.questionContent img {
  width: 200px;
  height: 100%;
  border-radius: 10px;
}
.questionContent .mainContent {
  /* display: flex; */
  /* flex-direction: column; */
  /* flex-wrap: wrap; */
}
.questionContent .mainContent > span:first-child {
  display: -webkit-box;
  /* margin-left: 10px; */
  margin-right: 10px;
  margin-bottom: 15px;
  min-height: 68%;
  -webkit-box-orient: vertical;
  -webkit-line-clamp: 4;
  overflow: hidden;
}

/* 图标 */
.questionContent .mainContent .threeIcon {
  /* margin-left: 10px; */
  display: flex;
  height: 22px;
}
.questionContent .mainContent .threeIcon .share {
  display: flex;
  align-items: center;
  flex-direction: row;
}
.questionContent .mainContent .threeIcon .share #shareIcon {
  margin-left: 5px;
}
.questionContent .mainContent .threeIcon .comments {
  display: flex;
  align-items: center;
}
.questionContent .mainContent .threeIcon .comments .el-icon {
  margin-left: 20px;
}
.questionContent .mainContent .threeIcon .comments #commentsIcon {
  margin-left: 5px;
}
.questionContent .mainContent .threeIcon .collection {
  display: flex;
  align-items: center;
}
.questionContent .mainContent .threeIcon .collection img {
  margin-left: 10px;
}
.questionContent .mainContent .threeIcon .collection #collectionIcon {
  margin-left: 5px;
}
</style>
