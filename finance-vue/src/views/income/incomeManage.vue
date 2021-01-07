<template>
  <div
    class="index"
    v-loading="loading"
    element-loading-text="拼命加载中"
    element-loading-spinner="el-icon-loading"
  >
    <el-breadcrumb separator="/">
      <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>收入管理</el-breadcrumb-item>
    </el-breadcrumb>
    <el-form :inline="true" :model="formInline" class="demo-form-inline">
      <el-form-item label="账户">
        <el-input v-model="formInline.actName" placeholder="输入账户"></el-input>
      </el-form-item>
      <el-form-item label="姓名">
        <el-input
          v-model="formInline.name"
          placeholder="输入用户姓名"
        ></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="select">查询</el-button>
        <el-button type="" @click="rese">重置</el-button>
      </el-form-item>
    </el-form>
    <el-button
      type="primary"
      @click="showAdd = true"
      style="float: left; margin-bottom: 10px"
      >添加收入</el-button
    >
    <el-table :data="tableData" style="width: 100%" highlight-current-row fit>
      <el-table-column fixed prop="inTime" label="时间"> </el-table-column>
      <el-table-column fixed prop="actName" label="账户"> </el-table-column>
      <el-table-column fixed prop="name" label="姓名"> </el-table-column>
      <el-table-column fixed prop="money" label="收入"> </el-table-column>
      <el-table-column fixed prop="type" label="类型"> </el-table-column>
      <el-table-column fixed label="操作">
        <template slot-scope="scope">
          <el-button
            @click="
              () => {
                handleClick(scope.row), (showDetail = true);
              }
            "
            type="text"
            size="small"
            >查看</el-button
          >
          <el-button type="text" size="small" @click="handleDelete(scope.row)"
            >删除</el-button
          >
          <el-button
            type="text"
            size="small"
            @click="
              () => {
                (showEdit = true), handleClick(scope.row);
              }
            "
            >编辑</el-button
          >
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
      background
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :current-page="currentPage"
      :page-sizes="[10, 20, 30, 40]"
      :page-size="query.pageSize"
      layout="total, sizes, prev, pager, next, jumper"
      :total="pagination.totalSize"
    >
    </el-pagination>
    <!-- 组件 -->
    <income-add
      :showAdd="showAdd"
      @close="() => (showAdd = false)"
      @init="init"
    />
    <income-detail
      :showDetail="showDetail"
      @close="() => (showDetail = false)"
      :row="rowInfo"
    />
    <income-edit
      :showEdit="showEdit"
      @close="() => (showEdit = false)"
      :row="rowInfo"
      @init="init"
    />
  </div>
</template>

<script>
import incomeAdd from "../income/incomeAdd";
import incomeDetail from "../income/incomeDetail";
import incomeEdit from "../income/incomeEdit";
let page = this.thisPage;
export default {
  name: "incomeManage",
  components: {
    incomeAdd,
    incomeDetail,
    incomeEdit
  },
  data() {
    return {
      //组件传过来的数据定义
      showAdd: false,
      showDetail: false,
      showEdit: false,
      //本页数据
      currentPage: 1,
      pagination: {
        totalPages: 1,
        totalSize: 1,
      },
      formInline: {
        name: null,
        actName: null,
      },
      tableData: [],
      rowInfo: {},
      query: {
        pageNum: 1,
        pageSize: 10,
      },
      //是否点过查询
      isSelect: false,
      loading: false,
    };
  },
  created() {
    this.init();
  },
  methods: {
    //初始数据
    init(
      query = { pageNum: this.query.pageNum, pageSize: this.query.pageSize },
      formInline = {
        name: "",
        actName: "",
      }
    ) {
      this.loading = true;
      const name = this.formInline.name;
      const actName = this.formInline.actName;
      this.query = query;
      const pageNum = this.query.pageNum;
      const pageSize = this.query.pageSize;
      this.$axios
        .get("/api/income/selectIncome", {
          params: { name, actName, pageNum, pageSize },
        })
        .then((res) => {
          console.log(res);
          this.tableData = res.data.content;
          this.$store.commit(
            "setincome",
            JSON.parse(JSON.stringify(this.tableData))
          );
          // for(let i in this.tableData){
          //   this.tableData[i].loginPass = this.passHide(this.tableData[i].loginPass.length)
          // }
          this.pagination.totalPages = res.data.totalPages;
          this.pagination.totalSize = res.data.totalSize;
          this.currentPage = res.data.pageNum;
          this.query.pageSize = res.data.pageSize;
          this.loading = false;
        });
    },
    //密码隐藏
    passHide(num) {
      let nb = "";
      for (let i = 1; i <= num; i++) {
        nb += "*";
      }
      return nb;
    },
    //删除信息
    handleDelete(row) {
      this.$confirm("确定删除此收入?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          const inId = row.inId;
          this.$axios
            .get("/api/income/deleteIncome", { params: { inId } })
            .then((res) => {
              if (res.data == 200) {
                this.init();
                this.$message({
                  type: "success",
                  message: "删除成功!",
                });
              }
            });
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消删除",
          });
        });
    },
    //查询用户
    select() {
      const formInline = {
        name: this.formInline.name,
        actName: this.formInline.actName,
      };
      const query = {
        pageNum: this.query.pageNum,
        pageSize: this.query.pageSize,
      };
      if (formInline.name == "" && formInline.actName == "") {
        this.isSelect = false;
      } else {
        this.isSelect = true;
        this.$store.commit("setform", formInline);
      }
      this.init(query, formInline);
      this.$message({
        type: "success",
        message: "查询成功!",
      });
    },
    //数据单向绑定
    handleClick(row) {
      this.rowInfo = JSON.parse(JSON.stringify(row));
    },
    //重置
    rese() {
      const query = { pageNum: 1, pageSize: 10 };
      (this.formInline.name = ""), (this.formInline.actName = "");
      const formInline = this.formInline;
      this.init(query, formInline);
      this.isSelect = false;
      this.$message({
        type: "success",
        message: "已重置!",
      });
    },
    handleSizeChange(val) {
      console.log(`每页 ${val} 条`);
      const query = { pageNum: this.query.pageNum, pageSize: val };
      this.query = query;
      if (!this.isSelect) {
        const formInline = {
          name: "",
          loginId: "",
        };
        this.init(query, formInline);
      } else {
        const formInline = this.$store.getters.getform;
        this.init(query, formInline);
      }
    },
    handleCurrentChange(val) {
      console.log(`当前页: ${val}`);
      const query = { pageNum: val, pageSize: this.query.pageSize };
      this.query = query;
      if (!this.isSelect) {
        const formInline = {
          name: "",
          loginId: "",
        };
        this.init(query, formInline);
      } else {
        const formInline = this.$store.getters.getform;
        this.init(query, formInline);
      }
    },
  },
};
</script>

<style lang="scss" scoped>
.index {
  padding: 20px;
  background-color: #ffffff;
  .el-breadcrumb {
    margin-bottom: 20px;
  }
  .el-table {
    ::-webkit-scrollbar-track {
      background: rgba(0, 0, 0, 0.1);
      border-radius: 0;
    }

    ::-webkit-scrollbar {
      -webkit-appearance: none;
      width: 10px;
      height: 10px;
    }

    ::-webkit-scrollbar-thumb {
      cursor: pointer;
      border-radius: 5px;
      background: rgba(0, 0, 0, 0.25);
      transition: color 0.2s ease;
    }
  }
  .el-pagination {
    margin-top: 20px;
  }
}
</style>