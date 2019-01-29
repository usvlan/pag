package com.gitee.pagesmanager.server.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.gitee.easyopen.ApiConfig;
import com.gitee.easyopen.ResultSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author tanghc
 */
@Configuration
public class ApiConfigConf {

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

        return apiConfig;
    }
}
