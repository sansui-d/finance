import Vue from 'vue';
import Vuex from 'vuex';
 
Vue.use(Vuex);
let store = new Vuex.Store({
    // 1. state
    state:{
        form:"",
        user:{},
        account:{},
        income:{},
        pay:{},
        insure:{},
        loan:{},
        debt:{},
        budget:{}
    },
 
    // // 2. getters
    getters:{
        // 参数列表state指的是state数据
        getform(state){
            return state.form;
        },
        getuser(state){
            return state.user;
        },
        getaccount(state){
            return state.state;
        },
        getincome(state){
            return state.income;
        },
        getpay(state){
            return state.pay;
        },
        getinsure(state){
            return state.insure;
        },
        getloan(state){
            return state.loan;
        },
        getdebt(state){
            return state.debt;
        },
        getbudget(state){
            return state.budget;
        }
    },
    // 3. actions
    // 通常跟api接口打交道
    actions:{
        setformvalue({commit,state}, name){
            commit("setform", name);
        },
    },
    // 4. mutations
    mutations:{
        // state指的是state的数据
        // name传递过来的数据
        setform(state, name){
            state.form = name;//将传参设置给state的form
        },
        setuser(state,name){
            state.user = name;
        },
        setaccount(state,name){
            state.account = name;
        },
        setincome(state,name){
            state.income = name;
        },
        setpay(state,name){
            state.pay = name;
        },
        setinsure(state,name){
            state.insure = name;
        },
        setloan(state,name){
            state.loan = name;
        },
        setdebt(state,name){
            state.debt = name;
        },
        setbudget(state,name){
            state.budget = name;
        }
    }
});
 
export default store;