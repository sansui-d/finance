<template>
	<view>
		<uni-nav-bar leftIcon="back" @clickLeft="myback" title="我的贷款"></uni-nav-bar>
		<view class="pay">
			<text>贷款金额：{{ all }}</text>
			<view class="top">
				<view class="topPay"><text>姓名</text><text>{{ user.name }}</text></view>
				<view class="topIncome"><text>账户</text><text>{{ account.actName }}</text></view>
				<view class="topAll"><text>贷款数</text><text>{{ debt.length }}</text></view>
			</view>
			<view class="info">
				<view class="title">
					<view><text>贷款项</text></view>
				</view>
				<view class="content" v-for="item in debt">
					<view class="c1">
						<text>{{ item.debtName }}</text>
						<text>{{ item.debtMoney }}</text>
					</view>
					<view class="c2">
						<text>还款日期{{ item.debtTime }}</text>
					</view>
				</view>
			</view>
		</view>
	</view>
</template>

<script>
	import {
		userDebt
	} from '@/common/api/api'
	export default {
		data() {
			return {
				debt: [],
				account:{},
				user:{},
				all:0
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
					for(let i in res){
						if(res[i].type=='贷款'){
							this.debt.push(res[i])
						}
					}
					for(let j in this.debt){
						this.all += Number(this.debt[j].debtMoney)
					}
				})
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
						color: #ffcd54;
					}

					text:nth-child(2) {
						font-size: 22rpx;
						color: #f35d33;
						margin-left: 30rpx;
					}
				}

				.c2 {
					display: flex;
					align-items: center;

					text {
						font-size: 30rpx;
						color: #877bfc;
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
