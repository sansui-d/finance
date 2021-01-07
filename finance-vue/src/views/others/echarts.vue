<template>
  <div>
    <div>
      <el-row :gutter="12">
        <el-col :span="8">
          <el-card shadow="hover">
            <div>总用户数：{{ user.length }}</div>
            <div></div>
          </el-card>
        </el-col>
        <el-col :span="8">
          <el-card shadow="hover">
            <div>总家庭数：{{ account.length }}</div>
            <div></div>
          </el-card>
        </el-col>
        <el-col :span="8">
          <el-card shadow="hover">
            <div>系统总金额：{{ all }}</div>
            <div></div>
          </el-card>
        </el-col>
      </el-row>
    </div>
    <div class="kong">所有支出</div>
    <div class="echarts">
      <div id="myChartOne" :style="{ width: '100%', height: '400px' }"></div>
    </div>
    <div class="kong">所有支出</div>
    <div class="echarts">
      <div id="myChartTwo" :style="{ width: '100%', height: '400px' }"></div>
    </div>
  </div>
</template>

<script>
export default {
  name: "hello",
  data() {
    return {
      msg: "Welcome to Your Vue.js App",
      user: [],
      account: [],
      income: [],
      pay: [],
      all: 0,
      pie: [
        { value: 10, name: "rose1" },
        { value: 5, name: "rose2" },
        { value: 15, name: "rose3" },
        { value: 25, name: "rose4" },
      ],
      listPie: [],
    };
  },
  mounted() {
    this.init();
  },
  methods: {
    init() {
      this.$axios.get("/api/user/eCharts", {}).then((res) => {
        this.user = res.data.user;
        this.account = res.data.account;
        this.pay = res.data.pay;
        this.income = res.data.income;
        for (let i in this.account) {
          this.all += Number(this.account[i].surplus);
        }
        let list = [],
          a = 0,
          b = 0,
          c = 0,
          d = 0;
        //获取所有支出类型
        for (let j in this.pay) {
          if (!list.includes(this.pay[j].type)) {
            list.push(this.pay[j].type);
          }
        }
        for (let m in this.pay) {
          if (this.pay[m].type == list[0]) {
            a += Number(this.pay[m].money);
          } else if (this.pay[m].type == list[1]) {
            b += Number(this.pay[m].money);
          } else if (this.pay[m].type == list[2]) {
            c += Number(this.pay[m].money);
          } else if (this.pay[m].type == list[3]) {
            d += Number(this.pay[m].money);
          }
        }
        for (let x in this.pie) {
          this.pie[x].name = list[x];
        }
        this.pie[0].value = a;
        this.pie[1].value = b;
        this.pie[2].value = c;
        this.pie[3].value = d;
        this.listPie = list;
        this.drawLine();
      });
    },
    drawLine() {
      // 基于准备好的dom，初始化echarts实例
      let myChartOne = this.$echarts.init(
        document.getElementById("myChartOne")
      );
      let myChartTwo = this.$echarts.init(
        document.getElementById("myChartTwo")
      );
      // 绘制图表
      let op1 = {
        tooltip: {
          trigger: "item",
          formatter: "{a} <br/>{b} : {c} ({d}%)",
        },
        legend: {
          left: "center",
          top: "bottom",
          data: ["吃喝", "护肤", "衣物", "娱乐"],
        },
        toolbox: {
          show: true,
          feature: {
            restore: { show: true },
          },
        },
        series: [
          {
            name: "半径模式",
            type: "pie",
            radius: [20, 110],
            center: ["25%", "50%"],
            roseType: "radius",
            label: {
              show: true,
            },
            emphasis: {
              label: {
                show: true,
              },
            },
            data: [],
          },
          {
            name: "面积模式",
            type: "pie",
            radius: [30, 110],
            center: ["75%", "50%"],
            roseType: "area",
            data: [],
          },
        ],
      };
      let op2 = {
        tooltip: {
          trigger: "axis",
          axisPointer: {
            // 坐标轴指示器，坐标轴触发有效
            type: "shadow", // 默认为直线，可选为：'line' | 'shadow'
          },
        },
        legend: {
          data: ["吃喝", "护肤", "衣物", "娱乐"],
        },
        grid: {
          left: "3%",
          right: "4%",
          bottom: "3%",
          containLabel: true,
        },
        xAxis: {
          type: "value",
        },
        yAxis: {
          type: "category",
          data: ["周一", "周二", "周三", "周四", "周五", "周六", "周日"],
        },
        series: [
          {
            name: "吃喝",
            type: "bar",
            stack: "总量",
            label: {
              show: true,
              position: "insideRight",
            },
            data: [320, 302, 301, 334, 390, 330, 320],
          },
          {
            name: "护肤",
            type: "bar",
            stack: "总量",
            label: {
              show: true,
              position: "insideRight",
            },
            data: [120, 132, 101, 134, 90, 230, 210],
          },
          {
            name: "衣物",
            type: "bar",
            stack: "总量",
            label: {
              show: true,
              position: "insideRight",
            },
            data: [220, 182, 191, 234, 290, 330, 310],
          },
          {
            name: "娱乐",
            type: "bar",
            stack: "总量",
            label: {
              show: true,
              position: "insideRight",
            },
            data: [150, 212, 201, 154, 190, 330, 410],
          },
        ],
      };
      console.log(this.listPie);
      op1.series[0].data = this.pie;
      op1.series[1].data = this.pie;
      myChartOne.setOption(op1);
      myChartTwo.setOption(op2);
    },
  },
};
</script>

<style scoped>
.echarts {
  margin-top: 20px;
}
.kong {
  margin-top: 20px;
  font-size: 20px;
}
</style>>
