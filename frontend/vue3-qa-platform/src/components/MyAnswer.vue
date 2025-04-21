<template>
    <div style="
        margin-bottom: 24px;
        border-top: 1px solid rgb(244, 246, 249);
    ">
        <div class="questionTitle">
            <h4 @click="toAnswers(problem.problemId)">{{ problem.problemName }}</h4>
        </div>
        <!-- 问题 -->
        <div class="questionContent">
            <!-- <img
                v-if="problem.coverToString == null ? false : true"
                :src="problem.coverToString"
                alt="问题封面"
                style="margin-right: 10px; cursor: pointer"
                @click="toAnswers(problem.id)"
            /> -->
            <div class="mainContent">
                <div class="userImg">
                    <el-avatar :src="problem.headPortrait" />
                    <div class="personalContent">
                    <span class="uname">{{ problem.username }}</span>
                    <el-icon v-if="problem.gender == '男'" style="color: blue">
                        <Male />
                    </el-icon>
                    <el-icon v-else style="color: pink">
                        <Female />
                    </el-icon>
                    <span class="time"
                    >回答于 {{ problem.createTime.replace("T", " ") }}</span
                    >
                    <el-popconfirm title="确定要删除该回答吗?" @confirm="handleConfirmClick(problem.id)">
                        <template #reference>
                            <el-button link type="warning" size="small" style="margin-left: 10px;">删除回答</el-button>
                        </template>
                    </el-popconfirm>
                    
                </div>
                </div>
                <div class="question-con" v-html="problem.content" style="display: flex;flex-direction: column; width: 100%; margin-top: 6px; margin-bottom: 6px">
                </div>
            </div>
        </div>
    </div>
</template>

<script setup>
import { delComment } from "@/api/api.comment";
import { ChatLineSquare, Iphone, Plus,Male, Female  } from "@element-plus/icons-vue";
import { onBeforeMount } from "vue";
import { useRouter } from "vue-router";

defineProps(["problem"]);

const router = useRouter();

const toAnswers = (pi) => {
    router.push({
        name: "answers",
        params: {
            questionId: pi,
        },
    });
};

const handleConfirmClick = (commentId)=> {
    handleDelComment(commentId)
}

const handleDelComment = (commentId) => {
    delComment(commentId).then(res => {
        ElMessage.success(res.message)
    })
}
</script>

<style scoped>
/* 问题标题 */
.questionTitle {
    margin: 6px 0;
    cursor: pointer;
}

.questionTitle:hover {
    color: rgb(82, 168, 236);
}

.questionTitle h4 {}

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

.questionContent .mainContent .userImg {
    display: flex;
    flex-direction: row;
}

.questionContent .mainContent .userImg .el-avatar {
    display: flex;
    flex-direction: row;
}

.questionContent .mainContent .userImg span {
    display: flex;
    flex-direction: row;
}

.content {
    cursor: pointer;
    display: -webkit-box;
    margin-bottom: 6px;
    -webkit-box-orient: vertical;
    -webkit-line-clamp: 4;
    overflow: hidden;
}

.content:hover {
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

.del-problem {
    display: flex;
    align-items: flex-end;
    margin-left: 3px;
    margin-right: 15px;
    color: orange;
    cursor: pointer;
}

.personalContent {
    display: flex;
    align-items: center;
    margin-left: 10px;
}
.personalContent .uname {
    font-size: 14px;
    font-weight: bold;
}
.personalContent .time {
    color: rgb(143, 136, 136);
    font-size: 12px;
    margin-left: 10px;
}
.personalContent .el-icon {
    color: rgb(143, 136, 136);
    font-size: 14px;
    margin-left: 4px;
}
</style>