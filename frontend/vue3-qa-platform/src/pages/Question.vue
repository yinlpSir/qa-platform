<template>
  <div
    style="padding: 10px 10px; margin-bottom: 6px;"
  >
    <div class="userIfor">
      <img :src="question.headPortrait"  alt="头像" />
      <div class="personalContent">
        <span class="uname">{{ question.username }}</span>
        <el-icon v-if="question.gender == '男'" style="color: blue">
          <Male />
        </el-icon>
        <el-icon v-else style="color: pink">
          <Female />
        </el-icon>
        <span class="time"
          >发布于{{ question.problem.createTime.replace("T", " ") }}</span
        >
      </div>
    </div>

    <div class="questionTitle" >
      <h4 @click="toAnswers(question.problem.id)">{{ question.problem.title }}</h4>
    </div>
    <!-- 问题 -->
    <div class="questionContent">
      <img
        v-if="question.problem.coverToString == null ? false : true"
        :src="question.problem.coverToString"
        alt="问题封面"
        style="margin-right: 10px; cursor: pointer;"
        @click="toAnswers(question.problem.id)"
      />
      <div class="mainContent">
        <div class="content" @click="toAnswers(question.problem.id)">
          {{ question.problem.content }}
        </div>
        <div class="threeIcon" >
          <span class="share" @click="handleLikeClick(question.problem)">
            <img
              v-if="!question.problem.likeStatus"
              src="/src/assets/icon/like.png"
              alt="点赞"
              style="width: 18px; height: 18px"
            />
            <img
              v-else
              src="/src/assets/icon/like_active.png"
              alt="已点赞"
              style="width: 18px; height: 18px"
            />
            <span id="shareIcon"
              >{{ question.problem.lc > 100 ? "99+" : question.problem.lc }} 个赞</span
            >
          </span>

          <span class="comments" @click="toAnswers(question.problem.id)">
            <el-icon>
              <ChatLineSquare />
            </el-icon>
            <span id="commentsIcon">
              {{ question.commentCount > 100 ? "99+" : question.commentCount }} 回答
            </span>
          </span>

          <span class="collection" @click="handleCollectClick(question.problem)">
            <img
              v-if="!question.problem.collectStatus"
              src="/src/assets/icon/collect.png"
              alt="未收藏icon"
              style="width: 18px; height: 18px"
            />
            <img
              v-else
              src="/src/assets/icon/collect_active.png"
              alt="已收藏icon"
              style="width: 18px; height: 18px"
            />
            <span id="collectionIcon">收藏</span>
          </span>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { cancelCollectionProblem, collectionProblem } from "@/api/api.collection";
import { like, unlike,getLikeCount } from "@/api/api.likes";
import authentication from "@/store/authentication";
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
import { useRoute, useRouter } from "vue-router";

const auth = authentication()

const router = useRouter();

defineProps(["question"]);

const toAnswers = (pi) => {
  router.push({
    name: "answers",
    params: {
      questionId: pi,
    },
  });
};

const handleLikeClick = (p) => {
  if(p.likeStatus == false){
    // 点赞问题
    like(auth.id, p.id).then(res=> {
      p.likeStatus = true
      getLikeCount(p.id).then(res=>{
        p.lc = res.data
      })
    })
  }else {
    // 取消点赞
    unlike(auth.id, p.id).then(res=> {
      p.likeStatus = false
      getLikeCount(p.id).then(res=>{
        p.lc = res.data
      })
    })
  }
}

const handleCollectClick = (p) => {
  if(p.collectStatus == false){
    // 收藏问题
    collectionProblem(auth.id,p.id).then(res => {
      ElMessage.success(res.message)
      p.collectStatus = true
    })
  }else {
    // 取消收藏
    cancelCollectionProblem(auth.id,p.id).then(res => {
      ElMessage.success(res.message)
      p.collectStatus = false
    })
  }
}
</script>

<style scoped>
.userIfor {
  display: flex;
  align-items: center;
}
.userIfor img {
  width: 35px;
  height: 35px;
  border-radius: 50%;
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
  cursor: pointer;
}
.questionTitle:hover{
  color: rgb(82, 168, 236);
}
.questionTitle h4 {
}

/* 问题内容 */
.questionContent {
  display: flex;
  /* min-height: 0; */
  /* max-height: 120px; */
  /* height: 120px; */
  overflow: hidden;
}

.questionContent img {
  width: 200px;
  height: 100%;
  border-radius: 10px;
}
.questionContent .mainContent {
  display: flex;
  flex-direction: column;
  justify-content: space-between;
}
.content{
  cursor: pointer;
  display: -webkit-box;
  margin-bottom: 6px;
  -webkit-box-orient: vertical;
  -webkit-line-clamp: 4;
  overflow: hidden;
}
.content:hover{
  color: rgb(82, 168, 236);
}

/* 图标 */
.questionContent .mainContent .threeIcon {
  display: flex;
  height: 22px;
}
.questionContent .mainContent .threeIcon .share {
  display: flex;
  align-items: center;
  margin-right: 15px;
  cursor: pointer;
}
.questionContent .mainContent .threeIcon .share #shareIcon {
  margin-left: 3px;
}
.questionContent .mainContent .threeIcon .comments {
  display: flex;
  align-items: center;
  margin-right: 15px;
  cursor: pointer;
}
.questionContent .mainContent .threeIcon .comments #commentsIcon {
  margin-left: 3px;
}
.questionContent .mainContent .threeIcon .collection {
  display: flex;
  align-items: center;
  margin-right: 15px;
  cursor: pointer;
}
.questionContent .mainContent .threeIcon .collection #collectionIcon {
  margin-left: 3px;
}
</style>
