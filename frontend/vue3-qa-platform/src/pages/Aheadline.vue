<template>
  <div style="width: 1050px; margin: 0 auto;">
    <h2>{{problem.title}}</h2>

    <div class="text-container" ref="textContainer" v-show="problem.content !== ''">
      <span>{{problem.content}}</span>
      <div class="toggle-button">
        <span ref="toggleButton">显示全部</span>
        <el-icon><ArrowDown /></el-icon>
      </div>
    </div>

    <div class="btn-container">
      <!-- 收藏 -->
      <button class="save" v-if="!problem.collectStatus" @click="handleCollectionClick(problem)">点击收藏</button>
      <button class="save" v-else @click="handleUnCollectionClick(problem)">取消收藏</button>
      <!-- 回答 -->
      <button class="rejoin" @click="onClick">写回答</button>
      <!-- 点赞 -->
      <div class="like" @click="handleLikeClick(problem)">
        <img
              v-if="!problem.likeStatus"
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
        <span ref="likeCount">{{ problem.lc }}</span>
      </div>
      <div class="page-view">
        <span style="margin-right: 4px;">浏览量</span>
        {{ problem.pageView }}</div>
    </div>
  </div>
</template>

<script setup>
import { onMounted, ref, defineEmits } from "vue";
import { ArrowDown } from "@element-plus/icons-vue";
import { collectionProblem,cancelCollectionProblem } from "@/api/api.collection";
import authentication from "@/store/authentication";
import { like,unlike,getLikeCount } from "@/api/api.likes";

defineProps(["problem"])

const auth = authentication()

const emit = defineEmits(["a"]);

function onClick() {
  emit("a", true);
}

const likeData = ref({
  like_count: 0,
  liked: false,
});

const handleCollectionClick = (problem) => {
  collectionProblem(auth.id,problem.id).then(res => {
    ElMessage.success(res.message)
    problem.collectStatus = true
  })
}

const handleUnCollectionClick = (problem) => {
  cancelCollectionProblem(auth.id,problem.id).then(res => {
    ElMessage.success(res.message)
    problem.collectStatus = false
  })
}

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

const textContainer = ref(null);

const toggleButton = ref(null);

onMounted(() => {
  toggleButton.value.addEventListener("click", () => {
    if (toggleButton.value.textContent == "显示全部") {
      toggleButton.value.innerText = "收起";
      textContainer.value.firstElementChild.style.whiteSpace = "wrap";
    } else {
      toggleButton.value.innerText = "显示全部";
      textContainer.value.firstElementChild.style.whiteSpace = "nowrap";
    }
  });
});
</script>

<style scoped>
div {
  background: rgb(245, 249, 255);
  display: flex;
  flex-direction: column;
}
div h2 {
  display: inline-block;
  padding-top: 0px;
  padding-left: 80px;
}
.text-container {
  display: flex;
  flex-direction: row;
  padding-left: 80px;
  overflow: hidden;
  /* flex: 1; */
}

.text-container > span:first-child {
  display: block;
  font-size: 16px;
  cursor: pointer;
  width: 660px;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}

.text-container .toggle-button {
  cursor: pointer;
  margin-left: 4px;
  border: none;
  background-color: rgb(245, 249, 255);
  font-size: 12px;
  color: rgb(132, 145, 165);
  display: flex;
  flex-direction: row;
  align-items: center;
}
.text-container .toggle-button .el-icon {
  font-size: 12px;
  color: rgb(132, 145, 165);
}
.btn-container {
  flex-direction: row;
  padding-left: 80px;
  margin-top: 15px;
  margin-bottom: 18px;
}
.btn-container button {
  background-color: rgb(23, 114, 246);
  border: none;
  margin-right: 20px;
  width: 80px;
  height: 30px;
  border-radius: 8px;
  color: #fff;
  cursor: pointer;
}
.btn-container .rejoin {
  color: rgb(87, 95, 108);
  background-color: #fff;
  border: 1px solid rgb(132, 145, 165);
}
.btn-container .like {
  flex-direction: row;
  cursor: pointer;
  display: flex;
  align-items: center;
}

.btn-container .like span {
  color: #787878;
  margin-left: 4px;
  font-size: 15px;
}

.page-view{
  margin-left: 20px;
  flex-direction: row;
  color: #787878;
  display: flex;
  align-items: center;
  font-size: 15px;
}
</style>
