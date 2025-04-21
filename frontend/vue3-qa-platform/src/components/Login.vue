<template>
  <div id="logo">
    <img class="login-logo" src="../assets/imgs/logo.png" alt="" />
  </div>
  <div class="login">
    <div class="login_one">
      <el-tabs v-model="activeName" @tab-click="handleClick">
        <!-- 1.免密码登入 -->
        <el-tab-pane label="免密码登入" name="first">
          <el-form
            :model="noPassWordForm"
            :rules="rules"
            ref="formRef"
            label-position="top"
            label-width="120px"
            class="phone_password"
          >
            <el-form-item label="手机号:" prop="userPhone">
              <el-input class="on_uphone_input" v-model="noPassWordForm.userPhone" />
            </el-form-item>
            <!-- 密码 -->
            <el-form-item label="密码:" prop="password">
              <el-input v-model="noPassWordForm.password" />
            </el-form-item>
          </el-form>

          <el-form
            :model="noPassWordForm"
            :rules="rules"
            ref="formRef"
            label-position="left"
            label-width="120px"
            class="yzCode"
          >
            <!-- 验证码 -->
            <el-form-item label="验证码:" prop="checkCode">
              <el-input
                v-model="noPassWordForm.checkCode"
                size="small"
                clearable
                placeholder="输入验证码"
              />
              <el-button
                type="primary"
                size="mini"
                style="margin-left: 10px"
                @click="getCode"
                v-if="show"
                >获取验证码</el-button
              >
              <el-button
                type="primary"
                size="mini"
                style="margin-left: 10px"
                disabled
                v-if="!show"
                >{{ count }}秒后重新发送</el-button
              >
            </el-form-item>
          </el-form>
          <el-form-item class="login_or_register">
            <el-button type="primary">登入</el-button>
          </el-form-item>
        </el-tab-pane>

        <!-- 2.密码登入 -->
        <el-tab-pane label="密码登入" name="second">
          <!-- 用户名/手机号登入 -->
          <el-form
            :model="passWordForm"
            :rules="pwdrules"
            ref="ruleFormRef"
            label-position="top"
            label-width="120px"
            class="phone_password"
          >
            <el-form-item label="手机号/用户名:" prop="username">
              <el-input class="on_uphone_input"  v-model="passWordForm.username" />
            </el-form-item>
            <!-- 密码 -->
            <el-form-item label="密码:" prop="password">
              <el-input v-model="passWordForm.password" show-password/>
            </el-form-item>
            <el-form-item class="login_or_register">
              <el-button type="primary" @click="submitForm(ruleFormRef)">登入</el-button>
            </el-form-item>
          </el-form>

          <!-- 验证码登入 -->
          <!-- <el-form
            :model="noPassWordForm"
            :rules="rules"
            ref="ruleFormRef"
            label-position="left"
            label-width="120px"
            class="yzCode"
          >
            <el-form-item label="验证码:" prop="checkCode">
              <el-input
                v-model="noPassWordForm.checkCode"
                size="small"
                clearable
                placeholder="输入验证码"
              />
              <el-button
                type="primary"
                size="mini"
                style="margin-left: 10px"
                @click="getCode"
                v-if="show"
                >获取验证码</el-button
              >
              <el-button
                type="primary"
                size="mini"
                style="margin-left: 10px"
                disabled
                v-if="!show"
                >{{ count }}秒后重新发送</el-button
              >
            </el-form-item>
          </el-form> -->
        </el-tab-pane>
        <!-- 公共部分 -->
        
      </el-tabs>
      <div class="login_sign_tips">
        <h4>
          用户名登入成功后自动记住登入状态,注册即代表同意
          <a href="#">《协议》</a>
          <a href="#">《隐私保护指引》</a>
          <router-link to="/register" style="color: #409eff;">点击注册</router-link>
        </h4>
      </div>
    </div>
  </div>
</template>

<script setup>
import { reactive, ref, toRaw } from "vue";
import { Search } from "@element-plus/icons-vue";
import { useRouter } from "vue-router";
import { login } from "@/api/api.user";
import authentication from "@/store/authentication";

const permission = authentication();

const noPassWordForm = reactive({
  userPhone: "",
  password: "",
  checkCode: "",
});

const passWordForm = reactive({
  username: "",
  password: "",
});

const formRef = ref();

const ruleFormRef = ref();

//密码登入规则
const pwdrules = {
  username: [
    {
      required: true,
      message: "请输入用户名",
      trigger: "blur",
    },
  ],
  password: [
    {
      required: true,
      validator: (rule, value, callback) => {
        if (value == "") callback(new Error("请输入密码"));
        else if (value.length < 6 || value.length > 10)
          callback(new Error("密码长度不能小于6位且不能大于19位!"));
        else callback();
      },
      trigger: "blur",
    },
  ],
};

//免密码登入规则
const rules = {
  userPhone: [
    {
      required: true,
      validator: (rule, value, callback) => {
        let reg = /^1[3|4|5|7|8][0-9]{9}$/;
        if (value == "") callback(new Error("请输入手机号"));
        else if (!reg.test(value)) callback(new Error("清输入正确的手机号格式"));
      },
      trigger: "blur",
    },
  ],
  password: [
    {
      required: true,
      validator: (rule, value, callback) => {
        if (value == "") callback(new Error("请输入密码"));
        else if (value.length < 6 || value.length > 10)
          callback(new Error("密码长度不能小于6位且不能大于19位!"));
      },
      trigger: "blur",
    },
  ],
  checkCode: [
    { required: true, message: "验证码不能为空", trigger: "blur" },
    { max: 4, message: "验证码长度为4位", trigger: "blur" },
  ],
};
//验证码倒计时
const count = ref(60);
//v-if判断
const show = ref(true);

const getCode = () => {
  show.value = false;
  count.value = 60;
  const timer = setInterval(() => {
    if (count.value > 0) {
      count.value--;
    } else {
      show.value = true;
      clearInterval(timer);
    }
  }, 1000);
};

const router = useRouter();

const activeName = ref("first");

const submitForm = async (formEl) => {
  if (!formEl) return;
  await formEl.validate((valid, fields) => {
    if (valid) {
      login(toRaw(passWordForm)).then(
        (response) => {
          ElMessage.success(response.message);

          permission.token = response.data.token;
          permission.id = response.data.id;
          permission.username = response.data.username;
          permission.role = response.data.role;
          router.push("/");
        },
        (error) => {
          console.log(error);
          // ElMessage.error(error.message);
        }
      );
    }
  });
};
</script>

<style scoped>
#logo .login-logo {
  margin: 0 auto;
  padding-top: 50px;
  display: block;
  width: 200px;
  height: 100px;
  background: url(../images/xs_logo.png) no-repeat center center;
}
.login {
  background: palevioletred;
  display: flex;
  flex-direction: column;
  width: 400px;
  height: 500px;
  background: white;
  margin: 0 auto;
  border: 1px solid #9196a1;
  border-radius: 4px 4px 0px 0px;
}
.login_one {
  /* background-color: pink; */
  display: flex;
  flex-direction: column;
  height: 470px;
  width: 360px;
  margin: 0 auto;
  margin-top: 15px;
}
/* 手机号，密码 */
.login .login_one .el-tabs .el-tab-pane .phone_password {
  /* background-color: peru; */
  margin-top: 12px;
}
/* 密码 */
.login .login_one .el-tabs .el-tab-pane .phone_password .on_uphone_input {
  /* background-color: palegreen; */
  margin-bottom: 8px;
}
/* 验证码 */
.login .login_one .el-tabs .el-tab-pane .yzCode {
  display: flex;
  width: 360px;
  /* align-items: center; */
  justify-content: space-between;
  /* background-color: palevioletred; */
  margin-top: 40px;
  /* margin-top: 30px; */
  /* padding-left: 10px; */
}
.login .login_one .el-tabs .el-tab-pane .yzCode .el-input {
  /* background-color: palegreen; */
  /* padding-left: 10px; */
  /* justify-content: space-between; */
  margin-left: -50px;
  width: 50%;
}
.login .login_one .el-tabs .el-tab-pane .yzCode .el-button {
  /* margin-top: 10px; */
  width: 60%;
  /* background-color: peachpuff; */
}
.el-tabs {
  width: 100%;

  /* background-color: palegreen;  */
}
/* .el-tabs .el-tab-pane .el-form .el-form-item .el-input */
.el-input__input {
  /* background-color: palevioletred; */
  /* width: 300px; */
  border: 0;
  /* border-width: 0; */
  /* border: 1px solid transparent;
  border-bottom: 1px solid rgb(132, 145, 165);
   */
}
/* .login_or_register{
  background-color:plum;
} */
.login_or_register .el-button {
  /* background-color: palevioletred; */
  margin-top: 20px;
  height: 40px;
  width: 100%;
}
/* 用户名登入成功后自动记住登入状态,
注册即代表同意 */
.login .login_one .login_sign_tips {
  height: 100%;
  /* background: hotpink; */
}

.login .login_one .login_sign_tips > h4 {
  box-sizing: border-box;
  margin: 0;
  text-indent: 2em;
  /* padding-top: 40px; */
  /* padding-left: 35px; */
  /* padding-right: 30px; */
  font-size: 12px;
  font-weight: 400;
  /* background: paleturquoise; */
}

.login .login_one .login_sign_tips > h4 a {
  text-decoration: none;
}
</style>
