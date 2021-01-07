<template>
  <el-dialog
    title="添加保险"
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
          label="保险名称"
          prop="sureTitle"
          :rules="[
            { required: true, message: '请输入保险名称', trigger: 'blur' },
          ]"
        >
          <el-input v-model="thisUser.sureTitle"></el-input>
        </el-form-item>
        <el-form-item
          label="保险内容"
          prop="sureContent"
          :rules="[
            { required: true, message: '请输入保险内容', trigger: 'blur' },
          ]"
        >
          <el-input v-model="thisUser.sureContent"></el-input>
        </el-form-item>
        <el-form-item
          label="金额"
          prop="sureMoney"
          :rules="[{ required: true, message: '请输入金额', trigger: 'blur' }]"
        >
          <el-input v-model="thisUser.sureMoney"></el-input>
        </el-form-item>
        <el-form-item
          label="时限"
          prop="sureTime"
          :rules="[{ required: true, message: '请输入时限', trigger: 'blur' }]"
        >
          <el-input v-model="thisUser.sureTime"></el-input>
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
        sureTitle: "",
        sureContent: "",
        sureMoney: "",
        sureTime: "",
        type: "保险",
      },
    };
  },
  methods: {
    //关闭组件弹窗
    close(formName) {
      this.thisUser.sureTitle = "";
      this.thisUser.sureContent = "";
      this.thisUser.sureMoney = "";
      this.thisUser.sureTime = "";
      this.$emit("close");
      this.$refs[formName].clearValidate();
    },
    //添加
    addUser(thisUser) {
      this.$refs[thisUser].validate((valid) => {
        if (valid) {
          let insure = this.thisUser;
          this.$axios.post("/api/insure/addInsure", insure).then((res) => {
            if (res.data == 200) {
              this.thisUser.sureTitle = "";
              this.thisUser.sureContent = "";
              this.thisUser.sureMoney = "";
              this.thisUser.sureTime = "";
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