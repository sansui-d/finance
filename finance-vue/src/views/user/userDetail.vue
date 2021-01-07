<template>
  <el-dialog
    title="用户详情"
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
        <el-form-item label="账号:">
          <div>{{ row.loginId }}</div>
        </el-form-item>
        <el-form-item label="性别:">
          <div>{{ row.sex }}</div>
        </el-form-item>
        <el-form-item label="电话:">
          <div>{{ row.phone }}</div>
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
  name: "userDetail",
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