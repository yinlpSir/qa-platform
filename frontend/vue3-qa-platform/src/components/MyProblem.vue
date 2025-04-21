<template>
    <div style="margin-bottom: 24px;">
        <div class="questionTitle">
            <h4 @click="toAnswers(problem.id)">{{ problem.title }}</h4>
        </div>
        <!-- 问题 -->
        <div class="questionContent">
            <img v-if="problem.coverToString == null ? false : true" :src="problem.coverToString"
                alt="问题封面" style="margin-right: 10px; cursor: pointer;" @click="toAnswers(problem.id)" />
            <div class="mainContent">
                <div class="content" @click="toAnswers(problem.id)">
                    {{ problem.content }}
                </div>
                <div class="threeIcon">
                    <span class="share">
                        <img src="/src/assets/icon/like.png" alt="点赞" style="width: 18px; height: 18px" />
                        <span id="shareIcon">
                            {{ problem.likeCount > 100 ? "99+" : problem.likeCount }}
                            个赞
                        </span>
                    </span>

                    <span class="comments">
                        <el-icon>
                            <ChatLineSquare />
                        </el-icon>
                        <span id="commentsIcon">
                            {{ problem.commentsCount > 100 ? "99+" : problem.commentsCount }}
                            回答
                        </span>
                    </span>
                    
                    <span style="display: flex;align-items:flex-end;margin-left: 3px;margin-right: 15px;">
                        {{problem.pageView}} 浏览
                    </span>

                    
                    <el-popconfirm title="确定要删除这个问题吗?" @confirm="handleConfirmClick(problem.id)">
                        <template #reference>
                            <el-button link type="warning" size="large" style="font-size: 16px;margin-top: 3px;margin-right: 15px;" >点击删除</el-button>
                        </template>
                    </el-popconfirm>

                    <span style="color: rgb(143, 136, 136);display: flex;align-items:flex-end;">
                        创建时间：{{problem.createTime.replace("T"," ")}}
                    </span>
                </div>
            </div>
        </div>
    </div>
</template>

<script setup>
import { delProblem } from "@/api/api.problem";
import { ChatLineSquare,Iphone,Plus } from "@element-plus/icons-vue";
import { onBeforeMount } from "vue";
import { useRouter } from 'vue-router';

defineProps(["problem"]);

const router = useRouter()

const toAnswers = (pi) => {
    router.push({
        name: "answers",
        params: {
            questionId: pi,
        },
    });
};

const handleConfirmClick = (id)=> {
    handleDelProblem(id)
}

const handleDelProblem = (id) => {
    delProblem(id).then(res => {
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

</style>