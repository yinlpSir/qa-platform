<template>
  <div class="question-form-container">
    <el-form
      ref="publishProblemFormRef"
      style="max-width: 600px"
      :model="problemFormData"
      :rules="publishProblemRules"
      label-width="auto"
      status-icon
    >
      <el-form-item label="问题封面">
        <el-upload
          class="avatar-uploader"
          :show-file-list="false"
          :on-success="handleAvatarSuccess"
          :auto-upload="false"
          :on-change="handleFileChange"
        >
          <img v-if="coverUrl" :src="coverUrl" class="avatar" />
          <el-icon v-else class="avatar-uploader-icon"><Plus /></el-icon>
        </el-upload>
      </el-form-item>
      <el-form-item label="问题标题" prop="title">
        <el-input v-model="problemFormData.title" placeholder="请输入您的问题" clearable />
      </el-form-item>
      <el-form-item label="问题正文">
        <el-input type="textarea" v-model="problemFormData.content" placeholder="请输入您对问题的一些补充" rows="4"/>
      </el-form-item>
    </el-form>
    <div style="display: flex; justify-content: center;margin-top: 30px;">
      <el-button @click="ps.publishProblemVisible = false">取消</el-button>
      <el-button type="primary" @click="handlePublishProblem(publishProblemFormRef)">
        立即创建
      </el-button>
    </div>
  </div>
</template>

<script setup>
import { reactive, ref } from "vue";
import { Plus } from "@element-plus/icons-vue";
import problemStore from "@/store/problem.store";
import { create } from "@/api/api.problem";
import { ElNotification } from "element-plus";

const ps = problemStore()

const publishProblemFormRef = ref(null);

const publishProblemRules = reactive({
  title: [
    { required: true, message: "请输入您的问题", trigger: ['blur'] },
    {min:3,max:40, message:"问题长度不符",trigger:['blur','change'] }
  ],
});

const problemFormData = reactive({
  title: "",
  cover: "",
  content: "",
});

const coverUrl = ref("");

const handleFileChange = (uploadFile, uploadFiles) => {
  problemFormData.cover = uploadFile.raw;
  coverUrl.value = URL.createObjectURL(uploadFile.raw);
};

const handlePublishProblem = (formEl) => {
  if (!formEl) return
  formEl.validate((valid, fields) => {
    if (valid) {
      create({
        cover:problemFormData.cover,
        title:problemFormData.title,
        content:problemFormData.content
      }).then(res => {
        ElNotification({
          title:"Success",
          message:"创建成功",
          type:"success",
          position:"top-left"
        })
        ps.publishProblemVisible = false
        coverUrl.value = ""
        problemFormData.cover = ""
        problemFormData.title = ""
        problemFormData.content = ""
      })
    }
  })
}
</script>

<style scoped>
.question-form-container {
  display: flex;
  flex-direction: column;
  margin: 0 auto;
  padding: 20px;
  margin: 0 20px;
  margin-top: 10px;
  border-radius: 8px;
}
/* 问题标题 */
.wtTitle {
  display: flex;
  flex-direction: row;
}
.wtTitle h4 {
  flex: 1;
}
.wtTitle .el-input {
  width: 500px;
}

.form-section {
  display: flex;
  flex-direction: column;
}
/* 问题封面 */
.question-fm {
  display: flex;
  flex-direction: row;
}
.question-fm h4 {
  flex: 1;
}
.question-cover {
  margin-bottom: 10px;
  margin-top: 10px;
  display: flex;
  /* justify-content: flex-start; */
  margin-right: 250px;
  /* justify-content: flex-start; */
  background-color: palegoldenrod;
  /* padding: 10px; */
  border: 1px dashed #ccc; /* 使用虚线边框表示这里是放置图片的位置 */
  /* 如果放置文本，则居中对齐 */
}
.question-cover .el-upload {
  background-color: palevioletred;
}
.question-cover img {
  max-width: 100%;
  height: auto;
  border-radius: 4px;
}
/* 问题正文 */
.question-zw {
  display: flex;
  flex-direction: row;
  margin-bottom: 20px;
}
.question-zw h4 {
  width: 100px;
}
.question-body {
  width: 100%;
  padding: 10px;
  margin-bottom: 20px;
  border: 1px solid #ccc;
  border-radius: 4px;
  resize: vertical;
  font-size: 16px;
}

.form-buttons {
  display: flex;
  justify-content: space-between;
  margin-top: 20px;
}

.cancel-button,
.submit-button {
  padding: 10px 20px;
  font-size: 16px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

.cancel-button {
  background-color: #fff;
  border: 1px solid #ccc;
  color: #333;
}

.cancel-button:hover {
  background-color: #f0f0f0;
}

.submit-button {
  background-color: #007bff;
  color: #fff;
}

.submit-button:hover {
  background-color: #0056b3;
}
/* 图片上传 */

/* img */
.avatar-uploader .avatar {
  width: 340px!important;
  height: 200px;
  display: block;
}
.fm {
  width: 200px;
  height: 100px;
}
.avatar-uploader {
  width: 340px!important;
  height: 200px;
}
:deep(.avatar-uploader .el-upload){
  border: 1px dashed var(--el-border-color);
  border-radius: 6px;
  cursor: pointer;
  width: 340px;
  position: relative;
  overflow: hidden;
  transition: var(--el-transition-duration-fast);
}
</style>
<style>

.avatar-uploader .el-upload:hover {
  border-color: var(--el-color-primary);
}

.el-icon.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 178px;
  text-align: center;
}
</style>
