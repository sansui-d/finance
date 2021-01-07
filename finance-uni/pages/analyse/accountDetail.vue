<template>
	<view>
		<uni-nav-bar leftIcon="back" @clickLeft="myback" title="账户详情"></uni-nav-bar>
		<view class="pay">
			<text>账户号：{{ account.actName }}</text>
			<view class="top">
				<view class="topPay"><text>账户余额</text><text>{{ account.surplus }}</text></view>
				<view class="topIncome"><text>账户户主</text><text>{{ houseHolder }}</text></view>
				<view class="topAll"><text>账户成员</text><text>{{ user.length }}</text></view>
			</view>
			<view class="info">
				<view class="title">
					<view><text>成员</text></view>
				</view>
				<view class="content" v-for="item in user">
					<view>
						<image src="@/static/home/8.png"></image>
						<text>{{ item.name }}</text>
					</view>
					<view>
						<text>{{ account.houseHolder==item.userId?'户主':'成员' }}</text>
					</view>
				</view>
			</view>
		</view>
	</view>
</template>

<script>
	import {
		accountByAll
	} from '@/common/api/api'
	export default {
		data() {
			return {
				user:[],
				account:{},
				houseHolder:""
			}
		},
		onShow() {
			this.init()
		},
		methods: {
			init() {
				const actId = uni.getStorageSync('actId')
				accountByAll({
					actId: actId
				}).then(res => {
					console.log(res)
					this.user = res.user
					this.account = res.account[0]
					for(let i in this.user){
						if(this.user[i].userId == this.account.houseHolder){
							this.houseHolder = this.user[i].name
						}
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
				width: 79rpx;
				height: 64rpx;
			}
		}
	}
</style>
