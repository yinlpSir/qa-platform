<template>
  <div style="width: 1050px; margin: 0 auto;margin-bottom: 20px;">
    <!-- 用户评论后弹出的评论样式 -->
    
    <div class="user">
      <div class="left">
        <el-avatar
          size="small"
          :src="comment.headPortraitToString"
        />
      </div>
      <div class="right">
        <div class="username">
          <span>{{comment.username}}</span>
          <span class="time" style="margin-right: 15px;">回答时间：{{comment.createTime.replace("T", " ")}}</span>
        </div>
        <div class="user-info" v-html="comment.content">
        </div>
        <div class="user-stats">
          
          <!-- <div class="consent">
            <span class="iconfont" ref="icon" style="font-size: 18px; color: blank"
              >&#xe626;</span
            ><span>474</span>
          </div>
          <div class="disagree">
            <span class="iconfont" ref="icon2" style="font-size: 18px; color: blank"
              >&#xe63e;</span
            >
          </div> -->
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
defineProps(["comment"]);
</script>

<style scoped>
.inputUser {
  display: flex;
  flex-direction: row;
}
.user {
  display: flex;
  flex-direction: row;
}
.right {
  margin-left: 10px;
  width: 87%;
  border-bottom: 1px solid rgb(132, 145, 165);
  padding-bottom: 8px;
}
.username {
  display: flex;
  margin-top: 4px;
  font-weight: bold;
  align-items: center;
}
.time{
  font-weight: normal;
  font-size: 12px;
  color: #787878;
  margin-left: 20px;
}
.user-info {
  display: flex;
  flex-direction: column;
  margin-top: 6px;
  width: 100%;
  /* background-color: paleturquoise; */
}

.user-stats {
  display: flex;
  flex-direction: row;
  margin-top: 6px;
}
.user-stats div {
  /* margin-right: 15px; */
  font-size: 14px;
}
.user-stats .consent {
  cursor: pointer;
}
.user-stats .disagree {
  cursor: pointer;
}

.user .left .el-avatar {
  width: 40px;
  height: 40px;
  border-radius: 50%;
  margin-left: 6px;
  border: 2px solid #fff;
  box-shadow: 0 0 4px rgba(0, 0, 0, 0.12);
  background-color: #f8f9fa;
  transition: all 0.3s ease;
}

</style>
