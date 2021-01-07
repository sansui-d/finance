<template>
	<view>
		<view class="top">
			<text>分析</text>
			<picker mode=multiSelector @change="bindPickerChange" :value="index" :range="array" class="pic">
				<view class="ViewFlex">
					<view>年：{{array[0][index[0]]}}</view>
					<view>月：{{array[1][index[1]]}}</view>
				</view>
			</picker>
			<view class="echarts">
				<radio-group @change="changeRadio">
					<view>
						<radio checked color="#ffcd54" value="收入" style="transform:scale(0.7)">收入</radio>
					</view>
					<view>
						<radio color="#ffcd54" value="支出" style="transform:scale(0.7)">支出</radio>
					</view>
				</radio-group>
				<view class="qiun-charts">
					<canvas canvas-id="canvasColumn" id="canvasColumn" class="charts" @touchstart="touchColumn"></canvas>
				</view>
			</view>
		</view>
		<view class="list">
			<view class="title">
				<text>本月支出</text>
				<text>01.2020</text>
			</view>
		</view>
		<view class="pay">
			<view class="left">
				<image src="@/static/home/chihe.png"></image>
				<view>
					<text>吃喝</text>
					<text>共{{ chi.length }}笔</text>
				</view>
			</view>
			<view class="right">
				<text>{{ chiM==null?0:chiM }}</text>
				<view>
					<text>{{ a!==NaN?a:0 }}%</text>
					<progress :percent="a!==NaN?a:0" active stroke-width="3" activeColor="#5d9dfb" />
				</view>
			</view>
		</view>
		<view class="pay">
			<view class="left">
				<image src="@/static/home/huhu.png"></image>
				<view>
					<text>护肤</text>
					<text>共{{ fu.length }}笔</text>
				</view>
			</view>
			<view class="right">
				<text>{{ fuM==null?0:fuM }}</text>
				<view>
					<text>{{ b!==NaN?b:0 }}%</text>
					<progress :percent="b!==NaN?b:0" active stroke-width="3" activeColor="#5d9dfb" />
				</view>
			</view>
		</view>
		<view class="pay">
			<view class="left">
				<image src="@/static/home/chaoshi.png"></image>
				<view>
					<text>衣物</text>
					<text>共{{ yi.length }}笔</text>
				</view>
			</view>
			<view class="right">
				<text>{{ yiM==null?0:yiM }}</text>
				<view>
					<text>{{ c!==NaN?c:0 }}%</text>
					<progress :percent="c!==NaN?c:0" active stroke-width="3" activeColor="#5d9dfb" />
				</view>
			</view>
		</view>
		<view class="pay">
			<view class="left">
				<image src="@/static/home/juhui.png"></image>
				<view>
					<text>娱乐</text>
					<text>共{{ wan.length }}笔</text>
				</view>
			</view>
			<view class="right">
				<text>{{ wanM==null?0:wanM }}</text>
				<view>
					<text>{{ d!==NaN?d:0 }}%</text>
					<progress :percent="d!==NaN?d:0" active stroke-width="3" activeColor="#5d9dfb" />
				</view>
			</view>
		</view>
	</view>
</template>

<script>
	import uCharts from '@/components/u-charts/u-charts.js';
	var _self;
	var canvaColumn = null;
	var canvaRing = null;
	export default {
		components: {
			uCharts,
		},
		watch: {
			index: {
				handler(newName, oldName) {
					if (this.isValue == '收入') {
						let a = this.getAll(this.array[0][this.index[0]], this.index[1] + 1)
						this.chartData.categories = a.day
						this.chartData.series[0].data = a.money
						this.cWidth = uni.upx2px(650);
						this.cHeight = uni.upx2px(500);
						this.getServerData();
					}else{
						let b = this.getAllPay(this.array[0][this.index[0]], this.index[1] + 1)
						this.chartData.categories = b.day
						this.chartData.series[0].data = b.money
						this.cWidth = uni.upx2px(650);
						this.cHeight = uni.upx2px(500);
						this.getServerData();
					}
				},
				deep: true,
			},
		},
		data() {
			return {
				array: [
					[],
					[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12]
				],
				index: [0, 0],
				isValue: '收入',
				updateStatus: false,
				updateStatus: false,
				cWidth: '',
				cHeight: '',
				pixelRatio: 1,
				serverData: '',
				chartData: {
					categories: [],
					series: [{
						name: "收入",
						data: [],
						color: '#ffcd54'
					}]
				},
				user: {},
				pay: [],
				income: [],
				account: {},
				allYm: [
					[],
					[]
				],
				//每日收入
				allMoneyIncome: [],
				//每日支出
				allMoneyPay: [],
				//收入日期
				dayIncome: [],
				//支出日期
				dayPay: [],
				///////////////
				yearIncome: [],
				yearPay: [],
				chi:[],
				fu:[],
				yi:[],
				wan:[],
				payMoney:null,
				chiM:null,
				fuM:null,
				yiM:null,
				wanM:null,
				a:0,
				b:0,
				c:0,
				d:0
			}
		},
		onShow() {
			this.array[0] = []
			this.chi = []
			this.fu=[]
			this.yi =[]
			this.wan= []
			this.payMoney = null
			this.chiM = null
			this.fuM = null
			this.yiM = null
			this.wanM = null
			this.a = 0
			this.b = 0
			this.c = 0
			this.d = 0
			_self = this;
			this.getUser()
			this.cWidth = uni.upx2px(650);
			this.cHeight = uni.upx2px(500);
			this.getServerData();
			console.log(this.array[1])
		},
		methods: {
			changeRadio(evt) {
				this.isValue = evt.target.value
				if (this.isValue == '收入') {
					let a = this.getAll(this.array[0][this.index[0]], this.index[1] + 1)
					this.chartData.categories = a.day
					this.chartData.series[0].data = a.money
					this.cWidth = uni.upx2px(650);
					this.cHeight = uni.upx2px(500);
					this.getServerData();
				} else {
					let b = this.getAllPay(this.array[0][this.index[0]], this.index[1] + 1)
					this.chartData.categories = b.day
					this.chartData.series[0].data = b.money
					this.cWidth = uni.upx2px(650);
					this.cHeight = uni.upx2px(500);
					this.getServerData();
				}
			},
			//用户数据
			getUser() {
				this.user = uni.getStorageSync('user')
				this.pay = uni.getStorageSync('pay')
				this.income = uni.getStorageSync('income')
				this.account = uni.getStorageSync('account')
				//获取所有年份
				for (let i in this.income) {
					if (!this.array[0].includes(this.getY(this.income[i].inTime))) {
						this.array[0].push(this.getY(this.income[i].inTime))
					}
				}
				console.log(this.array)
				let time = this.getTime()
				let a = this.getAll(this.getY(time), this.getM(time))
				this.index = [this.array[0].length-1,this.getM(time)-1]
				this.chartData.categories = a.day
				this.chartData.series[0].data = a.money
				//当月支出情况
				let now = this.getTime()
				for(let x in this.pay){
					if(this.getY(this.pay[x].outTime)==this.getY(now)){
						if(this.getM(this.pay[x].outTime)==this.getM(now)){
							if(this.pay[x].type=='吃喝'){
								this.chi.push(this.pay[x])
								this.chiM += Number(this.pay[x].money)
							}
							if(this.pay[x].type=='护肤'){
								this.fu.push(this.pay[x])
								this.fuM += Number(this.pay[x].money)
							}
							if(this.pay[x].type=='衣物'){
								this.yi.push(this.pay[x])
								this.yiM += Number(this.pay[x].money)
							}
							if(this.pay[x].type=='娱乐'){
								this.wan.push(this.pay[x])
								this.wanM += Number(this.pay[x].money)
							}
							this.payMoney += Number(this.pay[x].money)
						}
					}
				}
				this.a = Math.round(this.chiM/this.payMoney*100)
				this.b = Math.round(this.fuM/this.payMoney*100)
				this.c = Math.round(this.yiM/this.payMoney*100)
				this.d = Math.round(this.wanM/this.payMoney*100)
			},
			//获取当前时间
			getTime() {
				let date = new Date();
				let year = date.getFullYear();
				let month = date.getMonth() + 1;
				let day = date.getDate();
				return year + "年" + month + "月" + day + "日";
			},
			//根据年月查天
			getAll(y, m) {
				let obj = {}
				let money = [],
					day = []
				for (let i in this.income) {
					if (this.getY(this.income[i].inTime) == y &&
						this.getM(this.income[i].inTime) == m) {
						if (!day.includes(this.getD(this.income[i].inTime))) {
							day.push(this.getD(this.income[i].inTime))
							money.push(this.getMoneyIncome(this.income[i].inTime))
						}
					}
				}
				obj['money'] = money
				obj['day'] = day
				this.chartData.series[0].name = '收入'
				return obj
			},
			getAllPay(y, m) {
				let obj = {}
				let money = [],
					day = []
				for (let i in this.pay) {
					if (this.getY(this.pay[i].outTime) == y &&
						this.getM(this.pay[i].outTime) == m) {
						if (!day.includes(this.getD(this.pay[i].outTime))) {
							day.push(this.getD(this.pay[i].outTime))
							money.push(this.getMoneyPay(this.pay[i].outTime))
						}
					}
				}
				obj['money'] = money
				obj['day'] = day
				this.chartData.series[0].name = '支出'
				return obj
			},
			//循环遍历日收入
			getMoneyIncome(m) {
				let all = 0
				for (let i in this.income) {
					if (m == this.income[i].inTime) {
						all += Number(this.income[i].money)
					}
				}
				return all
			},
			//循环遍历日支出
			getMoneyPay(m) {
				let all = 0
				for (let i in this.pay) {
					if (m == this.pay[i].outTime) {
						all += Number(this.pay[i].money)
					}
				}
				return all
			},
			//截取年份
			getY(m) {
				return m.slice(0, 4);
			},
			//截取月份
			getM(m) {
				return m.substring(m.indexOf("年") + 1, m.indexOf("月"))
			},
			//截取日期
			getD(m) {
				return m.substring(m.indexOf("月") + 1, m.indexOf("日"))
			},

			bindPickerChange: function(f) {
				console.log('picker发送选择改变，携带值为', f.target.value)
				this.index = f
					.target.value
			},
			getServerData() {
				_self.showColumn("canvasColumn", this.chartData);
			},
			showColumn(canvasId, chartData) {
				canvaColumn = new uCharts({
					$this: _self,
					canvasId: canvasId,
					type: 'column',
					legend: {
						show: true
					},
					fontSize: 11,
					background: '#FFFFFF',
					pixelRatio: _self.pixelRatio,
					animation: true,
					categories: chartData.categories,
					series: chartData.series,
					xAxis: {
						disableGrid: true,
					},
					yAxis: {
						//disabled:true
					},
					dataLabel: true,
					width: _self.cWidth * _self.pixelRatio,
					height: _self.cHeight * _self.pixelRatio,
					extra: {
						column: {
							type: 'group',
							width: _self.cWidth * _self.pixelRatio * 0.45 / chartData.categories.length
						}
					}
				});

			},
			touchColumn(e) {
				canvaColumn.showToolTip(e, {
					format: function(item, category) {
						if (typeof item.data === 'object') {
							return category + ' ' + item.name + ':' + item.data.value
						} else {
							return category + ' ' + item.name + ':' + item.data
						}
					}
				});
			}
		}
	}
</script>

<style lang="scss" scoped>
	.top {
		background-color: #ffcd54;
		text-align: center;
		height: 310rpx;
		padding: 70rpx 32rpx 0 32rpx;
		box-sizing: border-box;

		text {
			font-size: 42rpx;
			color: #ffffff;
		}

		.pic {
			.ViewFlex {
				display: flex;
				justify-content: space-around;

				view {
					display: inline-block;
					color: #FFFFFF;
				}
			}
		}

		.echarts {
			background-color: #FFFFFF;
			height: 456rpx;
			margin-top: 35rpx;
			border-radius: 20rpx;
			padding: 27rpx 0;

			radio-group {
				display: flex;
				justify-content: space-around;

				view {
					display: flex;
					flex-direction: column;

					text {
						font-size: 30rpx;
						color: #050505;
					}
				}
			}

			.qiun-charts {
				height: 500rpx;
				background-color: #FFFFFF;
			}

			.charts {
				width: 100%;
				height: 500rpx;
				background-color: #FFFFFF;
			}

		}
	}

	.list {
		display: flex;
		justify-content: space-between;
		margin-top: 550rpx;
		padding: 0 32rpx;
		margin-bottom: 20rpx;

		.title {
			display: flex;
			align-items: flex-end;

			text:nth-child(1) {
				font-size: 34rpx;
				color: #202020;
			}

			text:nth-child(2) {
				font-size: 20rpx;
				color: #afafaf;
				margin-left: 20rpx;
			}
		}

		.more {
			text {
				font-size: 18rpx;
				color: #7f7f7f;
			}
		}
	}

	.pay {
		background-color: #FFFFFF;
		margin: 0 32rpx;
		padding: 30rpx 40rpx;
		display: flex;
		justify-content: space-between;
		border-radius: 20rpx;
		margin-bottom: 20rpx;

		.left {
			display: flex;
			align-items: center;

			image {
				width: 55rpx;
				height: 55rpx;
			}

			view {
				margin-left: 20rpx;
				display: flex;
				flex-direction: column;

				text: nth-child(1) {
					font-size: 26rpx;
					color: #121212;
				}

				text:nth-child(2) {
					font-size: 18rpx;
					color: #aaaaaa;
				}
			}
		}

		.right {
			display: flex;
			flex-direction: column;

			view {
				display: flex;
				justify-content: space-between;
				width: 200rpx;

				progress {
					width: 100%;
					margin-left: 10rpx;
				}

				text {
					font-size: 18rpx;
					color: #adadad;
				}
			}

			text {
				font-size: 30rpx;
				color: #5d9dfb;
				text-align: right;
			}
		}
	}
</style>
