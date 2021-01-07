<template>
  <div class="login">
    <div class="form">
      <div class="title">家财万贯理财后台管理</div>
      <el-tabs value="first" stretch>
        <el-tab-pane label="登录" name="first">
          <el-form
            :model="ruleForm"
            status-icon
            ref="ruleForm"
            label-width="70px"
            class="demo-ruleForm"
          >
            <el-form-item
              label="账号"
              prop="loginId"
              :rules="[
                { required: true, message: '请输入账号', trigger: 'blur' },
              ]"
            >
              <el-input
                type="text"
                v-model="ruleForm.loginId"
                autocomplete="off"
              ></el-input>
            </el-form-item>
            <el-form-item
              label="密码"
              prop="loginPass"
              :rules="[
                { required: true, message: '请输入密码', trigger: 'blur' },
              ]"
            >
              <el-input
                type="password"
                v-model="ruleForm.loginPass"
                autocomplete="off"
              ></el-input>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="submitForm('ruleForm')"
                >登录</el-button
              >
              <el-button @click="resetForm('ruleForm')">重置</el-button>
            </el-form-item>
          </el-form>
        </el-tab-pane>
        <el-tab-pane label="注册" name="second">
          <div>管理员注册暂未开放，请联系开发人员</div>
        </el-tab-pane>
      </el-tabs>
    </div>
  </div>
</template>

<script>
import { getToken, setToken,setadmin } from "@/cookie/cookie.js";
export default {
  data() {
    return {
      ruleForm: {
        loginId: "",
        loginPass: "",
      },
    };
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          const login = this.ruleForm;
          this.$axios.post("/api/login/loginAdmin", login).then((res) => {
            if (res.data.isLogin == "200") {
              const token = res.data.token;
              const Admin = res.data.admin;
              setToken(token);
              setadmin(Admin);
              this.$router.push({
                name:'home',
              })
            }else{
              this.$message.error('账号密码错误，请重新输入');
            }
          });
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    },
  },
};
</script>

<style scoped lang="scss">
.login {
  width: 100%;
  height: 100%;
  background-image: url("../../assets/bgimg.jpg");
  box-sizing: border-box;
  padding-top: 200px;
}
.form {
  width: 30%;
  background-color: rgba(255, 255, 255, 0.8);
  margin: 0 auto;
  padding: 40px 60px;
  box-sizing: border-box;
  border-radius: 20px;

  .title {
    font-size: 20px;
  }
  .el-form {
    margin-top: 20px;
  }
}
</style>
