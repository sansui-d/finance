<template>
  <el-dialog
    title="添加用户"
    :visible="showAdd"
    @close="close('thisUser')"
    width="30%"
    center
  >
    <span>
      <el-form
        label-position="reght"
        label-width="80px"
        :model="thisUser"
        ref="thisUser"
      >
        <el-form-item
          label="账号"
          prop="loginId"
          :rules="[{ required: true, message: '请输入账号', trigger: 'blur' }]"
        >
          <el-input v-model="thisUser.loginId" placeholder="请输入账户"></el-input>
        </el-form-item>
        <el-form-item
          label="密码"
          prop="loginPass"
          :rules="[{ required: true, message: '请输入密码', trigger: 'blur' }]"
        >
          <el-input v-model="thisUser.loginPass" disabled></el-input>
        </el-form-item>
      </el-form>
    </span>
    <span slot="footer" class="dialog-footer">
      <el-button @click="close('thisUser')">取 消</el-button>
      <el-button type="primary" @click="addUser('thisUser')">添 加</el-button>
    </span>
  </el-dialog>
</template>

<script>
export default {
  name: "userAdd",
  props: {
    showAdd: {
      type: Boolean,
    },
  },
  data() {
    return {
      thisUser: {
        loginId: "",
        loginPass: "123456",
      },
    };
  },
  methods: {
    //关闭组件弹窗
    close(formName) {
      this.thisUser.loginId = "";
      this.$emit("close");
      this.$refs[formName].clearValidate();
    },
    //添加
    addUser(thisUser) {
      this.$refs[thisUser].validate((valid) => {
        if (valid) {
          this.thisUser.time = this.getTime();
          this.thisUser.name = "user" + Math.ceil(Math.random() * 10000);
          this.thisUser.sex = "男";
          this.thisUser.phone = "123456";
          let user = this.thisUser;
          this.$axios.post("/api/user/addUser", user).then((res) => {
            if (res.data == 200) {
              this.thisUser.loginId = "";
              this.$emit("close");
              this.$emit("init");
              this.$message({
                type: "success",
                message: "添加成功!",
              });
            }
          });
        } else {
          console.log("表单验证不通过!");
          return false;
        }
      });
    },
    //获取当前时间
    getTime() {
      let date = new Date();
      let year = date.getFullYear();
      let month = date.getMonth() + 1;
      let day = date.getDate();
      return year + "年" + month + "月" + day + "日";
    },
  },
};
</script>

<style>
</style>