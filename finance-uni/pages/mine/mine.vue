<template>
	<view>
		<view class="top">
			<text>个人中心</text>
		</view>
		<view class="content">
			<view class="conTop">
				<view class="msg">
					<image src="../../static/home/8.png"></image>
					<text>{{ name }}</text>
					<text>这个人很懒什么都没有留下~</text>
				</view>

				<image src="../../static/mine/纽扣.png" class="imgOne"></image>
				<image src="../../static/mine/纽扣.png" class="imgTwo"></image>
			</view>
			<view class="conBom">
				<view>
					<navigator url="./myLoan">
						<image src="../../static/mine/mine01.png"></image>
					</navigator>
					<text>我的贷款</text>
				</view>
				<view>
					<navigator url="./myInsure">
						<image src="../../static/mine/mine02.png"></image>
					</navigator>
					<text>我的保险</text>
				</view>
				<view>
					<navigator url="./myDebt">
						<image src="../../static/mine/mine03.png"></image>
					</navigator>
					<text>我的债务</text>
				</view>
				<view>
					<navigator url="./myFamily">
						<image src="../../static/mine/mine04.png"></image>
					</navigator>
					<text>我的家人</text>
				</view>
			</view>
		</view>
		<view class="bottom">
			<uni-list>
				<uni-list-item title="个人资料" showArrow to="profile"></uni-list-item>
				<uni-list-item title="账户管理" showArrow to="upAccount"></uni-list-item>
				<uni-list-item title="修改密码" showArrow to="pass"></uni-list-item>
				<uni-list-item title="关于我们" showArrow to="about"></uni-list-item>
			</uni-list>
		</view>
		<button type="primary" class="btn" @click="btn">退出</button>
	</view>
</template>

<script>
	export default {
		data(){
			return{
				name:""
			}
		},
		onShow(){
			this.init()
		},
		methods: {
			init(){
				const user = uni.getStorageSync('user')
				this.name = user.name
			},
			btn(){
				uni.showModal({
					title: '提示',
					content: '确定退出？',
					success: function(res) {
						if (res.confirm) {
							uni.navigateTo({
								url: '/pages/login'
							})
						} else if (res.cancel) {
							console.log('用户点击取消');
						}
					}
				});
			}
		}
	}
</script>

<style lang="scss" scoped>
	.top {
		height: 264rpx;
		padding: 33rpx 45rpx 0 45rpx;
		background-color: #ffcd54;
		color: #ffffff;
		text-align: center;

		text {
			font-size: 36rpx;
		}
		text:nth-child(2){
			float: right;
		}
	}

	.content {
		height: 410rpx;
		padding: 0 45rpx;

		.conTop {
			background-color: #ffffff;
			position: relative;
			top: -157rpx;
			border-radius: 10rpx;
			padding: 54rpx 60rpx;

			.imgOne {
				position: absolute;
				left: 59rpx;
				width: 27rpx;
				height: 91rpx;
				bottom: -55rpx;
				z-index: 100;
			}

			.imgTwo {
				position: absolute;
				right: 59rpx;
				width: 27rpx;
				height: 91rpx;
				bottom: -55rpx;
				z-index: 100;
			}

			.msg {
				display: flex;
				flex-direction: column;
				align-items: center;
				box-sizing: border-box;

				image {
					width: 94rpx;
					height: 94rpx;
					border-radius: 50%;
				}

				text:nth-child(2) {
					margin-top: 10rpx;
					font-size: 30rpx;
					color: #353536;
				}

				text:nth-child(3) {
					margin-top: 10rpx;
					font-size: 26rpx;
					color: #78787a;
				}
			}
		}

		.conBom {
			position: relative;
			top: -140rpx;
			background-color: #ffffff;
			border-radius: 10rpx;
			display: flex;
			justify-content: space-between;
			padding: 52rpx 45rpx 35rpx 51rpx;
			box-sizing: border-box;

			view {
				display: flex;
				flex-direction: column;
				align-items: center;

				image {
					width: 80rpx;
					height: 80rpx;
				}

				text {
					font-size: 22rpx;
					color: #414141;
					margin-top: 15rpx;
				}
			}
		}
	}
	.btn{
		margin-top: 20rpx;
		background-color: #ffcd54;
	}
</style>
