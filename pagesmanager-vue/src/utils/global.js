/*
注册全局方法
 */
import Vue from 'vue'
import ApiUtil from './ApiUtil'

Vue.prototype.post = function(uri, data, callback, errorCallback) {
  const that = this
  ApiUtil.post(uri, data, function(resp) {
    callback.call(that, resp)
  }, function(error) {
    errorCallback && errorCallback.call(that, error)
  })
}

Vue.prototype.get = function(uri, callback) {
  const that = this
  ApiUtil.get(uri, function(resp) {
    callback.call(that, resp)
  })
}

/**
 * tip，使用方式：this.tip('操作成功')，this.tip('错误', 'error')
 * @param msg 内容
 * @param type success / info / warning / error
 * @param stay 停留几秒，默认5秒
 */
Vue.prototype.tip = function(msg, type, stay) {
  stay = parseInt(stay) || 5
  this.$message({
    message: msg,
    type: type || 'success',
    duration: stay * 1000
  })
}

Vue.prototype.confirm = function(msg, okHandler, cancelHander) {
  const that = this
  this.$confirm(msg, '提示', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: 'warning'
  }).then(() => {
    okHandler.call(that)
  }).catch(() => {
    cancelHander && cancelHander.call(that)
  })
}
