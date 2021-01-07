<template>
	<view>
		<uni-nav-bar leftIcon="back" @clickLeft="myback" title="预算设置"></uni-nav-bar>
		<view class="budget">
			<view class="b1">{{ all!==NaN?all:0 }}</view>
			<view class="b2"><text>支出总预算</text><text>已用：{{ pay!==NaN?pay:0 }}</text></view>
			<view class="b3"><progress :percent="bili==NaN?0:bili" active show-info stroke-width="50" activeColor="#FFFFFF"
				 backgroundColor="#ffd776" /></view>
			<view class="b4"><text>0%</text><text>100%</text></view>
		</view>
		<view class="info">
			<image src="@/static/home/chihe.png"></image>
			<view class="pp">
				<view><text>吃喝</text><text>预算余额 {{ a }}</text></view>
				<progress :percent="isNaN(q)?0:q" active activeColor="#ffcd54"></progress>
				<text>支出预算 {{ chi }}</text>
			</view>
		</view>
		<view class="info">
			<image src="@/static/home/huhu.png"></image>
			<view class="pp">
				<view><text>护肤</text><text>预算余额 {{b}}</text></view>
				<progress :percent="isNaN(w)?0:w" active activeColor="#ffcd54"></progress>
				<text>支出预算 {{ fu }}</text>
			</view>
		</view>
		<view class="info">
			<image src="@/static/home/chaoshi.png"></image>
			<view class="pp">
				<view><text>衣物</text><text>预算余额 {{c}}</text></view>
				<progress :percent="isNaN(e)?0:e" active activeColor="#ffcd54"></progress>
				<text>支出预算 {{ yi }}</text>
			</view>
		</view>
		<view class="info">
			<image src="@/static/home/juhui.png"></image>
			<view class="pp">
				<view><text>娱乐</text><text>预算余额 {{d}}</text></view>
				<progress :percent="isNaN(r)?0:r" active activeColor="#ffcd54"></progress>
				<text>支出预算 {{ wan }}</text>
			</view>
		</view>
		<button class="btn" @click="btn">添加预算</button>
	</view>
</template>

<script>
	import {
		selectBudget
	} from '@/common/api/api'
	export default {
		data() {
			return {
				all: 0,
				pay: 0,
				bili: 0,
				chi: 0,
				yi: 0,
				wan: 0,
				fu: 0,
				a: 0,
				b: 0,
				c: 0,
				d: 0,
				q: 0,
				w: 0,
				e: 0,
				r: 0
			}
		},
		onShow() {
			this.init()
		},
		methods: {
			init() {
				let userId = uni.getStorageSync('userId')
				let actId = uni.getStorageSync('actId')
				let pay = uni.getStorageSync('pay')
				let payAll = 0,
					x = 0,
					y = 0,
					m = 0,
					n = 0
				for (let j in pay) {
					payAll += Number(pay[j].money)
					this.pay = payAll
					if (pay[j].type == '吃喝') {
						x += Number(pay[j].money)
					}
					if (pay[j].type == '护肤') {
						y += Number(pay[j].money)
					}
					if (pay[j].type == '衣物') {
						m += Number(pay[j].money)
					}
					if (pay[j].type == '娱乐') {
						n += Number(pay[j].money)
					}
				}
				selectBudget({
					userId: userId,
					actId: actId,
				}).then(res => {
					console.log(res)
					if (res.length !== 0) {
						let all = 0,
							a = 0,
							b = 0,
							c = 0,
							d = 0
						for (let i in res) {
							all += Number(res[i].money)
							this.all = all
							if (res[i].type == '吃喝') {
								a += Number(res[i].money)
							}
							if (res[i].type == '护肤') {
								b += Number(res[i].money)
							}
							if (res[i].type == '衣物') {
								c += Number(res[i].money)
							}
							if (res[i].type == '娱乐') {
								d += Number(res[i].money)
							}
						}
						this.bili = parseInt(this.pay / this.all * 100)
						this.chi = a
						this.fu = b
						this.yi = c
						this.wan = d
						console.log(y)
						this.a = a - x
						this.b = b - y
						this.c = c - m
						this.d = d - n
							this.q = parseInt(this.a / a * 100)
							this.w = parseInt(this.b / b * 100)
						this.e = parseInt(this.c / c * 100)
						this.r = parseInt(this.d / d * 100)
					}
				})
			},
			btn() {
				uni.navigateTo({
					url: '/pages/analyse/addBudget'
				})
			}
		}
	}
</script>

<style scoped lang="scss">
	/deep/ .uni-progress-bar {
		border-radius: 10px;
	}

	/deep/ .uni-progress-inner-bar {
		border-radius: 10px;
	}

	/deep/ uni-button:after {
		border: none;
	}

	.budget {
		height: 384rpx;
		background-color: #ffcd54;
		margin: 20rpx 24rpx 0 24rpx;
		border-radius: 10rpx;
		box-shadow: 0rpx 0rpx 18rpx 0rpx rgba(235, 144, 12, 0.75);
		padding: 70rpx 39rpx 36rpx 39rpx;
		box-sizing: border-box;

		.b1 {
			font-size: 46rpx;
			color: #ffffff;
		}

		.b2 {
			font-size: 26rpx;
			color: #ffffff;
			display: flex;
			justify-content: space-between;
		}

		.b3 {
			margin-top: 50rpx;

			/deep/ .uni-progress-info {
				margin: 0;
				position: relative;
				bottom: -70rpx;
				left: -300rpx;
				font-size: 26rpx;
				color: #ffffff;
			}

			progress {
				margin-left: 40rpx;
			}
		}

		.b4 {
			font-size: 26rpx;
			color: #ffffff;
			display: flex;
			justify-content: space-between;
		}
	}

	.info {
		padding: 0 30rpx;
		margin-top: 54rpx;
		display: flex;
		flex-wrap: nowrap;
		align-items: center;

		image {
			width: 55rpx;
			height: 55rpx;
		}

		.pp {
			width: 100%;
			margin-left: 8rpx;

			.uni-progress {
				margin: 14rpx 0;
			}

			view {
				display: flex;
				justify-content: space-between;

				text:nth-child(1) {
					font-size: 28rpx;
					color: #585858;
				}

				text:nth-child(2) {
					font-size: 26rpx;
					color: #b5b5b5;
				}
			}

			text {
				font-size: 26rpx;
				color: #b5b5b5;
			}
		}
	}

	.btn {
		width: 680rpx;
		height: 100rpx;
		line-height: 100rpx;
		border-radius: 6rpx;
		border: solid 2rpx #ffcd54;
		font-size: 32rpx;
		color: #ffcd54;
		margin-top: 50rpx;
	}
</style>
