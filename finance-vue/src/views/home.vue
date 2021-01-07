<template>
  <div class="home">
    <el-container class="cont">
      <el-aside width="width">
        <div class="title">
          <img src="../assets/icon/yezi.png" />
          <div v-if="!isCollapse">家财万贯理财系统后台</div>
        </div>
        <el-menu
          router
          class="el-menu-vertical-demo"
          @open="handleOpen"
          @close="handleClose"
          :collapse="isCollapse"
          background-color="#545c64"
          text-color="#ffffff"
          :default-active="$route.path"
        >
          <el-menu-item index="/home">
            <i class="el-icon-s-home"></i>
            <span slot="title">系统首页</span>
          </el-menu-item>
          <el-menu-item index="/user">
            <i class="el-icon-user"></i>
            <span slot="title">用户管理</span>
          </el-menu-item>
          <el-menu-item index="/account">
            <i class="el-icon-folder"></i>
            <span slot="title">账户管理</span>
          </el-menu-item>
          <el-submenu index="1">
            <template slot="title">
              <i class="el-icon-bank-card"></i>
              <span slot="title">收支管理</span>
            </template>
            <el-menu-item index="/income">
              <i class="el-icon-plus"></i>
              <span slot="title">收入管理</span>
            </el-menu-item>
            <el-menu-item index="/pay">
              <i class="el-icon-minus"></i>
              <span slot="title">支出管理</span>
            </el-menu-item>
          </el-submenu>
          <el-submenu index="2">
            <template slot="title">
              <i class="el-icon-data-board"></i>
              <span slot="title">理财管理</span>
            </template>
            <el-menu-item index="/insure">
              <i class="el-icon-data-line"></i>
              <span slot="title">保险管理</span>
            </el-menu-item>
            <el-menu-item index="/loan">
              <i class="el-icon-data-analysis"></i>
              <span slot="title">贷款管理</span>
            </el-menu-item>
          </el-submenu>
          <el-menu-item index="/budget">
            <i class="el-icon-pie-chart"></i>
            <span slot="title">收支预算</span>
          </el-menu-item>
          <el-menu-item index="/debt">
            <i class="el-icon-connection"></i>
            <span slot="title">债务管理</span>
          </el-menu-item>
        </el-menu>
      </el-aside>
      <el-container>
        <el-header>
          <el-radio-group v-model="isCollapse">
            <el-radio-button :label="false">展开</el-radio-button>
            <el-radio-button :label="true">收起</el-radio-button>
          </el-radio-group>
          <div>
            你好，{{ admin
            }}<el-button type="text" @click="back">退出</el-button>
          </div>
        </el-header>
        <el-main>
          <router-view />
          <el-footer>家庭理财系统后台</el-footer>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>
import { getadmin } from "@/cookie/cookie.js";
export default {
  data() {
    return {
      isCollapse: false,
      admin: "",
    };
  },
  mounted() {
    this.admin = getadmin();
  },
  methods: {
    handleOpen(key, keyPath) {
      console.log(key, keyPath);
    },
    handleClose(key, keyPath) {
      console.log(key, keyPath);
    },
    back() {
      this.$confirm("是否退出?", "提示", {
        confirmButtonText: "退出",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          this.$router.push({
            name: "login",
          });
        })
        .catch(() => {});
    },
  },
};
</script>

<style scoped lang="scss">
.home {
  height: 100%;
}
.el-aside {
  background-color: #545c64;
}
.cont {
  height: 100%;
  width: 100%;
}
.title {
  background-color: #545c64;
  color: #ffffff;
  padding: 20px 0;
  font-size: 20px;
  display: flex;
  justify-content: center;
}
.el-menu {
  border: none;
  .el-menu-item {
    height: 56px;
    line-height: 56px;
    padding: 0 20px;
  }
}
.el-menu-vertical-demo:not(.el-menu--collapse) {
  width: 260px;
}
.el-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}
.el-main {
  background-color: #f7f7f7;
  padding-bottom: 0;
  .el-footer {
    display: flex;
    align-items: center;
    justify-content: center;
    color: rgba(0, 0, 0, 0.65);
    font-size: 14px;
  }
}

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
</style>