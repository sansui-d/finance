<template>
	<view class="finance">
		<view class="top">
			<uni-segmented-control class="seg" :current="current" :values="items" @clickItem="onClickItem" style-type="text"
			 active-color="#ffcd54"></uni-segmented-control>
		</view>
		<view class="content">
			<view v-if="current === 0">
				<template v-for="i in loan">
					<view class="list">
						<text>{{ i.loanName }}</text>
						<view class="num">
							<view class="one">
								<text>{{ i.loanContent }}</text>
							</view>
							<view class="two">
								<text>{{ i.loanTime }}天后还款</text>
								<text>{{ i.loanMoney }}元</text>
							</view>
							<button @click="dai(i)">贷款</button>
						</view>
					</view>
				</template>
			</view>
			<view v-if="current === 1">
				<template v-for="j in insure">
					<view class="list">
						<text>{{ j.sureTitle }}</text>
						<view class="num">
							<view class="one">
								<text>{{ j.sureContent }}</text>
							</view>
							<view class="two">
								<text>{{ j.sureTime }}天</text>
								<text>{{ j.sureMoney }}元</text>
							</view>
							<button @click="buy(j)">购买</button>
						</view>
					</view>
				</template>
			</view>
		</view>
	</view>
</template>

<script>
	import {
		getLoan,
		getInsure,
		addDebt
	} from '@/common/api/api';
	import uniSegmentedControl from "@/components/uni-segmented-control/uni-segmented-control.vue"
	export default {
		components: {
			uniSegmentedControl
		},
		data() {
			return {
				items: ['贷款', '保险'],
				current: 0,
				loan: [],
				insure: []
			}
		},
		onShow() {
			this.getLoan()
			this.getInsure()
		},
		methods: {
			onClickItem(e) {
				if (this.current !== e.currentIndex) {
					this.current = e.currentIndex;
				}
			},
			getLoan() {
				getLoan({}).then(res => {
					this.loan = res
				})
			},
			getInsure() {
				getInsure({}).then(res => {
					this.insure = res
					console.log(res)
				})
			},
			dai(i) {
				let debt = {}
				debt.surplus = Number(uni.getStorageSync('account').surplus) + i.loanMoney
				debt.userId = uni.getStorageSync('userId')
				debt.actId = uni.getStorageSync('actId')
				debt.debtName = i.loanName
				debt.type = i.type
				debt.debtMoney = i.loanMoney
				debt.debtTime = this.getTime(i.loanTime)
				uni.showModal({
					title: '提示',
					content: '确定贷款此项目？',
					success: function(res) {
						if (res.confirm) {
							addDebt({
								userId: debt.userId,
								actId: debt.actId,
								debtName: debt.debtName,
								type: debt.type,
								debtMoney: debt.debtMoney,
								debtTime: debt.debtTime,
								surplus: debt.surplus
							}).then(res => {
								if (res == 200) {
									let x = uni.getStorageSync('account')
									x.surplus = debt.surplus
									uni.setStorage({
										key: 'account',
										data: x
									})
									uni.showToast({
										title: '贷款成功',
										duration: 2000
									});
								} else {
									uni.showToast({
										title: '贷款失败',
										duration: 2000
									});
								}
							})
						} else if (res.cancel) {
							console.log('用户点击取消');
						}
					}
				});
			},
			buy(j) {
				let debt = {}
				debt.surplus = Number(uni.getStorageSync('account').surplus) - j.sureMoney
				debt.userId = uni.getStorageSync('userId')
				debt.actId = uni.getStorageSync('actId')
				debt.debtName = j.sureTitle
				debt.type = j.type
				debt.debtMoney = j.sureMoney
				debt.debtTime = this.getTime(j.sureTime)
				const a = Number(uni.getStorageSync('account').surplus)
				const b = j.sureMoney
				uni.showModal({
					title: '提示',
					content: '确定购买此项目？',
					success: function(res) {
						if (res.confirm) {
							if (a > b) {
								addDebt({
									userId: debt.userId,
									actId: debt.actId,
									debtName: debt.debtName,
									type: debt.type,
									debtMoney: debt.debtMoney,
									debtTime: debt.debtTime,
									surplus: debt.surplus
								}).then(res => {
									if (res == 200) {
										let x = uni.getStorageSync('account')
										x.surplus = debt.surplus
										uni.setStorage({
											key: 'account',
											data: x
										})
										uni.showToast({
											title: '购买成功',
											duration: 2000
										});
									} else {
										uni.showToast({
											title: '购买失败',
											duration: 2000
										});
									}
								})
							} else {
								uni.showToast({
									title: '余额不足',
									duration: 2000
								});
							}
						} else if (res.cancel) {
							console.log('用户点击取消');
						}
					}
				});
			},
			getTime(time) {
				let date = new Date();
				date.setTime(date.getTime() + 3600 * 1000 * 24 * time);
				let year = date.getFullYear();
				let month = date.getMonth() + 1;
				let day = date.getDate();
				return year + "年" + month + "月" + day + "日";
			},
		}
	}
</script>

<style scoped lang="scss">
	.finance {
		padding: 0 32rpx;
		background-color: #FFFFFF;

		.top {
			padding: 50rpx 200rpx 0 200rpx;
		}

		.content {
			margin-top: 40rpx;

			.list {
				display: flex;
				flex-direction: column;

				text {
					font-size: 30rpx;
					color: #323232;
				}

				.num {
					padding: 40rpx 0;
					display: flex;

					view {
						display: flex;
						flex-direction: column;
					}

					.one {
						justify-content: space-around;

						text:nth-child(1) {
							font-size: 24rpx;
							color: #333333;
						}

						text:nth-child(2) {
							font-size: 24rpx;
							color: #999999;
						}
					}

					.two {
						justify-content: flex-end;
						margin-left: 40rpx;

						text:nth-child(1) {
							font-size: 30rpx;
							color: #333333;
							margin-bottom: 15rpx;
						}

						text:nth-child(2) {
							font-size: 24rpx;
							color: #999999;
						}
					}

					button {
						width: 160rpx;
						height: 72rpx;
						line-height: 72rpx;
						font-size: 30rpx;
						color: #ffffff;
						margin-right: 0rpx;
						margin-top: 20rpx;
						background-color: #ffcd54;
					}
				}
			}
		}
	}
</style>
