<template>
	<view>
		<uni-nav-bar leftIcon="back" @clickLeft="myback" title="明细"></uni-nav-bar>
		<view class="pay">
			<text>所有收支</text>
			<view class="top">
				<view class="topPay"><text>支出(元)</text><text>-{{ a }}</text></view>
				<view class="topIncome"><text>收入(元)</text><text>+{{ b }}</text></view>
				<view class="topAll"><text>结余(元)</text><text>{{ m.surplus }}</text></view>
			</view>
			<view class="info">
			</view>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				a:0,
				b:0,
				m:null
			}
		},
		onShow() {
			this.init()
		},
		methods: {
			init() {
				let pay = uni.getStorageSync('pay')
				let income = uni.getStorageSync('income')
				this.m = uni.getStorageSync('account') 
				let payM = 0,
				incomeM = 0
				for(let i in pay){
					payM += Number(pay[i].money)
				}
				this.a = payM
				for(let j in income){
					incomeM += Number(income[j].money)
				}
				this.b = incomeM
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

				view {
					display: flex;
					align-items: center;

					text:nth-child(1) {
						font-size: 30rpx;
						color: #37af6b;
					}

					text:nth-child(2) {
						margin-left: 20rpx;
						font-size: 30rpx;
						color: #2a3030;
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
