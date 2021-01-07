<template>
	<view>
		<uni-nav-bar leftIcon="back" @clickLeft="back" title="我的债务"></uni-nav-bar>
		<view class="pay">
			<text>欠债：{{ all}}</text>
			<view class="top">
				<view class="topPay"><text>姓名</text><text>{{ user.name }}</text></view>
				<view class="topIncome"><text>账户</text><text>{{ account.actName }}</text></view>
				<view class="topAll"><text>欠债项数</text><text>{{ debt.length }}</text></view>
			</view>
			<view class="info">
				<view class="title">
					<view><text>欠债项</text></view>
				</view>
				<view class="content" v-for="item in debt">
					<view class="c1">
						<text>{{ item.debtName }}</text>
						<text>{{ item.debtMoney }}</text>
					</view>
					<view class="c2">
						<button type="primary" class="btn" @click="btn(item)">还款</button>
					</view>
				</view>
			</view>
		</view>
	</view>
</template>

<script>
	import {
		userDebt,
		deleteDebt
	} from '@/common/api/api'
	export default {
		data() {
			return {
				debt: [],
				account: {},
				user: {},
				all: 0,
				surplus: null
			}
		},
		onShow() {
			this.init()
		},
		methods: {
			init() {
				const userId = uni.getStorageSync('userId')
				const actId = uni.getStorageSync('actId')
				this.account = uni.getStorageSync('account')
				this.user = uni.getStorageSync('user')
				userDebt({
					userId: userId,
					actId: actId
				}).then(res => {
					for (let i in res) {
						if (res[i].type == '贷款') {
							this.debt.push(res[i])
						}
					}
					for (let j in this.debt) {
						this.all += Number(this.debt[j].debtMoney)
					}
				})
			},
			btn(i) {
				let debtId = i.debtId
				let actId = uni.getStorageSync('actId')
				let m = Number(this.account.surplus)
				let n = i.debtMoney
				uni.showModal({
					title: '提示',
					content: '确定还款？',
					success: function(res) {
						if (res.confirm) {
							if (m < n) {
								uni.showToast({
									title: '余额不足',
									duration: 2000
								});
							} else {
								 this.surplus = m - n
								deleteDebt({
									debtId: debtId,
									actId: actId,
									surplus: this.surplus
								}).then(res => {
									if (res == 200) {
										uni.showToast({
											title: '还款成功',
											duration: 2000
										});
										this.account = uni.getStorageSync('account')
										this.account.surplus = this.surplus
										uni.setStorage({
											key: 'account',
											data: this.account
										})
										location.reload()
									}
								})
							}
						} else if (res.cancel) {
							console.log('用户点击取消');
						}
					}
				});

			},
			back(){
				uni.reLaunch({
				    url: '/pages/mine/mine'
				});
			}
		}
	}
</script>

<style scoped lang="scss">
	/deep/ .uni-navbar--border {
		border: none;
		height: 270rpx;
		background-color: #ffcd54 !important;

		.uni-navbar__content_view {
			background-color: #ffcd54 !important;
		}

		span {
			color: #FFFFFF;
		}
	}

	.pay {
		background-color: #FFFFFF;
		margin: 0 39rpx;
		position: relative;
		top: -180rpx;
		display: flex;
		flex-direction: column;
		padding: 39rpx 30rpx 0 30rpx;
		border-radius: 10rpx;

		text {
			font-size: 36rpx;
			color: #29282b;
		}

		.top {
			display: flex;
			justify-content: space-between;
			padding-bottom: 20rpx;
			border-bottom: 1rpx solid #f7f7f7;
			text-align: center;

			view {
				display: flex;
				flex-direction: column;
				margin-top: 24rpx;
			}

			.topPay {
				text:nth-child(1) {
					font-size: 21rpx;
					color: #bebebe;
				}

				text:nth-child(2) {
					font-size: 30rpx;
					color: #37af6b;
					margin-top: 10rpx;
				}
			}

			.topIncome {
				text:nth-child(1) {
					font-size: 21rpx;
					color: #bebebe;
				}

				text:nth-child(2) {
					font-size: 30rpx;
					color: #f35d33;
					margin-top: 10rpx;
				}
			}

			.topAll {
				text:nth-child(1) {
					font-size: 21rpx;
					color: #bebebe;
				}

				text:nth-child(2) {
					font-size: 30rpx;
					color: #2a3030;
					margin-top: 10rpx;
				}
			}
		}

		.info {
			padding: 10rpx 0 30rpx 0;

			.title {
				display: flex;
				justify-content: space-between;

				text {
					font-size: 24rpx;
					color: #bebebe;
				}
			}

			.content {
				display: flex;
				justify-content: space-between;
				margin-top: 25rpx;

				.c1 {
					display: flex;
					align-items: center;

					text:nth-child(1) {
						font-size: 30rpx;
						color: #877bfc;
					}

					text:nth-child(2) {
						font-size: 22rpx;
						color: #b8b8b8;
						margin-left: 30rpx;
					}
				}

				.c2 {
					display: flex;
					align-items: center;

					.btn {
						background-color: #ffcd54;
						height: 60rpx;
						line-height: 60rpx;
						font-size: 20rpx;
					}
				}
			}

			image {
				width: 75rpx;
				height: 75rpx;
			}
		}
	}
</style>
