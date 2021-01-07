import Vue from 'vue'
import App from './App'
import myJs from './common/js/myJs.js'
import config from './common/js/config.js'
import store from './common/store/index.js';

Vue.config.productionTip = false
Vue.prototype.$config = config

App.mpType = 'app'

Vue.mixin(myJs)
const app = new Vue({
    ...App,
		store
})
app.$mount()
