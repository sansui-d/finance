import requestApi from './request.js'
import config from '../js/config.js'


/////////////////////  登录  ///////////////////////
// 登录
export const login = data => {
	return requestApi.post('/login/loginUser', data)
}
// 获取用户信息
export const getUserInfo = data => {
	return requestApi.post('/user/getUser', data)
}
//获取所有贷款
export const getLoan = data => {
	return requestApi.get('/loan/allLoan', data)
}
//获取所有保险
export const getInsure = data => {
	return requestApi.get('/insure/allInsure', data)
}
//账户详情
export const accountByAll = data => {
	return requestApi.get('/account/accountByAll', data)
}
//用户查询债务
export const userDebt = data => {
	return requestApi.get('/debt/userDebt', data)
}
//购买保险或贷款
export const addDebt = data => {
	return requestApi.post('/debt/addDebt', data)
}
//债务还款
export const deleteDebt = data => {
	return requestApi.post('/debt/deleteDebt', data)
}
//用户修改个人信息
export const upUser = data => {
	return requestApi.post('/user/upUser', data)
}
//用户添加收入
export const addCome = data => {
	return requestApi.post('/income/addCome', data)
}
//用户添加支出
export const userPay = data => {
	return requestApi.post('/pay/userPay', data)
}
//添加预算
export const addBudget = data => {
	return requestApi.post('/budget/addBudget',data)
}
//获取所有预算
export const selectBudget = data => {
	return requestApi.get('/budget/selectBudget',data)
}
//注册
export const register = data => {
	return requestApi.post('/user/register',data)
}
//创建账户
export const accountCreate = data => {
	return requestApi.post('/account/accountCreate', data)
}
export const inActId = data => {
	return requestApi.post('/account/inActId',data)
}
//加入账户
export const accountCome = data => {
	return requestApi.post('/account/accountCome',data)
}
//修改账户信息
export const upAct = data => {
	return requestApi.post('/account/upAct',data)
}