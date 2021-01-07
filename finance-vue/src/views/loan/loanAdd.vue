<template>
  <el-dialog
    title="添加贷款"
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
          label="贷款名称"
          prop="loanName"
          :rules="[
            { required: true, message: '请输入贷款名称', trigger: 'blur' },
          ]"
        >
          <el-input v-model="thisUser.loanName"></el-input>
        </el-form-item>
        <el-form-item
          label="贷款内容"
          prop="loanContent"
          :rules="[
            { required: true, message: '请输入贷款内容', trigger: 'blur' },
          ]"
        >
          <el-input v-model="thisUser.loanContent"></el-input>
        </el-form-item>
        <el-form-item
          label="贷款金额"
          prop="loanMoney"
          :rules="[
            { required: true, message: '请输入贷款金额', trigger: 'blur' },
          ]"
        >
          <el-input v-model="thisUser.loanMoney"></el-input>
        </el-form-item>
        <el-form-item
          label="贷款时限"
          prop="loanTime"
          :rules="[
            { required: true, message: '请输入贷款时限', trigger: 'blur' },
          ]"
        >
          <el-input v-model="thisUser.loanTime"></el-input>
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
        loanName: "",
        loanContent: "",
        loanMoney: "",
        type: "贷款",
        loanTime:"",
      },
    };
  },
  methods: {
    //关闭组件弹窗
    close(formName) {
      this.thisUser.loanName = "";
      this.thisUser.loanContent = "";
      this.thisUser.loanMoney = "";
      this.thisUser.loanTime = "";
      this.$emit("close");
      this.$refs[formName].clearValidate();
    },
    //添加
    addUser(thisUser) {
      this.$refs[thisUser].validate((valid) => {
        if (valid) {
          let loan = this.thisUser;
          this.$axios.post("/api/loan/addLoan", loan).then((res) => {
            if (res.data == 200) {
              this.thisUser.loanName = "";
              this.thisUser.loanContent = "";
              this.thisUser.loanMoney = "";
              this.thisUser.loanTime = "";
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