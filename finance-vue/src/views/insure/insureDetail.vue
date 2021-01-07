<template>
  <el-dialog
    title="保险详情"
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
        <el-form-item label="保险名称:">
          <div>{{ row.sureTitle }}</div>
        </el-form-item>
        <el-form-item label="保险内容:">
          <div>{{ row.sureContent }}</div>
        </el-form-item>
        <el-form-item label="金额:">
          <div>{{ row.sureMoney }}</div>
        </el-form-item>
        <el-form-item label="时限:">
          <div>{{ row.sureTime }}</div>
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
  name: "insureDatail",
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
        sureTitle: "",
        sureContent: "",
        sureMoney: "",
        sureTime:""
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