import Vue from 'vue';
import Vuex from 'vuex';

Vue.use(Vuex);
let store = new Vuex.Store({
	// 1. state
	state: {
		account: {},
		income:[],
		pay:[],
		user:{}
	},

	// // 2. getters
	getters: {
		// 参数列表state指的是state数据
		getaccount(state) {
			return state.account;
		},
		getincome(state) {
			return state.income;
		},
		getpay(state) {
			return state.pay;
		},
		getuser(state) {
			return state.user;
		},
	},
	// 3. actions
	// 通常跟api接口打交道
	actions: {
		setformvalue({
			commit,
			state
		}, name) {
			commit("setform", name);
		},
	},
	// 4. mutations
	mutations: {
		// state指的是state的数据
		// name传递过来的数据
		setuser(state, name) {
			state.user = name; //将传参设置给state的form
		},
		setaccount(state, name) {
			state.account = name; //将传参设置给state的form
		},
		setpay(state, name) {
			state.pay = name; //将传参设置给state的form
		},
		setincome(state, name) {
			state.income = name; //将传参设置给state的form
		},
	}
});

export default store;
