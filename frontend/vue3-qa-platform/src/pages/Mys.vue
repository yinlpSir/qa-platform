<template>
  <div class="mys-box">

    <div class="header">
      <div class="mys-img">
        <el-row>
          <el-col :span="24">
            <el-avatar shape="square" :size="50" :src="us.headPortrait" />
          </el-col>
        </el-row>
      </div>
      <div class="header-side">
        <div class="mys-text">
          <div class="name">
            <h3>
              <span style="margin-right: 4px">{{ userInfo.username }}</span>
              <el-icon v-if="userInfo.gender == '男'" style="color: blue">
                <Male />
              </el-icon>
              <el-icon v-else style="color: pink">
                <Female />
              </el-icon>
            </h3>

            <div class="followed" @click="handleAttentionClick(userInfo.id)">
              <h4>{{us.followedNumber}} 关注</h4>
            </div>
            <div class="fans" @click="handleFansClick(userInfo.id)">
              <h4>{{us.followerNumber}} 粉丝</h4>
            </div>
          </div>
          <div class="contact">
            <el-icon><Iphone /></el-icon>
            <span style="margin-left: 2px;">{{ userInfo.phoneNumber }}</span>
          </div>
          <div class="personal-profile" ref="personal_text">
            <span class="per">{{ userInfo.introduce }}</span>
          </div>
        </div>
        <div style="display: flex;align-items: center;">
          <el-row>
            <el-button type="primary" plain @click="dialogFormVisible = true"
              >编辑个人资料</el-button
            >
          </el-row>
          <!-- 头像，姓名，性别，签名，故乡，联系方式 ，爱好-->
          <el-dialog @open="handleDialogOpen" v-model="dialogFormVisible" title="编辑个人资料" width="730">
            <div style="display: flex;align-items: center;margin: 30px 40px;justify-content: space-between;">
              <div>
                <el-upload
                    class="avatar-uploader"
                    :show-file-list="false"
                    :on-success="handleAvatarSuccess"
                    :auto-upload="false"
                    :on-change="handleFileChange"
                  >
                    <img v-if="avatarUrl" :src="avatarUrl" class="avatar" />
                    <el-icon v-else class="avatar-uploader-icon"><Plus /></el-icon>
                  </el-upload>
                  <h4 style="text-align: center;margin-top:4px;">点击修改</h4>
              </div>
              <el-form :model="form" ref="modifyUserFormRef" :rules="modifyUserFormRules" label-width="100px" style="width: 400px;">
                <el-form-item label="用户昵称:" prop="username">
                  <el-input v-model="form.username" clearable autocomplete="off" />
                </el-form-item>
                <el-form-item label="联系方式:" prop="phoneNumber">
                  <el-input v-model="form.phoneNumber" autocomplete="off" />
                </el-form-item>
                <el-form-item label="年龄:" prop="age">
                  <el-input v-model="form.age" type="number" autocomplete="off" />
                </el-form-item>
                <el-form-item label="性别:" prop="gender">
                  <el-radio-group v-model="form.gender">
                    <el-radio value="男" size="small">男</el-radio>
                    <el-radio value="女" size="small">女</el-radio>
                  </el-radio-group>
                </el-form-item>
                <el-form-item label="个人简介:">
                  <el-input v-model="form.introduce" type="textarea" clearable autocomplete="off" />
                </el-form-item>
                <el-form-item>
                  <el-button type="primary" @click="submitForm(modifyUserFormRef)">
                    提交
                  </el-button>
                </el-form-item>
              </el-form>
            </div>
          </el-dialog>
        </div>
      </div>
    </div>

    <div class="mysContent">
      <el-tabs
        v-model="activeTab"
        class="tabs"
        @tab-change="handleTabChange"
      >
        <el-tab-pane label="我的问题" name="myProblem" style="min-height: 500px;">
          <el-empty description="空空如也~" v-show="myProblemEmpty" />
          <MyProblem v-for="(problem,index) in myProblem" :key="index" :problem="problem" />
        </el-tab-pane>
        <el-tab-pane :label="'我的回答('+myCommentCount+')'" name="myAnswer" style="min-height: 500px;">
          <el-empty description="空空如也~" v-show="myAnswerEmpty" />
          <!-- 显示"回答的页面" -->
          <my-answer v-for="(p,i) in myComments" :key="i" :problem="p" />
        </el-tab-pane>
        <el-tab-pane :label="'我的收藏('+myCollectedCount+')'" name="myCollected" style="min-height: 500px;">
          <el-empty description="空空如也~" v-show="myCollectedEmpty" />
          <!-- 显示页面 -->
          <Question v-for="p in myCollectedProblem" :key="p.username" :question="p" />
        </el-tab-pane>
        <div style="display: flex;justify-content: center;margin: 10px 0;">
          <el-pagination class="paging" 
            background 
            layout="prev, pager, next" 
            :total="total" 
            :default-page-size="paging.pageSize"
            :hide-on-single-page="true"
            @current-change="handleCurrentChange"
          />
        </div>
      </el-tabs>
    </div>

    <el-drawer v-model="followDrawerVisible" :show-close="true" @close="handleFollowDrawerClose">
      <template #header>
        <div style="font-size: 20px;font-weight: bold;">{{followDrawerTitle}}</div>
      </template>
      <Fans v-for="(f,ind) in followList" :user="f" :key="ind" />
    </el-drawer>
  </div>
</template>

<script setup>
import MyProblem from "@/components/MyProblem.vue";
import Question from "./Question.vue";
import Fans from "@/components/Fans.vue";
import { getCommentCount, getCommentCountByUserId, getCommentsByUserId } from "@/api/api.comment";
import { ArrowDown,Iphone,Plus, Male, Female } from "@element-plus/icons-vue";
import { onMounted, reactive, ref, toRaw } from "vue";
import { getCurrentUser,getHeadPortrait,getUserById,modifyHeadPortrait, modifyUserBaseInfo } from "@/api/api.user";
import {  getProblemById, getProblemsByUserId,getProblemByUserId } from "@/api/api.problem";
import userStore from "@/store/user.store";
import { getFollowedUsers,getFollowedCount, getFollowersCount,getFans,isUserFollowed } from "@/api/api.follow";
import { ElMessage } from "element-plus";
import authentication from "@/store/authentication";
import { checkLikeStatus, getLikeCount } from "@/api/api.likes";
import { checkProblemCollected, getCollectByUserId, getCountByUserId } from "@/api/api.collection";

const auth = authentication()

const us = userStore()

const activeTab = ref("myProblem");

const userInfo = reactive({}); // 用户信息

const myProblemEmpty = ref(false)

const myAnswerEmpty = ref(false)

const myCollectedEmpty = ref(false)

const myProblem = ref([])

const myComments = ref([])

const myCommentCount = ref(0) // 回答数

const myCollectedProblem = ref([])

const myCollectedCount = ref(0) // 收藏数

const total = ref(0) // 分页的总记录数

const paging = reactive({
  pageSize:6,
  currentPage:1
})

const handleCurrentChange = (number) => {
  paging.currentPage = number
  
  if(activeTab.value == 'myProblem'){
    getMineProblems()
  }else if(activeTab.value == 'myAnswer'){
    getMineComments()
  }else {
    getMineCollectedProblems()
  }
}

const followList = ref([]) // 用户关注的用户 

const followDrawerVisible = ref(false)

const followDrawerTitle = ref('')

const handleAttentionClick = (userId)=>{
  getFollowedUsers(userId).then(res=>{
    const {data} = res
    followList.value = data
    checkUserFollowed(followList.value)
    followDrawerTitle.value = '我的关注'
    followDrawerVisible.value = true
  })
}

const handleFansClick = (userId)=>{
  getFans(userId).then(res=>{
    const {data} = res
    followList.value = data
    checkUserFollowed(followList.value)
    followDrawerTitle.value = '我的粉丝'
    followDrawerVisible.value = true
  })
}

const checkUserFollowed = (users)=>{
  users.forEach(u => {
    isUserFollowed(userInfo.id,u.id).then(res=>{
      const {data} = res
      u.followStatus = data
    })
  });
}

const handleFollowDrawerClose = ()=>{
  getFollowCount(userInfo.id)
}

const handleTabChange = (tabPaneName)=>{
  if(tabPaneName === 'myProblem'){
    paging.currentPage = 1;
    paging.pageSize = 6;
    getMineProblems()
  }else if(tabPaneName === 'myAnswer'){
    paging.currentPage = 1;
    paging.pageSize = 6;
    getMineComments()
  }else {
    paging.currentPage = 1;
    paging.pageSize = 6;
    getMineCollectedProblems()
  }
}

const getMineComments = async()=>{
  await getCommentCountByUserId(userInfo.id).then(res => {myCommentCount.value = res.data})

  if(myCommentCount.value <= 0){
    myAnswerEmpty.value = true;
    myComments.value = []
    return ;
  }else myAnswerEmpty.value = false;

  await getCommentsByUserId(userInfo.id,toRaw(paging)).then(res => {
    const {data} = res
    myComments.value = data.data;
    total.value = data.total

    myComments.value.forEach(c => {
      c.headPortrait = userInfo.headPortraitToString;
      c.username = userInfo.username
      c.gender = userInfo.gender
      getProblemById(c.problemId).then(res => {
        const {data} = res
        c.problemName = data.title
      })
    })
  })
}

const getMineCollectedProblems = async ()=>{
    await getCountByUserId(userInfo.id).then(res => {myCollectedCount.value = res.data} )
    if(myCollectedCount.value <= 0) {
      myCollectedEmpty.value = true
      myCollectedProblem.value = []
      return ;
    }
    const collectedProblems = await getCollectByUserId(userInfo.id,toRaw(paging))
    const {data} = collectedProblems
    myCollectedProblem.value = []
    
    myCollectedEmpty.value = false
    total.value = data.total
    data.data.forEach(async c => {
      let temp={};
      await getProblemById(c.problemId).then(res => {
        const {data} = res
        temp.problem = data
      })
      await getUserById(temp.problem.userId).then(res=>{
        const {data} = res
        temp.username = data.username
        temp.gender = data.gender
        temp.headPortrait = data.headPortraitToString
      })
      await getLikeCount(temp.problem.id).then(res => {
        temp.problem.lc = res.data // 问题点赞数
      })
      await checkLikeStatus(auth.id,temp.problem.id).then(res => {
        temp.problem.likeStatus = res // 当前用户的点赞状态
      })
      await checkProblemCollected(auth.id,temp.problem.id).then(res => {
        temp.problem.collectStatus = res // 当前用户的收藏状态
      })
      await getCommentCount(temp.problem.id).then(res => {
        temp.commentCount = res.data // 问题的评论数
      })
      myCollectedProblem.value.push(temp)
    })
}

const getMineProblems = async () => {
  await getProblemsByUserId(userInfo.id,toRaw(paging)).then(res => {
    const {data} = res
    myProblem.value = data.data
    total.value = data.total

    if(myProblem.value.length <= 0) {
      myProblemEmpty.value = true
      return ;
    }else myProblemEmpty.value = false

    myProblem.value.forEach(p => {
      getCommentCount(p.id).then(res => {
        p.commentsCount = res.data
      })
      getLikeCount(p.id).then(res => {
        p.likeCount = res.data
      })
    })
  })
}

/**
 * 获取粉丝和关注 数
 * @param userId 
 */
const getFollowCount = (userId) => {
  getFollowedCount(userId).then((res) => {
      const { data } = res;
      us.setFollowedNumber(data)
    });
    getFollowersCount(userId).then((res) => {
      const { data } = res;
      us.setFollowerNumber(data)
    });
}

onMounted( async () => {
  await getCurrentUser().then((res) => {
    const { data } = res;
    Object.assign(userInfo, data);
    us.setHeadPortrait(userInfo.headPortraitToString)
    console.log(userInfo);
    getFollowCount(userInfo.id)
  });
  await getMineProblems()
  // 获取我的回答数
  await getCommentCountByUserId(userInfo.id).then(res => {myCommentCount.value = res.data})
  // 获取我的收藏数
  await getCountByUserId(userInfo.id).then(res => {myCollectedCount.value = res.data} )
});

const dialogFormVisible = ref(false);

const avatarUrl = ref("");

const modifyUserFormRef = ref(null);

const form = reactive({
  id:"",
  username: "",
  phoneNumber:"",
  gender: "",
  introduce: "",
  age: 0
});

const modifyUserFormRules = reactive({
  username:[
    { required: true, message: '请输入用户名', trigger: 'blur' },
  ],
  phoneNumber:[
    {
      required: true,
      validator: (rule, value, callback) => {
        let reg = /^1[3|4|5|7|8|9][0-9]{9}$/;
        if (value == "") callback(new Error("请输入手机号"));
        else if (!reg.test(value)) callback(new Error("清输入正确的手机号格式"));
        else callback();
      },
      trigger: ["blur","change"],
    },
  ],
  age:[
    {
      validator:(rule,value,callback)=>{
        if(value <= 0 || value >= 120 ) callback(new Error("年龄不符，请重新输入"))
        else callback()
      },
      trigger:"blur"
    }
  ],
  gender: [{ required: true, message: "请选择性别", trigger: "change" }],
})

const submitForm = async (formEl) => {
  if (!formEl) return
  await formEl.validate((valid, fields) => {
    if (valid) {
      modifyUserBaseInfo(toRaw(form)).then(res => {
        location.reload()
        ElMessage.success(res.message)
        auth.token = res.data
        dialogFormVisible.value = false
      })
    }
  })
}

const handleDialogOpen = ()=>{
  Object.assign(form,userInfo);
  avatarUrl.value = us.headPortrait
}

const handleFileChange = (uploadFile, uploadFiles) => {
  // uploadFile.raw 才是上传的原始文件
  modifyHeadPortrait(userInfo.id,uploadFile.raw).then(res=>{
    ElMessage.success(res.message)
    us.setHeadPortrait(URL.createObjectURL(uploadFile.raw))
    avatarUrl.value = URL.createObjectURL(uploadFile.raw);
  })
};

</script>

<style scoped>
.mys-box {
  margin: 0 auto;
  height: 500px;
  width: 1100px;
  display: flex;
  flex-direction: column;
}
.header {
  display: flex;
  flex-direction: row;
  background-image: url("/src/assets/imgs/beijing7.jpg");
  background-repeat: no-repeat;
  background-size:100% 350px;
  border-radius: 10px;
  padding-bottom: 15px;
  padding-left: 15px;
  padding-top: 80px;
}
.mysContent .el-tabs {
  padding: 0 15px;
  border: 1px solid #ccc;
  border-radius: 10px;
  min-height: 500px;
  margin-top: 10px;
}
.mys-img {
  /* background-color: rgb(167, 144, 238); */
  display: flex;
  flex-direction: row;
}
.mys-img .el-avatar {
  width: 130px;
  height: 130px;
  border: 2px solid #fff;
}
.header-side {
  display: flex;
  justify-content: space-between;
  /* background-color: lightyellow; */
}
.mys-text {
  display: flex;
  flex-direction: column;
  width: 800px;
  /* background-color: palegoldenrod; */
}

.paging{
  /* margin: 10px auto;
  width: 100px;
  height: 40px; */
}

.name {
  display: flex;
  flex-direction: row;
  /* background-color: palevioletred; */
  margin-top: 20px;
}
.name h3 {
  margin-left: 15px;
  font-size: 20px;
  display: flex;
  align-items: center;
}

:deep(.el-tabs__nav-wrap:after){
  height: 1px;
}

.contact{
  font-size: 12px;
  padding-left: 15px;
  display: flex;
  align-items: center;
  margin: 4px 0;
}

.personal-profile {
  display: flex;
  padding-left: 15px;
  flex-direction: row;
  /* background-color: pink; */
  font-size: 12px;
  /* overflow: hidden; */
}
.personal-profile .per {
  display: block;

  cursor: pointer;
  width: 600px;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}

.personal-profile #showall {
  margin-left: 15px;
  font-size: 12px;
  cursor: pointer;
}
.personal-profile span .el-icon {
  font-size: 12px;
  color: rgb(132, 145, 165);
}
.followed {
  margin-left: 35px;
  margin-top: 5px;
  display: flex;
  flex-direction: row;
  cursor: pointer;
}
.followed h4 {
  font-size: 14px;
  font-weight: normal;
}
.followed:hover{
  color: #409eff;
}
.fans {
  margin-left: 35px;
  margin-top: 5px;
  display: flex;
  flex-direction: row;
  cursor: pointer;
}
.fans h4 {
  font-size: 13px;
  font-weight: normal;
}
.fans:hover{
  color: #409eff;
}
</style>

<style scoped>
.avatar{
  width: 180px!important;
  height: 180px!important;
  border-radius: 8px;
}
.avatar-uploader .el-upload {
  border: 1px dashed var(--el-border-color);
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
  width: 180px;
  height: 180px!important;
  transition: var(--el-transition-duration-fast);
}

.avatar-uploader .el-upload:hover {
  border-color: var(--el-color-primary);
}

.el-icon.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 178px!important;
  height: 178px!important;
  text-align: center;
}
</style>
