<template>
  <el-dialog
    title="贷款详情"
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
        <el-form-item label="贷款名称:">
          <div>{{ row.loanName }}</div>
        </el-form-item>
        <el-form-item label="贷款内容:">
          <div>{{ row.loanContent }}</div>
        </el-form-item>
        <el-form-item label="贷款金额:">
          <div>{{ row.loanMoney }}</div>
        </el-form-item>
         <el-form-item label="贷款时限:">
          <div>{{ row.loanTime }}</div>
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
        loanName: "",
        loanContent: "",
        loanMoney: "",
        loanTime:""
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