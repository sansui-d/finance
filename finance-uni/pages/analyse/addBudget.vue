<template>
	<view>
		<uni-nav-bar leftIcon="back" @clickLeft="myback" title="增加预算" rightText="保存" @clickRight="save"></uni-nav-bar>
		<view class="add">
			<radio-group name="radio" @change="rad1">
				<label>
					<radio value="吃喝" color="#ffcd54" checked /><text>吃喝</text>
				</label>
				<label>
					<radio value="护肤" color="#ffcd54" /><text>护肤</text>
				</label>
				<label>
					<radio value="衣物" color="#ffcd54" /><text>衣物</text>
				</label>
				<label>
					<radio value="娱乐" color="#ffcd54" /><text>娱乐</text>
				</label>
			</radio-group>
			<view class="ipt">
				<text>输入金额</text>
				<input v-model="money" type="number" placeholder="请输入金额" name="input" />
			</view>
		</view>
	</view>
</template>

<script>
	import {
		addBudget
	} from '@/common/api/api'
	export default {
		data() {
			return {
				type: "吃喝",
				money: null
			}
		},
		onShow() {

		},
		methods: {
			rad1(e) {
				this.type = e.target.value
			},
			save() {
				let userId = uni.getStorageSync('userId')
				let actId = uni.getStorageSync('actId')
				let type = this.type
				let time = this.getTime()
				let money = this.money
				if (this.money !== null) {
					addBudget({
						userId: userId,
						actId: actId,
						type: type,
						time: time,
						money: money
					}).then(res => {
						if (res == "200") {
							uni.showToast({
								title: '保存成功',
								duration: 2000
							});
							this.money = null
						} else {
							uni.showToast({
								title: '保存失败',
								duration: 2000
							});
						}
					})
				} else {
					uni.showToast({
						title: '输入框不能为空',
						duration: 2000
					});
				}
			},
			getTime() {
				let date = new Date();
				let year = date.getFullYear();
				let month = date.getMonth() + 1;
				let day = date.getDate();
				return year + "年" + month + "月" + day + "日";
			}
		}

	}
</script>

<style scoped lang="scss">
	.add {
		padding: 40rpx 40rpx;
	}

	.ipt {
		margin-top: 20rpx;

		input {
			height: 68rpx;
			margin-top: 20rpx;
			background-color: #FFFFFF;
		}
	}
</style>
