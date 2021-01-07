<template>
	<view>
		<uni-nav-bar leftIcon="back" @clickLeft="myback" title="账户管理" rightText="保存" @clickRight="save"></uni-nav-bar>
		<view class="pro">
			<view class="title">账户</view>
			<input class="ip" name="input" placeholder="这是一个输入框" v-model="account.actName"/>
		</view>
		<view class="pro">
			<view class="title">密码</view>
			<input type="password" class="ip" name="input" placeholder="这是一个输入框" v-model="account.actPass"/>
		</view>
	</view>
</template>

<script>
	import {
		upAct
	} from '@/common/api/api'
	export default {
		data() {
			return {
				account: {}
			}
		},
		onShow() {
			this.init()
		},
		methods: {
			init() {
				this.account = uni.getStorageSync('account')
			},
			r(evt) {
				this.user.sex = evt.target.value
			},
			save() {
				const account = this.account
				uni.showModal({
					title: '提示',
					content: '确定保存？',
					success: function(res) {
						if (res.confirm) {
							upAct({
								actId:account.actId,
								actName:account.actName,
								actPass:account.actPass
							}).then(res => {
								console.log(res)
								if (res == 200) {
									uni.showToast({
										title: '保存成功',
										duration: 2000
									});
									uni.setStorage({
										key: 'account',
										data: account
									})
								}
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

<style scoped lang="scss">
	.pro{
		display: flex;
		background-color: #FFFFFF;
		padding: 20rpx 20rpx;
		border-bottom: 1rpx solid #f7f7f7;
		.title{
			width: 15%;
		}
		.ip{
			width: 100%;
		}
	}
</style>
