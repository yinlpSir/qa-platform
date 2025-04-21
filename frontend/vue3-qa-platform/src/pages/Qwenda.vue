<template>
  <div class="qwenda">
    <div class="container">
      <div>
        <el-tabs
          v-model="activeTab"
          class="tabs"
          @tab-change="handleTabChange"
          @tab-click="handleTabClick"
        >
          <div class="nav_wenda">
            <el-tab-pane name="first">
              <template #label>
                <span class="custom-tabs-label">
                  <span class="iconfont" style="font-size: 17px; color: rgb(23, 114, 246)"
                    >&#xe6ac;</span
                  >
                  <span>猜你喜欢</span>
                </span>
              </template>
              <div class="context_wenda">
                <Qlist v-for="(p,i) in likeProblems" :key="i" :p="p"></Qlist>
              </div>
            </el-tab-pane>
            <el-tab-pane label="Config" name="second">
              <template #label>
                <span class="custom-tabs-label">
                  <span
                    class="iconfont"
                    style="font-size: 17px; color: rgb(132, 145, 165)"
                    >&#xe66e;</span
                  >
                  <span>人气问答</span>
                </span>
              </template>
              <div class="people_wenda">
                <Plist v-for="(p,i) in popularProblems" :key="i" :p="p"></Plist>
              </div>
            </el-tab-pane>
            <el-tab-pane label="Role" name="third">
              <template #label>
                <span class="custom-tabs-label">
                  <span
                    class="iconfont"
                    style="font-size: 17px; color: rgb(132, 145, 165)"
                    >&#xe628;</span
                  >
                  <span>最新挑战</span>
                </span>
              </template>
              <div class="new_wenda">
                <el-empty description="空空如也~" v-show="latestProblemsEmpty" />
                <Nlist v-for="(p,i) in latestProblems" :key="i" :p="p"></Nlist>
              </div>
            </el-tab-pane>
          </div>

        </el-tabs>
      </div>
      <!-- aside -->
      <div class="aside">
        <!-- 创作中心 -->
        <Aside></Aside>

        <!-- 推荐关注 -->
        <div class="suggested-follows">
          <div class="follows-header">
            <el-icon size="20"><UserFilled /></el-icon>
            <span>推荐关注</span>
          </div>

          <div class="follows-list">
            <div class="user" v-for="user in recommendedUsers" :key="user.username">
              <el-image class="user-avatar" :src="user.headPortraitToString" fit="cover" />
              <div class="user-body">
                <!-- 名字 -->
                <div class="follows-uname">{{ user.username }}</div>
                <!-- 个人签名 -->
                <div class="introduce">{{ user.introduce }}</div>
              </div>
              <!-- +关注 -->
              <div class="add-follows" v-if="!user.followStatus" @click="handleFollowClick(user)">
                <el-icon><Plus /></el-icon>
                <span>关注</span>
              </div>
              <div style="margin-left: 20px;"  v-else >
                <span>已关注</span>
              </div>
            </div>

            <div class="follows-refresh" @click="refreshFollowList">
              <el-icon><Refresh /></el-icon>
              <span>换一换</span>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { onMounted, ref } from "vue";
import { getRecommendedUser, getUserById } from "@/api/api.user";
import { follow,isUserFollowed } from "@/api/api.follow";
import authentication from "@/store/authentication";
import {
  Female,
  Document,
  Promotion,
  EditPen,
  ChatLineSquare,
  StarFilled,
  UserFilled,
  Plus,
  Edit,
  Refresh,
} from "@element-plus/icons-vue";
import Qlist from "./Qlist.vue";
import Plist from "./Plist.vue";
import Nlist from "./Nlist.vue";
import Aside from "./Aside.vue";
import { getLatestProblems, getPopularProblems, getProblemYouLike } from "@/api/api.problem";
import { getCommentCount, getLatelyCommentCount } from "@/api/api.comment";
import { getCountByProblemId } from "@/api/api.collection";
import { parseDate } from "element-plus";
import { getLikeCount } from "@/api/api.likes";

const auth = authentication()

const activeTab = ref("first");

// 处理tab被改变
const handleTabChange = (pane, event) => {
  if(pane == 'first'){
    getYouLikeP()
  }else if(pane == 'second'){
    getPopularP()
  }else if(pane == 'third'){
    getLatestP()
  }
};

const latestProblemsEmpty = ref(false)

const latestProblems = ref([])

const getLatestP = () => {
  getLatestProblems().then(res => {
    const {data} = res
    if(data.length <= 0){
      latestProblemsEmpty.value = true
      return ;
    }else latestProblemsEmpty.value = false
    latestProblems.value = data
    latestProblems.value.forEach(p => {
      getCommentCount(p.id).then(res => {
        p.comments = res.data
      })
      getCountByProblemId(p.id).then(res=>{
        p.collects = res.data // 收藏数
      })
    })
  })
}

const popularProblems = ref([])

const getPopularP = () => {
  getPopularProblems().then(res => {
    const {data} = res
    popularProblems.value = data
    popularProblems.value.forEach(p => {
      getLatelyCommentCount(p.id).then(res => {
        p.recentComments = res.data
      })
      getCommentCount(p.id).then(res => {
        p.comments = res.data
      })
      getLikeCount(p.id).then(res=>{
        p.likeCount = res.data
      })
    })
  })
}

const likeProblems = ref([])

const getYouLikeP = async() => {
  await getProblemYouLike(auth.id).then(res => {
    const {data} = res
    likeProblems.value = data
    likeProblems.value.forEach(p => {
      getLatelyCommentCount(p.id).then(res => {
        p.recentComments = res.data
      })
      getCommentCount(p.id).then(res => {
        p.comments = res.data
      })
      getCountByProblemId(p.id).then(res=>{
        p.collects = res.data // 收藏数
      })
    })
  })
}

onMounted(()=>{
  getYouLikeP()
  getRecommendedUsers()
})

const recommendedUsers = ref([]) // 推荐关注用户列表

const getRecommendedUsers = async() => {
  await getRecommendedUser().then(res => {
    const {data} = res
    recommendedUsers.value = data
    checkUserFollowed(recommendedUsers.value)
  })
}

const checkUserFollowed = (users)=>{
  users.forEach(u => {
    isUserFollowed(auth.id,u.id).then(res=>{
      const {data} = res
      u.followStatus = data
    })
  });
}

// 关注
const handleFollowClick = (user) => {
  follow(auth.id,user.id).then(res => {
    ElMessage.success(res.message)
    user.followStatus = true
  })
};

// 换一换 推荐关注列表
const refreshFollowList = () => {
  getRecommendedUsers()
};

</script>

<style scoped>

/* 去掉下划线 */
:deep(.el-tabs__active-bar) {
  background-color: transparent !important;
}

.qwenda {
  display: flex;
  justify-content: center;
  flex-wrap: wrap;
  min-width: 1200px;
  margin-top: 4px;
}

.container {
  display: flex;
  justify-content: space-between;
  width: 1100px;
}
.tabs {
  border: 1px solid #ccc;
  border-top: 1px solid #ccc;
  border-radius: 6px;
  width: 800px;
  min-height: 700px;
  padding: 4px 0px;
  /* background: palegreen; */
}

/* div盒子下面bottom */

.tabs .context_wenda {
  border: none;
}

:deep(.el-tabs__content) {
  margin: 0 6px;
}

.tabs .custom-tabs-label .iconfont {
  padding-left: 10px;
}
.tabs .custom-tabs-label span {
  margin-left: 5px;
}
.aside {
  width: 280px;
}
.aside .create-center {
  border: 1px solid #ccc;
  border-radius: 6px;
}
.aside .create-center .create-header {
  display: flex;
  align-items: center;
  border-bottom: 1.5px solid #e4e7ed;
  padding: 11px 20px;
}
.aside .create-center .create-header span {
  margin-left: 6px;
  font-weight: bold;
}
.aside .create-center .nav-user {
  display: flex;
  justify-content: space-around;
  margin-top: 20px;
}
.aside .create-center .nav-user .qq {
  width: 85px;
  display: flex;
  justify-content: center;
  align-items: center;
  flex-wrap: wrap;
}
.aside .create-center .nav-user .qq span {
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
.aside .create-center .random-advertising {
  width: 85%;
  height: 110px;
  margin: 0 auto;
  border-radius: 8px;
  margin-top: 10px;
}
.aside .create-center .random-advertising img {
  width: 100%;
  height: 100%;
  border-radius: 8px;
}
.aside .create-center .go-create {
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
.aside .create-center .go-create:hover {
  box-shadow: 0px 0px 10px 4px pink;
  cursor: pointer;
}
.aside .create-center .go-create span {
  margin-left: 8px;
}

.aside .suggested-follows {
  margin-top: 8px;
  border: 1px solid #ccc;
  border-radius: 6px;
}
.aside .suggested-follows .follows-header {
  display: flex;
  align-items: center;
  border-bottom: 1.5px solid #e4e7ed;
  padding: 11px 20px;
}
.aside .suggested-follows .follows-header span {
  margin-left: 6px;
  font-weight: bold;
}
.aside .suggested-follows .follows-list .user {
  display: flex;
  justify-content: left;
  align-items: center;
  width: 85%;
  border: 1px solid #ccc;
  margin: 16px auto;
  border-radius: 6px;
}
.aside .suggested-follows .follows-list .user .user-avatar {
  width: 45px;
  height: 45px;
  border-radius: 6px;
}
.aside .suggested-follows .follows-list .user .user-body {
  margin-left: 8px;
}
.aside .suggested-follows .follows-list .user .user-body .follows-uname {
  width: 110px;
  white-space: nowrap;
  text-overflow: ellipsis;
  overflow: hidden;
}
.aside .suggested-follows .follows-list .user .user-body .introduce {
  width: 110px;
  font-size: 12px;
  white-space: nowrap;
  text-overflow: ellipsis;
  overflow: hidden;
}
.aside .suggested-follows .follows-list .user .add-follows {
  display: flex;
  justify-content: center;
  align-items: center;
  margin-left: 20px;
  cursor: pointer;
}
.aside .suggested-follows .follows-list .user .add-follows:hover{
  color: #caa8b8;
}
.aside .suggested-follows .follows-list .follows-refresh {
  display: flex;
  align-items: center;
  justify-content: center;
  margin-bottom: 16px;
  cursor: pointer;
}
.aside .suggested-follows .follows-list .follows-refresh:hover{
  color: #caa8b8;
}
.aside .suggested-follows .follows-list .follows-refresh span {
  margin-left: 8px;
}
/* 问答导航栏的内边距 */
:deep(.el-tabs__nav-wrap) {
  z-index: 0;
  padding-top: 12px;
  padding-bottom: 18px;
}
</style>
