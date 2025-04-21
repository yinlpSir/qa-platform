<template>
  <div class="main">
    <div id="app">
      <div class="" v-if="gb">
        <img src="https://devpress.csdnimg.cn/bf3432ea868e4b75a1f5325d49a8d769.png" />
        <span @click="dj">关闭</span>
      </div>
    </div>
    <!-- 单一广告 -->
    <a href="https://www.csdn.net/" target="_blank">
      <img
        class="ad"
        src="https://devpress.csdnimg.cn/bf3432ea868e4b75a1f5325d49a8d769.png"
        alt="ad"
        style="cursor: pointer"
      />
    </a>
    <div class="container">
      <div>
        <el-tabs
          v-model="activeTab"
          class="tabs"
          @tab-change="handleTabChange"
          @tab-click="handleTabClick"
        >
          <el-tab-pane label="热门" name="first">
            <div v-loading="loading" :element-loading-text="'正在加载中'" style="height: 100%;
            overflow: auto;">
              <hot-question v-for="(o, index) in hotData" :key="index" :question="o" />
            </div>
          </el-tab-pane>
          <el-tab-pane label="关注" name="second">
            <el-empty description="空空如也~" v-if="followedProblemEmptyShow" />
            <Question v-for="p in followedUserProblems" :key="p.username" :question="p" />
          </el-tab-pane>
          <el-tab-pane label="推荐" name="third">
            <el-empty description="空空如也~" v-if="recommendEmptyShow" />
            <div v-loading="loading" element-loading-text="正在加载中" style="height: 100%;
            overflow: auto;">
              <Question v-for="p in recommendedData" :key="p.username" :question="p" />
            </div>
          </el-tab-pane>
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
import { onMounted, reactive, ref, toRaw } from "vue";
import {
  Female,
  Promotion,
  ChatLineSquare,
  StarFilled,
  UserFilled,
  Refresh,
  Tickets,
  Plus,
  Loading
} from "@element-plus/icons-vue";
import HotQuestion from "./HotQuestion.vue";
import { hot, getRecommended,create, getFollowedUserProblems } from "@/api/api.problem";
import { getRecommendedUser, getUserById } from "@/api/api.user";
import { follow,isUserFollowed } from "@/api/api.follow";
import Question from "./Question.vue";
import Aside from "./Aside.vue";
import { useRoute, useRouter } from "vue-router";
import authentication from "@/store/authentication";
import problemStore from "@/store/problem.store";
import { checkProblemCollected } from "@/api/api.collection";
import { checkLikeStatus, getLikeCount } from "@/api/api.likes";
import { getCommentCount } from "@/api/api.comment";

const auth = authentication()

const ps = problemStore()

const router = useRouter();

const loading = ref(true)

const activeTab = ref("first");

// 处理tab被改变
const handleTabChange = (pane, event) => {
  if (pane == "first") {
    getHotData();
  } else if (pane == "second") {
    // 获取关注列表数据
    loadFollowedUserData();
  } else {
    loadRecommendedData();
  }
};

const hotData = ref([]); // 热门数据

const followedUserProblems = ref([]) // 我关注的用户的问题

const recommendedData = ref([]); // 推荐的问题

const recommendedUsers = ref([]) // 推荐关注用户列表

const recommendEmptyShow = ref(false);

const followedProblemEmptyShow = ref(false);

const getHotData = async () => {
  loading.value = true
  await hot().then((res) => {
    hotData.value = res.data;
  });
  loading.value = false
};

const getRecommendedUsers = async() => {
  loading.value = true
  await getRecommendedUser().then(res => {
    const {data} = res
    recommendedUsers.value = data
    checkUserFollowed(recommendedUsers.value)
  })
  loading.value = false
}

const checkUserFollowed = (users)=>{
  users.forEach(u => {
    isUserFollowed(auth.id,u.id).then(res=>{
      const {data} = res
      u.followStatus = data
    })
  });
}

const pagingParam = reactive({
  pageSize: 40,
  currentPage: 1,
});

const loadFollowedUserData = async () => {
  pagingParam.currentPage = 1;
  pagingParam.pageSize = 40;
  getFollowedUserProblems(auth.id,toRaw(pagingParam)).then(res => {
    const {data} = res
    followedUserProblems.value = data.data
    if(data.total == 0){
      followedProblemEmptyShow.value = true;
      return ;
    }else followedProblemEmptyShow.value = false
    followedUserProblems.value.forEach(p => {
      p.problem = p
      getUserById(p.userId).then(res => {
        const {data} = res
        p.headPortrait = data.headPortraitToString
        p.username = data.username
      })
      checkProblemCollected(auth.id,p.id).then(res => {
        p.problem.collectStatus = res // 用户收藏问题的状态。 true is collected
      })
      getCommentCount(p.id).then(res => {
        p.commentCount = res.data
      })
      // 获取每个问题的点赞数
      getLikeCount(p.id).then(res => {
        p.problem.lc = res.data
      })
      // 用户的点赞状态
      checkLikeStatus(auth.id,p.id).then(res => {
        p.problem.likeStatus = res
      })
    })
  })
}

const loadRecommendedData = async () => {
  pagingParam.currentPage = 1;
  pagingParam.pageSize = 40;
  await getRecommended(toRaw(pagingParam)).then((res) => {
    const { data } = res;
    recommendedData.value = data.data;
    if (res.data.total == 0) {
      recommendEmptyShow.value = true;
      return ;
    }else recommendEmptyShow.value = false;

    recommendedData.value.forEach(p => {
      checkProblemCollected(auth.id,p.problem.id).then(res => {
        p.problem.collectStatus = res // 用户收藏问题的状态。 true is collected
      })
      // 获取每个问题的点赞数
      getLikeCount(p.problem.id).then(res => {
        p.problem.lc = res.data
      })
      checkLikeStatus(auth.id,p.problem.id).then(res => {
        p.problem.likeStatus = res
      })
    })
  });
};

onMounted(() => {
  getHotData();
  getRecommendedUsers()
});

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
.main {
  display: flex;
  justify-content: center;
  flex-wrap: wrap;
  min-width: 1200px;
  margin-top: 4px;
}
.ad {
  width: 1100px;
  height: 100px;
  margin-bottom: 8px;
  border-radius: 8px;
}
.container {
  display: flex;
  justify-content: space-between;
  width: 1100px;
}
.tabs {
  border: 1px solid #ccc;
  border-radius: 6px;
  width: 800px;
  min-height: 700px;
  padding: 5px 6px;
}

:deep(.el-tabs__nav-wrap:after){
  height: 1px;
}
:deep(.el-tabs__header){
  margin: 0;
}

.aside {
  width: 280px;
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
</style>
