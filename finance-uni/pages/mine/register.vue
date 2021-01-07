<template>
	<view>
		<view class="bg"></view>
		<view class="yuan">注册</view>
		<view class="inp">
			<view>
				<text>账号：</text><input type="text" placeholder="请输入您的账号" v-model="loginA.loginId" placeholder-class="inin" />
			</view>
			<view>
				<text>密码：</text><input type="text" password placeholder="请输入您的密码" v-model="loginA.loginPass" placeholder-class="inin" />
			</view>
			<button type="primary" @click="register">注册</button>
			<button type="primary" @click="back">返回</button>
		</view>
	</view>
</template>

<script>
	import {
		register
	} from '@/common/api/api';
	export default {
		data() {
			return {
				loginA: {
					loginId: "",
					loginPass: ""
				}
			}
		},
		methods: {
			register() {
				let name = "user" + Math.ceil(Math.random() * 10000);
				let loginId = this.loginA.loginId
				let loginPass = this.loginA.loginPass
				let sex = "男"
				let phone = "123"
				let time = this.getTime()
				register({
					name:name,
					loginId:loginId,
					loginPass:loginPass,
					sex:sex,
					phone:phone,
					time:time
				}).then(res => {
					if(res=="200"){
						uni.showToast({
							title: '注册成功',
							duration: 2000
						});
						uni.reLaunch({
						  url: '/pages/login'
						});
					}
				})
			},
			getTime() {
				let date = new Date();
				let year = date.getFullYear();
				let month = date.getMonth() + 1;
				let day = date.getDate();
				return year + "年" + month + "月" + day + "日";
			},
			back(){
				uni.reLaunch({
				    url: '/pages/login'
				});
			}
		}
	}
</script>

<style lang="scss" scoped>
	.bg {
		background-image: url(@/static/home/login.png);
		background-size: cover;
		height: 558rpx;
		border-radius: 0 0 100% 100%/0 0 50% 50%;
	}

	.yuan {
		height: 126rpx;
		line-height: 126rpx;
		text-align: center;
		color: #FFFFFF;
		width: 126rpx;
		border-radius: 50%;
		background-color: #f6d365;
		position: relative;
		top: -70rpx;
		margin: 0 auto;
	}

	.inp {
		padding: 0 50rpx;

		view {
			margin-bottom: 20rpx;
			display: flex;
			align-items: center;
			justify-content: space-between;
			text{
				width: 130rpx;
			}

			input {
				width: 100%;
				height: 80rpx;
				border-radius: 6rpx;
				border: solid 2rpx #dadada;
				font-size: 30rpx;
				color: #555555;
				padding: 0 20rpx;

				/deep/.inin {
					color: #dadada;
				}
			}
		}

		view:nth-child(3) {
			justify-content: flex-end;

			text:nth-child(1) {
				font-size: 26rpx;
				color: #dbdbdb;
			}

			button {
				font-size: 20rpx;
				background-color: #F7f7f7;
				margin: 0;
				color: #f6d365;
			}
		}

		button {
			background-color: #ffcd54;
			margin-top: 20rpx;
		}

		.bottom {
			display: flex;
			justify-content: flex-end;
			margin-top: 20rpx;
			text-align: right;
			text {
				width: 100%;
				font-size: 28rpx;
				color: #9a9a9a;
			}
		}
	}
</style>
