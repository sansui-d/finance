<template>
  <el-dialog
    title="预算详情"
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
        <el-form-item label="姓名:">
          <div>{{ row.name }}</div>
        </el-form-item>
        <el-form-item label="账户:">
          <div>{{ row.actName }}</div>
        </el-form-item>
        <el-form-item label="类型:">
          <div>{{ row.type }}</div>
        </el-form-item>
        <el-form-item label="预算金额:">
          <div>{{ row.money }}</div>
        </el-form-item>
        <el-form-item label="时间:">
          <div>{{ row.time }}</div>
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
        name: "",
        actName: "",
        type: "",
        money:"",
        time:""
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