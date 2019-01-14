/**
请求示例：

 var data = $form.form('getData');
 ApiUtil.post('project.create', data, function (resp) {
	location.href = 'main.html';
});
*/
var ApiUtil = (function(){	
	var params = {};

	var url = '../api';

    (function() {
    	var aPairs, aTmp;  
        var queryString = window.location.search.toString();
        queryString = queryString.substr(1, queryString.length); //remove   "?"     
        aPairs = queryString.split("&");  
        for (var i = 0; i < aPairs.length; i++) {  
            aTmp = aPairs[i].split("=");  
            params[aTmp[0]] = decodeURIComponent(aTmp[1]);
        }  
    })();
    
    /* **************私有方法************** */
  	//Html编码获取Html转义实体
	function htmlEncode(value){ 
		return $('<div/>').text(value).html(); 
	} 
	//Html解码获取Html实体 
	function htmlDecode(value){ 
		return $('<div/>').html(value).text(); 
	} 



    return {    	
    	post:function(name,data,callback) {
    		var param = {
    			name : name,
				data : JSON.stringify(data)
			}
			$.ajax({
				url: url,
				type: 'post',
				cache: false,
				dataType: 'json',
				data: param,
				charset: 'utf-8',
				success: function (resp) {
					MaskUtil.unmask();
					var code = resp.code;
					if(!code || code == '-9') {
						MsgUtil.topMsg('系统错误');
						return;
					}
					if(code == '-100' || code == '18' || code == '21') { // 未登录
						ApiUtil.logout();
						return;
					}
					if(code == '0') { // 成功
						callback(resp);
					} else {
						MsgUtil.topMsg(resp.msg);
					}
				},
				error: function () {
					MsgUtil.topMsg('系统错误');
				}
			});
         }
    	,logout:function() {
            this.post('nologin.admin.logout',{},function (resp) {
            	ApiUtil.removeAccessToken();
                top.location = 'login.html?q=' + new Date().getTime();
            })
    	}
    	,setJwt:function(jwt) {
    		localStorage.setItem(JWT_KEY,jwt);
    	}
    	,removeJwt:function() {
    		localStorage.removeItem(JWT_KEY);
    	}
        ,setAccessToken:function (accessToken) {
            localStorage.setItem(ACCESS_TOKEN_KEY,accessToken);
        }
        ,removeAccessToken:function () {
            localStorage.removeItem(ACCESS_TOKEN_KEY);
        }
    	,getParam:function(key) {
    		return params[key];
    	}
    	,htmlEncode:function(value) {
    		return htmlEncode(value);
    	}
    	,htmlDecode:function(value) {
    		return htmlDecode(value);
    	}
    	,page:function(resId) {
    		window.location = 'main.html?resId=' + resId
    	}
    	,goMain:function() {
    		delete params.opt;
    		var q = $.param(params);
    		window.location = 'main.html?' + q;
    	}
    	,getUrl:function() {
    		return url;
    	}
    	,formatTime:function(time) {
    		return formatDate(new Date(time));
    	}
    };
})();