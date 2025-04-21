<template>
  <div style="background-color: rgb(244, 246, 249);height: 100%;">
    <div style="width: 100%; margin: 0 auto; background: rgb(245, 249, 255);">
      <Aheadline :problem="problem" @a="onShow"></Aheadline>
    </div>
    <!-- 富文本 -->

    <div id="sample" style="margin: 0 auto;padding-left: 80px;" v-show="isShow">
      <!-- <img alt="Vue logo" class="logo" src="./assets/logo.svg" width="125" height="125" /> -->
      <Editor
        v-model="tinyContent"
        api-key="nzn8ixegx380zflxo87z4a8tkqjatd46nti2mcb916datx2g"
        :init="tinyInit"
      />
      <!-- 固定发布栏 -->
      <div class="fixed-button">
        <span
          >发表该话题即表示您已阅读并接受<a href="#">《用户协议》</a>,请遵守该协议</span
        >
        <el-button type="primary" @click="handlePublishAnswer">发布回答</el-button>
      </div>
    </div>

    <div class="comment-container">
      <div style="border: 1px solid #ccc;border-radius: 10px;padding: 10px 8px;min-height: 400px;">
        <el-empty description="暂无评论~" v-show="commentEmpty"/>
        <Comment v-for="(comment,i) in comments" :key="i" :comment="comment"></Comment>
      </div>
    </div>
  </div>
</template>

<script setup>
import { useRoute } from "vue-router";
import { ref, onMounted, reactive } from "vue";
import Aheadline from "./Aheadline.vue";
import Comment from "./Comment.vue";
import Editor from "@tinymce/tinymce-vue";
import { getProblemById } from "@/api/api.problem";
import { checkProblemCollected } from "@/api/api.collection";
import { addComment, getComments } from "@/api/api.comment";
import { getUserById } from "@/api/api.user";
import authentication from "@/store/authentication";
import { checkLikeStatus, getLikeCount } from "@/api/api.likes";

const auth = authentication()

const isShow = ref(false);

const route = useRoute();

const problem = reactive({})

const comments = ref([])

const commentEmpty = ref(false)

const onShow = function (data) {
  isShow.value = data;
};

onMounted( async () => {
  await getProblemById(route.params.questionId)
    .then((res) => {
      const {data} = res
      Object.assign(problem,data)
      checkProblemCollected(auth.id,problem.id).then(res => {
        problem.collectStatus = res // 用户收藏问题的状态。 true is collected
      })
      checkLikeStatus(auth.id,problem.id).then(res => {
        problem.likeStatus = res
      })
      getLikeCount(problem.id).then(res => {
        problem.lc = res.data
      })
      getCommentsByProblemId(problem.id)
    })
    .catch((error) => console.log("获取问题失败！"));
});

const handlePublishAnswer = async () => {
  if(tinyContent.value.trim() == '') return ;
  await addComment({
    content:tinyContent.value,
    problemId:problem.id,
    userId:auth.id
  }).then(res=>{
    isShow.value = false
    tinyContent.value = ""
    ElMessage.success(res.message)
  })
  await getCommentsByProblemId(problem.id)
}

const getCommentsByProblemId = (pi)=>{
  getComments(pi).then(res => {
    const {data} = res
    comments.value = data
    if(comments.value.length <= 0){
      commentEmpty.value = true;
      return ;
    }
    commentEmpty.value = false;
    comments.value.forEach(c => {
      getUserById(c.userId).then(res => {
        const {data} = res
        c.username = data.username
        c.headPortraitToString = data.headPortraitToString
      })
    })
  })
}

// #region tinymce
const tinyContent = ref('');

const tinymcePlugins =
  "codesample link image lists advlist help emoticons table preview fullscreen"; // powerpaste

const tinymceToolbar = [
  `undo redo | styles formatselect fontselect fontsizeselect | bold italic underline strikethrough subscript superscript removeformat blockquote | outdent indent emoticons`,
  `codesample link image preview fullscreen| alignleft aligncenter alignright alignjustify | bullist numlist table`,
];

const tinyInit = {
  language_url: "/public/tinymce/langs/zh-CN.js", // 指定语言包文件。官网下的语言包：https://www.tiny.cloud/get-tiny/language-packages/
  language: "zh-Hans", //设置语种。 tinymce.addI18n("zh-Hans",{...})
  height: 350,// 设置整个Tinymce这个组件的高度
  width: 1050, // 设置整个Tinymce这个组件的宽度
  plugins: tinymcePlugins, // 插件设置
  menubar: false, // "file edit view insert format tools table commonMenu help", // 菜单栏
  menu: {
    tools: {
      title: "Tools",
      items: "spellchecker spellcheckerlanguage | a11ycheck code wordcount",
    },
    commonMenu: {
      // 自定义菜单栏
      title: "常用", // 菜单标题
      items: "selectall image link hr", // 自定义菜单项
    },
  },
  toolbar: tinymceToolbar, // 工具栏
  // images_upload_url:'', // 指定一个接受上传文件的后端处理程序地址。自定义上传图片不需要这个。
  automatic_uploads: false,
  paste_data_images: true, // 开启 粘贴图像，并会自动调用 images_upload_handler 钩子。可实现粘贴图片后自动上传图片操作
  images_upload_handler: function (blobInfo, successCallback, failureCallback) {
    // 自定义函数代替TinyMCE来处理上传图片操作.(只要涉及上传图片就会触发方法)
    const file = blobInfo.blob(); // 上传的文件对象
    console.log(file);
  },
  images_reuse_filename: true, // TinyMCE默认会给每个上传的文件生成唯一的文件名。将该配置设为true则可以避免此问题，它将告诉TinyMCE使用图片文件实际的文件名，而不是每次随即生成一个新的。
  file_picker_types: "image", // 设置 文件选择器 的文件上传类型。允许的值包括：file,image和media
  // file_picker_callback: function(callback, value, meta) {} // 文件选择器回调函数
  browser_spellcheck: true, // 拼写检查
  branding: false, // 去水印
  elementpath: false, //禁用编辑器底部的状态栏
  smart_paste: true, // 开启智能粘贴
  statusbar: false, // 隐藏状态栏
  // setup: function (editor) {
  //   editor.on("change", function (e) {
  //     // 为editor搞个change事件，当编辑器内容发生变动调用此方法。当然也可以这样：@change=""
  //     saveEditorContent(e);
  //   });
  // },
};
// #endregion
</script>

<style scoped>
.fixed-button {
  display: flex;
  flex-direction: row;
  width: 1050px;
  height: 55px;
  /* border-radius: 6px; */
  background-color: rgb(250, 251, 252);
  justify-content: space-between;
  /* position: fixed; */
  border-bottom-left-radius: 10px;
  border-bottom-right-radius: 10px;
}
.fixed-button span {
  display: flex;
  align-items: center;
  margin-left: 20px;
  color: rgb(132, 145, 165);
}
.fixed-button .el-button {
  display: flex;
  margin-right: 20px;
  margin-top: 10px;
  width: 85px;
  height: 35px;
  font-size: 14px;
}
.logo {
  display: block;
  margin: 0 auto 2rem;
}
.comment-container{
  width: 1050px;
  min-height: 480px;
  margin: 0 auto;
  /* margin-top: 10px; */
  padding-left: 80px;
}

@media (min-width: 1024px) {
  #sample {
    display: flex;
    flex-direction: column;
    place-items: center;
    width: 1000px;
  }
}
:deep(.tox-tinymce) {
  border: none;
  border-radius: 0px!important;
  border-bottom: 1px solid #ccc;
  border-top-left-radius: 10px!important;
  border-top-right-radius: 10px!important;
}

</style>
