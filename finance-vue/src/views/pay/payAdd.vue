<template>
  <el-dialog
    title="添加支出"
    :visible="showAdd"
    @close="close('thisUser')"
    width="30%"
    center
  >
    <span>
      <el-form
        label-position="right"
        label-width="80px"
        :model="thisUser"
        ref="thisUser"
      >
        <el-form-item
          label="账户"
          prop="actId"
          :rules="[{ required: true, message: '请选择账户', trigger: 'blur' }]"
        >
          <el-select
            v-model="thisUser.actId"
            filterable
            placeholder="请选择账户"
            @focus="allAccount"
            @change="change"
          >
            <el-option
              v-for="item in options"
              :key="item.actId"
              :label="item.actName"
              :value="item.actId"
            >
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item
          label="姓名"
          prop="userId"
          :rules="[{ required: true, message: '请选择姓名', trigger: 'blur' }]"
        >
          <el-select
            v-model="thisUser.userId"
            filterable
            placeholder="请选择用户姓名"
            @focus="nameByActId"
          >
            <el-option
              v-for="item in option"
              :key="item.userId"
              :label="item.name"
              :value="item.userId"
            >
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item
          label="支出"
          prop="money"
          :rules="[{ required: true, message: '请输入支出', trigger: 'blur' }]"
        >
          <el-input v-model="thisUser.money"></el-input>
        </el-form-item>
        <el-form-item
          label="类型"
          prop="type"
          :rules="[{ required: true, message: '请输入类型', trigger: 'blur' }]"
        >
          <el-input v-model="thisUser.type"></el-input>
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
  name: "payAdd",
  props: {
    showAdd: {
      type: Boolean,
    },
  },
  watch:{
    "thisUser.actId":{
      handler(newName,oldName){
        for(let i in this.options){
          if(this.options[i].actId==newName){
            this.thisUser.surplus = this.options[i].surplus
          }
        }
      },
      deep: true,
      immediate: true
    } 
  },
  data() {
    return {
      thisUser: {
        type: "",
        money: "",
        actId: "",
        userId: "",
        outTime: "",
        surplus: "",
      },
      options: null,
      option: null,
    };
  },
  methods: {
    //关闭组件弹窗
    close(formName) {
      this.thisUser.money = "";
      this.thisUser.userId = "";
      this.thisUser.actId = "";
      this.thisUser.type = "";
      this.option = null;
      this.$emit("close");
      this.$refs[formName].clearValidate();
    },
    //添加
    addUser(thisUser) {
      this.$refs[thisUser].validate((valid) => {
        if (valid) {
          this.thisUser.outTime = this.getTime();
          const surplus = parseInt(this.thisUser.surplus,10) - parseInt(this.thisUser.money,10)
          this.thisUser.surplus = String(surplus);
          let pay = this.thisUser;
          this.$axios.post("/api/pay/addPay", pay).then((res) => {
            if (res.data == 200) {
              this.thisUser.money = "";
              this.thisUser.userId = "";
              this.thisUser.actId = "";
              this.thisUser.type = "";
              this.option = null;
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
    allAccount() {
      this.$axios.get("/api/income/allAccount").then((res) => {
        this.options = res.data;
        console.log(res);
      });
    },
    //清空2
    change() {
      this.thisUser.userId = null;
    },
    nameByActId() {
      if (this.thisUser.actId !== "") {
        const actId = this.thisUser.actId;
        this.$axios
          .get("/api/income/nameByActId", {
            params: { actId },
          })
          .then((res) => {
            this.option = res.data;
            console.log(res);
          });
      } else {
        this.$message({
          type: "info",
          message: "请先选择账户!",
        });
      }
    },
  },
};
</script>

<style>
</style>