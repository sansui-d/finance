<template>
  <el-dialog
    title="修改用户信息"
    :visible="showEdit"
    @close="close('row')"
    width="30%"
    center
  >
    <span>
      <el-form label-position="right" label-width="80px" :model="row" ref="row">
        <el-form-item
          label="姓名"
          prop="name"
          :rules="[{ required: true, message: '请输入姓名', trigger: 'blur' }]"
        >
          <el-input v-model="row.name"></el-input>
        </el-form-item>
        <el-form-item
          label="账号"
          prop="loginId"
          :rules="[{ required: true, message: '请输入账号', trigger: 'blur' }]"
        >
          <el-input v-model="row.loginId"></el-input>
        </el-form-item>
        <el-form-item
          label="密码"
          prop="loginPass"
          :rules="[{ required: true, message: '请输入密码', trigger: 'blur' }]"
        >
          <el-row>
            <el-col :span="17">
              <el-input
                v-model="row.loginPass"
                type="password"
                disabled
                max="100"
              ></el-input>
            </el-col>
            <el-col :span="6">
              <el-tooltip placement="top">
                <div slot="content">选中重置用户密码<br />请慎重选择！！！</div>
                <el-checkbox border v-model="checked">重置密码</el-checkbox>
              </el-tooltip>
            </el-col>
          </el-row>
        </el-form-item>
        <el-form-item
          label="性别"
          prop="sex"
          :rules="[{ required: true, message: '请选择性别', trigger: 'blur' }]"
        >
          <el-radio-group v-model="row.sex">
            <el-radio label="男"></el-radio>
            <el-radio label="女"></el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item
          label="电话"
          prop="phone"
          :rules="[{ required: true, message: '请输入电话', trigger: 'blur' }]"
        >
          <el-input v-model="row.phone"></el-input>
        </el-form-item>
      </el-form>
    </span>
    <span slot="footer" class="dialog-footer">
      <el-button @click="close('row')">取 消</el-button>
      <el-button type="primary" @click="btn('row')">修 改</el-button>
    </span>
  </el-dialog>
</template>

<script>
export default {
  name: "userEdit",
  props: {
    showEdit: {
      type: Boolean,
    },
    row: {
      type: Object,
      default: {},
    },
  },
  data() {
    return {
      formLabelAlign: {
        name: "",
        loginId: "",
        sex: "",
        phone: "",
      },
      //重置密码
      checked: false,
    };
  },
  methods: {
    //关闭组件弹窗
    close(formName) {
      this.$emit("close");
      this.$refs[formName].clearValidate();
      this.checked = false;
    },
    //修改信息
    btn(row) {
      this.$refs[row].validate((valid) => {
        if (valid) {
          const findId = this.$store.getters.getuser;
          const user = this.row;
          console.log(findId);
          console.log(user);
          for (let i in findId) {
            if (findId[i].userId == user.userId) {
              user.loginPass = findId[i].loginPass;
            }
          }
          if (this.checked == true) {
            user.loginPass = "123456";
          }
          this.$axios.post("/api/user/updateUser", user).then((res) => {
            if (res.data == 200) {
              this.$message({
                type: "success",
                message: "修改成功!",
              });
              this.$emit("close");
              this.$emit("init");
            } else {
              this.$message.error("修改失败");
            }
          });
        } else {
          console.log("表单验证不通过!");
          return false;
        }
      });
    },
  },
};
</script>

<style>
</style>