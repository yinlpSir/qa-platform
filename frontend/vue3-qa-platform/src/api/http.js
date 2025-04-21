
import axios from "axios"
import { viewLoading } from '@/store/ViewLoading'
import authentication from '@/store/authentication'
import { ElMessage } from "element-plus"
import router from "@/router/router"

// const router = useRouter()

//自定义 axios 实例
const http = axios.create({
  baseURL: 'http://127.0.0.1:8888/wdSystem',
  // baseURL: 'http://anitsunat.natapp1.cc/wdSystem',
  timeout: 5000,
})

// request interceptor
http.interceptors.request.use(function (config) {

  // 展示加载中的弹窗
  // if (config.viewLoading !== false && viewLoading.status === false) {
  //   viewLoading.status = true
  // }

  const auth = authentication()

  if (auth.token) config.headers.Authorization = 'Bearer ' + auth.token // 在请求头中设置个 token

  return config;

}, function (error) {
  // 对请求错误做些什么
  console.log(error)
  return Promise.reject(error);
});

// response interceptor
http.interceptors.response.use(
  response => {
    // if (viewLoading.status === true) {
    //   viewLoading.status = false
    // }

    return response.data
  }, error => {

    console.log(error)

    if (error.status == 401){
      router.push("/login")
      localStorage.clear()

      ElMessage.error(error.response.data.message)
      return ;
    }
  }
)

export default http;
