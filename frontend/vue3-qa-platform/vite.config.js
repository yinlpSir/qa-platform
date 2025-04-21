import { fileURLToPath, URL } from 'node:url'

import { defineConfig } from 'vite'
import AutoImport from 'unplugin-auto-import/vite'
import Components from 'unplugin-vue-components/vite'
import { ElementPlusResolver } from 'unplugin-vue-components/resolvers'
import vue from '@vitejs/plugin-vue'

// https://vitejs.dev/config/
export default defineConfig({
  // ...
  plugins: [
    // ...
    AutoImport({
      resolvers: [ElementPlusResolver()],
    }),
    Components({
      resolvers: [ElementPlusResolver()],
    }),
    vue(),
  ],
  resolve: {
    alias: {
      '@': fileURLToPath(new URL('./src', import.meta.url))
    },
    proxy:{//配置 代理 转发的规则
      '/api':{// api 表示拦截以 /api开头的请求路径
        target:'http://127.0.0.1:8888/wdSystem/',//配置 此规则 转发的地址。即跨域的域名，不需要写路径
        changeOrigin:true,//是否开启跨域
        rewrite:(path)=>path.replace(/^\/api/,''),
      }
    },
  },
  server: {
    host: "0.0.0.0",
    port:5174
  }
})
