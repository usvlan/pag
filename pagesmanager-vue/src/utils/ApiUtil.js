import axios from 'axios'
import { Message } from 'element-ui'

// 创建axios实例
const client = axios.create({
  baseURL: process.env.BASE_API, // api 的 base_url
  timeout: 5000 // 请求超时时间
})

const ApiUtil = {
  /**
   * 请求接口
   * @param uri uri，如：goods.get,goods.get/1.0
   * @param data
   * @param callback
   */
  post: function(uri, data, callback, errorCallback) {
    const paramStr = JSON.stringify(data)
    if (!uri.endsWith('/')) {
      uri = uri + '/'
    }
    if (!uri.startsWith('/')) {
      uri = '/' + uri
    }
    client.post(uri, {
      data: encodeURIComponent(paramStr)
    })
      .then(function(response) {
        const resp = response.data
        const code = resp.code
        if (!code || code === '-9') {
          Message({
            message: '系统错误',
            type: 'error',
            duration: 5 * 1000
          })
          return
        }
        if (code === '-100' || code === '18' || code === '21') { // 未登录
          ApiUtil.logout()
          return
        }
        if (code === '0') { // 成功
          callback(resp)
        } else {
          Message({
            message: resp.msg,
            type: 'error',
            duration: 5 * 1000
          })
        }
      })
      .catch(function(error) {
        console.log('err' + error) // for debug
        errorCallback && errorCallback(error)
        Message({
          message: error.message,
          type: 'error',
          duration: 5 * 1000
        })
      })
  },
  logout: function() {
    console.log('logout')
  }
}

export default ApiUtil

