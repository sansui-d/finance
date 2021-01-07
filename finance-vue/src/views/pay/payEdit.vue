<template>
  <el-dialog
    title="修改支出信息"
    :visible="showEdit"
    @close="close('row')"
    width="30%"
    center
  >
    <span>
      <el-form label-position="right" label-width="80px" :model="row" ref="row">
        <el-form-item
          label="账户"
          prop="actName"
          :rules="[
            { required: true, message: '请输入账户', trigger: 'blur' },
          ]"
        >
          <el-input v-model="row.actName" disabled></el-input>
        </el-form-item>
        <el-form-item
          label="姓名"
          prop="name"
          :rules="[
            { required: true, message: '请输入姓名', trigger: 'blur' },
          ]"
        >
          <el-input v-model="row.name" disabled></el-input>
        </el-form-item>
        <el-form-item
          label="支出"
          prop="money"
          :rules="[{ required: true, message: '请输入支出', trigger: 'blur' }]"
        >
          <el-input v-model="row.money"></el-input>
        </el-form-item>
        <el-form-item
          label="类型"
          prop="type"
          :rules="[
            { required: true, message: '请输入类型', trigger: 'blur' },
          ]"
        >
          <el-input v-model="row.type"></el-input>
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
  name: "payEdit",
  props: {
    showEdit: {
      type: Boolean,
      default: false
    },
    row: {
      type: Object,
      default: {},
    },
  },
  watch:{
    showEdit:{
      handler(newName,oldName){
        this.num = JSON.parse(JSON.stringify(this.row.money))
      },
      deep:true,
    }
  },
  data() {
    return {
      formLabelAlign: {
        money: "",
        type: "",
        actId:"",
      },
      num:null,
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
          const all = parseInt(this.row.surplus) - parseInt(this.num) + parseInt(this.row.money)
          this.row.surplus = String(all)
          const pay = this.row
          console.log(this.row)
          this.$axios.post("/api/pay/updatePay", pay).then((res) => {
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