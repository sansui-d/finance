<template>
	<view>
		<view class="bg"></view>
		<view class="yuan">理财</view>
		<view class="inp">
			<view>
				<text>账号：</text><input type="text" placeholder="请输入您的账号" v-model="loginA.loginId" placeholder-class="inin" />
			</view>
			<view>
				<text>密码：</text><input type="text" password placeholder="请输入您的密码" v-model="loginA.loginPass" placeholder-class="inin" />
			</view>
			<view>
				<text>没有账号？</text>
				<button type="default" @click="register">点击注册</button>
			</view>
			<button type="primary" @click="loginUser">登录</button>
			<view class="bottom">
				<text>忘记密码？</text>
			</view>
		</view>
	</view>
</template>

<script>
	import {
		login
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
			loginUser() {
				login({
					loginId: this.loginA.loginId,
					loginPass: this.loginA.loginPass
				}).then(res => {
					if(res.isLogin=="200"){
						console.log(res)
					uni.setStorage({
						key: 'token',
						data: res.token
					})
					uni.setStorage({
						key: 'userId',
						data: res.user[0].userId,
					})
					uni.setStorage({
						key:'actId',
						data:res.user[0].actId,
					})
					uni.setStorage({
						key:'isLogin',
						data:true
					})
					uni.reLaunch({
						url: '/pages/home'
					})
					}else if(res.isLogin=="400"){
						console.log(1)
						uni.showModal({
						    title: '提示',
						    content: '账号或密码错误，请重新输入',
								showCancel:false,
								success: () => {
									this.loginA.loginId=""
									this.loginA.loginPass=""
								}
						});
					}
				})
			},
			register(){
				uni.reLaunch({
				    url: '/pages/mine/register'
				});
			}
		}
	}
</script>

<style lang="scss" scoped>
	.bg {
		background-image: url(../static/home/login.png);
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
