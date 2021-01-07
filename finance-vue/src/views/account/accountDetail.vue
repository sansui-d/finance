<template>
  <el-dialog
    title="账户详情"
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
        <el-form-item label="户主:">
          <div>{{ row.name }}</div>
        </el-form-item>
        <el-form-item label="余额:">
          <div>{{ row.surplus }}</div>
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
        loginId: "",
        sex: "",
        phone:""
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