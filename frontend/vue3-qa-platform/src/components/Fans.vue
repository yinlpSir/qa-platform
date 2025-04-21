<template>
    <div class="user">
        <div class="left">
            <el-avatar :size="50" :src="user.headPortrait" />
            <div style="margin-left: 8px;">
                <h4 style="display: flex; align-items: center;">
                    <span>{{user.username }}</span>
                    <el-icon v-if="user.gender == '男'" style="color: blue">
                        <Male />
                    </el-icon>
                    <el-icon v-else style="color: pink">
                        <Female />
                    </el-icon>
                </h4>
                <span class="introduce">{{user.introduce}}</span>
            </div>
        </div>
        <div class="right">
            <el-button v-if="user.followStatus" style="width: 70px;" size="small" round @click="handleFollowedClick(user)">已关注</el-button>
            <el-button v-else type="primary" style="width: 70px;" size="small" round @click="handleFollowClick(user)">关注</el-button>
        </div>
    </div>
</template>

<script setup>
import { Male, Female } from "@element-plus/icons-vue";
import { follow,unFollow } from "@/api/api.follow";
import { ElMessage } from "element-plus";
import authentication from "@/store/authentication";
import userStore from "@/store/user.store";

const auth = authentication()

const us = userStore()

defineProps(["user"])

/**
 * 取消关注
 * @param auth.id 关注者Id
 * @param user.id 被关注者Id
 */
const handleFollowedClick = (user) => {
    unFollow(auth.id,user.id).then(res => {
        ElMessage.success(res.message)
        user.followStatus = false
    })
}

/**
 * 关注
 * @param auth.id 关注者Id
 * @param user.id 被关注者Id
 */
const handleFollowClick = (user) => {
    follow(auth.id,user.id).then(res => {
        ElMessage.success(res.message)
        user.followStatus = true
    })
}
</script>

<style scoped>
    .user{
        display: flex;
        justify-content: space-between;
        align-items: center;
        margin-bottom: 16px;
    }
    .user .left{
        display: flex;
        align-items: center;
    }
    .introduce{
        font-size: 12px;
    }
</style>