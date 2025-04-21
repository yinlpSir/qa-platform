<template>
  <!-- <div>这里是问答区！</div> -->
  <div class="contentArea">
    <div class="advertisement">
      广告位
      <Advertisement />
    </div>
    <!-- 问答区域 -->
    <div class="questionArea">
      <nav class="questionNav">
        <ul>
          <li>热门</li>
          <li>关注</li>
          <li>推荐</li>
        </ul>
      </nav>
      <!-- 一个问答列表 -->
      <div id="questionList" class="questionList" >
        <!--question list-->
        <Question/>
        <!--
        <div>
          <div class="userIfor">
          <img src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png" alt="">
          <div class="personalContent">
            <span class="uname">晴天</span>
            <el-icon>
              <Female />
            </el-icon>
            <span class="time">发布于 2022-03-24 15:20:30</span>
          </div>
        </div>
        <div class="questionTitle">
          <h4>为什么银行不建议买金条?</h4>
        </div>
        <div class="questionContent">
          <div class="questionImg">
            <img src="../assets/imgs/f4eab0d393e10224b16bd5b154b83528.jpeg" alt="">
          </div>
          <div class="mainContent">
            <div class="title">
              <p>这里是问答内容。。。这里是问答内容。。。
                这里是问答内容。。。这里是问答内容。。。这里是问答内容。。。
                这里是问答内容。。。这里是问答内容。。。这里是问答内容。。。
                这里是问答内容。。。这里是问答内容。。。这里是问答内容。。。
              </p>
            </div>
            <div class="threeIcon">
              <span class="share">
                <el-icon>
                  <Promotion />
                </el-icon>
                <span id="shareIcon">分享</span>

              </span>
              <span class="comments">
                <el-icon>
                  <ChatLineSquare /> 
                </el-icon>
                <p id="commentsIcon">评论</p>
              </span>
              <span class="collection">
                <el-icon>
                  <StarFilled />
                </el-icon>
                <p id="collectionIcon">收藏</p>
              </span>

            </div>
          </div>
        </div>
        </div>
        -->
      </div>
      
      <!-- 第二个 -->
      
      你问我答
      <!-- <Question /> -->
    </div>
    <!-- 创作中心 -->
    <div class="asideList">


      <!-- 1 -->
      <div class="createCenter" >
        <div class="users">
          <div class="createUser">
            <el-icon><Edit /></el-icon>
            <h5>创作中心</h5>
          </div>
          <div class="nav_user">
            <div class="q1">
              <img src="" alt="">
              <p>回答问题</p>
            </div>
            <div class="q2">
              <img src="" alt="">
              <p>发布问题</p>
            </div>
          </div>
          <!-- 这里放一个随机图片广告 -->
          <div class="random_advertising">
            <img src="" alt="">随机广告
          </div>
          <!-- 去创作吧 -->
          <div class="go_create">
            <!-- 图标 -->
            <el-icon><Plus /></el-icon>
            <!-- 文字 -->
            <span>开始创作</span>
          </div>
        </div>
      </div>
      <!-- 2 -->
      <div class="suggested_follows">
        <div class="follows">
          <div class="followsUser">
            <el-icon><UserFilled /></el-icon>
            <h5>推荐关注</h5>
          </div>
          <div class="followsUserLists">
            <div class="u1">
              <div class="avatar1">
                <img src="" alt="">
              </div>
              <!-- 名字 -->
              <span class="followsUname">张小梅</span>
              <!-- 个人签名 -->
              <p>你好，我叫张小梅</p>
              <!-- +关注 -->
              <div class="addFollows">
                <el-icon><Plus /></el-icon>
                <span>关注</span>
              </div>
        
            </div>
            <div class="u2">
              <div class="avatar2">
                <img src="" alt="">
              </div>
              <!-- 名字 -->
              <span class="followsUname">吃个栗子</span>
              <!-- 个人签名 -->
              <p>美食问题找栗子</p>
              <!-- +关注 -->
              <div class="addFollows">
                <el-icon><Plus /></el-icon>
                <span>关注</span>
              </div>
            </div>
            <div class="u3">
              <div class="avatar3">
                <img src="" alt="">
              </div>
              <!-- 名字 -->
              <span class="followsUname">徐笑笑</span>
              <!-- 个人签名 -->
              <p>笑着健身</p>
              <!-- +关注 -->
              <div class="addFollows">
                <el-icon><Plus /></el-icon>
                <span>关注</span>
              </div>
            </div>
          </div>
          <div class="followsRefresh">
            <el-icon><Refresh /></el-icon>
            <span>换一换</span>
          </div>

        </div>
      </div>
      <!-- <div class="b3"></div> -->
      <CreationCenter />
    </div> 
  </div>

</template>

<script setup>
import { ref } from 'vue'
import { Female, Promotion, ChatLineSquare, StarFilled,UserFilled,Plus,Edit,Refresh} from '@element-plus/icons-vue'

import Question from './Question.vue';

const questions = [
  {
    username:"小明",

  },
]
//获取问答盒子,剩余距离不够时,会循环复制第一个问答内容
/*
const box = document.getElementById('questionList')
const html=document.documentElement

console.log(box+'成功获得了');
document.addEventListener("scroll",()=>{
            // console.log(html.getBoundingClientRect().bottom)
            // console.log(html.clientHeight)
            if(html.getBoundingClientRect().bottom - html.clientHeight < 5){
                console.log("到底了")
                let h1=document.createElement("h1")
                h1.innerHTML = new Date()
                box.appendChild(h1)
            }
        })

*/
</script>

<style scoped>



.contentArea{
  margin: 0 auto;
  position: relative;
  background-color: lightblue;
  height: 500px;
  width: 1100px;
}
.contentArea .advertisement{
  margin: 0 auto;
  background-color: palegreen;
  height: 65px;
  width: 1100px;
  }
.contentArea .questionArea{
    background: pink;
    position:absolute;
    left: 0;
    /* padding-top: 65px; */
    width: 75%;
    border-radius: 8px;
    /* height: 100%; */
  }
.contentArea .questionArea .questionNav{
  /* position: absolute;
    left: 0; */
    border-bottom: 1px solid #000;
  /* background-color: rgb(66, 85, 210); */
  width: 100%;
}
.contentArea .questionArea .questionNav ul{
  list-style: none;
  /* background-color: purple; */
  /* margin-left: 0; */
}
.contentArea .questionArea .questionNav ul li:first-child{
  /* background: rgb(155, 140, 240); */
  margin-left: -18px;
}
.contentArea .questionArea .questionNav ul li{
  display: inline-block;
  /* background: palegoldenrod; */
  /* position: absolute;
  left: 0; */
  
  margin-right: 35px;
}
/* 一个问答列表 */
.contentArea .questionArea .questionList{
  background-color: rgb(115, 228, 218);
  position: relative;
  height: 228px;
}
.contentArea .questionArea .questionList .userIfor{
  background-color: rgb(13, 99, 220);
  position: relative;
}
.contentArea .questionArea .questionList .userIfor img{
  /* background-color: rgb(158, 38, 239); */
  
  width: 35px;
  border-radius: 30px;
  margin-left: 20px;
  margin-top: 13px;
  margin-bottom: 5px;
}
.contentArea .questionArea .questionList .userIfor .personalContent{
  background: paleturquoise;
  position: absolute;
  left: 68px;
  top: 20px;
}
.contentArea .questionArea .questionList .userIfor .personalContent .uname{
  background-color: plum;
  font-size: 14px;
  font-weight: bold;
}
.contentArea .questionArea .questionList .userIfor .personalContent .time {
  /* background-color: rgb(137, 16, 137); */
  color: rgb(143,136,136);
  font-size: 12px;
  margin-left: 10px;
}
.contentArea .questionArea .questionList .userIfor .personalContent .el-icon{
  color: rgb(143, 136, 136);
  font-size: 14px;
  margin-left: 4px;
}
/* 问题标题 */
.contentArea .questionArea .questionList .questionTitle{
  background-color: lightcoral;
  position: relative;
}
.contentArea .questionArea .questionList .questionTitle h4{
  background-color: palegoldenrod;
  margin-left: 20px;
}
/* 问题内容 */
.contentArea .questionArea .questionList .questionContent{
  background-color: fuchsia;
  height: 130px;
  width: 100%;
  margin-top: 8px;
  position: relative;
}
.contentArea .questionArea .questionList .questionContent .questionImg{
  background-color: red;
  width: 30%;
  height: 130px;
  margin-left: 20px;
  /* margin-top: 10px; */
  border-radius: 5px;
  position: absolute;
  
  /* left: 10px; */
}
.contentArea .questionArea .questionList .questionContent .questionImg img{
  width: 100%;
  height: 100%;
}
.contentArea .questionArea .questionList .questionContent .mainContent{
  background-color: plum;
  position: absolute;
  /* left: 220px; */
  height: 130px;
  width: 60%;
  margin-left: 250px;
  /* margin-top: 10px; */
}
.contentArea .questionArea .questionList .questionContent .mainContent .title{
  background-color: blue;
  height: 75%;
  position: absolute;
}
.contentArea .questionArea .questionList .questionContent .mainContent .title p{
  /* line-height: 60px; */
  /* height: 180px; */
  background-color: red;
  position:relative;
  top: -16%;
  display: -webkit-box;
  -webkit-box-orient: vertical;
  -webkit-line-clamp: 4;
  overflow: hidden;
}
/* 图标 */
.contentArea .questionArea .questionList .questionContent .mainContent .threeIcon {
  background-color: rgb(125, 125, 248);
  height: 25%;
  position: relative;
  top: 75%;

}
.contentArea .questionArea .questionList .questionContent .mainContent .threeIcon .share{
  background-color: rgb(255, 176, 189);
  position: absolute;
  top: 7px;
}
.contentArea .questionArea .questionList .questionContent .mainContent .threeIcon .share #shareIcon {
  background-color: rgb(237, 36, 70);
  position: absolute;
  width: 40px;
  margin-left: 8px;
  font-size: 14px;
  top: 2px;
}
.contentArea .questionArea .questionList .questionContent .mainContent .threeIcon .comments {
  background-color: rgb(255, 176, 189);
  position: absolute;
  left: 80px;
  top: 7px;
}
.contentArea .questionArea .questionList .questionContent .mainContent .threeIcon .comments #commentsIcon {
  background-color: rgb(237, 36, 70);
  position: absolute;
  top: -12px;
  left: 25px;
  width: 40px;
  font-size: 14px;
}
.contentArea .questionArea .questionList .questionContent .mainContent .threeIcon .collection{
  background-color: rgb(255, 176, 189);
  position: absolute;
  left: 160px;
  top: 7px;
}
.contentArea .questionArea .questionList .questionContent .mainContent .threeIcon .collection #collectionIcon {
  background-color: rgb(237, 36, 70);
  position: absolute;
  top: -12px;
  left: 25px;
  width: 40px;
  font-size: 14px;
}
/* 边导航 */
.contentArea .asideList {
  background: palevioletred;
  position:absolute;
  right: 0;
  /* padding-top: 65px; */
  width: 25%;
  /* height: 100%; */
}
.contentArea .asideList .createCenter{
  background-color: gold;
  border-radius: 3px;
  width: 100%;
  height: 300px;
  position: relative;
  margin-bottom: 10px;
}
.contentArea .asideList .createCenter .users{
  background-color: powderblue;
  width: 95%;
  height: 100%;
  position: relative;
  left: 7px;
}
/* 创作中心的logo */
.contentArea .asideList .createCenter .users .createUser{
  background-color: rgb(198, 166, 198);
  width: 240px;
  height: 50px;
  margin-left: 10px;
}
.contentArea .asideList .createCenter .users .createUser .el-icon{
  color: rgb(55,58,64);
  font-size: 20px;
  margin-top: 15px;
  margin-left: 10px;
}
.contentArea .asideList .createCenter .users .createUser h5{
  font-size: 15px;
  font-weight: 400;
  position: absolute;
  top: 15px;
  left: 45px;
}
/* 小导航user */
.contentArea .asideList .createCenter .users .nav_user{
  background: peru;
  width: 240px;
  height: 95px;
  position: relative;

  left: 10px;
  
}
.contentArea .asideList .createCenter .users .nav_user .q1{
  background-color: palegreen;
  position: relative;
  width: 120px;
  height: 95px;
}
.contentArea .asideList .createCenter .users .nav_user .q1 img{
  width: 45px;
  height: 45px;
  border-radius: 50%;
  background-color: palegoldenrod;
  position: absolute;
  top: 15px;
  left: 35px;
}
.contentArea .asideList .createCenter .users .nav_user .q1 p{
  font-size: 14px;
  position: absolute;
  top: 50px;
  left: 30px;
}

.contentArea .asideList .createCenter .users .nav_user .q2{
  background-color: rgb(25, 112, 25);
  position: relative;
  top: -95px;
  left: 120px;
  width: 120px;
  height: 95px;
}
.contentArea .asideList .createCenter .users .nav_user .q2 img{
  width: 45px;
  height: 45px;
  border-radius: 50%;
  background-color: palegoldenrod;
  position: absolute;
  top: 15px;
  left: 40px;
}
.contentArea .asideList .createCenter .users .nav_user .q2 p{
  font-size: 14px;
  position: absolute;
  top: 50px;
  left: 30px;
}
/* 随机图片广告 */
.contentArea .asideList .createCenter .users .random_advertising{
  background: rgb(225, 213, 83);
  width: 240px;
  height: 80px;
  position: relative;
  left: 10px;
}
/* 去创作吧 */
.contentArea .asideList .createCenter .users .go_create{
  background: rgb(161, 155, 92);
  width: 240px;
  height: 50px;
  position: relative;
  left: 10px;
  top: 10px;
}
.contentArea .asideList .createCenter .users .go_create .el-icon{
  color: white;
  font-size: 20px;
  position: absolute;
  left: 75px;
  top: 15px;
}
.contentArea .asideList .createCenter .users .go_create span{
  font-size: 16px;
  color: white;
  position: absolute;
  left: 100px;
  top: 15px;
}




/* 
.contentArea .asideList .createCenter .users .nav_user .q1{
  background-color: rgb(198, 166, 198);
  width: 100%;
  height: 50px;
  position: relative;
  margin-bottom: 10px;
} */
.contentArea .asideList .suggested_follows{
  background-color: gold;
  border-radius: 3px;
  width: 100%;
  height: 300px;
  position: relative;
  margin-bottom: 10px;
}
.contentArea .asideList .suggested_follows .follows{
  background-color: powderblue;
  width: 95%;
  height: 100%;
  position: relative;
  left: 7px;
}

.contentArea .asideList .suggested_follows .follows .followsUser{
  background-color: rgb(198, 166, 198);
  width: 240px;
  height: 50px;
  margin-left: 10px;
  
}
.contentArea .asideList .suggested_follows .follows .followsUser .el-icon{
  color: rgb(55,58,64);
  font-size: 20px;
  margin-top: 15px;
  margin-left: 10px;
}
.contentArea .asideList .suggested_follows .follows .followsUser h5{
  font-size: 15px;
  font-weight: 400;
  position: absolute;
  top: 15px;
  left: 45px;
}
.contentArea .asideList .suggested_follows .follows .followsUserLists{
  background-color: rgb(219, 57, 111);
  width: 240px;
  height: 200px;
  margin-left: 10px;
}
.contentArea .asideList .suggested_follows .follows .followsUserLists .u1{
  background-color: palegreen;
  width: 100%;
  height: 50px;
  position: relative;
  top: 15px;
  
}
.contentArea .asideList .suggested_follows .follows .followsUserLists .u1 .avatar1{
  background-color: rgb(244, 143, 244);
  position: absolute;
  width: 50px;
  height: 50px;
}
.contentArea .asideList .suggested_follows .follows .followsUserLists .u1 .avatar1 img{
  width: 100%;
  height: 100%;
}
.contentArea .asideList .suggested_follows .follows .followsUserLists .u1 .followsUname{
  font-size: 14px;
  position: absolute;
  top: 5px;
  left: 55px;
}
.contentArea .asideList .suggested_follows .follows .followsUserLists .u1 p{
  font-size: 12px;
  color: rgb(132, 130, 130);
  position: absolute;
  top: 15px;
  left: 55px;
}
.contentArea .asideList .suggested_follows .follows .followsUserLists .u1 .addFollows{
  background-color: rgb(244, 143, 244);
  width: 80px;
  height: 50px;
  position: absolute;
  right: 0px;
}
.contentArea .asideList .suggested_follows .follows .followsUserLists .u1 .addFollows .el-icon{
  /* color: white; */
  font-size: 18px;
  position: absolute;
  top: 15px;
  right: 40px;
}
.contentArea .asideList .suggested_follows .follows .followsUserLists .u1 .addFollows span{
  font-size: 14px;
  position: absolute;
  top: 15px;
  right: 10px;
}




.contentArea .asideList .suggested_follows .follows .followsUserLists .u2{
  background-color: rgb(79, 174, 79);
  width: 100%;
  height: 50px;
  position: relative;
  top: 25px;
 
}
.contentArea .asideList .suggested_follows .follows .followsUserLists .u2 .avatar2{
  background-color: rgb(244, 143, 244);
  position: absolute;
  width: 50px;
  height: 50px;
}
.contentArea .asideList .suggested_follows .follows .followsUserLists .u2 .avatar2 img{
  width: 100%;
  height: 100%;
}
.contentArea .asideList .suggested_follows .follows .followsUserLists .u2 .followsUname{
  font-size: 14px;
  position: absolute;
  top: 5px;
  left: 55px;
}
.contentArea .asideList .suggested_follows .follows .followsUserLists .u2 p{
  font-size: 12px;
  color: rgb(132, 130, 130);
  position: absolute;
  top: 15px;
  left: 55px;
}
.contentArea .asideList .suggested_follows .follows .followsUserLists .u2 .addFollows{
  background-color: rgb(244, 143, 244);
  width: 80px;
  height: 50px;
  position: absolute;
  right: 0px;
}
.contentArea .asideList .suggested_follows .follows .followsUserLists .u2 .addFollows .el-icon{
  /* color: white; */
  font-size: 18px;
  position: absolute;
  top: 15px;
  right: 40px;
}
.contentArea .asideList .suggested_follows .follows .followsUserLists .u2 .addFollows span{
  font-size: 14px;
  position: absolute;
  top: 15px;
  right: 10px;
}




.contentArea .asideList .suggested_follows .follows .followsUserLists .u3{
  background-color: palegreen;
  width: 100%;
  height: 50px;
  position: relative;
  top: 35px;
  
}
.contentArea .asideList .suggested_follows .follows .followsUserLists .u3 .avatar3{
  background-color: rgb(244, 143, 244);
  position: absolute;
  width: 50px;
  height: 50px;
}
.contentArea .asideList .suggested_follows .follows .followsUserLists .u3 .avatar3 img{
  width: 100%;
  height: 100%;
}
.contentArea .asideList .suggested_follows .follows .followsUserLists .u3 .followsUname{
  font-size: 14px;
  position: absolute;
  top: 5px;
  left: 55px;
}
.contentArea .asideList .suggested_follows .follows .followsUserLists .u3 p{
  font-size: 12px;
  color: rgb(132, 130, 130);
  position: absolute;
  top: 15px;
  left: 55px;
}
.contentArea .asideList .suggested_follows .follows .followsUserLists .u3 .addFollows{
  background-color: rgb(244, 143, 244);
  width: 80px;
  height: 50px;
  position: absolute;
  right: 0px;
}
.contentArea .asideList .suggested_follows .follows .followsUserLists .u3 .addFollows .el-icon{
  /* color: white; */
  font-size: 18px;
  position: absolute;
  top: 15px;
  right: 40px;
}
.contentArea .asideList .suggested_follows .follows .followsUserLists .u3 .addFollows span{
  font-size: 14px;
  position: absolute;
  top: 15px;
  right: 10px;
}





.contentArea .asideList .suggested_follows .follows .followsRefresh{
  background-color: rgb(230, 163, 185);
  width: 240px;
  height: 50px;
  margin-left: 10px;
  position: relative;
}
.contentArea .asideList .suggested_follows .follows .followsRefresh span{
  font-size: 14px;
  position: absolute;
  top: 15px;
  left: 100px;
}
.contentArea .asideList .suggested_follows .follows .followsRefresh .el-icon{
  font-size: 18px;
  position: absolute;
  left: 75px;
  top: 15px;
}









.contentArea .asideList .b3{
  background-color: gold;
  border-radius: 3px;
  width: 100%;
  height: 300px;
  position: relative;
  margin-bottom: 10px;
}
</style>
