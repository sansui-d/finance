<template>
	<view>
		<uni-nav-bar leftIcon="back" @clickLeft="myback" title="账户管理"></uni-nav-bar>
		<view class="act">
			<uni-segmented-control :current="current" :values="items" @clickItem="onClickItem" style-type="button" active-color="#ffcd54"></uni-segmented-control>
			<view class="content">
				<view v-if="current === 0">
					<view class="one">
						<text>账号：</text><input type="text" placeholder="请输入您创建账户的账号" v-model="actName" placeholder-class="inin" />
					</view>
					<view class="one">
						<text>密码：</text><input type="password" placeholder="请输入您创建账户的密码" v-model="actPass" placeholder-class="inin" />
					</view>
					<button type="primary" class="btn1" @click="btn1">创建</button>
				</view>
				<view v-if="current === 1">
					<view class="one">
						<text>账号：</text><input type="text" placeholder="请输入您加入账户的账号" v-model="actName1" placeholder-class="inin" />
					</view>
					<view class="one">
						<text>密码：</text><input type="password" placeholder="请输入您加入账户的密码" v-model="actPass1" placeholder-class="inin" />
					</view>
					<button type="primary" class="btn1" @click="btn2">加入</button>
				</view>
			</view>
		</view>
	</view>
</template>

<script>
	import {
		accountCreate,
		inActId,
		accountCome
	} from '@/common/api/api'
	export default {
		data() {
			return {
				items: ['创建账户', '加入账户'],
				current: 0,
				actName: "",
				actPass: "",
				actName1: "",
				actPass1: ""
			}
		},
		onShow() {

		},
		methods: {
			onClickItem(e) {
				if (this.current !== e.currentIndex) {
					this.current = e.currentIndex;
				}
			},
			btn1() {
				let actName = this.actName
				let actPass = this.actPass
				let houseHolder = uni.getStorageSync('userId')
				let surplus = 0
				accountCreate({
					actName: actName,
					actPass: actPass,
					houseHolder: houseHolder,
					surplus: surplus
				}).then(res => {
					console.log(res)
					if (res.is == '200') {
						let actId = res.account[0].actId
						uni.setStorage({
							key: 'actId',
							data: actId
						})
						uni.showModal({
							title: '提示',
							content: '创建成功',
							showCancel: false,
							confirmText: "开始使用",
							success: () => {
								let userId = uni.getStorageSync("userId")
								let actId = uni.getStorageSync('actId')
								inActId({
									userId: userId,
									actId: actId
								}).then(res => {
									if (res == "200") {
										uni.reLaunch({
											url: '/pages/home'
										})
									}
								})
							}
						});
					}
				})
			},
			btn2() {
				let actName1 = this.actName1
				let actPass1 = this.actPass1
				accountCome({
					actName: actName1,
					actPass: actPass1,
				}).then(res => {
					console.log(res)
					if (res.is == '200') {
						let actId = res.account[0].actId
						uni.setStorage({
							key: 'actId',
							data: actId
						})
						uni.showModal({
							title: '提示',
							content: '加入成功',
							showCancel: false,
							confirmText: "开始使用",
							success: () => {
								let userId = uni.getStorageSync("userId")
								let actId = uni.getStorageSync('actId')
								inActId({
									userId: userId,
									actId: actId
								}).then(res => {
									if (res == "200") {
										uni.reLaunch({
											url: '/pages/home'
										})
									}
								})
							}
						});
					}
				})
			}
		}
	}
</script>

<style scoped lang="scss">
	.act {
		padding: 40rpx 40rpx;
	}

	.one {
		display: flex;
		justify-content: space-between;
		align-items: center;
		padding-top: 40rpx;

		input {
			background-color: #FFFFFF;
			width: 85%;
			height: 60rpx;
		}
	}

	.btn1 {
		margin-top: 40rpx;
		background-color: #ffcd54;
	}
</style>
