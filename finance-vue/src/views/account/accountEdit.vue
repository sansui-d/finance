<template>
  <el-dialog
    title="修改账户信息"
    :visible="showEdit"
    @close="close('row')"
    width="30%"
    center
  >
    <span>
      <el-form label-position="right" label-width="80px" :model="row" ref="row">
        <el-form-item
          label="账户号"
          prop="actName"
          :rules="[
            { required: true, message: '请输入账户号', trigger: 'blur' },
          ]"
        >
          <el-input v-model="row.actName"></el-input>
        </el-form-item>
        <el-form-item
          label="账户密码"
          prop="actPass"
          :rules="[
            { required: true, message: '请输入账户密码', trigger: 'blur' },
          ]"
        >
          <el-input v-model="row.actPass"></el-input>
        </el-form-item>
        <el-form-item
          label="户主"
          prop="name"
          :rules="[{ required: true, message: '请输入户主', trigger: 'blur' }]"
        >
          <el-input v-model="row.name" disabled></el-input>
        </el-form-item>
        <el-form-item
          label="余额"
          prop="surplus"
          :rules="[
            { required: true, message: '请输入账户余额', trigger: 'blur' },
          ]"
        >
          <el-input v-model="row.surplus"></el-input>
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
        actName: "",
        actPass: "",
        name: "",
        surplus: "",
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
          const account = this.row;
          this.$axios.post("/api/account/updateAccount", account).then((res) => {
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