<template>
  <el-dialog
    title="债务详情"
    :visible="showDetail"
    @close="close"
    width="30%"
    center
  >
    <span>
      <el-form
        label-position="reght"
        label-width="80px"
        :model="formLabelAlign"
      >
        <el-form-item label="账户号:">
          <div>{{ row.actName }}</div>
        </el-form-item>
        <el-form-item label="姓名:">
          <div>{{ row.name }}</div>
        </el-form-item>
        <el-form-item label="名称:">
          <div>{{ row.debtName }}</div>
        </el-form-item>
        <el-form-item label="金额:">
          <div>{{ row.debtMoney }}</div>
        </el-form-item>
        <el-form-item label="类型:">
          <div>{{ row.type }}</div>
        </el-form-item>
        <el-form-item label="到期时间:">
          <div>{{ row.debtTime }}</div>
        </el-form-item>
      </el-form>
    </span>
    <span slot="footer" class="dialog-footer">
      <el-button @click="close">关 闭</el-button>
    </span>
  </el-dialog>
</template>

<script>
export default {
  name: "userDatail",
  props: {
    showDetail: {
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
        debtName: "",
        actName: "",
        debtMoney:"",
        debtTime:"",
        type:"",
      },
    };
  },
  methods: {
    //关闭组件弹窗
    close() {
      this.$emit("close");
    },
  },
};
</script>

<style>
</style>