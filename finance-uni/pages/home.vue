<template>
	<view>
		<view class="content">
			<text>首页</text>
			<view class="take">
				<text>本月支出（元）</text>
				<text>￥{{ moneyMpay}}</text>
			</view>
			<view class="income">
				<view>收入<text>￥{{moneyMin }}</text></view>
				<view>结余<text>￥{{ account.surplus }}</text></view>
			</view>
			<view class="list">
				<view class="listImg">
					<navigator url="./analyse/accountDetail">
						<image src="../static/home/01.png"></image>
					</navigator>
					<navigator url="./analyse/add">
						<image src="../static/home/02.png"></image>
					</navigator>
					<navigator url="./analyse/payDetail">
						<image src="../static/home/03.png"></image>
					</navigator>
					<navigator url="./analyse/budget">
						<image src="../static/home/04.png"></image>
					</navigator>
				</view>
				<view class="listText">
					<text>账户详情</text>
					<text>添加收支</text>
					<text>全部收支</text>
					<text>收支预算</text>
				</view>
			</view>
		</view>
		<view class="day">
			<view class="title">
				<view class="titleOne">
					<text>今天</text>
					<text>{{ day }}</text>
				</view>
				<view class="titleTwo">
					<view>收:<text>{{ moneyDin }}</text></view>
					<view>支:<text>{{ moneyDpay }}</text></view>
					<view>余:<text>{{ account.surplus }}</text></view>
				</view>
			</view>
			<view class="pay" v-for="(item,index) in todayPay">
				<view class="payLeft">
					<image src="../static/home/5.png"></image>
					<view>
						<text>{{ item.type }}</text>
					</view>
				</view>
				<view class="payRight">
					<text style="color: #f35d33;">-{{ item.money }}</text>
				</view>
			</view>
			<view class="pay" v-for="(it,index) in todayIncome">
				<view class="payLeft">
					<image src="../static/home/3.png"></image>
					<view>
						<text>{{ it.type }}</text>
					</view>
				</view>
				<view class="payRight">
					<text>+{{ it.money }}</text>
				</view>
			</view>
		</view>
	</view>
</template>

<script>
	import {
		getUserInfo
	} from '@/common/api/api'
	export default {
		data() {
			return {
				userId: null,
				actId: null,
				account: {},
				income: [],
				pay: [],
				user: {},
				moneyDin: null,
				moneyDpay: null,
				moneyMin: null,
				moneyMpay: null,
				todayPay: [],
				todayIncome: [],
				day: ""
			}
		},
		onShow() {
			this.todayPay = []
			this.todayIncome = []
			this.getUser();
		},
		methods: {
			//获取用户数据
			getUser() {
				this.userId = uni.getStorageSync('userId')
				this.actId = uni.getStorageSync('actId')
				if (this.actId == 0) {
					uni.showModal({
						title: '提示',
						content: '检测到您还未绑定账户，为了您的正常使用，请绑定账户',
						showCancel: true,
						cancelText: "退出",
						confirmText: "绑定账户",
						success: (res) => {
							if (res.confirm) {
								uni.navigateTo({
									url: '/pages/mine/account'
								})
							}else if (res.cancel) {
									uni.navigateTo({
										url: '/pages/login'
									})
								}
						}
					});
				} else {
					getUserInfo({
						userId: this.userId,
						actId: this.actId
					}).then(res => {
						console.log(res)
						uni.setStorage({
							key: 'user',
							data: res.user[0]
						})
						uni.setStorage({
							key: 'income',
							data: res.income
						})
						uni.setStorage({
							key: 'pay',
							data: res.pay
						})
						uni.setStorage({
							key: 'account',
							data: res.account[0]
						})
						this.user = res.user[0]
						this.pay = res.pay
						this.income = res.income
						this.account = res.account[0]
						this.computeData();
					})
				}
			},
			//计算用户数据
			computeData() {
				this.day = this.getTime()
				const year = this.getY(this.getTime())
				const month = this.getM(this.getTime())
				const day = this.getD(this.getTime())
				let moneyMin = 0,
					moneyDin = 0,
					moneyMpay = 0,
					moneyDpay = 0
				//循环遍历当月收入
				for (let i in this.income) {
					if (this.getY(this.income[i].inTime) == year) {
						if (this.getM(this.income[i].inTime) == month) {
							moneyMin += Number(this.income[i].money)
						}
					}
				}
				//循环遍历当月支出
				for (let j in this.pay) {
					if (this.getY(this.pay[j].outTime) == year) {
						if (this.getM(this.pay[j].outTime) == month) {
							moneyMpay += Number(this.pay[j].money)
						}
					}
				}
				//循环遍历当日收入
				for (let m in this.income) {
					if (this.getTime() == this.income[m].inTime) {
						moneyDin += Number(this.income[m].money)
						this.todayIncome.push(this.income[m])
					}
				}
				//循环遍历当日支出
				for (let m in this.pay) {
					if (this.getTime() == this.pay[m].outTime) {
						moneyDpay += Number(this.pay[m].money)
						this.todayPay.push(this.pay[m])
					}
				}
				this.moneyDin = moneyDin
				this.moneyDpay = moneyDpay
				this.moneyMin = moneyMin
				this.moneyMpay = moneyMpay
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
			//获取当前时间
			getTime() {
				let date = new Date();
				let year = date.getFullYear();
				let month = date.getMonth() + 1;
				let day = date.getDate();
				return year + "年" + month + "月" + day + "日";
			},
		}
	}
</script>

<style lang="scss" scoped>
	.content {
		height: 365rpx;
		background-image: url('../static/home/big.png');
		background-size: cover;
		text-align: center;
		padding: 60rpx 33rpx 0 33rpx;

		text {
			color: #ffffff;
			font-size: 42rpx;
		}

		.take {
			display: flex;
			flex-direction: column;
			text-align: left;
			margin-top: 20rpx;

			text:nth-child(1) {
				font-size: 24rpx;
			}

			text:nth-child(2) {
				font-size: 54rpx;
			}
		}

		.income {
			text-align: left;
			display: flex;
			justify-content: space-between;
			align-items: flex-end;
			margin-right: 130rpx;
			margin-top: 50rpx;
			font-size: 20rpx;
			color: #FFFFFF;

			text {
				font-size: 32rpx;
			}
		}

		.list {
			position: relative;
			background-color: #FFFFFF;
			border-radius: 10rpx;
			box-sizing: border-box;
			padding: 29rpx 43rpx;
			margin-top: 20rpx;

			.listImg {
				display: flex;
				justify-content: space-between;

				image {
					width: 92rpx;
					height: 92rpx;
				}
			}

			.listText {
				margin-top: 10rpx;
				display: flex;
				justify-content: space-between;

				text {
					font-size: 26rpx;
					color: #2a2a2a;
				}
			}
		}
	}

	.day {
		padding: 20rpx 0;
		background-color: #FFFFFF;
		margin-top: 160rpx;
		padding: 20rpx 33rpx;

		text {
			color: #2a2a2a;
		}

		.title {
			display: flex;
			justify-content: space-between;
			margin-bottom: 10rpx;

			.titleOne {
				text:nth-child(1) {
					font-size: 36rpx;
				}

				text:nth-child(2) {
					font-size: 18rpx;
					color: #6c6c6c;
					margin-left: 10rpx;
				}
			}

			.titleTwo {
				display: flex;
				align-items: flex-end;

				view {
					font-size: 20rpx;
					color: #6c6c6c;

					text {
						font-size: 20rpx;
					}
				}

				view:nth-child(1) {
					margin-right: 10rpx;

					text {
						color: #37af6b;
					}
				}

				view:nth-child(2) {
					margin-right: 10rpx;

					text {
						color: #f35d33;
					}
				}

				view:nth-child(3) {
					margin-right: 10rpx;

					text {
						color: #5eb6fa;
					}
				}
			}
		}

		.pay {
			display: flex;
			justify-content: space-between;
			align-items: center;
			padding: 10rpx 0;

			.payLeft {
				display: flex;
				align-items: center;

				view {
					display: flex;
					flex-direction: column;
					margin-left: 20rpx;

					text:nth-child(1) {
						font-size: 26rpx;
						color: #2a2a2a;
					}

					text:nth-child(2) {
						font-size: 18rpx;
						color: #a8a8a8;
					}
				}

				image {
					width: 56rpx;
					height: 48rpx;
				}
			}

			.payRight {
				text {
					font-size: 36rpx;
					color: #37af6b;
				}
			}
		}
	}
</style>
