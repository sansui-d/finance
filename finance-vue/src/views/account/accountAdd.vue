<template>
  <el-dialog
    title="添加账户"
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
          label="账户号"
          prop="actName"
          :rules="[
            { required: true, message: '请输入账户号', trigger: 'blur' },
          ]"
        >
          <el-input v-model="thisUser.actName" placeholder="请输入账户号"></el-input>
        </el-form-item>
        <el-form-item
          label="账户密码"
          prop="actPass"
          :rules="[
            { required: true, message: '请输入账户密码', trigger: 'blur' },
          ]"
        >
          <el-input v-model="thisUser.actPass" placeholder="请输入账户密码" type="password"></el-input>
        </el-form-item>
        <el-form-item
          label="姓名"
          prop="houseHolder"
          :rules="[{ required: true, message: '请选择用户姓名', trigger: 'blur' }]"
        >
          <el-select v-model="thisUser.houseHolder" filterable placeholder="请选择用户姓名" @focus="allUser">
            <el-option
              v-for="item in options"
              :key="item.userId"
              :label="item.name"
              :value="item.userId"
            >
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item
          label="余额"
          prop="surplus"
          :rules="[{ required: true, message: '请输入余额', trigger: 'blur' }]"
        >
          <el-input v-model="thisUser.surplus" oninput="value=value.replace(/[^0-9]/g,'')"
          placeholder="请输入余额"></el-input>
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
        actName: "",
        actPass: "",
        houseHolder: "",
        surplus: "",
      },
      options: null,
    };
  },
  
  methods: {
    //关闭组件弹窗
    close(formName) {
      this.thisUser.actName = "";
      this.thisUser.actPass = "";
      this.thisUser.surplus = "";
      this.thisUser.houseHolder = ""
      this.$emit("close");
      this.$refs[formName].clearValidate();
    },
    //添加
    addUser(thisUser) {
      this.$refs[thisUser].validate((valid) => {
        if (valid) {
          console.log(this.thisUser)
          let account = this.thisUser;
          this.$axios.post("/api/account/addAccount", account).then((res) => {
            console.log(res)
            if (res.data == 200) {
              
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
    //获取所以用户名字
    allUser() {
      this.$axios.get("/api/account/allUser").then((res) => {
        this.options = res.data;
        console.log(res)
      });
    },
  },
};
</script>

<style>
</style>