import './assets/main.css'

import { createApp } from 'vue'
import App from './App.vue'
// import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import router from '@/router/router'


// import '@css/iconfont.css'
//导入bootstrap
// import 'bootstrap/dist/css/bootstrap.css'
// 引入矢量图标样式
import "../src/assets/iconfont/iconfont.css"
import "../src/assets/iconfont/iconfont.js"

import { createPinia } from 'pinia'
import piniaPluginPersistedstate from 'pinia-plugin-persistedstate'

const pinia = createPinia()
pinia.use(piniaPluginPersistedstate)

const app = createApp(App)
app.use(pinia)
app.use(router)

app.mount('#app')
