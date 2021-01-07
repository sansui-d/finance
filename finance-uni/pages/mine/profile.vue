<template>
	<view>
		<uni-nav-bar leftIcon="back" @clickLeft="myback" title="个人资料" rightText="保存" @clickRight="save"></uni-nav-bar>
		<view class="pro">
			<view class="title">姓名</view>
			<input class="ip" name="input" placeholder="这是一个输入框" v-model="user.name" />
		</view>
		<view class="pro">
			<view class="title">电话</view>
			<input class="ip" name="input" placeholder="这是一个输入框" v-model="user.phone" />
		</view>
		<view class="pro">
			<view class="title">性别</view>
			<radio-group name="radio1" @change="r">
				<label>
					<radio color="#ffcd54" value="男" :checked="user.sex=='男'" /><text>男</text>
				</label>
				<label>
					<radio color="#ffcd54" value="女" :checked="user.sex=='女'" /><text>女</text>
				</label>
			</radio-group>
		</view>
	</view>
</template>

<script>
	import {
		upUser
	} from '@/common/api/api'
	export default {
		data() {
			return {
				user: {}
			}
		},
		onShow() {
			this.init()
		},
		methods: {
			init() {
				this.user = uni.getStorageSync('user')
			},
			r(evt) {
				this.user.sex = evt.target.value
			},
			save() {
				const user = this.user
				uni.showModal({
					title: '提示',
					content: '确定保存？',
					success: function(res) {
						if (res.confirm) {
							upUser({
								name: user.name,
								loginId: user.loginId,
								loginPass: user.loginPass,
								sex: user.sex,
								phone: user.phone,
								userId: user.userId
							}).then(res => {
								console.log(res)
								if (res == 200) {
									uni.showToast({
										title: '保存成功',
										duration: 2000
									});
									uni.setStorage({
										key: 'user',
										data: user
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
	.pro {
		display: flex;
		background-color: #FFFFFF;
		padding: 20rpx 20rpx;
		border-bottom: 1rpx solid #f7f7f7;

		.title {
			width: 15%;
		}

		.ip {
			width: 100%;
		}
	}
</style>
