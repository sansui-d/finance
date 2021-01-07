import config from '@/common/js/config'

// 请求失败的统一处理
function fail(error) {
	console.log(error)
	if (error.errMsg) {
		console.log(error.errMsg, '请求失败了')
	}
}

let code = {
	'401': '您没有访问权限',
	'403': '暂时无法访问',
	'404': '页面丢失',
	'408': '请求超时',
	'500': '后台错误'
}

const requestApi = {}

requestApi.request = function(url, data, method, success, complete) {
	let token = uni.getStorageSync('token')
	uni.request({
		url: config.BASE_URL + url,
		data,
		dataType: 'json',
		header: {
			'Content-Type': 'application/json;charset=UTF-8',
			'token': token || ''
		},
		method,
		// timeout: '10000',
		success,
		fail,
		complete:(res)=>{
			if (res.data == 88) {
				uni.showModal({
					title: '提示',
					content: '账号登录异常，请重新登录',
					confirmText:"重新登录",
					showCancel: false,
					success: () => {
						uni.navigateTo({
							url: '/pages/login'
						})
					}
				});
			}
		}
	})
}

requestApi.response = function() {
	if (response.data == 88) {
		uni.showModal({
			title: '提示',
			content: '账号登录异常，请重新登录',
			confirmText:"重新登录",
			showCancel: false,
			success: () => {
				uni.navigateTo({
					url: '/pages/login'
				})
			}
		});
	}
}

requestApi.post = function(url, data) {
	return new Promise(resovle => {
		requestApi.request(url, data, 'POST', res => {
			resovle(res.data)
		})
	})
}

requestApi.get = function(url, data) {
	return new Promise(resovle => {
		requestApi.request(url, data, 'GET', res => {
			resovle(res.data)
		})
	})
}

export default requestApi
