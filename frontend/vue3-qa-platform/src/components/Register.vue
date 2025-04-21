<template>
  <div id="logo">
    <img class="login-logo" src="../assets/imgs/logo.png" alt="" />
  </div>
  <div class="register">
    <!-- 姓名 -->
    <el-form
      :model="registerForm"
      :rules="rules"
      ref="ruleFormRef"
      label-position="top"
      label-width="120px"
    >
      <el-form-item label="姓名:" prop="username"
        ><!--:newName="newName"-->
        <el-input v-model="registerForm.username" />
      </el-form-item>
      <!-- 密码 -->
      <el-form-item label="密码:" prop="password" >
        <el-input v-model="registerForm.password" show-password />
      </el-form-item>
      <!-- 性别 -->
      <el-form-item label="性别:" prop="gender">
        <el-radio-group v-model="registerForm.gender">
          <el-radio value="男">男</el-radio>
          <el-radio value="女">女</el-radio>
        </el-radio-group>
      </el-form-item>
      <!-- 电话 -->
      <el-form-item label="电话:" prop="phoneNumber">
        <el-input v-model="registerForm.phoneNumber" />
      </el-form-item>
    </el-form>
    <button class="register_submit" @click="submit(ruleFormRef)">注册</button>
    <div>
      <router-link to="/login" style="text-decoration: none;color: #409eff;margin-left: 24px;display: inline-block;font-size: 12px;">去登录</router-link>
    </div>
  </div>
</template>

<script setup>
import { ref, reactive, toRaw } from "vue";
import { Search } from "@element-plus/icons-vue";
import { useRouter } from "vue-router";
import { register } from "@/api/api.user";
import { ElMessage } from "element-plus";

const router = useRouter();

const registerForm = reactive({
  username: "",
  password: "",
  gender: "",
  phoneNumber: "",
});

const ruleFormRef = ref();

const submit = async (ruleFormEl) => {
  if (!ruleFormEl) return;
  await ruleFormEl.validate((valid, fields) => {
    if (valid) {
      register(toRaw(registerForm))
        .then((res) => {
          ElMessage.success(res.message);
          router.push("/login");
        })
        .catch((err) => {
          const { response } = err;
          ElMessage.error(response.data.message);
        });
    }
  });
};

const rules = {
  username: [
    { required: true, message: "请输入您的名字", trigger: "blur" },
    { min: 3, max: 20, message: "username长度不符", trigger: "blur" },
  ],
  password: [
    {
      required: true,
      validator: (rule, value, callback) => {
        let password = /^[0-9a-zA-z_*#@]{6,10}$/;
        if (value == "") callback(new Error("请输入密码"));
        else if (!password.test(value)) callback(new Error("请输入正确的密码格式"));
        else callback();
      },
      trigger: "blur",
    },
  ],
  gender: [{ required: true, message: "请选择性别", trigger: "change" }],
  phoneNumber: [
    {
      required: true,
      validator: (rule, value, callback) => {
        let reg = /^1[3|4|5|7|8][0-9]{9}$/;
        if (value == "") callback(new Error("请输入手机号"));
        else if (!reg.test(value)) callback(new Error("清输入正确的手机号格式"));
        else callback();
      },
      trigger: "blur",
    },
  ],
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
.register {
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
.el-form-item {
  display: block;
  margin-top: 30px;
  width: 350px;
  padding-left: 30px;
  padding-right: 30px;
}
.el-input {
  width: 350px;
  height: 35px;
  margin: 0 auto;
}
.register_submit {
  width: 350px;
  margin: 0 auto;
  height: 35px;
  margin: 0 auto;
  margin-top: 30px;
  background: #0084ff;
  color: white;
  border-radius: 4px 4px 0px 0px;
  border: 1px solid transparent;
}

</style>
