<template>
  <el-dialog
    title="修改保险信息"
    :visible="showEdit"
    @close="close('row')"
    width="30%"
    center
  >
    <span>
      <el-form label-position="right" label-width="80px" :model="row" ref="row">
        <el-form-item
          label="保险名称"
          prop="sureTitle"
          :rules="[
            { required: true, message: '请输入保险名称', trigger: 'blur' },
          ]"
        >
          <el-input v-model="row.sureTitle"></el-input>
        </el-form-item>
        <el-form-item
          label="保险内容"
          prop="sureContent"
          :rules="[
            { required: true, message: '请输入保险内容', trigger: 'blur' },
          ]"
        >
          <el-input v-model="row.sureContent"></el-input>
        </el-form-item>
        <el-form-item
          label="金额"
          prop="sureMoney"
          :rules="[{ required: true, message: '请输入金额', trigger: 'blur' }]"
        >
          <el-input v-model="row.sureMoney"></el-input>
        </el-form-item>
        <el-form-item
          label="时限"
          prop="sureTime"
          :rules="[
            { required: true, message: '请输入账户时限', trigger: 'blur' },
          ]"
        >
          <el-input v-model="row.sureTime"></el-input>
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
        sureTitle: "",
        sureContent: "",
        sureMoney: "",
        sureTime:""
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
          const findId = this.$store.getters.getinsure;
          const insure = this.row;
          this.$axios.post("/api/insure/updateInsure", insure).then((res) => {
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