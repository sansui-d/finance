<template>
	<view>
		<uni-nav-bar leftIcon="back" @clickLeft="myback" title="添加收支"></uni-nav-bar>
		<view class="add">
			<form @submit="formSubmit" @reset="formReset">
				<view class="uni-form-item uni-column">
					<view class="title">选择收支</view>
					<radio-group name="radio" @change="rad1">
						<label>
							<radio value="支出" color="#ffcd54" checked/><text>支出</text>
						</label>
						<label>
							<radio value="收入" color="#ffcd54"  /><text>收入</text>
						</label>
					</radio-group>
				</view>
				<view class="uni-form-item uni-column" v-if="t=='支出'">
					<view class="title">选择类型</view>
					<radio-group name="radio1" @change="rad2">
						<label>
							<radio value="吃喝" color="#ffcd54" /><text>吃喝</text>
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
				</view>
				<view class="uni-form-item uni-column" v-if="t=='收入'">
					<view class="title">选择类型</view>
					<radio-group name="radio2" @change="rad3">
						<label>
							<radio value="工资" color="#ffcd54" /><text>工资</text>
						</label>
						<label>
							<radio value="兼职" color="#ffcd54" /><text>兼职</text>
						</label>
					</radio-group>
				</view>
				<view class="ipt">
					<text>输入金额</text>
					<input v-model="money" type="number" placeholder="请输入金额" name="input" />
				</view>
				<view class="uni-btn-v">
					<button form-type="submit">保存</button>
					<button type="default" form-type="reset">重置</button>
				</view>
			</form>
		</view>
	</view>
</template>

<script>
	import {
		addCome,
		userPay
	} from '@/common/api/api'
	export default {
		data() {
			return {
				box: 1,
				t: "支出",
				type1: "",
				type2: "",
				money: null
			}
		},
		methods: {
			formSubmit: function(e) {
				let actId = uni.getStorageSync('actId')
				let userId = uni.getStorageSync('userId')
				let inTime = this.getTime()
				let outTime = this.getTime()
				let money = this.money
				let type2 = this.type2
				let type1 = this.type1
				let surplus1 = Number(uni.getStorageSync('account').surplus) + Number(this.money)
				let surplus2 = Number(uni.getStorageSync('account').surplus) - Number(this.money)
				if (this.t == '收入'&&this.money!==null) {
					addCome({
						actId: actId,
						userId: userId,
						inTime: inTime,
						money: money,
						type: type2,
						surplus: surplus1
					}).then(res => {
						if (res == '200') {
							let account = uni.getStorageSync('account')
							account.surplus = surplus1
							uni.setStorage({
								key: 'account',
								data: account
							})
							uni.showToast({
								title: '保存成功',
								duration: 2000
							});
							this.money = null
						}
					})
				}else if(this.t=='支出'&&this.money!==null){
					userPay({
						actId: actId,
						userId: userId,
						outTime: outTime,
						money: money,
						type: type1,
						surplus: surplus2
					}).then(res =>{
						if (res == '200') {
							let account = uni.getStorageSync('account')
							account.surplus = surplus2
							uni.setStorage({
								key: 'account',
								data: account
							})
							uni.showToast({
								title: '保存成功',
								duration: 2000
							});
							this.money = null
						}
					})
				}
			},
			formReset: function(e) {
				console.log('清空数据')
			},
			rad1(evt) {
				this.t = evt.target.value
			},
			rad2(e) {
				this.type1 = e.target.value
			},
			rad3(z) {
				this.type2 = z.target.value
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

		.uni-form-item {
			padding-bottom: 20rpx;

			.title {
				margin-bottom: 10rpx;
			}
		}

		.ipt {
			input {
				height: 68rpx;
				margin-top: 20rpx;
				background-color: #FFFFFF;
			}
		}

		button {
			background-color: #ffcd54;
			border-radius: 5rpx;
			color: #FFFFFF;
			margin-top: 20rpx;
		}
	}
</style>
