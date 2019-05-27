package com.gitee.pagesmanager.server.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.gitee.easyopen.ApiConfig;
import com.gitee.easyopen.ApiParam;
import com.gitee.easyopen.ApiParamParser;
import com.gitee.easyopen.ParamNames;
import com.gitee.easyopen.ResultSerializer;
import com.gitee.easyopen.interceptor.ApiInterceptor;
import com.gitee.easyopen.session.ApiSessionManager;
import com.gitee.pagesmanager.server.interceptor.LoginInterceptor;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.http.HttpServletRequest;

/**
 * @author tanghc
 */
@Configuration
public class ApiConfigConf {

    public static final String HEADER_TOKEN_NAME = ParamNames.ACCESS_TOKEN_NAME;

    @Value("${admin.access-token.timeout-minutes}")
    private String accessTokenTimeout;

    @Bean
    public ApiConfig apiConfig() {
        ApiConfig apiConfig = new ApiConfig();
        apiConfig.setJsonResultSerializer(new ResultSerializer() {
            @Override
            public String serialize(Object obj) {
                return JSON.toJSONString(obj,
                        SerializerFeature.WriteDateUseDateFormat
                        ,SerializerFeature.WriteMapNullValue
                        ,SerializerFeature.WriteNullListAsEmpty
                        ,SerializerFeature.WriteNullStringAsEmpty
                );
            }
        });

        ApiSessionManager apiSessionManager = new ApiSessionManager();
        // session有效期
        int timeout = NumberUtils.toInt(accessTokenTimeout, 30);
        apiSessionManager.setSessionTimeout(timeout);
        apiConfig.setSessionManager(apiSessionManager);
        // 登录拦截器
        apiConfig.setInterceptors(new ApiInterceptor[]{new LoginInterceptor()});

        apiConfig.setParamParser(new ApiParamParser() {
            @Override
            public ApiParam parse(HttpServletRequest request) {
                ApiParam param = super.parse(request);
                String accessToken = request.getHeader(HEADER_TOKEN_NAME);
                if (StringUtils.isNotBlank(accessToken)) {
                    param.put(ParamNames.ACCESS_TOKEN_NAME, accessToken);
                }
                return param;
            }
        });

        return apiConfig;
    }
}
